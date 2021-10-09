package com.google.firebase.messaging;

import com.google.android.gms.tasks.OnSuccessListener;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
final /* synthetic */ class FirebaseMessaging$$Lambda$0 implements OnSuccessListener {
    private final FirebaseMessaging arg$1;

    FirebaseMessaging$$Lambda$0(FirebaseMessaging firebaseMessaging) {
        this.arg$1 = firebaseMessaging;
    }

    public final void onSuccess(Object obj) {
        this.arg$1.lambda$new$0$FirebaseMessaging((TopicsSubscriber) obj);
    }
}
