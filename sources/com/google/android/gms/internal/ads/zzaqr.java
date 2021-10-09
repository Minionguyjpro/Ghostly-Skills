package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsResult;

final class zzaqr implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsResult zzdbk;

    zzaqr(JsResult jsResult) {
        this.zzdbk = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdbk.confirm();
    }
}
