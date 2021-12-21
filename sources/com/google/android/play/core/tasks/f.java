package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

final class f<ResultT> implements g<ResultT> {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f1139a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    public final OnSuccessListener<? super ResultT> c;

    public f(Executor executor, OnSuccessListener<? super ResultT> onSuccessListener) {
        this.f1139a = executor;
        this.c = onSuccessListener;
    }

    public final void a(Task<ResultT> task) {
        if (task.isSuccessful()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.f1139a.execute(new e(this, task));
                }
            }
        }
    }
}
