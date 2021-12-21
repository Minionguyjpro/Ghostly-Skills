package com.yandex.metrica;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.ae;
import com.yandex.metrica.impl.af;
import com.yandex.metrica.impl.ag;
import com.yandex.metrica.impl.utils.j;

public class MetricaService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private b f684a = new b() {
        public void a(int i) {
            MetricaService.this.stopSelfResult(i);
        }
    };
    /* access modifiers changed from: private */
    public ae b;
    private final IMetricaService.Stub c = new IMetricaService.Stub() {
        public void reportEvent(String str, int i, String str2, Bundle bundle) throws RemoteException {
            MetricaService.this.b.a(getCallingUid(), str, i, str2, bundle);
        }

        public void reportData(Bundle bundle) throws RemoteException {
            MetricaService.this.b.a(getCallingUid(), bundle);
        }
    };

    public interface b {
        void a(int i);
    }

    static class a extends Binder {
        a() {
        }
    }

    public void onCreate() {
        super.onCreate();
        j.a(getApplicationContext());
        af afVar = new af(new ag(getApplicationContext(), this.f684a));
        this.b = afVar;
        afVar.a();
    }

    public void onStart(Intent intent, int i) {
        this.b.a(intent, i);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.b.a(intent, i, i2);
        return 2;
    }

    public IBinder onBind(Intent intent) {
        if ("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER".equals(intent.getAction())) {
            return new a();
        }
        this.b.a(intent);
        return this.c;
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        this.b.b(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.b();
    }

    public boolean onUnbind(Intent intent) {
        if ("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER".equals(intent.getAction())) {
            return false;
        }
        if (intent == null || intent.getData() == null) {
            return false;
        }
        this.b.c(intent);
        return true;
    }
}
