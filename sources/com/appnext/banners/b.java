package com.appnext.banners;

import android.content.Context;
import android.util.Pair;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.a;
import com.appnext.core.d;
import com.appnext.core.f;
import com.appnext.core.g;
import com.appnext.core.p;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

final class b extends d {
    private static b de;
    private final int aM = 50;

    /* access modifiers changed from: protected */
    public final <T> void a(String str, Ad ad, T t) {
    }

    public static synchronized b R() {
        b bVar;
        synchronized (b.class) {
            if (de == null) {
                de = new b();
            }
            bVar = de;
        }
        return bVar;
    }

    private b() {
    }

    /* access modifiers changed from: protected */
    public final String a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        StringBuilder sb = new StringBuilder("&auid=");
        sb.append(ad != null ? ad.getAUID() : "1000");
        return sb.toString();
    }

    public final void a(Context context, Ad ad, String str, d.a aVar, BannerAdRequest bannerAdRequest) {
        ((BannerAd) ad).setAdRequest(new BannerAdRequest(bannerAdRequest));
        super.a(context, ad, str, aVar);
    }

    /* access modifiers changed from: protected */
    public final void a(Context context, Ad ad, a aVar) throws Exception {
        AppnextAd a2 = a(context, ad, ((BannerAdRequest) ((BannerAd) ad).getAdRequest()).getCreativeType());
        if (a2 != null) {
            f.Y(a2.getImageURL());
            if (ad instanceof MediumRectangleAd) {
                f.Y(a2.getWideImageURL());
                return;
            }
            return;
        }
        throw new Exception(AppnextError.NO_ADS);
    }

    /* access modifiers changed from: protected */
    public final int a(Context context, g gVar) {
        BannerAdData bannerAdData = new BannerAdData((AppnextAd) gVar);
        int a2 = a(context, bannerAdData);
        if (a2 != 0) {
            return a2;
        }
        if (!bannerAdData.getCampaignGoal().equals(com.appnext.core.a.b.hX) || !f.c(context, bannerAdData.getAdPackage())) {
            return (!bannerAdData.getCampaignGoal().equals(com.appnext.core.a.b.hY) || f.c(context, bannerAdData.getAdPackage())) ? 0 : 1;
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    public final void a(Ad ad, String str, String str2) {
        new StringBuilder("error ").append(str);
    }

    /* access modifiers changed from: protected */
    public final p c(Ad ad) {
        return d.S();
    }

    /* access modifiers changed from: protected */
    public final boolean a(Context context, Ad ad, ArrayList<?> arrayList) {
        return a(context, ad, arrayList, ((BannerAdRequest) ((BannerAd) ad).getAdRequest()).getCreativeType()) != null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0068, code lost:
        if (hasVideo(r0) == false) goto L_0x006b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0008 A[LOOP:0: B:4:0x0008->B:40:0x0008, LOOP_END, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0085 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.appnext.core.AppnextAd a(android.content.Context r8, com.appnext.core.Ad r9, java.util.ArrayList<?> r10, java.lang.String r11, java.util.ArrayList<java.lang.String> r12) {
        /*
            r7 = this;
            r8 = 0
            if (r10 != 0) goto L_0x0004
            return r8
        L_0x0004:
            java.util.Iterator r10 = r10.iterator()
        L_0x0008:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0086
            java.lang.Object r0 = r10.next()
            com.appnext.core.AppnextAd r0 = (com.appnext.core.AppnextAd) r0
            boolean r1 = r9 instanceof com.appnext.banners.MediumRectangleAd
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x006a
            r1 = -1
            int r4 = r11.hashCode()
            r5 = -892481938(0xffffffffcacdce6e, float:-6743863.0)
            r6 = 2
            if (r4 == r5) goto L_0x0044
            r5 = 96673(0x179a1, float:1.35468E-40)
            if (r4 == r5) goto L_0x003a
            r5 = 112202875(0x6b0147b, float:6.6233935E-35)
            if (r4 == r5) goto L_0x0030
            goto L_0x004d
        L_0x0030:
            java.lang.String r4 = "video"
            boolean r4 = r11.equals(r4)
            if (r4 == 0) goto L_0x004d
            r1 = 2
            goto L_0x004d
        L_0x003a:
            java.lang.String r4 = "all"
            boolean r4 = r11.equals(r4)
            if (r4 == 0) goto L_0x004d
            r1 = 0
            goto L_0x004d
        L_0x0044:
            java.lang.String r4 = "static"
            boolean r4 = r11.equals(r4)
            if (r4 == 0) goto L_0x004d
            r1 = 1
        L_0x004d:
            if (r1 == 0) goto L_0x005e
            if (r1 == r3) goto L_0x0059
            if (r1 == r6) goto L_0x0054
            goto L_0x006b
        L_0x0054:
            boolean r2 = hasVideo(r0)
            goto L_0x006b
        L_0x0059:
            boolean r2 = c((com.appnext.core.AppnextAd) r0)
            goto L_0x006b
        L_0x005e:
            boolean r1 = c((com.appnext.core.AppnextAd) r0)
            if (r1 != 0) goto L_0x006a
            boolean r1 = hasVideo(r0)
            if (r1 == 0) goto L_0x006b
        L_0x006a:
            r2 = 1
        L_0x006b:
            if (r2 == 0) goto L_0x0008
            java.lang.String r1 = r0.getBannerID()
            java.lang.String r2 = r9.getPlacementID()
            boolean r1 = a((java.lang.String) r1, (java.lang.String) r2)
            if (r1 != 0) goto L_0x0008
            java.lang.String r1 = r0.getBannerID()
            boolean r1 = r12.contains(r1)
            if (r1 != 0) goto L_0x0008
            return r0
        L_0x0086:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.b.a(android.content.Context, com.appnext.core.Ad, java.util.ArrayList, java.lang.String, java.util.ArrayList):com.appnext.core.AppnextAd");
    }

    /* access modifiers changed from: protected */
    public final AppnextAd a(Context context, Ad ad, ArrayList<?> arrayList, String str) {
        return a(context, ad, arrayList, str, (ArrayList<String>) new ArrayList());
    }

    /* access modifiers changed from: protected */
    public final AppnextAd a(Context context, Ad ad, String str) {
        ArrayList<?> ads;
        if (k(ad) == null || (ads = k(ad).getAds()) == null) {
            return null;
        }
        return a(context, ad, ads, str);
    }

    private static boolean a(Ad ad, AppnextAd appnextAd, String str) {
        if (ad instanceof MediumRectangleAd) {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -892481938) {
                if (hashCode != 96673) {
                    if (hashCode == 112202875 && str.equals("video")) {
                        c = 2;
                    }
                } else if (str.equals(BannerAdRequest.TYPE_ALL)) {
                    c = 0;
                }
            } else if (str.equals("static")) {
                c = 1;
            }
            if (c != 0) {
                if (c == 1) {
                    return c(appnextAd);
                }
                if (c != 2) {
                    return false;
                }
                return hasVideo(appnextAd);
            } else if (c(appnextAd) || hasVideo(appnextAd)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public final ArrayList<AppnextAd> f(Ad ad) {
        return k(ad).getAds();
    }

    private static int a(Context context, BannerAdData bannerAdData) {
        if (!bannerAdData.getCptList().equals("") && !bannerAdData.getCptList().equals("[]")) {
            try {
                JSONArray jSONArray = new JSONArray(bannerAdData.getCptList());
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (f.c(context, jSONArray.getString(i))) {
                        return 0;
                    }
                }
                return 3;
            } catch (JSONException unused) {
            }
        }
        return 0;
    }

    static boolean hasVideo(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrl().equals("") || !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrl30Sec().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    static boolean c(AppnextAd appnextAd) {
        return !appnextAd.getWideImageURL().equals("");
    }
}
