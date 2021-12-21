package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.WindowManager;

@zzadh
final class zzapr implements SensorEventListener {
    private final SensorManager zzcyn;
    private final Object zzcyo = new Object();
    private final Display zzcyp;
    private final float[] zzcyq = new float[9];
    private final float[] zzcyr = new float[9];
    private float[] zzcys;
    private Handler zzcyt;
    private zzapt zzcyu;

    zzapr(Context context) {
        this.zzcyn = (SensorManager) context.getSystemService("sensor");
        this.zzcyp = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    private final void zzg(int i, int i2) {
        float[] fArr = this.zzcyr;
        float f = fArr[i];
        fArr[i] = fArr[i2];
        fArr[i2] = f;
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr[0] != 0.0f || fArr[1] != 0.0f || fArr[2] != 0.0f) {
            synchronized (this.zzcyo) {
                if (this.zzcys == null) {
                    this.zzcys = new float[9];
                }
            }
            SensorManager.getRotationMatrixFromVector(this.zzcyq, fArr);
            int rotation = this.zzcyp.getRotation();
            if (rotation == 1) {
                SensorManager.remapCoordinateSystem(this.zzcyq, 2, 129, this.zzcyr);
            } else if (rotation == 2) {
                SensorManager.remapCoordinateSystem(this.zzcyq, 129, 130, this.zzcyr);
            } else if (rotation != 3) {
                System.arraycopy(this.zzcyq, 0, this.zzcyr, 0, 9);
            } else {
                SensorManager.remapCoordinateSystem(this.zzcyq, 130, 1, this.zzcyr);
            }
            zzg(1, 3);
            zzg(2, 6);
            zzg(5, 7);
            synchronized (this.zzcyo) {
                System.arraycopy(this.zzcyr, 0, this.zzcys, 0, 9);
            }
            zzapt zzapt = this.zzcyu;
            if (zzapt != null) {
                zzapt.zznn();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void start() {
        if (this.zzcyt == null) {
            Sensor defaultSensor = this.zzcyn.getDefaultSensor(11);
            if (defaultSensor == null) {
                zzakb.e("No Sensor of TYPE_ROTATION_VECTOR");
                return;
            }
            HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper());
            this.zzcyt = handler;
            if (!this.zzcyn.registerListener(this, defaultSensor, 0, handler)) {
                zzakb.e("SensorManager.registerListener failed.");
                stop();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void stop() {
        if (this.zzcyt != null) {
            this.zzcyn.unregisterListener(this);
            this.zzcyt.post(new zzaps(this));
            this.zzcyt = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzapt zzapt) {
        this.zzcyu = zzapt;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(float[] fArr) {
        synchronized (this.zzcyo) {
            if (this.zzcys == null) {
                return false;
            }
            System.arraycopy(this.zzcys, 0, fArr, 0, this.zzcys.length);
            return true;
        }
    }
}
