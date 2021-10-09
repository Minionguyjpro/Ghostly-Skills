package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

final /* synthetic */ class zzalg implements DialogInterface.OnClickListener {
    private final zzald zzcsh;
    private final String zzzo;

    zzalg(zzald zzald, String str) {
        this.zzcsh = zzald;
        this.zzzo = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzcsh.zza(this.zzzo, dialogInterface, i);
    }
}
