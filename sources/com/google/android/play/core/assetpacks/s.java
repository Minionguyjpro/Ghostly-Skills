package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.bh;
import com.google.android.play.core.internal.ce;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class s implements ce<Executor> {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ int f1106a = 0;

    public s() {
    }

    public s(byte[] bArr) {
    }

    public s(char[] cArr) {
    }

    public s(short[] sArr) {
    }

    public static Executor b() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(k.f1097a);
        bh.k(newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    public static Executor c() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(k.b);
        bh.k(newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    public static be d() {
        return new be();
    }

    public static bo e() {
        return new bo();
    }

    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.f1106a;
        return i != 0 ? i != 1 ? i != 2 ? e() : d() : c() : b();
    }
}
