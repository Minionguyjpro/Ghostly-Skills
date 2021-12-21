package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final /* synthetic */ class zzph implements zzasd {
    private final zzpg zzbjk;
    private final Map zzbjl;
    private final zzacm zzbjm;

    zzph(zzpg zzpg, Map map, zzacm zzacm) {
        this.zzbjk = zzpg;
        this.zzbjl = map;
        this.zzbjm = zzacm;
    }

    public final void zze(boolean z) {
        zzpg zzpg = this.zzbjk;
        Map map = this.zzbjl;
        zzacm zzacm = this.zzbjm;
        String unused = zzpg.zzbjj.zzbjh = (String) map.get("id");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("messageType", "htmlLoaded");
            jSONObject.put("id", zzpg.zzbjj.zzbjh);
            zzacm.zza("sendMessageToNativeJs", jSONObject);
        } catch (JSONException e) {
            zzakb.zzb("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
