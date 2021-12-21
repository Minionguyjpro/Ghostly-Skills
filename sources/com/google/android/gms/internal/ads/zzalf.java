package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

final /* synthetic */ class zzalf implements DialogInterface.OnClickListener {
    private final zzald zzcsh;
    private final int zzcsi;
    private final int zzcsj;
    private final int zzcsk;

    zzalf(zzald zzald, int i, int i2, int i3) {
        this.zzcsh = zzald;
        this.zzcsi = i;
        this.zzcsj = i2;
        this.zzcsk = i3;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzcsh.zza(this.zzcsi, this.zzcsj, this.zzcsk, dialogInterface, i);
    }
}
