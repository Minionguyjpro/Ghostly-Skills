package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

@zzadh
public abstract class zzadz implements zzadx, zzalc<Void> {
    private final Object mLock = new Object();
    private final zzaol<zzaef> zzccp;
    private final zzadx zzccq;

    public zzadz(zzaol<zzaef> zzaol, zzadx zzadx) {
        this.zzccp = zzaol;
        this.zzccq = zzadx;
    }

    public final void cancel() {
        zznz();
    }

    public final void zza(zzaej zzaej) {
        synchronized (this.mLock) {
            this.zzccq.zza(zzaej);
            zznz();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzaen zzaen, zzaef zzaef) {
        try {
            zzaen.zza(zzaef, (zzaeq) new zzaei(this));
            return true;
        } catch (Throwable th) {
            zzakb.zzc("Could not fetch ad response from ad request service due to an Exception.", th);
            zzbv.zzeo().zza(th, "AdRequestClientTask.getAdResponseFromService");
            this.zzccq.zza(new zzaej(0));
            return false;
        }
    }

    public final /* synthetic */ Object zznt() {
        zzaen zzoa = zzoa();
        if (zzoa == null) {
            this.zzccq.zza(new zzaej(0));
            zznz();
            return null;
        }
        this.zzccp.zza(new zzaea(this, zzoa), new zzaeb(this));
        return null;
    }

    public abstract void zznz();

    public abstract zzaen zzoa();
}
