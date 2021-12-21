package com.startapp.android.publish.cache;

import android.app.Activity;
import android.content.Context;
import com.startapp.android.publish.ads.b.d;
import com.startapp.android.publish.ads.b.e;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.b.c;
import com.startapp.android.publish.cache.i;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
public class g {
    public static boolean h = false;

    /* renamed from: a  reason: collision with root package name */
    protected com.startapp.android.publish.adsCommon.g f293a;
    protected AtomicBoolean b;
    protected long c;
    protected f d;
    protected b e;
    protected Map<AdEventListener, List<StartAppAd>> f;
    protected b g;
    /* access modifiers changed from: private */
    public final AdPreferences.Placement i;
    private Context j;
    private com.startapp.android.publish.adsCommon.a k;
    private AdPreferences l;
    private String m;
    private boolean n;
    private int o;
    private boolean p;

    /* compiled from: StartAppSDK */
    public interface b {
        void a(g gVar);
    }

    /* compiled from: StartAppSDK */
    class a implements AdEventListener {
        private boolean b = false;
        private boolean c = false;

        a() {
        }

        public void onReceiveAd(Ad ad) {
            List<StartAppAd> list;
            boolean z = g.this.f293a != null && g.this.f293a.getVideoCancelCallBack();
            if (!this.b && !z) {
                this.b = true;
                synchronized (g.this.f) {
                    for (AdEventListener next : g.this.f.keySet()) {
                        if (!(next == null || (list = g.this.f.get(next)) == null)) {
                            for (StartAppAd startAppAd : list) {
                                startAppAd.setErrorMessage(ad.getErrorMessage());
                                new com.startapp.android.publish.adsCommon.adListeners.b(next).onReceiveAd(startAppAd);
                            }
                        }
                    }
                    g.this.f.clear();
                }
            }
            g.this.d.f();
            g.this.e.a();
            g.this.b.set(false);
        }

        public void onFailedToReceiveAd(Ad ad) {
            List<StartAppAd> list;
            ConcurrentHashMap concurrentHashMap;
            ConcurrentHashMap concurrentHashMap2 = null;
            if (!this.c) {
                synchronized (g.this.f) {
                    concurrentHashMap = new ConcurrentHashMap(g.this.f);
                    g.this.f293a = null;
                    g.this.f.clear();
                }
                concurrentHashMap2 = concurrentHashMap;
            }
            if (concurrentHashMap2 != null) {
                for (AdEventListener adEventListener : concurrentHashMap2.keySet()) {
                    if (!(adEventListener == null || (list = (List) concurrentHashMap2.get(adEventListener)) == null)) {
                        for (StartAppAd startAppAd : list) {
                            startAppAd.setErrorMessage(ad.getErrorMessage());
                            new com.startapp.android.publish.adsCommon.adListeners.b(adEventListener).onFailedToReceiveAd(startAppAd);
                        }
                    }
                }
            }
            this.c = true;
            g.this.e.f();
            g.this.d.a();
            g.this.b.set(false);
        }
    }

    public g(Context context, AdPreferences.Placement placement, AdPreferences adPreferences) {
        this.f293a = null;
        this.b = new AtomicBoolean(false);
        this.m = null;
        this.n = false;
        this.d = null;
        this.e = null;
        this.f = new ConcurrentHashMap();
        this.p = true;
        this.i = placement;
        this.l = adPreferences;
        a(context);
        o();
    }

    public g(Context context, AdPreferences.Placement placement, AdPreferences adPreferences, boolean z) {
        this(context, placement, adPreferences);
        this.p = z;
    }

    private void a(Context context) {
        if (context instanceof Activity) {
            this.j = context.getApplicationContext();
            this.k = new com.startapp.android.publish.adsCommon.a((Activity) context);
            return;
        }
        this.j = context;
        this.k = null;
    }

    private void o() {
        this.d = new f(this);
        this.e = new b(this);
    }

    public AdPreferences a() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public void a(AdPreferences adPreferences) {
        this.l = adPreferences;
    }

    public com.startapp.android.publish.adsCommon.g b() {
        return this.f293a;
    }

    /* access modifiers changed from: protected */
    public AdPreferences.Placement c() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.m = str;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        this.n = z;
    }

    public void a(StartAppAd startAppAd, AdEventListener adEventListener) {
        a(startAppAd, adEventListener, false);
    }

    /* access modifiers changed from: protected */
    public void b(boolean z) {
        a((StartAppAd) null, (AdEventListener) null, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008a, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.startapp.android.publish.adsCommon.StartAppAd r6, com.startapp.android.publish.adsCommon.adListeners.AdEventListener r7, boolean r8) {
        /*
            r5 = this;
            java.util.Map<com.startapp.android.publish.adsCommon.adListeners.AdEventListener, java.util.List<com.startapp.android.publish.adsCommon.StartAppAd>> r0 = r5.f
            monitor-enter(r0)
            boolean r1 = r5.g()     // Catch:{ all -> 0x008b }
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0016
            boolean r1 = r5.u()     // Catch:{ all -> 0x008b }
            if (r1 != 0) goto L_0x0016
            if (r8 == 0) goto L_0x0014
            goto L_0x0016
        L_0x0014:
            r8 = 0
            goto L_0x0017
        L_0x0016:
            r8 = 1
        L_0x0017:
            r1 = 3
            if (r8 == 0) goto L_0x0065
            if (r6 == 0) goto L_0x0035
            if (r7 == 0) goto L_0x0035
            java.util.Map<com.startapp.android.publish.adsCommon.adListeners.AdEventListener, java.util.List<com.startapp.android.publish.adsCommon.StartAppAd>> r8 = r5.f     // Catch:{ all -> 0x008b }
            java.lang.Object r8 = r8.get(r7)     // Catch:{ all -> 0x008b }
            java.util.List r8 = (java.util.List) r8     // Catch:{ all -> 0x008b }
            if (r8 != 0) goto L_0x0032
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x008b }
            r8.<init>()     // Catch:{ all -> 0x008b }
            java.util.Map<com.startapp.android.publish.adsCommon.adListeners.AdEventListener, java.util.List<com.startapp.android.publish.adsCommon.StartAppAd>> r4 = r5.f     // Catch:{ all -> 0x008b }
            r4.put(r7, r8)     // Catch:{ all -> 0x008b }
        L_0x0032:
            r8.add(r6)     // Catch:{ all -> 0x008b }
        L_0x0035:
            java.util.concurrent.atomic.AtomicBoolean r6 = r5.b     // Catch:{ all -> 0x008b }
            boolean r6 = r6.compareAndSet(r2, r3)     // Catch:{ all -> 0x008b }
            if (r6 == 0) goto L_0x004b
            com.startapp.android.publish.cache.f r6 = r5.d     // Catch:{ all -> 0x008b }
            r6.g()     // Catch:{ all -> 0x008b }
            com.startapp.android.publish.cache.b r6 = r5.e     // Catch:{ all -> 0x008b }
            r6.g()     // Catch:{ all -> 0x008b }
            r5.p()     // Catch:{ all -> 0x008b }
            goto L_0x0089
        L_0x004b:
            java.lang.String r6 = "CachedAd"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            r7.<init>()     // Catch:{ all -> 0x008b }
            com.startapp.android.publish.common.model.AdPreferences$Placement r8 = r5.i     // Catch:{ all -> 0x008b }
            r7.append(r8)     // Catch:{ all -> 0x008b }
            java.lang.String r8 = " ad is currently loading"
            r7.append(r8)     // Catch:{ all -> 0x008b }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x008b }
            com.startapp.common.a.g.a((java.lang.String) r6, (int) r1, (java.lang.String) r7)     // Catch:{ all -> 0x008b }
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            return
        L_0x0065:
            java.lang.String r8 = "CachedAd"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            r2.<init>()     // Catch:{ all -> 0x008b }
            com.startapp.android.publish.common.model.AdPreferences$Placement r3 = r5.i     // Catch:{ all -> 0x008b }
            r2.append(r3)     // Catch:{ all -> 0x008b }
            java.lang.String r3 = " ad already loaded"
            r2.append(r3)     // Catch:{ all -> 0x008b }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008b }
            com.startapp.common.a.g.a((java.lang.String) r8, (int) r1, (java.lang.String) r2)     // Catch:{ all -> 0x008b }
            if (r6 == 0) goto L_0x0089
            if (r7 == 0) goto L_0x0089
            com.startapp.android.publish.adsCommon.adListeners.b r8 = new com.startapp.android.publish.adsCommon.adListeners.b     // Catch:{ all -> 0x008b }
            r8.<init>(r7)     // Catch:{ all -> 0x008b }
            r8.onReceiveAd(r6)     // Catch:{ all -> 0x008b }
        L_0x0089:
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            return
        L_0x008b:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.cache.g.a(com.startapp.android.publish.adsCommon.StartAppAd, com.startapp.android.publish.adsCommon.adListeners.AdEventListener, boolean):void");
    }

    /* access modifiers changed from: protected */
    public void d() {
        com.startapp.common.a.g.a("CachedAd", 4, "Invalidating: " + this.i);
        if (g()) {
            if (c.a(this.j, (Ad) this.f293a) || u()) {
                com.startapp.common.a.g.a("CachedAd", 3, "App present or cache TTL passed, reloading");
                b(true);
            } else if (!this.b.get()) {
                this.d.f();
            }
        } else if (!this.b.get()) {
            this.e.f();
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        this.e.h();
    }

    /* access modifiers changed from: protected */
    public void f() {
        this.d.h();
    }

    public boolean g() {
        com.startapp.android.publish.adsCommon.g gVar = this.f293a;
        return gVar != null && gVar.isReady();
    }

    public boolean h() {
        return this.p;
    }

    public com.startapp.android.publish.adsCommon.g i() {
        if (!g()) {
            return null;
        }
        com.startapp.android.publish.adsCommon.g gVar = this.f293a;
        m();
        if (!AdsConstants.OVERRIDE_NETWORK.booleanValue() && h()) {
            com.startapp.common.a.g.a("CachedAd", 3, "Ad shown, reloading " + this.i);
            b(true);
            return gVar;
        } else if (h()) {
            return gVar;
        } else {
            b bVar = this.g;
            if (bVar != null) {
                bVar.a(this);
            }
            f fVar = this.d;
            if (fVar == null) {
                return gVar;
            }
            fVar.a();
            return gVar;
        }
    }

    /* renamed from: com.startapp.android.publish.cache.g$3  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f296a;

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
                com.startapp.android.publish.common.model.AdPreferences$Placement[] r0 = com.startapp.android.publish.common.model.AdPreferences.Placement.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f296a = r0
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_FULL_SCREEN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f296a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_OVERLAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f296a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_OFFER_WALL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f296a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_RETURN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f296a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_SPLASH     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.cache.g.AnonymousClass3.<clinit>():void");
        }
    }

    public com.startapp.android.publish.adsCommon.g j() {
        com.startapp.android.publish.adsCommon.g gVar;
        i.a(this.j, this.l);
        int i2 = AnonymousClass3.f296a[this.i.ordinal()];
        if (i2 == 1) {
            gVar = new d(this.j);
        } else if (i2 != 2) {
            if (i2 == 3) {
                gVar = v();
            } else if (i2 == 4) {
                gVar = new e(this.j);
            } else if (i2 != 5) {
                gVar = new d(this.j);
            } else {
                gVar = new com.startapp.android.publish.ads.splash.b(this.j);
            }
        } else if (i.a(4)) {
            gVar = new com.startapp.android.publish.ads.video.e(this.j);
        } else {
            gVar = new d(this.j);
        }
        com.startapp.common.a.g.a("CachedAd", 4, "ad Type: [" + gVar.getClass().toString() + "]");
        return gVar;
    }

    private void p() {
        com.startapp.android.publish.adsCommon.g gVar = this.f293a;
        if (gVar != null) {
            gVar.setVideoCancelCallBack(false);
        }
        if (q()) {
            a(false);
            r();
            return;
        }
        n();
    }

    private boolean q() {
        return this.n && this.m != null;
    }

    private void r() {
        com.startapp.common.a.g.a("CachedAd", 4, "Loading " + this.i + " from disk " + "file name: " + this.m);
        final a aVar = new a();
        i.a(this.j, this.m, (i.a) new i.a() {
            public void a(com.startapp.android.publish.adsCommon.g gVar) {
                com.startapp.common.a.g.a("CachedAd", 4, "Success loading from disk: " + g.this.i);
                g.this.f293a = gVar;
            }
        }, (AdEventListener) new AdEventListener() {
            public void onReceiveAd(Ad ad) {
                aVar.onReceiveAd(ad);
            }

            public void onFailedToReceiveAd(Ad ad) {
                com.startapp.common.a.g.a("CachedAd", 3, "Failed to load " + g.this.i + " from disk");
                g.this.f293a = null;
                g.this.n();
            }
        });
    }

    public void a(b bVar) {
        this.g = bVar;
    }

    public boolean k() {
        if (s()) {
            b(true);
            t();
            return true;
        }
        b bVar = this.g;
        if (bVar == null) {
            return false;
        }
        bVar.a(this);
        return false;
    }

    private boolean s() {
        return this.o < MetaData.getInstance().getStopAutoLoadAmount();
    }

    private void t() {
        this.o++;
    }

    public int l() {
        return this.o;
    }

    public void a(int i2) {
        this.o = i2;
    }

    /* access modifiers changed from: protected */
    public void m() {
        this.o = 0;
    }

    /* access modifiers changed from: protected */
    public void n() {
        com.startapp.common.a.g.a("CachedAd", 4, "Loading " + this.i + " from server");
        com.startapp.android.publish.adsCommon.g j2 = j();
        this.f293a = j2;
        j2.setActivityExtra(this.k);
        this.f293a.load(this.l, new a());
        this.c = System.currentTimeMillis();
    }

    private boolean u() {
        com.startapp.android.publish.adsCommon.g gVar = this.f293a;
        if (gVar == null) {
            return false;
        }
        return gVar.hasAdCacheTtlPassed();
    }

    private com.startapp.android.publish.adsCommon.g v() {
        boolean z = new Random().nextInt(100) < com.startapp.android.publish.adsCommon.b.a().d();
        boolean a2 = com.startapp.android.publish.adsCommon.Utils.i.a(this.l, "forceOfferWall3D");
        boolean a3 = true ^ com.startapp.android.publish.adsCommon.Utils.i.a(this.l, "forceOfferWall2D");
        boolean a4 = com.startapp.android.publish.adsCommon.Utils.i.a(64);
        if (w() || (a4 && ((z || a2) && a3))) {
            return new com.startapp.android.publish.ads.c.b.b(this.j);
        }
        return new com.startapp.android.publish.ads.c.a.b(this.j);
    }

    private boolean w() {
        return com.startapp.android.publish.adsCommon.Utils.i.a(64) && !com.startapp.android.publish.adsCommon.Utils.i.a(128);
    }
}
