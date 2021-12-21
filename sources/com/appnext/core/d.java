package com.appnext.core;

import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Pair;
import com.google.android.gms.common.internal.AccountType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class d {
    private static final int fP = 50;
    protected final int fQ = 0;
    protected final int fR = 1;
    protected final int fS = 2;
    protected final int fT = 3;
    /* access modifiers changed from: private */
    public final HashMap<b, a> fU = new HashMap<>();

    public interface a {
        <T> void a(T t);

        void error(String str);
    }

    protected static int aV() {
        return 8000;
    }

    /* access modifiers changed from: protected */
    public abstract int a(Context context, g gVar);

    /* access modifiers changed from: protected */
    public abstract String a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList);

    /* access modifiers changed from: protected */
    public abstract void a(Context context, Ad ad, a aVar) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void a(Ad ad, String str, String str2);

    /* access modifiers changed from: protected */
    public abstract <T> void a(String str, Ad ad, T t);

    /* access modifiers changed from: protected */
    public abstract boolean a(Context context, Ad ad, ArrayList<?> arrayList);

    /* access modifiers changed from: protected */
    public abstract p c(Ad ad);

    public final void a(Context context, Ad ad, String str, a aVar) {
        a(context, ad, str, aVar, true);
    }

    public final void a(Context context, Ad ad, String str, a aVar, boolean z) {
        final Ad ad2 = ad;
        final Context context2 = context;
        final a aVar2 = aVar;
        final String str2 = str;
        final boolean z2 = z;
        new Thread(new Runnable() {
            public final void run() {
                try {
                    if (d.this.a(ad2) || (d.this.h(ad2) && d.this.i(ad2))) {
                        d.this.a(context2, ad2, d.this.k(ad2));
                    }
                } catch (Throwable unused) {
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        try {
                            if (d.this.a(ad2) || (d.this.h(ad2) && d.this.i(ad2))) {
                                ArrayList<?> ads = d.this.k(ad2).getAds();
                                if (ads.size() == 0) {
                                    aVar2.error(AppnextError.NO_ADS);
                                    return;
                                } else if (!d.this.a(context2, ad2, ads)) {
                                    d.this.l(str2);
                                } else if (aVar2 != null) {
                                    aVar2.a(ads);
                                    return;
                                } else {
                                    return;
                                }
                            }
                        } catch (Throwable unused) {
                            if (aVar2 != null) {
                                aVar2.error(AppnextError.NO_ADS);
                            }
                        }
                        try {
                            if (!f.bd().equals("")) {
                                d.this.b(context2, ad2, str2, aVar2, z2);
                                return;
                            }
                            f.m(context2);
                            d.this.b(context2, ad2, str2, aVar2, z2);
                        } catch (Throwable unused2) {
                            if (aVar2 != null) {
                                aVar2.error(AppnextError.NO_ADS);
                            }
                        }
                    }
                });
            }
        }).start();
    }

    private String b(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        StringBuilder sb = new StringBuilder("https://global.appnext.com/offerWallApi.aspx?ext=t&pimp=1&igroup=sdk&m=1&osid=100&s2s=1&type=json&id=");
        sb.append(str);
        sb.append("&cnt=50");
        sb.append("&tid=");
        sb.append(ad != null ? ad.getTID() : "301");
        sb.append("&vid=");
        sb.append(ad != null ? ad.getVID() : "2.5.1.472");
        sb.append("&cat=");
        String str2 = "";
        sb.append(ad != null ? ad.getCategories() : str2);
        sb.append("&pbk=");
        sb.append(ad != null ? ad.getPostback() : str2);
        sb.append("&did=");
        sb.append(f.b(context, Boolean.parseBoolean(c(ad).get("didPrivacy"))));
        sb.append("&devn=");
        sb.append(f.be());
        sb.append("&dosv=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("&dct=");
        sb.append(f.Z(f.o(context)));
        sb.append("&lang=");
        sb.append(Locale.getDefault().getLanguage());
        sb.append("&dcc=");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getSimState() == 5) {
            String simOperator = telephonyManager.getSimOperator();
            str2 = simOperator.substring(0, 3) + "_" + simOperator.substring(3);
        }
        sb.append(str2);
        sb.append("&dds=0");
        sb.append("&packageId=");
        sb.append(context.getPackageName());
        sb.append("&g=");
        sb.append(j(context));
        sb.append("&rnd=");
        sb.append(new Random().nextInt());
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public void b(Context context, Ad ad, String str, a aVar, boolean z) {
        final Ad ad2 = ad;
        final Context context2 = context;
        final a aVar2 = aVar;
        final String str2 = str;
        final boolean z2 = z;
        new Thread() {
            /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
                java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
                	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
                	at java.util.ArrayList.get(ArrayList.java:435)
                	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
                	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
                	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
                	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
                	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
                */
            public final void run() {
                /*
                    r7 = this;
                    super.run()
                    com.appnext.core.Ad r0 = r2     // Catch:{ all -> 0x000e }
                    android.content.Context r1 = r3     // Catch:{ all -> 0x000e }
                    java.lang.String r1 = com.appnext.core.f.r(r1)     // Catch:{ all -> 0x000e }
                    r0.setSessionId(r1)     // Catch:{ all -> 0x000e }
                L_0x000e:
                    com.appnext.core.d r0 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    java.util.HashMap r0 = r0.fU     // Catch:{ all -> 0x0179 }
                    monitor-enter(r0)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.d r1 = com.appnext.core.d.this     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r2 = r2     // Catch:{ all -> 0x0176 }
                    com.appnext.core.a r1 = r1.k(r2)     // Catch:{ all -> 0x0176 }
                    r2 = 1
                    if (r1 == 0) goto L_0x0041
                    com.appnext.core.d r1 = com.appnext.core.d.this     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0176 }
                    com.appnext.core.a r1 = r1.k(r3)     // Catch:{ all -> 0x0176 }
                    int r1 = r1.getState()     // Catch:{ all -> 0x0176 }
                    if (r1 != r2) goto L_0x0041
                    com.appnext.core.d$a r1 = r4     // Catch:{ all -> 0x0176 }
                    if (r1 == 0) goto L_0x003f
                    com.appnext.core.d r1 = com.appnext.core.d.this     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r2 = r2     // Catch:{ all -> 0x0176 }
                    com.appnext.core.a r1 = r1.k(r2)     // Catch:{ all -> 0x0176 }
                    com.appnext.core.d$a r2 = r4     // Catch:{ all -> 0x0176 }
                    r1.a((com.appnext.core.d.a) r2)     // Catch:{ all -> 0x0176 }
                L_0x003f:
                    monitor-exit(r0)     // Catch:{ all -> 0x0176 }
                    return
                L_0x0041:
                    com.appnext.core.a r1 = new com.appnext.core.a     // Catch:{ all -> 0x0176 }
                    r1.<init>()     // Catch:{ all -> 0x0176 }
                    com.appnext.core.d$a r3 = r4     // Catch:{ all -> 0x0176 }
                    r1.a((com.appnext.core.d.a) r3)     // Catch:{ all -> 0x0176 }
                    java.lang.String r3 = r5     // Catch:{ all -> 0x0176 }
                    r1.setPlacementID(r3)     // Catch:{ all -> 0x0176 }
                    r1.setState(r2)     // Catch:{ all -> 0x0176 }
                    com.appnext.core.d r2 = com.appnext.core.d.this     // Catch:{ all -> 0x0176 }
                    java.util.HashMap r2 = r2.fU     // Catch:{ all -> 0x0176 }
                    com.appnext.core.b r3 = new com.appnext.core.b     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x0176 }
                    r3.<init>(r4)     // Catch:{ all -> 0x0176 }
                    r2.remove(r3)     // Catch:{ all -> 0x0176 }
                    com.appnext.core.d r2 = com.appnext.core.d.this     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0176 }
                    r2.a((com.appnext.core.Ad) r3, (com.appnext.core.a) r1)     // Catch:{ all -> 0x0176 }
                    monitor-exit(r0)     // Catch:{ all -> 0x0176 }
                    r0 = 0
                    java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r1.<init>()     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r2.<init>()     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    com.appnext.core.d r3 = com.appnext.core.d.this     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    android.content.Context r4 = r3     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    com.appnext.core.Ad r5 = r2     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r6 = r5     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r3 = com.appnext.core.d.a((com.appnext.core.d) r3, (android.content.Context) r4, (com.appnext.core.Ad) r5, (java.lang.String) r6, (java.util.ArrayList) r1)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    com.appnext.core.d r3 = com.appnext.core.d.this     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    android.content.Context r4 = r3     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    com.appnext.core.Ad r5 = r2     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r6 = r5     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r3 = r3.a((android.content.Context) r4, (com.appnext.core.Ad) r5, (java.lang.String) r6, (java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>>) r1)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r2 = r2.toString()     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r4 = "AdsManager request url: "
                    r3.<init>(r4)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r3.append(r2)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r3 = 8000(0x1f40, float:1.121E-41)
                    java.lang.String r1 = com.appnext.core.f.a((java.lang.String) r2, (java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>>) r1, (int) r3)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r2 = "{}"
                    boolean r2 = r1.equals(r2)     // Catch:{ all -> 0x0179 }
                    if (r2 != 0) goto L_0x013f
                    boolean r2 = com.appnext.core.d.P(r1)     // Catch:{ all -> 0x0179 }
                    if (r2 == 0) goto L_0x00b8
                    goto L_0x013f
                L_0x00b8:
                    com.appnext.core.d r2 = com.appnext.core.d.this     // Catch:{ all -> 0x0130 }
                    android.content.Context r3 = r3     // Catch:{ all -> 0x0130 }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x0130 }
                    r5 = 50
                    java.util.ArrayList r1 = r2.a((android.content.Context) r3, (com.appnext.core.Ad) r4, (java.lang.String) r1, (int) r5)     // Catch:{ all -> 0x0130 }
                    int r2 = r1.size()     // Catch:{ all -> 0x0179 }
                    if (r2 != 0) goto L_0x00d4
                    com.appnext.core.d r0 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r1 = "No Ads"
                    com.appnext.core.Ad r2 = r2     // Catch:{ all -> 0x0179 }
                    r0.b((java.lang.String) r1, (com.appnext.core.Ad) r2)     // Catch:{ all -> 0x0179 }
                    return
                L_0x00d4:
                    com.appnext.core.d r2 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0179 }
                    com.appnext.core.a r2 = r2.k(r3)     // Catch:{ all -> 0x0179 }
                    r2.d(r1)     // Catch:{ all -> 0x0179 }
                    boolean r2 = r6     // Catch:{ all -> 0x0179 }
                    r3 = 2
                    if (r2 == 0) goto L_0x0113
                    com.appnext.core.d r2 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    android.content.Context r4 = r3     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r5 = r2     // Catch:{ all -> 0x0179 }
                    boolean r1 = r2.a((android.content.Context) r4, (com.appnext.core.Ad) r5, (java.util.ArrayList<?>) r1)     // Catch:{ all -> 0x0179 }
                    if (r1 != 0) goto L_0x00f7
                    com.appnext.core.d r1 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r2 = r5     // Catch:{ all -> 0x0179 }
                    r1.l((java.lang.String) r2)     // Catch:{ all -> 0x0179 }
                L_0x00f7:
                    r1 = 3
                    if (r0 >= r1) goto L_0x0113
                    com.appnext.core.d r1 = com.appnext.core.d.this     // Catch:{ all -> 0x010c }
                    android.content.Context r2 = r3     // Catch:{ all -> 0x010c }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x010c }
                    com.appnext.core.d r5 = com.appnext.core.d.this     // Catch:{ all -> 0x010c }
                    com.appnext.core.Ad r6 = r2     // Catch:{ all -> 0x010c }
                    com.appnext.core.a r5 = r5.k(r6)     // Catch:{ all -> 0x010c }
                    r1.a((android.content.Context) r2, (com.appnext.core.Ad) r4, (com.appnext.core.a) r5)     // Catch:{ all -> 0x010c }
                    goto L_0x0113
                L_0x010c:
                    r1 = move-exception
                    if (r0 == r3) goto L_0x0112
                    int r0 = r0 + 1
                    goto L_0x00f7
                L_0x0112:
                    throw r1     // Catch:{ all -> 0x0179 }
                L_0x0113:
                    com.appnext.core.d r0 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r1 = r2     // Catch:{ all -> 0x0179 }
                    com.appnext.core.a r0 = r0.k(r1)     // Catch:{ all -> 0x0179 }
                    r0.setState(r3)     // Catch:{ all -> 0x0179 }
                    android.os.Handler r0 = new android.os.Handler     // Catch:{ all -> 0x0179 }
                    android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0179 }
                    r0.<init>(r1)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.d$2$1 r1 = new com.appnext.core.d$2$1     // Catch:{ all -> 0x0179 }
                    r1.<init>()     // Catch:{ all -> 0x0179 }
                    r0.post(r1)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0130:
                    r0 = move-exception
                    com.appnext.core.d r1 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r2 = "Internal error"
                    java.lang.String r0 = com.appnext.core.f.b((java.lang.Throwable) r0)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0179 }
                    r1.a((java.lang.String) r2, (java.lang.String) r0, (com.appnext.core.Ad) r3)     // Catch:{ all -> 0x0179 }
                    return
                L_0x013f:
                    com.appnext.core.d r0 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r2 = "No Ads"
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0179 }
                    r0.a((java.lang.String) r2, (java.lang.String) r1, (com.appnext.core.Ad) r3)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0149:
                    r0 = move-exception
                    com.appnext.core.d r1 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r2 = "Internal error"
                    java.lang.String r0 = com.appnext.core.f.b((java.lang.Throwable) r0)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0179 }
                    r1.a((java.lang.String) r2, (java.lang.String) r0, (com.appnext.core.Ad) r3)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0158:
                    r1 = move-exception
                    com.appnext.core.d r2 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r3 = "Connection Error"
                    java.lang.String r1 = com.appnext.core.f.b((java.lang.Throwable) r1)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x0179 }
                    r2.a((java.lang.String) r3, (java.lang.String) r1, (com.appnext.core.Ad) r4, (int) r0)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0167:
                    r1 = move-exception
                    com.appnext.core.d r2 = com.appnext.core.d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r3 = "Timeout"
                    java.lang.String r1 = com.appnext.core.f.b((java.lang.Throwable) r1)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x0179 }
                    r2.a((java.lang.String) r3, (java.lang.String) r1, (com.appnext.core.Ad) r4, (int) r0)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0176:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0176 }
                    throw r1     // Catch:{ all -> 0x0179 }
                L_0x0179:
                    r0 = move-exception
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    java.lang.String r2 = "finished custom after load with error "
                    r1.<init>(r2)
                    java.lang.String r2 = com.appnext.core.f.b((java.lang.Throwable) r0)
                    r1.append(r2)
                    com.appnext.core.d r1 = com.appnext.core.d.this
                    java.lang.String r0 = r0.getMessage()
                    com.appnext.core.Ad r2 = r2
                    r1.b((java.lang.String) r0, (com.appnext.core.Ad) r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.d.AnonymousClass2.run():void");
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public boolean a(Ad ad) {
        try {
            return h(ad) && i(ad);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final boolean h(Ad ad) {
        return (this.fU == null || k(ad) == null || k(ad).getState() != 2 || k(ad).getAds() == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    public final boolean i(Ad ad) {
        return j(ad) == 0 ? this.fU != null && k(ad) != null && k(ad).getAds().size() == 0 && k(ad).aU().longValue() + 60000 > System.currentTimeMillis() : (this.fU == null || k(ad) == null || k(ad).aU().longValue() + j(ad) <= System.currentTimeMillis()) ? false : true;
    }

    /* access modifiers changed from: protected */
    public final long j(Ad ad) {
        try {
            return c(ad).get("_cachingRequest") == null ? a(ad, "ads_caching_time_minutes") * 60000 : a(ad, "_cachingRequest") * 1000;
        } catch (Throwable unused) {
            return a(ad, "ads_caching_time_minutes") * 60000;
        }
    }

    private long a(Ad ad, String str) {
        return Long.valueOf(c(ad).get(str)).longValue();
    }

    public final void c(Context context, Ad ad, String str) {
        this.fU.remove(new b(ad));
        b(context, ad, str, (a) null, true);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x007f, code lost:
        if (r7.getRevenueType().equals("cpc") != false) goto L_0x0081;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.ArrayList<? extends com.appnext.core.g> a(final android.content.Context r11, com.appnext.core.Ad r12, java.lang.String r13, int r14) throws org.json.JSONException {
        /*
            r10 = this;
            com.appnext.core.a r14 = r10.k(r12)
            r14.N(r13)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>(r13)
            java.lang.String r13 = "apps"
            org.json.JSONArray r13 = r1.getJSONArray(r13)
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0021:
            int r6 = r13.length()
            if (r1 >= r6) goto L_0x00b6
            org.json.JSONObject r6 = r13.getJSONObject(r1)
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00b2 }
            com.appnext.core.g r6 = parseAd(r6)     // Catch:{ all -> 0x00b2 }
            com.appnext.core.AppnextAd r6 = (com.appnext.core.AppnextAd) r6     // Catch:{ all -> 0x00b2 }
            int r7 = r14.size()     // Catch:{ all -> 0x00b2 }
            r6.setAdID(r7)     // Catch:{ all -> 0x00b2 }
            java.lang.String r7 = r12.getPlacementID()     // Catch:{ all -> 0x00b2 }
            r6.setPlacementID(r7)     // Catch:{ all -> 0x00b2 }
            int r7 = r10.a((android.content.Context) r11, (com.appnext.core.g) r6)     // Catch:{ all -> 0x00b2 }
            if (r7 != 0) goto L_0x008b
            com.appnext.core.AppnextAd r7 = b((java.util.ArrayList<com.appnext.core.AppnextAd>) r14, (com.appnext.core.AppnextAd) r6)     // Catch:{ all -> 0x00b2 }
            if (r7 == 0) goto L_0x0085
            r14.remove(r7)     // Catch:{ all -> 0x00b2 }
            java.lang.String r8 = r7.getRevenueType()     // Catch:{ all -> 0x00b2 }
            java.lang.String r9 = r6.getRevenueType()     // Catch:{ all -> 0x00b2 }
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x00b2 }
            if (r8 == 0) goto L_0x0075
            java.lang.String r8 = r7.getRevenueRate()     // Catch:{ all -> 0x00b2 }
            float r8 = java.lang.Float.parseFloat(r8)     // Catch:{ all -> 0x00b2 }
            java.lang.String r9 = r6.getRevenueRate()     // Catch:{ all -> 0x00b2 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ all -> 0x00b2 }
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 < 0) goto L_0x0082
            goto L_0x0081
        L_0x0075:
            java.lang.String r8 = r7.getRevenueType()     // Catch:{ all -> 0x00b2 }
            java.lang.String r9 = "cpc"
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x00b2 }
            if (r8 == 0) goto L_0x0082
        L_0x0081:
            r6 = r7
        L_0x0082:
            int r5 = r5 + 1
            goto L_0x0087
        L_0x0085:
            int r2 = r2 + 1
        L_0x0087:
            r14.add(r6)     // Catch:{ all -> 0x00b2 }
            goto L_0x00a9
        L_0x008b:
            java.lang.String r6 = r6.getBannerID()     // Catch:{ all -> 0x00b2 }
            r0.append(r6)     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = ","
            r0.append(r6)     // Catch:{ all -> 0x00b2 }
            r6 = 1
            if (r7 == r6) goto L_0x00a7
            r6 = 2
            if (r7 == r6) goto L_0x00a4
            r6 = 3
            if (r7 == r6) goto L_0x00a1
            goto L_0x00a9
        L_0x00a1:
            int r5 = r5 + 1
            goto L_0x00a9
        L_0x00a4:
            int r4 = r4 + 1
            goto L_0x00a9
        L_0x00a7:
            int r3 = r3 + 1
        L_0x00a9:
            int r6 = r14.size()     // Catch:{ all -> 0x00b2 }
            r7 = 50
            if (r6 != r7) goto L_0x00b2
            goto L_0x00b6
        L_0x00b2:
            int r1 = r1 + 1
            goto L_0x0021
        L_0x00b6:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "Filtering values {count = "
            r12.<init>(r13)
            r12.append(r2)
            java.lang.String r13 = ", new filtered = "
            r12.append(r13)
            r12.append(r3)
            java.lang.String r13 = ", existing  filtered = "
            r12.append(r13)
            r12.append(r4)
            java.lang.String r13 = ",  other = "
            r12.append(r13)
            r12.append(r5)
            java.lang.Thread r12 = new java.lang.Thread
            com.appnext.core.d$3 r13 = new com.appnext.core.d$3
            r13.<init>(r0, r11)
            r12.<init>(r13)
            r12.start()
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.d.a(android.content.Context, com.appnext.core.Ad, java.lang.String, int):java.util.ArrayList");
    }

    private static AppnextAd a(AppnextAd appnextAd, AppnextAd appnextAd2) {
        return appnextAd.getRevenueType().equals(appnextAd2.getRevenueType()) ? Float.parseFloat(appnextAd.getRevenueRate()) < Float.parseFloat(appnextAd2.getRevenueRate()) ? appnextAd2 : appnextAd : appnextAd.getRevenueType().equals("cpc") ? appnextAd : appnextAd2;
    }

    private static AppnextAd b(ArrayList<AppnextAd> arrayList, AppnextAd appnextAd) {
        Iterator<AppnextAd> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd next = it.next();
            if (next.getAdPackage().equals(appnextAd.getAdPackage())) {
                return next;
            }
        }
        return null;
    }

    protected static boolean P(String str) {
        try {
            return new JSONObject(str).has("rnd");
        } catch (Throwable unused) {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public String a(ArrayList<AppnextAd> arrayList) {
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<AppnextAd> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(new JSONObject(it.next().getAdJSON()));
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("apps", jSONArray);
            return jSONObject.toString().replace(" ", "\\u2028").replace(" ", "\\u2029");
        } catch (Throwable unused) {
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public final void b(String str, Ad ad) {
        a(str, "", ad);
    }

    /* access modifiers changed from: protected */
    public final void a(String str, String str2, Ad ad) {
        a(str, str2, ad, 2);
    }

    /* access modifiers changed from: protected */
    public final void a(String str, String str2, Ad ad, int i) {
        final Ad ad2 = ad;
        final int i2 = i;
        final String str3 = str;
        final String str4 = str2;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public final void run() {
                a k = d.this.k(ad2);
                if (k != null) {
                    if (k.getAds() == null) {
                        k.d(new ArrayList());
                    } else {
                        k.d(k.getAds());
                    }
                    k.setState(i2);
                    k.O(str3);
                    d dVar = d.this;
                    Ad ad = ad2;
                    dVar.a(ad, str3 + " " + str4, k.getPlacementID());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public final a k(Ad ad) {
        return this.fU.get(new b(ad));
    }

    /* access modifiers changed from: protected */
    public final HashMap<b, a> aW() {
        return this.fU;
    }

    /* access modifiers changed from: protected */
    public final void a(Ad ad, a aVar) {
        this.fU.put(new b(ad), aVar);
    }

    public static String d(AppnextAd appnextAd) {
        return appnextAd.getAdJSON();
    }

    public final String l(Ad ad) {
        return k(ad).A();
    }

    public static g parseAd(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            AppnextAd appnextAd = (AppnextAd) l.a((Class<?>) AppnextAd.class, jSONObject);
            if (appnextAd != null) {
                appnextAd.setAdJSON(jSONObject.toString());
                if (jSONObject.has("sid")) {
                    appnextAd.setSession(jSONObject.getString("sid"));
                }
                if (appnextAd.getStoreRating().equals("")) {
                    appnextAd.setStoreRating("0");
                }
            }
            return appnextAd;
        } catch (Throwable unused) {
            return null;
        }
    }

    protected static boolean a(String str, String str2) {
        return j.bj().o(str, str2);
    }

    /* access modifiers changed from: protected */
    public void l(String str) {
        j.bj().ab(str);
    }

    /* access modifiers changed from: protected */
    public void a(String str, Ad ad) {
        j.bj().n(str, ad.getPlacementID());
    }

    protected static int j(Context context) {
        try {
            if (f.a(context, "android.permission.READ_CONTACTS") && f.a(context, "android.permission.GET_ACCOUNTS")) {
                return AccountManager.get(context).getAccountsByType(AccountType.GOOGLE).length > 0 ? 0 : 1;
            }
        } catch (Throwable unused) {
        }
        return 2;
    }

    static /* synthetic */ String a(d dVar, Context context, Ad ad, String str, ArrayList arrayList) {
        StringBuilder sb = new StringBuilder("https://global.appnext.com/offerWallApi.aspx?ext=t&pimp=1&igroup=sdk&m=1&osid=100&s2s=1&type=json&id=");
        sb.append(str);
        sb.append("&cnt=50");
        sb.append("&tid=");
        sb.append(ad != null ? ad.getTID() : "301");
        sb.append("&vid=");
        sb.append(ad != null ? ad.getVID() : "2.5.1.472");
        sb.append("&cat=");
        String str2 = "";
        sb.append(ad != null ? ad.getCategories() : str2);
        sb.append("&pbk=");
        sb.append(ad != null ? ad.getPostback() : str2);
        sb.append("&did=");
        sb.append(f.b(context, Boolean.parseBoolean(dVar.c(ad).get("didPrivacy"))));
        sb.append("&devn=");
        sb.append(f.be());
        sb.append("&dosv=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("&dct=");
        sb.append(f.Z(f.o(context)));
        sb.append("&lang=");
        sb.append(Locale.getDefault().getLanguage());
        sb.append("&dcc=");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getSimState() == 5) {
            String simOperator = telephonyManager.getSimOperator();
            str2 = simOperator.substring(0, 3) + "_" + simOperator.substring(3);
        }
        sb.append(str2);
        sb.append("&dds=0");
        sb.append("&packageId=");
        sb.append(context.getPackageName());
        sb.append("&g=");
        sb.append(j(context));
        sb.append("&rnd=");
        sb.append(new Random().nextInt());
        return sb.toString();
    }
}
