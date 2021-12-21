package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class zzdy extends zzei {
    private List<Long> zztt = null;

    public zzdy(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 31);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdy = -1L;
        this.zztq.zzdz = -1L;
        if (this.zztt == null) {
            this.zztt = (List) this.zztz.invoke((Object) null, new Object[]{this.zzps.getContext()});
        }
        List<Long> list = this.zztt;
        if (list != null && list.size() == 2) {
            synchronized (this.zztq) {
                this.zztq.zzdy = Long.valueOf(this.zztt.get(0).longValue());
                this.zztq.zzdz = Long.valueOf(this.zztt.get(1).longValue());
            }
        }
    }
}
