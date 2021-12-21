package com.startapp.android.publish.adsCommon.h;

import android.content.Context;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.common.metaData.MetaData;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class b extends a {
    public b(Context context, Runnable runnable, com.startapp.android.publish.adsCommon.f.b bVar) {
        super(context, runnable, bVar);
    }

    /* access modifiers changed from: protected */
    public void b() {
        try {
            long millis = TimeUnit.SECONDS.toMillis((long) MetaData.getInstance().getBluetoothConfig().a());
            final com.startapp.android.publish.adsCommon.c.b bVar = new com.startapp.android.publish.adsCommon.c.b(this.f253a, this);
            a(new Runnable() {
                public void run() {
                    bVar.a();
                    b.this.a(bVar.b());
                }
            }, millis);
            bVar.a(c());
        } catch (Exception unused) {
            a((Object) null);
        }
    }

    private boolean c() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - k.a(this.f253a, "lastBtDiscoveringTime", (Long) 0L).longValue() >= ((long) MetaData.getInstance().getBluetoothConfig().c()) * 60000;
        if (z) {
            k.b(this.f253a, "lastBtDiscoveringTime", Long.valueOf(currentTimeMillis));
        }
        return z;
    }

    public void a(Object obj) {
        if (obj != null) {
            this.c.b(obj.toString());
        }
        super.a(obj);
    }
}
