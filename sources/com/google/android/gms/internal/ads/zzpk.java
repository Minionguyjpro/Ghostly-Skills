package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class zzpk implements zzv<Object> {
    private final /* synthetic */ zzacm zzbji;
    private final /* synthetic */ zzpf zzbjj;

    zzpk(zzpf zzpf, zzacm zzacm) {
        this.zzbjj = zzpf;
        this.zzbji = zzacm;
    }

    public final void zza(Object obj, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String next : map.keySet()) {
                jSONObject.put(next, map.get(next));
            }
            jSONObject.put("id", this.zzbjj.zzbjh);
            this.zzbji.zza("sendMessageToNativeJs", jSONObject);
        } catch (JSONException e) {
            zzakb.zzb("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
