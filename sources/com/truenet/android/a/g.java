package com.truenet.android.a;

import a.a.b.b.i;
import a.a.b.b.m;
import java.io.BufferedReader;

/* compiled from: StartAppSDK */
public final class g {
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0094 A[SYNTHETIC, Splitter:B:32:0x0094] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean a(java.net.URL r6, java.lang.String r7, android.content.Context r8) {
        /*
            java.lang.String r0 = "stream closed with error!"
            java.lang.String r1 = "$receiver"
            a.a.b.b.h.b(r6, r1)
            java.lang.String r1 = "data"
            a.a.b.b.h.b(r7, r1)
            r1 = 0
            r2 = r1
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
            java.io.BufferedOutputStream r1 = (java.io.BufferedOutputStream) r1
            r3 = 0
            java.net.URLConnection r4 = r6.openConnection()     // Catch:{ all -> 0x0092 }
            if (r4 == 0) goto L_0x008a
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ all -> 0x0092 }
            java.lang.String r2 = "Cache-Control"
            java.lang.String r5 = "no-cache"
            r4.setRequestProperty(r2, r5)     // Catch:{ all -> 0x0088 }
            if (r8 == 0) goto L_0x002f
            java.lang.String r2 = "User-Agent"
            com.truenet.android.a.i$a r5 = com.truenet.android.a.i.f668a     // Catch:{ all -> 0x0088 }
            java.lang.String r8 = r5.a(r8)     // Catch:{ all -> 0x0088 }
            r4.setRequestProperty(r2, r8)     // Catch:{ all -> 0x0088 }
        L_0x002f:
            java.lang.String r8 = "PUT"
            r4.setRequestMethod(r8)     // Catch:{ all -> 0x0088 }
            r8 = 1
            r4.setDoOutput(r8)     // Catch:{ all -> 0x0088 }
            java.lang.String r2 = "Content-Type"
            java.lang.String r5 = "text/html"
            r4.setRequestProperty(r2, r5)     // Catch:{ all -> 0x0088 }
            java.nio.charset.Charset r2 = a.a.e.a.f971a     // Catch:{ all -> 0x0088 }
            byte[] r7 = r7.getBytes(r2)     // Catch:{ all -> 0x0088 }
            java.lang.String r2 = "(this as java.lang.String).getBytes(charset)"
            a.a.b.b.h.a((java.lang.Object) r7, (java.lang.String) r2)     // Catch:{ all -> 0x0088 }
            int r2 = r7.length     // Catch:{ all -> 0x0088 }
            r4.setFixedLengthStreamingMode(r2)     // Catch:{ all -> 0x0088 }
            r2 = 50000(0xc350, float:7.0065E-41)
            r4.setConnectTimeout(r2)     // Catch:{ all -> 0x0088 }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0088 }
            java.io.OutputStream r5 = r4.getOutputStream()     // Catch:{ all -> 0x0088 }
            r2.<init>(r5)     // Catch:{ all -> 0x0088 }
            r2.write(r7)     // Catch:{ all -> 0x0087 }
            r2.flush()     // Catch:{ all -> 0x0087 }
            r7 = 299(0x12b, float:4.19E-43)
            r1 = 200(0xc8, float:2.8E-43)
            int r5 = r4.getResponseCode()     // Catch:{ all -> 0x0087 }
            if (r1 <= r5) goto L_0x006e
            goto L_0x0071
        L_0x006e:
            if (r7 < r5) goto L_0x0071
            r3 = 1
        L_0x0071:
            r2.close()     // Catch:{ all -> 0x0075 }
            goto L_0x0081
        L_0x0075:
            r7 = move-exception
            java.lang.Class r6 = r6.getClass()
            java.lang.String r6 = r6.getCanonicalName()
            android.util.Log.e(r6, r0, r7)
        L_0x0081:
            if (r4 == 0) goto L_0x0086
            r4.disconnect()
        L_0x0086:
            return r3
        L_0x0087:
            r1 = r2
        L_0x0088:
            r2 = r4
            goto L_0x0092
        L_0x008a:
            a.a.h r7 = new a.a.h     // Catch:{ all -> 0x0092 }
            java.lang.String r8 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            r7.<init>(r8)     // Catch:{ all -> 0x0092 }
            throw r7     // Catch:{ all -> 0x0092 }
        L_0x0092:
            if (r1 == 0) goto L_0x00a4
            r1.close()     // Catch:{ all -> 0x0098 }
            goto L_0x00a4
        L_0x0098:
            r7 = move-exception
            java.lang.Class r6 = r6.getClass()
            java.lang.String r6 = r6.getCanonicalName()
            android.util.Log.e(r6, r0, r7)
        L_0x00a4:
            if (r2 == 0) goto L_0x00a9
            r2.disconnect()
        L_0x00a9:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.truenet.android.a.g.a(java.net.URL, java.lang.String, android.content.Context):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00de A[SYNTHETIC, Splitter:B:42:0x00de] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e6 A[Catch:{ all -> 0x00e2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String b(java.net.URL r7, java.lang.String r8, android.content.Context r9) {
        /*
            java.lang.String r0 = "stream closed with error!"
            java.lang.String r1 = "$receiver"
            a.a.b.b.h.b(r7, r1)
            java.lang.String r1 = "data"
            a.a.b.b.h.b(r8, r1)
            r1 = 0
            r2 = r1
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
            r3 = r1
            java.io.BufferedOutputStream r3 = (java.io.BufferedOutputStream) r3
            r4 = r1
            java.io.BufferedInputStream r4 = (java.io.BufferedInputStream) r4
            java.net.URLConnection r5 = r7.openConnection()     // Catch:{ all -> 0x00dc }
            if (r5 == 0) goto L_0x00d4
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ all -> 0x00dc }
            java.lang.String r2 = "Cache-Control"
            java.lang.String r6 = "no-cache"
            r5.setRequestProperty(r2, r6)     // Catch:{ all -> 0x00d2 }
            if (r9 == 0) goto L_0x0032
            java.lang.String r2 = "User-Agent"
            com.truenet.android.a.i$a r6 = com.truenet.android.a.i.f668a     // Catch:{ all -> 0x00d2 }
            java.lang.String r9 = r6.a(r9)     // Catch:{ all -> 0x00d2 }
            r5.setRequestProperty(r2, r9)     // Catch:{ all -> 0x00d2 }
        L_0x0032:
            java.lang.String r9 = "POST"
            r5.setRequestMethod(r9)     // Catch:{ all -> 0x00d2 }
            r9 = 1
            r5.setDoOutput(r9)     // Catch:{ all -> 0x00d2 }
            r5.setDoInput(r9)     // Catch:{ all -> 0x00d2 }
            java.nio.charset.Charset r9 = a.a.e.a.f971a     // Catch:{ all -> 0x00d2 }
            byte[] r8 = r8.getBytes(r9)     // Catch:{ all -> 0x00d2 }
            java.lang.String r9 = "(this as java.lang.String).getBytes(charset)"
            a.a.b.b.h.a((java.lang.Object) r8, (java.lang.String) r9)     // Catch:{ all -> 0x00d2 }
            int r9 = r8.length     // Catch:{ all -> 0x00d2 }
            r5.setFixedLengthStreamingMode(r9)     // Catch:{ all -> 0x00d2 }
            java.lang.String r9 = "Content-Type"
            java.lang.String r2 = "application/json"
            r5.setRequestProperty(r9, r2)     // Catch:{ all -> 0x00d2 }
            r9 = 50000(0xc350, float:7.0065E-41)
            r5.setConnectTimeout(r9)     // Catch:{ all -> 0x00d2 }
            java.io.BufferedOutputStream r9 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00d2 }
            java.io.OutputStream r2 = r5.getOutputStream()     // Catch:{ all -> 0x00d2 }
            r9.<init>(r2)     // Catch:{ all -> 0x00d2 }
            r9.write(r8)     // Catch:{ all -> 0x00d1 }
            r9.flush()     // Catch:{ all -> 0x00d1 }
            int r8 = r5.getResponseCode()     // Catch:{ all -> 0x00d1 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r8 != r2) goto L_0x00b6
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d1 }
            r8.<init>()     // Catch:{ all -> 0x00d1 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00d1 }
            java.io.InputStream r3 = r5.getInputStream()     // Catch:{ all -> 0x00d1 }
            r2.<init>(r3)     // Catch:{ all -> 0x00d1 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x00b3 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ all -> 0x00b3 }
            r6 = r2
            java.io.InputStream r6 = (java.io.InputStream) r6     // Catch:{ all -> 0x00b3 }
            r4.<init>(r6)     // Catch:{ all -> 0x00b3 }
            java.io.Reader r4 = (java.io.Reader) r4     // Catch:{ all -> 0x00b3 }
            r3.<init>(r4)     // Catch:{ all -> 0x00b3 }
            a.a.b.b.m$a r4 = new a.a.b.b.m$a     // Catch:{ all -> 0x00b3 }
            r4.<init>()     // Catch:{ all -> 0x00b3 }
            r6 = r1
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x00b3 }
            r4.element = r6     // Catch:{ all -> 0x00b3 }
        L_0x0098:
            com.truenet.android.a.g$a r6 = new com.truenet.android.a.g$a     // Catch:{ all -> 0x00b3 }
            r6.<init>(r4, r3)     // Catch:{ all -> 0x00b3 }
            a.a.b.a.a r6 = (a.a.b.a.a) r6     // Catch:{ all -> 0x00b3 }
            java.lang.Object r6 = r6.a()     // Catch:{ all -> 0x00b3 }
            if (r6 == 0) goto L_0x00ad
            T r6 = r4.element     // Catch:{ all -> 0x00b3 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x00b3 }
            r8.append(r6)     // Catch:{ all -> 0x00b3 }
            goto L_0x0098
        L_0x00ad:
            java.lang.String r1 = r8.toString()     // Catch:{ all -> 0x00b3 }
            r4 = r2
            goto L_0x00b6
        L_0x00b3:
            r3 = r9
            r4 = r2
            goto L_0x00d2
        L_0x00b6:
            r9.close()     // Catch:{ all -> 0x00bf }
            if (r4 == 0) goto L_0x00cb
            r4.close()     // Catch:{ all -> 0x00bf }
            goto L_0x00cb
        L_0x00bf:
            r8 = move-exception
            java.lang.Class r7 = r7.getClass()
            java.lang.String r7 = r7.getCanonicalName()
            android.util.Log.e(r7, r0, r8)
        L_0x00cb:
            if (r5 == 0) goto L_0x00d0
            r5.disconnect()
        L_0x00d0:
            return r1
        L_0x00d1:
            r3 = r9
        L_0x00d2:
            r2 = r5
            goto L_0x00dc
        L_0x00d4:
            a.a.h r8 = new a.a.h     // Catch:{ all -> 0x00dc }
            java.lang.String r9 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            r8.<init>(r9)     // Catch:{ all -> 0x00dc }
            throw r8     // Catch:{ all -> 0x00dc }
        L_0x00dc:
            if (r3 == 0) goto L_0x00e4
            r3.close()     // Catch:{ all -> 0x00e2 }
            goto L_0x00e4
        L_0x00e2:
            r8 = move-exception
            goto L_0x00ea
        L_0x00e4:
            if (r4 == 0) goto L_0x00f5
            r4.close()     // Catch:{ all -> 0x00e2 }
            goto L_0x00f5
        L_0x00ea:
            java.lang.Class r7 = r7.getClass()
            java.lang.String r7 = r7.getCanonicalName()
            android.util.Log.e(r7, r0, r8)
        L_0x00f5:
            if (r2 == 0) goto L_0x00fa
            r2.disconnect()
        L_0x00fa:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.truenet.android.a.g.b(java.net.URL, java.lang.String, android.content.Context):java.lang.String");
    }

    /* compiled from: StartAppSDK */
    static final class a extends i implements a.a.b.a.a<String> {
        final /* synthetic */ m.a $line;
        final /* synthetic */ BufferedReader $reader;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(m.a aVar, BufferedReader bufferedReader) {
            super(0);
            this.$line = aVar;
            this.$reader = bufferedReader;
        }

        /* renamed from: b */
        public final String a() {
            this.$line.element = this.$reader.readLine();
            return (String) this.$line.element;
        }
    }
}
