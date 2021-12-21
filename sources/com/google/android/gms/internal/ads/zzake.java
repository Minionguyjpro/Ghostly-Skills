package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

final class zzake extends zzakg {
    private final /* synthetic */ Context zzcrg;
    private final /* synthetic */ zzakd zzcrh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzake(zzakd zzakd, Context context) {
        super((zzake) null);
        this.zzcrh = zzakd;
        this.zzcrg = context;
    }

    public final void zzdn() {
        SharedPreferences sharedPreferences = this.zzcrg.getSharedPreferences("admob", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        synchronized (this.zzcrh.mLock) {
            SharedPreferences unused = this.zzcrh.zzatw = sharedPreferences;
            this.zzcrh.zzcqw = edit;
            boolean unused2 = this.zzcrh.zzcqx = zzakd.zzqq();
            boolean unused3 = this.zzcrh.zzcik = this.zzcrh.zzatw.getBoolean("use_https", this.zzcrh.zzcik);
            boolean unused4 = this.zzcrh.zzcil = this.zzcrh.zzatw.getBoolean("content_url_opted_out", this.zzcrh.zzcil);
            String unused5 = this.zzcrh.zzcqy = this.zzcrh.zzatw.getString("content_url_hashes", this.zzcrh.zzcqy);
            boolean unused6 = this.zzcrh.zzcit = this.zzcrh.zzatw.getBoolean("auto_collect_location", this.zzcrh.zzcit);
            boolean unused7 = this.zzcrh.zzcim = this.zzcrh.zzatw.getBoolean("content_vertical_opted_out", this.zzcrh.zzcim);
            String unused8 = this.zzcrh.zzcqz = this.zzcrh.zzatw.getString("content_vertical_hashes", this.zzcrh.zzcqz);
            int unused9 = this.zzcrh.zzcrd = this.zzcrh.zzatw.getInt("version_code", this.zzcrh.zzcrd);
            String unused10 = this.zzcrh.zzcpj = this.zzcrh.zzatw.getString("app_settings_json", this.zzcrh.zzcpj);
            long unused11 = this.zzcrh.zzcra = this.zzcrh.zzatw.getLong("app_settings_last_update_ms", this.zzcrh.zzcra);
            long unused12 = this.zzcrh.zzcrb = this.zzcrh.zzatw.getLong("app_last_background_time_ms", this.zzcrh.zzcrb);
            int unused13 = this.zzcrh.zzcqg = this.zzcrh.zzatw.getInt("request_in_session_count", this.zzcrh.zzcqg);
            long unused14 = this.zzcrh.zzcrc = this.zzcrh.zzatw.getLong("first_ad_req_time_ms", this.zzcrh.zzcrc);
            Set unused15 = this.zzcrh.zzcre = this.zzcrh.zzatw.getStringSet("never_pool_slots", this.zzcrh.zzcre);
            try {
                JSONObject unused16 = this.zzcrh.zzcrf = new JSONObject(this.zzcrh.zzatw.getString("native_advanced_settings", "{}"));
            } catch (JSONException e) {
                zzakb.zzc("Could not convert native advanced settings to json object", e);
            }
            this.zzcrh.zze(this.zzcrh.zzqs());
        }
    }
}
