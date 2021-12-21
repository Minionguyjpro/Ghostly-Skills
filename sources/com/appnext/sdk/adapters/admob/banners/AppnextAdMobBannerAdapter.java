package com.appnext.sdk.adapters.admob.banners;

import android.content.Context;
import android.os.Bundle;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerListener;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.appnext.banners.d;
import com.appnext.core.AppnextAdCreativeType;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;

public class AppnextAdMobBannerAdapter implements CustomEventBanner {
    public static final String AppNextBannerAdRequestKey = "AppNextBannerAdRequestKey";
    public static final String AppNextBannerLanguageKey = "AppNextBannerLanguageKey";
    protected BannerView bannerView;

    public void onPause() {
    }

    public void onResume() {
    }

    public void onDestroy() {
        BannerView bannerView2 = this.bannerView;
        if (bannerView2 != null) {
            bannerView2.destroy();
            this.bannerView = null;
        }
    }

    public void requestBannerAd(Context context, final CustomEventBannerListener customEventBannerListener, String str, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle) {
        try {
            if (this.bannerView != null) {
                this.bannerView.destroy();
                this.bannerView = null;
            }
            d.S().q("tid", "321");
            AppnextAdmobBannerView appnextAdmobBannerView = new AppnextAdmobBannerView(context);
            this.bannerView = appnextAdmobBannerView;
            if (bundle != null) {
                appnextAdmobBannerView.setLanguage(bundle.getString(AppNextBannerLanguageKey, ""));
            }
            this.bannerView.setPlacementId(str);
            if (adSize.getWidth() == BannerSize.BANNER.getWidth() && adSize.getHeight() == BannerSize.BANNER.getHeight()) {
                this.bannerView.setBannerSize(BannerSize.BANNER);
            } else if (adSize.getWidth() == BannerSize.LARGE_BANNER.getWidth() && adSize.getHeight() == BannerSize.LARGE_BANNER.getHeight()) {
                this.bannerView.setBannerSize(BannerSize.LARGE_BANNER);
            } else if (adSize.getWidth() == BannerSize.MEDIUM_RECTANGLE.getWidth() && adSize.getHeight() == BannerSize.MEDIUM_RECTANGLE.getHeight()) {
                this.bannerView.setBannerSize(BannerSize.MEDIUM_RECTANGLE);
            } else {
                throw new IllegalArgumentException("Wrong size");
            }
            this.bannerView.setBannerListener(new BannerListener() {
                public void onAdLoaded(String str, AppnextAdCreativeType appnextAdCreativeType) {
                    customEventBannerListener.onAdLoaded(AppnextAdMobBannerAdapter.this.bannerView);
                    super.onAdLoaded(str, appnextAdCreativeType);
                }

                public void onAdClicked() {
                    customEventBannerListener.onAdClicked();
                    super.onAdClicked();
                }

                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onError(com.appnext.core.AppnextError r6) {
                    /*
                        r5 = this;
                        super.onError(r6)
                        java.lang.String r6 = r6.getErrorMessage()
                        int r0 = r6.hashCode()
                        r1 = 0
                        r2 = 1
                        r3 = 3
                        r4 = 2
                        switch(r0) {
                            case -1958363695: goto L_0x0031;
                            case -1477010874: goto L_0x0027;
                            case 350741825: goto L_0x001d;
                            case 844170097: goto L_0x0013;
                            default: goto L_0x0012;
                        }
                    L_0x0012:
                        goto L_0x003b
                    L_0x0013:
                        java.lang.String r0 = "Too Slow Connection"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x003b
                        r6 = 0
                        goto L_0x003c
                    L_0x001d:
                        java.lang.String r0 = "Timeout"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x003b
                        r6 = 1
                        goto L_0x003c
                    L_0x0027:
                        java.lang.String r0 = "Connection Error"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x003b
                        r6 = 2
                        goto L_0x003c
                    L_0x0031:
                        java.lang.String r0 = "No Ads"
                        boolean r6 = r6.equals(r0)
                        if (r6 == 0) goto L_0x003b
                        r6 = 3
                        goto L_0x003c
                    L_0x003b:
                        r6 = -1
                    L_0x003c:
                        if (r6 == 0) goto L_0x0050
                        if (r6 == r2) goto L_0x0050
                        if (r6 == r4) goto L_0x0050
                        if (r6 == r3) goto L_0x004a
                        com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener r6 = r4
                        r6.onAdFailedToLoad(r1)
                        return
                    L_0x004a:
                        com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener r6 = r4
                        r6.onAdFailedToLoad(r3)
                        return
                    L_0x0050:
                        com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener r6 = r4
                        r6.onAdFailedToLoad(r4)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.appnext.sdk.adapters.admob.banners.AppnextAdMobBannerAdapter.AnonymousClass1.onError(com.appnext.core.AppnextError):void");
                }

                public void adImpression() {
                    super.adImpression();
                }
            });
            this.bannerView.loadAd((BannerAdRequest) bundle.getSerializable(AppNextBannerAdRequestKey));
        } catch (Throwable unused) {
        }
    }
}
