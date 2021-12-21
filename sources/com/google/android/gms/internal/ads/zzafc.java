package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzafc implements Runnable {
    private final /* synthetic */ zzafa zzcgj;
    final /* synthetic */ JSONObject zzcgk;
    final /* synthetic */ String zzcgl;

    zzafc(zzafa zzafa, JSONObject jSONObject, String str) {
        this.zzcgj = zzafa;
        this.zzcgk = jSONObject;
        this.zzcgl = str;
    }

    public final void run() {
        zzvs unused = this.zzcgj.zzcgi = zzafa.zzcge.zzb((zzci) null);
        this.zzcgj.zzcgi.zza(new zzafd(this), new zzafe(this));
    }
}
