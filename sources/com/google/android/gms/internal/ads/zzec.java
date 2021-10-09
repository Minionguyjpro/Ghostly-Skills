package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzec extends zzei {
    private final zzdi zzqx;
    private long zzti;

    public zzec(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2, zzdi zzdi) {
        super(zzcz, str, str2, zzba, i, 53);
        this.zzqx = zzdi;
        if (zzdi != null) {
            this.zzti = zzdi.zzap();
        }
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zzqx != null) {
            this.zztq.zzep = (Long) this.zztz.invoke((Object) null, new Object[]{Long.valueOf(this.zzti)});
        }
    }
}
