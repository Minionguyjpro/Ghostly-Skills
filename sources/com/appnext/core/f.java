package com.appnext.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.WebView;
import com.appnext.ads.a;
import com.appnext.base.b.d;
import com.appnext.base.b.e;
import com.google.android.gms.ads.AdRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.io.StringWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;
import org.json.JSONObject;

public final class f {
    private static final boolean DEBUG = false;
    public static final String VID = "2.5.1.472";
    static final int fd = 8000;
    private static final String gP = "expiredTimems";
    private static double gQ = -1.0d;
    private static HashMap<String, Bitmap> gR = new HashMap<>();
    private static String gS = "";
    private static String gT = "";
    private static String gU = "";
    public static final Executor gV = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1, (Runtime.getRuntime().availableProcessors() * 2) + 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new ThreadFactory() {
        private final AtomicInteger gW = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.gW.getAndIncrement());
        }
    });

    public static double bg() {
        return 0.0d;
    }

    public static void c(Throwable th) {
    }

    public static void o(String str) {
    }

    static {
        CookieHandler.setDefault(new CookieManager());
    }

    public static String l(Context context) {
        return b(context, true);
    }

    public static String b(Context context, boolean z) {
        String str;
        if (context == null || context.getApplicationContext() == null) {
            return "";
        }
        try {
            Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            str = AdsIDHelper.a(context.getApplicationContext(), z);
        } catch (ClassNotFoundException unused) {
            str = "";
        }
        try {
            gT = str;
            return str;
        } catch (Throwable unused2) {
            if (!gT.equals("")) {
                return gT;
            }
            return "";
        }
    }

    static void m(Context context) {
        try {
            WebView webView = new WebView(context);
            gS = webView.getSettings().getUserAgentString();
            webView.destroy();
        } catch (Throwable unused) {
        }
    }

    static String bd() {
        return gS;
    }

    private static void V(String str) {
        gS = str;
    }

    public static String be() {
        try {
            return URLEncoder.encode("android " + Build.VERSION.SDK_INT + " " + Build.MANUFACTURER + " " + Build.MODEL, "UTF-8");
        } catch (Throwable unused) {
            return "android";
        }
    }

    public static int bf() {
        Runtime runtime = Runtime.getRuntime();
        return (int) ((runtime.maxMemory() / d.fc) - ((runtime.totalMemory() - runtime.freeMemory()) / d.fc));
    }

    public static String a(String str, HashMap<String, String> hashMap) throws IOException {
        return a(str, hashMap, true, (int) fd);
    }

    public static String a(String str, HashMap<String, String> hashMap, boolean z) throws IOException {
        return a(str, hashMap, z, (int) fd);
    }

    public static String a(String str, HashMap<String, String> hashMap, int i) throws IOException {
        return a(str, (HashMap<String, String>) null, true, i);
    }

    public static String a(String str, HashMap<String, String> hashMap, boolean z, int i) throws IOException {
        return new String(b(str, hashMap, z, i, d.a.HashMap), "UTF-8");
    }

    public static String a(String str, ArrayList<Pair<String, String>> arrayList) throws IOException {
        return a(str, arrayList, (int) fd);
    }

    public static String a(String str, ArrayList<Pair<String, String>> arrayList, int i) throws IOException {
        return new String(b(str, arrayList, true, i, d.a.ArrayList), "UTF-8");
    }

    public static String b(String str, JSONObject jSONObject) throws IOException {
        return new String(b(str, jSONObject, true, fd, d.a.JSONObject), "UTF-8");
    }

    public static byte[] a(String str, Object obj, boolean z, int i) throws IOException {
        return b(str, obj, z, i, d.a.HashMap);
    }

    private static boolean W(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("aid")) {
                return false;
            }
            String string = jSONObject.getString("aid");
            if (TextUtils.isEmpty(string) || !string.equals(b(e.getContext(), true))) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
        }
    }

    private static boolean X(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(gP)) {
                return true;
            }
            if (System.currentTimeMillis() >= jSONObject.getLong(gP)) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [byte[], java.io.InputStream] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01fd  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0202  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r18, java.lang.Object r19, boolean r20, int r21, com.appnext.base.b.d.a r22) throws java.lang.Exception {
        /*
            r0 = r19
            r1 = r22
            java.lang.String r2 = "secret"
            java.lang.String r3 = "rnd"
            com.appnext.base.services.a r4 = com.appnext.base.services.a.aK()
            java.lang.String r4 = r4.getKey()
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            java.lang.String r6 = "UTF-8"
            java.lang.String r7 = "aid"
            r8 = 15000(0x3a98, float:2.102E-41)
            r9 = 0
            r10 = 1
            r11 = 0
            if (r5 != 0) goto L_0x002b
            boolean r5 = W(r4)
            if (r5 == 0) goto L_0x002b
            boolean r5 = X(r4)
            if (r5 == 0) goto L_0x0094
        L_0x002b:
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            com.appnext.base.b.h r5 = com.appnext.base.b.h.aO()
            android.content.Context r12 = com.appnext.base.b.e.getContext()
            java.lang.String r12 = b((android.content.Context) r12, (boolean) r10)
            java.lang.String r5 = r5.M(r12)
            r4.put(r7, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0207 }
            java.lang.String r12 = "https://api.appnxt.net"
            r5.<init>(r12)     // Catch:{ all -> 0x0207 }
            java.lang.String r12 = "/api/token"
            r5.append(r12)     // Catch:{ all -> 0x0207 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0207 }
            com.appnext.base.b.d$a r12 = com.appnext.base.b.d.a.HashMap     // Catch:{ all -> 0x0207 }
            byte[] r4 = b(r5, r4, r9, r8, r12)     // Catch:{ all -> 0x0207 }
            if (r4 != 0) goto L_0x005c
            return r11
        L_0x005c:
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x0207 }
            r5.<init>(r4, r6)     // Catch:{ all -> 0x0207 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ all -> 0x0207 }
            r4.<init>(r5)     // Catch:{ all -> 0x0207 }
            android.content.Context r12 = com.appnext.base.b.e.getContext()     // Catch:{ all -> 0x0207 }
            java.lang.String r12 = b((android.content.Context) r12, (boolean) r10)     // Catch:{ all -> 0x0207 }
            r4.put(r7, r12)     // Catch:{ all -> 0x0207 }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0207 }
            java.lang.String r14 = "expire"
            long r14 = r4.getLong(r14)     // Catch:{ all -> 0x0207 }
            r16 = 1000(0x3e8, double:4.94E-321)
            long r14 = r14 * r16
            long r12 = r12 + r14
            r14 = 10000(0x2710, double:4.9407E-320)
            long r12 = r12 - r14
            java.lang.String r14 = "expiredTimems"
            r4.put(r14, r12)     // Catch:{ all -> 0x0207 }
            com.appnext.base.services.a r12 = com.appnext.base.services.a.aK()     // Catch:{ all -> 0x0207 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0207 }
            r12.setKey(r4)     // Catch:{ all -> 0x0207 }
            r4 = r5
        L_0x0094:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ all -> 0x0206 }
            r5.<init>(r4)     // Catch:{ all -> 0x0206 }
            boolean r4 = r5.has(r2)     // Catch:{ all -> 0x0206 }
            if (r4 == 0) goto L_0x0206
            boolean r4 = r5.has(r3)     // Catch:{ all -> 0x0206 }
            if (r4 != 0) goto L_0x00a7
            goto L_0x0206
        L_0x00a7:
            java.lang.String r4 = r5.getString(r3)     // Catch:{ all -> 0x0206 }
            java.lang.String r2 = r5.getString(r2)     // Catch:{ all -> 0x0206 }
            java.net.URL r5 = new java.net.URL
            r12 = r18
            r5.<init>(r12)
            java.net.URLConnection r5 = r5.openConnection()     // Catch:{ all -> 0x01f9 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ all -> 0x01f9 }
            r5.setReadTimeout(r8)     // Catch:{ all -> 0x01f7 }
            r5.setConnectTimeout(r8)     // Catch:{ all -> 0x01f7 }
            java.lang.String r12 = "Accept-Encoding"
            java.lang.String r13 = "gzip"
            r5.setRequestProperty(r12, r13)     // Catch:{ all -> 0x01f7 }
            java.lang.String r12 = "User-Agent"
            java.lang.String r13 = gS     // Catch:{ all -> 0x01f7 }
            r5.setRequestProperty(r12, r13)     // Catch:{ all -> 0x01f7 }
            r5.setRequestProperty(r3, r4)     // Catch:{ all -> 0x01f7 }
            android.content.Context r3 = com.appnext.base.b.e.getContext()     // Catch:{ all -> 0x01f7 }
            java.lang.String r3 = b((android.content.Context) r3, (boolean) r10)     // Catch:{ all -> 0x01f7 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x01f7 }
            java.lang.String r12 = "aidForSend"
            if (r4 == 0) goto L_0x00f8
            com.appnext.base.b.d$a r3 = com.appnext.base.b.d.a.String     // Catch:{ all -> 0x01f7 }
            java.lang.Object r3 = com.appnext.base.b.j.a(r12, r3)     // Catch:{ all -> 0x01f7 }
            if (r3 == 0) goto L_0x00f2
            boolean r4 = r3 instanceof java.lang.String     // Catch:{ all -> 0x01f7 }
            if (r4 == 0) goto L_0x00f2
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x01f7 }
            goto L_0x00fd
        L_0x00f2:
            if (r5 == 0) goto L_0x00f7
            r5.disconnect()
        L_0x00f7:
            return r11
        L_0x00f8:
            com.appnext.base.b.d$a r4 = com.appnext.base.b.d.a.String     // Catch:{ all -> 0x01f7 }
            com.appnext.base.b.j.a((java.lang.String) r12, (java.lang.String) r3, (com.appnext.base.b.d.a) r4)     // Catch:{ all -> 0x01f7 }
        L_0x00fd:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x01f7 }
            if (r4 == 0) goto L_0x0109
            if (r5 == 0) goto L_0x0108
            r5.disconnect()
        L_0x0108:
            return r11
        L_0x0109:
            com.appnext.base.b.h r4 = com.appnext.base.b.h.aO()     // Catch:{ all -> 0x01f7 }
            java.lang.String r3 = r4.M(r3)     // Catch:{ all -> 0x01f7 }
            r5.setRequestProperty(r7, r3)     // Catch:{ all -> 0x01f7 }
            r5.setDoOutput(r10)     // Catch:{ all -> 0x01f7 }
            java.lang.String r3 = "POST"
            r5.setRequestMethod(r3)     // Catch:{ all -> 0x01f7 }
            com.appnext.base.b.d$a r3 = com.appnext.base.b.d.a.JSONObject     // Catch:{ all -> 0x01f7 }
            if (r1 == r3) goto L_0x0124
            com.appnext.base.b.d$a r3 = com.appnext.base.b.d.a.JSONArray     // Catch:{ all -> 0x01f7 }
            if (r1 != r3) goto L_0x0139
        L_0x0124:
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/json"
            r5.setRequestProperty(r3, r4)     // Catch:{ all -> 0x01f7 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01f7 }
            java.lang.String r4 = "report params "
            r3.<init>(r4)     // Catch:{ all -> 0x01f7 }
            java.lang.String r4 = r19.toString()     // Catch:{ all -> 0x01f7 }
            r3.append(r4)     // Catch:{ all -> 0x01f7 }
        L_0x0139:
            java.lang.String r3 = ""
            com.appnext.base.b.d$a r4 = com.appnext.base.b.d.a.JSONArray     // Catch:{ all -> 0x01f7 }
            if (r1 != r4) goto L_0x0147
            r3 = r0
            org.json.JSONArray r3 = (org.json.JSONArray) r3     // Catch:{ all -> 0x01f7 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x01f7 }
            goto L_0x0194
        L_0x0147:
            com.appnext.base.b.d$a r4 = com.appnext.base.b.d.a.JSONObject     // Catch:{ all -> 0x01f7 }
            if (r1 != r4) goto L_0x0153
            r3 = r0
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ all -> 0x01f7 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x01f7 }
            goto L_0x0194
        L_0x0153:
            com.appnext.base.b.d$a r4 = com.appnext.base.b.d.a.HashMap     // Catch:{ all -> 0x01f7 }
            if (r1 != r4) goto L_0x0189
            r3 = r0
            java.util.HashMap r3 = (java.util.HashMap) r3     // Catch:{ all -> 0x01f7 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x01f7 }
            r4.<init>()     // Catch:{ all -> 0x01f7 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ all -> 0x01f7 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x01f7 }
        L_0x0167:
            boolean r7 = r3.hasNext()     // Catch:{ all -> 0x01f7 }
            if (r7 == 0) goto L_0x0184
            java.lang.Object r7 = r3.next()     // Catch:{ all -> 0x01f7 }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ all -> 0x01f7 }
            android.util.Pair r10 = new android.util.Pair     // Catch:{ all -> 0x01f7 }
            java.lang.Object r12 = r7.getKey()     // Catch:{ all -> 0x01f7 }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x01f7 }
            r10.<init>(r12, r7)     // Catch:{ all -> 0x01f7 }
            r4.add(r10)     // Catch:{ all -> 0x01f7 }
            goto L_0x0167
        L_0x0184:
            java.lang.String r3 = b((java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) r4, (boolean) r9)     // Catch:{ all -> 0x01f7 }
            goto L_0x0194
        L_0x0189:
            com.appnext.base.b.d$a r4 = com.appnext.base.b.d.a.ArrayList     // Catch:{ all -> 0x01f7 }
            if (r1 != r4) goto L_0x0194
            r3 = r0
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x01f7 }
            java.lang.String r3 = b((java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) r3, (boolean) r9)     // Catch:{ all -> 0x01f7 }
        L_0x0194:
            com.appnext.base.b.h r4 = com.appnext.base.b.h.aO()     // Catch:{ all -> 0x01f7 }
            byte[] r2 = r4.f(r3, r2)     // Catch:{ all -> 0x01f7 }
            java.io.OutputStream r3 = r5.getOutputStream()     // Catch:{ all -> 0x01f7 }
            if (r2 == 0) goto L_0x01a5
            r3.write(r2)     // Catch:{ all -> 0x01f7 }
        L_0x01a5:
            r3.close()     // Catch:{ all -> 0x01f7 }
            int r2 = r5.getResponseCode()     // Catch:{ all -> 0x01f7 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 != r3) goto L_0x01c7
            java.io.InputStream r11 = r5.getInputStream()     // Catch:{ all -> 0x01f7 }
            java.io.InputStream r0 = a((java.io.InputStream) r11)     // Catch:{ all -> 0x01f7 }
            byte[] r0 = b((java.io.InputStream) r0)     // Catch:{ all -> 0x01f7 }
            if (r11 == 0) goto L_0x01c1
            r11.close()
        L_0x01c1:
            if (r5 == 0) goto L_0x01c6
            r5.disconnect()
        L_0x01c6:
            return r0
        L_0x01c7:
            r3 = 301(0x12d, float:4.22E-43)
            if (r2 == r3) goto L_0x01e7
            r3 = 302(0x12e, float:4.23E-43)
            if (r2 == r3) goto L_0x01e7
            r3 = 303(0x12f, float:4.25E-43)
            if (r2 != r3) goto L_0x01d4
            goto L_0x01e7
        L_0x01d4:
            java.io.InputStream r11 = r5.getErrorStream()     // Catch:{ all -> 0x01f7 }
            byte[] r0 = b((java.io.InputStream) r11)     // Catch:{ all -> 0x01f7 }
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x01f7 }
            r1.<init>(r0, r6)     // Catch:{ all -> 0x01f7 }
            java.net.HttpRetryException r0 = new java.net.HttpRetryException     // Catch:{ all -> 0x01f7 }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x01f7 }
            throw r0     // Catch:{ all -> 0x01f7 }
        L_0x01e7:
            java.lang.String r2 = "Location"
            java.lang.String r2 = r5.getHeaderField(r2)     // Catch:{ all -> 0x01f7 }
            byte[] r0 = b(r2, r0, r9, r8, r1)     // Catch:{ all -> 0x01f7 }
            if (r5 == 0) goto L_0x01f6
            r5.disconnect()
        L_0x01f6:
            return r0
        L_0x01f7:
            r0 = move-exception
            goto L_0x01fb
        L_0x01f9:
            r0 = move-exception
            r5 = r11
        L_0x01fb:
            if (r11 == 0) goto L_0x0200
            r11.close()
        L_0x0200:
            if (r5 == 0) goto L_0x0205
            r5.disconnect()
        L_0x0205:
            throw r0
        L_0x0206:
            return r11
        L_0x0207:
            r0 = move-exception
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r0 = r0.getMessage()
            r1.<init>(r0)
            goto L_0x0213
        L_0x0212:
            throw r1
        L_0x0213:
            goto L_0x0212
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.f.a(java.lang.String, java.lang.Object, boolean, int, com.appnext.base.b.d$a):byte[]");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.net.HttpURLConnection} */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] b(java.lang.String r9, java.lang.Object r10, boolean r11, int r12, com.appnext.base.b.d.a r13) throws java.io.IOException {
        /*
            java.net.URL r0 = new java.net.URL
            r0.<init>(r9)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "performURLCall "
            r1.<init>(r2)
            r1.append(r9)
            r9 = 0
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ all -> 0x0126 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ all -> 0x0126 }
            r0.setReadTimeout(r12)     // Catch:{ all -> 0x0124 }
            r0.setConnectTimeout(r12)     // Catch:{ all -> 0x0124 }
            java.lang.String r1 = "Accept-Encoding"
            java.lang.String r2 = "gzip"
            r0.setRequestProperty(r1, r2)     // Catch:{ all -> 0x0124 }
            java.lang.String r1 = "User-Agent"
            java.lang.String r2 = gS     // Catch:{ all -> 0x0124 }
            r0.setRequestProperty(r1, r2)     // Catch:{ all -> 0x0124 }
            java.lang.String r1 = "UTF-8"
            if (r10 == 0) goto L_0x00d0
            r2 = 1
            r0.setDoOutput(r2)     // Catch:{ all -> 0x0124 }
            java.lang.String r2 = "POST"
            r0.setRequestMethod(r2)     // Catch:{ all -> 0x0124 }
            com.appnext.base.b.d$a r2 = com.appnext.base.b.d.a.JSONObject     // Catch:{ all -> 0x0124 }
            if (r13 == r2) goto L_0x003f
            com.appnext.base.b.d$a r2 = com.appnext.base.b.d.a.JSONArray     // Catch:{ all -> 0x0124 }
            if (r13 != r2) goto L_0x0054
        L_0x003f:
            java.lang.String r2 = "Content-Type"
            java.lang.String r3 = "application/json"
            r0.setRequestProperty(r2, r3)     // Catch:{ all -> 0x0124 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0124 }
            java.lang.String r3 = "report params "
            r2.<init>(r3)     // Catch:{ all -> 0x0124 }
            java.lang.String r3 = r10.toString()     // Catch:{ all -> 0x0124 }
            r2.append(r3)     // Catch:{ all -> 0x0124 }
        L_0x0054:
            java.io.OutputStream r2 = r0.getOutputStream()     // Catch:{ all -> 0x0124 }
            java.io.BufferedWriter r3 = new java.io.BufferedWriter     // Catch:{ all -> 0x0124 }
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x0124 }
            r4.<init>(r2, r1)     // Catch:{ all -> 0x0124 }
            r3.<init>(r4)     // Catch:{ all -> 0x0124 }
            com.appnext.base.b.d$a r4 = com.appnext.base.b.d.a.JSONArray     // Catch:{ all -> 0x0124 }
            if (r13 != r4) goto L_0x0071
            r4 = r10
            org.json.JSONArray r4 = (org.json.JSONArray) r4     // Catch:{ all -> 0x0124 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0124 }
            r3.write(r4)     // Catch:{ all -> 0x0124 }
            goto L_0x00c7
        L_0x0071:
            com.appnext.base.b.d$a r4 = com.appnext.base.b.d.a.JSONObject     // Catch:{ all -> 0x0124 }
            if (r13 != r4) goto L_0x0080
            r4 = r10
            org.json.JSONObject r4 = (org.json.JSONObject) r4     // Catch:{ all -> 0x0124 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0124 }
            r3.write(r4)     // Catch:{ all -> 0x0124 }
            goto L_0x00c7
        L_0x0080:
            com.appnext.base.b.d$a r4 = com.appnext.base.b.d.a.HashMap     // Catch:{ all -> 0x0124 }
            if (r13 != r4) goto L_0x00b9
            r4 = r10
            java.util.HashMap r4 = (java.util.HashMap) r4     // Catch:{ all -> 0x0124 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0124 }
            r5.<init>()     // Catch:{ all -> 0x0124 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ all -> 0x0124 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0124 }
        L_0x0094:
            boolean r6 = r4.hasNext()     // Catch:{ all -> 0x0124 }
            if (r6 == 0) goto L_0x00b1
            java.lang.Object r6 = r4.next()     // Catch:{ all -> 0x0124 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ all -> 0x0124 }
            android.util.Pair r7 = new android.util.Pair     // Catch:{ all -> 0x0124 }
            java.lang.Object r8 = r6.getKey()     // Catch:{ all -> 0x0124 }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x0124 }
            r7.<init>(r8, r6)     // Catch:{ all -> 0x0124 }
            r5.add(r7)     // Catch:{ all -> 0x0124 }
            goto L_0x0094
        L_0x00b1:
            java.lang.String r4 = b((java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) r5, (boolean) r11)     // Catch:{ all -> 0x0124 }
            r3.write(r4)     // Catch:{ all -> 0x0124 }
            goto L_0x00c7
        L_0x00b9:
            com.appnext.base.b.d$a r4 = com.appnext.base.b.d.a.ArrayList     // Catch:{ all -> 0x0124 }
            if (r13 != r4) goto L_0x00c7
            r4 = r10
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch:{ all -> 0x0124 }
            java.lang.String r4 = b((java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) r4, (boolean) r11)     // Catch:{ all -> 0x0124 }
            r3.write(r4)     // Catch:{ all -> 0x0124 }
        L_0x00c7:
            r3.flush()     // Catch:{ all -> 0x0124 }
            r3.close()     // Catch:{ all -> 0x0124 }
            r2.close()     // Catch:{ all -> 0x0124 }
        L_0x00d0:
            int r2 = r0.getResponseCode()     // Catch:{ all -> 0x0124 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 != r3) goto L_0x00ef
            java.io.InputStream r9 = r0.getInputStream()     // Catch:{ all -> 0x0124 }
            java.io.InputStream r10 = a((java.io.InputStream) r9)     // Catch:{ all -> 0x0124 }
            byte[] r10 = b((java.io.InputStream) r10)     // Catch:{ all -> 0x0124 }
            if (r9 == 0) goto L_0x00e9
            r9.close()
        L_0x00e9:
            if (r0 == 0) goto L_0x00ee
            r0.disconnect()
        L_0x00ee:
            return r10
        L_0x00ef:
            r3 = 301(0x12d, float:4.22E-43)
            if (r2 == r3) goto L_0x0114
            r3 = 302(0x12e, float:4.23E-43)
            if (r2 == r3) goto L_0x0114
            r3 = 303(0x12f, float:4.25E-43)
            if (r2 != r3) goto L_0x00fc
            goto L_0x0114
        L_0x00fc:
            java.lang.String r10 = ""
            java.io.InputStream r9 = r0.getErrorStream()     // Catch:{ all -> 0x0124 }
            if (r9 == 0) goto L_0x010e
            byte[] r10 = b((java.io.InputStream) r9)     // Catch:{ all -> 0x0124 }
            java.lang.String r11 = new java.lang.String     // Catch:{ all -> 0x0124 }
            r11.<init>(r10, r1)     // Catch:{ all -> 0x0124 }
            r10 = r11
        L_0x010e:
            java.net.HttpRetryException r11 = new java.net.HttpRetryException     // Catch:{ all -> 0x0124 }
            r11.<init>(r10, r2)     // Catch:{ all -> 0x0124 }
            throw r11     // Catch:{ all -> 0x0124 }
        L_0x0114:
            java.lang.String r1 = "Location"
            java.lang.String r1 = r0.getHeaderField(r1)     // Catch:{ all -> 0x0124 }
            byte[] r9 = b(r1, r10, r11, r12, r13)     // Catch:{ all -> 0x0124 }
            if (r0 == 0) goto L_0x0123
            r0.disconnect()
        L_0x0123:
            return r9
        L_0x0124:
            r10 = move-exception
            goto L_0x0128
        L_0x0126:
            r10 = move-exception
            r0 = r9
        L_0x0128:
            if (r9 == 0) goto L_0x012d
            r9.close()
        L_0x012d:
            if (r0 == 0) goto L_0x0132
            r0.disconnect()
        L_0x0132:
            goto L_0x0134
        L_0x0133:
            throw r10
        L_0x0134:
            goto L_0x0133
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.f.b(java.lang.String, java.lang.Object, boolean, int, com.appnext.base.b.d$a):byte[]");
    }

    public static InputStream a(InputStream inputStream) throws IOException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 2);
        byte[] bArr = new byte[2];
        try {
            pushbackInputStream.unread(bArr, 0, pushbackInputStream.read(bArr));
            return (bArr[0] == 31 && bArr[1] == -117) ? new GZIPInputStream(pushbackInputStream) : pushbackInputStream;
        } catch (Throwable unused) {
            return inputStream;
        }
    }

    private static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[d.fb];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:6|(4:8|9|10|(4:13|(2:15|40)(1:39)|38|11))|16|17|18|(3:19|20|(2:(1:23)|24)(5:25|26|(1:28)|(1:30)|31))) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0042 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0071 A[SYNTHETIC, Splitter:B:25:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap Y(java.lang.String r4) {
        /*
            r0 = 0
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r1 = gR     // Catch:{ all -> 0x0086 }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x0012
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r1 = gR     // Catch:{ all -> 0x0086 }
            java.lang.Object r4 = r1.get(r4)     // Catch:{ all -> 0x0086 }
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4     // Catch:{ all -> 0x0086 }
            return r4
        L_0x0012:
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r1 = gR     // Catch:{ all -> 0x0086 }
            java.util.Set r1 = r1.keySet()     // Catch:{ all -> 0x0086 }
            int r1 = r1.size()     // Catch:{ all -> 0x0086 }
            r2 = 50
            if (r1 <= r2) goto L_0x0042
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r1 = gR     // Catch:{ all -> 0x0086 }
            java.util.Set r1 = r1.keySet()     // Catch:{ all -> 0x0042 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0042 }
        L_0x002a:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0042 }
            if (r2 == 0) goto L_0x0042
            r1.next()     // Catch:{ all -> 0x0042 }
            java.util.Random r2 = new java.util.Random     // Catch:{ all -> 0x0042 }
            r2.<init>()     // Catch:{ all -> 0x0042 }
            boolean r2 = r2.nextBoolean()     // Catch:{ all -> 0x0042 }
            if (r2 == 0) goto L_0x002a
            r1.remove()     // Catch:{ all -> 0x0042 }
            goto L_0x002a
        L_0x0042:
            java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x0086 }
            r1.<init>(r4)     // Catch:{ all -> 0x0086 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = "performURLCall "
            r2.<init>(r3)     // Catch:{ all -> 0x0086 }
            r2.append(r4)     // Catch:{ all -> 0x0086 }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ all -> 0x0086 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ all -> 0x0086 }
            r2 = 1
            r1.setDoInput(r2)     // Catch:{ all -> 0x0084 }
            r1.connect()     // Catch:{ all -> 0x0084 }
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ all -> 0x0084 }
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeStream(r2)     // Catch:{ all -> 0x0084 }
            r2.close()     // Catch:{ all -> 0x0084 }
            if (r3 != 0) goto L_0x0071
            if (r1 == 0) goto L_0x0070
            r1.disconnect()
        L_0x0070:
            return r0
        L_0x0071:
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r2 = gR     // Catch:{ all -> 0x0084 }
            boolean r2 = r2.containsKey(r4)     // Catch:{ all -> 0x0084 }
            if (r2 != 0) goto L_0x007e
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r2 = gR     // Catch:{ all -> 0x0084 }
            r2.put(r4, r3)     // Catch:{ all -> 0x0084 }
        L_0x007e:
            if (r1 == 0) goto L_0x0083
            r1.disconnect()
        L_0x0083:
            return r3
        L_0x0084:
            goto L_0x0087
        L_0x0086:
            r1 = r0
        L_0x0087:
            if (r1 == 0) goto L_0x008c
            r1.disconnect()
        L_0x008c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.f.Y(java.lang.String):android.graphics.Bitmap");
    }

    private static void d(HashMap<String, Bitmap> hashMap) {
        try {
            Iterator<String> it = hashMap.keySet().iterator();
            while (it.hasNext()) {
                it.next();
                if (new Random().nextBoolean()) {
                    it.remove();
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static String b(List<Pair<String, String>> list, boolean z) {
        StringBuilder sb = new StringBuilder();
        boolean z2 = true;
        for (Pair next : list) {
            try {
                if (next.first != null) {
                    if (next.second != null) {
                        StringBuilder sb2 = new StringBuilder();
                        if (z2) {
                            z2 = false;
                        } else {
                            sb2.append("&");
                        }
                        if (z) {
                            sb2.append(URLEncoder.encode(URLDecoder.decode((String) next.first, "UTF-8"), "UTF-8"));
                            sb2.append("=");
                            sb2.append(URLEncoder.encode(URLDecoder.decode((String) next.second, "UTF-8"), "UTF-8"));
                        } else {
                            sb2.append(URLEncoder.encode((String) next.first, "UTF-8"));
                            sb2.append("=");
                            sb2.append(URLEncoder.encode((String) next.second, "UTF-8"));
                        }
                        StringBuilder sb3 = new StringBuilder("params: key: ");
                        sb3.append((String) next.first);
                        sb3.append(" value: ");
                        sb3.append((String) next.second);
                        sb.append(sb2);
                    }
                }
            } catch (Throwable unused) {
            }
        }
        new StringBuilder("raw params: ").append(sb.toString());
        return sb.toString();
    }

    public static int a(Context context, float f) {
        return (int) (f * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        String str10 = str6;
        try {
            if (str10.equals(a.I) || str10.equals(a.M)) {
                final String str11 = str5;
                final String str12 = str6;
                final String str13 = str;
                final String str14 = str2;
                final String str15 = str3;
                final String str16 = str4;
                final String str17 = str7;
                final String str18 = str8;
                final String str19 = str9;
                new Thread(new Runnable() {
                    public final void run() {
                        String str;
                        String str2;
                        try {
                            str = URLEncoder.encode(str11.replace(" ", "_"), "UTF-8");
                        } catch (Throwable unused) {
                            str = "";
                        }
                        try {
                            str2 = URLEncoder.encode(str12.replace(" ", "_"), "UTF-8");
                        } catch (Throwable unused2) {
                            str2 = "";
                        }
                        Object[] objArr = new Object[10];
                        objArr[0] = str13;
                        objArr[1] = str14;
                        objArr[2] = "100";
                        objArr[3] = str15;
                        objArr[4] = str;
                        objArr[5] = str16;
                        objArr[6] = str2;
                        objArr[7] = str17;
                        String str3 = "0";
                        objArr[8] = str18.equals("") ? str3 : str18;
                        if (!str19.equals("")) {
                            str3 = str19;
                        }
                        objArr[9] = str3;
                        String format = String.format("https://admin.appnext.com/tp12.aspx?tid=%s&vid=%s&osid=%s&auid=%s&session_id=%s&pid=%s&ref=%s&ads_type=%s&bid=%s&cid=%s", objArr);
                        try {
                            new StringBuilder("report: ").append(format);
                            f.a(format, (HashMap<String, String>) null);
                        } catch (Throwable th) {
                            new StringBuilder("report error: ").append(th.getMessage());
                        }
                    }
                }).start();
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            final Context context2 = context;
            final String str9 = str;
            final String str10 = str2;
            final String str11 = str3;
            final String str12 = str4;
            final String str13 = str5;
            final String str14 = str6;
            final String str15 = str7;
            final String str16 = str8;
            new Thread(new Runnable() {
                public final void run() {
                    String str;
                    try {
                        str = f.q(context2);
                    } catch (Throwable unused) {
                        str = "";
                    }
                    f.a(str9, str10, str11, str12, str, str13, str14, str15, str16);
                }
            }).start();
        } catch (Throwable unused) {
        }
    }

    public static void a(Ad ad, AppnextAd appnextAd, String str, String str2, p pVar) {
        final p pVar2 = pVar;
        final Ad ad2 = ad;
        final AppnextAd appnextAd2 = appnextAd;
        final String str3 = str2;
        final String str4 = str;
        new Thread(new Runnable() {
            public final void run() {
                try {
                    if (Boolean.parseBoolean(pVar2.get("stp_flag"))) {
                        ArrayList arrayList = new ArrayList();
                        StringBuilder sb = new StringBuilder();
                        sb.append(System.currentTimeMillis());
                        arrayList.add(new Pair("client_date", sb.toString()));
                        arrayList.add(new Pair("did", f.b(ad2.getContext(), true)));
                        arrayList.add(new Pair("session_id", appnextAd2.getSession()));
                        arrayList.add(new Pair("tid", ad2.getTID()));
                        arrayList.add(new Pair("vid", ad2.getVID()));
                        arrayList.add(new Pair("auid", ad2.getAUID()));
                        arrayList.add(new Pair("osid", "100"));
                        arrayList.add(new Pair("tem_id", str3));
                        arrayList.add(new Pair("pid", ad2.getPlacementID()));
                        arrayList.add(new Pair("banner_id", appnextAd2.getBannerID()));
                        arrayList.add(new Pair("cm_id", appnextAd2.getCampaignID()));
                        arrayList.add(new Pair("event_name", str4));
                        arrayList.add(new Pair("package_id", ad2.getContext().getPackageName()));
                        f.a("https://global.appnext.com/stp.aspx", (ArrayList<Pair<String, String>>) arrayList, (int) f.fd);
                    }
                } catch (Throwable unused) {
                }
            }
        }).start();
    }

    public static boolean c(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String m(String str, String str2) {
        String cookie = android.webkit.CookieManager.getInstance().getCookie(str);
        if (cookie == null) {
            return "";
        }
        String str3 = "";
        for (String str4 : cookie.split(";")) {
            if (str4.contains(str2)) {
                String[] split = str4.split("=");
                if (split.length <= 1) {
                    return "";
                }
                str3 = split[1];
            }
        }
        return str3;
    }

    public static String n(Context context) {
        if (context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
            return "Unknown";
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "-";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "wifi";
        }
        if (activeNetworkInfo.getType() != 0) {
            return "Unknown";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(activeNetworkInfo.getSubtype());
        return sb.toString();
    }

    public static String o(Context context) {
        if (context == null || context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
            return "Unknown";
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "-";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "wifi";
        }
        if (activeNetworkInfo.getType() != 0) {
            return "Unknown";
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return "Unknown";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0056 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int Z(java.lang.String r7) {
        /*
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r7 = r7.toLowerCase(r0)
            int r0 = r7.hashCode()
            r1 = 1653(0x675, float:2.316E-42)
            r2 = 0
            r3 = -1
            r4 = 3
            r5 = 2
            r6 = 1
            if (r0 == r1) goto L_0x003f
            r1 = 1684(0x694, float:2.36E-42)
            if (r0 == r1) goto L_0x0035
            r1 = 1715(0x6b3, float:2.403E-42)
            if (r0 == r1) goto L_0x002b
            r1 = 3649301(0x37af15, float:5.11376E-39)
            if (r0 == r1) goto L_0x0021
            goto L_0x0049
        L_0x0021:
            java.lang.String r0 = "wifi"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0049
            r7 = 3
            goto L_0x004a
        L_0x002b:
            java.lang.String r0 = "4g"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0049
            r7 = 2
            goto L_0x004a
        L_0x0035:
            java.lang.String r0 = "3g"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0049
            r7 = 1
            goto L_0x004a
        L_0x003f:
            java.lang.String r0 = "2g"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0049
            r7 = 0
            goto L_0x004a
        L_0x0049:
            r7 = -1
        L_0x004a:
            if (r7 == 0) goto L_0x0056
            if (r7 == r6) goto L_0x0055
            if (r7 == r5) goto L_0x0054
            if (r7 == r4) goto L_0x0053
            return r3
        L_0x0053:
            return r4
        L_0x0054:
            return r5
        L_0x0055:
            return r6
        L_0x0056:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.f.Z(java.lang.String):int");
    }

    public static String b(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String obj = stringWriter.toString();
        return obj.length() > 512 ? obj.substring(0, AdRequest.MAX_CONTENT_URL_LENGTH) : obj;
    }

    public static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || context.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
            return false;
        }
        return true;
    }

    public static String p(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getSimState() != 5) {
            return "";
        }
        String simOperator = telephonyManager.getSimOperator();
        return simOperator.substring(0, 3) + "_" + simOperator.substring(3);
    }

    public static String bh() {
        return Locale.getDefault().getLanguage();
    }

    public static String q(Context context) {
        if (gU.equals("")) {
            synchronized ("2.5.1.472") {
                if (gU.equals("")) {
                    gU = r(context);
                }
            }
        }
        return gU;
    }

    private static String aa(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            int length = digest.length;
            StringBuilder sb = new StringBuilder(length << 1);
            for (int i = 0; i < length; i++) {
                sb.append(Character.forDigit((digest[i] & 240) >> 4, 16));
                sb.append(Character.forDigit(digest[i] & 15, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return c(32);
        }
    }

    private static String c(int i) {
        char[] charArray = "0123456789abcdef".toCharArray();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 32; i2++) {
            sb.append(charArray[random.nextInt(charArray.length)]);
        }
        return sb.toString();
    }

    public static String c(String str) {
        String substring = str.substring(str.lastIndexOf("/") + 1);
        if (substring.contains("?")) {
            substring = substring.substring(0, substring.indexOf("?"));
        }
        try {
            String queryParameter = Uri.parse(str).getQueryParameter("rnd");
            if (queryParameter == null || queryParameter.equals("")) {
                return substring;
            }
            return substring.substring(0, substring.lastIndexOf(46)) + "_" + queryParameter + substring.substring(substring.lastIndexOf(46));
        } catch (Throwable unused) {
            return substring;
        }
    }

    public static String g(AppnextAd appnextAd) {
        String str;
        StringBuilder sb = new StringBuilder("https://www.appnext.com/privacy_policy/index.html?z=");
        sb.append(new Random().nextInt(10));
        sb.append(appnextAd.getZoneID());
        sb.append(new Random().nextInt(10));
        if (appnextAd.isGdpr()) {
            str = "&edda=1&geo=" + appnextAd.getCountry();
        } else {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }

    public static boolean s(Context context) {
        try {
            if (context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
                a("http://www.appnext.com/myid.html", (HashMap<String, String>) null);
                return true;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
            throw new IOException();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String bi() {
        String[] split = "2.5.1.472".split("\\.");
        if (split.length < 4) {
            return "2.5.1.472";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(split[i]);
            if (i < 2) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public static void a(File file) {
        if (file.isDirectory()) {
            for (File a2 : file.listFiles()) {
                a(a2);
            }
        }
        file.delete();
    }

    public static String r(Context context) {
        String b = b(context, true);
        if (b.equals("")) {
            return c(32);
        }
        return aa(b + "_" + (System.currentTimeMillis() / 1000));
    }
}
