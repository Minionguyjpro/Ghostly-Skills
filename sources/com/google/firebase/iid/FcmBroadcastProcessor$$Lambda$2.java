package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class FcmBroadcastProcessor$$Lambda$2 implements Continuation {
    static final Continuation $instance = new FcmBroadcastProcessor$$Lambda$2();

    private FcmBroadcastProcessor$$Lambda$2() {
    }

    public final Object then(Task task) {
        return FcmBroadcastProcessor.lambda$bindToMessagingService$3$FcmBroadcastProcessor(task);
    }
}
