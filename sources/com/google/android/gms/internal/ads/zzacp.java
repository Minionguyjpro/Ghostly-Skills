package com.google.android.gms.internal.ads;

import androidx.collection.SimpleArrayMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzacp implements zzacd<zzos> {
    private final boolean zzcbk;

    public zzacp(boolean z) {
        this.zzcbk = z;
    }

    public final /* synthetic */ zzpb zza(zzabv zzabv, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        zzanz<zzoj> zzg = zzabv.zzg(jSONObject);
        zzanz<zzaqw> zzc = zzabv.zzc(jSONObject, "video");
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("type");
            if ("string".equals(string)) {
                simpleArrayMap2.put(jSONObject2.getString("name"), jSONObject2.getString("string_value"));
            } else if ("image".equals(string)) {
                simpleArrayMap.put(jSONObject2.getString("name"), zzabv.zza(jSONObject2, "image_value", this.zzcbk));
            } else {
                String valueOf = String.valueOf(string);
                zzakb.zzdk(valueOf.length() != 0 ? "Unknown custom asset type: ".concat(valueOf) : new String("Unknown custom asset type: "));
            }
        }
        zzaqw zzc2 = zzabv.zzc(zzc);
        String string2 = jSONObject.getString("custom_template_id");
        SimpleArrayMap simpleArrayMap3 = new SimpleArrayMap();
        for (int i2 = 0; i2 < simpleArrayMap.size(); i2++) {
            simpleArrayMap3.put(simpleArrayMap.keyAt(i2), ((Future) simpleArrayMap.valueAt(i2)).get());
        }
        return new zzos(string2, simpleArrayMap3, simpleArrayMap2, (zzoj) zzg.get(), zzc2 != null ? zzc2.zztm() : null, zzc2 != null ? zzc2.getView() : null);
    }
}
