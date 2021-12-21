package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsResult;

final class zzaqq implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsResult zzdbk;

    zzaqq(JsResult jsResult) {
        this.zzdbk = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdbk.cancel();
    }
}
