package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzakd {
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    @Nullable
    public SharedPreferences zzatw;
    /* access modifiers changed from: private */
    public boolean zzcik = true;
    /* access modifiers changed from: private */
    public boolean zzcil = true;
    /* access modifiers changed from: private */
    public boolean zzcim = true;
    /* access modifiers changed from: private */
    public boolean zzcit = false;
    /* access modifiers changed from: private */
    public String zzcpj = "";
    /* access modifiers changed from: private */
    public int zzcqg = -1;
    private zzanz<?> zzcqu;
    /* access modifiers changed from: private */
    public CopyOnWriteArraySet<zzakh> zzcqv = new CopyOnWriteArraySet<>();
    @Nullable
    SharedPreferences.Editor zzcqw;
    /* access modifiers changed from: private */
    public boolean zzcqx = false;
    /* access modifiers changed from: private */
    @Nullable
    public String zzcqy;
    /* access modifiers changed from: private */
    @Nullable
    public String zzcqz;
    /* access modifiers changed from: private */
    public long zzcra = 0;
    /* access modifiers changed from: private */
    public long zzcrb = 0;
    /* access modifiers changed from: private */
    public long zzcrc = 0;
    /* access modifiers changed from: private */
    public int zzcrd = 0;
    /* access modifiers changed from: private */
    public Set<String> zzcre = Collections.emptySet();
    /* access modifiers changed from: private */
    public JSONObject zzcrf = new JSONObject();

    /* access modifiers changed from: private */
    public final void zze(Bundle bundle) {
        new zzakf(this, bundle).zznt();
    }

    /* access modifiers changed from: private */
    public static boolean zzqq() {
        return PlatformVersion.isAtLeastM() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    private final void zzqr() {
        zzanz<?> zzanz = this.zzcqu;
        if (zzanz != null && !zzanz.isDone()) {
            try {
                this.zzcqu.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                zzakb.zzc("Interrupted while waiting for preferences loaded.", e);
            } catch (CancellationException | ExecutionException | TimeoutException e2) {
                zzakb.zzb("Fail to initialize AdSharedPreferenceManager.", e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public final Bundle zzqs() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.mLock) {
            bundle.putBoolean("use_https", this.zzcik);
            bundle.putBoolean("content_url_opted_out", this.zzcil);
            bundle.putBoolean("content_vertical_opted_out", this.zzcim);
            bundle.putBoolean("auto_collect_location", this.zzcit);
            bundle.putInt("version_code", this.zzcrd);
            bundle.putStringArray("never_pool_slots", (String[]) this.zzcre.toArray(new String[this.zzcre.size()]));
            bundle.putString("app_settings_json", this.zzcpj);
            bundle.putLong("app_settings_last_update_ms", this.zzcra);
            bundle.putLong("app_last_background_time_ms", this.zzcrb);
            bundle.putInt("request_in_session_count", this.zzcqg);
            bundle.putLong("first_ad_req_time_ms", this.zzcrc);
            bundle.putString("native_advanced_settings", this.zzcrf.toString());
            if (this.zzcqy != null) {
                bundle.putString("content_url_hashes", this.zzcqy);
            }
            if (this.zzcqz != null) {
                bundle.putString("content_vertical_hashes", this.zzcqz);
            }
        }
        return bundle;
    }

    public final void initialize(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzcqu = (zzanz) new zzake(this, context).zznt();
    }

    public final void zza(zzakh zzakh) {
        synchronized (this.mLock) {
            if (this.zzcqu != null && this.zzcqu.isDone()) {
                zzakh.zzd(zzqs());
            }
            this.zzcqv.add(zzakh);
        }
    }

    public final void zza(String str, String str2, boolean z) {
        zzqr();
        synchronized (this.mLock) {
            JSONArray optJSONArray = this.zzcrf.optJSONArray(str);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
            }
            int length = optJSONArray.length();
            int i = 0;
            while (true) {
                if (i < optJSONArray.length()) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        if (!str2.equals(optJSONObject.optString("template_id"))) {
                            i++;
                        } else if (!z || optJSONObject.optBoolean("uses_media_view", false) != z) {
                            length = i;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("template_id", str2);
                jSONObject.put("uses_media_view", z);
                jSONObject.put("timestamp_ms", zzbv.zzer().currentTimeMillis());
                optJSONArray.put(length, jSONObject);
                this.zzcrf.put(str, optJSONArray);
            } catch (JSONException e) {
                zzakb.zzc("Could not update native advanced settings", e);
            }
            if (this.zzcqw != null) {
                this.zzcqw.putString("native_advanced_settings", this.zzcrf.toString());
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", this.zzcrf.toString());
            zze(bundle);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzab(boolean r4) {
        /*
            r3 = this;
            r3.zzqr()
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzcik     // Catch:{ all -> 0x0031 }
            if (r1 != r4) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return
        L_0x000c:
            r3.zzcik = r4     // Catch:{ all -> 0x0031 }
            android.content.SharedPreferences$Editor r1 = r3.zzcqw     // Catch:{ all -> 0x0031 }
            if (r1 == 0) goto L_0x001e
            android.content.SharedPreferences$Editor r1 = r3.zzcqw     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "use_https"
            r1.putBoolean(r2, r4)     // Catch:{ all -> 0x0031 }
            android.content.SharedPreferences$Editor r1 = r3.zzcqw     // Catch:{ all -> 0x0031 }
            r1.apply()     // Catch:{ all -> 0x0031 }
        L_0x001e:
            boolean r1 = r3.zzcqx     // Catch:{ all -> 0x0031 }
            if (r1 != 0) goto L_0x002f
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0031 }
            r1.<init>()     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "use_https"
            r1.putBoolean(r2, r4)     // Catch:{ all -> 0x0031 }
            r3.zze((android.os.Bundle) r1)     // Catch:{ all -> 0x0031 }
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return
        L_0x0031:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzab(boolean):void");
    }

    public final void zzac(boolean z) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcil != z) {
                this.zzcil = z;
                if (this.zzcqw != null) {
                    this.zzcqw.putBoolean("content_url_opted_out", z);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzcil);
                bundle.putBoolean("content_vertical_opted_out", this.zzcim);
                zze(bundle);
            }
        }
    }

    public final void zzad(boolean z) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcim != z) {
                this.zzcim = z;
                if (this.zzcqw != null) {
                    this.zzcqw.putBoolean("content_vertical_opted_out", z);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzcil);
                bundle.putBoolean("content_vertical_opted_out", this.zzcim);
                zze(bundle);
            }
        }
    }

    public final void zzae(int i) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcrd != i) {
                this.zzcrd = i;
                if (this.zzcqw != null) {
                    this.zzcqw.putInt("version_code", i);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("version_code", i);
                zze(bundle);
            }
        }
    }

    public final void zzae(boolean z) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcit != z) {
                this.zzcit = z;
                if (this.zzcqw != null) {
                    this.zzcqw.putBoolean("auto_collect_location", z);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("auto_collect_location", z);
                zze(bundle);
            }
        }
    }

    public final void zzaf(int i) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcqg != i) {
                this.zzcqg = i;
                if (this.zzcqw != null) {
                    this.zzcqw.putInt("request_in_session_count", i);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("request_in_session_count", i);
                zze(bundle);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcn(@javax.annotation.Nullable java.lang.String r4) {
        /*
            r3 = this;
            r3.zzqr()
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0032
            java.lang.String r1 = r3.zzcqy     // Catch:{ all -> 0x0034 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0032
        L_0x0011:
            r3.zzcqy = r4     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzcqw     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0023
            android.content.SharedPreferences$Editor r1 = r3.zzcqw     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzcqw     // Catch:{ all -> 0x0034 }
            r1.apply()     // Catch:{ all -> 0x0034 }
        L_0x0023:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0034 }
            r1.<init>()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            r3.zze((android.os.Bundle) r1)     // Catch:{ all -> 0x0034 }
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0034:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzcn(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzco(@javax.annotation.Nullable java.lang.String r4) {
        /*
            r3 = this;
            r3.zzqr()
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0032
            java.lang.String r1 = r3.zzcqz     // Catch:{ all -> 0x0034 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0032
        L_0x0011:
            r3.zzcqz = r4     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzcqw     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0023
            android.content.SharedPreferences$Editor r1 = r3.zzcqw     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzcqw     // Catch:{ all -> 0x0034 }
            r1.apply()     // Catch:{ all -> 0x0034 }
        L_0x0023:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0034 }
            r1.<init>()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            r3.zze((android.os.Bundle) r1)     // Catch:{ all -> 0x0034 }
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0034:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzco(java.lang.String):void");
    }

    public final void zzcp(String str) {
        zzqr();
        synchronized (this.mLock) {
            if (!this.zzcre.contains(str)) {
                this.zzcre.add(str);
                if (this.zzcqw != null) {
                    this.zzcqw.putStringSet("never_pool_slots", this.zzcre);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.zzcre.toArray(new String[this.zzcre.size()]));
                zze(bundle);
            }
        }
    }

    public final void zzcq(String str) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcre.contains(str)) {
                this.zzcre.remove(str);
                if (this.zzcqw != null) {
                    this.zzcqw.putStringSet("never_pool_slots", this.zzcre);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.zzcre.toArray(new String[this.zzcre.size()]));
                zze(bundle);
            }
        }
    }

    public final boolean zzcr(String str) {
        boolean contains;
        zzqr();
        synchronized (this.mLock) {
            contains = this.zzcre.contains(str);
        }
        return contains;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcs(java.lang.String r6) {
        /*
            r5 = this;
            r5.zzqr()
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzbv.zzer()     // Catch:{ all -> 0x004a }
            long r1 = r1.currentTimeMillis()     // Catch:{ all -> 0x004a }
            r5.zzcra = r1     // Catch:{ all -> 0x004a }
            if (r6 == 0) goto L_0x0048
            java.lang.String r3 = r5.zzcpj     // Catch:{ all -> 0x004a }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x004a }
            if (r3 == 0) goto L_0x001b
            goto L_0x0048
        L_0x001b:
            r5.zzcpj = r6     // Catch:{ all -> 0x004a }
            android.content.SharedPreferences$Editor r3 = r5.zzcqw     // Catch:{ all -> 0x004a }
            if (r3 == 0) goto L_0x0034
            android.content.SharedPreferences$Editor r3 = r5.zzcqw     // Catch:{ all -> 0x004a }
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x004a }
            android.content.SharedPreferences$Editor r3 = r5.zzcqw     // Catch:{ all -> 0x004a }
            java.lang.String r4 = "app_settings_last_update_ms"
            r3.putLong(r4, r1)     // Catch:{ all -> 0x004a }
            android.content.SharedPreferences$Editor r3 = r5.zzcqw     // Catch:{ all -> 0x004a }
            r3.apply()     // Catch:{ all -> 0x004a }
        L_0x0034:
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x004a }
            r3.<init>()     // Catch:{ all -> 0x004a }
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x004a }
            java.lang.String r6 = "app_settings_last_update_ms"
            r3.putLong(r6, r1)     // Catch:{ all -> 0x004a }
            r5.zze((android.os.Bundle) r3)     // Catch:{ all -> 0x004a }
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return
        L_0x004a:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzcs(java.lang.String):void");
    }

    public final void zzj(long j) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcrb != j) {
                this.zzcrb = j;
                if (this.zzcqw != null) {
                    this.zzcqw.putLong("app_last_background_time_ms", j);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("app_last_background_time_ms", j);
                zze(bundle);
            }
        }
    }

    public final void zzk(long j) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcrc != j) {
                this.zzcrc = j;
                if (this.zzcqw != null) {
                    this.zzcqw.putLong("first_ad_req_time_ms", j);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("first_ad_req_time_ms", j);
                zze(bundle);
            }
        }
    }

    public final boolean zzqt() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            if (!this.zzcik) {
                if (!this.zzcqx) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public final boolean zzqu() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcil;
        }
        return z;
    }

    @Nullable
    public final String zzqv() {
        String str;
        zzqr();
        synchronized (this.mLock) {
            str = this.zzcqy;
        }
        return str;
    }

    public final boolean zzqw() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcim;
        }
        return z;
    }

    @Nullable
    public final String zzqx() {
        String str;
        zzqr();
        synchronized (this.mLock) {
            str = this.zzcqz;
        }
        return str;
    }

    public final boolean zzqy() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcit;
        }
        return z;
    }

    public final int zzqz() {
        int i;
        zzqr();
        synchronized (this.mLock) {
            i = this.zzcrd;
        }
        return i;
    }

    public final zzajl zzra() {
        zzajl zzajl;
        zzqr();
        synchronized (this.mLock) {
            zzajl = new zzajl(this.zzcpj, this.zzcra);
        }
        return zzajl;
    }

    public final long zzrb() {
        long j;
        zzqr();
        synchronized (this.mLock) {
            j = this.zzcrb;
        }
        return j;
    }

    public final int zzrc() {
        int i;
        zzqr();
        synchronized (this.mLock) {
            i = this.zzcqg;
        }
        return i;
    }

    public final long zzrd() {
        long j;
        zzqr();
        synchronized (this.mLock) {
            j = this.zzcrc;
        }
        return j;
    }

    public final JSONObject zzre() {
        JSONObject jSONObject;
        zzqr();
        synchronized (this.mLock) {
            jSONObject = this.zzcrf;
        }
        return jSONObject;
    }

    public final void zzrf() {
        zzqr();
        synchronized (this.mLock) {
            this.zzcrf = new JSONObject();
            if (this.zzcqw != null) {
                this.zzcqw.remove("native_advanced_settings");
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            zze(bundle);
        }
    }
}
