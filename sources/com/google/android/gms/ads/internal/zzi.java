package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzaam;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzpb;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzxa;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxq;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzi extends zzd implements zzaf, zzaam {
    private boolean zzwl;

    public zzi(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, zzw zzw) {
        super(context, zzjn, str, zzxn, zzang, zzw);
    }

    /* access modifiers changed from: protected */
    public zzaqw zza(zzaji zzaji, zzx zzx, zzait zzait) throws zzarg {
        zzaji zzaji2 = zzaji;
        View nextView = this.zzvw.zzacs.getNextView();
        if (nextView instanceof zzaqw) {
            ((zzaqw) nextView).destroy();
        }
        if (nextView != null) {
            this.zzvw.zzacs.removeView(nextView);
        }
        zzbv.zzel();
        zzaqw zza = zzarc.zza(this.zzvw.zzrt, zzasi.zzb(this.zzvw.zzacv), this.zzvw.zzacv.zzarb, false, false, this.zzvw.zzacq, this.zzvw.zzacr, this.zzvr, this, this.zzwc, zzaji2.zzcoq);
        if (this.zzvw.zzacv.zzard == null) {
            zzg(zza.getView());
        }
        zza.zzuf().zza(this, this, this, this, this, false, (zzz) null, zzx, this, zzait);
        zza(zza);
        zza.zzdr(zzaji2.zzcgs.zzcdi);
        return zza;
    }

    public final void zza(int i, int i2, int i3, int i4) {
        zzbp();
    }

    /* access modifiers changed from: protected */
    public void zza(zzaji zzaji, zznx zznx) {
        if (zzaji.errorCode != -2) {
            zzakk.zzcrm.post(new zzk(this, zzaji));
            return;
        }
        if (zzaji.zzacv != null) {
            this.zzvw.zzacv = zzaji.zzacv;
        }
        if (!zzaji.zzcos.zzceq || zzaji.zzcos.zzarg) {
            zzakk.zzcrm.post(new zzl(this, zzaji, this.zzwc.zzxa.zza(this.zzvw.zzrt, this.zzvw.zzacr, zzaji.zzcos), zznx));
            return;
        }
        this.zzvw.zzadv = 0;
        zzbw zzbw = this.zzvw;
        zzbv.zzej();
        zzbw.zzacu = zzabl.zza(this.zzvw.zzrt, this, zzaji, this.zzvw.zzacq, (zzaqw) null, this.zzwh, this, zznx);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzaqw zzaqw) {
        zzaqw.zza("/trackActiveViewUnit", (zzv<? super zzaqw>) new zzj(this));
    }

    public final void zza(zzod zzod) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzvw.zzado = zzod;
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzajh zzajh, zzajh zzajh2) {
        if (this.zzvw.zzfo() && this.zzvw.zzacs != null) {
            this.zzvw.zzacs.zzfr().zzdb(zzajh2.zzcev);
        }
        try {
            if (zzajh2.zzbyo != null && !zzajh2.zzceq && zzajh2.zzcor) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbfq)).booleanValue() && !zzajh2.zzccv.extras.containsKey("sdk_less_server_data")) {
                    try {
                        zzajh2.zzbyo.zzus();
                    } catch (Throwable unused) {
                        zzakb.v("Could not render test Ad label.");
                    }
                }
            }
        } catch (RuntimeException unused2) {
            zzakb.v("Could not render test AdLabel.");
        }
        return super.zza(zzajh, zzajh2);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzaqw zzaqw) {
        if (this.zzvw.zzacw != null) {
            this.zzvy.zza(this.zzvw.zzacv, this.zzvw.zzacw, zzaqw.getView(), zzaqw);
            this.zzwl = false;
            return;
        }
        this.zzwl = true;
        zzakb.zzdk("Request to enable ActiveView before adState is available.");
    }

    /* access modifiers changed from: protected */
    public void zzbq() {
        super.zzbq();
        if (this.zzwl) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbcb)).booleanValue()) {
                zzb(this.zzvw.zzacw.zzbyo);
            }
        }
    }

    public final void zzcn() {
        onAdClicked();
    }

    public final void zzco() {
        recordImpression();
        zzbm();
    }

    /* access modifiers changed from: protected */
    public final boolean zzcp() {
        return (this.zzvw.zzacx == null || this.zzvw.zzacx.zzcos == null || !this.zzvw.zzacx.zzcos.zzcfp) ? false : true;
    }

    public final void zzcq() {
        zzbn();
    }

    public final void zzh(View view) {
        this.zzvw.zzadu = view;
        zzb(new zzajh(this.zzvw.zzacx, (zzaqw) null, (zzwx) null, (zzxq) null, (String) null, (zzxa) null, (zzpb) null, (String) null));
    }
}
