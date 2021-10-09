package com.startapp.android.publish.adsCommon.h;

import android.content.Context;
import com.startapp.android.publish.adsCommon.f.b;
import com.startapp.android.publish.common.metaData.MetaData;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class c extends a {
    public c(Context context, Runnable runnable, b bVar) {
        super(context, runnable, bVar);
    }

    /* access modifiers changed from: protected */
    public void b() {
        try {
            long millis = TimeUnit.SECONDS.toMillis((long) MetaData.getInstance().getSensorsConfig().a());
            final com.startapp.android.publish.adsCommon.j.b bVar = new com.startapp.android.publish.adsCommon.j.b(this.f253a, this);
            a(new Runnable() {
                public void run() {
                    bVar.b();
                    c.this.a(bVar.c());
                }
            }, millis);
            bVar.a();
        } catch (Exception unused) {
            a((Object) null);
        }
    }

    public void a(Object obj) {
        if (obj != null) {
            this.c.a(obj.toString());
        }
        super.a(obj);
    }
}
