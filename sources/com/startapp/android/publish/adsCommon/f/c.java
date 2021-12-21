package com.startapp.android.publish.adsCommon.f;

import android.content.Context;
import com.startapp.android.publish.adsCommon.h.a;
import com.startapp.android.publish.adsCommon.h.b;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.a.g;
import com.startapp.common.d;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
public class c {
    static AtomicBoolean f = new AtomicBoolean(false);

    /* renamed from: a  reason: collision with root package name */
    Context f242a;
    d b;
    ArrayList<a> c;
    int d;
    b e;
    private Runnable g;

    public c(Context context, boolean z) {
        this(context, z, (d) null);
    }

    public c(Context context, boolean z, d dVar) {
        this.g = new Runnable() {
            public void run() {
                synchronized (this) {
                    c cVar = c.this;
                    int i = cVar.d - 1;
                    cVar.d = i;
                    if (i == 0) {
                        g.a("DataEventTask", 3, "sending DataEvent");
                        f.a(c.this.f242a, c.this.e, "");
                        c.f.set(false);
                        c.this.b();
                    }
                }
            }
        };
        this.f242a = context;
        this.b = dVar;
        this.c = new ArrayList<>();
        b bVar = new b(d.PERIODIC);
        this.e = bVar;
        bVar.a(z);
        if (MetaData.getInstance().getSensorsConfig().b()) {
            this.c.add(new com.startapp.android.publish.adsCommon.h.c(context, this.g, this.e));
        }
        if (MetaData.getInstance().getBluetoothConfig().b()) {
            this.c.add(new b(context, this.g, this.e));
        }
        this.d = this.c.size();
    }

    public void a() {
        if (this.d > 0) {
            if (f.compareAndSet(false, true)) {
                for (int i = 0; i < this.d; i++) {
                    this.c.get(i).a();
                }
                return;
            }
        }
        b();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        d dVar = this.b;
        if (dVar != null) {
            dVar.a((Object) null);
        }
    }

    public b c() {
        return this.e;
    }
}
