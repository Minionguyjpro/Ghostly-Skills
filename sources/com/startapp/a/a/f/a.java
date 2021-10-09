package com.startapp.a.a.f;

import com.startapp.a.a.d.e;
import com.startapp.a.a.e.b;
import com.startapp.a.a.g.c;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final b f17a;
    private final c b;

    public a(b bVar, c cVar) {
        this.b = cVar;
        this.f17a = bVar;
    }

    public String a(com.startapp.a.a.g.a aVar, com.startapp.a.a.a.c cVar, long j) {
        try {
            String a2 = this.f17a.a(cVar);
            e b2 = this.b.b(aVar);
            return j + "-" + aVar.a() + "-" + b2.a(a2);
        } catch (Throwable unused) {
            return null;
        }
    }
}
