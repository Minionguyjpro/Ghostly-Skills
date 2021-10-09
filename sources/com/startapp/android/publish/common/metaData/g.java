package com.startapp.android.publish.common.metaData;

import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class g implements Serializable {
    private static final long serialVersionUID = 1;
    @f(a = true)
    private a ambientTemperatureSensor = new a(14);
    private boolean enabled = false;
    @f(a = true)
    private a gravitySensor = new a(9);
    @f(a = true)
    private a gyroscopeUncalibratedSensor = new a(18);
    @f(a = true)
    private a lightSensor = new a(3);
    @f(a = true)
    private a linearAccelerationSensor = new a(9);
    @f(a = true)
    private a magneticFieldSensor = new a(3);
    @f(a = true)
    private a pressureSensor = new a(9);
    @f(a = true)
    private a relativeHumiditySensor = new a(14);
    @f(a = true)
    private a rotationVectorSensor = new a(9);
    private int timeoutInSec = 10;

    public int a() {
        return this.timeoutInSec;
    }

    public boolean b() {
        return this.enabled;
    }

    public a c() {
        return this.ambientTemperatureSensor;
    }

    public a d() {
        return this.gravitySensor;
    }

    public a e() {
        return this.lightSensor;
    }

    public a f() {
        return this.linearAccelerationSensor;
    }

    public a g() {
        return this.magneticFieldSensor;
    }

    public a h() {
        return this.pressureSensor;
    }

    public a i() {
        return this.relativeHumiditySensor;
    }

    public a j() {
        return this.rotationVectorSensor;
    }

    public a k() {
        return this.gyroscopeUncalibratedSensor;
    }
}
