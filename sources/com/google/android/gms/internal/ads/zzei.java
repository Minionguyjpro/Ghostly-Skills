package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzei implements Callable {
    private final String TAG = getClass().getSimpleName();
    private final String className;
    protected final zzcz zzps;
    protected final zzba zztq;
    private final String zztx;
    protected Method zztz;
    private final int zzud;
    private final int zzue;

    public zzei(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        this.zzps = zzcz;
        this.className = str;
        this.zztx = str2;
        this.zztq = zzba;
        this.zzud = i;
        this.zzue = i2;
    }

    /* access modifiers changed from: protected */
    public abstract void zzar() throws IllegalAccessException, InvocationTargetException;

    /* renamed from: zzat */
    public Void call() throws Exception {
        try {
            long nanoTime = System.nanoTime();
            Method zza = this.zzps.zza(this.className, this.zztx);
            this.zztz = zza;
            if (zza == null) {
                return null;
            }
            zzar();
            zzcc zzag = this.zzps.zzag();
            if (!(zzag == null || this.zzud == Integer.MIN_VALUE)) {
                zzag.zza(this.zzue, this.zzud, (System.nanoTime() - nanoTime) / 1000);
            }
            return null;
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }
}
