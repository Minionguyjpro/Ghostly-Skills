package com.startapp.android.publish.adsCommon.k;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.BaseRequest;
import com.startapp.android.publish.adsCommon.BaseResponse;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.a.b;
import com.startapp.common.a.g;
import com.startapp.common.a.h;
import com.startapp.common.e;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* compiled from: StartAppSDK */
public class a {
    public static <T extends BaseResponse> T a(Context context, String str, BaseRequest baseRequest, Map<String, String> map, Class<T> cls) {
        return (BaseResponse) i.a(b(context, str, baseRequest, map, 3, 0).a(), cls);
    }

    public static h.a a(Context context, String str, BaseRequest baseRequest, Map<String, String> map) {
        return b(context, str, baseRequest, map, 3, 0);
    }

    public static boolean a(Context context, String str, Map<String, String> map) {
        b(context, str, (BaseRequest) null, map, 3, 0);
        return true;
    }

    public static boolean a(Context context, String str, BaseRequest baseRequest, Map<String, String> map, int i, long j) {
        c(context, str, baseRequest, map, i, j);
        return true;
    }

    private static h.a b(Context context, String str, BaseRequest baseRequest, Map<String, String> map, int i, long j) {
        String str2;
        g.a("Transport", 3, "Sending get to URL: " + str);
        if (baseRequest != null) {
            str2 = str + baseRequest.getRequestString();
        } else {
            str2 = str;
        }
        Map<String, String> a2 = a(context, map);
        int i2 = 1;
        while (true) {
            if (baseRequest == null || i2 <= 1) {
                break;
            }
            try {
                baseRequest.setRetry(i2 - 1);
                str2 = str + baseRequest.getRequestString();
                break;
            } catch (e e) {
                if (!e.b() || i2 >= i || !a(e)) {
                    throw e;
                }
                i2++;
                if (j > 0) {
                    try {
                        Thread.sleep(j);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
        return h.a(context, str2, a2, k.a(context, "User-Agent", "-1"), MetaData.getInstance().isCompressionEnabled());
    }

    private static boolean a(e eVar) {
        if (eVar.a() != 0) {
            return !MetaData.getInstance().getInvalidForRetry().contains(Integer.valueOf(eVar.a()));
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        throw new com.startapp.common.e("failed compressing json to gzip", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0074, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007c, code lost:
        throw new com.startapp.common.e("failed encoding json to UTF-8", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0089, code lost:
        r13 = r13 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008f, code lost:
        if (r20 > 0) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        java.lang.Thread.sleep(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0097, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006b A[ExcHandler: IOException (r0v4 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:7:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0074 A[ExcHandler: UnsupportedEncodingException (r0v3 'e' java.io.UnsupportedEncodingException A[CUSTOM_DECLARE]), Splitter:B:7:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(android.content.Context r15, java.lang.String r16, com.startapp.android.publish.adsCommon.BaseRequest r17, java.util.Map<java.lang.String, java.lang.String> r18, int r19, long r20) {
        /*
            r7 = r15
            r0 = 0
            if (r17 == 0) goto L_0x000a
            com.startapp.android.publish.adsCommon.Utils.e r1 = r17.getNameValueJson()
            r8 = r1
            goto L_0x000b
        L_0x000a:
            r8 = r0
        L_0x000b:
            r1 = 3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Sending post to URL: "
            r2.append(r3)
            r9 = r16
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "Transport"
            com.startapp.common.a.g.a((java.lang.String) r3, (int) r1, (java.lang.String) r2)
            r1 = r18
            java.util.Map r10 = a(r15, r1)
            r1 = 0
            r11 = 1
            r1 = r0
            r12 = 0
            r13 = 1
        L_0x002f:
            if (r12 != 0) goto L_0x0098
            if (r8 == 0) goto L_0x004f
            java.lang.String r0 = r8.toString()     // Catch:{ e -> 0x004d, UnsupportedEncodingException -> 0x0074, IOException -> 0x006b }
            java.lang.String r2 = "UTF-8"
            byte[] r1 = r0.getBytes(r2)     // Catch:{ e -> 0x004d, UnsupportedEncodingException -> 0x0074, IOException -> 0x006b }
            com.startapp.android.publish.common.metaData.MetaData r0 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ e -> 0x004d, UnsupportedEncodingException -> 0x0074, IOException -> 0x006b }
            boolean r0 = r0.isCompressionEnabled()     // Catch:{ e -> 0x004d, UnsupportedEncodingException -> 0x0074, IOException -> 0x006b }
            if (r0 == 0) goto L_0x004f
            byte[] r0 = a((byte[]) r1)     // Catch:{ e -> 0x004d, UnsupportedEncodingException -> 0x0074, IOException -> 0x006b }
            r14 = r0
            goto L_0x0050
        L_0x004d:
            r0 = move-exception
            goto L_0x007f
        L_0x004f:
            r14 = r1
        L_0x0050:
            java.lang.String r0 = "User-Agent"
            java.lang.String r1 = "-1"
            java.lang.String r5 = com.startapp.android.publish.adsCommon.k.a((android.content.Context) r15, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ e -> 0x007d, UnsupportedEncodingException -> 0x0074, IOException -> 0x006b }
            com.startapp.android.publish.common.metaData.MetaData r0 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ e -> 0x007d, UnsupportedEncodingException -> 0x0074, IOException -> 0x006b }
            boolean r6 = r0.isCompressionEnabled()     // Catch:{ e -> 0x007d, UnsupportedEncodingException -> 0x0074, IOException -> 0x006b }
            r1 = r15
            r2 = r16
            r3 = r14
            r4 = r10
            com.startapp.common.a.h.a(r1, r2, r3, r4, r5, r6)     // Catch:{ e -> 0x007d, UnsupportedEncodingException -> 0x0074, IOException -> 0x006b }
            r1 = r14
            r12 = 1
            goto L_0x002f
        L_0x006b:
            r0 = move-exception
            com.startapp.common.e r1 = new com.startapp.common.e
            java.lang.String r2 = "failed compressing json to gzip"
            r1.<init>(r2, r0)
            throw r1
        L_0x0074:
            r0 = move-exception
            com.startapp.common.e r1 = new com.startapp.common.e
            java.lang.String r2 = "failed encoding json to UTF-8"
            r1.<init>(r2, r0)
            throw r1
        L_0x007d:
            r0 = move-exception
            r1 = r14
        L_0x007f:
            boolean r2 = r0.b()
            if (r2 == 0) goto L_0x0097
            r2 = r19
            if (r13 >= r2) goto L_0x0097
            int r13 = r13 + 1
            r3 = 0
            int r0 = (r20 > r3 ? 1 : (r20 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x002f
            java.lang.Thread.sleep(r20)     // Catch:{ InterruptedException -> 0x0095 }
            goto L_0x002f
        L_0x0095:
            goto L_0x002f
        L_0x0097:
            throw r0
        L_0x0098:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.k.a.c(android.content.Context, java.lang.String, com.startapp.android.publish.adsCommon.BaseRequest, java.util.Map, int, long):void");
    }

    private static byte[] a(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream2.write(bArr);
                gZIPOutputStream2.flush();
                gZIPOutputStream2.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e = e;
                gZIPOutputStream = gZIPOutputStream2;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = gZIPOutputStream2;
                if (gZIPOutputStream != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception unused) {
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            throw e;
        }
    }

    private static Map<String, String> a(Context context, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (!AdsConstants.g.booleanValue()) {
            String a2 = b.a().a(context).a();
            try {
                a2 = URLEncoder.encode(a2, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
            map.put("device-id", a2);
        }
        map.put("Accept-Language", Locale.getDefault().toString());
        return map;
    }
}
