package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdx extends zzei {
    public zzdx(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 3);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zztq) {
            zzcm zzcm = new zzcm((String) this.zztz.invoke((Object) null, new Object[]{this.zzps.getContext()}));
            synchronized (this.zztq) {
                this.zztq.zzdd = Long.valueOf(zzcm.zzri);
                this.zztq.zzey = Long.valueOf(zzcm.zzrj);
            }
        }
    }
}
