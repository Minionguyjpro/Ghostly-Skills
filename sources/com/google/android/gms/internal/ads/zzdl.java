package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzdl extends zzei {
    private final Activity zztk;
    private final View zztl;

    public zzdl(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2, View view, Activity activity) {
        super(zzcz, str, str2, zzba, i, 62);
        this.zztl = view;
        this.zztk = activity;
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zztl != null) {
            boolean booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzbas)).booleanValue();
            Object[] objArr = (Object[]) this.zztz.invoke((Object) null, new Object[]{this.zztl, this.zztk, Boolean.valueOf(booleanValue)});
            synchronized (this.zztq) {
                this.zztq.zzfa = Long.valueOf(((Long) objArr[0]).longValue());
                this.zztq.zzfb = Long.valueOf(((Long) objArr[1]).longValue());
                if (booleanValue) {
                    this.zztq.zzfc = (String) objArr[2];
                }
            }
        }
    }
}
