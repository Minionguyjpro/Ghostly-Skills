package com.google.firebase.iid;

import com.google.firebase.iid.MessengerIpcClient;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class MessengerIpcClient$Connection$$Lambda$5 implements Runnable {
    private final MessengerIpcClient.Connection arg$1;
    private final MessengerIpcClient.Request arg$2;

    MessengerIpcClient$Connection$$Lambda$5(MessengerIpcClient.Connection connection, MessengerIpcClient.Request request) {
        this.arg$1 = connection;
        this.arg$2 = request;
    }

    public final void run() {
        this.arg$1.lambda$scheduleSendingRequests$1$MessengerIpcClient$Connection(this.arg$2);
    }
}
