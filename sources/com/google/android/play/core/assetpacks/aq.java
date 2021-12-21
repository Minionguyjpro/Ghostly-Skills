package com.google.android.play.core.assetpacks;

import android.os.Bundle;

final /* synthetic */ class aq implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final ar f1032a;
    private final Bundle b;

    aq(ar arVar, Bundle bundle) {
        this.f1032a = arVar;
        this.b = bundle;
    }

    public final void run() {
        this.f1032a.c(this.b);
    }
}
