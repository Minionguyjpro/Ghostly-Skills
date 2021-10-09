package com.yandex.metrica.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class af implements ae {

    /* renamed from: a  reason: collision with root package name */
    private Executor f706a;
    /* access modifiers changed from: private */
    public ae b;

    abstract class a implements Runnable {
        public abstract void a() throws Exception;

        a() {
        }

        public void run() {
            try {
                a();
            } catch (Exception unused) {
            }
        }
    }

    public af(ae aeVar) {
        this(Executors.newSingleThreadExecutor(), aeVar);
    }

    public void a() {
        this.f706a.execute(new a() {
            public void a() {
                af.this.b.a();
            }
        });
    }

    public void a(final Intent intent, final int i) {
        this.f706a.execute(new a() {
            public void a() {
                af.this.b.a(intent, i);
            }
        });
    }

    public void a(final Intent intent, final int i, final int i2) {
        this.f706a.execute(new a() {
            public void a() {
                af.this.b.a(intent, i, i2);
            }
        });
    }

    public void a(final Intent intent) {
        this.f706a.execute(new a() {
            public void a() {
                af.this.b.a(intent);
            }
        });
    }

    public void b(final Intent intent) {
        this.f706a.execute(new a() {
            public void a() {
                af.this.b.b(intent);
            }
        });
    }

    public void c(final Intent intent) {
        this.f706a.execute(new a() {
            public void a() {
                af.this.b.c(intent);
            }
        });
    }

    public void b() {
        this.b.b();
    }

    public void a(int i, String str, int i2, String str2, Bundle bundle) throws RemoteException {
        final int i3 = i;
        final String str3 = str;
        final int i4 = i2;
        final String str4 = str2;
        final Bundle bundle2 = bundle;
        this.f706a.execute(new a() {
            public void a() throws RemoteException {
                af.this.b.a(i3, str3, i4, str4, bundle2);
            }
        });
    }

    public void a(final int i, final Bundle bundle) throws RemoteException {
        this.f706a.execute(new a() {
            public void a() throws Exception {
                af.this.b.a(i, bundle);
            }
        });
    }

    af(Executor executor, ae aeVar) {
        this.f706a = executor;
        this.b = aeVar;
    }
}
