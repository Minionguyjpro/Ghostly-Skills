package com.android.volley.toolbox;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.appnext.base.b.d;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0071, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0072, code lost:
        r17 = null;
        r18 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d2, code lost:
        r23 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d9, code lost:
        r23 = r5;
        r17 = null;
        r2 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e2, code lost:
        r18 = r1;
        r17 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e6, code lost:
        r2 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e9, code lost:
        r18 = r1;
        r17 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ef, code lost:
        r1 = r2.getStatusLine().getStatusCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f9, code lost:
        if (r1 == 301) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00fe, code lost:
        com.android.volley.VolleyLog.e("Unexpected response code %d for %s", java.lang.Integer.valueOf(r1), r25.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0112, code lost:
        com.android.volley.VolleyLog.e("Request at %s has been redirected to %s", r25.getOriginUrl(), r25.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0125, code lost:
        if (r17 != null) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0127, code lost:
        r15 = new com.android.volley.NetworkResponse(r1, r17, r18, false, android.os.SystemClock.elapsedRealtime() - r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0139, code lost:
        if (r1 == 401) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0140, code lost:
        if (r1 == 301) goto L_0x014b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x014a, code lost:
        throw new com.android.volley.ServerError(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x014b, code lost:
        attemptRetryOnException("redirect", r8, new com.android.volley.RedirectError(r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0157, code lost:
        attemptRetryOnException("auth", r8, new com.android.volley.AuthFailureError(r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0168, code lost:
        throw new com.android.volley.NetworkError(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x016e, code lost:
        throw new com.android.volley.NoConnectionError(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x016f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x018a, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r25.getUrl(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x018b, code lost:
        attemptRetryOnException("connection", r8, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0197, code lost:
        attemptRetryOnException("socket", r8, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x016f A[ExcHandler: MalformedURLException (r0v2 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:78:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:2:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0169 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r25) throws com.android.volley.VolleyError {
        /*
            r24 = this;
            r7 = r24
            r8 = r25
            long r9 = android.os.SystemClock.elapsedRealtime()
        L_0x0008:
            java.util.Map r1 = java.util.Collections.emptyMap()
            r2 = 0
            r11 = 302(0x12e, float:4.23E-43)
            r12 = 0
            r13 = 301(0x12d, float:4.22E-43)
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e8 }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e8 }
            com.android.volley.Cache$Entry r3 = r25.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e8 }
            r7.addCacheHeaders(r0, r3)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e8 }
            com.android.volley.toolbox.HttpStack r3 = r7.mHttpStack     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e8 }
            org.apache.http.HttpResponse r14 = r3.performRequest(r8, r0)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e8 }
            org.apache.http.StatusLine r6 = r14.getStatusLine()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e1 }
            int r0 = r6.getStatusCode()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e1 }
            org.apache.http.Header[] r3 = r14.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e1 }
            java.util.Map r5 = convertHeaders(r3)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00e1 }
            r1 = 304(0x130, float:4.26E-43)
            if (r0 != r1) goto L_0x0078
            com.android.volley.Cache$Entry r0 = r25.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            if (r0 != 0) goto L_0x0053
            com.android.volley.NetworkResponse r0 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            r16 = 304(0x130, float:4.26E-43)
            r17 = 0
            r19 = 1
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            long r20 = r3 - r9
            r15 = r0
            r18 = r5
            r15.<init>(r16, r17, r18, r19, r20)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            return r0
        L_0x0053:
            java.util.Map<java.lang.String, java.lang.String> r1 = r0.responseHeaders     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            r1.putAll(r5)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            com.android.volley.NetworkResponse r1 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            r16 = 304(0x130, float:4.26E-43)
            byte[] r3 = r0.data     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            java.util.Map<java.lang.String, java.lang.String> r0 = r0.responseHeaders     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            r19 = 1
            long r17 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            long r20 = r17 - r9
            r15 = r1
            r17 = r3
            r18 = r0
            r15.<init>(r16, r17, r18, r19, r20)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            return r1
        L_0x0071:
            r0 = move-exception
            r17 = r2
            r18 = r5
            goto L_0x00e6
        L_0x0078:
            if (r0 == r13) goto L_0x007c
            if (r0 != r11) goto L_0x0087
        L_0x007c:
            java.lang.String r1 = "Location"
            java.lang.Object r1 = r5.get(r1)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00d8 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00d8 }
            r8.setRedirectUrl(r1)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00d8 }
        L_0x0087:
            org.apache.http.HttpEntity r1 = r14.getEntity()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00d8 }
            if (r1 == 0) goto L_0x0096
            org.apache.http.HttpEntity r1 = r14.getEntity()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            byte[] r1 = r7.entityToBytes(r1)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x0071 }
            goto L_0x0098
        L_0x0096:
            byte[] r1 = new byte[r12]     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00d8 }
        L_0x0098:
            r22 = r1
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00d1 }
            long r3 = r1 - r9
            r1 = r24
            r2 = r3
            r4 = r25
            r23 = r5
            r5 = r22
            r1.logSlowRequests(r2, r4, r5, r6)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00cf }
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 < r1) goto L_0x00c9
            r1 = 299(0x12b, float:4.19E-43)
            if (r0 > r1) goto L_0x00c9
            com.android.volley.NetworkResponse r1 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00cf }
            r19 = 0
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00cf }
            long r20 = r2 - r9
            r15 = r1
            r16 = r0
            r17 = r22
            r18 = r23
            r15.<init>(r16, r17, r18, r19, r20)     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00cf }
            return r1
        L_0x00c9:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00cf }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00cf }
            throw r0     // Catch:{ SocketTimeoutException -> 0x0197, ConnectTimeoutException -> 0x018b, MalformedURLException -> 0x016f, IOException -> 0x00cf }
        L_0x00cf:
            r0 = move-exception
            goto L_0x00d4
        L_0x00d1:
            r0 = move-exception
            r23 = r5
        L_0x00d4:
            r2 = r14
            r17 = r22
            goto L_0x00de
        L_0x00d8:
            r0 = move-exception
            r23 = r5
            r17 = r2
            r2 = r14
        L_0x00de:
            r18 = r23
            goto L_0x00ed
        L_0x00e1:
            r0 = move-exception
            r18 = r1
            r17 = r2
        L_0x00e6:
            r2 = r14
            goto L_0x00ed
        L_0x00e8:
            r0 = move-exception
            r18 = r1
            r17 = r2
        L_0x00ed:
            if (r2 == 0) goto L_0x0169
            org.apache.http.StatusLine r1 = r2.getStatusLine()
            int r1 = r1.getStatusCode()
            r2 = 1
            r3 = 2
            if (r1 == r13) goto L_0x0112
            if (r1 != r11) goto L_0x00fe
            goto L_0x0112
        L_0x00fe:
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            r3[r12] = r4
            java.lang.String r4 = r25.getUrl()
            r3[r2] = r4
            java.lang.String r2 = "Unexpected response code %d for %s"
            com.android.volley.VolleyLog.e(r2, r3)
            goto L_0x0125
        L_0x0112:
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r4 = r25.getOriginUrl()
            r3[r12] = r4
            java.lang.String r4 = r25.getUrl()
            r3[r2] = r4
            java.lang.String r2 = "Request at %s has been redirected to %s"
            com.android.volley.VolleyLog.e(r2, r3)
        L_0x0125:
            if (r17 == 0) goto L_0x0163
            com.android.volley.NetworkResponse r0 = new com.android.volley.NetworkResponse
            r19 = 0
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r20 = r2 - r9
            r15 = r0
            r16 = r1
            r15.<init>(r16, r17, r18, r19, r20)
            r2 = 401(0x191, float:5.62E-43)
            if (r1 == r2) goto L_0x0157
            r2 = 403(0x193, float:5.65E-43)
            if (r1 != r2) goto L_0x0140
            goto L_0x0157
        L_0x0140:
            if (r1 == r13) goto L_0x014b
            if (r1 != r11) goto L_0x0145
            goto L_0x014b
        L_0x0145:
            com.android.volley.ServerError r1 = new com.android.volley.ServerError
            r1.<init>(r0)
            throw r1
        L_0x014b:
            com.android.volley.RedirectError r1 = new com.android.volley.RedirectError
            r1.<init>(r0)
            java.lang.String r0 = "redirect"
            attemptRetryOnException(r0, r8, r1)
            goto L_0x0008
        L_0x0157:
            com.android.volley.AuthFailureError r1 = new com.android.volley.AuthFailureError
            r1.<init>(r0)
            java.lang.String r0 = "auth"
            attemptRetryOnException(r0, r8, r1)
            goto L_0x0008
        L_0x0163:
            com.android.volley.NetworkError r1 = new com.android.volley.NetworkError
            r1.<init>(r0)
            throw r1
        L_0x0169:
            com.android.volley.NoConnectionError r1 = new com.android.volley.NoConnectionError
            r1.<init>(r0)
            throw r1
        L_0x016f:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Bad URL "
            r2.append(r3)
            java.lang.String r3 = r25.getUrl()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x018b:
            com.android.volley.TimeoutError r0 = new com.android.volley.TimeoutError
            r0.<init>()
            java.lang.String r1 = "connection"
            attemptRetryOnException(r1, r8, r0)
            goto L_0x0008
        L_0x0197:
            com.android.volley.TimeoutError r0 = new com.android.volley.TimeoutError
            r0.<init>()
            java.lang.String r1 = "socket"
            attemptRetryOnException(r1, r8, r0)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.BasicNetwork.performRequest(com.android.volley.Request):com.android.volley.NetworkResponse");
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > ((long) SLOW_REQUEST_THRESHOLD_MS)) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> map, Cache.Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                map.put("If-None-Match", entry.etag);
            }
            if (entry.lastModified > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.lastModified)));
            }
        }
    }

    private byte[] entityToBytes(HttpEntity httpEntity) throws IOException, ServerError {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                bArr = this.mPool.getBuf(d.fb);
                while (true) {
                    int read = content.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    poolingByteArrayOutputStream.write(bArr, 0, read);
                }
                return poolingByteArrayOutputStream.toByteArray();
            }
            throw new ServerError();
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException unused) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(bArr);
            poolingByteArrayOutputStream.close();
        }
    }

    protected static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
