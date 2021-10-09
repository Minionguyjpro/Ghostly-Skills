package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdr extends zzei {
    private long startTime;

    public zzdr(zzcz zzcz, String str, String str2, zzba zzba, long j, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 25);
        this.startTime = j;
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zztz.invoke((Object) null, new Object[0])).longValue();
        synchronized (this.zztq) {
            this.zztq.zzfm = Long.valueOf(longValue);
            if (this.startTime != 0) {
                this.zztq.zzdr = Long.valueOf(longValue - this.startTime);
                this.zztq.zzdw = Long.valueOf(this.startTime);
            }
        }
    }
}
