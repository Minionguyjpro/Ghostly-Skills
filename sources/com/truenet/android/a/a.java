package com.truenet.android.a;

/* compiled from: StartAppSDK */
public final class a {
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0088 A[SYNTHETIC, Splitter:B:29:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean a(android.graphics.Bitmap r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "stream closed with error!"
            java.lang.String r1 = "$receiver"
            a.a.b.b.h.b(r7, r1)
            java.lang.String r1 = "url"
            a.a.b.b.h.b(r8, r1)
            r1 = 0
            r2 = r1
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
            java.io.ByteArrayOutputStream r1 = (java.io.ByteArrayOutputStream) r1
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ all -> 0x0086 }
            r4.<init>(r8)     // Catch:{ all -> 0x0086 }
            java.net.URLConnection r8 = r4.openConnection()     // Catch:{ all -> 0x0086 }
            if (r8 == 0) goto L_0x007e
            java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ all -> 0x0086 }
            r2 = 1
            r8.setDoOutput(r2)     // Catch:{ all -> 0x007c }
            java.lang.String r4 = "PUT"
            r8.setRequestMethod(r4)     // Catch:{ all -> 0x007c }
            java.lang.String r4 = "Content-Type"
            java.lang.String r5 = "image/jpeg"
            r8.setRequestProperty(r4, r5)     // Catch:{ all -> 0x007c }
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x007c }
            r4.<init>()     // Catch:{ all -> 0x007c }
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x0079 }
            r5 = 100
            r6 = r4
            java.io.OutputStream r6 = (java.io.OutputStream) r6     // Catch:{ all -> 0x0079 }
            r7.compress(r1, r5, r6)     // Catch:{ all -> 0x0079 }
            byte[] r1 = r4.toByteArray()     // Catch:{ all -> 0x0079 }
            int r5 = r1.length     // Catch:{ all -> 0x0079 }
            r8.setFixedLengthStreamingMode(r5)     // Catch:{ all -> 0x0079 }
            java.io.OutputStream r5 = r8.getOutputStream()     // Catch:{ all -> 0x0079 }
            r5.write(r1)     // Catch:{ all -> 0x0079 }
            java.io.OutputStream r1 = r8.getOutputStream()     // Catch:{ all -> 0x0079 }
            r1.flush()     // Catch:{ all -> 0x0079 }
            r1 = 299(0x12b, float:4.19E-43)
            r5 = 200(0xc8, float:2.8E-43)
            int r6 = r8.getResponseCode()     // Catch:{ all -> 0x0079 }
            if (r5 <= r6) goto L_0x0060
            goto L_0x0063
        L_0x0060:
            if (r1 < r6) goto L_0x0063
            r3 = 1
        L_0x0063:
            r4.close()     // Catch:{ all -> 0x0067 }
            goto L_0x0073
        L_0x0067:
            r1 = move-exception
            java.lang.Class r7 = r7.getClass()
            java.lang.String r7 = r7.getCanonicalName()
            android.util.Log.e(r7, r0, r1)
        L_0x0073:
            if (r8 == 0) goto L_0x0078
            r8.disconnect()
        L_0x0078:
            return r3
        L_0x0079:
            r2 = r8
            r1 = r4
            goto L_0x0086
        L_0x007c:
            r2 = r8
            goto L_0x0086
        L_0x007e:
            a.a.h r8 = new a.a.h     // Catch:{ all -> 0x0086 }
            java.lang.String r4 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            r8.<init>(r4)     // Catch:{ all -> 0x0086 }
            throw r8     // Catch:{ all -> 0x0086 }
        L_0x0086:
            if (r1 == 0) goto L_0x0098
            r1.close()     // Catch:{ all -> 0x008c }
            goto L_0x0098
        L_0x008c:
            r8 = move-exception
            java.lang.Class r7 = r7.getClass()
            java.lang.String r7 = r7.getCanonicalName()
            android.util.Log.e(r7, r0, r8)
        L_0x0098:
            if (r2 == 0) goto L_0x009d
            r2.disconnect()
        L_0x009d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.truenet.android.a.a.a(android.graphics.Bitmap, java.lang.String):boolean");
    }
}
