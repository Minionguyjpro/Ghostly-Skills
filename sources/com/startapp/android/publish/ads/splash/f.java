package com.startapp.android.publish.ads.splash;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.common.a.e;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class f implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private static transient f f93a = new f();
    private static Object b = new Object();
    private static final long serialVersionUID = 1;
    @com.startapp.common.c.f(a = true)
    private SplashConfig SplashConfig = new SplashConfig();
    private String splashMetadataUpdateVersion = AdsConstants.h;

    private f() {
    }

    public SplashConfig a() {
        return this.SplashConfig;
    }

    public static f b() {
        return f93a;
    }

    public static void a(Context context, f fVar) {
        synchronized (b) {
            fVar.splashMetadataUpdateVersion = AdsConstants.h;
            f93a = fVar;
            e.a(context, "StartappSplashMetadata", (Serializable) fVar);
        }
    }

    public static void a(Context context) {
        f fVar = (f) e.a(context, "StartappSplashMetadata", f.class);
        f fVar2 = new f();
        if (fVar != null) {
            boolean a2 = i.a(fVar, fVar2);
            if (!fVar.c() && a2) {
                com.startapp.android.publish.adsCommon.f.f.a(context, d.METADATA_NULL, "SplashMetaData", "", "");
            }
            f93a = fVar;
            return;
        }
        f93a = fVar2;
    }

    private boolean c() {
        return !AdsConstants.h.equals(this.splashMetadataUpdateVersion);
    }
}
