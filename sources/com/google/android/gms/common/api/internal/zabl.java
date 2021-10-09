package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class zabl extends BroadcastReceiver {
    private Context zaa;
    private final zabk zab;

    public zabl(zabk zabk) {
        this.zab = zabk;
    }

    public final void zaa(Context context) {
        this.zaa = context;
    }

    public final synchronized void zaa() {
        if (this.zaa != null) {
            this.zaa.unregisterReceiver(this);
        }
        this.zaa = null;
    }

    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if ("com.google.android.gms".equals(data != null ? data.getSchemeSpecificPart() : null)) {
            this.zab.zaa();
            zaa();
        }
    }
}
