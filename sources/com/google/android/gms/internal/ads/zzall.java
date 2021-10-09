package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.mopub.mobileads.resource.DrawableConstants;

final class zzall implements Runnable {
    final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzcsq;
    private final /* synthetic */ boolean zzcsr;
    private final /* synthetic */ boolean zzcss;

    zzall(zzalk zzalk, Context context, String str, boolean z, boolean z2) {
        this.val$context = context;
        this.zzcsq = str;
        this.zzcsr = z;
        this.zzcss = z2;
    }

    public final void run() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.val$context);
        builder.setMessage(this.zzcsq);
        builder.setTitle(this.zzcsr ? "Error" : "Info");
        if (this.zzcss) {
            builder.setNeutralButton("Dismiss", (DialogInterface.OnClickListener) null);
        } else {
            builder.setPositiveButton(DrawableConstants.CtaButton.DEFAULT_CTA_TEXT, new zzalm(this));
            builder.setNegativeButton("Dismiss", (DialogInterface.OnClickListener) null);
        }
        builder.create().show();
    }
}
