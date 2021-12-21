package com.google.firebase.iid;

import android.os.IBinder;
import com.google.firebase.iid.MessengerIpcClient;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class MessengerIpcClient$Connection$$Lambda$2 implements Runnable {
    private final MessengerIpcClient.Connection arg$1;
    private final IBinder arg$2;

    MessengerIpcClient$Connection$$Lambda$2(MessengerIpcClient.Connection connection, IBinder iBinder) {
        this.arg$1 = connection;
        this.arg$2 = iBinder;
    }

    public final void run() {
        this.arg$1.lambda$onServiceConnected$0$MessengerIpcClient$Connection(this.arg$2);
    }
}
