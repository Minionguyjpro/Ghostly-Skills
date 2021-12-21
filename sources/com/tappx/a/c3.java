package com.tappx.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class c3 {

    /* renamed from: a  reason: collision with root package name */
    private final BroadcastReceiver f391a;
    private final Context b;
    private int c = -1;
    private c d;
    private boolean e = false;

    class a extends BroadcastReceiver {
        a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if ("android.intent.action.USER_PRESENT".equals(action)) {
                    c3.this.a(true);
                } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    c3.this.a(false);
                }
            }
        }
    }

    public interface c {
        void onDeviceScreenStateChanged(boolean z);
    }

    public c3(Context context) {
        this.b = context.getApplicationContext();
        this.f391a = new a();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.b.registerReceiver(this.f391a, intentFilter);
    }

    private void c() {
        c cVar = this.d;
        if (cVar != null) {
            cVar.onDeviceScreenStateChanged(b());
        }
    }

    public boolean b() {
        return this.c != 0;
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z != this.c) {
            this.c = z ? 1 : 0;
            c();
        }
    }

    public void a(c cVar) {
        this.d = cVar;
    }

    public void a() {
        if (!this.e) {
            this.e = true;
            a((c) null);
            this.b.unregisterReceiver(this.f391a);
        }
    }

    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private static volatile b f393a;

        public static b a() {
            b bVar = f393a;
            if (bVar == null) {
                synchronized (b.class) {
                    bVar = f393a;
                    if (bVar == null) {
                        bVar = new b();
                    }
                }
            }
            return bVar;
        }

        public c3 a(Context context) {
            return new c3(context);
        }
    }
}
