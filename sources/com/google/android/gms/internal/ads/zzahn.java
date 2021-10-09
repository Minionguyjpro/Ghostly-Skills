package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Future;

@zzadh
public final class zzahn extends zzajx implements zzaht, zzahw, zzaia {
    /* access modifiers changed from: private */
    public final Context mContext;
    private int mErrorCode = 3;
    private final Object mLock;
    public final String zzbth;
    private final zzaji zzbze;
    private final zzaib zzcll;
    private final zzahw zzclm;
    /* access modifiers changed from: private */
    public final String zzcln;
    private final zzwx zzclo;
    private final long zzclp;
    private int zzclq = 0;
    private zzahq zzclr;
    private Future zzcls;
    private volatile zzb zzclt;

    public zzahn(Context context, String str, String str2, zzwx zzwx, zzaji zzaji, zzaib zzaib, zzahw zzahw, long j) {
        this.mContext = context;
        this.zzbth = str;
        this.zzcln = str2;
        this.zzclo = zzwx;
        this.zzbze = zzaji;
        this.zzcll = zzaib;
        this.mLock = new Object();
        this.zzclm = zzahw;
        this.zzclp = j;
    }

    /* access modifiers changed from: private */
    public final void zza(zzjj zzjj, zzxq zzxq) {
        this.zzcll.zzpf().zza((zzahw) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                zzxq.zza(zzjj, this.zzcln, this.zzclo.zzbrr);
            } else {
                zzxq.zzc(zzjj, this.zzcln);
            }
        } catch (RemoteException e) {
            zzakb.zzc("Fail to load ad from adapter.", e);
            zza(this.zzbth, 0);
        }
    }

    private final boolean zzf(long j) {
        int i;
        long elapsedRealtime = this.zzclp - (zzbv.zzer().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            i = 4;
        } else {
            try {
                this.mLock.wait(elapsedRealtime);
                return true;
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                i = 5;
            }
        }
        this.mErrorCode = i;
        return false;
    }

    public final void onStop() {
    }

    public final void zza(zzb zzb) {
        this.zzclt = zzb;
    }

    public final void zza(String str, int i) {
        synchronized (this.mLock) {
            this.zzclq = 2;
            this.mErrorCode = i;
            this.mLock.notify();
        }
    }

    public final void zzac(int i) {
        zza(this.zzbth, 0);
    }

    public final void zzc(Bundle bundle) {
        zzb zzb = this.zzclt;
        if (zzb != null) {
            zzb.zza("", bundle);
        }
    }

    public final void zzcb(String str) {
        synchronized (this.mLock) {
            this.zzclq = 1;
            this.mLock.notify();
        }
    }

    public final void zzdn() {
        Handler handler;
        Runnable zzahp;
        zzaib zzaib = this.zzcll;
        if (zzaib != null && zzaib.zzpf() != null && this.zzcll.zzpe() != null) {
            zzahv zzpf = this.zzcll.zzpf();
            zzpf.zza((zzahw) null);
            zzpf.zza((zzaht) this);
            zzpf.zza((zzaia) this);
            zzjj zzjj = this.zzbze.zzcgs.zzccv;
            zzxq zzpe = this.zzcll.zzpe();
            try {
                if (zzpe.isInitialized()) {
                    handler = zzamu.zzsy;
                    zzahp = new zzaho(this, zzjj, zzpe);
                } else {
                    handler = zzamu.zzsy;
                    zzahp = new zzahp(this, zzpe, zzjj, zzpf);
                }
                handler.post(zzahp);
            } catch (RemoteException e) {
                zzakb.zzc("Fail to check if adapter is initialized.", e);
                zza(this.zzbth, 0);
            }
            long elapsedRealtime = zzbv.zzer().elapsedRealtime();
            while (true) {
                synchronized (this.mLock) {
                    if (this.zzclq != 0) {
                        this.zzclr = new zzahs().zzg(zzbv.zzer().elapsedRealtime() - elapsedRealtime).zzad(1 == this.zzclq ? 6 : this.mErrorCode).zzcc(this.zzbth).zzcd(this.zzclo.zzbru).zzpd();
                    } else if (!zzf(elapsedRealtime)) {
                        this.zzclr = new zzahs().zzad(this.mErrorCode).zzg(zzbv.zzer().elapsedRealtime() - elapsedRealtime).zzcc(this.zzbth).zzcd(this.zzclo.zzbru).zzpd();
                        break;
                    }
                }
            }
            zzpf.zza((zzahw) null);
            zzpf.zza((zzaht) null);
            if (this.zzclq == 1) {
                this.zzclm.zzcb(this.zzbth);
            } else {
                this.zzclm.zza(this.zzbth, this.mErrorCode);
            }
        }
    }

    public final Future zzoz() {
        Future future = this.zzcls;
        if (future != null) {
            return future;
        }
        zzanz zzanz = (zzanz) zznt();
        this.zzcls = zzanz;
        return zzanz;
    }

    public final zzahq zzpa() {
        zzahq zzahq;
        synchronized (this.mLock) {
            zzahq = this.zzclr;
        }
        return zzahq;
    }

    public final zzwx zzpb() {
        return this.zzclo;
    }

    public final void zzpc() {
        zza(this.zzbze.zzcgs.zzccv, this.zzcll.zzpe());
    }
}
