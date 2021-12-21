package com.google.android.play.core.tasks;

import java.util.concurrent.CountDownLatch;

final class n implements OnFailureListener, OnSuccessListener {

    /* renamed from: a  reason: collision with root package name */
    private final CountDownLatch f1144a = new CountDownLatch(1);

    private n() {
    }

    /* synthetic */ n(byte[] bArr) {
    }

    public final void a() throws InterruptedException {
        this.f1144a.await();
    }

    public final void onFailure(Exception exc) {
        this.f1144a.countDown();
    }

    public final void onSuccess(Object obj) {
        this.f1144a.countDown();
    }
}
