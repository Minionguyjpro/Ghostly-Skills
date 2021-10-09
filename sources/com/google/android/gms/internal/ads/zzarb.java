package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzn;

final class zzarb implements zzn {
    private zzn zzbnc;
    private zzaqw zzdcj;

    public zzarb(zzaqw zzaqw, zzn zzn) {
        this.zzdcj = zzaqw;
        this.zzbnc = zzn;
    }

    public final void onPause() {
    }

    public final void onResume() {
    }

    public final void zzcb() {
        this.zzbnc.zzcb();
        this.zzdcj.zzty();
    }

    public final void zzcc() {
        this.zzbnc.zzcc();
        this.zzdcj.zzno();
    }
}
