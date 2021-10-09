package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.mopub.common.AdReport;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.LocationService;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.network.AdLoader;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.SingleImpression;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

public class AdViewController {
    private static final double BACKOFF_FACTOR = 1.5d;
    static final int DEFAULT_REFRESH_TIME_MILLISECONDS = 60000;
    private static final int MAX_REFRESH_TIME_MILLISECONDS = 600000;
    private static final FrameLayout.LayoutParams WRAP_AND_CENTER_LAYOUT_PARAMS = new FrameLayout.LayoutParams(-2, -2, 17);
    private static final WeakHashMap<View, Boolean> sViewShouldHonorServerDimensions = new WeakHashMap<>();
    private Request mActiveRequest;
    private final AdLoader.Listener mAdListener;
    AdLoader mAdLoader;
    private AdResponse mAdResponse;
    private String mAdUnitId;
    private boolean mAdWasLoaded;
    int mBackoffPower = 1;
    private final long mBroadcastIdentifier;
    private Context mContext;
    private boolean mCurrentAutoRefreshStatus = true;
    private String mCustomEventClassName;
    private Handler mHandler;
    private boolean mHasOverlay;
    private boolean mIsDestroyed;
    private boolean mIsTesting;
    private String mKeywords;
    private String mLastTrackedRequestId;
    private Map<String, Object> mLocalExtras = new HashMap();
    /* access modifiers changed from: private */
    public MoPubView mMoPubView;
    private final Runnable mRefreshRunnable;
    private Integer mRefreshTimeMillis;
    private Point mRequestedAdSize;
    private boolean mShouldAllowAutoRefresh = true;
    private WebViewAdUrlGenerator mUrlGenerator;
    private String mUserDataKeywords;
    private WindowInsets mWindowInsets;

    public void setLocation(Location location) {
    }

    public static void setShouldHonorServerDimensions(View view) {
        sViewShouldHonorServerDimensions.put(view, true);
    }

    private static boolean getShouldHonorServerDimensions(View view) {
        return sViewShouldHonorServerDimensions.get(view) != null;
    }

    public AdViewController(Context context, MoPubView moPubView) {
        this.mContext = context;
        this.mMoPubView = moPubView;
        this.mBroadcastIdentifier = Utils.generateUniqueId();
        this.mUrlGenerator = new WebViewAdUrlGenerator(this.mContext.getApplicationContext());
        this.mAdListener = new AdLoader.Listener() {
            public void onSuccess(AdResponse adResponse) {
                AdViewController.this.onAdLoadSuccess(adResponse);
            }

            public void onErrorResponse(VolleyError volleyError) {
                AdViewController.this.onAdLoadError(volleyError);
            }
        };
        this.mRefreshRunnable = new Runnable() {
            public void run() {
                MoPubView access$000 = AdViewController.this.mMoPubView;
                if (access$000 != null) {
                    AdViewController.this.setRequestedAdSize(access$000.resolveAdSize());
                }
                AdViewController.this.internalLoadAd();
            }
        };
        this.mRefreshTimeMillis = Integer.valueOf(DEFAULT_REFRESH_TIME_MILLISECONDS);
        this.mHandler = new Handler();
        this.mLastTrackedRequestId = "";
    }

    /* access modifiers changed from: package-private */
    public void onAdLoadSuccess(AdResponse adResponse) {
        this.mBackoffPower = 1;
        this.mAdResponse = adResponse;
        this.mCustomEventClassName = adResponse.getCustomEventClassName();
        this.mRefreshTimeMillis = this.mAdResponse.getRefreshTimeMillis();
        this.mActiveRequest = null;
        loadCustomEvent(this.mMoPubView, adResponse.getCustomEventClassName(), adResponse.getServerExtras());
        scheduleRefreshTimerIfEnabled();
    }

    /* access modifiers changed from: package-private */
    public void onAdLoadError(VolleyError volleyError) {
        if (volleyError instanceof MoPubNetworkError) {
            MoPubNetworkError moPubNetworkError = (MoPubNetworkError) volleyError;
            if (moPubNetworkError.getRefreshTimeMillis() != null) {
                this.mRefreshTimeMillis = moPubNetworkError.getRefreshTimeMillis();
            }
        }
        MoPubErrorCode errorCodeFromVolleyError = getErrorCodeFromVolleyError(volleyError, this.mContext);
        if (errorCodeFromVolleyError == MoPubErrorCode.SERVER_ERROR) {
            this.mBackoffPower++;
        }
        adDidFail(errorCodeFromVolleyError);
    }

    /* access modifiers changed from: package-private */
    public void loadCustomEvent(MoPubView moPubView, String str, Map<String, String> map) {
        Preconditions.checkNotNull(map);
        if (moPubView == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Can't load an ad in this ad view because it was destroyed.");
            return;
        }
        moPubView.loadCustomEvent(str, map);
    }

    static MoPubErrorCode getErrorCodeFromVolleyError(VolleyError volleyError, Context context) {
        NetworkResponse networkResponse = volleyError.networkResponse;
        if (volleyError instanceof MoPubNetworkError) {
            int i = AnonymousClass4.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()];
            if (i == 1) {
                return MoPubErrorCode.WARMUP;
            }
            if (i != 2) {
                return MoPubErrorCode.UNSPECIFIED;
            }
            return MoPubErrorCode.NO_FILL;
        } else if (networkResponse == null) {
            if (!DeviceUtils.isNetworkAvailable(context)) {
                return MoPubErrorCode.NO_CONNECTION;
            }
            return MoPubErrorCode.UNSPECIFIED;
        } else if (volleyError.networkResponse.statusCode >= 400) {
            return MoPubErrorCode.SERVER_ERROR;
        } else {
            return MoPubErrorCode.UNSPECIFIED;
        }
    }

    /* renamed from: com.mopub.mobileads.AdViewController$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$network$MoPubNetworkError$Reason;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.mopub.network.MoPubNetworkError$Reason[] r0 = com.mopub.network.MoPubNetworkError.Reason.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason = r0
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.WARMING_UP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$network$MoPubNetworkError$Reason     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.NO_FILL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.AdViewController.AnonymousClass4.<clinit>():void");
        }
    }

    public MoPubView getMoPubView() {
        return this.mMoPubView;
    }

    public void loadAd() {
        this.mBackoffPower = 1;
        internalLoadAd();
    }

    /* access modifiers changed from: private */
    public void internalLoadAd() {
        this.mAdWasLoaded = true;
        if (TextUtils.isEmpty(this.mAdUnitId)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Can't load an ad in this ad view because the ad unit ID is not set. Did you forget to call setAdUnitId()?");
            adDidFail(MoPubErrorCode.MISSING_AD_UNIT_ID);
        } else if (!isNetworkAvailable()) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Can't load an ad because there is no network connectivity.");
            adDidFail(MoPubErrorCode.NO_CONNECTION);
        } else {
            loadNonJavascript(generateAdUrl(), (MoPubError) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void loadNonJavascript(String str, MoPubError moPubError) {
        if (str == null) {
            adDidFail(MoPubErrorCode.NO_FILL);
            return;
        }
        if (!str.startsWith("javascript:")) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Loading url: " + str);
        }
        if (this.mActiveRequest == null) {
            fetchAd(str, moPubError);
        } else if (!TextUtils.isEmpty(this.mAdUnitId)) {
            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent2, "Already loading an ad for " + this.mAdUnitId + ", wait to finish.");
        }
    }

    @Deprecated
    public void reload() {
        loadAd();
    }

    /* access modifiers changed from: package-private */
    public boolean loadFailUrl(MoPubErrorCode moPubErrorCode) {
        if (moPubErrorCode == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, Integer.valueOf(MoPubErrorCode.UNSPECIFIED.getIntCode()), MoPubErrorCode.UNSPECIFIED);
        } else {
            MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, Integer.valueOf(moPubErrorCode.getIntCode()), moPubErrorCode);
        }
        AdLoader adLoader = this.mAdLoader;
        if (adLoader == null || !adLoader.hasMoreAds()) {
            adDidFail(MoPubErrorCode.NO_FILL);
            return false;
        }
        loadNonJavascript("", moPubErrorCode);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setNotLoading() {
        Request request = this.mActiveRequest;
        if (request != null) {
            if (!request.isCanceled()) {
                this.mActiveRequest.cancel();
            }
            this.mActiveRequest = null;
        }
        this.mAdLoader = null;
    }

    /* access modifiers changed from: package-private */
    public void creativeDownloadSuccess() {
        scheduleRefreshTimerIfEnabled();
        AdLoader adLoader = this.mAdLoader;
        if (adLoader == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "mAdLoader is not supposed to be null");
            return;
        }
        adLoader.creativeDownloadSuccess();
        this.mAdLoader = null;
    }

    public String getKeywords() {
        return this.mKeywords;
    }

    public void setKeywords(String str) {
        this.mKeywords = str;
    }

    public String getUserDataKeywords() {
        if (!MoPub.canCollectPersonalInformation()) {
            return null;
        }
        return this.mUserDataKeywords;
    }

    public void setUserDataKeywords(String str) {
        if (!MoPub.canCollectPersonalInformation()) {
            this.mUserDataKeywords = null;
        } else {
            this.mUserDataKeywords = str;
        }
    }

    public Location getLocation() {
        return LocationService.getLastKnownLocation(this.mContext);
    }

    /* access modifiers changed from: package-private */
    public void setRequestedAdSize(Point point) {
        this.mRequestedAdSize = point;
    }

    public void setWindowInsets(WindowInsets windowInsets) {
        this.mWindowInsets = windowInsets;
    }

    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    public String getCustomEventClassName() {
        return this.mCustomEventClassName;
    }

    public void setAdUnitId(String str) {
        this.mAdUnitId = str;
    }

    public long getBroadcastIdentifier() {
        return this.mBroadcastIdentifier;
    }

    public int getAdWidth() {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse == null || adResponse.getWidth() == null) {
            return 0;
        }
        return this.mAdResponse.getWidth().intValue();
    }

    public int getAdHeight() {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse == null || adResponse.getHeight() == null) {
            return 0;
        }
        return this.mAdResponse.getHeight().intValue();
    }

    @Deprecated
    public boolean getAutorefreshEnabled() {
        return getCurrentAutoRefreshStatus();
    }

    public boolean getCurrentAutoRefreshStatus() {
        return this.mCurrentAutoRefreshStatus;
    }

    /* access modifiers changed from: package-private */
    public void pauseRefresh() {
        setAutoRefreshStatus(false);
    }

    /* access modifiers changed from: package-private */
    public void resumeRefresh() {
        if (this.mShouldAllowAutoRefresh && !this.mHasOverlay) {
            setAutoRefreshStatus(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void setShouldAllowAutoRefresh(boolean z) {
        this.mShouldAllowAutoRefresh = z;
        setAutoRefreshStatus(z);
    }

    private void setAutoRefreshStatus(boolean z) {
        if (this.mAdWasLoaded && this.mCurrentAutoRefreshStatus != z) {
            String str = z ? "enabled" : "disabled";
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Refresh " + str + " for ad unit (" + this.mAdUnitId + ").");
        }
        this.mCurrentAutoRefreshStatus = z;
        if (this.mAdWasLoaded && z) {
            scheduleRefreshTimerIfEnabled();
        } else if (!this.mCurrentAutoRefreshStatus) {
            cancelRefreshTimer();
        }
    }

    /* access modifiers changed from: package-private */
    public void engageOverlay() {
        this.mHasOverlay = true;
        pauseRefresh();
    }

    /* access modifiers changed from: package-private */
    public void dismissOverlay() {
        this.mHasOverlay = false;
        resumeRefresh();
    }

    public AdReport getAdReport() {
        String str = this.mAdUnitId;
        if (str == null || this.mAdResponse == null) {
            return null;
        }
        return new AdReport(str, ClientMetadata.getInstance(this.mContext), this.mAdResponse);
    }

    public boolean getTesting() {
        return this.mIsTesting;
    }

    public void setTesting(boolean z) {
        this.mIsTesting = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    /* access modifiers changed from: package-private */
    public void cleanup() {
        if (!this.mIsDestroyed) {
            setNotLoading();
            setAutoRefreshStatus(false);
            cancelRefreshTimer();
            this.mMoPubView = null;
            this.mContext = null;
            this.mUrlGenerator = null;
            this.mLastTrackedRequestId = "";
            this.mIsDestroyed = true;
        }
    }

    /* access modifiers changed from: package-private */
    public Integer getAdTimeoutDelay(int i) {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse == null) {
            return Integer.valueOf(i);
        }
        return adResponse.getAdTimeoutMillis(i);
    }

    /* access modifiers changed from: package-private */
    public void trackImpression() {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse != null) {
            String requestId = adResponse.getRequestId();
            if (this.mLastTrackedRequestId.equals(requestId)) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Ignoring duplicate impression.");
                return;
            }
            if (requestId != null) {
                this.mLastTrackedRequestId = requestId;
            }
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) this.mAdResponse.getImpressionTrackingUrls(), this.mContext);
            new SingleImpression(this.mAdResponse.getAdUnitId(), this.mAdResponse.getImpressionData()).sendImpression();
        }
    }

    /* access modifiers changed from: package-private */
    public void registerClick() {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse != null) {
            TrackingRequest.makeTrackingHttpRequest(adResponse.getClickTrackingUrl(), this.mContext);
        }
    }

    /* access modifiers changed from: package-private */
    public void fetchAd(String str, MoPubError moPubError) {
        MoPubView moPubView = getMoPubView();
        if (moPubView == null || this.mContext == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Can't load an ad in this ad view because it was destroyed.");
            setNotLoading();
            return;
        }
        synchronized (this) {
            if (this.mAdLoader == null || !this.mAdLoader.hasMoreAds()) {
                this.mAdLoader = new AdLoader(str, moPubView.getAdFormat(), this.mAdUnitId, this.mContext, this.mAdListener);
            }
        }
        this.mActiveRequest = this.mAdLoader.loadNextAd(moPubError);
    }

    /* access modifiers changed from: package-private */
    public void forceRefresh() {
        setNotLoading();
        loadAd();
    }

    /* access modifiers changed from: package-private */
    public String generateAdUrl() {
        String str = null;
        if (this.mUrlGenerator == null) {
            return null;
        }
        boolean canCollectPersonalInformation = MoPub.canCollectPersonalInformation();
        AdUrlGenerator withKeywords = this.mUrlGenerator.withAdUnitId(this.mAdUnitId).withKeywords(this.mKeywords);
        if (canCollectPersonalInformation) {
            str = this.mUserDataKeywords;
        }
        withKeywords.withUserDataKeywords(str).withRequestedAdSize(this.mRequestedAdSize).withWindowInsets(this.mWindowInsets);
        return this.mUrlGenerator.generateUrlString(Constants.HOST);
    }

    /* access modifiers changed from: package-private */
    public void adDidFail(MoPubErrorCode moPubErrorCode) {
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Ad failed to load.");
        setNotLoading();
        MoPubView moPubView = getMoPubView();
        if (moPubView != null) {
            if (!TextUtils.isEmpty(this.mAdUnitId)) {
                scheduleRefreshTimerIfEnabled();
            }
            moPubView.adFailed(moPubErrorCode);
        }
    }

    /* access modifiers changed from: package-private */
    public void scheduleRefreshTimerIfEnabled() {
        Integer num;
        cancelRefreshTimer();
        if (this.mCurrentAutoRefreshStatus && (num = this.mRefreshTimeMillis) != null && num.intValue() > 0) {
            this.mHandler.postDelayed(this.mRefreshRunnable, Math.min(600000, ((long) this.mRefreshTimeMillis.intValue()) * ((long) Math.pow(BACKOFF_FACTOR, (double) this.mBackoffPower))));
        }
    }

    /* access modifiers changed from: package-private */
    public void setLocalExtras(Map<String, Object> map) {
        TreeMap treeMap;
        if (map == null) {
            treeMap = new TreeMap();
        }
        this.mLocalExtras = treeMap;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> getLocalExtras() {
        return this.mLocalExtras != null ? new TreeMap(this.mLocalExtras) : new TreeMap();
    }

    private void cancelRefreshTimer() {
        this.mHandler.removeCallbacks(this.mRefreshRunnable);
    }

    private boolean isNetworkAvailable() {
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        if (!DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setAdContentView(final View view) {
        this.mHandler.post(new Runnable() {
            public void run() {
                MoPubView moPubView = AdViewController.this.getMoPubView();
                if (moPubView != null) {
                    moPubView.removeAllViews();
                    View view = view;
                    moPubView.addView(view, AdViewController.this.getAdLayoutParams(view));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public FrameLayout.LayoutParams getAdLayoutParams(View view) {
        Integer num;
        AdResponse adResponse = this.mAdResponse;
        Integer num2 = null;
        if (adResponse != null) {
            num2 = adResponse.getWidth();
            num = this.mAdResponse.getHeight();
        } else {
            num = null;
        }
        if (num2 == null || num == null || !getShouldHonorServerDimensions(view) || num2.intValue() <= 0 || num.intValue() <= 0) {
            return WRAP_AND_CENTER_LAYOUT_PARAMS;
        }
        return new FrameLayout.LayoutParams(Dips.asIntPixels((float) num2.intValue(), this.mContext), Dips.asIntPixels((float) num.intValue(), this.mContext), 17);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public Integer getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setRefreshTimeMillis(Integer num) {
        this.mRefreshTimeMillis = num;
    }
}
