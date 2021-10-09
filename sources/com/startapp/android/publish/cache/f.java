package com.startapp.android.publish.cache;

import com.startapp.android.publish.adsCommon.g;
import com.startapp.android.publish.adsCommon.m;

/* compiled from: StartAppSDK */
public class f extends e {
    /* access modifiers changed from: protected */
    public String e() {
        return "CacheTTLReloadTimer";
    }

    public f(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return m.a().a(this.f291a.c());
    }

    /* access modifiers changed from: protected */
    public long d() {
        g b = this.f291a.b();
        if (b == null) {
            com.startapp.common.a.g.a("CacheTTLReloadTimer", 3, "Missing ad");
            return -1;
        }
        Long adCacheTtl = b.getAdCacheTtl();
        Long lastLoadTime = b.getLastLoadTime();
        if (adCacheTtl == null || lastLoadTime == null) {
            com.startapp.common.a.g.a("CacheTTLReloadTimer", 3, "Missing TTL or last loading time");
            return -1;
        }
        long longValue = adCacheTtl.longValue() - (System.currentTimeMillis() - lastLoadTime.longValue());
        if (longValue >= 0) {
            return longValue;
        }
        return 0;
    }
}
