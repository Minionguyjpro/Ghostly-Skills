package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzeb extends zzei {
    public zzeb(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 51);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zztq) {
            zzcy zzcy = new zzcy((String) this.zztz.invoke((Object) null, new Object[0]));
            this.zztq.zzen = zzcy.zzrr;
            this.zztq.zzeo = zzcy.zzrs;
        }
    }
}
