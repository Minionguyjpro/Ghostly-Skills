package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

final class zzaqs implements DialogInterface.OnCancelListener {
    private final /* synthetic */ JsPromptResult zzdbl;

    zzaqs(JsPromptResult jsPromptResult) {
        this.zzdbl = jsPromptResult;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.zzdbl.cancel();
    }
}
