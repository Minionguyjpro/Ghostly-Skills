package com.google.android.gms.internal.ads;

final class zzhf implements zzgj {
    private final /* synthetic */ zzhd zzajt;

    zzhf(zzhd zzhd) {
        this.zzajt = zzhd;
    }

    public final void zzh(boolean z) {
        if (z) {
            this.zzajt.connect();
        } else {
            this.zzajt.disconnect();
        }
    }
}
