package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.i;

public class k<T extends i> {

    /* renamed from: a  reason: collision with root package name */
    private final j<T> f924a;
    private final h<T> b;

    /* synthetic */ k(a aVar, byte b2) {
        this(aVar);
    }

    private k(a aVar) {
        this.f924a = aVar.b;
        this.b = aVar.f925a;
    }

    /* access modifiers changed from: package-private */
    public final void a(i iVar) {
        this.f924a.a(iVar);
    }

    /* access modifiers changed from: package-private */
    public final boolean b(i iVar) {
        h<T> hVar = this.b;
        if (hVar == null) {
            return false;
        }
        return hVar.a(iVar);
    }

    public static <T extends i> a<T> a(j<T> jVar) {
        return new a<>(jVar);
    }

    public static final class a<T extends i> {

        /* renamed from: a  reason: collision with root package name */
        public h<T> f925a;
        /* access modifiers changed from: private */
        public j<T> b;

        a(j<T> jVar) {
            this.b = jVar;
        }

        public a<T> a(h<T> hVar) {
            this.f925a = hVar;
            return this;
        }

        public k<T> a() {
            return new k<>(this, (byte) 0);
        }
    }
}
