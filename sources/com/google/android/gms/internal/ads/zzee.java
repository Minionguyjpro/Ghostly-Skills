package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzee extends zzei {
    public zzee(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 48);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        zzba zzba;
        int i;
        this.zztq.zzel = 2;
        boolean booleanValue = ((Boolean) this.zztz.invoke((Object) null, new Object[]{this.zzps.getContext()})).booleanValue();
        synchronized (this.zztq) {
            if (booleanValue) {
                zzba = this.zztq;
                i = 1;
            } else {
                zzba = this.zztq;
                i = 0;
            }
            zzba.zzel = i;
        }
    }
}
