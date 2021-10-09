package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zaw implements OnCompleteListener<TResult> {
    private final /* synthetic */ TaskCompletionSource zaa;
    private final /* synthetic */ zav zab;

    zaw(zav zav, TaskCompletionSource taskCompletionSource) {
        this.zab = zav;
        this.zaa = taskCompletionSource;
    }

    public final void onComplete(Task<TResult> task) {
        this.zab.zab.remove(this.zaa);
    }
}
