package com.google.android.gms.common.api.internal;

import android.app.Dialog;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zam extends zabk {
    private final /* synthetic */ Dialog zaa;
    private final /* synthetic */ zan zab;

    zam(zan zan, Dialog dialog) {
        this.zab = zan;
        this.zaa = dialog;
    }

    public final void zaa() {
        this.zab.zaa.zab();
        if (this.zaa.isShowing()) {
            this.zaa.dismiss();
        }
    }
}
