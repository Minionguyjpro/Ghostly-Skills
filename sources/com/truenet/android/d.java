package com.truenet.android;

import a.a.b.a.b;
import a.a.b.b.h;
import java.lang.Thread;

/* compiled from: StartAppSDK */
final class d implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ b f679a;

    d(b bVar) {
        this.f679a = bVar;
    }

    public final /* synthetic */ void uncaughtException(Thread thread, Throwable th) {
        h.a(this.f679a.a(thread, th), "invoke(...)");
    }
}
