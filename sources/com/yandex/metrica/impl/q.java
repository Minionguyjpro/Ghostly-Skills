package com.yandex.metrica.impl;

import android.os.SystemClock;

class q {

    /* renamed from: a  reason: collision with root package name */
    private long f948a = (SystemClock.elapsedRealtime() - 2000000);
    private boolean b = true;

    q() {
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        boolean z = this.b;
        this.b = false;
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f948a;
        if (!z || elapsedRealtime <= 1000) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.b = true;
        this.f948a = SystemClock.elapsedRealtime();
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.b;
    }
}
