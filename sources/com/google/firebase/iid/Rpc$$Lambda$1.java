package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class Rpc$$Lambda$1 implements Continuation {
    private final Rpc arg$1;
    private final Bundle arg$2;

    Rpc$$Lambda$1(Rpc rpc, Bundle bundle) {
        this.arg$1 = rpc;
        this.arg$2 = bundle;
    }

    public final Object then(Task task) {
        return this.arg$1.lambda$registerRpcViaIntent$2$Rpc(this.arg$2, task);
    }
}
