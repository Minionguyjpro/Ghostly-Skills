package com.google.android.play.core.assetpacks;

final /* synthetic */ class ao implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final ar f1030a;
    private final AssetPackState b;

    ao(ar arVar, AssetPackState assetPackState) {
        this.f1030a = arVar;
        this.b = assetPackState;
    }

    public final void run() {
        this.f1030a.i(this.b);
    }
}
