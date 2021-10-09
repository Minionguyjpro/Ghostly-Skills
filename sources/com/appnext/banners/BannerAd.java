package com.appnext.banners;

import android.content.Context;
import com.appnext.core.Ad;
import com.appnext.core.c;
import com.appnext.core.callbacks.OnECPMLoaded;

class BannerAd extends Ad {
    protected static final String TID = "301";
    protected static final String VID = "2.5.1.472";

    public String getAUID() {
        return "1000";
    }

    public void getECPM(OnECPMLoaded onECPMLoaded) {
    }

    public String getTID() {
        return TID;
    }

    public String getVID() {
        return "2.5.1.472";
    }

    public boolean isAdLoaded() {
        return false;
    }

    public void loadAd() {
    }

    public void showAd() {
    }

    protected BannerAd(Ad ad) {
        super(ad);
    }

    public BannerAd(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public String getSessionId() {
        return super.getSessionId();
    }

    /* access modifiers changed from: protected */
    public void setAdRequest(c cVar) {
        super.setAdRequest(cVar);
    }

    /* access modifiers changed from: protected */
    public c getAdRequest() {
        return super.getAdRequest();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        if (r8.equals("a") != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0087, code lost:
        if (r8.equals("a") != false) goto L_0x008b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0090 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getTemId(com.appnext.banners.BannerAdData r8) {
        /*
            r7 = this;
            java.lang.String r8 = r8.getRevenueType()
            java.lang.String r0 = "cpi"
            boolean r8 = r8.equals(r0)
            r0 = 0
            java.lang.String r1 = "b"
            java.lang.String r2 = "a"
            r3 = -1
            r4 = 1
            if (r8 == 0) goto L_0x0060
            com.appnext.banners.d r8 = com.appnext.banners.d.S()
            java.lang.String r5 = "BANNER_cpiActiveFlow"
            java.lang.String r8 = r8.get(r5)
            java.lang.String r8 = r8.toLowerCase()
            int r5 = r8.hashCode()
            r6 = 2
            switch(r5) {
                case 97: goto L_0x0046;
                case 98: goto L_0x003e;
                case 99: goto L_0x0034;
                case 100: goto L_0x002a;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x004d
        L_0x002a:
            java.lang.String r0 = "d"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x004d
            r0 = 3
            goto L_0x004e
        L_0x0034:
            java.lang.String r0 = "c"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x004d
            r0 = 2
            goto L_0x004e
        L_0x003e:
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x004d
            r0 = 1
            goto L_0x004e
        L_0x0046:
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x004d
            goto L_0x004e
        L_0x004d:
            r0 = -1
        L_0x004e:
            if (r0 == 0) goto L_0x005d
            if (r0 == r4) goto L_0x005a
            if (r0 == r6) goto L_0x0057
            java.lang.String r8 = "109"
            return r8
        L_0x0057:
            java.lang.String r8 = "106"
            return r8
        L_0x005a:
            java.lang.String r8 = "103"
            return r8
        L_0x005d:
            java.lang.String r8 = "100"
            return r8
        L_0x0060:
            com.appnext.banners.d r8 = com.appnext.banners.d.S()
            java.lang.String r5 = "BANNER_cpcActiveFlow"
            java.lang.String r8 = r8.get(r5)
            java.lang.String r8 = r8.toLowerCase()
            int r5 = r8.hashCode()
            r6 = 97
            if (r5 == r6) goto L_0x0083
            r0 = 98
            if (r5 == r0) goto L_0x007b
            goto L_0x008a
        L_0x007b:
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x008a
            r0 = 1
            goto L_0x008b
        L_0x0083:
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x008a
            goto L_0x008b
        L_0x008a:
            r0 = -1
        L_0x008b:
            if (r0 == 0) goto L_0x0090
            java.lang.String r8 = "203"
            return r8
        L_0x0090:
            java.lang.String r8 = "200"
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.BannerAd.getTemId(com.appnext.banners.BannerAdData):java.lang.String");
    }
}
