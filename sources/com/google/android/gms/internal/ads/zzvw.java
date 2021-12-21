package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;

public final class zzvw extends zzaop<zzuu> {
    private final Object mLock = new Object();
    /* access modifiers changed from: private */
    public zzalo<zzuu> zzbpz;
    private boolean zzbqt;
    private int zzbqu;

    public zzvw(zzalo<zzuu> zzalo) {
        this.zzbpz = zzalo;
        this.zzbqt = false;
        this.zzbqu = 0;
    }

    private final void zzmc() {
        synchronized (this.mLock) {
            Preconditions.checkState(this.zzbqu >= 0);
            if (!this.zzbqt || this.zzbqu != 0) {
                zzakb.v("There are still references to the engine. Not destroying.");
            } else {
                zzakb.v("No reference is left (including root). Cleaning up engine.");
                zza(new zzvz(this), new zzaon());
            }
        }
    }

    public final zzvs zzlz() {
        zzvs zzvs = new zzvs(this);
        synchronized (this.mLock) {
            zza(new zzvx(this, zzvs), new zzvy(this, zzvs));
            Preconditions.checkState(this.zzbqu >= 0);
            this.zzbqu++;
        }
        return zzvs;
    }

    /* access modifiers changed from: protected */
    public final void zzma() {
        synchronized (this.mLock) {
            Preconditions.checkState(this.zzbqu > 0);
            zzakb.v("Releasing 1 reference for JS Engine");
            this.zzbqu--;
            zzmc();
        }
    }

    public final void zzmb() {
        synchronized (this.mLock) {
            Preconditions.checkState(this.zzbqu >= 0);
            zzakb.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.zzbqt = true;
            zzmc();
        }
    }
}
