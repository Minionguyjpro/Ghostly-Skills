package org.altbeacon.beacon.distance;

public class DistanceConfigFetcher {
    protected Exception mException;
    protected String mResponse;
    private int mResponseCode = -1;
    private String mUrlString;
    private String mUserAgentString;

    public DistanceConfigFetcher(String str, String str2) {
        this.mUrlString = str;
        this.mUserAgentString = str2;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public String getResponseString() {
        return this.mResponse;
    }

    public Exception getException() {
        return this.mException;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b2 A[EDGE_INSN: B:48:0x00b2->B:37:0x00b2 ?: BREAK  
    EDGE_INSN: B:49:0x00b2->B:37:0x00b2 ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x000d A[LOOP:0: B:1:0x000d->B:47:0x000d, LOOP_END, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void request() {
        /*
            r11 = this;
            r0 = 0
            r11.mResponse = r0
            java.lang.String r1 = r11.mUrlString
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r3 = 0
            r5 = r0
            r4 = 0
        L_0x000d:
            r6 = 1
            java.lang.String r7 = "DistanceConfigFetcher"
            if (r4 == 0) goto L_0x002a
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r8 = r11.mUrlString
            r1[r3] = r8
            java.lang.String r8 = "Location"
            java.lang.String r9 = r5.getHeaderField(r8)
            r1[r6] = r9
            java.lang.String r9 = "Following redirect from %s to %s"
            org.altbeacon.beacon.logging.LogManager.d(r7, r9, r1)
            java.lang.String r1 = r5.getHeaderField(r8)
        L_0x002a:
            int r4 = r4 + 1
            r8 = -1
            r11.mResponseCode = r8
            java.net.URL r8 = new java.net.URL     // Catch:{ Exception -> 0x0035 }
            r8.<init>(r1)     // Catch:{ Exception -> 0x0035 }
            goto L_0x0044
        L_0x0035:
            r8 = move-exception
            java.lang.Object[] r9 = new java.lang.Object[r6]
            java.lang.String r10 = r11.mUrlString
            r9[r3] = r10
            java.lang.String r10 = "Can't construct URL from: %s"
            org.altbeacon.beacon.logging.LogManager.e(r7, r10, r9)
            r11.mException = r8
            r8 = r0
        L_0x0044:
            if (r8 != 0) goto L_0x004e
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r8 = "URL is null.  Cannot make request"
            org.altbeacon.beacon.logging.LogManager.d(r7, r8, r6)
            goto L_0x00a0
        L_0x004e:
            java.net.URLConnection r8 = r8.openConnection()     // Catch:{ SecurityException -> 0x0093, FileNotFoundException -> 0x0086, IOException -> 0x0079 }
            java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ SecurityException -> 0x0093, FileNotFoundException -> 0x0086, IOException -> 0x0079 }
            java.lang.String r5 = "User-Agent"
            java.lang.String r9 = r11.mUserAgentString     // Catch:{ SecurityException -> 0x0077, FileNotFoundException -> 0x0075, IOException -> 0x0073 }
            r8.addRequestProperty(r5, r9)     // Catch:{ SecurityException -> 0x0077, FileNotFoundException -> 0x0075, IOException -> 0x0073 }
            int r5 = r8.getResponseCode()     // Catch:{ SecurityException -> 0x0077, FileNotFoundException -> 0x0075, IOException -> 0x0073 }
            r11.mResponseCode = r5     // Catch:{ SecurityException -> 0x0077, FileNotFoundException -> 0x0075, IOException -> 0x0073 }
            java.lang.String r5 = "response code is %s"
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ SecurityException -> 0x0077, FileNotFoundException -> 0x0075, IOException -> 0x0073 }
            int r9 = r8.getResponseCode()     // Catch:{ SecurityException -> 0x0077, FileNotFoundException -> 0x0075, IOException -> 0x0073 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ SecurityException -> 0x0077, FileNotFoundException -> 0x0075, IOException -> 0x0073 }
            r6[r3] = r9     // Catch:{ SecurityException -> 0x0077, FileNotFoundException -> 0x0075, IOException -> 0x0073 }
            org.altbeacon.beacon.logging.LogManager.d(r7, r5, r6)     // Catch:{ SecurityException -> 0x0077, FileNotFoundException -> 0x0075, IOException -> 0x0073 }
            goto L_0x009f
        L_0x0073:
            r5 = move-exception
            goto L_0x007c
        L_0x0075:
            r5 = move-exception
            goto L_0x0089
        L_0x0077:
            r5 = move-exception
            goto L_0x0096
        L_0x0079:
            r6 = move-exception
            r8 = r5
            r5 = r6
        L_0x007c:
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r9 = "Can't reach server"
            org.altbeacon.beacon.logging.LogManager.w(r5, r7, r9, r6)
            r11.mException = r5
            goto L_0x009f
        L_0x0086:
            r6 = move-exception
            r8 = r5
            r5 = r6
        L_0x0089:
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r9 = "No data exists at \"+urlString"
            org.altbeacon.beacon.logging.LogManager.w(r5, r7, r9, r6)
            r11.mException = r5
            goto L_0x009f
        L_0x0093:
            r6 = move-exception
            r8 = r5
            r5 = r6
        L_0x0096:
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r9 = "Can't reach sever.  Have you added android.permission.INTERNET to your manifest?"
            org.altbeacon.beacon.logging.LogManager.w(r5, r7, r9, r6)
            r11.mException = r5
        L_0x009f:
            r5 = r8
        L_0x00a0:
            r6 = 10
            if (r4 >= r6) goto L_0x00b2
            int r6 = r11.mResponseCode
            r8 = 302(0x12e, float:4.23E-43)
            if (r6 == r8) goto L_0x000d
            r8 = 301(0x12d, float:4.22E-43)
            if (r6 == r8) goto L_0x000d
            r8 = 303(0x12f, float:4.25E-43)
            if (r6 == r8) goto L_0x000d
        L_0x00b2:
            java.lang.Exception r0 = r11.mException
            if (r0 != 0) goto L_0x00e2
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00d8 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00d8 }
            java.io.InputStream r4 = r5.getInputStream()     // Catch:{ Exception -> 0x00d8 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x00d8 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00d8 }
        L_0x00c4:
            java.lang.String r1 = r0.readLine()     // Catch:{ Exception -> 0x00d8 }
            if (r1 == 0) goto L_0x00ce
            r2.append(r1)     // Catch:{ Exception -> 0x00d8 }
            goto L_0x00c4
        L_0x00ce:
            r0.close()     // Catch:{ Exception -> 0x00d8 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x00d8 }
            r11.mResponse = r0     // Catch:{ Exception -> 0x00d8 }
            goto L_0x00e2
        L_0x00d8:
            r0 = move-exception
            r11.mException = r0
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.String r2 = "error reading beacon data"
            org.altbeacon.beacon.logging.LogManager.w(r0, r7, r2, r1)
        L_0x00e2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.distance.DistanceConfigFetcher.request():void");
    }
}
