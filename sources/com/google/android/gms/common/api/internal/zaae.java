package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zaae implements Runnable {
    private final /* synthetic */ zaaf zaa;

    zaae(zaaf zaaf) {
        this.zaa = zaaf;
    }

    public final void run() {
        this.zaa.zad.cancelAvailabilityErrorNotifications(this.zaa.zac);
    }
}
