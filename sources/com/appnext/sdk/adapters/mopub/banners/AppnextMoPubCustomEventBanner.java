package com.appnext.sdk.adapters.mopub.banners;

import android.content.Context;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerListener;
import com.appnext.banners.BannerView;
import com.appnext.banners.d;
import com.appnext.core.AppnextAdCreativeType;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.MoPubErrorCode;
import java.util.Map;

public class AppnextMoPubCustomEventBanner extends CustomEventBanner {
    public static final String AppnextConfigurationExtraKey = "AppnextConfiguration";
    protected BannerView mBanner;
    /* access modifiers changed from: private */
    public CustomEventBanner.CustomEventBannerListener mBannerListener;

    /* access modifiers changed from: protected */
    public BannerView createBannerView(Context context, Map<String, Object> map, Map<String, String> map2) {
        try {
            d.S().q("tid", "311");
            AppnextMopubBannerView appnextMopubBannerView = new AppnextMopubBannerView(context);
            appnextMopubBannerView.setPlacementId(Helper.getAppnextPlacementIdExtraKey(map2));
            appnextMopubBannerView.setBannerSize(Helper.getBannerSize(map2));
            return appnextMopubBannerView;
        } catch (Throwable th) {
            new StringBuilder("AppnextMoPubCustomEventBanner createAd: ").append(th.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasAdConfigServerExtras(Map<String, String> map) {
        return map != null && (Helper.hasAdConfigServerExtras(map) || map.containsKey("AppnextCreativeType") || map.containsKey("AppnextCategories") || map.containsKey("AppnextPostback") || map.containsKey("AppnextMute") || map.containsKey("AppnextVideoLength") || map.containsKey("AppnextMaxVideoLen") || map.containsKey("AppnextMinVideoLen") || map.containsKey("AppnextClickEnabled") || map.containsKey("AppnextAutoPlay") || map.containsKey("AppnextLanguage"));
    }

    /* access modifiers changed from: protected */
    public void setConfigFromExtras(BannerAdRequest bannerAdRequest, Map<String, String> map) {
        if (bannerAdRequest != null) {
            if (map.containsKey("AppnextCreativeType")) {
                try {
                    bannerAdRequest.setCreativeType(map.get("AppnextCreativeType"));
                    StringBuilder sb = new StringBuilder();
                    sb.append(map.get("AppnextCreativeType"));
                    sb.append("set creative");
                } catch (Throwable th) {
                    new StringBuilder("setCreativeType: ").append(th.getMessage());
                }
            }
            if (map.containsKey("AppnextCategories")) {
                try {
                    bannerAdRequest.setCategories(map.get("AppnextCategories"));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(map.get("AppnextCategories"));
                    sb2.append("set categories");
                } catch (Throwable th2) {
                    new StringBuilder("setCategories: ").append(th2.getMessage());
                }
            }
            if (map.containsKey("AppnextPostback")) {
                try {
                    bannerAdRequest.setPostback(map.get("AppnextPostback"));
                } catch (Throwable th3) {
                    new StringBuilder("setPostback: ").append(th3.getMessage());
                }
            }
            if (map.containsKey("AppnextMute")) {
                try {
                    bannerAdRequest.setMute(Boolean.parseBoolean(map.get("AppnextMute")));
                } catch (Throwable th4) {
                    new StringBuilder("setMute: ").append(th4.getMessage());
                }
            }
            if (map.containsKey("AppnextVideoLength")) {
                try {
                    bannerAdRequest.setVideoLength(map.get("AppnextVideoLength"));
                } catch (Throwable th5) {
                    new StringBuilder("setVideoLength: ").append(th5.getMessage());
                }
            }
            if (map.containsKey("AppnextMaxVideoLen")) {
                try {
                    bannerAdRequest.setVidMax(Integer.getInteger(map.get("AppnextMaxVideoLen")).intValue());
                } catch (Throwable th6) {
                    new StringBuilder("setVidMax: ").append(th6.getMessage());
                }
            }
            if (map.containsKey("AppnextMinVideoLen")) {
                try {
                    bannerAdRequest.setVidMin(Integer.getInteger(map.get("AppnextMinVideoLen")).intValue());
                } catch (Throwable th7) {
                    new StringBuilder("setVidMin: ").append(th7.getMessage());
                }
            }
            if (map.containsKey("AppnextAutoPlay")) {
                try {
                    bannerAdRequest.setAutoPlay(Boolean.parseBoolean(map.get("AppnextAutoPlay")));
                } catch (Throwable th8) {
                    new StringBuilder("setAutoPlay: ").append(th8.getMessage());
                }
            }
            if (map.containsKey("AppnextClickEnabled")) {
                try {
                    bannerAdRequest.setClickEnabled(Boolean.parseBoolean(map.get("AppnextClickEnabled")));
                } catch (Throwable th9) {
                    new StringBuilder("setClickEnabled: ").append(th9.getMessage());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void loadBanner(Context context, CustomEventBanner.CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        this.mBannerListener = customEventBannerListener;
        BannerView createBannerView = createBannerView(context, map, map2);
        this.mBanner = createBannerView;
        if (createBannerView == null) {
            this.mBannerListener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
            return;
        }
        BannerAdRequest bannerAdRequest = null;
        if (map != null) {
            bannerAdRequest = map.get("AppnextConfiguration");
            if (map.containsKey("AppnextLanguage") && (map.get("AppnextLanguage") instanceof String)) {
                this.mBanner.setLanguage((String) map.get("AppnextLanguage"));
            }
        }
        if (bannerAdRequest == null) {
            bannerAdRequest = new BannerAdRequest();
            if (hasAdConfigServerExtras(map2)) {
                setConfigFromExtras(bannerAdRequest, map2);
            }
            if (map2.containsKey("AppnextLanguage")) {
                this.mBanner.setLanguage(map2.get("AppnextLanguage"));
            }
        }
        try {
            this.mBanner.setBannerListener(new BannerListener() {
                public void onAdLoaded(String str, AppnextAdCreativeType appnextAdCreativeType) {
                    super.onAdLoaded(str, appnextAdCreativeType);
                    AppnextMoPubCustomEventBanner.this.mBannerListener.onBannerLoaded(AppnextMoPubCustomEventBanner.this.mBanner);
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    AppnextMoPubCustomEventBanner.this.mBannerListener.onBannerClicked();
                    AppnextMoPubCustomEventBanner.this.mBannerListener.onLeaveApplication();
                }

                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onError(com.appnext.core.AppnextError r5) {
                    /*
                        r4 = this;
                        super.onError(r5)
                        java.lang.String r5 = r5.getErrorMessage()
                        int r0 = r5.hashCode()
                        r1 = 3
                        r2 = 2
                        r3 = 1
                        switch(r0) {
                            case -1958363695: goto L_0x0030;
                            case -1477010874: goto L_0x0026;
                            case 297538105: goto L_0x001c;
                            case 350741825: goto L_0x0012;
                            default: goto L_0x0011;
                        }
                    L_0x0011:
                        goto L_0x003a
                    L_0x0012:
                        java.lang.String r0 = "Timeout"
                        boolean r5 = r5.equals(r0)
                        if (r5 == 0) goto L_0x003a
                        r5 = 1
                        goto L_0x003b
                    L_0x001c:
                        java.lang.String r0 = "Ad Not Ready"
                        boolean r5 = r5.equals(r0)
                        if (r5 == 0) goto L_0x003a
                        r5 = 0
                        goto L_0x003b
                    L_0x0026:
                        java.lang.String r0 = "Connection Error"
                        boolean r5 = r5.equals(r0)
                        if (r5 == 0) goto L_0x003a
                        r5 = 2
                        goto L_0x003b
                    L_0x0030:
                        java.lang.String r0 = "No Ads"
                        boolean r5 = r5.equals(r0)
                        if (r5 == 0) goto L_0x003a
                        r5 = 3
                        goto L_0x003b
                    L_0x003a:
                        r5 = -1
                    L_0x003b:
                        if (r5 == 0) goto L_0x0073
                        if (r5 == r3) goto L_0x0067
                        if (r5 == r2) goto L_0x005b
                        if (r5 == r1) goto L_0x004f
                        com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner r5 = com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner.this
                        com.mopub.mobileads.CustomEventBanner$CustomEventBannerListener r5 = r5.mBannerListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.INTERNAL_ERROR
                        r5.onBannerFailed(r0)
                        return
                    L_0x004f:
                        com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner r5 = com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner.this
                        com.mopub.mobileads.CustomEventBanner$CustomEventBannerListener r5 = r5.mBannerListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.NO_FILL
                        r5.onBannerFailed(r0)
                        return
                    L_0x005b:
                        com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner r5 = com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner.this
                        com.mopub.mobileads.CustomEventBanner$CustomEventBannerListener r5 = r5.mBannerListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.NO_CONNECTION
                        r5.onBannerFailed(r0)
                        return
                    L_0x0067:
                        com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner r5 = com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner.this
                        com.mopub.mobileads.CustomEventBanner$CustomEventBannerListener r5 = r5.mBannerListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.NETWORK_TIMEOUT
                        r5.onBannerFailed(r0)
                        return
                    L_0x0073:
                        com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner r5 = com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner.this
                        com.mopub.mobileads.CustomEventBanner$CustomEventBannerListener r5 = r5.mBannerListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.WARMUP
                        r5.onBannerFailed(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.appnext.sdk.adapters.mopub.banners.AppnextMoPubCustomEventBanner.AnonymousClass1.onError(com.appnext.core.AppnextError):void");
                }
            });
            this.mBanner.loadAd((BannerAdRequest) bannerAdRequest);
        } catch (Throwable th) {
            new StringBuilder("requestBannerAd: ").append(th.getMessage());
            this.mBannerListener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        BannerView bannerView = this.mBanner;
        if (bannerView != null) {
            bannerView.destroy();
        }
        this.mBanner = null;
    }
}
