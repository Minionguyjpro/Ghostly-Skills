package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzfr extends BroadcastReceiver {
    private final /* synthetic */ zzfp zzagk;

    zzfr(zzfp zzfp) {
        this.zzagk = zzfp;
    }

    public final void onReceive(Context context, Intent intent) {
        this.zzagk.zzm(3);
    }
}
