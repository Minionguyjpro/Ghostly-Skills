package com.startapp.android.publish.cache;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.g;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.g;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: StartAppSDK */
public class i {

    /* compiled from: StartAppSDK */
    protected interface a {
        void a(g gVar);
    }

    /* compiled from: StartAppSDK */
    protected interface c {
        void a(List<b> list);
    }

    /* compiled from: StartAppSDK */
    protected interface e {
        void a();
    }

    protected static String a() {
        return "startapp_ads";
    }

    private static boolean a(com.startapp.android.publish.ads.b.c cVar) {
        return true;
    }

    /* compiled from: StartAppSDK */
    protected static class b implements Serializable {
        private static final long serialVersionUID = 1;
        protected AdPreferences adPreferences;
        private int numberOfLoadedAd;
        protected AdPreferences.Placement placement;

        protected b(AdPreferences.Placement placement2, AdPreferences adPreferences2) {
            this.placement = placement2;
            this.adPreferences = adPreferences2;
        }

        /* access modifiers changed from: protected */
        public AdPreferences.Placement a() {
            return this.placement;
        }

        /* access modifiers changed from: protected */
        public AdPreferences b() {
            return this.adPreferences;
        }

        /* access modifiers changed from: protected */
        public int c() {
            return this.numberOfLoadedAd;
        }

        /* access modifiers changed from: protected */
        public void a(int i) {
            this.numberOfLoadedAd = i;
        }
    }

    /* compiled from: StartAppSDK */
    protected static class d implements Serializable {
        private static final long serialVersionUID = 1;
        private g ad;
        private String html;

        protected d(g gVar) {
            a(gVar);
            c();
        }

        /* access modifiers changed from: protected */
        public g a() {
            return this.ad;
        }

        /* access modifiers changed from: protected */
        public String b() {
            return this.html;
        }

        private void a(g gVar) {
            this.ad = gVar;
        }

        private void c() {
            g gVar = this.ad;
            if (gVar != null && (gVar instanceof com.startapp.android.publish.adsCommon.e)) {
                this.html = ((com.startapp.android.publish.adsCommon.e) gVar).f();
            }
        }
    }

    protected static void a(final Context context, final e eVar) {
        com.startapp.common.g.a(g.a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                try {
                    com.startapp.common.a.e.a(context, i.a());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            eVar.a();
                        }
                    });
                } catch (Exception e) {
                    f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, " DiskAdCacheManager.deleteDiskCacheAsync - Unexpected Thread Exception", e.getMessage(), "");
                }
            }
        });
    }

    protected static void a(Context context, AdPreferences.Placement placement, AdPreferences adPreferences, String str, int i) {
        b bVar = new b(placement, adPreferences);
        bVar.a(i);
        com.startapp.common.a.e.b(context, b(), str, (Serializable) bVar);
    }

    protected static void a(final Context context, final c cVar) {
        com.startapp.common.g.a(g.a.HIGH, (Runnable) new Runnable() {
            public void run() {
                try {
                    final List<b> b2 = com.startapp.common.a.e.b(context, i.b(), b.class);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            cVar.a(b2);
                        }
                    });
                } catch (Exception e) {
                    f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, " DiskAdCacheManager.loadCacheKeysAsync - Unexpected Thread Exception", e.getMessage(), "");
                }
            }
        });
    }

    protected static void a(Context context, g gVar, String str) {
        com.startapp.common.a.e.b(context, c(), str, (Serializable) new d(gVar.b()));
    }

    protected static void a(final Context context, final String str, final a aVar, final AdEventListener adEventListener) {
        com.startapp.common.g.a(g.a.HIGH, (Runnable) new Runnable() {
            public void run() {
                try {
                    final d dVar = (d) com.startapp.common.a.e.b(context, i.c(), str, d.class);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            try {
                                if (dVar == null) {
                                    com.startapp.common.a.g.a("DiskAdCacheManager", 4, "File not found or error: " + str);
                                    adEventListener.onFailedToReceiveAd((Ad) null);
                                    return;
                                }
                                if (dVar.a() != null) {
                                    if (dVar.a().isReady()) {
                                        if (dVar.a().hasAdCacheTtlPassed()) {
                                            com.startapp.common.a.g.a("DiskAdCacheManager", 3, "Disk ad TTL has passed");
                                            adEventListener.onFailedToReceiveAd((Ad) null);
                                            return;
                                        }
                                        i.a(context, dVar, aVar, adEventListener);
                                        return;
                                    }
                                }
                                com.startapp.common.a.g.a("DiskAdCacheManager", 3, "Disk ad is not ready or null");
                                adEventListener.onFailedToReceiveAd((Ad) null);
                            } catch (Exception e) {
                                f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "DiskAdCacheManager.loadCachedAdAsync - Unexpected Thread Exception", e.getMessage(), "");
                                adEventListener.onFailedToReceiveAd((Ad) null);
                            }
                        }
                    });
                } catch (Exception e) {
                    f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "DiskAdCacheManager.loadCachedAdAsync - Unexpected Thread Exception", e.getMessage(), "");
                    adEventListener.onFailedToReceiveAd((Ad) null);
                }
            }
        });
    }

    protected static String b() {
        return a().concat(File.separator).concat("keys");
    }

    protected static String c() {
        return a().concat(File.separator).concat("interstitials");
    }

    protected static void a(Context context, d dVar, a aVar, AdEventListener adEventListener) {
        com.startapp.android.publish.adsCommon.g a2 = dVar.a();
        a2.setContext(context);
        if (com.startapp.android.publish.adsCommon.Utils.i.a(2) && (a2 instanceof com.startapp.android.publish.ads.b.c)) {
            a(context, (com.startapp.android.publish.ads.b.c) a2, dVar.b(), aVar, adEventListener);
        } else if (!com.startapp.android.publish.adsCommon.Utils.i.a(64) || !(a2 instanceof com.startapp.android.publish.ads.c.b.b)) {
            com.startapp.common.a.g.a("DiskAdCacheManager", 4, "Unsupported disk ad type");
            adEventListener.onFailedToReceiveAd((Ad) null);
        } else {
            a(context, (com.startapp.android.publish.ads.c.b.b) a2, aVar, adEventListener);
        }
    }

    private static void a(Context context, com.startapp.android.publish.ads.c.b.b bVar, a aVar, AdEventListener adEventListener) {
        List<AdDetails> d2 = bVar.d();
        if (d2 == null) {
            com.startapp.common.a.g.a("DiskAdCacheManager", 4, "No ad details");
            adEventListener.onFailedToReceiveAd((Ad) null);
            return;
        }
        if (com.startapp.android.publish.adsCommon.b.a().E()) {
            d2 = com.startapp.android.publish.adsCommon.b.c.a(context, d2, 0, new HashSet());
        }
        if (d2 == null || d2.size() <= 0) {
            com.startapp.common.a.g.a("DiskAdCacheManager", 4, "App presence - no interstitials to display");
            adEventListener.onFailedToReceiveAd((Ad) null);
            return;
        }
        aVar.a(bVar);
        a(bVar, adEventListener, d2);
    }

    private static void a(Context context, com.startapp.android.publish.ads.b.c cVar, String str, a aVar, AdEventListener adEventListener) {
        if (str == null || str.equals("")) {
            com.startapp.common.a.g.a("DiskAdCacheManager", 3, "Missing Html");
            adEventListener.onFailedToReceiveAd((Ad) null);
        } else if (!a(cVar)) {
            com.startapp.common.a.g.a("DiskAdCacheManager", 3, "Missing video file");
            adEventListener.onFailedToReceiveAd((Ad) null);
        } else if (!a(context, str)) {
            com.startapp.common.a.g.a("DiskAdCacheManager", 3, "App is present");
            adEventListener.onFailedToReceiveAd((Ad) null);
        } else {
            a.a().a(str, cVar.g());
            aVar.a(cVar);
            a(context, cVar, str, adEventListener);
        }
    }

    private static void a(Context context, final com.startapp.android.publish.ads.b.c cVar, String str, final AdEventListener adEventListener) {
        com.startapp.android.publish.adsCommon.Utils.i.a(context, str, (i.a) new i.a() {
            public void a() {
                adEventListener.onReceiveAd(cVar);
            }

            public void a(String str) {
                com.startapp.common.a.g.a("DiskAdCacheManager", 3, "Html Cache failed: " + str);
                adEventListener.onFailedToReceiveAd(cVar);
            }
        });
    }

    private static void a(com.startapp.android.publish.ads.c.b.b bVar, AdEventListener adEventListener, List<AdDetails> list) {
        com.startapp.android.publish.ads.list3d.e a2 = com.startapp.android.publish.ads.list3d.f.a().a(bVar.a());
        a2.a();
        for (AdDetails a3 : list) {
            a2.a(a3);
        }
        adEventListener.onReceiveAd(bVar);
    }

    private static boolean a(Context context, String str) {
        List<com.startapp.android.publish.adsCommon.b.a> a2;
        if (!com.startapp.android.publish.adsCommon.b.a().E() || (a2 = com.startapp.android.publish.adsCommon.b.c.a(str, 0)) == null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        if (!com.startapp.android.publish.adsCommon.b.c.a(context, a2, 0, (Set<String>) new HashSet(), (List<com.startapp.android.publish.adsCommon.b.a>) arrayList).booleanValue()) {
            return true;
        }
        new com.startapp.android.publish.adsCommon.b.b(context, arrayList).a();
        return false;
    }
}
