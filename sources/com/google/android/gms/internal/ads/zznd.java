package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import org.json.JSONObject;

final class zznd extends zzna<Long> {
    zznd(int i, String str, Long l) {
        super(i, str, l, (zznb) null);
    }

    public final /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return Long.valueOf(sharedPreferences.getLong(getKey(), ((Long) zzja()).longValue()));
    }

    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Object obj) {
        editor.putLong(getKey(), ((Long) obj).longValue());
    }

    public final /* synthetic */ Object zzb(JSONObject jSONObject) {
        return Long.valueOf(jSONObject.optLong(getKey(), ((Long) zzja()).longValue()));
    }
}
