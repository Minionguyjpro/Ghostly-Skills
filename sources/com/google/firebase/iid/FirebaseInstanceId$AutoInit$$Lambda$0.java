package com.google.firebase.iid;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.iid.FirebaseInstanceId;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class FirebaseInstanceId$AutoInit$$Lambda$0 implements EventHandler {
    private final FirebaseInstanceId.AutoInit arg$1;

    FirebaseInstanceId$AutoInit$$Lambda$0(FirebaseInstanceId.AutoInit autoInit) {
        this.arg$1 = autoInit;
    }

    public final void handle(Event event) {
        this.arg$1.lambda$initialize$0$FirebaseInstanceId$AutoInit(event);
    }
}
