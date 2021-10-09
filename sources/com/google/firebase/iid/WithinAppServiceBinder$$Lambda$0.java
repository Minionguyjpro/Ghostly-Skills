package com.google.firebase.iid;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.WithinAppServiceConnection;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class WithinAppServiceBinder$$Lambda$0 implements OnCompleteListener {
    private final WithinAppServiceConnection.BindRequest arg$1;

    WithinAppServiceBinder$$Lambda$0(WithinAppServiceConnection.BindRequest bindRequest) {
        this.arg$1 = bindRequest;
    }

    public final void onComplete(Task task) {
        this.arg$1.finish();
    }
}
