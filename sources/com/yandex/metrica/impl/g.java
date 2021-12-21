package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.bz;
import com.yandex.metrica.impl.ob.dt;
import com.yandex.metrica.impl.utils.i;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private final bz f765a;
    private final Executor b;
    /* access modifiers changed from: private */
    public final c c;
    /* access modifiers changed from: private */
    public volatile Map<String, Long> d = null;

    public g(bz bzVar, c cVar, Executor executor) {
        this.f765a = bzVar;
        this.b = executor;
        this.c = cVar;
        b();
        this.b.execute(new Runnable() {
            public void run() {
                HashMap<String, String> a2 = g.this.c.a();
                HashMap hashMap = new HashMap();
                if (!bk.a((Map) a2)) {
                    for (Map.Entry next : a2.entrySet()) {
                        hashMap.put(next.getKey(), Long.valueOf(i.a((String) next.getValue(), 0)));
                    }
                }
                Map unused = g.this.d = hashMap;
            }
        });
    }

    private void b() {
        String l = this.f765a.l((String) null);
        dt dtVar = new dt();
        HashMap<String, String> a2 = com.yandex.metrica.impl.utils.g.a(l);
        if (!bk.a((Map) a2)) {
            for (Map.Entry next : a2.entrySet()) {
                dtVar.a((String) next.getKey(), i.a((String) next.getValue(), Integer.MAX_VALUE));
            }
        }
        this.f765a.b((String) null);
        this.f765a.a((String) null);
        this.f765a.n((String) null);
    }

    public void a() {
        b();
    }
}
