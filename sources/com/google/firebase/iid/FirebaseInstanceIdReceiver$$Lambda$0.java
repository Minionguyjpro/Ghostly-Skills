package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class FirebaseInstanceIdReceiver$$Lambda$0 implements OnCompleteListener {
    private final boolean arg$1;
    private final BroadcastReceiver.PendingResult arg$2;

    FirebaseInstanceIdReceiver$$Lambda$0(boolean z, BroadcastReceiver.PendingResult pendingResult) {
        this.arg$1 = z;
        this.arg$2 = pendingResult;
    }

    public final void onComplete(Task task) {
        FirebaseInstanceIdReceiver.lambda$onReceiveInternal$0$FirebaseInstanceIdReceiver(this.arg$1, this.arg$2, task);
    }
}
