package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public class HttpClient implements zzv<zzue> {
    private final Context mContext;
    private final zzang zzyf;

    @zzadh
    static class zza {
        private final String mKey;
        private final String mValue;

        public zza(String str, String str2) {
            this.mKey = str;
            this.mValue = str2;
        }

        public final String getKey() {
            return this.mKey;
        }

        public final String getValue() {
            return this.mValue;
        }
    }

    @zzadh
    static class zzb {
        private final String zzbmm;
        private final URL zzbmn;
        private final ArrayList<zza> zzbmo;
        private final String zzbmp;

        zzb(String str, URL url, ArrayList<zza> arrayList, String str2) {
            this.zzbmm = str;
            this.zzbmn = url;
            this.zzbmo = arrayList;
            this.zzbmp = str2;
        }

        public final String zzkv() {
            return this.zzbmm;
        }

        public final URL zzkw() {
            return this.zzbmn;
        }

        public final ArrayList<zza> zzkx() {
            return this.zzbmo;
        }

        public final String zzky() {
            return this.zzbmp;
        }
    }

    @zzadh
    class zzc {
        private final zzd zzbmq;
        private final boolean zzbmr;
        private final String zzbms;

        public zzc(HttpClient httpClient, boolean z, zzd zzd, String str) {
            this.zzbmr = z;
            this.zzbmq = zzd;
            this.zzbms = str;
        }

        public final String getReason() {
            return this.zzbms;
        }

        public final boolean isSuccess() {
            return this.zzbmr;
        }

        public final zzd zzkz() {
            return this.zzbmq;
        }
    }

    @zzadh
    static class zzd {
        private final String zzbhy;
        private final String zzbmm;
        private final int zzbmt;
        private final List<zza> zzcf;

        zzd(String str, int i, List<zza> list, String str2) {
            this.zzbmm = str;
            this.zzbmt = i;
            this.zzcf = list;
            this.zzbhy = str2;
        }

        public final String getBody() {
            return this.zzbhy;
        }

        public final int getResponseCode() {
            return this.zzbmt;
        }

        public final String zzkv() {
            return this.zzbmm;
        }

        public final Iterable<zza> zzla() {
            return this.zzcf;
        }
    }

    public HttpClient(Context context, zzang zzang) {
        this.mContext = context;
        this.zzyf = zzang;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.ads.internal.gmsg.HttpClient.zzc zza(com.google.android.gms.ads.internal.gmsg.HttpClient.zzb r13) {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
            java.net.URL r2 = r13.zzkw()     // Catch:{ Exception -> 0x00ef, all -> 0x00ed }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x00ef, all -> 0x00ed }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x00ef, all -> 0x00ed }
            com.google.android.gms.internal.ads.zzakk r3 = com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ Exception -> 0x00eb }
            android.content.Context r4 = r12.mContext     // Catch:{ Exception -> 0x00eb }
            com.google.android.gms.internal.ads.zzang r5 = r12.zzyf     // Catch:{ Exception -> 0x00eb }
            java.lang.String r5 = r5.zzcw     // Catch:{ Exception -> 0x00eb }
            r3.zza((android.content.Context) r4, (java.lang.String) r5, (boolean) r0, (java.net.HttpURLConnection) r2)     // Catch:{ Exception -> 0x00eb }
            java.util.ArrayList r3 = r13.zzkx()     // Catch:{ Exception -> 0x00eb }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ Exception -> 0x00eb }
            int r4 = r3.size()     // Catch:{ Exception -> 0x00eb }
            r5 = 0
        L_0x0024:
            if (r5 >= r4) goto L_0x003a
            java.lang.Object r6 = r3.get(r5)     // Catch:{ Exception -> 0x00eb }
            int r5 = r5 + 1
            com.google.android.gms.ads.internal.gmsg.HttpClient$zza r6 = (com.google.android.gms.ads.internal.gmsg.HttpClient.zza) r6     // Catch:{ Exception -> 0x00eb }
            java.lang.String r7 = r6.getKey()     // Catch:{ Exception -> 0x00eb }
            java.lang.String r6 = r6.getValue()     // Catch:{ Exception -> 0x00eb }
            r2.addRequestProperty(r7, r6)     // Catch:{ Exception -> 0x00eb }
            goto L_0x0024
        L_0x003a:
            java.lang.String r3 = r13.zzky()     // Catch:{ Exception -> 0x00eb }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x00eb }
            r4 = 1
            if (r3 != 0) goto L_0x0064
            r2.setDoOutput(r4)     // Catch:{ Exception -> 0x00eb }
            java.lang.String r3 = r13.zzky()     // Catch:{ Exception -> 0x00eb }
            byte[] r3 = r3.getBytes()     // Catch:{ Exception -> 0x00eb }
            int r5 = r3.length     // Catch:{ Exception -> 0x00eb }
            r2.setFixedLengthStreamingMode(r5)     // Catch:{ Exception -> 0x00eb }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x00eb }
            java.io.OutputStream r6 = r2.getOutputStream()     // Catch:{ Exception -> 0x00eb }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00eb }
            r5.write(r3)     // Catch:{ Exception -> 0x00eb }
            r5.close()     // Catch:{ Exception -> 0x00eb }
            goto L_0x0065
        L_0x0064:
            r3 = r1
        L_0x0065:
            com.google.android.gms.internal.ads.zzamy r5 = new com.google.android.gms.internal.ads.zzamy     // Catch:{ Exception -> 0x00eb }
            r5.<init>()     // Catch:{ Exception -> 0x00eb }
            r5.zza((java.net.HttpURLConnection) r2, (byte[]) r3)     // Catch:{ Exception -> 0x00eb }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x00eb }
            r3.<init>()     // Catch:{ Exception -> 0x00eb }
            java.util.Map r6 = r2.getHeaderFields()     // Catch:{ Exception -> 0x00eb }
            if (r6 == 0) goto L_0x00b5
            java.util.Map r6 = r2.getHeaderFields()     // Catch:{ Exception -> 0x00eb }
            java.util.Set r6 = r6.entrySet()     // Catch:{ Exception -> 0x00eb }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x00eb }
        L_0x0084:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x00eb }
            if (r7 == 0) goto L_0x00b5
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x00eb }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ Exception -> 0x00eb }
            java.lang.Object r8 = r7.getValue()     // Catch:{ Exception -> 0x00eb }
            java.util.List r8 = (java.util.List) r8     // Catch:{ Exception -> 0x00eb }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x00eb }
        L_0x009a:
            boolean r9 = r8.hasNext()     // Catch:{ Exception -> 0x00eb }
            if (r9 == 0) goto L_0x0084
            java.lang.Object r9 = r8.next()     // Catch:{ Exception -> 0x00eb }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x00eb }
            com.google.android.gms.ads.internal.gmsg.HttpClient$zza r10 = new com.google.android.gms.ads.internal.gmsg.HttpClient$zza     // Catch:{ Exception -> 0x00eb }
            java.lang.Object r11 = r7.getKey()     // Catch:{ Exception -> 0x00eb }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ Exception -> 0x00eb }
            r10.<init>(r11, r9)     // Catch:{ Exception -> 0x00eb }
            r3.add(r10)     // Catch:{ Exception -> 0x00eb }
            goto L_0x009a
        L_0x00b5:
            com.google.android.gms.ads.internal.gmsg.HttpClient$zzd r6 = new com.google.android.gms.ads.internal.gmsg.HttpClient$zzd     // Catch:{ Exception -> 0x00eb }
            java.lang.String r13 = r13.zzkv()     // Catch:{ Exception -> 0x00eb }
            int r7 = r2.getResponseCode()     // Catch:{ Exception -> 0x00eb }
            com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ Exception -> 0x00eb }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00eb }
            java.io.InputStream r9 = r2.getInputStream()     // Catch:{ Exception -> 0x00eb }
            r8.<init>(r9)     // Catch:{ Exception -> 0x00eb }
            java.lang.String r8 = com.google.android.gms.internal.ads.zzakk.zza((java.io.InputStreamReader) r8)     // Catch:{ Exception -> 0x00eb }
            r6.<init>(r13, r7, r3, r8)     // Catch:{ Exception -> 0x00eb }
            int r13 = r6.getResponseCode()     // Catch:{ Exception -> 0x00eb }
            r5.zza((java.net.HttpURLConnection) r2, (int) r13)     // Catch:{ Exception -> 0x00eb }
            java.lang.String r13 = r6.getBody()     // Catch:{ Exception -> 0x00eb }
            r5.zzdg(r13)     // Catch:{ Exception -> 0x00eb }
            com.google.android.gms.ads.internal.gmsg.HttpClient$zzc r13 = new com.google.android.gms.ads.internal.gmsg.HttpClient$zzc     // Catch:{ Exception -> 0x00eb }
            r13.<init>(r12, r4, r6, r1)     // Catch:{ Exception -> 0x00eb }
            if (r2 == 0) goto L_0x00ea
            r2.disconnect()
        L_0x00ea:
            return r13
        L_0x00eb:
            r13 = move-exception
            goto L_0x00f1
        L_0x00ed:
            r13 = move-exception
            goto L_0x0102
        L_0x00ef:
            r13 = move-exception
            r2 = r1
        L_0x00f1:
            com.google.android.gms.ads.internal.gmsg.HttpClient$zzc r3 = new com.google.android.gms.ads.internal.gmsg.HttpClient$zzc     // Catch:{ all -> 0x0100 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0100 }
            r3.<init>(r12, r0, r1, r13)     // Catch:{ all -> 0x0100 }
            if (r2 == 0) goto L_0x00ff
            r2.disconnect()
        L_0x00ff:
            return r3
        L_0x0100:
            r13 = move-exception
            r1 = r2
        L_0x0102:
            if (r1 == 0) goto L_0x0107
            r1.disconnect()
        L_0x0107:
            goto L_0x0109
        L_0x0108:
            throw r13
        L_0x0109:
            goto L_0x0108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.gmsg.HttpClient.zza(com.google.android.gms.ads.internal.gmsg.HttpClient$zzb):com.google.android.gms.ads.internal.gmsg.HttpClient$zzc");
    }

    private static JSONObject zza(zzd zzd2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", zzd2.zzkv());
            if (zzd2.getBody() != null) {
                jSONObject.put("body", zzd2.getBody());
            }
            JSONArray jSONArray = new JSONArray();
            for (zza next : zzd2.zzla()) {
                jSONArray.put(new JSONObject().put("key", next.getKey()).put("value", next.getValue()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", zzd2.getResponseCode());
        } catch (JSONException e) {
            zzakb.zzb("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    private static zzb zzc(JSONObject jSONObject) {
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString("url");
        URL url = null;
        String optString3 = jSONObject.optString("post_body", (String) null);
        try {
            url = new URL(optString2);
        } catch (MalformedURLException e) {
            zzakb.zzb("Error constructing http request.", e);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new zza(optJSONObject.optString("key"), optJSONObject.optString("value")));
            }
        }
        return new zzb(optString, url, arrayList, optString3);
    }

    public JSONObject send(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            String optString = jSONObject.optString("http_request_id");
            zzc zza2 = zza(zzc(jSONObject));
            if (zza2.isSuccess()) {
                jSONObject2.put("response", zza(zza2.zzkz()));
                jSONObject2.put("success", true);
            } else {
                jSONObject2.put("response", new JSONObject().put("http_request_id", optString));
                jSONObject2.put("success", false);
                jSONObject2.put("reason", zza2.getReason());
            }
        } catch (Exception e) {
            zzakb.zzb("Error executing http request.", e);
            try {
                jSONObject2.put("response", new JSONObject().put("http_request_id", ""));
                jSONObject2.put("success", false);
                jSONObject2.put("reason", e.toString());
            } catch (JSONException e2) {
                zzakb.zzb("Error executing http request.", e2);
            }
        }
        return jSONObject2;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaki.zzb(new zzw(this, map, (zzue) obj));
    }
}
