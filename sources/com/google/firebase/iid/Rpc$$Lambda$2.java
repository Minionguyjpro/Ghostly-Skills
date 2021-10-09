package com.google.firebase.iid;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class Rpc$$Lambda$2 implements Runnable {
    private final TaskCompletionSource arg$1;

    Rpc$$Lambda$2(TaskCompletionSource taskCompletionSource) {
        this.arg$1 = taskCompletionSource;
    }

    public final void run() {
        Rpc.lambda$registerRpcInternal$3$Rpc(this.arg$1);
    }
}
