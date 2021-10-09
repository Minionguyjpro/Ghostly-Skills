package com.appnext.sdk.adapters.mopub.ads;

import android.content.Context;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAdCreativeType;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.MoPubErrorCode;
import java.util.Map;

public abstract class AppnextMoPubCustomEvent extends CustomEventInterstitial {
    protected static final String AppnextAutoPlayExtraKey = "AppnextAutoPlay";
    protected static final String AppnextBackButtonCanCloseExtraKey = "AppnextBackButtonCanClose";
    protected static final String AppnextBannerSizeExtraKey = "AppnextBannerSize";
    protected static final String AppnextButtonColorExtraKey = "AppnextButtonColor";
    protected static final String AppnextCategoriesExtraKey = "AppnextCategories";
    protected static final String AppnextCloseDelayExtraKey = "AppnextCloseDelay";
    public static final String AppnextConfigurationExtraKey = "AppnextConfiguration";
    protected static final String AppnextCreativeTypeExtraKey = "AppnextCreativeType";
    protected static final String AppnextLanguageExtraKey = "AppnextLanguage";
    protected static final String AppnextMaxVideoLenExtraKey = "AppnextMaxVideoLen";
    protected static final String AppnextMinVideoLenExtraKey = "AppnextMinVideoLen";
    protected static final String AppnextMultiTimerLengthExtraKey = "AppnextMultiTimerLength";
    protected static final String AppnextMuteExtraKey = "AppnextMute";
    protected static final String AppnextOrientationExtraKey = "AppnextOrientation";
    protected static final String AppnextPlacementIdExtraKey = "AppnextPlacementId";
    protected static final String AppnextPostbackExtraKey = "AppnextPostback";
    protected static final String AppnextProgressColorExtraKey = "AppnextProgressColor";
    protected static final String AppnextProgressTypeExtraKey = "AppnextProgressType";
    public static final String AppnextRewardPostbackExtraKey = "AppnextRewardPostback";
    protected static final String AppnextRollCapTimeExtraKey = "AppnextRollCapTime";
    protected static final String AppnextShowCloseExtraKey = "AppnextShowClose";
    protected static final String AppnextShowCtaExtrakey = "AppnextShowCta";
    protected static final String AppnextSkipTextExtraKey = "AppnextSkipText";
    protected static final String AppnextVideoLengthExtraKey = "AppnextVideoLength";
    protected static final String AppnextVideoModeExtraKey = "AppnextVideoMode";
    protected Ad mAd;
    /* access modifiers changed from: private */
    public CustomEventInterstitial.CustomEventInterstitialListener mInterstitialListener;

    /* access modifiers changed from: protected */
    public abstract Ad createAd(Context context, Map<String, Object> map, Map<String, String> map2);

    /* access modifiers changed from: protected */
    public void onInvalidate() {
    }

    /* access modifiers changed from: protected */
    public void loadInterstitial(Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        this.mInterstitialListener = customEventInterstitialListener;
        Ad createAd = createAd(context.getApplicationContext(), map, map2);
        this.mAd = createAd;
        if (createAd == null) {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            return;
        }
        try {
            createAd.setOnAdLoadedCallback(new OnAdLoaded() {
                public void adLoaded(String str, AppnextAdCreativeType appnextAdCreativeType) {
                    AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialLoaded();
                }
            });
            this.mAd.setOnAdOpenedCallback(new OnAdOpened() {
                public void adOpened() {
                    AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialShown();
                }
            });
            this.mAd.setOnAdClickedCallback(new OnAdClicked() {
                public void adClicked() {
                    AppnextMoPubCustomEvent.this.mInterstitialListener.onLeaveApplication();
                }
            });
            this.mAd.setOnAdClosedCallback(new OnAdClosed() {
                public void onAdClosed() {
                    AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialDismissed();
                }
            });
            this.mAd.setOnAdErrorCallback(new OnAdError() {
                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void adError(java.lang.String r6) {
                    /*
                        r5 = this;
                        int r0 = r6.hashCode()
                        r1 = 4
                        r2 = 3
                        r3 = 2
                        r4 = 1
                        switch(r0) {
                            case -1958363695: goto L_0x0034;
                            case -1477010874: goto L_0x002a;
                            case 297538105: goto L_0x0020;
                            case 350741825: goto L_0x0016;
                            case 844170097: goto L_0x000c;
                            default: goto L_0x000b;
                        }
                    L_0x000b:
                        goto L_0x003e
                    L_0x000c:
                        java.lang.String r0 = "Too Slow Connection"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x003e
                        r6 = 1
                        goto L_0x003f
                    L_0x0016:
                        java.lang.String r0 = "Timeout"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x003e
                        r6 = 2
                        goto L_0x003f
                    L_0x0020:
                        java.lang.String r0 = "Ad Not Ready"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x003e
                        r6 = 0
                        goto L_0x003f
                    L_0x002a:
                        java.lang.String r0 = "Connection Error"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x003e
                        r6 = 3
                        goto L_0x003f
                    L_0x0034:
                        java.lang.String r0 = "No Ads"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x003e
                        r6 = 4
                        goto L_0x003f
                    L_0x003e:
                        r6 = -1
                    L_0x003f:
                        if (r6 == 0) goto L_0x0079
                        if (r6 == r4) goto L_0x006d
                        if (r6 == r3) goto L_0x006d
                        if (r6 == r2) goto L_0x0061
                        if (r6 == r1) goto L_0x0055
                        com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent.this
                        com.mopub.mobileads.CustomEventInterstitial$CustomEventInterstitialListener r6 = r6.mInterstitialListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.INTERNAL_ERROR
                        r6.onInterstitialFailed(r0)
                        return
                    L_0x0055:
                        com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent.this
                        com.mopub.mobileads.CustomEventInterstitial$CustomEventInterstitialListener r6 = r6.mInterstitialListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.NO_FILL
                        r6.onInterstitialFailed(r0)
                        return
                    L_0x0061:
                        com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent.this
                        com.mopub.mobileads.CustomEventInterstitial$CustomEventInterstitialListener r6 = r6.mInterstitialListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.NO_CONNECTION
                        r6.onInterstitialFailed(r0)
                        return
                    L_0x006d:
                        com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent.this
                        com.mopub.mobileads.CustomEventInterstitial$CustomEventInterstitialListener r6 = r6.mInterstitialListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.NETWORK_TIMEOUT
                        r6.onInterstitialFailed(r0)
                        return
                    L_0x0079:
                        com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent.this
                        com.mopub.mobileads.CustomEventInterstitial$CustomEventInterstitialListener r6 = r6.mInterstitialListener
                        com.mopub.mobileads.MoPubErrorCode r0 = com.mopub.mobileads.MoPubErrorCode.WARMUP
                        r6.onInterstitialFailed(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.appnext.sdk.adapters.mopub.ads.AppnextMoPubCustomEvent.AnonymousClass5.adError(java.lang.String):void");
                }
            });
            this.mAd.loadAd();
        } catch (Throwable th) {
            new StringBuilder("requestInterstitialAd: ").append(th.getMessage());
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void showInterstitial() {
        try {
            if (this.mAd.isAdLoaded()) {
                this.mAd.showAd();
            } else {
                this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.WARMUP);
            }
        } catch (Throwable th) {
            new StringBuilder("showInterstitial: ").append(th.getMessage());
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
        }
    }
}
