package com.appnext.ads.interstitial;

import android.content.Context;
import android.util.Pair;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.a.b;
import com.appnext.core.d;
import com.appnext.core.f;
import com.appnext.core.g;
import com.appnext.core.p;
import com.appnext.core.webview.AppnextWebView;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

public final class a extends d {
    private static final int aM = 30;
    private static a cl;
    private String bT;

    /* access modifiers changed from: protected */
    public final <T> void a(String str, Ad ad, T t) {
    }

    public static synchronized a G() {
        a aVar;
        synchronized (a.class) {
            if (cl == null) {
                cl = new a();
            }
            aVar = cl;
        }
        return aVar;
    }

    private a() {
    }

    public final void a(Context context, Ad ad, String str, d.a aVar, String str2) {
        this.bT = str2;
        super.a(context, ad, str, aVar);
    }

    /* access modifiers changed from: protected */
    public final String a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        Object obj;
        StringBuilder sb = new StringBuilder("&auid=");
        sb.append(ad != null ? ad.getAUID() : "600");
        sb.append("&vidmin=");
        String str2 = "";
        sb.append(ad == null ? str2 : Integer.valueOf(ad.getMinVideoLength()));
        sb.append("&vidmax=");
        if (ad == null) {
            obj = str2;
        } else {
            obj = Integer.valueOf(ad.getMaxVideoLength());
        }
        sb.append(obj);
        if (this.bT.equals("static")) {
            str2 = "&creative=0";
        }
        sb.append(str2);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final void a(Context context, Ad ad, com.appnext.core.a aVar) throws Exception {
        AppnextWebView.u(context).a(((Interstitial) ad).getPageUrl(), (AppnextWebView.c) null);
        if (aVar != null && aVar.getAds() != null && aVar.getAds().size() > 0) {
            f.Y(((AppnextAd) aVar.getAds().get(0)).getImageURL());
        }
    }

    /* access modifiers changed from: protected */
    public final int a(Context context, g gVar) {
        int i;
        AppnextAd appnextAd = (AppnextAd) gVar;
        InterstitialAd interstitialAd = new InterstitialAd(appnextAd);
        if (!interstitialAd.getCampaignGoal().equals(b.hX) || !f.c(context, interstitialAd.getAdPackage())) {
            i = (!interstitialAd.getCampaignGoal().equals(b.hY) || f.c(context, interstitialAd.getAdPackage())) ? 0 : 2;
        } else {
            i = 1;
        }
        int b = b(context, appnextAd);
        if (i == 0 && b == 0) {
            return 0;
        }
        return i != 0 ? i : b;
    }

    /* access modifiers changed from: protected */
    public final void a(Ad ad, String str, String str2) {
        new StringBuilder("error ").append(str);
    }

    /* access modifiers changed from: protected */
    public final boolean a(Context context, Ad ad, ArrayList<?> arrayList) {
        return a(context, (ArrayList<AppnextAd>) arrayList, ((Interstitial) ad).getCreativeType(), ad) != null;
    }

    /* access modifiers changed from: protected */
    public final p c(Ad ad) {
        return c.K();
    }

    /* access modifiers changed from: protected */
    public final boolean d(Ad ad) {
        return h(ad) && k(ad).getAds() != null && k(ad).getAds().size() > 0 && k(ad).aU().longValue() + 300000 > System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public final ArrayList<AppnextAd> b(Context context, Ad ad, String str) {
        ArrayList<?> ads;
        AppnextAd a2;
        if (k(ad) == null || (ads = k(ad).getAds()) == null || (a2 = a(context, (ArrayList<AppnextAd>) ads, str, ad)) == null) {
            return null;
        }
        ads.remove(a2);
        ads.add(0, a2);
        return ads;
    }

    private static ArrayList<AppnextAd> a(ArrayList<AppnextAd> arrayList, AppnextAd appnextAd) {
        arrayList.remove(appnextAd);
        arrayList.add(0, appnextAd);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final String a(ArrayList<AppnextAd> arrayList) {
        return super.a(arrayList);
    }

    /* access modifiers changed from: protected */
    public final void a(String str, Ad ad) {
        super.a(str, ad);
    }

    private static boolean hasVideo(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrl().equals("") || !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrl30Sec().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    private static boolean c(AppnextAd appnextAd) {
        return !appnextAd.getWideImageURL().equals("");
    }

    /* access modifiers changed from: protected */
    public final AppnextAd a(Context context, ArrayList<AppnextAd> arrayList, String str, Ad ad) {
        Iterator<AppnextAd> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd next = it.next();
            if (a(next, str, ad)) {
                return next;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(com.appnext.core.AppnextAd r6, java.lang.String r7, com.appnext.core.Ad r8) {
        /*
            r5 = this;
            int r0 = r7.hashCode()
            r1 = -892481938(0xffffffffcacdce6e, float:-6743863.0)
            r2 = 0
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x002b
            r1 = 112202875(0x6b0147b, float:6.6233935E-35)
            if (r0 == r1) goto L_0x0021
            r1 = 835260319(0x31c90f9f, float:5.851646E-9)
            if (r0 == r1) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r0 = "managed"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = 0
            goto L_0x0036
        L_0x0021:
            java.lang.String r0 = "video"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = 1
            goto L_0x0036
        L_0x002b:
            java.lang.String r0 = "static"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = 2
            goto L_0x0036
        L_0x0035:
            r7 = -1
        L_0x0036:
            if (r7 == 0) goto L_0x0067
            if (r7 == r4) goto L_0x0052
            if (r7 == r3) goto L_0x003d
            goto L_0x0082
        L_0x003d:
            boolean r7 = c((com.appnext.core.AppnextAd) r6)
            if (r7 == 0) goto L_0x0082
            java.lang.String r6 = r6.getBannerID()
            java.lang.String r7 = r8.getPlacementID()
            boolean r6 = a((java.lang.String) r6, (java.lang.String) r7)
            if (r6 != 0) goto L_0x0082
            return r4
        L_0x0052:
            boolean r7 = hasVideo(r6)
            if (r7 == 0) goto L_0x0082
            java.lang.String r6 = r6.getBannerID()
            java.lang.String r7 = r8.getPlacementID()
            boolean r6 = a((java.lang.String) r6, (java.lang.String) r7)
            if (r6 != 0) goto L_0x0082
            return r4
        L_0x0067:
            boolean r7 = c((com.appnext.core.AppnextAd) r6)
            if (r7 != 0) goto L_0x0073
            boolean r7 = hasVideo(r6)
            if (r7 == 0) goto L_0x0082
        L_0x0073:
            java.lang.String r6 = r6.getBannerID()
            java.lang.String r7 = r8.getPlacementID()
            boolean r6 = a((java.lang.String) r6, (java.lang.String) r7)
            if (r6 != 0) goto L_0x0082
            return r4
        L_0x0082:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.interstitial.a.a(com.appnext.core.AppnextAd, java.lang.String, com.appnext.core.Ad):boolean");
    }

    private static int a(Context context, AppnextAd appnextAd) {
        InterstitialAd interstitialAd = new InterstitialAd(appnextAd);
        if (!interstitialAd.getCampaignGoal().equals(b.hX) || !f.c(context, interstitialAd.getAdPackage())) {
            return (!interstitialAd.getCampaignGoal().equals(b.hY) || f.c(context, interstitialAd.getAdPackage())) ? 0 : 2;
        }
        return 1;
    }

    private static int b(Context context, AppnextAd appnextAd) {
        InterstitialAd interstitialAd = new InterstitialAd(appnextAd);
        if (!interstitialAd.getCptList().equals("") && !interstitialAd.getCptList().equals("[]")) {
            try {
                JSONArray jSONArray = new JSONArray(interstitialAd.getCptList());
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

    /* access modifiers changed from: protected */
    public final void g(Ad ad) {
        if (ad != null && j(ad) == 0) {
            aW().remove(new com.appnext.core.b(ad));
        }
    }
}
