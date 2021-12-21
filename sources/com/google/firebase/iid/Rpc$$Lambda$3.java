package com.google.firebase.iid;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledFuture;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class Rpc$$Lambda$3 implements OnCompleteListener {
    private final Rpc arg$1;
    private final String arg$2;
    private final ScheduledFuture arg$3;

    Rpc$$Lambda$3(Rpc rpc, String str, ScheduledFuture scheduledFuture) {
        this.arg$1 = rpc;
        this.arg$2 = str;
        this.arg$3 = scheduledFuture;
    }

    public final void onComplete(Task task) {
        this.arg$1.lambda$registerRpcInternal$4$Rpc(this.arg$2, this.arg$3, task);
    }
}
