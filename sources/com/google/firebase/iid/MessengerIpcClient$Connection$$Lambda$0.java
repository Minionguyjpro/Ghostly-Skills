package com.google.firebase.iid;

import android.os.Handler;
import android.os.Message;
import com.google.firebase.iid.MessengerIpcClient;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class MessengerIpcClient$Connection$$Lambda$0 implements Handler.Callback {
    private final MessengerIpcClient.Connection arg$1;

    MessengerIpcClient$Connection$$Lambda$0(MessengerIpcClient.Connection connection) {
        this.arg$1 = connection;
    }

    public final boolean handleMessage(Message message) {
        return this.arg$1.receivedResponse(message);
    }
}
