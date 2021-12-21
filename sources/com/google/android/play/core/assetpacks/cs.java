package com.google.android.play.core.assetpacks;

final /* synthetic */ class cs implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final au f1079a;

    private cs(au auVar) {
        this.f1079a = auVar;
    }

    static Runnable a(au auVar) {
        return new cs(auVar);
    }

    public final void run() {
        this.f1079a.s();
    }
}
