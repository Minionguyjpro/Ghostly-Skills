package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdu extends zzei {
    private static final Object zztn = new Object();
    private static volatile Long zztr;

    public zzdu(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 22);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (zztr == null) {
            synchronized (zztn) {
                if (zztr == null) {
                    zztr = (Long) this.zztz.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zztq) {
            this.zztq.zzdv = zztr;
        }
    }
}
