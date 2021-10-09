package com.google.ads.mediation;

import com.google.android.gms.ads.reward.zza;

final class zzb extends zza {
    private final /* synthetic */ AbstractAdViewAdapter zzhd;

    zzb(AbstractAdViewAdapter abstractAdViewAdapter) {
        this.zzhd = abstractAdViewAdapter;
    }

    public final void zzt() {
        if (this.zzhd.zzha != null && this.zzhd.zzhb != null) {
            this.zzhd.zzhb.zzc(this.zzhd.zzha.zzba());
        }
    }
}
