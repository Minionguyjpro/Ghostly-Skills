package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class aq<T extends IInterface> {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, Handler> f1122a = new HashMap();
    /* access modifiers changed from: private */
    public final Context b;
    /* access modifiers changed from: private */
    public final ag c;
    private final String d;
    /* access modifiers changed from: private */
    public final List<ah> e = new ArrayList();
    /* access modifiers changed from: private */
    public boolean f;
    private final Intent g;
    /* access modifiers changed from: private */
    public final am<T> h;
    private final WeakReference<al> i;
    private final IBinder.DeathRecipient j = new ai(this);
    /* access modifiers changed from: private */
    public ServiceConnection k;
    /* access modifiers changed from: private */
    public T l;

    public aq(Context context, ag agVar, String str, Intent intent, am<T> amVar) {
        this.b = context;
        this.c = agVar;
        this.d = str;
        this.g = intent;
        this.h = amVar;
        this.i = new WeakReference<>((Object) null);
    }

    static /* synthetic */ void d(aq aqVar, ah ahVar) {
        if (aqVar.l == null && !aqVar.f) {
            aqVar.c.d("Initiate binding to the service.", new Object[0]);
            aqVar.e.add(ahVar);
            ap apVar = new ap(aqVar);
            aqVar.k = apVar;
            aqVar.f = true;
            if (!aqVar.b.bindService(aqVar.g, apVar, 1)) {
                aqVar.c.d("Failed to bind to the service.", new Object[0]);
                aqVar.f = false;
                for (ah b2 : aqVar.e) {
                    b2.b(new ar());
                }
                aqVar.e.clear();
            }
        } else if (aqVar.f) {
            aqVar.c.d("Waiting to bind to the service.", new Object[0]);
            aqVar.e.add(ahVar);
        } else {
            ahVar.run();
        }
    }

    static /* synthetic */ void j(aq aqVar) {
        aqVar.c.d("linkToDeath", new Object[0]);
        try {
            aqVar.l.asBinder().linkToDeath(aqVar.j, 0);
        } catch (RemoteException e2) {
            aqVar.c.c(e2, "linkToDeath failed", new Object[0]);
        }
    }

    static /* synthetic */ void m(aq aqVar) {
        aqVar.c.d("unlinkToDeath", new Object[0]);
        aqVar.l.asBinder().unlinkToDeath(aqVar.j, 0);
    }

    /* access modifiers changed from: private */
    public final void r(ah ahVar) {
        Handler handler;
        synchronized (f1122a) {
            if (!f1122a.containsKey(this.d)) {
                HandlerThread handlerThread = new HandlerThread(this.d, 10);
                handlerThread.start();
                f1122a.put(this.d, new Handler(handlerThread.getLooper()));
            }
            handler = f1122a.get(this.d);
        }
        handler.post(ahVar);
    }

    public final void a(ah ahVar) {
        r(new aj(this, ahVar.c(), ahVar));
    }

    public final void b() {
        r(new ak(this));
    }

    public final T c() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void n() {
        this.c.d("reportBinderDeath", new Object[0]);
        al alVar = (al) this.i.get();
        if (alVar == null) {
            this.c.d("%s : Binder has died.", this.d);
            for (ah b2 : this.e) {
                b2.b(Build.VERSION.SDK_INT < 15 ? new RemoteException() : new RemoteException(String.valueOf(this.d).concat(" : Binder has died.")));
            }
            this.e.clear();
            return;
        }
        this.c.d("calling onBinderDied", new Object[0]);
        alVar.a();
    }
}
