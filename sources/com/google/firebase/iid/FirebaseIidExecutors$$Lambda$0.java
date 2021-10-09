package com.google.firebase.iid;

import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class FirebaseIidExecutors$$Lambda$0 implements Executor {
    static final Executor $instance = new FirebaseIidExecutors$$Lambda$0();

    private FirebaseIidExecutors$$Lambda$0() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
