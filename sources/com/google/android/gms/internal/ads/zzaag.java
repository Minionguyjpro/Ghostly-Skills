package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

final class zzaag implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzaae zzbwq;

    zzaag(zzaae zzaae) {
        this.zzbwq = zzaae;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzbwq.zzbw("User canceled the download.");
    }
}
