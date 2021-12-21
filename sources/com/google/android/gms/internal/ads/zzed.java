package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzed extends zzei {
    private static final Object zztn = new Object();
    private static volatile Long zztw;

    public zzed(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 33);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (zztw == null) {
            synchronized (zztn) {
                if (zztw == null) {
                    zztw = (Long) this.zztz.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zztq) {
            this.zztq.zzea = zztw;
        }
    }
}
