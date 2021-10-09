package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zacm implements zaco {
    private final /* synthetic */ zacn zaa;

    zacm(zacn zacn) {
        this.zaa = zacn;
    }

    public final void zaa(BasePendingResult<?> basePendingResult) {
        this.zaa.zab.remove(basePendingResult);
    }
}
