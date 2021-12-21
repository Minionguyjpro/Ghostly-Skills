package com.appnext.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

public abstract class o {
    private ServiceConnection mConnection = new ServiceConnection() {
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Messenger unused = o.this.mMessenger = new Messenger(iBinder);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            Messenger unused = o.this.mMessenger = null;
        }
    };
    /* access modifiers changed from: private */
    public Messenger mMessenger;

    /* access modifiers changed from: protected */
    public abstract void a(Intent intent);

    public final void t(Context context) {
        Intent intent = new Intent(context, AdsService.class);
        a(intent);
        try {
            context.getApplicationContext().bindService(intent, this.mConnection, 1);
            Message obtain = Message.obtain((Handler) null, AdsService.ADD_PACK, 0, 0);
            obtain.setData(intent.getExtras());
            this.mMessenger.send(obtain);
        } catch (Throwable unused) {
            context.startService(intent);
        }
    }

    protected static Class<?> bm() {
        return AdsService.class;
    }

    public void a(Context context) {
        try {
            context.unbindService(this.mConnection);
            this.mMessenger = null;
            this.mConnection = null;
        } catch (Throwable unused) {
        }
    }
}
