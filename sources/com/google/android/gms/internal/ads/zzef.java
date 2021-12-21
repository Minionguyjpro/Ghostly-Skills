package com.google.android.gms.internal.ads;

import android.util.DisplayMetrics;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzef extends zzei {
    private final View zztl;

    public zzef(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2, View view) {
        super(zzcz, str, str2, zzba, i, 57);
        this.zztl = view;
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zztl != null) {
            DisplayMetrics displayMetrics = this.zzps.getContext().getResources().getDisplayMetrics();
            zzdh zzdh = new zzdh((String) this.zztz.invoke((Object) null, new Object[]{this.zztl, displayMetrics}));
            zzbc zzbc = new zzbc();
            zzbc.zzgi = zzdh.zzsx;
            zzbc.zzgj = zzdh.zzgj;
            zzbc.zzgk = zzdh.zzgk;
            this.zztq.zzev = zzbc;
        }
    }
}
