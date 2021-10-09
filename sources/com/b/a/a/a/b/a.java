package com.b.a.a.a.b;

import com.b.a.a.a.e.e;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final i f979a;

    private a(i iVar) {
        this.f979a = iVar;
    }

    public static a a(b bVar) {
        i iVar = (i) bVar;
        e.a((Object) bVar, "AdSession is null");
        e.d(iVar);
        e.b(iVar);
        a aVar = new a(iVar);
        iVar.f().a(aVar);
        return aVar;
    }

    public void a() {
        e.b(this.f979a);
        e.f(this.f979a);
        if (!this.f979a.i()) {
            try {
                this.f979a.a();
            } catch (Exception unused) {
            }
        }
        if (this.f979a.i()) {
            this.f979a.e();
        }
    }
}
