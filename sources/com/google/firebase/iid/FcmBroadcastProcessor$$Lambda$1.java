package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class FcmBroadcastProcessor$$Lambda$1 implements Continuation {
    private final Context arg$1;
    private final Intent arg$2;

    FcmBroadcastProcessor$$Lambda$1(Context context, Intent intent) {
        this.arg$1 = context;
        this.arg$2 = intent;
    }

    public final Object then(Task task) {
        return FcmBroadcastProcessor.lambda$startMessagingService$2$FcmBroadcastProcessor(this.arg$1, this.arg$2, task);
    }
}
