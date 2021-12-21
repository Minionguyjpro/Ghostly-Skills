package com.google.android.play.core.assetpacks;

import java.util.concurrent.ThreadFactory;

final /* synthetic */ class k implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    static final ThreadFactory f1097a = new k();
    static final ThreadFactory b = new k((byte[]) null);
    private final /* synthetic */ int c = 0;

    private k() {
    }

    private k(byte[] bArr) {
    }

    public final Thread newThread(Runnable runnable) {
        return this.c != 0 ? new Thread(runnable, "AssetPackBackgroundExecutor") : new Thread(runnable, "UpdateListenerExecutor");
    }
}
