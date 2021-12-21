package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzjj;
import java.lang.ref.WeakReference;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbl {
    private final zzbn zzaan;
    /* access modifiers changed from: private */
    public zzjj zzaao;
    /* access modifiers changed from: private */
    public boolean zzaap;
    private boolean zzaaq;
    private long zzaar;
    private final Runnable zzy;

    public zzbl(zza zza) {
        this(zza, new zzbn(zzakk.zzcrm));
    }

    private zzbl(zza zza, zzbn zzbn) {
        this.zzaap = false;
        this.zzaaq = false;
        this.zzaar = 0;
        this.zzaan = zzbn;
        this.zzy = new zzbm(this, new WeakReference(zza));
    }

    public final void cancel() {
        this.zzaap = false;
        this.zzaan.removeCallbacks(this.zzy);
    }

    public final void pause() {
        this.zzaaq = true;
        if (this.zzaap) {
            this.zzaan.removeCallbacks(this.zzy);
        }
    }

    public final void resume() {
        this.zzaaq = false;
        if (this.zzaap) {
            this.zzaap = false;
            zza(this.zzaao, this.zzaar);
        }
    }

    public final void zza(zzjj zzjj, long j) {
        if (this.zzaap) {
            zzakb.zzdk("An ad refresh is already scheduled.");
            return;
        }
        this.zzaao = zzjj;
        this.zzaap = true;
        this.zzaar = j;
        if (!this.zzaaq) {
            StringBuilder sb = new StringBuilder(65);
            sb.append("Scheduling ad refresh ");
            sb.append(j);
            sb.append(" milliseconds from now.");
            zzakb.zzdj(sb.toString());
            this.zzaan.postDelayed(this.zzy, j);
        }
    }

    public final void zzdy() {
        this.zzaaq = false;
        this.zzaap = false;
        zzjj zzjj = this.zzaao;
        if (!(zzjj == null || zzjj.extras == null)) {
            this.zzaao.extras.remove("_ad");
        }
        zza(this.zzaao, 0);
    }

    public final boolean zzdz() {
        return this.zzaap;
    }

    public final void zzf(zzjj zzjj) {
        this.zzaao = zzjj;
    }

    public final void zzg(zzjj zzjj) {
        zza(zzjj, 60000);
    }
}
