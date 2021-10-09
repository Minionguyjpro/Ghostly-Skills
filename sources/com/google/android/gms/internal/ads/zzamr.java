package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzamr extends BroadcastReceiver {
    private final /* synthetic */ zzamq zzcuf;

    zzamr(zzamq zzamq) {
        this.zzcuf = zzamq;
    }

    public final void onReceive(Context context, Intent intent) {
        this.zzcuf.zzc(context, intent);
    }
}
