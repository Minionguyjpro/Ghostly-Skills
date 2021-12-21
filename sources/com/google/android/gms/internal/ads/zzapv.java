package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzapv {
    private final boolean zzczu;
    private final int zzczv;
    private final int zzczw;
    private final int zzczx;
    private final String zzczy;
    private final int zzczz;
    private final int zzdaa;
    private final int zzdab;
    private final boolean zzdac;

    public zzapv(String str) {
        JSONObject jSONObject = null;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
            }
        }
        this.zzczu = zza(jSONObject, "aggressive_media_codec_release", zznk.zzavl);
        this.zzczv = zzb(jSONObject, "byte_buffer_precache_limit", zznk.zzauv);
        this.zzczw = zzb(jSONObject, "exo_cache_buffer_size", zznk.zzauz);
        this.zzczx = zzb(jSONObject, "exo_connect_timeout_millis", zznk.zzaur);
        this.zzczy = zzc(jSONObject, "exo_player_version", zznk.zzauq);
        this.zzczz = zzb(jSONObject, "exo_read_timeout_millis", zznk.zzaus);
        this.zzdaa = zzb(jSONObject, "load_check_interval_bytes", zznk.zzaut);
        this.zzdab = zzb(jSONObject, "player_precache_limit", zznk.zzauu);
        this.zzdac = zza(jSONObject, "use_cache_data_source", zznk.zzbdr);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.ads.zzna<java.lang.Boolean>, com.google.android.gms.internal.ads.zzna] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(org.json.JSONObject r0, java.lang.String r1, com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r2) {
        /*
            if (r0 == 0) goto L_0x0007
            boolean r0 = r0.getBoolean(r1)     // Catch:{ JSONException -> 0x0007 }
            return r0
        L_0x0007:
            com.google.android.gms.internal.ads.zzni r0 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r0.zzd(r2)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapv.zza(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.ads.zzna):boolean");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.ads.zzna<java.lang.Integer>, com.google.android.gms.internal.ads.zzna] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzb(org.json.JSONObject r0, java.lang.String r1, com.google.android.gms.internal.ads.zzna<java.lang.Integer> r2) {
        /*
            if (r0 == 0) goto L_0x0007
            int r0 = r0.getInt(r1)     // Catch:{ JSONException -> 0x0007 }
            return r0
        L_0x0007:
            com.google.android.gms.internal.ads.zzni r0 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r0.zzd(r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapv.zzb(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.ads.zzna):int");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.ads.zzna<java.lang.String>, com.google.android.gms.internal.ads.zzna] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzc(org.json.JSONObject r0, java.lang.String r1, com.google.android.gms.internal.ads.zzna<java.lang.String> r2) {
        /*
            if (r0 == 0) goto L_0x0007
            java.lang.String r0 = r0.getString(r1)     // Catch:{ JSONException -> 0x0007 }
            return r0
        L_0x0007:
            com.google.android.gms.internal.ads.zzni r0 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r0.zzd(r2)
            java.lang.String r0 = (java.lang.String) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapv.zzc(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.ads.zzna):java.lang.String");
    }
}
