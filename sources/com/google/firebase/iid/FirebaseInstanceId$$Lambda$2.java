package com.google.firebase.iid;

import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class FirebaseInstanceId$$Lambda$2 implements Executor {
    static final Executor $instance = new FirebaseInstanceId$$Lambda$2();

    private FirebaseInstanceId$$Lambda$2() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
