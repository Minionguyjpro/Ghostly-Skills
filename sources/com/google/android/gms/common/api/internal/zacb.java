package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zacb implements Runnable {
    private final /* synthetic */ zacc zaa;

    zacb(zacc zacc) {
        this.zaa = zacc;
    }

    public final void run() {
        this.zaa.zah.zaa(new ConnectionResult(4));
    }
}
