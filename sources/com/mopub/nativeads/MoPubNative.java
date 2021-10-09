package com.mopub.nativeads;

import android.content.Context;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.ManifestUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.nativeads.CustomEventNative;
import com.mopub.network.AdLoader;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.TreeMap;

public class MoPubNative {
    static final MoPubNativeNetworkListener EMPTY_NETWORK_LISTENER = new MoPubNativeNetworkListener() {
        public void onNativeFail(NativeErrorCode nativeErrorCode) {
        }

        public void onNativeLoad(NativeAd nativeAd) {
            nativeAd.destroy();
        }
    };
    /* access modifiers changed from: private */
    public AdLoader mAdLoader;
    AdRendererRegistry mAdRendererRegistry;
    /* access modifiers changed from: private */
    public final String mAdUnitId;
    private final WeakReference<Context> mContext;
    private Map<String, Object> mLocalExtras;
    /* access modifiers changed from: private */
    public MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    /* access modifiers changed from: private */
    public CustomEventNativeAdapter mNativeAdapter;
    private Request mNativeRequest;
    private final AdLoader.Listener mVolleyListener;

    public interface MoPubNativeNetworkListener {
        void onNativeFail(NativeErrorCode nativeErrorCode);

        void onNativeLoad(NativeAd nativeAd);
    }

    public MoPubNative(Context context, String str, MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this(context, str, new AdRendererRegistry(), moPubNativeNetworkListener);
    }

    public MoPubNative(Context context, String str, AdRendererRegistry adRendererRegistry, MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this.mLocalExtras = new TreeMap();
        Preconditions.checkNotNull(context, "context may not be null.");
        Preconditions.checkNotNull(str, "AdUnitId may not be null.");
        Preconditions.checkNotNull(adRendererRegistry, "AdRendererRegistry may not be null.");
        Preconditions.checkNotNull(moPubNativeNetworkListener, "MoPubNativeNetworkListener may not be null.");
        ManifestUtils.checkNativeActivitiesDeclared(context);
        this.mContext = new WeakReference<>(context);
        this.mAdUnitId = str;
        this.mMoPubNativeNetworkListener = moPubNativeNetworkListener;
        this.mAdRendererRegistry = adRendererRegistry;
        this.mVolleyListener = new AdLoader.Listener() {
            public void onSuccess(AdResponse adResponse) {
                MoPubNative.this.onAdLoad(adResponse);
            }

            public void onErrorResponse(VolleyError volleyError) {
                MoPubNative.this.onAdError(volleyError);
            }
        };
    }

    public void registerAdRenderer(MoPubAdRenderer moPubAdRenderer) {
        if (Preconditions.NoThrow.checkNotNull(moPubAdRenderer, "Can't register a null adRenderer")) {
            this.mAdRendererRegistry.registerAdRenderer(moPubAdRenderer);
        }
    }

    public void destroy() {
        this.mContext.clear();
        Request request = this.mNativeRequest;
        if (request != null) {
            request.cancel();
            this.mNativeRequest = null;
        }
        this.mAdLoader = null;
        this.mMoPubNativeNetworkListener = EMPTY_NETWORK_LISTENER;
    }

    public void setLocalExtras(Map<String, Object> map) {
        if (map == null) {
            this.mLocalExtras = new TreeMap();
        } else {
            this.mLocalExtras = new TreeMap(map);
        }
    }

    public void makeRequest() {
        makeRequest((RequestParameters) null);
    }

    public void makeRequest(RequestParameters requestParameters) {
        makeRequest(requestParameters, (Integer) null);
    }

    public void makeRequest(RequestParameters requestParameters, Integer num) {
        Context contextOrDestroy = getContextOrDestroy();
        if (contextOrDestroy != null) {
            if (!DeviceUtils.isNetworkAvailable(contextOrDestroy)) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
            } else {
                loadNativeAd(requestParameters, num);
            }
        }
    }

    private void loadNativeAd(RequestParameters requestParameters, Integer num) {
        Context contextOrDestroy = getContextOrDestroy();
        if (contextOrDestroy != null) {
            MoPubLog.log(MoPubLog.AdLogEvent.LOAD_ATTEMPTED, new Object[0]);
            NativeUrlGenerator withRequest = new NativeUrlGenerator(contextOrDestroy).withAdUnitId(this.mAdUnitId).withRequest(requestParameters);
            if (num != null) {
                withRequest.withSequenceNumber(num.intValue());
            }
            String generateUrlString = withRequest.generateUrlString(Constants.HOST);
            if (generateUrlString != null) {
                MoPubLog.AdLogEvent adLogEvent = MoPubLog.AdLogEvent.CUSTOM;
                MoPubLog.log(adLogEvent, "MoPubNative Loading ad from: " + generateUrlString);
            }
            requestNativeAd(generateUrlString, (NativeErrorCode) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void requestNativeAd(String str, NativeErrorCode nativeErrorCode) {
        Context contextOrDestroy = getContextOrDestroy();
        if (contextOrDestroy != null) {
            AdLoader adLoader = this.mAdLoader;
            if (adLoader == null || !adLoader.hasMoreAds()) {
                if (TextUtils.isEmpty(str)) {
                    MoPubNativeNetworkListener moPubNativeNetworkListener = this.mMoPubNativeNetworkListener;
                    if (nativeErrorCode == null) {
                        nativeErrorCode = NativeErrorCode.INVALID_REQUEST_URL;
                    }
                    moPubNativeNetworkListener.onNativeFail(nativeErrorCode);
                    return;
                }
                this.mAdLoader = new AdLoader(str, AdFormat.NATIVE, this.mAdUnitId, contextOrDestroy, this.mVolleyListener);
            }
            this.mNativeRequest = this.mAdLoader.loadNextAd(nativeErrorCode);
        }
    }

    /* access modifiers changed from: private */
    public void onAdLoad(final AdResponse adResponse) {
        Context contextOrDestroy = getContextOrDestroy();
        if (contextOrDestroy != null) {
            AnonymousClass3 r1 = new CustomEventNative.CustomEventNativeListener() {
                public void onNativeAdLoaded(BaseNativeAd baseNativeAd) {
                    MoPubLog.log(MoPubLog.AdLogEvent.LOAD_SUCCESS, new Object[0]);
                    CustomEventNativeAdapter unused = MoPubNative.this.mNativeAdapter = null;
                    Context contextOrDestroy = MoPubNative.this.getContextOrDestroy();
                    if (contextOrDestroy != null) {
                        MoPubAdRenderer rendererForAd = MoPubNative.this.mAdRendererRegistry.getRendererForAd(baseNativeAd);
                        if (rendererForAd == null) {
                            onNativeAdFailed(NativeErrorCode.NATIVE_RENDERER_CONFIGURATION_ERROR);
                            return;
                        }
                        if (MoPubNative.this.mAdLoader != null) {
                            MoPubNative.this.mAdLoader.creativeDownloadSuccess();
                        }
                        MoPubNative.this.mMoPubNativeNetworkListener.onNativeLoad(new NativeAd(contextOrDestroy, adResponse, MoPubNative.this.mAdUnitId, baseNativeAd, rendererForAd));
                    }
                }

                public void onNativeAdFailed(NativeErrorCode nativeErrorCode) {
                    MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, Integer.valueOf(nativeErrorCode.getIntCode()), nativeErrorCode.toString());
                    CustomEventNativeAdapter unused = MoPubNative.this.mNativeAdapter = null;
                    MoPubNative.this.requestNativeAd("", nativeErrorCode);
                }
            };
            if (this.mNativeAdapter != null) {
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Native adapter is not null.");
                this.mNativeAdapter.stopLoading();
            }
            CustomEventNativeAdapter customEventNativeAdapter = new CustomEventNativeAdapter(r1);
            this.mNativeAdapter = customEventNativeAdapter;
            customEventNativeAdapter.loadNativeAd(contextOrDestroy, this.mLocalExtras, adResponse);
        }
    }

    /* access modifiers changed from: package-private */
    public void onAdError(VolleyError volleyError) {
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Native ad request failed.", volleyError);
        if (volleyError instanceof MoPubNetworkError) {
            int i = AnonymousClass4.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()];
            if (i == 1) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
            } else if (i == 2) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
            } else if (i == 3) {
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, MoPubErrorCode.WARMUP);
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
            } else if (i != 4) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
            } else {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
            }
        } else {
            NetworkResponse networkResponse = volleyError.networkResponse;
            if (networkResponse != null && networkResponse.statusCode >= 500 && networkResponse.statusCode < 600) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.SERVER_ERROR_RESPONSE_CODE);
            } else if (networkResponse != null || DeviceUtils.isNetworkAvailable((Context) this.mContext.get())) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
            } else {
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, MoPubErrorCode.NO_CONNECTION);
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
            }
        }
    }

    /* renamed from: com.mopub.nativeads.MoPubNative$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$network$MoPubNetworkError$Reason;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.mopub.network.MoPubNetworkError$Reason[] r0 = com.mopub.network.MoPubNetworkError.Reason.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason = r0
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.BAD_BODY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$network$MoPubNetworkError$Reason     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.BAD_HEADER_DATA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$network$MoPubNetworkError$Reason     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.WARMING_UP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$mopub$network$MoPubNetworkError$Reason     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$mopub$network$MoPubNetworkError$Reason     // Catch:{ NoSuchFieldError -> 0x003e }
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.UNSPECIFIED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.MoPubNative.AnonymousClass4.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public Context getContextOrDestroy() {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            destroy();
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Weak reference to Context in MoPubNative became null. This instance of MoPubNative is destroyed and No more requests will be processed.");
        }
        return context;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public MoPubNativeNetworkListener getMoPubNativeNetworkListener() {
        return this.mMoPubNativeNetworkListener;
    }
}
