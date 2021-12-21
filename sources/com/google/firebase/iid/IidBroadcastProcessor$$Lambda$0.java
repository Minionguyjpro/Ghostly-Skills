package com.google.firebase.iid;

import android.content.Intent;
import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class IidBroadcastProcessor$$Lambda$0 implements Callable {
    private final Intent arg$1;

    IidBroadcastProcessor$$Lambda$0(Intent intent) {
        this.arg$1 = intent;
    }

    public final Object call() {
        return Integer.valueOf(IidBroadcastProcessor.processImpl(this.arg$1));
    }
}
