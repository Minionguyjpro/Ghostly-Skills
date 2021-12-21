package com.startapp.android.publish.common.metaData;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.startapp.common.b.a;
import com.startapp.common.b.a.b;

/* compiled from: StartAppSDK */
public class InfoEventService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private static final String f305a = "InfoEventService";

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        a.a((Context) this);
        boolean a2 = a.a(intent, (b.C0011b) new b.C0011b() {
            public void a(b.a aVar) {
                InfoEventService.this.stopSelf();
            }
        });
        a.a(3, f305a, "onHandleIntent: RunnerManager.runJob" + a2, (Throwable) null);
        return super.onStartCommand(intent, i, i2);
    }
}
