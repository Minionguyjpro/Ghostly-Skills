package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

final class zzaqt implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsPromptResult zzdbl;

    zzaqt(JsPromptResult jsPromptResult) {
        this.zzdbl = jsPromptResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdbl.cancel();
    }
}
