package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zabd implements Runnable {
    private final /* synthetic */ GoogleApiManager.zaa zaa;

    zabd(GoogleApiManager.zaa zaa2) {
        this.zaa = zaa2;
    }

    public final void run() {
        this.zaa.zam();
    }
}
