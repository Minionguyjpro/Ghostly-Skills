package com.google.android.gms.internal.ads;

final class zzvo implements zzaoo<zzuu> {
    private final /* synthetic */ zzvf zzbqk;
    private final /* synthetic */ zzvw zzbqn;

    zzvo(zzvf zzvf, zzvw zzvw) {
        this.zzbqk = zzvf;
        this.zzbqn = zzvw;
    }

    public final /* synthetic */ void zze(Object obj) {
        synchronized (this.zzbqk.mLock) {
            int unused = this.zzbqk.zzbqb = 0;
            if (!(this.zzbqk.zzbqa == null || this.zzbqn == this.zzbqk.zzbqa)) {
                zzakb.v("New JS engine is loaded, marking previous one as destroyable.");
                this.zzbqk.zzbqa.zzmb();
            }
            zzvw unused2 = this.zzbqk.zzbqa = this.zzbqn;
        }
    }
}
