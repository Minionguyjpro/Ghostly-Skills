package com.appnext.base.b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class g {
    private static g fs;
    private static Context mContext;
    private Handler ft;
    private Handler fu;
    private HandlerThread fv;

    public g() {
        try {
            this.ft = new Handler(Looper.getMainLooper());
            HandlerThread handlerThread = new HandlerThread("ExecutesManagerWorkerThread");
            this.fv = handlerThread;
            handlerThread.start();
            this.fu = new Handler(this.fv.getLooper());
        } catch (Throwable unused) {
        }
    }

    public static g aN() {
        if (fs == null) {
            synchronized (g.class) {
                if (fs == null) {
                    fs = new g();
                }
            }
        }
        return fs;
    }

    public final void a(Runnable runnable) {
        if (runnable != null) {
            try {
                this.ft.post(runnable);
            } catch (Throwable unused) {
            }
        }
    }

    public final void a(Runnable runnable, long j) {
        if (runnable != null) {
            try {
                this.ft.postDelayed(runnable, j);
            } catch (Throwable unused) {
            }
        }
    }

    public final void b(Runnable runnable) {
        try {
            this.fu.post(runnable);
        } catch (Throwable unused) {
        }
    }

    public final void b(Runnable runnable, long j) {
        if (runnable != null) {
            try {
                this.fu.postDelayed(runnable, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            this.fu.removeCallbacks((Runnable) null);
            this.fv.quit();
            super.finalize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
