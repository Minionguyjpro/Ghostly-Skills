package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdw extends zzei {
    private static volatile String zzdc;
    private static final Object zztn = new Object();

    public zzdw(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 1);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdc = "E";
        if (zzdc == null) {
            synchronized (zztn) {
                if (zzdc == null) {
                    zzdc = (String) this.zztz.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zztq) {
            this.zztq.zzdc = zzdc;
        }
    }
}
