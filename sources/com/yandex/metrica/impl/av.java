package com.yandex.metrica.impl;

import com.yandex.metrica.IReporter;
import com.yandex.metrica.impl.i;

public class av extends i {

    /* renamed from: a  reason: collision with root package name */
    private final IReporter f732a;

    av(IReporter iReporter, i.a aVar) {
        super(aVar);
        this.f732a = iReporter;
    }

    /* access modifiers changed from: package-private */
    public void b(Throwable th) {
        this.f732a.reportUnhandledException(th);
    }
}
