package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzbv;

final class zzalm implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzall zzcst;

    zzalm(zzall zzall) {
        this.zzcst = zzall;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        zzbv.zzek();
        zzakk.zza(this.zzcst.val$context, Uri.parse("https://support.google.com/dfp_premium/answer/7160685#push"));
    }
}
