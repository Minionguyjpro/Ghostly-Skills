package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaoj;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzaa implements zzv<Object> {
    private final HashMap<String, zzaoj<JSONObject>> zzbmv = new HashMap<>();

    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("request_id");
        String str2 = map.get("fetched_ad");
        zzakb.zzck("Received ad from the cache.");
        zzaoj zzaoj = this.zzbmv.get(str);
        if (zzaoj == null) {
            zzakb.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        try {
            zzaoj.set(new JSONObject(str2));
        } catch (JSONException e) {
            zzakb.zzb("Failed constructing JSON object from value passed from javascript", e);
            zzaoj.set(null);
        } finally {
            this.zzbmv.remove(str);
        }
    }

    public final Future<JSONObject> zzas(String str) {
        zzaoj zzaoj = new zzaoj();
        this.zzbmv.put(str, zzaoj);
        return zzaoj;
    }

    public final void zzat(String str) {
        zzaoj zzaoj = this.zzbmv.get(str);
        if (zzaoj == null) {
            zzakb.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!zzaoj.isDone()) {
            zzaoj.cancel(true);
        }
        this.zzbmv.remove(str);
    }
}
