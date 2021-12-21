package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzagf extends zzagh {
    private final Object mLock = new Object();
    private final Context zzaeo;
    private SharedPreferences zzckn;
    private final zzwf<JSONObject, JSONObject> zzcko;

    public zzagf(Context context, zzwf<JSONObject, JSONObject> zzwf) {
        this.zzaeo = context.getApplicationContext();
        this.zzcko = zzwf;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzn(JSONObject jSONObject) {
        zznk.zza(this.zzaeo, 1, jSONObject);
        this.zzckn.edit().putLong("js_last_update", zzbv.zzer().currentTimeMillis()).apply();
        return null;
    }

    public final zzanz<Void> zzop() {
        synchronized (this.mLock) {
            if (this.zzckn == null) {
                this.zzckn = this.zzaeo.getSharedPreferences("google_ads_flags_meta", 0);
            }
        }
        if (zzbv.zzer().currentTimeMillis() - this.zzckn.getLong("js_last_update", 0) < ((Long) zzkb.zzik().zzd(zznk.zzbbl)).longValue()) {
            return zzano.zzi(null);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("js", zzang.zzsl().zzcw);
            jSONObject.put("mf", zzkb.zzik().zzd(zznk.zzbbm));
            jSONObject.put("cl", "193400285");
            jSONObject.put("rapid_rc", "dev");
            jSONObject.put("rapid_rollup", "HEAD");
            jSONObject.put("dynamite_version", ModuleDescriptor.MODULE_VERSION);
            return zzano.zza(this.zzcko.zzf(jSONObject), new zzagg(this), zzaoe.zzcvz);
        } catch (JSONException e) {
            zzakb.zzb("Unable to populate SDK Core Constants parameters.", e);
            return zzano.zzi(null);
        }
    }
}
