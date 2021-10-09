package com.facebook.ads.internal.dynamicloading;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.facebook.ads.internal.api.BuildConfigApi;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class DexLoadErrorReporter {
    public static final double SAMPLING = 0.1d;
    private static final AtomicBoolean sAlreadyReported = new AtomicBoolean();

    public static void reportDexLoadingIssue(final Context context, final String str, double d) {
        if (!sAlreadyReported.get() && Math.random() < d) {
            sAlreadyReported.set(true);
            new Thread() {
                /* JADX INFO: finally extract failed */
                /* JADX WARNING: Removed duplicated region for block: B:41:0x016e A[SYNTHETIC, Splitter:B:41:0x016e] */
                /* JADX WARNING: Removed duplicated region for block: B:46:0x0179 A[SYNTHETIC, Splitter:B:46:0x0179] */
                /* JADX WARNING: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r22 = this;
                        r1 = r22
                        java.lang.String r0 = "data"
                        java.lang.String r2 = ""
                        java.lang.String r3 = "0"
                        java.lang.String r4 = "attempt"
                        java.lang.String r5 = "UTF-8"
                        java.lang.String r6 = "Can't close connection."
                        java.lang.String r7 = "FBAudienceNetwork"
                        super.run()
                        java.net.URL r9 = new java.net.URL     // Catch:{ all -> 0x0161 }
                        java.lang.String r10 = "https://www.facebook.com/adnw_logging/"
                        r9.<init>(r10)     // Catch:{ all -> 0x0161 }
                        java.net.URLConnection r9 = r9.openConnection()     // Catch:{ all -> 0x0161 }
                        java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ all -> 0x0161 }
                        java.lang.String r10 = "POST"
                        r9.setRequestMethod(r10)     // Catch:{ all -> 0x015a }
                        java.lang.String r10 = "Content-Type"
                        java.lang.String r11 = "application/x-www-form-urlencoded;charset=UTF-8"
                        r9.setRequestProperty(r10, r11)     // Catch:{ all -> 0x015a }
                        java.lang.String r10 = "Accept"
                        java.lang.String r11 = "application/json"
                        r9.setRequestProperty(r10, r11)     // Catch:{ all -> 0x015a }
                        java.lang.String r10 = "Accept-Charset"
                        r9.setRequestProperty(r10, r5)     // Catch:{ all -> 0x015a }
                        java.lang.String r10 = "user-agent"
                        java.lang.String r11 = "[FBAN/AudienceNetworkForAndroid;FBSN/Android]"
                        r9.setRequestProperty(r10, r11)     // Catch:{ all -> 0x015a }
                        r10 = 1
                        r9.setDoOutput(r10)     // Catch:{ all -> 0x015a }
                        r9.setDoInput(r10)     // Catch:{ all -> 0x015a }
                        r9.connect()     // Catch:{ all -> 0x015a }
                        java.util.UUID r10 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x015a }
                        java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x015a }
                        org.json.JSONObject r11 = new org.json.JSONObject     // Catch:{ all -> 0x015a }
                        r11.<init>()     // Catch:{ all -> 0x015a }
                        r11.put(r4, r3)     // Catch:{ all -> 0x015a }
                        android.content.Context r12 = r3     // Catch:{ all -> 0x015a }
                        com.facebook.ads.internal.dynamicloading.DexLoadErrorReporter.addEnvFields(r12, r11, r10)     // Catch:{ all -> 0x015a }
                        org.json.JSONObject r12 = new org.json.JSONObject     // Catch:{ all -> 0x015a }
                        r12.<init>()     // Catch:{ all -> 0x015a }
                        java.lang.String r13 = "subtype"
                        java.lang.String r14 = "generic"
                        r12.put(r13, r14)     // Catch:{ all -> 0x015a }
                        java.lang.String r13 = "subtype_code"
                        java.lang.String r14 = "1320"
                        r12.put(r13, r14)     // Catch:{ all -> 0x015a }
                        java.lang.String r13 = "caught_exception"
                        java.lang.String r14 = "1"
                        r12.put(r13, r14)     // Catch:{ all -> 0x015a }
                        java.lang.String r13 = "stacktrace"
                        java.lang.String r14 = r4     // Catch:{ all -> 0x015a }
                        r12.put(r13, r14)     // Catch:{ all -> 0x015a }
                        org.json.JSONObject r13 = new org.json.JSONObject     // Catch:{ all -> 0x015a }
                        r13.<init>()     // Catch:{ all -> 0x015a }
                        java.lang.String r14 = "id"
                        java.util.UUID r15 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x015a }
                        java.lang.String r15 = r15.toString()     // Catch:{ all -> 0x015a }
                        r13.put(r14, r15)     // Catch:{ all -> 0x015a }
                        java.lang.String r14 = "type"
                        java.lang.String r15 = "debug"
                        r13.put(r14, r15)     // Catch:{ all -> 0x015a }
                        java.lang.String r14 = "session_time"
                        java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x015a }
                        r15.<init>()     // Catch:{ all -> 0x015a }
                        r15.append(r2)     // Catch:{ all -> 0x015a }
                        long r16 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x015a }
                        r18 = 1000(0x3e8, double:4.94E-321)
                        r21 = r9
                        long r8 = r16 / r18
                        r15.append(r8)     // Catch:{ all -> 0x0158 }
                        java.lang.String r8 = r15.toString()     // Catch:{ all -> 0x0158 }
                        r13.put(r14, r8)     // Catch:{ all -> 0x0158 }
                        java.lang.String r8 = "time"
                        java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0158 }
                        r9.<init>()     // Catch:{ all -> 0x0158 }
                        r9.append(r2)     // Catch:{ all -> 0x0158 }
                        long r14 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0158 }
                        long r14 = r14 / r18
                        r9.append(r14)     // Catch:{ all -> 0x0158 }
                        java.lang.String r2 = r9.toString()     // Catch:{ all -> 0x0158 }
                        r13.put(r8, r2)     // Catch:{ all -> 0x0158 }
                        java.lang.String r2 = "session_id"
                        r13.put(r2, r10)     // Catch:{ all -> 0x0158 }
                        r13.put(r0, r12)     // Catch:{ all -> 0x0158 }
                        r13.put(r4, r3)     // Catch:{ all -> 0x0158 }
                        android.content.Context r2 = r3     // Catch:{ all -> 0x0158 }
                        com.facebook.ads.internal.dynamicloading.DexLoadErrorReporter.addEnvFields(r2, r12, r10)     // Catch:{ all -> 0x0158 }
                        org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ all -> 0x0158 }
                        r2.<init>()     // Catch:{ all -> 0x0158 }
                        r2.put(r13)     // Catch:{ all -> 0x0158 }
                        org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ all -> 0x0158 }
                        r3.<init>()     // Catch:{ all -> 0x0158 }
                        r3.put(r0, r11)     // Catch:{ all -> 0x0158 }
                        java.lang.String r0 = "events"
                        r3.put(r0, r2)     // Catch:{ all -> 0x0158 }
                        java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0158 }
                        java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ all -> 0x0158 }
                        java.io.OutputStream r3 = r21.getOutputStream()     // Catch:{ all -> 0x0158 }
                        r2.<init>(r3)     // Catch:{ all -> 0x0158 }
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0155 }
                        r3.<init>()     // Catch:{ all -> 0x0155 }
                        java.lang.String r4 = "payload="
                        r3.append(r4)     // Catch:{ all -> 0x0155 }
                        java.lang.String r0 = java.net.URLEncoder.encode(r0, r5)     // Catch:{ all -> 0x0155 }
                        r3.append(r0)     // Catch:{ all -> 0x0155 }
                        java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0155 }
                        r2.writeBytes(r0)     // Catch:{ all -> 0x0155 }
                        r2.flush()     // Catch:{ all -> 0x0155 }
                        r0 = 16384(0x4000, float:2.2959E-41)
                        byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0155 }
                        java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0155 }
                        r3.<init>()     // Catch:{ all -> 0x0155 }
                        java.io.InputStream r8 = r21.getInputStream()     // Catch:{ all -> 0x0155 }
                    L_0x012a:
                        int r4 = r8.read(r0)     // Catch:{ all -> 0x0150 }
                        r5 = -1
                        if (r4 == r5) goto L_0x0136
                        r5 = 0
                        r3.write(r0, r5, r4)     // Catch:{ all -> 0x0150 }
                        goto L_0x012a
                    L_0x0136:
                        r3.flush()     // Catch:{ all -> 0x0150 }
                        r2.close()     // Catch:{ Exception -> 0x013d }
                        goto L_0x0142
                    L_0x013d:
                        r0 = move-exception
                        r2 = r0
                        android.util.Log.e(r7, r6, r2)
                    L_0x0142:
                        if (r8 == 0) goto L_0x014d
                        r8.close()     // Catch:{ Exception -> 0x0148 }
                        goto L_0x014d
                    L_0x0148:
                        r0 = move-exception
                        r2 = r0
                        android.util.Log.e(r7, r6, r2)
                    L_0x014d:
                        if (r21 == 0) goto L_0x0187
                        goto L_0x0184
                    L_0x0150:
                        r0 = move-exception
                        r20 = r8
                        r8 = r2
                        goto L_0x0167
                    L_0x0155:
                        r0 = move-exception
                        r8 = r2
                        goto L_0x015e
                    L_0x0158:
                        r0 = move-exception
                        goto L_0x015d
                    L_0x015a:
                        r0 = move-exception
                        r21 = r9
                    L_0x015d:
                        r8 = 0
                    L_0x015e:
                        r20 = 0
                        goto L_0x0167
                    L_0x0161:
                        r0 = move-exception
                        r8 = 0
                        r20 = 0
                        r21 = 0
                    L_0x0167:
                        java.lang.String r2 = "Can't send error."
                        android.util.Log.e(r7, r2, r0)     // Catch:{ all -> 0x0188 }
                        if (r8 == 0) goto L_0x0177
                        r8.close()     // Catch:{ Exception -> 0x0172 }
                        goto L_0x0177
                    L_0x0172:
                        r0 = move-exception
                        r2 = r0
                        android.util.Log.e(r7, r6, r2)
                    L_0x0177:
                        if (r20 == 0) goto L_0x0182
                        r20.close()     // Catch:{ Exception -> 0x017d }
                        goto L_0x0182
                    L_0x017d:
                        r0 = move-exception
                        r2 = r0
                        android.util.Log.e(r7, r6, r2)
                    L_0x0182:
                        if (r21 == 0) goto L_0x0187
                    L_0x0184:
                        r21.disconnect()
                    L_0x0187:
                        return
                    L_0x0188:
                        r0 = move-exception
                        r2 = r0
                        if (r8 == 0) goto L_0x0195
                        r8.close()     // Catch:{ Exception -> 0x0190 }
                        goto L_0x0195
                    L_0x0190:
                        r0 = move-exception
                        r3 = r0
                        android.util.Log.e(r7, r6, r3)
                    L_0x0195:
                        if (r20 == 0) goto L_0x01a0
                        r20.close()     // Catch:{ Exception -> 0x019b }
                        goto L_0x01a0
                    L_0x019b:
                        r0 = move-exception
                        r3 = r0
                        android.util.Log.e(r7, r6, r3)
                    L_0x01a0:
                        if (r21 == 0) goto L_0x01a5
                        r21.disconnect()
                    L_0x01a5:
                        goto L_0x01a7
                    L_0x01a6:
                        throw r2
                    L_0x01a7:
                        goto L_0x01a6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.dynamicloading.DexLoadErrorReporter.AnonymousClass1.run():void");
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public static void addEnvFields(Context context, JSONObject jSONObject, String str) throws JSONException, PackageManager.NameNotFoundException {
        String packageName = context.getPackageName();
        jSONObject.put("APPBUILD", context.getPackageManager().getPackageInfo(packageName, 0).versionCode);
        jSONObject.put("APPNAME", context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(packageName, 0)));
        jSONObject.put("APPVERS", context.getPackageManager().getPackageInfo(packageName, 0).versionName);
        jSONObject.put("OSVERS", Build.VERSION.RELEASE);
        jSONObject.put("SDK", "android");
        jSONObject.put("SESSION_ID", str);
        jSONObject.put("MODEL", Build.MODEL);
        jSONObject.put("BUNDLE", packageName);
        jSONObject.put("SDK_VERSION", BuildConfigApi.getVersionName(context));
        jSONObject.put("OS", "Android");
    }
}
