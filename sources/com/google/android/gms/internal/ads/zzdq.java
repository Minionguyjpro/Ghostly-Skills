package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdq extends zzei {
    private static volatile Long zzej;
    private static final Object zztn = new Object();

    public zzdq(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 44);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (zzej == null) {
            synchronized (zztn) {
                if (zzej == null) {
                    zzej = (Long) this.zztz.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zztq) {
            this.zztq.zzej = zzej;
        }
    }
}
