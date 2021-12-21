package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdz extends zzei {
    private final boolean zztu;

    public zzdz(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 61);
        this.zztu = zzcz.zzai();
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zztz.invoke((Object) null, new Object[]{this.zzps.getContext(), Boolean.valueOf(this.zztu)})).longValue();
        synchronized (this.zztq) {
            this.zztq.zzez = Long.valueOf(longValue);
        }
    }
}
