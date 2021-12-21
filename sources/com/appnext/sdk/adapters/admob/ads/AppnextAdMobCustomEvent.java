package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAdCreativeType;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;

public abstract class AppnextAdMobCustomEvent implements CustomEventInterstitial {
    public static final String AppnextConfigurationExtraKey = "AppnextConfiguration";
    public static final String AppnextRewardPostbackExtraKey = "AppnextRewardPostback";
    protected Ad mAd;
    protected CustomEventInterstitialListener mListener;

    /* access modifiers changed from: protected */
    public abstract Ad createAd(Context context, String str, Bundle bundle);

    public void onPause() {
    }

    public void onResume() {
    }

    public void requestInterstitialAd(Context context, CustomEventInterstitialListener customEventInterstitialListener, String str, MediationAdRequest mediationAdRequest, Bundle bundle) {
        try {
            Ad createAd = createAd(context, str, bundle);
            this.mAd = createAd;
            if (createAd == null) {
                customEventInterstitialListener.onAdFailedToLoad(0);
                return;
            }
            this.mListener = customEventInterstitialListener;
            createAd.setOnAdLoadedCallback(new OnAdLoaded() {
                public void adLoaded(String str, AppnextAdCreativeType appnextAdCreativeType) {
                    AppnextAdMobCustomEvent.this.mListener.onAdLoaded();
                }
            });
            this.mAd.setOnAdOpenedCallback(new OnAdOpened() {
                public void adOpened() {
                    AppnextAdMobCustomEvent.this.mListener.onAdOpened();
                }
            });
            this.mAd.setOnAdClickedCallback(new OnAdClicked() {
                public void adClicked() {
                    AppnextAdMobCustomEvent.this.mListener.onAdClicked();
                    AppnextAdMobCustomEvent.this.mListener.onAdLeftApplication();
                }
            });
            this.mAd.setOnAdClosedCallback(new OnAdClosed() {
                public void onAdClosed() {
                    AppnextAdMobCustomEvent.this.mListener.onAdClosed();
                }
            });
            this.mAd.setOnAdErrorCallback(new OnAdError() {
                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void adError(java.lang.String r6) {
                    /*
                        r5 = this;
                        int r0 = r6.hashCode()
                        r1 = 0
                        r2 = 1
                        r3 = 3
                        r4 = 2
                        switch(r0) {
                            case -1958363695: goto L_0x002a;
                            case -1477010874: goto L_0x0020;
                            case 350741825: goto L_0x0016;
                            case 844170097: goto L_0x000c;
                            default: goto L_0x000b;
                        }
                    L_0x000b:
                        goto L_0x0034
                    L_0x000c:
                        java.lang.String r0 = "Too Slow Connection"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x0034
                        r6 = 0
                        goto L_0x0035
                    L_0x0016:
                        java.lang.String r0 = "Timeout"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x0034
                        r6 = 1
                        goto L_0x0035
                    L_0x0020:
                        java.lang.String r0 = "Connection Error"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x0034
                        r6 = 2
                        goto L_0x0035
                    L_0x002a:
                        java.lang.String r0 = "No Ads"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x0034
                        r6 = 3
                        goto L_0x0035
                    L_0x0034:
                        r6 = -1
                    L_0x0035:
                        if (r6 == 0) goto L_0x004d
                        if (r6 == r2) goto L_0x004d
                        if (r6 == r4) goto L_0x004d
                        if (r6 == r3) goto L_0x0045
                        com.appnext.sdk.adapters.admob.ads.AppnextAdMobCustomEvent r6 = com.appnext.sdk.adapters.admob.ads.AppnextAdMobCustomEvent.this
                        com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener r6 = r6.mListener
                        r6.onAdFailedToLoad(r1)
                        return
                    L_0x0045:
                        com.appnext.sdk.adapters.admob.ads.AppnextAdMobCustomEvent r6 = com.appnext.sdk.adapters.admob.ads.AppnextAdMobCustomEvent.this
                        com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener r6 = r6.mListener
                        r6.onAdFailedToLoad(r3)
                        return
                    L_0x004d:
                        com.appnext.sdk.adapters.admob.ads.AppnextAdMobCustomEvent r6 = com.appnext.sdk.adapters.admob.ads.AppnextAdMobCustomEvent.this
                        com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener r6 = r6.mListener
                        r6.onAdFailedToLoad(r4)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.appnext.sdk.adapters.admob.ads.AppnextAdMobCustomEvent.AnonymousClass5.adError(java.lang.String):void");
                }
            });
            this.mAd.loadAd();
        } catch (Throwable th) {
            new StringBuilder("requestInterstitialAd: ").append(th.getMessage());
            this.mListener.onAdFailedToLoad(0);
        }
    }

    public void showInterstitial() {
        try {
            if (this.mAd.isAdLoaded()) {
                this.mAd.showAd();
            }
        } catch (Throwable th) {
            this.mListener.onAdFailedToLoad(3);
            new StringBuilder("showInterstitial: ").append(th.getMessage());
        }
    }

    public void onDestroy() {
        this.mAd.destroy();
    }
}
