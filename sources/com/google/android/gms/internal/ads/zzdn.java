package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdn extends zzei {
    private static volatile String zztm;
    private static final Object zztn = new Object();

    public zzdn(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 29);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdx = "E";
        if (zztm == null) {
            synchronized (zztn) {
                if (zztm == null) {
                    zztm = (String) this.zztz.invoke((Object) null, new Object[]{this.zzps.getContext()});
                }
            }
        }
        synchronized (this.zztq) {
            this.zztq.zzdx = zzbi.zza(zztm.getBytes(), true);
        }
    }
}
