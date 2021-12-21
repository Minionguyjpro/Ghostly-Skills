package com.startapp.android.publish.adsCommon.j;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.g;
import com.startapp.common.d;
import java.util.HashMap;
import org.json.JSONArray;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    protected a f264a = new a();
    protected SensorManager b;
    protected d c;
    private HashMap<Integer, a> d = null;
    /* access modifiers changed from: private */
    public int e;
    private SensorEventListener f = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            if (b.this.f264a.a(sensorEvent) == b.this.e) {
                b.this.b();
                if (b.this.c != null) {
                    b.this.c.a(b.this.c());
                }
            }
        }
    };

    public b(Context context, d dVar) {
        this.b = (SensorManager) context.getSystemService("sensor");
        this.c = dVar;
        this.e = 0;
        d();
    }

    public void a() {
        Sensor defaultSensor;
        for (Integer intValue : this.d.keySet()) {
            int intValue2 = intValue.intValue();
            a aVar = this.d.get(Integer.valueOf(intValue2));
            if (Build.VERSION.SDK_INT >= aVar.a() && (defaultSensor = this.b.getDefaultSensor(intValue2)) != null) {
                this.b.registerListener(this.f, defaultSensor, aVar.b());
                this.e++;
            }
        }
    }

    public void b() {
        this.b.unregisterListener(this.f);
    }

    public JSONArray c() {
        try {
            return this.f264a.a();
        } catch (Exception unused) {
            return null;
        }
    }

    private void d() {
        this.d = new HashMap<>();
        g sensorsConfig = MetaData.getInstance().getSensorsConfig();
        a(13, sensorsConfig.c());
        a(9, sensorsConfig.d());
        a(5, sensorsConfig.e());
        a(10, sensorsConfig.f());
        a(2, sensorsConfig.g());
        a(6, sensorsConfig.h());
        a(12, sensorsConfig.i());
        a(11, sensorsConfig.j());
        a(16, sensorsConfig.k());
    }

    private void a(int i, com.startapp.android.publish.common.metaData.a aVar) {
        if (aVar.c()) {
            this.d.put(Integer.valueOf(i), new a(aVar.b(), aVar.a()));
        }
    }

    /* compiled from: StartAppSDK */
    private class a {
        private int b;
        private int c;

        public a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }
    }
}
