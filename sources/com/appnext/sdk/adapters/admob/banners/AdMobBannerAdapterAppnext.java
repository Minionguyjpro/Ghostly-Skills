package com.appnext.sdk.adapters.admob.banners;

import com.appnext.banners.g;

public class AdMobBannerAdapterAppnext extends g {
    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.appnext.core.Ad createAd(android.content.Context r6, java.lang.String r7) {
        /*
            r5 = this;
            com.appnext.banners.BannerSize r0 = r5.getBannerSize()
            java.lang.String r0 = r0.toString()
            int r1 = r0.hashCode()
            r2 = -1966536496(0xffffffff8ac908d0, float:-1.9358911E-32)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L_0x0032
            r2 = -96588539(0xfffffffffa3e2d05, float:-2.4686238E35)
            if (r1 == r2) goto L_0x0028
            r2 = 1951953708(0x7458732c, float:6.859571E31)
            if (r1 == r2) goto L_0x001e
            goto L_0x003c
        L_0x001e:
            java.lang.String r1 = "BANNER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003c
            r0 = 0
            goto L_0x003d
        L_0x0028:
            java.lang.String r1 = "MEDIUM_RECTANGLE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003c
            r0 = 2
            goto L_0x003d
        L_0x0032:
            java.lang.String r1 = "LARGE_BANNER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003c
            r0 = 1
            goto L_0x003d
        L_0x003c:
            r0 = -1
        L_0x003d:
            if (r0 == 0) goto L_0x0067
            if (r0 == r4) goto L_0x0061
            if (r0 != r3) goto L_0x0049
            com.appnext.sdk.adapters.admob.banners.AppnextAdMobMediumBanner r0 = new com.appnext.sdk.adapters.admob.banners.AppnextAdMobMediumBanner
            r0.<init>(r6, r7)
            return r0
        L_0x0049:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "Wrong banner size "
            r7.<init>(r0)
            com.appnext.banners.BannerSize r0 = r5.getBannerSize()
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x0061:
            com.appnext.sdk.adapters.admob.banners.AppnextAdMobLargeBanner r0 = new com.appnext.sdk.adapters.admob.banners.AppnextAdMobLargeBanner
            r0.<init>(r6, r7)
            return r0
        L_0x0067:
            com.appnext.sdk.adapters.admob.banners.AppnextAdMobSmallBanner r0 = new com.appnext.sdk.adapters.admob.banners.AppnextAdMobSmallBanner
            r0.<init>(r6, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.sdk.adapters.admob.banners.AdMobBannerAdapterAppnext.createAd(android.content.Context, java.lang.String):com.appnext.core.Ad");
    }
}
