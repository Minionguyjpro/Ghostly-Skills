package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzeg {
    private static final String TAG = zzeg.class.getSimpleName();
    private final String className;
    private final zzcz zzps;
    private final String zztx;
    private final int zzty = 2;
    private volatile Method zztz = null;
    private final Class<?>[] zzua;
    private CountDownLatch zzub = new CountDownLatch(1);

    public zzeg(zzcz zzcz, String str, String str2, Class<?>... clsArr) {
        this.zzps = zzcz;
        this.className = str;
        this.zztx = str2;
        this.zzua = clsArr;
        zzcz.zzab().submit(new zzeh(this));
    }

    /* access modifiers changed from: private */
    public final void zzav() {
        try {
            Class loadClass = this.zzps.zzac().loadClass(zzb(this.zzps.zzae(), this.className));
            if (loadClass != null) {
                this.zztz = loadClass.getMethod(zzb(this.zzps.zzae(), this.zztx), this.zzua);
                Method method = this.zztz;
            }
        } catch (zzcl | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException unused) {
        } catch (Throwable th) {
            this.zzub.countDown();
            throw th;
        }
        this.zzub.countDown();
    }

    private final String zzb(byte[] bArr, String str) throws zzcl, UnsupportedEncodingException {
        return new String(this.zzps.zzad().zza(bArr, str), "UTF-8");
    }

    public final Method zzaw() {
        if (this.zztz != null) {
            return this.zztz;
        }
        try {
            if (!this.zzub.await(2, TimeUnit.SECONDS)) {
                return null;
            }
            return this.zztz;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
