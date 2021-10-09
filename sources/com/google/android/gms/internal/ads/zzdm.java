package com.google.android.gms.internal.ads;

import android.provider.Settings;
import java.lang.reflect.InvocationTargetException;

public final class zzdm extends zzei {
    public zzdm(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 49);
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzem = 2;
        try {
            int i = 1;
            boolean booleanValue = ((Boolean) this.zztz.invoke((Object) null, new Object[]{this.zzps.getContext()})).booleanValue();
            zzba zzba = this.zztq;
            if (!booleanValue) {
                i = 0;
            }
            zzba.zzem = Integer.valueOf(i);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof Settings.SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
