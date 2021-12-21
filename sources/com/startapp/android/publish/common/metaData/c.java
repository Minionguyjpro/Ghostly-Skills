package com.startapp.android.publish.common.metaData;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.ads.splash.f;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adinformation.a;
import com.startapp.android.publish.adsCommon.b;
import com.startapp.android.publish.adsCommon.f.e;
import com.startapp.android.publish.cache.d;
import com.startapp.android.publish.common.metaData.MetaDataRequest;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.g;
import java.net.UnknownHostException;
import java.util.Map;

/* compiled from: StartAppSDK */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final Context f313a;
    private final AdPreferences b;
    private MetaDataRequest.a c;
    private MetaData d = null;
    private com.startapp.android.publish.ads.banner.c e = null;
    private f f = null;
    private d g = null;
    private a h = null;
    private b i = null;
    private boolean j = false;

    public c(Context context, AdPreferences adPreferences, MetaDataRequest.a aVar) {
        this.f313a = context;
        this.b = adPreferences;
        this.c = aVar;
    }

    public void a() {
        g.a(g.a.HIGH, (Runnable) new Runnable() {
            public void run() {
                final Boolean c = c.this.c();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        c.this.a(c);
                    }
                });
            }
        });
    }

    public void b() {
        this.j = true;
    }

    /* access modifiers changed from: protected */
    public Boolean c() {
        com.startapp.common.a.g.a(3, "Loading MetaData");
        MetaDataRequest metaDataRequest = new MetaDataRequest(this.f313a, this.c);
        try {
            metaDataRequest.fillApplicationDetails(this.f313a, this.b, false);
            metaDataRequest.fillLocationDetails(this.b, this.f313a);
            com.startapp.common.a.g.a(3, "Networking MetaData");
            String a2 = com.startapp.android.publish.adsCommon.k.a.a(this.f313a, AdsConstants.a(AdsConstants.ServiceApiType.METADATA), metaDataRequest, (Map<String, String>) null).a();
            this.d = (MetaData) i.a(a2, MetaData.class);
            if (!i.a()) {
                this.i = (b) i.a(a2, b.class);
                if (i.a(16) || i.a(32)) {
                    this.e = (com.startapp.android.publish.ads.banner.c) i.a(a2, com.startapp.android.publish.ads.banner.c.class);
                }
                if (i.a(8)) {
                    this.f = (f) i.a(a2, f.class);
                }
                if (i.a(512)) {
                    this.g = (d) i.a(a2, d.class);
                }
                if (i.e()) {
                    this.h = (a) i.a(a2, a.class);
                }
            }
            d();
            return Boolean.TRUE;
        } catch (Exception e2) {
            com.startapp.common.a.g.a(6, "Unable to handle GetMetaData command!!!!", (Throwable) e2);
            if (!(e2 instanceof UnknownHostException) || !e2.getMessage().contains("init.startappservice.com")) {
                com.startapp.android.publish.adsCommon.f.f.a(this.f313a, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "GetMetaDataAsync.doInBackground - MetaData request failed", e2.getMessage(), "");
            }
            return Boolean.FALSE;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(4:9|(10:11|12|17|(2:21|22)|27|(2:29|30)|35|(2:37|38)|43|(2:45|46))|51|52)|53|54) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00a2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
            r3 = this;
            java.lang.Object r0 = com.startapp.android.publish.common.metaData.MetaData.getLock()
            monitor-enter(r0)
            boolean r1 = r3.j     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x00a2
            com.startapp.android.publish.common.metaData.MetaData r1 = r3.d     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x00a2
            android.content.Context r1 = r3.f313a     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x00a2
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.i.a()     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x0097
            android.content.Context r1 = r3.f313a     // Catch:{ Exception -> 0x001f }
            com.startapp.android.publish.adsCommon.b r2 = r3.i     // Catch:{ Exception -> 0x001f }
            com.startapp.android.publish.adsCommon.b.a(r1, r2)     // Catch:{ Exception -> 0x001f }
            goto L_0x0029
        L_0x001f:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-adscommon update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x0029:
            r1 = 16
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.i.a((long) r1)     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x0039
            r1 = 32
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.i.a((long) r1)     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x004b
        L_0x0039:
            android.content.Context r1 = r3.f313a     // Catch:{ Exception -> 0x0041 }
            com.startapp.android.publish.ads.banner.c r2 = r3.e     // Catch:{ Exception -> 0x0041 }
            com.startapp.android.publish.ads.banner.c.a(r1, r2)     // Catch:{ Exception -> 0x0041 }
            goto L_0x004b
        L_0x0041:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-banner update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x004b:
            r1 = 8
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.i.a((long) r1)     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x0065
            android.content.Context r1 = r3.f313a     // Catch:{ Exception -> 0x005b }
            com.startapp.android.publish.ads.splash.f r2 = r3.f     // Catch:{ Exception -> 0x005b }
            com.startapp.android.publish.ads.splash.f.a(r1, r2)     // Catch:{ Exception -> 0x005b }
            goto L_0x0065
        L_0x005b:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-splash update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x0065:
            r1 = 512(0x200, double:2.53E-321)
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.i.a((long) r1)     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x007f
            android.content.Context r1 = r3.f313a     // Catch:{ Exception -> 0x0075 }
            com.startapp.android.publish.cache.d r2 = r3.g     // Catch:{ Exception -> 0x0075 }
            com.startapp.android.publish.cache.d.a(r1, r2)     // Catch:{ Exception -> 0x0075 }
            goto L_0x007f
        L_0x0075:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-cache update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x007f:
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.i.e()     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x0097
            android.content.Context r1 = r3.f313a     // Catch:{ Exception -> 0x008d }
            com.startapp.android.publish.adsCommon.adinformation.a r2 = r3.h     // Catch:{ Exception -> 0x008d }
            com.startapp.android.publish.adsCommon.adinformation.a.a(r1, r2)     // Catch:{ Exception -> 0x008d }
            goto L_0x0097
        L_0x008d:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-adInfo update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x0097:
            android.content.Context r1 = r3.f313a     // Catch:{ Exception -> 0x00a2 }
            com.startapp.android.publish.common.metaData.MetaData r2 = r3.d     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r2 = r2.getAssetsBaseUrl()     // Catch:{ Exception -> 0x00a2 }
            com.startapp.android.publish.common.metaData.MetaData.preCacheResources(r1, r2)     // Catch:{ Exception -> 0x00a2 }
        L_0x00a2:
            monitor-exit(r0)     // Catch:{ all -> 0x00a4 }
            return
        L_0x00a4:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00a4 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.common.metaData.c.d():void");
    }

    /* access modifiers changed from: protected */
    public void a(Boolean bool) {
        synchronized (MetaData.getLock()) {
            if (!this.j) {
                if (!bool.booleanValue() || this.d == null || this.f313a == null) {
                    MetaData.failedLoading();
                } else {
                    try {
                        MetaData.update(this.f313a, this.d);
                    } catch (Exception e2) {
                        a("GetMetaDataAsyncTask.onPostExecute-metadata update fail", e2.getMessage());
                    }
                }
            }
        }
    }

    private void a(String str, String str2) {
        com.startapp.android.publish.adsCommon.f.f.a(this.f313a, new e(com.startapp.android.publish.adsCommon.f.d.EXCEPTION, str, str2), "");
    }
}
