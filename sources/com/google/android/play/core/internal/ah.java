package com.google.android.play.core.internal;

import com.google.android.play.core.tasks.i;

public abstract class ah implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final i<?> f1115a;

    ah() {
        this.f1115a = null;
    }

    public ah(i<?> iVar) {
        this.f1115a = iVar;
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    public final void b(Exception exc) {
        i<?> iVar = this.f1115a;
        if (iVar != null) {
            iVar.d(exc);
        }
    }

    /* access modifiers changed from: package-private */
    public final i<?> c() {
        return this.f1115a;
    }

    public final void run() {
        try {
            a();
        } catch (Exception e) {
            b(e);
        }
    }
}
