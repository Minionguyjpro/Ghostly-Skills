package com.onesignal;

import java.io.IOException;
import java.lang.Thread;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

class OneSignalRestClient {
    private static int getThreadTimeout(int i) {
        return i + 5000;
    }

    static abstract class ResponseHandler {
        /* access modifiers changed from: package-private */
        public void onFailure(int i, String str, Throwable th) {
        }

        /* access modifiers changed from: package-private */
        public void onSuccess(String str) {
        }

        ResponseHandler() {
        }
    }

    public static void put(final String str, final JSONObject jSONObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            public void run() {
                OneSignalRestClient.makeRequest(str, "PUT", jSONObject, responseHandler, 120000, (String) null);
            }
        }, "OS_REST_ASYNC_PUT").start();
    }

    public static void post(final String str, final JSONObject jSONObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            public void run() {
                OneSignalRestClient.makeRequest(str, "POST", jSONObject, responseHandler, 120000, (String) null);
            }
        }, "OS_REST_ASYNC_POST").start();
    }

    public static void get(final String str, final ResponseHandler responseHandler, final String str2) {
        new Thread(new Runnable() {
            public void run() {
                OneSignalRestClient.makeRequest(str, (String) null, (JSONObject) null, responseHandler, 60000, str2);
            }
        }, "OS_REST_ASYNC_GET").start();
    }

    public static void getSync(String str, ResponseHandler responseHandler, String str2) {
        makeRequest(str, (String) null, (JSONObject) null, responseHandler, 60000, str2);
    }

    public static void putSync(String str, JSONObject jSONObject, ResponseHandler responseHandler) {
        makeRequest(str, "PUT", jSONObject, responseHandler, 120000, (String) null);
    }

    public static void postSync(String str, JSONObject jSONObject, ResponseHandler responseHandler) {
        makeRequest(str, "POST", jSONObject, responseHandler, 120000, (String) null);
    }

    /* access modifiers changed from: private */
    public static void makeRequest(String str, String str2, JSONObject jSONObject, ResponseHandler responseHandler, int i, String str3) {
        String str4 = str2;
        if (OSUtils.isRunningOnMainThread()) {
            throw new OneSignalNetworkCallException("Method: " + str2 + " was called from the Main Thread!");
        } else if (str4 == null || !OneSignal.shouldLogUserPrivacyConsentErrorMessageForMethodName((String) null)) {
            Thread[] threadArr = new Thread[1];
            final Thread[] threadArr2 = threadArr;
            final String str5 = str;
            final String str6 = str2;
            final JSONObject jSONObject2 = jSONObject;
            final ResponseHandler responseHandler2 = responseHandler;
            final int i2 = i;
            final String str7 = str3;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    threadArr2[0] = OneSignalRestClient.startHTTPConnection(str5, str6, jSONObject2, responseHandler2, i2, str7);
                }
            }, "OS_HTTPConnection");
            thread.start();
            try {
                thread.join((long) getThreadTimeout(i));
                if (thread.getState() != Thread.State.TERMINATED) {
                    thread.interrupt();
                }
                if (threadArr[0] != null) {
                    threadArr[0].join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0250, code lost:
        if (r8 != null) goto L_0x0252;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0252, code lost:
        r8.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x02a8, code lost:
        if (r8 == null) goto L_0x02ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x02ab, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0266 A[Catch:{ all -> 0x02ac }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Thread startHTTPConnection(java.lang.String r16, java.lang.String r17, org.json.JSONObject r18, com.onesignal.OneSignalRestClient.ResponseHandler r19, int r20, java.lang.String r21) {
        /*
            r0 = r16
            r1 = r17
            r2 = r19
            r3 = r20
            r4 = r21
            java.lang.String r5 = "OneSignalRestClient: "
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 26
            if (r6 < r7) goto L_0x0017
            r6 = 10000(0x2710, float:1.4013E-41)
            android.net.TrafficStats.setThreadStatsTag(r6)
        L_0x0017:
            r6 = 0
            r7 = -1
            com.onesignal.OneSignal$LOG_LEVEL r8 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x0260 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0260 }
            r9.<init>()     // Catch:{ all -> 0x0260 }
            java.lang.String r10 = "OneSignalRestClient: Making request to: https://api.onesignal.com/"
            r9.append(r10)     // Catch:{ all -> 0x0260 }
            r9.append(r0)     // Catch:{ all -> 0x0260 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0260 }
            com.onesignal.OneSignal.Log(r8, r9)     // Catch:{ all -> 0x0260 }
            java.net.HttpURLConnection r8 = newHttpURLConnection(r16)     // Catch:{ all -> 0x0260 }
            r9 = 0
            r8.setUseCaches(r9)     // Catch:{ all -> 0x025e }
            r8.setConnectTimeout(r3)     // Catch:{ all -> 0x025e }
            r8.setReadTimeout(r3)     // Catch:{ all -> 0x025e }
            java.lang.String r3 = "SDK-Version"
            java.lang.String r9 = "onesignal/android/031502"
            r8.setRequestProperty(r3, r9)     // Catch:{ all -> 0x025e }
            java.lang.String r3 = "Accept"
            java.lang.String r9 = "application/vnd.onesignal.v1+json"
            r8.setRequestProperty(r3, r9)     // Catch:{ all -> 0x025e }
            r3 = 1
            if (r18 == 0) goto L_0x0051
            r8.setDoInput(r3)     // Catch:{ all -> 0x025e }
        L_0x0051:
            if (r1 == 0) goto L_0x0060
            java.lang.String r9 = "Content-Type"
            java.lang.String r10 = "application/json; charset=UTF-8"
            r8.setRequestProperty(r9, r10)     // Catch:{ all -> 0x025e }
            r8.setRequestMethod(r1)     // Catch:{ all -> 0x025e }
            r8.setDoOutput(r3)     // Catch:{ all -> 0x025e }
        L_0x0060:
            java.lang.String r3 = "UTF-8"
            if (r18 == 0) goto L_0x0093
            java.lang.String r9 = r18.toString()     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal$LOG_LEVEL r10 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r11.<init>()     // Catch:{ all -> 0x025e }
            r11.append(r5)     // Catch:{ all -> 0x025e }
            r11.append(r1)     // Catch:{ all -> 0x025e }
            java.lang.String r12 = " SEND JSON: "
            r11.append(r12)     // Catch:{ all -> 0x025e }
            r11.append(r9)     // Catch:{ all -> 0x025e }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal.Log(r10, r11)     // Catch:{ all -> 0x025e }
            byte[] r9 = r9.getBytes(r3)     // Catch:{ all -> 0x025e }
            int r10 = r9.length     // Catch:{ all -> 0x025e }
            r8.setFixedLengthStreamingMode(r10)     // Catch:{ all -> 0x025e }
            java.io.OutputStream r10 = r8.getOutputStream()     // Catch:{ all -> 0x025e }
            r10.write(r9)     // Catch:{ all -> 0x025e }
        L_0x0093:
            java.lang.String r9 = "PREFS_OS_ETAG_PREFIX_"
            if (r4 == 0) goto L_0x00c9
            java.lang.String r10 = com.onesignal.OneSignalPrefs.PREFS_ONESIGNAL     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r11.<init>()     // Catch:{ all -> 0x025e }
            r11.append(r9)     // Catch:{ all -> 0x025e }
            r11.append(r4)     // Catch:{ all -> 0x025e }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x025e }
            java.lang.String r10 = com.onesignal.OneSignalPrefs.getString(r10, r11, r6)     // Catch:{ all -> 0x025e }
            if (r10 == 0) goto L_0x00c9
            java.lang.String r11 = "if-none-match"
            r8.setRequestProperty(r11, r10)     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal$LOG_LEVEL r11 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r12.<init>()     // Catch:{ all -> 0x025e }
            java.lang.String r13 = "OneSignalRestClient: Adding header if-none-match: "
            r12.append(r13)     // Catch:{ all -> 0x025e }
            r12.append(r10)     // Catch:{ all -> 0x025e }
            java.lang.String r10 = r12.toString()     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal.Log(r11, r10)     // Catch:{ all -> 0x025e }
        L_0x00c9:
            int r7 = r8.getResponseCode()     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal$LOG_LEVEL r10 = com.onesignal.OneSignal.LOG_LEVEL.VERBOSE     // Catch:{ all -> 0x025a }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x025a }
            r11.<init>()     // Catch:{ all -> 0x025a }
            java.lang.String r12 = "OneSignalRestClient: After con.getResponseCode to: https://api.onesignal.com/"
            r11.append(r12)     // Catch:{ all -> 0x025a }
            r11.append(r0)     // Catch:{ all -> 0x025a }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x025a }
            com.onesignal.OneSignal.Log(r10, r11)     // Catch:{ all -> 0x025a }
            r10 = 200(0xc8, float:2.8E-43)
            java.lang.String r11 = " RECEIVED JSON: "
            java.lang.String r12 = ""
            java.lang.String r13 = "GET"
            java.lang.String r14 = "\\A"
            java.lang.String r15 = "PREFS_OS_HTTP_CACHE_PREFIX_"
            if (r7 == r10) goto L_0x01af
            r10 = 202(0xca, float:2.83E-43)
            if (r7 == r10) goto L_0x01af
            r9 = 304(0x130, float:4.26E-43)
            if (r7 == r9) goto L_0x0174
            com.onesignal.OneSignal$LOG_LEVEL r4 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r9.<init>()     // Catch:{ all -> 0x025e }
            java.lang.String r10 = "OneSignalRestClient: Failed request to: https://api.onesignal.com/"
            r9.append(r10)     // Catch:{ all -> 0x025e }
            r9.append(r0)     // Catch:{ all -> 0x025e }
            java.lang.String r0 = r9.toString()     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal.Log(r4, r0)     // Catch:{ all -> 0x025e }
            java.io.InputStream r0 = r8.getErrorStream()     // Catch:{ all -> 0x025e }
            if (r0 != 0) goto L_0x0119
            java.io.InputStream r0 = r8.getInputStream()     // Catch:{ all -> 0x025e }
        L_0x0119:
            if (r0 == 0) goto L_0x014c
            java.util.Scanner r4 = new java.util.Scanner     // Catch:{ all -> 0x025e }
            r4.<init>(r0, r3)     // Catch:{ all -> 0x025e }
            java.util.Scanner r0 = r4.useDelimiter(r14)     // Catch:{ all -> 0x025e }
            boolean r0 = r0.hasNext()     // Catch:{ all -> 0x025e }
            if (r0 == 0) goto L_0x012e
            java.lang.String r12 = r4.next()     // Catch:{ all -> 0x025e }
        L_0x012e:
            r4.close()     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal$LOG_LEVEL r0 = com.onesignal.OneSignal.LOG_LEVEL.WARN     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r3.<init>()     // Catch:{ all -> 0x025e }
            r3.append(r5)     // Catch:{ all -> 0x025e }
            r3.append(r1)     // Catch:{ all -> 0x025e }
            r3.append(r11)     // Catch:{ all -> 0x025e }
            r3.append(r12)     // Catch:{ all -> 0x025e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal.Log(r0, r3)     // Catch:{ all -> 0x025e }
            goto L_0x016e
        L_0x014c:
            com.onesignal.OneSignal$LOG_LEVEL r0 = com.onesignal.OneSignal.LOG_LEVEL.WARN     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r3.<init>()     // Catch:{ all -> 0x025e }
            r3.append(r5)     // Catch:{ all -> 0x025e }
            r3.append(r1)     // Catch:{ all -> 0x025e }
            java.lang.String r4 = " HTTP Code: "
            r3.append(r4)     // Catch:{ all -> 0x025e }
            r3.append(r7)     // Catch:{ all -> 0x025e }
            java.lang.String r4 = " No response body!"
            r3.append(r4)     // Catch:{ all -> 0x025e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal.Log(r0, r3)     // Catch:{ all -> 0x025e }
            r12 = r6
        L_0x016e:
            java.lang.Thread r0 = callResponseHandlerOnFailure(r2, r7, r12, r6)     // Catch:{ all -> 0x025e }
            goto L_0x0250
        L_0x0174:
            java.lang.String r0 = com.onesignal.OneSignalPrefs.PREFS_ONESIGNAL     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r3.<init>()     // Catch:{ all -> 0x025e }
            r3.append(r15)     // Catch:{ all -> 0x025e }
            r3.append(r4)     // Catch:{ all -> 0x025e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x025e }
            java.lang.String r0 = com.onesignal.OneSignalPrefs.getString(r0, r3, r6)     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal$LOG_LEVEL r3 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r4.<init>()     // Catch:{ all -> 0x025e }
            r4.append(r5)     // Catch:{ all -> 0x025e }
            if (r1 != 0) goto L_0x0196
            goto L_0x0197
        L_0x0196:
            r13 = r1
        L_0x0197:
            r4.append(r13)     // Catch:{ all -> 0x025e }
            java.lang.String r9 = " - Using Cached response due to 304: "
            r4.append(r9)     // Catch:{ all -> 0x025e }
            r4.append(r0)     // Catch:{ all -> 0x025e }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x025e }
            com.onesignal.OneSignal.Log(r3, r4)     // Catch:{ all -> 0x025e }
            java.lang.Thread r0 = callResponseHandlerOnSuccess(r2, r0)     // Catch:{ all -> 0x025e }
            goto L_0x0250
        L_0x01af:
            com.onesignal.OneSignal$LOG_LEVEL r10 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x025a }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x025a }
            r6.<init>()     // Catch:{ all -> 0x025a }
            r18 = r7
            java.lang.String r7 = "OneSignalRestClient: Successfully finished request to: https://api.onesignal.com/"
            r6.append(r7)     // Catch:{ all -> 0x0256 }
            r6.append(r0)     // Catch:{ all -> 0x0256 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x0256 }
            com.onesignal.OneSignal.Log(r10, r0)     // Catch:{ all -> 0x0256 }
            java.io.InputStream r0 = r8.getInputStream()     // Catch:{ all -> 0x0256 }
            java.util.Scanner r6 = new java.util.Scanner     // Catch:{ all -> 0x0256 }
            r6.<init>(r0, r3)     // Catch:{ all -> 0x0256 }
            java.util.Scanner r0 = r6.useDelimiter(r14)     // Catch:{ all -> 0x0256 }
            boolean r0 = r0.hasNext()     // Catch:{ all -> 0x0256 }
            if (r0 == 0) goto L_0x01de
            java.lang.String r12 = r6.next()     // Catch:{ all -> 0x0256 }
        L_0x01de:
            r6.close()     // Catch:{ all -> 0x0256 }
            com.onesignal.OneSignal$LOG_LEVEL r0 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x0256 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0256 }
            r3.<init>()     // Catch:{ all -> 0x0256 }
            r3.append(r5)     // Catch:{ all -> 0x0256 }
            if (r1 != 0) goto L_0x01ee
            goto L_0x01ef
        L_0x01ee:
            r13 = r1
        L_0x01ef:
            r3.append(r13)     // Catch:{ all -> 0x0256 }
            r3.append(r11)     // Catch:{ all -> 0x0256 }
            r3.append(r12)     // Catch:{ all -> 0x0256 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0256 }
            com.onesignal.OneSignal.Log(r0, r3)     // Catch:{ all -> 0x0256 }
            if (r4 == 0) goto L_0x024c
            java.lang.String r0 = "etag"
            java.lang.String r0 = r8.getHeaderField(r0)     // Catch:{ all -> 0x0256 }
            if (r0 == 0) goto L_0x024c
            com.onesignal.OneSignal$LOG_LEVEL r3 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x0256 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0256 }
            r6.<init>()     // Catch:{ all -> 0x0256 }
            java.lang.String r7 = "OneSignalRestClient: Response has etag of "
            r6.append(r7)     // Catch:{ all -> 0x0256 }
            r6.append(r0)     // Catch:{ all -> 0x0256 }
            java.lang.String r7 = " so caching the response."
            r6.append(r7)     // Catch:{ all -> 0x0256 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0256 }
            com.onesignal.OneSignal.Log(r3, r6)     // Catch:{ all -> 0x0256 }
            java.lang.String r3 = com.onesignal.OneSignalPrefs.PREFS_ONESIGNAL     // Catch:{ all -> 0x0256 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0256 }
            r6.<init>()     // Catch:{ all -> 0x0256 }
            r6.append(r9)     // Catch:{ all -> 0x0256 }
            r6.append(r4)     // Catch:{ all -> 0x0256 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0256 }
            com.onesignal.OneSignalPrefs.saveString(r3, r6, r0)     // Catch:{ all -> 0x0256 }
            java.lang.String r0 = com.onesignal.OneSignalPrefs.PREFS_ONESIGNAL     // Catch:{ all -> 0x0256 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0256 }
            r3.<init>()     // Catch:{ all -> 0x0256 }
            r3.append(r15)     // Catch:{ all -> 0x0256 }
            r3.append(r4)     // Catch:{ all -> 0x0256 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0256 }
            com.onesignal.OneSignalPrefs.saveString(r0, r3, r12)     // Catch:{ all -> 0x0256 }
        L_0x024c:
            java.lang.Thread r0 = callResponseHandlerOnSuccess(r2, r12)     // Catch:{ all -> 0x0256 }
        L_0x0250:
            if (r8 == 0) goto L_0x02ab
        L_0x0252:
            r8.disconnect()
            goto L_0x02ab
        L_0x0256:
            r0 = move-exception
            r7 = r18
            goto L_0x0262
        L_0x025a:
            r0 = move-exception
            r18 = r7
            goto L_0x0262
        L_0x025e:
            r0 = move-exception
            goto L_0x0262
        L_0x0260:
            r0 = move-exception
            r8 = 0
        L_0x0262:
            boolean r3 = r0 instanceof java.net.ConnectException     // Catch:{ all -> 0x02ac }
            if (r3 != 0) goto L_0x0285
            boolean r3 = r0 instanceof java.net.UnknownHostException     // Catch:{ all -> 0x02ac }
            if (r3 == 0) goto L_0x026b
            goto L_0x0285
        L_0x026b:
            com.onesignal.OneSignal$LOG_LEVEL r3 = com.onesignal.OneSignal.LOG_LEVEL.WARN     // Catch:{ all -> 0x02ac }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x02ac }
            r4.<init>()     // Catch:{ all -> 0x02ac }
            r4.append(r5)     // Catch:{ all -> 0x02ac }
            r4.append(r1)     // Catch:{ all -> 0x02ac }
            java.lang.String r1 = " Error thrown from network stack. "
            r4.append(r1)     // Catch:{ all -> 0x02ac }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x02ac }
            com.onesignal.OneSignal.Log(r3, r1, r0)     // Catch:{ all -> 0x02ac }
            goto L_0x02a3
        L_0x0285:
            com.onesignal.OneSignal$LOG_LEVEL r1 = com.onesignal.OneSignal.LOG_LEVEL.INFO     // Catch:{ all -> 0x02ac }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02ac }
            r3.<init>()     // Catch:{ all -> 0x02ac }
            java.lang.String r4 = "OneSignalRestClient: Could not send last request, device is offline. Throwable: "
            r3.append(r4)     // Catch:{ all -> 0x02ac }
            java.lang.Class r4 = r0.getClass()     // Catch:{ all -> 0x02ac }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x02ac }
            r3.append(r4)     // Catch:{ all -> 0x02ac }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x02ac }
            com.onesignal.OneSignal.Log(r1, r3)     // Catch:{ all -> 0x02ac }
        L_0x02a3:
            r1 = 0
            java.lang.Thread r0 = callResponseHandlerOnFailure(r2, r7, r1, r0)     // Catch:{ all -> 0x02ac }
            if (r8 == 0) goto L_0x02ab
            goto L_0x0252
        L_0x02ab:
            return r0
        L_0x02ac:
            r0 = move-exception
            if (r8 == 0) goto L_0x02b2
            r8.disconnect()
        L_0x02b2:
            goto L_0x02b4
        L_0x02b3:
            throw r0
        L_0x02b4:
            goto L_0x02b3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignalRestClient.startHTTPConnection(java.lang.String, java.lang.String, org.json.JSONObject, com.onesignal.OneSignalRestClient$ResponseHandler, int, java.lang.String):java.lang.Thread");
    }

    private static Thread callResponseHandlerOnSuccess(final ResponseHandler responseHandler, final String str) {
        if (responseHandler == null) {
            return null;
        }
        Thread thread = new Thread(new Runnable() {
            public void run() {
                responseHandler.onSuccess(str);
            }
        }, "OS_REST_SUCCESS_CALLBACK");
        thread.start();
        return thread;
    }

    private static Thread callResponseHandlerOnFailure(final ResponseHandler responseHandler, final int i, final String str, final Throwable th) {
        if (responseHandler == null) {
            return null;
        }
        Thread thread = new Thread(new Runnable() {
            public void run() {
                responseHandler.onFailure(i, str, th);
            }
        }, "OS_REST_FAILURE_CALLBACK");
        thread.start();
        return thread;
    }

    private static HttpURLConnection newHttpURLConnection(String str) throws IOException {
        return (HttpURLConnection) new URL("https://api.onesignal.com/" + str).openConnection();
    }

    private static class OneSignalNetworkCallException extends RuntimeException {
        public OneSignalNetworkCallException(String str) {
            super(str);
        }
    }
}
