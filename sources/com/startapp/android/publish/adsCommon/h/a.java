package com.startapp.android.publish.adsCommon.h;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.adsCommon.f.b;
import com.startapp.common.d;
import com.startapp.common.g;

/* compiled from: StartAppSDK */
public abstract class a implements d {

    /* renamed from: a  reason: collision with root package name */
    protected final Context f253a;
    protected final Runnable b;
    protected b c;
    private Handler d = new Handler(Looper.getMainLooper());

    /* access modifiers changed from: protected */
    public abstract void b();

    public a(Context context, Runnable runnable, b bVar) {
        this.f253a = context;
        this.b = runnable;
        this.c = bVar;
    }

    public void a() {
        g.a(g.a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                a.this.b();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(Runnable runnable, long j) {
        this.d.postDelayed(runnable, j);
    }

    public void a(Object obj) {
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        this.b.run();
    }
}
