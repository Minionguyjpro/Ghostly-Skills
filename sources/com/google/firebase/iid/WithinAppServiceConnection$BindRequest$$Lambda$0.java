package com.google.firebase.iid;

import com.google.firebase.iid.WithinAppServiceConnection;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class WithinAppServiceConnection$BindRequest$$Lambda$0 implements Runnable {
    private final WithinAppServiceConnection.BindRequest arg$1;

    WithinAppServiceConnection$BindRequest$$Lambda$0(WithinAppServiceConnection.BindRequest bindRequest) {
        this.arg$1 = bindRequest;
    }

    public final void run() {
        this.arg$1.lambda$arrangeTimeout$0$WithinAppServiceConnection$BindRequest();
    }
}
