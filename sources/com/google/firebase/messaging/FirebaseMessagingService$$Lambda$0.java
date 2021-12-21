package com.google.firebase.messaging;

import com.google.android.datatransport.Transformer;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
final /* synthetic */ class FirebaseMessagingService$$Lambda$0 implements Transformer {
    static final Transformer $instance = new FirebaseMessagingService$$Lambda$0();

    private FirebaseMessagingService$$Lambda$0() {
    }

    public final Object apply(Object obj) {
        return ((String) obj).getBytes();
    }
}
