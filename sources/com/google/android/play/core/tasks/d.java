package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

final class d<ResultT> implements g<ResultT> {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f1137a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    public final OnFailureListener c;

    public d(Executor executor, OnFailureListener onFailureListener) {
        this.f1137a = executor;
        this.c = onFailureListener;
    }

    public final void a(Task<ResultT> task) {
        if (!task.isSuccessful()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.f1137a.execute(new c(this, task));
                }
            }
        }
    }
}
