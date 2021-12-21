package com.appnext.base.services;

import android.content.Context;
import android.os.Bundle;
import com.appnext.base.a.b.c;
import com.appnext.base.b.e;
import com.appnext.base.b.i;
import com.appnext.base.operations.a;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public final class b {
    public final void a(Context context, String str, String str2, Bundle bundle, Object obj, a.C0038a aVar) {
        try {
            i.aR().init(context.getApplicationContext());
            e.init(context.getApplicationContext());
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context.getApplicationContext());
            if (advertisingIdInfo != null && advertisingIdInfo.isLimitAdTrackingEnabled()) {
                i.aR().putBoolean(i.fC, true);
                b(aVar);
                return;
            }
            c t = com.appnext.base.a.a.X().ab().t(str);
            if (t == null) {
                b(aVar);
            } else {
                com.appnext.base.operations.b.aI().a(t.getKey(), t, bundle, obj, aVar);
            }
        } catch (Throwable unused) {
            b(aVar);
        }
    }

    private static void b(a.C0038a aVar) {
        if (aVar != null) {
            try {
                aVar.aH();
            } catch (Throwable unused) {
            }
        }
    }
}
