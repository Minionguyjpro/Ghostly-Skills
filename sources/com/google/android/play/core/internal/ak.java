package com.google.android.play.core.internal;

final class ak extends ah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aq f1118a;

    ak(aq aqVar) {
        this.f1118a = aqVar;
    }

    public final void a() {
        if (this.f1118a.l != null) {
            this.f1118a.c.d("Unbind from service.", new Object[0]);
            this.f1118a.b.unbindService(this.f1118a.k);
            this.f1118a.f = false;
            this.f1118a.l = null;
            this.f1118a.k = null;
        }
    }
}
