package com.google.android.gms.internal.ads;

import android.location.Location;
import com.appnext.base.b.d;
import com.appnext.base.b.i;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

@zzadh
public final class zzafs {
    private static final SimpleDateFormat zzcho = new SimpleDateFormat("yyyyMMdd", Locale.US);

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b4 A[Catch:{ JSONException -> 0x0269 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d9 A[Catch:{ JSONException -> 0x0269 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e1 A[Catch:{ JSONException -> 0x0269 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e7 A[Catch:{ JSONException -> 0x0269 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0161 A[Catch:{ JSONException -> 0x0269 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016a A[Catch:{ JSONException -> 0x0269 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzaej zza(android.content.Context r54, com.google.android.gms.internal.ads.zzaef r55, java.lang.String r56) {
        /*
            r0 = r55
            java.lang.String r1 = "interstitial_timeout"
            java.lang.String r10 = ""
            r15 = 0
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0269 }
            r2 = r56
            r11.<init>(r2)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r2 = "ad_base_url"
            r12 = 0
            java.lang.String r2 = r11.optString(r2, r12)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r3 = "ad_url"
            java.lang.String r4 = r11.optString(r3, r12)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r3 = "ad_size"
            java.lang.String r13 = r11.optString(r3, r12)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r3 = "ad_slot_size"
            java.lang.String r40 = r11.optString(r3, r13)     // Catch:{ JSONException -> 0x0269 }
            if (r0 == 0) goto L_0x0030
            int r3 = r0.zzcdb     // Catch:{ JSONException -> 0x0269 }
            if (r3 == 0) goto L_0x0030
            r24 = 1
            goto L_0x0032
        L_0x0030:
            r24 = 0
        L_0x0032:
            java.lang.String r3 = "ad_json"
            java.lang.String r3 = r11.optString(r3, r12)     // Catch:{ JSONException -> 0x0269 }
            if (r3 != 0) goto L_0x0040
            java.lang.String r3 = "ad_html"
            java.lang.String r3 = r11.optString(r3, r12)     // Catch:{ JSONException -> 0x0269 }
        L_0x0040:
            if (r3 != 0) goto L_0x0048
            java.lang.String r3 = "body"
            java.lang.String r3 = r11.optString(r3, r12)     // Catch:{ JSONException -> 0x0269 }
        L_0x0048:
            if (r3 != 0) goto L_0x0056
            java.lang.String r5 = "ads"
            boolean r5 = r11.has(r5)     // Catch:{ JSONException -> 0x0269 }
            if (r5 == 0) goto L_0x0056
            java.lang.String r3 = r11.toString()     // Catch:{ JSONException -> 0x0269 }
        L_0x0056:
            java.lang.String r5 = "debug_dialog"
            java.lang.String r19 = r11.optString(r5, r12)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r5 = "debug_signals"
            java.lang.String r42 = r11.optString(r5, r12)     // Catch:{ JSONException -> 0x0269 }
            boolean r5 = r11.has(r1)     // Catch:{ JSONException -> 0x0269 }
            r8 = -1
            if (r5 == 0) goto L_0x0079
            double r5 = r11.getDouble(r1)     // Catch:{ JSONException -> 0x0269 }
            r16 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r5 = r5 * r16
            long r5 = (long) r5     // Catch:{ JSONException -> 0x0269 }
            r16 = r5
            goto L_0x007b
        L_0x0079:
            r16 = r8
        L_0x007b:
            java.lang.String r1 = "orientation"
            java.lang.String r1 = r11.optString(r1, r12)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r5 = "portrait"
            boolean r5 = r5.equals(r1)     // Catch:{ JSONException -> 0x0269 }
            r7 = -1
            if (r5 == 0) goto L_0x0095
            com.google.android.gms.internal.ads.zzakq r1 = com.google.android.gms.ads.internal.zzbv.zzem()     // Catch:{ JSONException -> 0x0269 }
            int r1 = r1.zzrm()     // Catch:{ JSONException -> 0x0269 }
        L_0x0092:
            r18 = r1
            goto L_0x00a8
        L_0x0095:
            java.lang.String r5 = "landscape"
            boolean r1 = r5.equals(r1)     // Catch:{ JSONException -> 0x0269 }
            if (r1 == 0) goto L_0x00a6
            com.google.android.gms.internal.ads.zzakq r1 = com.google.android.gms.ads.internal.zzbv.zzem()     // Catch:{ JSONException -> 0x0269 }
            int r1 = r1.zzrl()     // Catch:{ JSONException -> 0x0269 }
            goto L_0x0092
        L_0x00a6:
            r18 = -1
        L_0x00a8:
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ JSONException -> 0x0269 }
            if (r1 == 0) goto L_0x00d9
            boolean r1 = android.text.TextUtils.isEmpty(r4)     // Catch:{ JSONException -> 0x0269 }
            if (r1 != 0) goto L_0x00d9
            com.google.android.gms.internal.ads.zzang r1 = r0.zzacr     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r3 = r1.zzcw     // Catch:{ JSONException -> 0x0269 }
            r5 = 0
            r6 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r1 = r55
            r2 = r54
            r14 = -1
            r7 = r20
            r8 = r21
            r9 = r22
            com.google.android.gms.internal.ads.zzaej r1 = com.google.android.gms.internal.ads.zzafn.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r2 = r1.zzbyq     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r3 = r1.zzceo     // Catch:{ JSONException -> 0x0269 }
            long r4 = r1.zzceu     // Catch:{ JSONException -> 0x0269 }
            r20 = r4
            r4 = r3
            goto L_0x00de
        L_0x00d9:
            r14 = -1
            r4 = r3
            r1 = r12
            r20 = -1
        L_0x00de:
            r3 = r2
            if (r4 != 0) goto L_0x00e7
            com.google.android.gms.internal.ads.zzaej r0 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ JSONException -> 0x0269 }
            r0.<init>(r15)     // Catch:{ JSONException -> 0x0269 }
            return r0
        L_0x00e7:
            java.lang.String r2 = "click_urls"
            org.json.JSONArray r2 = r11.optJSONArray(r2)     // Catch:{ JSONException -> 0x0269 }
            if (r1 != 0) goto L_0x00f1
            r5 = r12
            goto L_0x00f3
        L_0x00f1:
            java.util.List<java.lang.String> r5 = r1.zzbsn     // Catch:{ JSONException -> 0x0269 }
        L_0x00f3:
            if (r2 == 0) goto L_0x00fa
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r5)     // Catch:{ JSONException -> 0x0269 }
            r5 = r2
        L_0x00fa:
            java.lang.String r2 = "impression_urls"
            org.json.JSONArray r2 = r11.optJSONArray(r2)     // Catch:{ JSONException -> 0x0269 }
            if (r1 != 0) goto L_0x0104
            r6 = r12
            goto L_0x0106
        L_0x0104:
            java.util.List<java.lang.String> r6 = r1.zzbso     // Catch:{ JSONException -> 0x0269 }
        L_0x0106:
            if (r2 == 0) goto L_0x010d
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r6)     // Catch:{ JSONException -> 0x0269 }
            r6 = r2
        L_0x010d:
            java.lang.String r2 = "downloaded_impression_urls"
            org.json.JSONArray r2 = r11.optJSONArray(r2)     // Catch:{ JSONException -> 0x0269 }
            if (r1 != 0) goto L_0x0117
            r7 = r12
            goto L_0x0119
        L_0x0117:
            java.util.List<java.lang.String> r7 = r1.zzbsp     // Catch:{ JSONException -> 0x0269 }
        L_0x0119:
            if (r2 == 0) goto L_0x0122
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r7)     // Catch:{ JSONException -> 0x0269 }
            r48 = r2
            goto L_0x0124
        L_0x0122:
            r48 = r7
        L_0x0124:
            java.lang.String r2 = "manual_impression_urls"
            org.json.JSONArray r2 = r11.optJSONArray(r2)     // Catch:{ JSONException -> 0x0269 }
            if (r1 != 0) goto L_0x012e
            r7 = r12
            goto L_0x0130
        L_0x012e:
            java.util.List<java.lang.String> r7 = r1.zzces     // Catch:{ JSONException -> 0x0269 }
        L_0x0130:
            if (r2 == 0) goto L_0x0139
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r7)     // Catch:{ JSONException -> 0x0269 }
            r22 = r2
            goto L_0x013b
        L_0x0139:
            r22 = r7
        L_0x013b:
            if (r1 == 0) goto L_0x0151
            int r2 = r1.orientation     // Catch:{ JSONException -> 0x0269 }
            if (r2 == r14) goto L_0x0145
            int r2 = r1.orientation     // Catch:{ JSONException -> 0x0269 }
            r18 = r2
        L_0x0145:
            long r7 = r1.zzcep     // Catch:{ JSONException -> 0x0269 }
            r25 = 0
            int r2 = (r7 > r25 ? 1 : (r7 == r25 ? 0 : -1))
            if (r2 <= 0) goto L_0x0151
            long r1 = r1.zzcep     // Catch:{ JSONException -> 0x0269 }
            r7 = r1
            goto L_0x0153
        L_0x0151:
            r7 = r16
        L_0x0153:
            java.lang.String r1 = "active_view"
            java.lang.String r23 = r11.optString(r1)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "ad_is_javascript"
            boolean r25 = r11.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0269 }
            if (r25 == 0) goto L_0x016a
            java.lang.String r1 = "ad_passback_url"
            java.lang.String r1 = r11.optString(r1, r12)     // Catch:{ JSONException -> 0x0269 }
            r26 = r1
            goto L_0x016c
        L_0x016a:
            r26 = r12
        L_0x016c:
            java.lang.String r1 = "mediation"
            boolean r9 = r11.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "custom_render_allowed"
            boolean r27 = r11.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "content_url_opted_out"
            r2 = 1
            boolean r28 = r11.optBoolean(r1, r2)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "content_vertical_opted_out"
            boolean r43 = r11.optBoolean(r1, r2)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "prefetch"
            boolean r29 = r11.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "refresh_interval_milliseconds"
            r14 = r13
            r12 = -1
            long r16 = r11.optLong(r1, r12)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "mediation_config_cache_time_milliseconds"
            long r12 = r11.optLong(r1, r12)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "gws_query_id"
            java.lang.String r30 = r11.optString(r1, r10)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "height"
            java.lang.String r2 = "fluid"
            java.lang.String r2 = r11.optString(r2, r10)     // Catch:{ JSONException -> 0x0269 }
            boolean r31 = r1.equals(r2)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "native_express"
            boolean r32 = r11.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "video_start_urls"
            org.json.JSONArray r1 = r11.optJSONArray(r1)     // Catch:{ JSONException -> 0x0269 }
            r2 = 0
            java.util.List r33 = zza((org.json.JSONArray) r1, (java.util.List<java.lang.String>) r2)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "video_complete_urls"
            org.json.JSONArray r1 = r11.optJSONArray(r1)     // Catch:{ JSONException -> 0x0269 }
            java.util.List r34 = zza((org.json.JSONArray) r1, (java.util.List<java.lang.String>) r2)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "rewards"
            org.json.JSONArray r1 = r11.optJSONArray(r1)     // Catch:{ JSONException -> 0x0269 }
            com.google.android.gms.internal.ads.zzaig r35 = com.google.android.gms.internal.ads.zzaig.zza(r1)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "use_displayed_impression"
            boolean r36 = r11.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "auto_protection_configuration"
            org.json.JSONObject r1 = r11.optJSONObject(r1)     // Catch:{ JSONException -> 0x0269 }
            com.google.android.gms.internal.ads.zzael r37 = com.google.android.gms.internal.ads.zzael.zzl(r1)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "set_cookie"
            java.lang.String r38 = r11.optString(r1, r10)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "remote_ping_urls"
            org.json.JSONArray r1 = r11.optJSONArray(r1)     // Catch:{ JSONException -> 0x0269 }
            r2 = 0
            java.util.List r39 = zza((org.json.JSONArray) r1, (java.util.List<java.lang.String>) r2)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "safe_browsing"
            org.json.JSONObject r1 = r11.optJSONObject(r1)     // Catch:{ JSONException -> 0x0269 }
            com.google.android.gms.internal.ads.zzaiq r41 = com.google.android.gms.internal.ads.zzaiq.zzo(r1)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "render_in_browser"
            boolean r2 = r0.zzbss     // Catch:{ JSONException -> 0x0269 }
            boolean r44 = r11.optBoolean(r1, r2)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "custom_close_blocked"
            boolean r45 = r11.optBoolean(r1)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "enable_omid"
            boolean r47 = r11.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "omid_settings"
            r2 = 0
            java.lang.String r50 = r11.optString(r1, r2)     // Catch:{ JSONException -> 0x0269 }
            java.lang.String r1 = "disable_closable_area"
            boolean r49 = r11.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x0269 }
            com.google.android.gms.internal.ads.zzaej r51 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ JSONException -> 0x0269 }
            boolean r10 = r0.zzcdd     // Catch:{ JSONException -> 0x0269 }
            boolean r11 = r0.zzcdr     // Catch:{ JSONException -> 0x0269 }
            boolean r2 = r0.zzced     // Catch:{ JSONException -> 0x0269 }
            r46 = 0
            r1 = r51
            r52 = r2
            r2 = r55
            r0 = r10
            r53 = r11
            r10 = r12
            r12 = r22
            r22 = r14
            r13 = r16
            r15 = r18
            r16 = r22
            r17 = r20
            r20 = r25
            r21 = r26
            r22 = r23
            r23 = r27
            r25 = r0
            r26 = r28
            r27 = r29
            r28 = r30
            r29 = r31
            r30 = r32
            r31 = r35
            r32 = r33
            r33 = r34
            r34 = r36
            r35 = r37
            r36 = r53
            r37 = r38
            r38 = r39
            r39 = r44
            r44 = r52
            r1.<init>(r2, r3, r4, r5, r6, r7, r9, r10, r12, r13, r15, r16, r17, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50)     // Catch:{ JSONException -> 0x0269 }
            return r51
        L_0x0269:
            r0 = move-exception
            java.lang.String r1 = "Could not parse the inline ad response: "
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x027f
            java.lang.String r0 = r1.concat(r0)
            goto L_0x0284
        L_0x027f:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1)
        L_0x0284:
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)
            com.google.android.gms.internal.ads.zzaej r0 = new com.google.android.gms.internal.ads.zzaej
            r1 = 0
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafs.zza(android.content.Context, com.google.android.gms.internal.ads.zzaef, java.lang.String):com.google.android.gms.internal.ads.zzaej");
    }

    private static List<String> zza(JSONArray jSONArray, List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    /* JADX WARNING: Removed duplicated region for block: B:322:0x0807 A[Catch:{ JSONException -> 0x091d }] */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x0809 A[Catch:{ JSONException -> 0x091d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject zza(android.content.Context r23, com.google.android.gms.internal.ads.zzafl r24) {
        /*
            r1 = r24
            java.lang.String r2 = "web_view_count"
            com.google.android.gms.internal.ads.zzaef r3 = r1.zzcgs
            android.location.Location r4 = r1.zzaqe
            com.google.android.gms.internal.ads.zzaga r5 = r1.zzcgt
            android.os.Bundle r6 = r1.zzcdc
            org.json.JSONObject r7 = r1.zzcgu
            java.util.HashMap r9 = new java.util.HashMap     // Catch:{ JSONException -> 0x091d }
            r9.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "extra_caps"
            com.google.android.gms.internal.ads.zzna<java.lang.String> r11 = com.google.android.gms.internal.ads.zznk.zzbbk     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r12 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r11 = r12.zzd(r11)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r11)     // Catch:{ JSONException -> 0x091d }
            java.util.List<java.lang.String> r10 = r1.zzcdj     // Catch:{ JSONException -> 0x091d }
            int r10 = r10.size()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = ","
            if (r10 <= 0) goto L_0x0037
            java.lang.String r10 = "eid"
            java.util.List<java.lang.String> r12 = r1.zzcdj     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = android.text.TextUtils.join(r11, r12)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r12)     // Catch:{ JSONException -> 0x091d }
        L_0x0037:
            android.os.Bundle r10 = r3.zzccu     // Catch:{ JSONException -> 0x091d }
            if (r10 == 0) goto L_0x0042
            java.lang.String r10 = "ad_pos"
            android.os.Bundle r12 = r3.zzccu     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r12)     // Catch:{ JSONException -> 0x091d }
        L_0x0042:
            com.google.android.gms.internal.ads.zzjj r10 = r3.zzccv     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = com.google.android.gms.internal.ads.zzajw.zzqn()     // Catch:{ JSONException -> 0x091d }
            if (r12 == 0) goto L_0x004f
            java.lang.String r13 = "abf"
            r9.put(r13, r12)     // Catch:{ JSONException -> 0x091d }
        L_0x004f:
            long r12 = r10.zzapw     // Catch:{ JSONException -> 0x091d }
            r14 = -1
            int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r16 == 0) goto L_0x0069
            java.lang.String r12 = "cust_age"
            java.text.SimpleDateFormat r13 = zzcho     // Catch:{ JSONException -> 0x091d }
            java.util.Date r8 = new java.util.Date     // Catch:{ JSONException -> 0x091d }
            long r14 = r10.zzapw     // Catch:{ JSONException -> 0x091d }
            r8.<init>(r14)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = r13.format(r8)     // Catch:{ JSONException -> 0x091d }
            r9.put(r12, r8)     // Catch:{ JSONException -> 0x091d }
        L_0x0069:
            android.os.Bundle r8 = r10.extras     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x0074
            java.lang.String r8 = "extras"
            android.os.Bundle r12 = r10.extras     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
        L_0x0074:
            int r8 = r10.zzapx     // Catch:{ JSONException -> 0x091d }
            r12 = -1
            if (r8 == r12) goto L_0x0084
            java.lang.String r8 = "cust_gender"
            int r13 = r10.zzapx     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x091d }
        L_0x0084:
            java.util.List<java.lang.String> r8 = r10.zzapy     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x008f
            java.lang.String r8 = "kw"
            java.util.List<java.lang.String> r13 = r10.zzapy     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x091d }
        L_0x008f:
            int r8 = r10.zzaqa     // Catch:{ JSONException -> 0x091d }
            if (r8 == r12) goto L_0x009e
            java.lang.String r8 = "tag_for_child_directed_treatment"
            int r13 = r10.zzaqa     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x091d }
        L_0x009e:
            boolean r8 = r10.zzapz     // Catch:{ JSONException -> 0x091d }
            r13 = 1
            if (r8 == 0) goto L_0x00c4
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r8 = com.google.android.gms.internal.ads.zznk.zzbfp     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r14 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r8 = r14.zzd(r8)     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ JSONException -> 0x091d }
            boolean r8 = r8.booleanValue()     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x00bf
            java.lang.String r8 = "test_request"
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r13)     // Catch:{ JSONException -> 0x091d }
        L_0x00bb:
            r9.put(r8, r14)     // Catch:{ JSONException -> 0x091d }
            goto L_0x00c4
        L_0x00bf:
            java.lang.String r8 = "adtest"
            java.lang.String r14 = "on"
            goto L_0x00bb
        L_0x00c4:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x091d }
            r14 = 2
            if (r8 < r14) goto L_0x00e5
            boolean r8 = r10.zzaqb     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x00d6
            java.lang.String r8 = "d_imp_hdr"
            java.lang.Integer r15 = java.lang.Integer.valueOf(r13)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x00d6:
            java.lang.String r8 = r10.zzaqc     // Catch:{ JSONException -> 0x091d }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x091d }
            if (r8 != 0) goto L_0x00e5
            java.lang.String r8 = "ppid"
            java.lang.String r15 = r10.zzaqc     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x00e5:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x091d }
            r15 = 3
            if (r8 < r15) goto L_0x00f5
            java.lang.String r8 = r10.zzaqf     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x00f5
            java.lang.String r8 = "url"
            java.lang.String r15 = r10.zzaqf     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x00f5:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x091d }
            r15 = 5
            if (r8 < r15) goto L_0x011b
            android.os.Bundle r8 = r10.zzaqh     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x0105
            java.lang.String r8 = "custom_targeting"
            android.os.Bundle r15 = r10.zzaqh     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x0105:
            java.util.List<java.lang.String> r8 = r10.zzaqi     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x0110
            java.lang.String r8 = "category_exclusions"
            java.util.List<java.lang.String> r15 = r10.zzaqi     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x0110:
            java.lang.String r8 = r10.zzaqj     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x011b
            java.lang.String r8 = "request_agent"
            java.lang.String r15 = r10.zzaqj     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x011b:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x091d }
            r15 = 6
            if (r8 < r15) goto L_0x012b
            java.lang.String r8 = r10.zzaqk     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x012b
            java.lang.String r8 = "request_pkg"
            java.lang.String r15 = r10.zzaqk     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x012b:
            int r8 = r10.versionCode     // Catch:{ JSONException -> 0x091d }
            r15 = 7
            if (r8 < r15) goto L_0x013b
            java.lang.String r8 = "is_designed_for_families"
            boolean r10 = r10.zzaql     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r10)     // Catch:{ JSONException -> 0x091d }
        L_0x013b:
            com.google.android.gms.internal.ads.zzjn r8 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzjn[] r8 = r8.zzard     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "height"
            java.lang.String r15 = "fluid"
            java.lang.String r14 = "format"
            if (r8 != 0) goto L_0x0158
            com.google.android.gms.internal.ads.zzjn r8 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = r8.zzarb     // Catch:{ JSONException -> 0x091d }
            r9.put(r14, r8)     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzjn r8 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            boolean r8 = r8.zzarf     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x018d
            r9.put(r15, r10)     // Catch:{ JSONException -> 0x091d }
            goto L_0x018d
        L_0x0158:
            com.google.android.gms.internal.ads.zzjn r8 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzjn[] r8 = r8.zzard     // Catch:{ JSONException -> 0x091d }
            int r13 = r8.length     // Catch:{ JSONException -> 0x091d }
            r12 = 0
            r19 = 0
            r20 = 0
        L_0x0162:
            if (r12 >= r13) goto L_0x018d
            r21 = r13
            r13 = r8[r12]     // Catch:{ JSONException -> 0x091d }
            r22 = r8
            boolean r8 = r13.zzarf     // Catch:{ JSONException -> 0x091d }
            if (r8 != 0) goto L_0x0177
            if (r19 != 0) goto L_0x0177
            java.lang.String r8 = r13.zzarb     // Catch:{ JSONException -> 0x091d }
            r9.put(r14, r8)     // Catch:{ JSONException -> 0x091d }
            r19 = 1
        L_0x0177:
            boolean r8 = r13.zzarf     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x0182
            if (r20 != 0) goto L_0x0182
            r9.put(r15, r10)     // Catch:{ JSONException -> 0x091d }
            r20 = 1
        L_0x0182:
            if (r19 == 0) goto L_0x0186
            if (r20 != 0) goto L_0x018d
        L_0x0186:
            int r12 = r12 + 1
            r13 = r21
            r8 = r22
            goto L_0x0162
        L_0x018d:
            com.google.android.gms.internal.ads.zzjn r8 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            int r8 = r8.width     // Catch:{ JSONException -> 0x091d }
            r10 = -1
            if (r8 != r10) goto L_0x019b
            java.lang.String r8 = "smart_w"
            java.lang.String r10 = "full"
            r9.put(r8, r10)     // Catch:{ JSONException -> 0x091d }
        L_0x019b:
            com.google.android.gms.internal.ads.zzjn r8 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            int r8 = r8.height     // Catch:{ JSONException -> 0x091d }
            r10 = -2
            if (r8 != r10) goto L_0x01a9
            java.lang.String r8 = "smart_h"
            java.lang.String r12 = "auto"
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
        L_0x01a9:
            com.google.android.gms.internal.ads.zzjn r8 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzjn[] r8 = r8.zzard     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x021c
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x091d }
            r8.<init>()     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzjn r12 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzjn[] r12 = r12.zzard     // Catch:{ JSONException -> 0x091d }
            int r13 = r12.length     // Catch:{ JSONException -> 0x091d }
            r14 = 0
            r15 = 0
        L_0x01bb:
            java.lang.String r10 = "|"
            if (r15 >= r13) goto L_0x0204
            r20 = r13
            r13 = r12[r15]     // Catch:{ JSONException -> 0x091d }
            r21 = r12
            boolean r12 = r13.zzarf     // Catch:{ JSONException -> 0x091d }
            if (r12 == 0) goto L_0x01cb
            r14 = 1
            goto L_0x01fd
        L_0x01cb:
            int r12 = r8.length()     // Catch:{ JSONException -> 0x091d }
            if (r12 == 0) goto L_0x01d4
            r8.append(r10)     // Catch:{ JSONException -> 0x091d }
        L_0x01d4:
            int r10 = r13.width     // Catch:{ JSONException -> 0x091d }
            r12 = -1
            if (r10 != r12) goto L_0x01e1
            int r10 = r13.widthPixels     // Catch:{ JSONException -> 0x091d }
            float r10 = (float) r10     // Catch:{ JSONException -> 0x091d }
            float r12 = r5.zzagu     // Catch:{ JSONException -> 0x091d }
            float r10 = r10 / r12
            int r10 = (int) r10     // Catch:{ JSONException -> 0x091d }
            goto L_0x01e3
        L_0x01e1:
            int r10 = r13.width     // Catch:{ JSONException -> 0x091d }
        L_0x01e3:
            r8.append(r10)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "x"
            r8.append(r10)     // Catch:{ JSONException -> 0x091d }
            int r10 = r13.height     // Catch:{ JSONException -> 0x091d }
            r12 = -2
            if (r10 != r12) goto L_0x01f8
            int r10 = r13.heightPixels     // Catch:{ JSONException -> 0x091d }
            float r10 = (float) r10     // Catch:{ JSONException -> 0x091d }
            float r12 = r5.zzagu     // Catch:{ JSONException -> 0x091d }
            float r10 = r10 / r12
            int r10 = (int) r10     // Catch:{ JSONException -> 0x091d }
            goto L_0x01fa
        L_0x01f8:
            int r10 = r13.height     // Catch:{ JSONException -> 0x091d }
        L_0x01fa:
            r8.append(r10)     // Catch:{ JSONException -> 0x091d }
        L_0x01fd:
            int r15 = r15 + 1
            r13 = r20
            r12 = r21
            goto L_0x01bb
        L_0x0204:
            if (r14 == 0) goto L_0x0217
            int r12 = r8.length()     // Catch:{ JSONException -> 0x091d }
            if (r12 == 0) goto L_0x0211
            r12 = 0
            r8.insert(r12, r10)     // Catch:{ JSONException -> 0x091d }
            goto L_0x0212
        L_0x0211:
            r12 = 0
        L_0x0212:
            java.lang.String r10 = "320x50"
            r8.insert(r12, r10)     // Catch:{ JSONException -> 0x091d }
        L_0x0217:
            java.lang.String r10 = "sz"
            r9.put(r10, r8)     // Catch:{ JSONException -> 0x091d }
        L_0x021c:
            int r8 = r3.zzcdb     // Catch:{ JSONException -> 0x091d }
            r10 = 24
            if (r8 == 0) goto L_0x028c
            java.lang.String r8 = "native_version"
            int r12 = r3.zzcdb     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "native_templates"
            java.util.List<java.lang.String> r12 = r3.zzads     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "native_image_orientation"
            com.google.android.gms.internal.ads.zzpl r12 = r3.zzadj     // Catch:{ JSONException -> 0x091d }
            java.lang.String r13 = "any"
            if (r12 != 0) goto L_0x023d
            goto L_0x024f
        L_0x023d:
            int r12 = r12.zzbjo     // Catch:{ JSONException -> 0x091d }
            if (r12 == 0) goto L_0x024f
            r14 = 1
            if (r12 == r14) goto L_0x024d
            r13 = 2
            if (r12 == r13) goto L_0x024a
            java.lang.String r13 = "not_set"
            goto L_0x024f
        L_0x024a:
            java.lang.String r13 = "landscape"
            goto L_0x024f
        L_0x024d:
            java.lang.String r13 = "portrait"
        L_0x024f:
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x091d }
            java.util.List<java.lang.String> r8 = r3.zzcdk     // Catch:{ JSONException -> 0x091d }
            boolean r8 = r8.isEmpty()     // Catch:{ JSONException -> 0x091d }
            if (r8 != 0) goto L_0x0261
            java.lang.String r8 = "native_custom_templates"
            java.util.List<java.lang.String> r12 = r3.zzcdk     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
        L_0x0261:
            int r8 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            if (r8 < r10) goto L_0x0270
            java.lang.String r8 = "max_num_ads"
            int r12 = r3.zzceg     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
        L_0x0270:
            java.lang.String r8 = r3.zzcee     // Catch:{ JSONException -> 0x091d }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x091d }
            if (r8 != 0) goto L_0x028c
            java.lang.String r8 = "native_advanced_settings"
            org.json.JSONArray r12 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0285 }
            java.lang.String r13 = r3.zzcee     // Catch:{ JSONException -> 0x0285 }
            r12.<init>(r13)     // Catch:{ JSONException -> 0x0285 }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x0285 }
            goto L_0x028c
        L_0x0285:
            r0 = move-exception
            r8 = r0
            java.lang.String r12 = "Problem creating json from native advanced settings"
            com.google.android.gms.internal.ads.zzakb.zzc(r12, r8)     // Catch:{ JSONException -> 0x091d }
        L_0x028c:
            java.util.List<java.lang.Integer> r8 = r3.zzadn     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x02ca
            java.util.List<java.lang.Integer> r8 = r3.zzadn     // Catch:{ JSONException -> 0x091d }
            int r8 = r8.size()     // Catch:{ JSONException -> 0x091d }
            if (r8 <= 0) goto L_0x02ca
            java.util.List<java.lang.Integer> r8 = r3.zzadn     // Catch:{ JSONException -> 0x091d }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ JSONException -> 0x091d }
        L_0x029e:
            boolean r12 = r8.hasNext()     // Catch:{ JSONException -> 0x091d }
            if (r12 == 0) goto L_0x02ca
            java.lang.Object r12 = r8.next()     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r12 = (java.lang.Integer) r12     // Catch:{ JSONException -> 0x091d }
            int r13 = r12.intValue()     // Catch:{ JSONException -> 0x091d }
            r14 = 2
            if (r13 != r14) goto L_0x02bc
            java.lang.String r12 = "iba"
            r13 = 1
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r13)     // Catch:{ JSONException -> 0x091d }
        L_0x02b8:
            r9.put(r12, r14)     // Catch:{ JSONException -> 0x091d }
            goto L_0x029e
        L_0x02bc:
            int r12 = r12.intValue()     // Catch:{ JSONException -> 0x091d }
            r13 = 1
            if (r12 != r13) goto L_0x029e
            java.lang.String r12 = "ina"
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r13)     // Catch:{ JSONException -> 0x091d }
            goto L_0x02b8
        L_0x02ca:
            com.google.android.gms.internal.ads.zzjn r8 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            boolean r8 = r8.zzarg     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x02da
            java.lang.String r8 = "ene"
            r12 = 1
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r12)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x091d }
        L_0x02da:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r8 = com.google.android.gms.internal.ads.zznk.zzaxv     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r12 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r8 = r12.zzd(r8)     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ JSONException -> 0x091d }
            boolean r8 = r8.booleanValue()     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x02f6
            java.lang.String r8 = "xsrve"
            r12 = 1
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r12)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x091d }
        L_0x02f6:
            com.google.android.gms.internal.ads.zzlu r8 = r3.zzadl     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x0311
            java.lang.String r8 = "is_icon_ad"
            r12 = 1
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r12)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r13)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "icon_ad_expansion_behavior"
            com.google.android.gms.internal.ads.zzlu r12 = r3.zzadl     // Catch:{ JSONException -> 0x091d }
            int r12 = r12.zzasj     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
        L_0x0311:
            java.lang.String r8 = "slotname"
            java.lang.String r12 = r3.zzacp     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "pn"
            android.content.pm.ApplicationInfo r12 = r3.applicationInfo     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = r12.packageName     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
            android.content.pm.PackageInfo r8 = r3.zzccw     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x0332
            java.lang.String r8 = "vc"
            android.content.pm.PackageInfo r12 = r3.zzccw     // Catch:{ JSONException -> 0x091d }
            int r12 = r12.versionCode     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
        L_0x0332:
            java.lang.String r8 = "ms"
            java.lang.String r12 = r1.zzccx     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "seq_num"
            java.lang.String r12 = r3.zzccy     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "session_id"
            java.lang.String r12 = r3.zzccz     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "js"
            com.google.android.gms.internal.ads.zzang r12 = r3.zzacr     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = r12.zzcw     // Catch:{ JSONException -> 0x091d }
            r9.put(r8, r12)     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzagk r8 = r1.zzcgo     // Catch:{ JSONException -> 0x091d }
            android.os.Bundle r12 = r3.zzcdw     // Catch:{ JSONException -> 0x091d }
            android.os.Bundle r13 = r1.zzcgn     // Catch:{ JSONException -> 0x091d }
            java.lang.String r14 = "am"
            int r15 = r5.zzcjk     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r14 = "cog"
            boolean r15 = r5.zzcjl     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = zzv(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r14 = "coh"
            boolean r15 = r5.zzcjm     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = zzv(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r14 = r5.zzcjn     // Catch:{ JSONException -> 0x091d }
            boolean r14 = android.text.TextUtils.isEmpty(r14)     // Catch:{ JSONException -> 0x091d }
            if (r14 != 0) goto L_0x0386
            java.lang.String r14 = "carrier"
            java.lang.String r15 = r5.zzcjn     // Catch:{ JSONException -> 0x091d }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x0386:
            java.lang.String r14 = "gl"
            java.lang.String r15 = r5.zzcjo     // Catch:{ JSONException -> 0x091d }
            r9.put(r14, r15)     // Catch:{ JSONException -> 0x091d }
            boolean r14 = r5.zzcjp     // Catch:{ JSONException -> 0x091d }
            if (r14 == 0) goto L_0x039b
            java.lang.String r14 = "simulator"
            r15 = 1
            java.lang.Integer r10 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r14, r10)     // Catch:{ JSONException -> 0x091d }
        L_0x039b:
            boolean r10 = r5.zzcjq     // Catch:{ JSONException -> 0x091d }
            if (r10 == 0) goto L_0x03aa
            java.lang.String r10 = "is_sidewinder"
            r14 = 1
            java.lang.Integer r15 = java.lang.Integer.valueOf(r14)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
            goto L_0x03ab
        L_0x03aa:
            r14 = 1
        L_0x03ab:
            java.lang.String r10 = "ma"
            boolean r15 = r5.zzcjr     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = zzv(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "sp"
            boolean r15 = r5.zzcjs     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = zzv(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "hl"
            java.lang.String r15 = r5.zzcjt     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = r5.zzcju     // Catch:{ JSONException -> 0x091d }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ JSONException -> 0x091d }
            if (r10 != 0) goto L_0x03d7
            java.lang.String r10 = "mv"
            java.lang.String r15 = r5.zzcju     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x03d7:
            java.lang.String r10 = "muv"
            int r15 = r5.zzcjw     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
            int r10 = r5.zzcjx     // Catch:{ JSONException -> 0x091d }
            r15 = -2
            if (r10 == r15) goto L_0x03f2
            java.lang.String r10 = "cnt"
            int r15 = r5.zzcjx     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
        L_0x03f2:
            java.lang.String r10 = "gnt"
            int r15 = r5.zzcjy     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "pt"
            int r15 = r5.zzcjz     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "rm"
            int r15 = r5.zzcka     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "riv"
            int r15 = r5.zzckb     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ JSONException -> 0x091d }
            r9.put(r10, r15)     // Catch:{ JSONException -> 0x091d }
            android.os.Bundle r10 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r10.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r15 = "build_build"
            java.lang.String r14 = r5.zzckg     // Catch:{ JSONException -> 0x091d }
            r10.putString(r15, r14)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r14 = "build_device"
            java.lang.String r15 = r5.zzckh     // Catch:{ JSONException -> 0x091d }
            r10.putString(r14, r15)     // Catch:{ JSONException -> 0x091d }
            android.os.Bundle r14 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r14.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r15 = "is_charging"
            r19 = r11
            boolean r11 = r5.zzckd     // Catch:{ JSONException -> 0x091d }
            r14.putBoolean(r15, r11)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "battery_level"
            r15 = r6
            r21 = r7
            double r6 = r5.zzckc     // Catch:{ JSONException -> 0x091d }
            r14.putDouble(r11, r6)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = "battery"
            r10.putBundle(r6, r14)     // Catch:{ JSONException -> 0x091d }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r6.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r7 = "active_network_state"
            int r11 = r5.zzckf     // Catch:{ JSONException -> 0x091d }
            r6.putInt(r7, r11)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r7 = "active_network_metered"
            boolean r11 = r5.zzcke     // Catch:{ JSONException -> 0x091d }
            r6.putBoolean(r7, r11)     // Catch:{ JSONException -> 0x091d }
            if (r8 == 0) goto L_0x0485
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r7.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "predicted_latency_micros"
            int r14 = r8.zzckq     // Catch:{ JSONException -> 0x091d }
            r7.putInt(r11, r14)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "predicted_down_throughput_bps"
            r22 = r15
            long r14 = r8.zzckr     // Catch:{ JSONException -> 0x091d }
            r7.putLong(r11, r14)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "predicted_up_throughput_bps"
            long r14 = r8.zzcks     // Catch:{ JSONException -> 0x091d }
            r7.putLong(r11, r14)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "predictions"
            r6.putBundle(r8, r7)     // Catch:{ JSONException -> 0x091d }
            goto L_0x0487
        L_0x0485:
            r22 = r15
        L_0x0487:
            java.lang.String r7 = "network"
            r10.putBundle(r7, r6)     // Catch:{ JSONException -> 0x091d }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r6.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r7 = "is_browser_custom_tabs_capable"
            boolean r8 = r5.zzcki     // Catch:{ JSONException -> 0x091d }
            r6.putBoolean(r7, r8)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r7 = "browser"
            r10.putBundle(r7, r6)     // Catch:{ JSONException -> 0x091d }
            if (r12 == 0) goto L_0x0552
            java.lang.String r6 = "android_mem_info"
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r7.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "runtime_free"
            java.lang.String r11 = "runtime_free_memory"
            r14 = -1
            long r17 = r12.getLong(r11, r14)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = java.lang.Long.toString(r17)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r8, r11)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "runtime_max"
            java.lang.String r11 = "runtime_max_memory"
            long r17 = r12.getLong(r11, r14)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = java.lang.Long.toString(r17)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r8, r11)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r8 = "runtime_total"
            java.lang.String r11 = "runtime_total_memory"
            long r14 = r12.getLong(r11, r14)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = java.lang.Long.toString(r14)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r8, r11)     // Catch:{ JSONException -> 0x091d }
            r8 = 0
            int r11 = r12.getInt(r2, r8)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = java.lang.Integer.toString(r11)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r2, r11)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r2 = "debug_memory_info"
            android.os.Parcelable r2 = r12.getParcelable(r2)     // Catch:{ JSONException -> 0x091d }
            android.os.Debug$MemoryInfo r2 = (android.os.Debug.MemoryInfo) r2     // Catch:{ JSONException -> 0x091d }
            if (r2 == 0) goto L_0x054e
            java.lang.String r11 = "debug_info_dalvik_private_dirty"
            int r12 = r2.dalvikPrivateDirty     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "debug_info_dalvik_pss"
            int r12 = r2.dalvikPss     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "debug_info_dalvik_shared_dirty"
            int r12 = r2.dalvikSharedDirty     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "debug_info_native_private_dirty"
            int r12 = r2.nativePrivateDirty     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "debug_info_native_pss"
            int r12 = r2.nativePss     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "debug_info_native_shared_dirty"
            int r12 = r2.nativeSharedDirty     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "debug_info_other_private_dirty"
            int r12 = r2.otherPrivateDirty     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "debug_info_other_pss"
            int r12 = r2.otherPss     // Catch:{ JSONException -> 0x091d }
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r11, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r11 = "debug_info_other_shared_dirty"
            int r2 = r2.otherSharedDirty     // Catch:{ JSONException -> 0x091d }
            java.lang.String r2 = java.lang.Integer.toString(r2)     // Catch:{ JSONException -> 0x091d }
            r7.putString(r11, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x054e:
            r10.putBundle(r6, r7)     // Catch:{ JSONException -> 0x091d }
            goto L_0x0553
        L_0x0552:
            r8 = 0
        L_0x0553:
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r2.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = "parental_controls"
            r2.putBundle(r6, r13)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = r5.zzcjv     // Catch:{ JSONException -> 0x091d }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ JSONException -> 0x091d }
            if (r6 != 0) goto L_0x056c
            java.lang.String r6 = "package_version"
            java.lang.String r7 = r5.zzcjv     // Catch:{ JSONException -> 0x091d }
            r2.putString(r6, r7)     // Catch:{ JSONException -> 0x091d }
        L_0x056c:
            java.lang.String r6 = "play_store"
            r10.putBundle(r6, r2)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r2 = "device"
            r9.put(r2, r10)     // Catch:{ JSONException -> 0x091d }
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r2.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = "doritos"
            java.lang.String r7 = r1.zzcgp     // Catch:{ JSONException -> 0x091d }
            r2.putString(r6, r7)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = "doritos_v2"
            java.lang.String r7 = r1.zzcgq     // Catch:{ JSONException -> 0x091d }
            r2.putString(r6, r7)     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r6 = com.google.android.gms.internal.ads.zznk.zzayj     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r7 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r6 = r7.zzd(r6)     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ JSONException -> 0x091d }
            boolean r6 = r6.booleanValue()     // Catch:{ JSONException -> 0x091d }
            if (r6 == 0) goto L_0x05d7
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r6 = r1.zzcgr     // Catch:{ JSONException -> 0x091d }
            if (r6 == 0) goto L_0x05ac
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r6 = r1.zzcgr     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = r6.getId()     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r7 = r1.zzcgr     // Catch:{ JSONException -> 0x091d }
            boolean r12 = r7.isLimitAdTrackingEnabled()     // Catch:{ JSONException -> 0x091d }
            goto L_0x05ae
        L_0x05ac:
            r6 = 0
            r12 = 0
        L_0x05ae:
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ JSONException -> 0x091d }
            if (r7 != 0) goto L_0x05c6
            java.lang.String r7 = "rdid"
            r2.putString(r7, r6)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = "is_lat"
            r2.putBoolean(r6, r12)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = "idtype"
            java.lang.String r7 = "adid"
        L_0x05c2:
            r2.putString(r6, r7)     // Catch:{ JSONException -> 0x091d }
            goto L_0x05d7
        L_0x05c6:
            com.google.android.gms.internal.ads.zzkb.zzif()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = com.google.android.gms.internal.ads.zzamu.zzbd(r23)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r7 = "pdid"
            r2.putString(r7, r6)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r6 = "pdidtype"
            java.lang.String r7 = "ssaid"
            goto L_0x05c2
        L_0x05d7:
            java.lang.String r6 = "pii"
            r9.put(r6, r2)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r2 = "platform"
            java.lang.String r6 = android.os.Build.MANUFACTURER     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r6)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r2 = "submodel"
            java.lang.String r6 = android.os.Build.MODEL     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r6)     // Catch:{ JSONException -> 0x091d }
            if (r4 == 0) goto L_0x05f0
            zza((java.util.HashMap<java.lang.String, java.lang.Object>) r9, (android.location.Location) r4)     // Catch:{ JSONException -> 0x091d }
            goto L_0x0604
        L_0x05f0:
            com.google.android.gms.internal.ads.zzjj r2 = r3.zzccv     // Catch:{ JSONException -> 0x091d }
            int r2 = r2.versionCode     // Catch:{ JSONException -> 0x091d }
            r4 = 2
            if (r2 < r4) goto L_0x0604
            com.google.android.gms.internal.ads.zzjj r2 = r3.zzccv     // Catch:{ JSONException -> 0x091d }
            android.location.Location r2 = r2.zzaqe     // Catch:{ JSONException -> 0x091d }
            if (r2 == 0) goto L_0x0604
            com.google.android.gms.internal.ads.zzjj r2 = r3.zzccv     // Catch:{ JSONException -> 0x091d }
            android.location.Location r2 = r2.zzaqe     // Catch:{ JSONException -> 0x091d }
            zza((java.util.HashMap<java.lang.String, java.lang.Object>) r9, (android.location.Location) r2)     // Catch:{ JSONException -> 0x091d }
        L_0x0604:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r4 = 2
            if (r2 < r4) goto L_0x0610
            java.lang.String r2 = "quality_signals"
            android.os.Bundle r4 = r3.zzcda     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x091d }
        L_0x0610:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r4 = 4
            if (r2 < r4) goto L_0x0624
            boolean r2 = r3.zzcdd     // Catch:{ JSONException -> 0x091d }
            if (r2 == 0) goto L_0x0624
            java.lang.String r2 = "forceHttps"
            boolean r4 = r3.zzcdd     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x091d }
        L_0x0624:
            if (r22 == 0) goto L_0x062d
            java.lang.String r2 = "content_info"
            r4 = r22
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x091d }
        L_0x062d:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            java.lang.String r4 = "sw"
            java.lang.String r6 = "sh"
            java.lang.String r7 = "u_sd"
            r10 = 5
            if (r2 < r10) goto L_0x0654
            float r2 = r3.zzagu     // Catch:{ JSONException -> 0x091d }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ JSONException -> 0x091d }
            r9.put(r7, r2)     // Catch:{ JSONException -> 0x091d }
            int r2 = r3.zzcdf     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ JSONException -> 0x091d }
            r9.put(r6, r2)     // Catch:{ JSONException -> 0x091d }
            int r2 = r3.zzcde     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ JSONException -> 0x091d }
        L_0x0650:
            r9.put(r4, r2)     // Catch:{ JSONException -> 0x091d }
            goto L_0x066d
        L_0x0654:
            float r2 = r5.zzagu     // Catch:{ JSONException -> 0x091d }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ JSONException -> 0x091d }
            r9.put(r7, r2)     // Catch:{ JSONException -> 0x091d }
            int r2 = r5.zzcdf     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ JSONException -> 0x091d }
            r9.put(r6, r2)     // Catch:{ JSONException -> 0x091d }
            int r2 = r5.zzcde     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ JSONException -> 0x091d }
            goto L_0x0650
        L_0x066d:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r4 = 6
            if (r2 < r4) goto L_0x0699
            java.lang.String r2 = r3.zzcdg     // Catch:{ JSONException -> 0x091d }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ JSONException -> 0x091d }
            if (r2 != 0) goto L_0x068e
            java.lang.String r2 = "view_hierarchy"
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0687 }
            java.lang.String r5 = r3.zzcdg     // Catch:{ JSONException -> 0x0687 }
            r4.<init>(r5)     // Catch:{ JSONException -> 0x0687 }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x0687 }
            goto L_0x068e
        L_0x0687:
            r0 = move-exception
            r2 = r0
            java.lang.String r4 = "Problem serializing view hierarchy to JSON"
            com.google.android.gms.internal.ads.zzakb.zzc(r4, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x068e:
            java.lang.String r2 = "correlation_id"
            long r4 = r3.zzcdh     // Catch:{ JSONException -> 0x091d }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x091d }
        L_0x0699:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r4 = 7
            if (r2 < r4) goto L_0x06a5
            java.lang.String r2 = "request_id"
            java.lang.String r4 = r3.zzcdi     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x091d }
        L_0x06a5:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r4 = 12
            if (r2 < r4) goto L_0x06ba
            java.lang.String r2 = r3.zzcdm     // Catch:{ JSONException -> 0x091d }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ JSONException -> 0x091d }
            if (r2 != 0) goto L_0x06ba
            java.lang.String r2 = "anchor"
            java.lang.String r4 = r3.zzcdm     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x091d }
        L_0x06ba:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r4 = 13
            if (r2 < r4) goto L_0x06cb
            java.lang.String r2 = "android_app_volume"
            float r4 = r3.zzcdn     // Catch:{ JSONException -> 0x091d }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x091d }
        L_0x06cb:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r4 = 18
            if (r2 < r4) goto L_0x06dc
            java.lang.String r2 = "android_app_muted"
            boolean r5 = r3.zzcdt     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x091d }
        L_0x06dc:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r5 = 14
            if (r2 < r5) goto L_0x06f1
            int r2 = r3.zzcdo     // Catch:{ JSONException -> 0x091d }
            if (r2 <= 0) goto L_0x06f1
            java.lang.String r2 = "target_api"
            int r5 = r3.zzcdo     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x091d }
        L_0x06f1:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r5 = 15
            if (r2 < r5) goto L_0x0709
            java.lang.String r2 = "scroll_index"
            int r5 = r3.zzcdp     // Catch:{ JSONException -> 0x091d }
            r6 = -1
            if (r5 != r6) goto L_0x0700
            r12 = -1
            goto L_0x0702
        L_0x0700:
            int r12 = r3.zzcdp     // Catch:{ JSONException -> 0x091d }
        L_0x0702:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x091d }
        L_0x0709:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r5 = 16
            if (r2 < r5) goto L_0x071a
            java.lang.String r2 = "_activity_context"
            boolean r5 = r3.zzcdq     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x091d }
        L_0x071a:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            if (r2 < r4) goto L_0x0745
            java.lang.String r2 = r3.zzcdu     // Catch:{ JSONException -> 0x091d }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ JSONException -> 0x091d }
            if (r2 != 0) goto L_0x073a
            java.lang.String r2 = "app_settings"
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0733 }
            java.lang.String r6 = r3.zzcdu     // Catch:{ JSONException -> 0x0733 }
            r5.<init>(r6)     // Catch:{ JSONException -> 0x0733 }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x0733 }
            goto L_0x073a
        L_0x0733:
            r0 = move-exception
            r2 = r0
            java.lang.String r5 = "Problem creating json from app settings"
            com.google.android.gms.internal.ads.zzakb.zzc(r5, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x073a:
            java.lang.String r2 = "render_in_browser"
            boolean r5 = r3.zzbss     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r5)     // Catch:{ JSONException -> 0x091d }
        L_0x0745:
            int r2 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            if (r2 < r4) goto L_0x0754
            java.lang.String r2 = "android_num_video_cache_tasks"
            int r4 = r3.zzcdv     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ JSONException -> 0x091d }
            r9.put(r2, r4)     // Catch:{ JSONException -> 0x091d }
        L_0x0754:
            com.google.android.gms.internal.ads.zzang r2 = r3.zzacr     // Catch:{ JSONException -> 0x091d }
            boolean r4 = r3.zzceh     // Catch:{ JSONException -> 0x091d }
            boolean r1 = r1.zzcgv     // Catch:{ JSONException -> 0x091d }
            boolean r5 = r3.zzcej     // Catch:{ JSONException -> 0x091d }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r6.<init>()     // Catch:{ JSONException -> 0x091d }
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r7.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "cl"
            java.lang.String r11 = "193400285"
            r7.putString(r10, r11)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "rapid_rc"
            java.lang.String r11 = "dev"
            r7.putString(r10, r11)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "rapid_rollup"
            java.lang.String r11 = "HEAD"
            r7.putString(r10, r11)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = "build_meta"
            r6.putBundle(r10, r7)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r7 = "mf"
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r10 = com.google.android.gms.internal.ads.zznk.zzbbm     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r11 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r10 = r11.zzd(r10)     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ JSONException -> 0x091d }
            boolean r10 = r10.booleanValue()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r10 = java.lang.Boolean.toString(r10)     // Catch:{ JSONException -> 0x091d }
            r6.putString(r7, r10)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r7 = "instant_app"
            r6.putBoolean(r7, r4)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r4 = "lite"
            boolean r2 = r2.zzcvh     // Catch:{ JSONException -> 0x091d }
            r6.putBoolean(r4, r2)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r2 = "local_service"
            r6.putBoolean(r2, r1)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r1 = "is_privileged_process"
            r6.putBoolean(r1, r5)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r1 = "sdk_env"
            r9.put(r1, r6)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r1 = "cache_state"
            r2 = r21
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
            int r1 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r2 = 19
            if (r1 < r2) goto L_0x07c8
            java.lang.String r1 = "gct"
            java.lang.String r2 = r3.zzcdx     // Catch:{ JSONException -> 0x091d }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x07c8:
            int r1 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r2 = 21
            if (r1 < r2) goto L_0x07d9
            boolean r1 = r3.zzcdy     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x07d9
            java.lang.String r1 = "de"
            java.lang.String r2 = "1"
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x07d9:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zznk.zzayy     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ JSONException -> 0x091d }
            boolean r1 = r1.booleanValue()     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x081d
            com.google.android.gms.internal.ads.zzjn r1 = r3.zzacv     // Catch:{ JSONException -> 0x091d }
            java.lang.String r1 = r1.zzarb     // Catch:{ JSONException -> 0x091d }
            java.lang.String r2 = "interstitial_mb"
            boolean r2 = r1.equals(r2)     // Catch:{ JSONException -> 0x091d }
            if (r2 != 0) goto L_0x0802
            java.lang.String r2 = "reward_mb"
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x0800
            goto L_0x0802
        L_0x0800:
            r12 = 0
            goto L_0x0803
        L_0x0802:
            r12 = 1
        L_0x0803:
            android.os.Bundle r1 = r3.zzcdz     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x0809
            r13 = 1
            goto L_0x080a
        L_0x0809:
            r13 = 0
        L_0x080a:
            if (r12 == 0) goto L_0x081d
            if (r13 == 0) goto L_0x081d
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ JSONException -> 0x091d }
            r2.<init>()     // Catch:{ JSONException -> 0x091d }
            java.lang.String r4 = "interstitial_pool"
            r2.putBundle(r4, r1)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r1 = "counters"
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x081d:
            java.lang.String r1 = r3.zzcea     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x0828
            java.lang.String r1 = "gmp_app_id"
            java.lang.String r2 = r3.zzcea     // Catch:{ JSONException -> 0x091d }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x0828:
            java.lang.String r1 = r3.zzceb     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x084b
            java.lang.String r1 = "TIME_OUT"
            java.lang.String r2 = r3.zzceb     // Catch:{ JSONException -> 0x091d }
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x0846
            java.lang.String r1 = "sai_timeout"
            com.google.android.gms.internal.ads.zzna<java.lang.Long> r2 = com.google.android.gms.internal.ads.zznk.zzaxt     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r4 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r2 = r4.zzd(r2)     // Catch:{ JSONException -> 0x091d }
        L_0x0842:
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
            goto L_0x0850
        L_0x0846:
            java.lang.String r1 = "fbs_aiid"
            java.lang.String r2 = r3.zzceb     // Catch:{ JSONException -> 0x091d }
            goto L_0x0842
        L_0x084b:
            java.lang.String r1 = "fbs_aiid"
            java.lang.String r2 = ""
            goto L_0x0842
        L_0x0850:
            java.lang.String r1 = r3.zzcec     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x085b
            java.lang.String r1 = "fbs_aeid"
            java.lang.String r2 = r3.zzcec     // Catch:{ JSONException -> 0x091d }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x085b:
            int r1 = r3.versionCode     // Catch:{ JSONException -> 0x091d }
            r2 = 24
            if (r1 < r2) goto L_0x086c
            java.lang.String r1 = "disable_ml"
            boolean r2 = r3.zzcei     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ JSONException -> 0x091d }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x086c:
            com.google.android.gms.internal.ads.zzna<java.lang.String> r1 = com.google.android.gms.internal.ads.zznk.zzavo     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x08b4
            boolean r2 = r1.isEmpty()     // Catch:{ JSONException -> 0x091d }
            if (r2 != 0) goto L_0x08b4
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzna<java.lang.Integer> r4 = com.google.android.gms.internal.ads.zznk.zzavp     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r5 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r4 = r5.zzd(r4)     // Catch:{ JSONException -> 0x091d }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ JSONException -> 0x091d }
            int r4 = r4.intValue()     // Catch:{ JSONException -> 0x091d }
            if (r2 < r4) goto L_0x08b4
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ JSONException -> 0x091d }
            r2.<init>()     // Catch:{ JSONException -> 0x091d }
            r4 = r19
            java.lang.String[] r1 = r1.split(r4)     // Catch:{ JSONException -> 0x091d }
            int r4 = r1.length     // Catch:{ JSONException -> 0x091d }
            r13 = 0
        L_0x08a1:
            if (r13 >= r4) goto L_0x08af
            r5 = r1[r13]     // Catch:{ JSONException -> 0x091d }
            java.util.List r6 = com.google.android.gms.internal.ads.zzams.zzdd(r5)     // Catch:{ JSONException -> 0x091d }
            r2.put(r5, r6)     // Catch:{ JSONException -> 0x091d }
            int r13 = r13 + 1
            goto L_0x08a1
        L_0x08af:
            java.lang.String r1 = "video_decoders"
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x08b4:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zznk.zzbet     // Catch:{ JSONException -> 0x091d }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ JSONException -> 0x091d }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ JSONException -> 0x091d }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ JSONException -> 0x091d }
            boolean r1 = r1.booleanValue()     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x08d5
            java.lang.String r1 = "omid_v"
            com.google.android.gms.internal.ads.zzaan r2 = com.google.android.gms.ads.internal.zzbv.zzfa()     // Catch:{ JSONException -> 0x091d }
            r4 = r23
            java.lang.String r2 = r2.getVersion(r4)     // Catch:{ JSONException -> 0x091d }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x08d5:
            java.util.ArrayList<java.lang.String> r1 = r3.zzcek     // Catch:{ JSONException -> 0x091d }
            if (r1 == 0) goto L_0x08e8
            java.util.ArrayList<java.lang.String> r1 = r3.zzcek     // Catch:{ JSONException -> 0x091d }
            boolean r1 = r1.isEmpty()     // Catch:{ JSONException -> 0x091d }
            if (r1 != 0) goto L_0x08e8
            java.lang.String r1 = "android_permissions"
            java.util.ArrayList<java.lang.String> r2 = r3.zzcek     // Catch:{ JSONException -> 0x091d }
            r9.put(r1, r2)     // Catch:{ JSONException -> 0x091d }
        L_0x08e8:
            r1 = 2
            boolean r2 = com.google.android.gms.internal.ads.zzakb.isLoggable(r1)     // Catch:{ JSONException -> 0x091d }
            if (r2 == 0) goto L_0x0914
            com.google.android.gms.internal.ads.zzakk r2 = com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ JSONException -> 0x091d }
            org.json.JSONObject r2 = r2.zzn(r9)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r1 = r2.toString(r1)     // Catch:{ JSONException -> 0x091d }
            java.lang.String r2 = "Ad Request JSON: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ JSONException -> 0x091d }
            int r3 = r1.length()     // Catch:{ JSONException -> 0x091d }
            if (r3 == 0) goto L_0x090c
            java.lang.String r1 = r2.concat(r1)     // Catch:{ JSONException -> 0x091d }
            goto L_0x0911
        L_0x090c:
            java.lang.String r1 = new java.lang.String     // Catch:{ JSONException -> 0x091d }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x091d }
        L_0x0911:
            com.google.android.gms.internal.ads.zzakb.v(r1)     // Catch:{ JSONException -> 0x091d }
        L_0x0914:
            com.google.android.gms.internal.ads.zzakk r1 = com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ JSONException -> 0x091d }
            org.json.JSONObject r1 = r1.zzn(r9)     // Catch:{ JSONException -> 0x091d }
            return r1
        L_0x091d:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "Problem serializing ad request to JSON: "
            java.lang.String r1 = r1.getMessage()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r3 = r1.length()
            if (r3 == 0) goto L_0x0934
            java.lang.String r1 = r2.concat(r1)
            goto L_0x0939
        L_0x0934:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
        L_0x0939:
            com.google.android.gms.internal.ads.zzakb.zzdk(r1)
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafs.zza(android.content.Context, com.google.android.gms.internal.ads.zzafl):org.json.JSONObject");
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put(i.fC, valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put(d.fl, valueOf2);
        hashMap.put("uule", hashMap2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0160  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject zzb(com.google.android.gms.internal.ads.zzaej r7) throws org.json.JSONException {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = r7.zzbyq
            if (r1 == 0) goto L_0x0010
            java.lang.String r1 = r7.zzbyq
            java.lang.String r2 = "ad_base_url"
            r0.put(r2, r1)
        L_0x0010:
            java.lang.String r1 = r7.zzcet
            if (r1 == 0) goto L_0x001b
            java.lang.String r1 = r7.zzcet
            java.lang.String r2 = "ad_size"
            r0.put(r2, r1)
        L_0x001b:
            boolean r1 = r7.zzare
            java.lang.String r2 = "native"
            r0.put(r2, r1)
            boolean r1 = r7.zzare
            if (r1 == 0) goto L_0x002b
            java.lang.String r1 = r7.zzceo
            java.lang.String r2 = "ad_json"
            goto L_0x002f
        L_0x002b:
            java.lang.String r1 = r7.zzceo
            java.lang.String r2 = "ad_html"
        L_0x002f:
            r0.put(r2, r1)
            java.lang.String r1 = r7.zzcev
            if (r1 == 0) goto L_0x003d
            java.lang.String r1 = r7.zzcev
            java.lang.String r2 = "debug_dialog"
            r0.put(r2, r1)
        L_0x003d:
            java.lang.String r1 = r7.zzcfl
            if (r1 == 0) goto L_0x0048
            java.lang.String r1 = r7.zzcfl
            java.lang.String r2 = "debug_signals"
            r0.put(r2, r1)
        L_0x0048:
            long r1 = r7.zzcep
            r3 = -1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0061
            long r1 = r7.zzcep
            double r1 = (double) r1
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            java.lang.Double.isNaN(r1)
            double r1 = r1 / r5
            java.lang.String r5 = "interstitial_timeout"
            r0.put(r5, r1)
        L_0x0061:
            int r1 = r7.orientation
            com.google.android.gms.internal.ads.zzakq r2 = com.google.android.gms.ads.internal.zzbv.zzem()
            int r2 = r2.zzrm()
            java.lang.String r5 = "orientation"
            if (r1 != r2) goto L_0x0075
            java.lang.String r1 = "portrait"
        L_0x0071:
            r0.put(r5, r1)
            goto L_0x0084
        L_0x0075:
            int r1 = r7.orientation
            com.google.android.gms.internal.ads.zzakq r2 = com.google.android.gms.ads.internal.zzbv.zzem()
            int r2 = r2.zzrl()
            if (r1 != r2) goto L_0x0084
            java.lang.String r1 = "landscape"
            goto L_0x0071
        L_0x0084:
            java.util.List<java.lang.String> r1 = r7.zzbsn
            if (r1 == 0) goto L_0x0093
            java.util.List<java.lang.String> r1 = r7.zzbsn
            org.json.JSONArray r1 = zzm(r1)
            java.lang.String r2 = "click_urls"
            r0.put(r2, r1)
        L_0x0093:
            java.util.List<java.lang.String> r1 = r7.zzbso
            if (r1 == 0) goto L_0x00a2
            java.util.List<java.lang.String> r1 = r7.zzbso
            org.json.JSONArray r1 = zzm(r1)
            java.lang.String r2 = "impression_urls"
            r0.put(r2, r1)
        L_0x00a2:
            java.util.List<java.lang.String> r1 = r7.zzbsp
            if (r1 == 0) goto L_0x00b1
            java.util.List<java.lang.String> r1 = r7.zzbsp
            org.json.JSONArray r1 = zzm(r1)
            java.lang.String r2 = "downloaded_impression_urls"
            r0.put(r2, r1)
        L_0x00b1:
            java.util.List<java.lang.String> r1 = r7.zzces
            if (r1 == 0) goto L_0x00c0
            java.util.List<java.lang.String> r1 = r7.zzces
            org.json.JSONArray r1 = zzm(r1)
            java.lang.String r2 = "manual_impression_urls"
            r0.put(r2, r1)
        L_0x00c0:
            java.lang.String r1 = r7.zzcey
            if (r1 == 0) goto L_0x00cb
            java.lang.String r1 = r7.zzcey
            java.lang.String r2 = "active_view"
            r0.put(r2, r1)
        L_0x00cb:
            boolean r1 = r7.zzcew
            java.lang.String r2 = "ad_is_javascript"
            r0.put(r2, r1)
            java.lang.String r1 = r7.zzcex
            if (r1 == 0) goto L_0x00dd
            java.lang.String r1 = r7.zzcex
            java.lang.String r2 = "ad_passback_url"
            r0.put(r2, r1)
        L_0x00dd:
            boolean r1 = r7.zzceq
            java.lang.String r2 = "mediation"
            r0.put(r2, r1)
            boolean r1 = r7.zzcez
            java.lang.String r2 = "custom_render_allowed"
            r0.put(r2, r1)
            boolean r1 = r7.zzcfa
            java.lang.String r2 = "content_url_opted_out"
            r0.put(r2, r1)
            boolean r1 = r7.zzcfm
            java.lang.String r2 = "content_vertical_opted_out"
            r0.put(r2, r1)
            boolean r1 = r7.zzcfb
            java.lang.String r2 = "prefetch"
            r0.put(r2, r1)
            long r1 = r7.zzbsu
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x010d
            long r1 = r7.zzbsu
            java.lang.String r5 = "refresh_interval_milliseconds"
            r0.put(r5, r1)
        L_0x010d:
            long r1 = r7.zzcer
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x011a
            long r1 = r7.zzcer
            java.lang.String r3 = "mediation_config_cache_time_milliseconds"
            r0.put(r3, r1)
        L_0x011a:
            java.lang.String r1 = r7.zzamj
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0129
            java.lang.String r1 = r7.zzamj
            java.lang.String r2 = "gws_query_id"
            r0.put(r2, r1)
        L_0x0129:
            boolean r1 = r7.zzarf
            if (r1 == 0) goto L_0x0130
            java.lang.String r1 = "height"
            goto L_0x0132
        L_0x0130:
            java.lang.String r1 = ""
        L_0x0132:
            java.lang.String r2 = "fluid"
            r0.put(r2, r1)
            boolean r1 = r7.zzarg
            java.lang.String r2 = "native_express"
            r0.put(r2, r1)
            java.util.List<java.lang.String> r1 = r7.zzcff
            if (r1 == 0) goto L_0x014d
            java.util.List<java.lang.String> r1 = r7.zzcff
            org.json.JSONArray r1 = zzm(r1)
            java.lang.String r2 = "video_start_urls"
            r0.put(r2, r1)
        L_0x014d:
            java.util.List<java.lang.String> r1 = r7.zzcfg
            if (r1 == 0) goto L_0x015c
            java.util.List<java.lang.String> r1 = r7.zzcfg
            org.json.JSONArray r1 = zzm(r1)
            java.lang.String r2 = "video_complete_urls"
            r0.put(r2, r1)
        L_0x015c:
            com.google.android.gms.internal.ads.zzaig r1 = r7.zzcfe
            if (r1 == 0) goto L_0x0182
            com.google.android.gms.internal.ads.zzaig r1 = r7.zzcfe
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r3 = r1.type
            java.lang.String r4 = "rb_type"
            r2.put(r4, r3)
            int r1 = r1.zzcmk
            java.lang.String r3 = "rb_amount"
            r2.put(r3, r1)
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>()
            r1.put(r2)
            java.lang.String r2 = "rewards"
            r0.put(r2, r1)
        L_0x0182:
            boolean r1 = r7.zzcfh
            java.lang.String r2 = "use_displayed_impression"
            r0.put(r2, r1)
            com.google.android.gms.internal.ads.zzael r1 = r7.zzcfi
            java.lang.String r2 = "auto_protection_configuration"
            r0.put(r2, r1)
            boolean r1 = r7.zzbss
            java.lang.String r2 = "render_in_browser"
            r0.put(r2, r1)
            boolean r7 = r7.zzzm
            java.lang.String r1 = "disable_closable_area"
            r0.put(r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafs.zzb(com.google.android.gms.internal.ads.zzaej):org.json.JSONObject");
    }

    private static JSONArray zzm(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    private static Integer zzv(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }
}
