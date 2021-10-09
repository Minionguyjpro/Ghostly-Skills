package com.google.android.play.core.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

final class ap implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aq f1121a;

    /* synthetic */ ap(aq aqVar) {
        this.f1121a = aqVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f1121a.c.d("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.f1121a.r(new an(this, iBinder));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f1121a.c.d("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.f1121a.r(new ao(this));
    }
}
