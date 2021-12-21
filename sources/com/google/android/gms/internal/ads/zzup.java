package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final /* synthetic */ class zzup {
    public static void zza(zzuo zzuo, String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("(");
        sb.append(str2);
        sb.append(");");
        zzuo.zzbe(sb.toString());
    }

    public static void zza(zzuo zzuo, String str, Map map) {
        try {
            zzuo.zza(str, zzbv.zzek().zzn(map));
        } catch (JSONException unused) {
            zzakb.zzdk("Could not convert parameters to JSON.");
        }
    }

    public static void zza(zzuo zzuo, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzuo.zzf(str, jSONObject.toString());
    }

    public static void zzb(zzuo zzuo, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String valueOf = String.valueOf(sb.toString());
        zzakb.zzck(valueOf.length() != 0 ? "Dispatching AFMA event: ".concat(valueOf) : new String("Dispatching AFMA event: "));
        zzuo.zzbe(sb.toString());
    }
}
