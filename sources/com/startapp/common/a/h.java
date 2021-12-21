package com.startapp.common.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.nio.ByteOrder;
import java.util.Map;

/* compiled from: StartAppSDK */
public class h {

    /* compiled from: StartAppSDK */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private String f342a;
        private String b;
        private String c;

        public String a() {
            return this.f342a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public void a(String str) {
            this.f342a = str;
        }

        public void b(String str) {
            this.b = str;
        }

        public void c(String str) {
            this.c = str;
        }
    }

    /* compiled from: StartAppSDK */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f341a;
        private String b;

        public String a() {
            return this.f341a;
        }

        public void a(String str) {
            this.f341a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public String toString() {
            return "HttpResult: " + this.b + " " + this.f341a;
        }
    }

    static {
        if (Build.VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    public static String a(Context context, String str, byte[] bArr, Map<String, String> map, String str2, boolean z) {
        return a(context, str, bArr, map, str2, z, "application/json");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:14|(2:18|19)|20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b3, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b4, code lost:
        r0 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b7, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00e9, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0027 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0021 A[SYNTHETIC, Splitter:B:18:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b7 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x0008] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00e2 A[SYNTHETIC, Splitter:B:70:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r4, java.lang.String r5, byte[] r6, java.util.Map<java.lang.String, java.lang.String> r7, java.lang.String r8, boolean r9, java.lang.String r10) {
        /*
            r0 = 0
            r1 = 0
            java.net.HttpURLConnection r4 = b(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x00c0, all -> 0x00bd }
            if (r6 == 0) goto L_0x0028
            int r5 = r6.length     // Catch:{ Exception -> 0x00b9, all -> 0x00b7 }
            if (r5 <= 0) goto L_0x0028
            java.io.OutputStream r5 = r4.getOutputStream()     // Catch:{ all -> 0x001d }
            r5.write(r6)     // Catch:{ all -> 0x001b }
            if (r5 == 0) goto L_0x0028
            r5.flush()     // Catch:{ IOException -> 0x0028 }
            r5.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x0028
        L_0x001b:
            r6 = move-exception
            goto L_0x001f
        L_0x001d:
            r6 = move-exception
            r5 = r1
        L_0x001f:
            if (r5 == 0) goto L_0x0027
            r5.flush()     // Catch:{ IOException -> 0x0027 }
            r5.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0027:
            throw r6     // Catch:{ Exception -> 0x00b9, all -> 0x00b7 }
        L_0x0028:
            int r5 = r4.getResponseCode()     // Catch:{ Exception -> 0x00b9, all -> 0x00b7 }
            r6 = 200(0xc8, float:2.8E-43)
            r7 = -1
            java.lang.String r8 = "UTF-8"
            r9 = 1024(0x400, float:1.435E-42)
            if (r5 == r6) goto L_0x0079
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            r6.<init>()     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            java.lang.String r10 = "Error code = ["
            r6.append(r10)     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            r6.append(r5)     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            r10 = 93
            r6.append(r10)     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            java.io.InputStream r1 = r4.getErrorStream()     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            if (r1 == 0) goto L_0x006f
            java.io.StringWriter r10 = new java.io.StringWriter     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            r10.<init>()     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            char[] r9 = new char[r9]     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            r3.<init>(r1, r8)     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
        L_0x005e:
            int r8 = r2.read(r9)     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            if (r8 == r7) goto L_0x0068
            r10.write(r9, r0, r8)     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            goto L_0x005e
        L_0x0068:
            java.lang.String r7 = r10.toString()     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            r6.append(r7)     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
        L_0x006f:
            java.lang.Exception r7 = new java.lang.Exception     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            throw r7     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
        L_0x0079:
            java.io.InputStream r6 = r4.getInputStream()     // Catch:{ Exception -> 0x00b3, all -> 0x00b7 }
            if (r6 == 0) goto L_0x00a6
            java.io.StringWriter r10 = new java.io.StringWriter     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r10.<init>()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            char[] r9 = new char[r9]     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r2.<init>(r6, r8)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r1.<init>(r2)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
        L_0x0090:
            int r8 = r1.read(r9)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            if (r8 == r7) goto L_0x009a
            r10.write(r9, r0, r8)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            goto L_0x0090
        L_0x009a:
            java.lang.String r1 = r10.toString()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            goto L_0x00a6
        L_0x009f:
            r5 = move-exception
            goto L_0x00df
        L_0x00a1:
            r7 = move-exception
            r1 = r4
            r0 = r5
            r5 = r7
            goto L_0x00c2
        L_0x00a6:
            if (r6 == 0) goto L_0x00ad
            r6.close()     // Catch:{ IOException -> 0x00ac }
            goto L_0x00ad
        L_0x00ac:
        L_0x00ad:
            if (r4 == 0) goto L_0x00b2
            r4.disconnect()
        L_0x00b2:
            return r1
        L_0x00b3:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x00ba
        L_0x00b7:
            r5 = move-exception
            goto L_0x00e0
        L_0x00b9:
            r5 = move-exception
        L_0x00ba:
            r6 = r1
            r1 = r4
            goto L_0x00c2
        L_0x00bd:
            r5 = move-exception
            r4 = r1
            goto L_0x00e0
        L_0x00c0:
            r5 = move-exception
            r6 = r1
        L_0x00c2:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dd }
            r4.<init>()     // Catch:{ all -> 0x00dd }
            java.lang.String r7 = "Error execute Exception "
            r4.append(r7)     // Catch:{ all -> 0x00dd }
            java.lang.String r7 = r5.getMessage()     // Catch:{ all -> 0x00dd }
            r4.append(r7)     // Catch:{ all -> 0x00dd }
            com.startapp.common.e r7 = new com.startapp.common.e     // Catch:{ all -> 0x00dd }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00dd }
            r7.<init>((java.lang.String) r4, (java.lang.Throwable) r5, (int) r0)     // Catch:{ all -> 0x00dd }
            throw r7     // Catch:{ all -> 0x00dd }
        L_0x00dd:
            r5 = move-exception
            r4 = r1
        L_0x00df:
            r1 = r6
        L_0x00e0:
            if (r1 == 0) goto L_0x00e7
            r1.close()     // Catch:{ IOException -> 0x00e6 }
            goto L_0x00e7
        L_0x00e6:
        L_0x00e7:
            if (r4 == 0) goto L_0x00ec
            r4.disconnect()
        L_0x00ec:
            goto L_0x00ee
        L_0x00ed:
            throw r5
        L_0x00ee:
            goto L_0x00ed
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.a.h.a(android.content.Context, java.lang.String, byte[], java.util.Map, java.lang.String, boolean, java.lang.String):java.lang.String");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r10v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r10v6 */
    /* JADX WARNING: type inference failed for: r10v8 */
    /* JADX WARNING: type inference failed for: r10v9 */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ae, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00af, code lost:
        r9 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b1, code lost:
        r11 = th;
        r10 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b3, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b4, code lost:
        r13 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b1 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00dc A[SYNTHETIC, Splitter:B:55:0x00dc] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e3  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.startapp.common.a.h.a a(android.content.Context r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.lang.String r13, boolean r14) {
        /*
            r2 = 0
            r6 = 0
            r7 = 1
            r8 = 0
            r9 = 0
            r0 = r10
            r1 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            java.net.HttpURLConnection r10 = b(r0, r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            int r12 = r10.getResponseCode()     // Catch:{ Exception -> 0x00b3, all -> 0x00b1 }
            r13 = 200(0xc8, float:2.8E-43)
            r14 = -1
            java.lang.String r0 = "UTF-8"
            r1 = 1024(0x400, float:1.435E-42)
            if (r12 != r13) goto L_0x0064
            com.startapp.common.d.a.b(r10, r11)     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            java.io.InputStream r8 = r10.getInputStream()     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            com.startapp.common.a.h$a r11 = new com.startapp.common.a.h$a     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            r11.<init>()     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            java.lang.String r13 = r10.getContentType()     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            r11.b(r13)     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            if (r8 == 0) goto L_0x0052
            java.io.StringWriter r13 = new java.io.StringWriter     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            r13.<init>()     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            char[] r1 = new char[r1]     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            r3.<init>(r8, r0)     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
        L_0x0041:
            int r0 = r2.read(r1)     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            if (r0 == r14) goto L_0x004b
            r13.write(r1, r9, r0)     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            goto L_0x0041
        L_0x004b:
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
            r11.a(r13)     // Catch:{ Exception -> 0x005f, all -> 0x00b1 }
        L_0x0052:
            if (r8 == 0) goto L_0x0059
            r8.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x0059
        L_0x0058:
        L_0x0059:
            if (r10 == 0) goto L_0x005e
            r10.disconnect()
        L_0x005e:
            return r11
        L_0x005f:
            r11 = move-exception
            r9 = r12
            r13 = r8
            r7 = 0
            goto L_0x00b5
        L_0x0064:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ae, all -> 0x00b1 }
            r11.<init>()     // Catch:{ Exception -> 0x00ae, all -> 0x00b1 }
            java.lang.String r13 = "Error sendGetWithResponse code = ["
            r11.append(r13)     // Catch:{ Exception -> 0x00ae, all -> 0x00b1 }
            r11.append(r12)     // Catch:{ Exception -> 0x00ae, all -> 0x00b1 }
            r13 = 93
            r11.append(r13)     // Catch:{ Exception -> 0x00ae, all -> 0x00b1 }
            java.io.InputStream r13 = r10.getErrorStream()     // Catch:{ Exception -> 0x00ae, all -> 0x00b1 }
            if (r13 == 0) goto L_0x009e
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            r2.<init>()     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            char[] r1 = new char[r1]     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            r4.<init>(r13, r0)     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
        L_0x008d:
            int r0 = r3.read(r1)     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            if (r0 == r14) goto L_0x0097
            r2.write(r1, r9, r0)     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            goto L_0x008d
        L_0x0097:
            java.lang.String r14 = r2.toString()     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            r11.append(r14)     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
        L_0x009e:
            com.startapp.common.e r14 = new com.startapp.common.e     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            r14.<init>(r11, r8, r7, r12)     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
            throw r14     // Catch:{ Exception -> 0x00aa, all -> 0x00a8 }
        L_0x00a8:
            r11 = move-exception
            goto L_0x00d9
        L_0x00aa:
            r11 = move-exception
            r8 = r10
            r9 = r12
            goto L_0x00bc
        L_0x00ae:
            r11 = move-exception
            r9 = r12
            goto L_0x00b4
        L_0x00b1:
            r11 = move-exception
            goto L_0x00da
        L_0x00b3:
            r11 = move-exception
        L_0x00b4:
            r13 = r8
        L_0x00b5:
            r8 = r10
            goto L_0x00bc
        L_0x00b7:
            r11 = move-exception
            r10 = r8
            goto L_0x00da
        L_0x00ba:
            r11 = move-exception
            r13 = r8
        L_0x00bc:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d7 }
            r10.<init>()     // Catch:{ all -> 0x00d7 }
            java.lang.String r12 = "Error execute Exception "
            r10.append(r12)     // Catch:{ all -> 0x00d7 }
            java.lang.String r12 = r11.getMessage()     // Catch:{ all -> 0x00d7 }
            r10.append(r12)     // Catch:{ all -> 0x00d7 }
            com.startapp.common.e r12 = new com.startapp.common.e     // Catch:{ all -> 0x00d7 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x00d7 }
            r12.<init>(r10, r11, r7, r9)     // Catch:{ all -> 0x00d7 }
            throw r12     // Catch:{ all -> 0x00d7 }
        L_0x00d7:
            r11 = move-exception
            r10 = r8
        L_0x00d9:
            r8 = r13
        L_0x00da:
            if (r8 == 0) goto L_0x00e1
            r8.close()     // Catch:{ IOException -> 0x00e0 }
            goto L_0x00e1
        L_0x00e0:
        L_0x00e1:
            if (r10 == 0) goto L_0x00e6
            r10.disconnect()
        L_0x00e6:
            goto L_0x00e8
        L_0x00e7:
            throw r11
        L_0x00e8:
            goto L_0x00e7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.a.h.a(android.content.Context, java.lang.String, java.util.Map, java.lang.String, boolean):com.startapp.common.a.h$a");
    }

    private static HttpURLConnection b(Context context, String str, byte[] bArr, Map<String, String> map, String str2, boolean z, String str3) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.addRequestProperty("Cache-Control", "no-cache");
        com.startapp.common.d.a.a(httpURLConnection, str);
        if (!(str2 == null || str2.compareTo("-1") == 0)) {
            httpURLConnection.addRequestProperty("User-Agent", str2);
        }
        httpURLConnection.setRequestProperty("Accept", "application/json;text/html;text/plain");
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(10000);
        if (bArr != null) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            httpURLConnection.setRequestProperty("Content-Type", str3);
            if (z) {
                httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
            }
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        return httpURLConnection;
    }

    public static String a(Context context) {
        String str = "WIFI";
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "e100";
            }
            if (!c.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                return "e105";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                str = "e102";
            } else {
                String typeName = activeNetworkInfo.getTypeName();
                if (typeName.toLowerCase().compareTo(str.toLowerCase()) != 0) {
                    if (typeName.toLowerCase().compareTo("MOBILE".toLowerCase()) == 0) {
                        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                        str = telephonyManager != null ? Integer.toString(telephonyManager.getNetworkType()) : "e101";
                    } else {
                        str = "e100";
                    }
                }
            }
            return str;
        } catch (Exception unused) {
            return "e100";
        }
    }

    public static b a(Context context, String str) {
        if (str.toLowerCase().compareTo("WIFI".toLowerCase()) == 0) {
            return b(context);
        }
        return null;
    }

    public static b b(Context context) {
        String str = "e100";
        b bVar = new b();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            String str2 = "e105";
            if (connectivityManager != null) {
                if (c.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                        str2 = "e102";
                    } else if (activeNetworkInfo.getTypeName().compareTo("WIFI") != 0) {
                        str2 = "e103";
                    } else if (c.a(context, "android.permission.ACCESS_WIFI_STATE")) {
                        int rssi = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getRssi();
                        bVar.c(Integer.toString(WifiManager.calculateSignalLevel(rssi, 5)));
                        bVar.b(Integer.toString(rssi));
                        str2 = null;
                    }
                }
                str = str2;
            }
        } catch (Exception unused) {
        }
        bVar.a(str);
        return bVar;
    }

    public static Boolean c(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || !c.a(context, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return null;
        }
        return Boolean.valueOf(activeNetworkInfo.isRoaming());
    }

    public static String a(WifiInfo wifiInfo) {
        int ipAddress = wifiInfo.getIpAddress();
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }
        try {
            return InetAddress.getByAddress(BigInteger.valueOf((long) ipAddress).toByteArray()).getHostAddress();
        } catch (Exception unused) {
            return null;
        }
    }
}
