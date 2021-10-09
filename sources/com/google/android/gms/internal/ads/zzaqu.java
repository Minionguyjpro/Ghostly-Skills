package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.widget.EditText;

final class zzaqu implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsPromptResult zzdbl;
    private final /* synthetic */ EditText zzdbm;

    zzaqu(JsPromptResult jsPromptResult, EditText editText) {
        this.zzdbl = jsPromptResult;
        this.zzdbm = editText;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdbl.confirm(this.zzdbm.getText().toString());
    }
}
