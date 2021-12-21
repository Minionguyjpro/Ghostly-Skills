package com.startapp.a.a.e;

import com.startapp.a.a.a.c;
import java.io.DataInput;

/* compiled from: StartAppSDK */
public class a extends d {

    /* renamed from: a  reason: collision with root package name */
    private final int f13a;
    private final int b;

    public a(int i, int i2) {
        this.f13a = i;
        this.b = i2;
    }

    /* access modifiers changed from: protected */
    public c a(DataInput dataInput) {
        c cVar = new c((long) (this.f13a * this.b));
        a(dataInput, cVar, (long) cVar.b());
        return cVar;
    }
}
