package com.startapp.android.publish.cache;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.common.a.e;
import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class d implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private static transient d f290a = new d();
    private static final long serialVersionUID = 1;
    @f(a = true)
    private ACMConfig ACM = new ACMConfig();
    private String cacheMetaDataUpdateVersion = AdsConstants.h;
    private float sendCacheSizeProb = 20.0f;

    private d() {
    }

    public static d a() {
        return f290a;
    }

    public ACMConfig b() {
        return this.ACM;
    }

    public static void a(Context context, d dVar) {
        dVar.cacheMetaDataUpdateVersion = AdsConstants.h;
        f290a = dVar;
        e.a(context, "StartappCacheMetadata", (Serializable) dVar);
    }

    public static void a(Context context) {
        d dVar = (d) e.a(context, "StartappCacheMetadata", d.class);
        d dVar2 = new d();
        if (dVar != null) {
            boolean a2 = i.a(dVar, dVar2);
            if (!dVar.d() && a2) {
                com.startapp.android.publish.adsCommon.f.f.a(context, com.startapp.android.publish.adsCommon.f.d.METADATA_NULL, "CacheMetaData", "", "");
            }
            f290a = dVar;
            return;
        }
        f290a = dVar2;
    }

    private boolean d() {
        return !AdsConstants.h.equals(this.cacheMetaDataUpdateVersion);
    }

    public float c() {
        return this.sendCacheSizeProb;
    }
}
