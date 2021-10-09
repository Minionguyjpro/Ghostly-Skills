package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzov;
import com.google.android.gms.internal.ads.zzpb;
import java.util.List;

final class zzbf implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ int zzaag;
    private final /* synthetic */ zzpb zzaaj;
    private final /* synthetic */ List zzaak;

    zzbf(zzbc zzbc, zzpb zzpb, int i, List list) {
        this.zzaaf = zzbc;
        this.zzaaj = zzpb;
        this.zzaag = i;
        this.zzaak = list;
    }

    public final void run() {
        try {
            boolean z = false;
            if ((this.zzaaj instanceof zzoq) && this.zzaaf.zzvw.zzadg != null) {
                zzbc zzbc = this.zzaaf;
                if (this.zzaag != this.zzaak.size() - 1) {
                    z = true;
                }
                zzbc.zzvu = z;
                zzov zzb = zzbc.zza(this.zzaaj);
                this.zzaaf.zzvw.zzadg.zza(zzb);
                this.zzaaf.zzb(zzb.zzka());
            } else if ((this.zzaaj instanceof zzoq) && this.zzaaf.zzvw.zzadf != null) {
                zzbc zzbc2 = this.zzaaf;
                if (this.zzaag != this.zzaak.size() - 1) {
                    z = true;
                }
                zzbc2.zzvu = z;
                zzoq zzoq = (zzoq) this.zzaaj;
                this.zzaaf.zzvw.zzadf.zza(zzoq);
                this.zzaaf.zzb(zzoq.zzka());
            } else if ((this.zzaaj instanceof zzoo) && this.zzaaf.zzvw.zzadg != null) {
                zzbc zzbc3 = this.zzaaf;
                if (this.zzaag != this.zzaak.size() - 1) {
                    z = true;
                }
                zzbc3.zzvu = z;
                zzov zzb2 = zzbc.zza(this.zzaaj);
                this.zzaaf.zzvw.zzadg.zza(zzb2);
                this.zzaaf.zzb(zzb2.zzka());
            } else if (!(this.zzaaj instanceof zzoo) || this.zzaaf.zzvw.zzade == null) {
                zzbc zzbc4 = this.zzaaf;
                if (this.zzaag != this.zzaak.size() - 1) {
                    z = true;
                }
                zzbc4.zzc(3, z);
            } else {
                zzbc zzbc5 = this.zzaaf;
                if (this.zzaag != this.zzaak.size() - 1) {
                    z = true;
                }
                zzbc5.zzvu = z;
                zzoo zzoo = (zzoo) this.zzaaj;
                this.zzaaf.zzvw.zzade.zza(zzoo);
                this.zzaaf.zzb(zzoo.zzka());
            }
        } catch (RemoteException e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
