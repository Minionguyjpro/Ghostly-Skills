package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final /* synthetic */ class zzacf implements Runnable {
    private final zzaoj zzbns;
    private final zzace zzcbf;
    private final JSONObject zzcbg;

    zzacf(zzace zzace, JSONObject jSONObject, zzaoj zzaoj) {
        this.zzcbf = zzace;
        this.zzcbg = jSONObject;
        this.zzbns = zzaoj;
    }

    public final void run() {
        this.zzcbf.zza(this.zzcbg, this.zzbns);
    }
}
