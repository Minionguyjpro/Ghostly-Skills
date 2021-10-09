package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajx;
import com.google.android.gms.internal.ads.zzakk;

@zzadh
final class zzj extends zzajx {
    final /* synthetic */ zzd zzbyg;

    private zzj(zzd zzd) {
        this.zzbyg = zzd;
    }

    /* synthetic */ zzj(zzd zzd, zzf zzf) {
        this(zzd);
    }

    public final void onStop() {
    }

    public final void zzdn() {
        Bitmap zza = zzbv.zzfe().zza(Integer.valueOf(this.zzbyg.zzbxn.zzbyw.zzzj));
        if (zza != null) {
            zzakk.zzcrm.post(new zzk(this, zzbv.zzem().zza(this.zzbyg.mActivity, zza, this.zzbyg.zzbxn.zzbyw.zzzh, this.zzbyg.zzbxn.zzbyw.zzzi)));
        }
    }
}
