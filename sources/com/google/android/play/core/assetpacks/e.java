package com.google.android.play.core.assetpacks;

final /* synthetic */ class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final i f1092a;
    private final /* synthetic */ int b = 1;

    e(i iVar, byte[] bArr) {
        this.f1092a = iVar;
    }

    public final void run() {
        if (this.b != 0) {
            this.f1092a.d();
        } else {
            this.f1092a.c();
        }
    }
}
