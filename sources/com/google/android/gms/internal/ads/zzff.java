package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;

@zzadh
public final class zzff implements zzfo {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzet zzafq;
    private final zzv<zzwb> zzafs = new zzfk(this);
    private final zzv<zzwb> zzaft = new zzfl(this);
    private final zzv<zzwb> zzafu = new zzfm(this);
    /* access modifiers changed from: private */
    public final zzab zzafw;
    private zzvs zzafx;
    /* access modifiers changed from: private */
    public boolean zzafy;
    private final zzv<zzwb> zzafz = new zzfn(this);

    public zzff(zzet zzet, zzvf zzvf, Context context) {
        this.zzafq = zzet;
        this.mContext = context;
        this.zzafw = new zzab(context);
        zzvs zzb = zzvf.zzb((zzci) null);
        this.zzafx = zzb;
        zzb.zza(new zzfg(this), new zzfh(this));
        String valueOf = String.valueOf(this.zzafq.zzaet.zzfy());
        zzakb.zzck(valueOf.length() != 0 ? "Core JS tracking ad unit: ".concat(valueOf) : new String("Core JS tracking ad unit: "));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzwb zzwb) {
        zzwb.zza("/updateActiveView", this.zzafs);
        zzwb.zza("/untrackActiveViewUnit", this.zzaft);
        zzwb.zza("/visibilityChanged", this.zzafu);
        if (zzbv.zzfh().zzs(this.mContext)) {
            zzwb.zza("/logScionEvent", this.zzafz);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzwb zzwb) {
        zzwb.zzb("/visibilityChanged", this.zzafu);
        zzwb.zzb("/untrackActiveViewUnit", this.zzaft);
        zzwb.zzb("/updateActiveView", this.zzafs);
        if (zzbv.zzfh().zzs(this.mContext)) {
            zzwb.zzb("/logScionEvent", this.zzafz);
        }
    }

    public final void zzb(JSONObject jSONObject, boolean z) {
        this.zzafx.zza(new zzfi(this, jSONObject), new zzaon());
    }

    public final boolean zzgk() {
        return this.zzafy;
    }

    public final void zzgl() {
        this.zzafx.zza(new zzfj(this), new zzaon());
        this.zzafx.release();
    }
}
