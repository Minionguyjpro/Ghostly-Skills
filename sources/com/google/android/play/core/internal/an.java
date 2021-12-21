package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

final class an extends ah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ IBinder f1119a;
    final /* synthetic */ ap b;

    an(ap apVar, IBinder iBinder) {
        this.b = apVar;
        this.f1119a = iBinder;
    }

    public final void a() {
        aq aqVar = this.b.f1121a;
        aqVar.l = (IInterface) aqVar.h.a(this.f1119a);
        aq.j(this.b.f1121a);
        this.b.f1121a.f = false;
        for (Runnable run : this.b.f1121a.e) {
            run.run();
        }
        this.b.f1121a.e.clear();
    }
}
