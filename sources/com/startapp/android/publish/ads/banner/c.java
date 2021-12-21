package com.startapp.android.publish.ads.banner;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.common.a.e;
import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class c implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private static transient Object f54a = new Object();
    private static transient c b = new c();
    private static final long serialVersionUID = 1;
    @f(a = true)
    private BannerOptions BannerOptions = new BannerOptions();
    private String bannerMetadataUpdateVersion = AdsConstants.h;

    public static c a() {
        return b;
    }

    public BannerOptions b() {
        return this.BannerOptions;
    }

    public BannerOptions c() {
        return new BannerOptions(this.BannerOptions);
    }

    public static void a(Context context, c cVar) {
        synchronized (f54a) {
            cVar.bannerMetadataUpdateVersion = AdsConstants.h;
            b = cVar;
            e.a(context, "StartappBannerMetadata", (Serializable) cVar);
        }
    }

    public static void a(Context context) {
        c cVar = (c) e.a(context, "StartappBannerMetadata", c.class);
        c cVar2 = new c();
        if (cVar != null) {
            boolean a2 = i.a(cVar, cVar2);
            if (!cVar.d() && a2) {
                com.startapp.android.publish.adsCommon.f.f.a(context, d.METADATA_NULL, "BannerMetaData", "", "");
            }
            b = cVar;
            return;
        }
        b = cVar2;
    }

    private boolean d() {
        return !AdsConstants.h.equals(this.bannerMetadataUpdateVersion);
    }
}
