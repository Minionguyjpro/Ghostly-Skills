package com.startapp.android.publish.cache;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.b;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.adsCommon.m;
import com.startapp.android.publish.cache.g;
import com.startapp.android.publish.cache.i;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.d;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a.e;
import com.startapp.common.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: StartAppSDK */
public class a {
    private static a c = new a();

    /* renamed from: a  reason: collision with root package name */
    final Map<c, g> f282a = new ConcurrentHashMap();
    protected boolean b = false;
    private Map<String, String> d = new WeakHashMap();
    private boolean e = false;
    private Queue<C0007a> f = new ConcurrentLinkedQueue();
    private g.b g;
    private Context h;

    /* renamed from: com.startapp.android.publish.cache.a$a  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    private class C0007a {

        /* renamed from: a  reason: collision with root package name */
        StartAppAd f289a;
        AdPreferences.Placement b;
        AdPreferences c;
        AdEventListener d;

        C0007a(StartAppAd startAppAd, AdPreferences.Placement placement, AdPreferences adPreferences, AdEventListener adEventListener) {
            this.f289a = startAppAd;
            this.b = placement;
            this.c = adPreferences;
            this.d = adEventListener;
        }
    }

    private a() {
    }

    public static a a() {
        return c;
    }

    public c a(Context context, StartAppAd startAppAd, AdPreferences adPreferences, AdEventListener adEventListener) {
        if (!b(AdPreferences.Placement.INAPP_SPLASH)) {
            return null;
        }
        com.startapp.common.a.g.a("AdCacheManager", 3, "Loading splash");
        return a(context, startAppAd, AdPreferences.Placement.INAPP_SPLASH, adPreferences, adEventListener);
    }

    public c a(Context context, AdPreferences adPreferences) {
        if (!b(AdPreferences.Placement.INAPP_RETURN)) {
            return null;
        }
        com.startapp.common.a.g.a("AdCacheManager", 3, "Loading return ad");
        return a(context, (StartAppAd) null, AdPreferences.Placement.INAPP_RETURN, adPreferences, (AdEventListener) null);
    }

    public c a(Context context, StartAppAd startAppAd, StartAppAd.AdMode adMode, AdPreferences adPreferences, AdEventListener adEventListener) {
        if (adPreferences == null) {
            adPreferences = new AdPreferences();
        }
        AdPreferences adPreferences2 = adPreferences;
        AdPreferences.Placement b2 = b(adMode, adPreferences2);
        a(adMode, adPreferences2);
        return a(context, startAppAd, b2, adPreferences2, adEventListener, false, 0);
    }

    public void a(final Context context) {
        this.h = context.getApplicationContext();
        if (e()) {
            this.e = true;
            i.a(context, (i.c) new i.c() {
                public void a(List<i.b> list) {
                    if (list != null) {
                        try {
                            for (i.b next : list) {
                                if (a.this.b(next.placement)) {
                                    com.startapp.common.a.g.a("AdCacheManager", 4, "Loading from disk: " + next.placement);
                                    a.this.a(context, (StartAppAd) null, next.a(), next.b(), (AdEventListener) null, true, next.c());
                                }
                            }
                        } catch (Exception e) {
                            com.startapp.common.a.g.a("AdCacheManager", 6, "loadFromDisk - onCacheKeysLoaded failed", e);
                        }
                    }
                    a.this.d(context);
                }
            });
        }
    }

    public void b() {
        if (!this.e) {
            synchronized (this.f282a) {
                for (g d2 : this.f282a.values()) {
                    d2.d();
                }
            }
        }
    }

    public void a(Context context, boolean z) {
        e(context);
        a(z);
    }

    public void b(Context context) {
        this.b = true;
        i.a(context, (i.e) new i.e() {
            public void a() {
                a.this.b = false;
            }
        });
    }

    public void c(final Context context) {
        AnonymousClass3 r0 = new d() {
            public void a() {
                MetaData.getInstance().removeMetaDataListener(this);
                Set<StartAppAd.AdMode> autoLoad = d.a().b().getAutoLoad();
                if (autoLoad != null) {
                    for (StartAppAd.AdMode next : a.this.a(autoLoad)) {
                        com.startapp.common.a.g.a("preCache", 3, "preCacheAds load " + next.name());
                        int b2 = b.a().b();
                        if (next == StartAppAd.AdMode.FULLPAGE) {
                            if (b2 > 0) {
                                a.this.a(context, (StartAppAd) null, StartAppAd.AdMode.FULLPAGE, new AdPreferences(), (AdEventListener) null);
                            }
                        } else if (next != StartAppAd.AdMode.OFFERWALL) {
                            a.this.a(context, (StartAppAd) null, next, new AdPreferences(), (AdEventListener) null);
                        } else if (b2 < 100) {
                            a.this.a(context, (StartAppAd) null, StartAppAd.AdMode.OFFERWALL, new AdPreferences(), (AdEventListener) null);
                        }
                        String a2 = a.this.a(next);
                        if (a2 != null) {
                            k.b(context, a2, Integer.valueOf(k.a(context, a2, (Integer) 0).intValue() + 1));
                        }
                    }
                }
            }

            public void b() {
                com.startapp.common.a.g.a("AdCacheManager", 3, "Failed loading metadata, unable to pre-cache.");
                MetaData.getInstance().removeMetaDataListener(this);
            }
        };
        synchronized (MetaData.getLock()) {
            if (MetaData.getInstance().isReady()) {
                r0.a();
            } else {
                MetaData.getInstance().addMetaDataListener(r0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Set<StartAppAd.AdMode> a(Set<StartAppAd.AdMode> set) {
        Iterator<StartAppAd.AdMode> it = set.iterator();
        while (it.hasNext()) {
            StartAppAd.AdMode next = it.next();
            boolean z = false;
            if (k.a(this.h, a(next), (Integer) 0).intValue() >= MetaData.getInstance().getStopAutoLoadPreCacheAmount()) {
                z = true;
            }
            if (z) {
                com.startapp.common.a.g.a("preCache", 3, "preCacheAds.remove " + next.name());
                it.remove();
            }
        }
        if (!com.startapp.android.publish.adsCommon.Utils.i.a(128) && !com.startapp.android.publish.adsCommon.Utils.i.a(64)) {
            set.remove(StartAppAd.AdMode.OFFERWALL);
        }
        if (!com.startapp.android.publish.adsCommon.Utils.i.a(2) && !com.startapp.android.publish.adsCommon.Utils.i.a(4)) {
            set.remove(StartAppAd.AdMode.FULLPAGE);
        }
        if (!com.startapp.android.publish.adsCommon.Utils.i.a(4)) {
            set.remove(StartAppAd.AdMode.REWARDED_VIDEO);
            set.remove(StartAppAd.AdMode.VIDEO);
        }
        return set;
    }

    public int c() {
        return this.f282a.size();
    }

    public com.startapp.android.publish.adsCommon.g a(c cVar) {
        if (cVar == null) {
            com.startapp.common.a.g.a("AdCacheManager", 3, "Cache key is null");
            return null;
        }
        com.startapp.common.a.g.a("AdCacheManager", 3, "Retrieving ad with " + cVar);
        g gVar = this.f282a.get(cVar);
        if (gVar != null) {
            return gVar.i();
        }
        return null;
    }

    public com.startapp.android.publish.adsCommon.g b(c cVar) {
        g gVar = cVar != null ? this.f282a.get(cVar) : null;
        if (gVar != null) {
            return gVar.b();
        }
        return null;
    }

    public synchronized List<g> d() {
        return new ArrayList(this.f282a.values());
    }

    public String a(String str) {
        return a(str, UUID.randomUUID().toString());
    }

    public String a(String str, String str2) {
        this.d.put(str2, str);
        return str2;
    }

    public String b(String str) {
        return this.d.get(str);
    }

    public String c(String str) {
        com.startapp.common.a.g.a("AdCacheManager", 3, "cache size: " + this.d.size() + " - removing " + str);
        return this.d.remove(str);
    }

    private boolean e() {
        return !this.b && d.a().b().isLocalCache();
    }

    /* access modifiers changed from: protected */
    public void d(Context context) {
        this.e = false;
        for (C0007a aVar : this.f) {
            if (b(aVar.b)) {
                com.startapp.common.a.g.a("AdCacheManager", 4, "Loading pending request for: " + aVar.b);
                a(context, aVar.f289a, aVar.b, aVar.c, aVar.d);
            }
        }
        this.f.clear();
    }

    private void e(final Context context) {
        com.startapp.common.a.g.a("AdCacheManager", 3, "Saving to disk: eneter save to disk ");
        if (e()) {
            com.startapp.common.a.g.a("AdCacheManager", 3, "Saving to disk: cache to disk is enebaled ");
            com.startapp.common.g.a(g.a.DEFAULT, (Runnable) new Runnable() {
                public void run() {
                    try {
                        e.a(context, i.b());
                        e.a(context, i.c());
                        c cVar = null;
                        for (Map.Entry next : a.this.f282a.entrySet()) {
                            c cVar2 = (c) next.getKey();
                            if (cVar2.a() == AdPreferences.Placement.INAPP_SPLASH) {
                                cVar = cVar2;
                            } else {
                                g gVar = (g) next.getValue();
                                com.startapp.common.a.g.a("AdCacheManager", 3, "Saving to disk: " + cVar2.toString());
                                i.a(context, cVar2.a(), gVar.a(), a.this.c(cVar2), gVar.l());
                                i.a(context, gVar, a.this.c(cVar2));
                            }
                        }
                        if (cVar != null) {
                            a.this.f282a.remove(cVar);
                        }
                    } catch (Exception e) {
                        com.startapp.common.a.g.a("AdCacheManager", 3, "Saving to disk: exception caught");
                        f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "AdCacheManager.saveToDisk - Unexpected Thread Exception", e.getMessage(), "");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public String c(c cVar) {
        return String.valueOf(cVar.hashCode()).replace('-', '_');
    }

    private void a(boolean z) {
        for (g next : this.f282a.values()) {
            if (next.b() == null || !com.startapp.android.publish.adsCommon.Utils.i.a(2) || !(next.b() instanceof com.startapp.android.publish.ads.b.e) || z) {
                next.f();
            } else if (!d.a().b().shouldReturnAdLoadInBg()) {
                next.f();
            }
            next.e();
        }
    }

    public c a(Context context, StartAppAd startAppAd, AdPreferences.Placement placement, AdPreferences adPreferences, AdEventListener adEventListener) {
        return a(context, startAppAd, placement, adPreferences, adEventListener, false, 0);
    }

    /* access modifiers changed from: protected */
    public c a(Context context, StartAppAd startAppAd, AdPreferences.Placement placement, AdPreferences adPreferences, AdEventListener adEventListener, boolean z, int i) {
        g gVar;
        this.h = context.getApplicationContext();
        if (adPreferences == null) {
            adPreferences = new AdPreferences();
        }
        AdPreferences adPreferences2 = adPreferences;
        c cVar = new c(placement, adPreferences2);
        if (!this.e || z) {
            AdPreferences adPreferences3 = new AdPreferences(adPreferences2);
            synchronized (this.f282a) {
                gVar = this.f282a.get(cVar);
                if (gVar == null) {
                    com.startapp.common.a.g.a("AdCacheManager", 3, "CachedAd for " + placement + " not found. Adding new CachedAd with " + cVar);
                    if (AnonymousClass6.f288a[placement.ordinal()] != 1) {
                        gVar = new g(context, placement, adPreferences3);
                    } else {
                        gVar = new g(context, placement, adPreferences3, false);
                    }
                    gVar.a(f());
                    if (z) {
                        gVar.a(c(cVar));
                        gVar.a(true);
                        gVar.a(i);
                    }
                    a(cVar, gVar, context);
                } else {
                    com.startapp.common.a.g.a("AdCacheManager", 3, "CachedAd for " + placement + " already exists.");
                    gVar.a(adPreferences3);
                }
            }
            gVar.a(startAppAd, adEventListener);
            return cVar;
        }
        com.startapp.common.a.g.a("AdCacheManager", 4, "Adding to pending queue: " + placement);
        this.f.add(new C0007a(startAppAd, placement, adPreferences2, adEventListener));
        return cVar;
    }

    public void a(AdPreferences.Placement placement) {
        synchronized (this.f282a) {
            Iterator<Map.Entry<c, g>> it = this.f282a.entrySet().iterator();
            while (it.hasNext()) {
                if (((c) it.next().getKey()).a() == placement) {
                    it.remove();
                }
            }
        }
    }

    private void a(c cVar, g gVar, Context context) {
        synchronized (this.f282a) {
            int maxCacheSize = d.a().b().getMaxCacheSize();
            if (maxCacheSize != 0 && c() >= maxCacheSize) {
                long j = Long.MAX_VALUE;
                c cVar2 = null;
                for (c next : this.f282a.keySet()) {
                    g gVar2 = this.f282a.get(next);
                    if (gVar2.c() == gVar.c() && gVar2.c < j) {
                        j = gVar2.c;
                        cVar2 = next;
                    }
                }
                if (cVar2 != null) {
                    this.f282a.remove(cVar2);
                }
            }
            this.f282a.put(cVar, gVar);
            if (Math.random() * 100.0d < ((double) d.a().c())) {
                f.a(context, new com.startapp.android.publish.adsCommon.f.e(com.startapp.android.publish.adsCommon.f.d.GENERAL, "Cache Size", String.valueOf(c())), "");
            }
        }
    }

    private void a(StartAppAd.AdMode adMode, AdPreferences adPreferences) {
        if (adMode.equals(StartAppAd.AdMode.REWARDED_VIDEO)) {
            c.a(adPreferences, "type", Ad.AdType.REWARDED_VIDEO);
        }
        if (adMode.equals(StartAppAd.AdMode.VIDEO)) {
            c.a(adPreferences, "type", Ad.AdType.VIDEO);
        }
    }

    /* renamed from: com.startapp.android.publish.cache.a$6  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass6 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f288a;
        static final /* synthetic */ int[] b;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|14|15|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0028 */
        static {
            /*
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode[] r0 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r1 = 1
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r2 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.OFFERWALL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.OVERLAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.FULLPAGE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.VIDEO     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4 = 4
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.REWARDED_VIDEO     // Catch:{ NoSuchFieldError -> 0x003e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r4 = 5
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.AUTOMATIC     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r4 = 6
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.startapp.android.publish.common.model.AdPreferences$Placement[] r2 = com.startapp.android.publish.common.model.AdPreferences.Placement.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                f288a = r2
                com.startapp.android.publish.common.model.AdPreferences$Placement r3 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_SPLASH     // Catch:{ NoSuchFieldError -> 0x005a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = f288a     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r2 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_RETURN     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.cache.a.AnonymousClass6.<clinit>():void");
        }
    }

    private AdPreferences.Placement b(StartAppAd.AdMode adMode, AdPreferences adPreferences) {
        boolean z = false;
        switch (AnonymousClass6.b[adMode.ordinal()]) {
            case 1:
                if (com.startapp.android.publish.adsCommon.Utils.i.a(128) || com.startapp.android.publish.adsCommon.Utils.i.a(64)) {
                    z = true;
                }
                return z ? AdPreferences.Placement.INAPP_OFFER_WALL : AdPreferences.Placement.INAPP_FULL_SCREEN;
            case 2:
            case 3:
            case 4:
            case 5:
                return AdPreferences.Placement.INAPP_OVERLAY;
            case 6:
                if (com.startapp.android.publish.adsCommon.Utils.i.a(128) || com.startapp.android.publish.adsCommon.Utils.i.a(64)) {
                    z = true;
                }
                boolean a2 = com.startapp.android.publish.adsCommon.Utils.i.a(4);
                boolean a3 = com.startapp.android.publish.adsCommon.Utils.i.a(2);
                if (a2 && a3 && z) {
                    if (new Random().nextInt(100) < b.a().b()) {
                        return a(adPreferences);
                    }
                    return AdPreferences.Placement.INAPP_FULL_SCREEN;
                } else if (a2 || a3) {
                    return AdPreferences.Placement.INAPP_OVERLAY;
                } else {
                    if (z) {
                        return AdPreferences.Placement.INAPP_OFFER_WALL;
                    }
                }
                break;
        }
        return AdPreferences.Placement.INAPP_FULL_SCREEN;
    }

    private AdPreferences.Placement a(AdPreferences adPreferences) {
        if ((new Random().nextInt(100) < b.a().c() || com.startapp.android.publish.adsCommon.Utils.i.a(adPreferences, "forceFullpage")) && !com.startapp.android.publish.adsCommon.Utils.i.a(adPreferences, "forceOverlay")) {
            return AdPreferences.Placement.INAPP_FULL_SCREEN;
        }
        return AdPreferences.Placement.INAPP_OVERLAY;
    }

    /* access modifiers changed from: private */
    public boolean b(AdPreferences.Placement placement) {
        int i = AnonymousClass6.f288a[placement.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return true;
            }
            if (!m.a().h() || b.a().y()) {
                return false;
            }
            return true;
        } else if (!m.a().k() || b.a().z()) {
            return false;
        } else {
            return true;
        }
    }

    private g.b f() {
        if (this.g == null) {
            this.g = new g.b() {
                public void a(g gVar) {
                    synchronized (a.this.f282a) {
                        c cVar = null;
                        for (c next : a.this.f282a.keySet()) {
                            if (a.this.f282a.get(next) == gVar) {
                                cVar = next;
                            }
                        }
                        if (cVar != null) {
                            a.this.f282a.remove(cVar);
                        }
                    }
                }
            };
        }
        return this.g;
    }

    public String a(StartAppAd.AdMode adMode) {
        if (adMode == null) {
            return null;
        }
        return "autoLoadNotShownAdPrefix" + adMode.name();
    }
}
