package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.ag;

public final class p {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f1134a = new ag("SplitInstallInfoProvider");
    private final Context b;
    private final String c;

    public p(Context context) {
        this.b = context;
        this.c = context.getPackageName();
    }
}
