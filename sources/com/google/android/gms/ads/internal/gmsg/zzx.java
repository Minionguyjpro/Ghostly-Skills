package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakb;
import org.json.JSONObject;

final class zzx implements Runnable {
    private final /* synthetic */ JSONObject zzbmk;
    private final /* synthetic */ zzw zzbml;

    zzx(zzw zzw, JSONObject jSONObject) {
        this.zzbml = zzw;
        this.zzbmk = jSONObject;
    }

    public final void run() {
        this.zzbml.zzbmi.zza("fetchHttpRequestCompleted", this.zzbmk);
        zzakb.zzck("Dispatched http response.");
    }
}
