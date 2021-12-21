package com.google.firebase.iid;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CountDownLatch;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final /* synthetic */ class FirebaseInstanceId$$Lambda$3 implements OnCompleteListener {
    private final CountDownLatch arg$1;

    FirebaseInstanceId$$Lambda$3(CountDownLatch countDownLatch) {
        this.arg$1 = countDownLatch;
    }

    public final void onComplete(Task task) {
        this.arg$1.countDown();
    }
}
