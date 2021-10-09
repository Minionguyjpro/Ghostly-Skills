package com.google.android.play.core.internal;

public final class cb<T> implements ce<T> {

    /* renamed from: a  reason: collision with root package name */
    private ce<T> f1129a;

    public static <T> void b(ce<T> ceVar, ce<T> ceVar2) {
        bh.j(ceVar2);
        cb cbVar = (cb) ceVar;
        if (cbVar.f1129a == null) {
            cbVar.f1129a = ceVar2;
            return;
        }
        throw new IllegalStateException();
    }

    public final T a() {
        ce<T> ceVar = this.f1129a;
        if (ceVar != null) {
            return ceVar.a();
        }
        throw new IllegalStateException();
    }
}
