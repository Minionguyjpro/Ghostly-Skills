package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import org.json.JSONObject;

@zzadh
public final class zzfb implements zzfo {
    /* access modifiers changed from: private */
    public final zzet zzafq;
    private final zzaqw zzafr;
    private final zzv<zzaqw> zzafs = new zzfc(this);
    private final zzv<zzaqw> zzaft = new zzfd(this);
    private final zzv<zzaqw> zzafu = new zzfe(this);

    public zzfb(zzet zzet, zzaqw zzaqw) {
        this.zzafq = zzet;
        this.zzafr = zzaqw;
        zzaqw.zza("/updateActiveView", (zzv<? super zzaqw>) this.zzafs);
        zzaqw.zza("/untrackActiveViewUnit", (zzv<? super zzaqw>) this.zzaft);
        zzaqw.zza("/visibilityChanged", (zzv<? super zzaqw>) this.zzafu);
        String valueOf = String.valueOf(this.zzafq.zzaet.zzfy());
        zzakb.zzck(valueOf.length() != 0 ? "Custom JS tracking ad unit: ".concat(valueOf) : new String("Custom JS tracking ad unit: "));
    }

    public final void zzb(JSONObject jSONObject, boolean z) {
        if (!z) {
            this.zzafr.zzb("AFMA_updateActiveView", jSONObject);
        } else {
            this.zzafq.zzb(this);
        }
    }

    public final boolean zzgk() {
        return true;
    }

    public final void zzgl() {
        zzaqw zzaqw = this.zzafr;
        zzaqw.zzb("/visibilityChanged", this.zzafu);
        zzaqw.zzb("/untrackActiveViewUnit", this.zzaft);
        zzaqw.zzb("/updateActiveView", this.zzafs);
    }
}
