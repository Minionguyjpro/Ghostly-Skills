package com.mopub.mobileads;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.mopub.common.AdFormat;
import com.mopub.common.AdReport;
import com.mopub.common.MoPub;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ManifestUtils;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Visibility;
import com.mopub.mobileads.base.R;
import com.mopub.mobileads.factories.AdViewControllerFactory;
import java.util.Map;
import java.util.TreeMap;

public class MoPubView extends FrameLayout {
    private static final String CUSTOM_EVENT_BANNER_ADAPTER_FACTORY = "com.mopub.mobileads.factories.CustomEventBannerAdapterFactory";
    protected AdViewController mAdViewController;
    private BannerAdListener mBannerAdListener;
    private Context mContext;
    protected Object mCustomEventBannerAdapter;
    private MoPubAdSize mMoPubAdSize;
    private BroadcastReceiver mScreenStateReceiver;
    /* access modifiers changed from: private */
    public int mScreenVisibility;

    public interface BannerAdListener {
        void onBannerClicked(MoPubView moPubView);

        void onBannerCollapsed(MoPubView moPubView);

        void onBannerExpanded(MoPubView moPubView);

        void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode);

        void onBannerLoaded(MoPubView moPubView);
    }

    interface MoPubAdSizeInt {
        public static final int HEIGHT_250_INT = 250;
        public static final int HEIGHT_280_INT = 280;
        public static final int HEIGHT_50_INT = 50;
        public static final int HEIGHT_90_INT = 90;
        public static final int MATCH_VIEW_INT = -1;
    }

    @Deprecated
    public String getClickTrackingUrl() {
        return null;
    }

    @Deprecated
    public String getResponseString() {
        return null;
    }

    @Deprecated
    public void setLocation(Location location) {
    }

    @Deprecated
    public void setTimeout(int i) {
    }

    public enum MoPubAdSize implements MoPubAdSizeInt {
        MATCH_VIEW(-1),
        HEIGHT_50(50),
        HEIGHT_90(90),
        HEIGHT_250(MoPubAdSizeInt.HEIGHT_250_INT),
        HEIGHT_280(MoPubAdSizeInt.HEIGHT_280_INT);
        
        private final int mSizeInt;

        private MoPubAdSize(int i) {
            this.mSizeInt = i;
        }

        public static MoPubAdSize valueOf(int i) {
            if (i == 50) {
                return HEIGHT_50;
            }
            if (i == 90) {
                return HEIGHT_90;
            }
            if (i == 250) {
                return HEIGHT_250;
            }
            if (i != 280) {
                return MATCH_VIEW;
            }
            return HEIGHT_280;
        }

        public int toInt() {
            return this.mSizeInt;
        }
    }

    public MoPubView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MoPubView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMoPubAdSize = getMoPubAdSizeFromAttributeSet(context, attributeSet, MoPubAdSize.MATCH_VIEW);
        ManifestUtils.checkWebViewActivitiesDeclared(context);
        this.mContext = context;
        this.mScreenVisibility = getVisibility();
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        this.mAdViewController = AdViewControllerFactory.create(context, this);
        registerScreenStateBroadcastReceiver();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (Build.VERSION.SDK_INT >= 28) {
            setWindowInsets(getRootWindowInsets());
        }
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (Build.VERSION.SDK_INT >= 28) {
            setWindowInsets(windowInsets);
        }
        return super.onApplyWindowInsets(windowInsets);
    }

    private void registerScreenStateBroadcastReceiver() {
        this.mScreenStateReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (Visibility.isScreenVisible(MoPubView.this.mScreenVisibility) && intent != null) {
                    String action = intent.getAction();
                    if ("android.intent.action.USER_PRESENT".equals(action)) {
                        MoPubView.this.setAdVisibility(0);
                    } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                        MoPubView.this.setAdVisibility(8);
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.mContext.registerReceiver(this.mScreenStateReceiver, intentFilter);
    }

    private void unregisterScreenStateBroadcastReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mScreenStateReceiver);
        } catch (Exception unused) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Failed to unregister screen state broadcast receiver (never registered).");
        }
    }

    public void loadAd(MoPubAdSize moPubAdSize) {
        setAdSize(moPubAdSize);
        loadAd();
    }

    public void loadAd() {
        if (this.mAdViewController != null) {
            MoPubLog.log(MoPubLog.AdLogEvent.LOAD_ATTEMPTED, new Object[0]);
            this.mAdViewController.setRequestedAdSize(resolveAdSize());
            this.mAdViewController.loadAd();
        }
    }

    public void destroy() {
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Destroy() called");
        unregisterScreenStateBroadcastReceiver();
        removeAllViews();
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.cleanup();
            this.mAdViewController = null;
        }
        if (this.mCustomEventBannerAdapter != null) {
            invalidateAdapter();
            this.mCustomEventBannerAdapter = null;
        }
    }

    private void invalidateAdapter() {
        Object obj = this.mCustomEventBannerAdapter;
        if (obj != null) {
            try {
                new Reflection.MethodBuilder(obj, "invalidate").setAccessible().execute();
            } catch (Exception e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Error invalidating adapter", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Integer getAdTimeoutDelay(int i) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController == null) {
            return Integer.valueOf(i);
        }
        return adViewController.getAdTimeoutDelay(i);
    }

    /* access modifiers changed from: protected */
    public boolean loadFailUrl(MoPubErrorCode moPubErrorCode) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController == null) {
            return false;
        }
        return adViewController.loadFailUrl(moPubErrorCode);
    }

    /* access modifiers changed from: protected */
    public void loadCustomEvent(String str, Map<String, String> map) {
        if (this.mAdViewController != null) {
            if (TextUtils.isEmpty(str)) {
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Couldn't invoke custom event because the server did not specify one.");
                loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                return;
            }
            if (this.mCustomEventBannerAdapter != null) {
                invalidateAdapter();
            }
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Loading custom event adapter.");
            if (Reflection.classFound(CUSTOM_EVENT_BANNER_ADAPTER_FACTORY)) {
                try {
                    Object execute = new Reflection.MethodBuilder((Object) null, "create").setStatic(Class.forName(CUSTOM_EVENT_BANNER_ADAPTER_FACTORY)).addParam(MoPubView.class, this).addParam(String.class, str).addParam(Map.class, map).addParam(Long.TYPE, Long.valueOf(this.mAdViewController.getBroadcastIdentifier())).addParam(AdReport.class, this.mAdViewController.getAdReport()).execute();
                    this.mCustomEventBannerAdapter = execute;
                    new Reflection.MethodBuilder(execute, "loadAd").setAccessible().execute();
                } catch (Exception e) {
                    MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Error loading custom event", e);
                }
            } else {
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Could not load custom event -- missing banner module");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void registerClick() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.registerClick();
            adClicked();
        }
    }

    /* access modifiers changed from: protected */
    public void trackNativeImpression() {
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Tracking impression. MoPubView internal.");
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.trackImpression();
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        if (Visibility.hasScreenVisibilityChanged(this.mScreenVisibility, i)) {
            this.mScreenVisibility = i;
            setAdVisibility(i);
        }
    }

    /* access modifiers changed from: private */
    public void setAdVisibility(int i) {
        if (this.mAdViewController != null) {
            if (Visibility.isScreenVisible(i)) {
                this.mAdViewController.resumeRefresh();
            } else {
                this.mAdViewController.pauseRefresh();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void adLoaded() {
        MoPubLog.log(MoPubLog.AdLogEvent.LOAD_SUCCESS, new Object[0]);
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerLoaded(this);
        }
    }

    /* access modifiers changed from: protected */
    public void adFailed(MoPubErrorCode moPubErrorCode) {
        MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, Integer.valueOf(moPubErrorCode.getIntCode()), moPubErrorCode);
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerFailed(this, moPubErrorCode);
        }
    }

    /* access modifiers changed from: protected */
    public void adPresentedOverlay() {
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerExpanded(this);
        }
    }

    /* access modifiers changed from: protected */
    public void adClosed() {
        MoPubLog.log(MoPubLog.AdLogEvent.DID_DISAPPEAR, new Object[0]);
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerCollapsed(this);
        }
    }

    /* access modifiers changed from: protected */
    public void adClicked() {
        MoPubLog.log(MoPubLog.AdLogEvent.CLICKED, new Object[0]);
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerClicked(this);
        }
    }

    /* access modifiers changed from: protected */
    public void creativeDownloaded() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.creativeDownloadSuccess();
        }
        adLoaded();
    }

    private MoPubAdSize getMoPubAdSizeFromAttributeSet(Context context, AttributeSet attributeSet, MoPubAdSize moPubAdSize) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.MoPubView, 0, 0);
        try {
            moPubAdSize = MoPubAdSize.valueOf(obtainStyledAttributes.getInteger(R.styleable.MoPubView_moPubAdSize, moPubAdSize.toInt()));
        } catch (Resources.NotFoundException e) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM_WITH_THROWABLE, "Encountered a problem while setting the MoPubAdSize", e);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        obtainStyledAttributes.recycle();
        return moPubAdSize;
    }

    /* access modifiers changed from: protected */
    public Point resolveAdSize() {
        Point point = new Point(getWidth(), getHeight());
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (!(getParent() == null || layoutParams == null || layoutParams.width >= 0)) {
            point.x = ((View) getParent()).getWidth();
        }
        if (this.mMoPubAdSize != MoPubAdSize.MATCH_VIEW) {
            point.y = (int) Math.ceil((double) (((float) this.mMoPubAdSize.toInt()) * this.mContext.getResources().getDisplayMetrics().density));
        } else if (!(getParent() == null || layoutParams == null || layoutParams.height >= 0)) {
            point.y = ((View) getParent()).getHeight();
        }
        return point;
    }

    public void setAdUnitId(String str) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setAdUnitId(str);
        }
    }

    public String getAdUnitId() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getAdUnitId();
        }
        return null;
    }

    public void setKeywords(String str) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setKeywords(str);
        }
    }

    public String getKeywords() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getKeywords();
        }
        return null;
    }

    public void setUserDataKeywords(String str) {
        if (this.mAdViewController != null && MoPub.canCollectPersonalInformation()) {
            this.mAdViewController.setUserDataKeywords(str);
        }
    }

    public String getUserDataKeywords() {
        if (this.mAdViewController == null || !MoPub.canCollectPersonalInformation()) {
            return null;
        }
        return this.mAdViewController.getUserDataKeywords();
    }

    @Deprecated
    public Location getLocation() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getLocation();
        }
        return null;
    }

    public int getAdWidth() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getAdWidth();
        }
        return 0;
    }

    public int getAdHeight() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getAdHeight();
        }
        return 0;
    }

    public Activity getActivity() {
        return (Activity) this.mContext;
    }

    public void setBannerAdListener(BannerAdListener bannerAdListener) {
        this.mBannerAdListener = bannerAdListener;
    }

    public BannerAdListener getBannerAdListener() {
        return this.mBannerAdListener;
    }

    public void setLocalExtras(Map<String, Object> map) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setLocalExtras(map);
        }
    }

    public Map<String, Object> getLocalExtras() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getLocalExtras();
        }
        return new TreeMap();
    }

    public void setAutorefreshEnabled(boolean z) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setShouldAllowAutoRefresh(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void pauseAutoRefresh() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.pauseRefresh();
        }
    }

    /* access modifiers changed from: package-private */
    public void resumeAutoRefresh() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.resumeRefresh();
        }
    }

    /* access modifiers changed from: package-private */
    public void engageOverlay() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.engageOverlay();
        }
    }

    /* access modifiers changed from: package-private */
    public void dismissOverlay() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.dismissOverlay();
        }
    }

    public boolean getAutorefreshEnabled() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getCurrentAutoRefreshStatus();
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Can't get autorefresh status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void setAdContentView(View view) {
        MoPubLog.log(MoPubLog.AdLogEvent.SHOW_ATTEMPTED, new Object[0]);
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setAdContentView(view);
            MoPubLog.log(MoPubLog.AdLogEvent.SHOW_SUCCESS, new Object[0]);
            return;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.SHOW_FAILED, new Object[0]);
    }

    public void setTesting(boolean z) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setTesting(z);
        }
    }

    public boolean getTesting() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getTesting();
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Can't get testing status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void forceRefresh() {
        if (this.mCustomEventBannerAdapter != null) {
            invalidateAdapter();
            this.mCustomEventBannerAdapter = null;
        }
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.forceRefresh();
        }
    }

    public void setAdSize(MoPubAdSize moPubAdSize) {
        this.mMoPubAdSize = moPubAdSize;
    }

    public MoPubAdSize getAdSize() {
        return this.mMoPubAdSize;
    }

    /* access modifiers changed from: package-private */
    public void setWindowInsets(WindowInsets windowInsets) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setWindowInsets(windowInsets);
        }
    }

    /* access modifiers changed from: package-private */
    public AdViewController getAdViewController() {
        return this.mAdViewController;
    }

    public AdFormat getAdFormat() {
        return AdFormat.BANNER;
    }
}
