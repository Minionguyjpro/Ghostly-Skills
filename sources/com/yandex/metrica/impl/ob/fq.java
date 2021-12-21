package com.yandex.metrica.impl.ob;

import com.appnext.base.b.d;
import com.yandex.metrica.impl.ob.fr;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.http.HttpEntity;

public class fq {

    /* renamed from: a  reason: collision with root package name */
    protected final fm f909a;

    public fq(fm fmVar) {
        this.f909a = fmVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005b, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005c, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0063, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0064, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        r1 = r1.getStatusLine().getStatusCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006f, code lost:
        if (r3 != null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0071, code lost:
        new com.yandex.metrica.impl.ob.ft(r3, r0, (byte) 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0078, code lost:
        if (r1 == 401) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007f, code lost:
        r0 = com.yandex.metrica.impl.ob.fr.a.SERVER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0086, code lost:
        throw new com.yandex.metrica.impl.ob.fr((byte) 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0087, code lost:
        r1 = com.yandex.metrica.impl.ob.fr.a.AUTH;
        a(r12, new com.yandex.metrica.impl.ob.fr((byte) 0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0093, code lost:
        r0 = com.yandex.metrica.impl.ob.fr.a.NETWORK;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009a, code lost:
        throw new com.yandex.metrica.impl.ob.fr((byte) 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009b, code lost:
        r0 = com.yandex.metrica.impl.ob.fr.a.DEFAULT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a2, code lost:
        throw new com.yandex.metrica.impl.ob.fr((java.lang.Throwable) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bb, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r12.a(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bc, code lost:
        r1 = com.yandex.metrica.impl.ob.fr.a.NO_CONNECTION;
        a(r12, new com.yandex.metrica.impl.ob.fr());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c8, code lost:
        r1 = com.yandex.metrica.impl.ob.fr.a.TIMEOUT;
        a(r12, new com.yandex.metrica.impl.ob.fr());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a3 A[ExcHandler: MalformedURLException (r0v3 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x009b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.yandex.metrica.impl.ob.ft a(com.yandex.metrica.impl.ob.fu<?> r12) throws com.yandex.metrica.impl.ob.fr {
        /*
            r11 = this;
        L_0x0000:
            java.util.Map r0 = java.util.Collections.emptyMap()
            r1 = 0
            r2 = 0
            com.yandex.metrica.impl.ob.fm r3 = r11.f909a     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x0063 }
            org.apache.http.HttpResponse r3 = r3.a(r12)     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x0063 }
            org.apache.http.StatusLine r4 = r3.getStatusLine()     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            int r4 = r4.getStatusCode()     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            org.apache.http.Header[] r5 = r3.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            java.util.TreeMap r6 = new java.util.TreeMap     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            java.util.Comparator r7 = java.lang.String.CASE_INSENSITIVE_ORDER     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            r6.<init>(r7)     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            r7 = 0
        L_0x0020:
            int r8 = r5.length     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            if (r7 >= r8) goto L_0x0035
            r8 = r5[r7]     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            java.lang.String r8 = r8.getName()     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            r9 = r5[r7]     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            java.lang.String r9 = r9.getValue()     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            r6.put(r8, r9)     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005e }
            int r7 = r7 + 1
            goto L_0x0020
        L_0x0035:
            org.apache.http.HttpEntity r0 = r3.getEntity()     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005b }
            if (r0 == 0) goto L_0x0044
            org.apache.http.HttpEntity r0 = r3.getEntity()     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005b }
            byte[] r0 = a((org.apache.http.HttpEntity) r0)     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005b }
            goto L_0x0046
        L_0x0044:
            byte[] r0 = new byte[r2]     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005b }
        L_0x0046:
            r1 = r0
            r0 = 200(0xc8, float:2.8E-43)
            if (r4 < r0) goto L_0x0055
            r0 = 299(0x12b, float:4.19E-43)
            if (r4 > r0) goto L_0x0055
            com.yandex.metrica.impl.ob.ft r0 = new com.yandex.metrica.impl.ob.ft     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005b }
            r0.<init>(r1, r6, r2)     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005b }
            return r0
        L_0x0055:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005b }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005b }
            throw r0     // Catch:{ SocketTimeoutException -> 0x00c8, ConnectTimeoutException -> 0x00bc, MalformedURLException -> 0x00a3, IOException -> 0x005b }
        L_0x005b:
            r4 = move-exception
            r0 = r6
            goto L_0x005f
        L_0x005e:
            r4 = move-exception
        L_0x005f:
            r10 = r3
            r3 = r1
            r1 = r10
            goto L_0x0065
        L_0x0063:
            r4 = move-exception
            r3 = r1
        L_0x0065:
            if (r1 == 0) goto L_0x009b
            org.apache.http.StatusLine r1 = r1.getStatusLine()
            int r1 = r1.getStatusCode()
            if (r3 == 0) goto L_0x0093
            com.yandex.metrica.impl.ob.ft r4 = new com.yandex.metrica.impl.ob.ft
            r4.<init>(r3, r0, r2)
            r0 = 401(0x191, float:5.62E-43)
            if (r1 == r0) goto L_0x0087
            r0 = 403(0x193, float:5.65E-43)
            if (r1 != r0) goto L_0x007f
            goto L_0x0087
        L_0x007f:
            com.yandex.metrica.impl.ob.fr r12 = new com.yandex.metrica.impl.ob.fr
            com.yandex.metrica.impl.ob.fr$a r0 = com.yandex.metrica.impl.ob.fr.a.SERVER
            r12.<init>((byte) r2)
            throw r12
        L_0x0087:
            com.yandex.metrica.impl.ob.fr r0 = new com.yandex.metrica.impl.ob.fr
            com.yandex.metrica.impl.ob.fr$a r1 = com.yandex.metrica.impl.ob.fr.a.AUTH
            r0.<init>((byte) r2)
            a((com.yandex.metrica.impl.ob.fu<?>) r12, (com.yandex.metrica.impl.ob.fr) r0)
            goto L_0x0000
        L_0x0093:
            com.yandex.metrica.impl.ob.fr r12 = new com.yandex.metrica.impl.ob.fr
            com.yandex.metrica.impl.ob.fr$a r0 = com.yandex.metrica.impl.ob.fr.a.NETWORK
            r12.<init>((byte) r2)
            throw r12
        L_0x009b:
            com.yandex.metrica.impl.ob.fr r12 = new com.yandex.metrica.impl.ob.fr
            com.yandex.metrica.impl.ob.fr$a r0 = com.yandex.metrica.impl.ob.fr.a.DEFAULT
            r12.<init>((java.lang.Throwable) r4)
            throw r12
        L_0x00a3:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Bad URL "
            r2.<init>(r3)
            java.lang.String r12 = r12.a()
            r2.append(r12)
            java.lang.String r12 = r2.toString()
            r1.<init>(r12, r0)
            throw r1
        L_0x00bc:
            com.yandex.metrica.impl.ob.fr r0 = new com.yandex.metrica.impl.ob.fr
            com.yandex.metrica.impl.ob.fr$a r1 = com.yandex.metrica.impl.ob.fr.a.NO_CONNECTION
            r0.<init>()
            a((com.yandex.metrica.impl.ob.fu<?>) r12, (com.yandex.metrica.impl.ob.fr) r0)
            goto L_0x0000
        L_0x00c8:
            com.yandex.metrica.impl.ob.fr r0 = new com.yandex.metrica.impl.ob.fr
            com.yandex.metrica.impl.ob.fr$a r1 = com.yandex.metrica.impl.ob.fr.a.TIMEOUT
            r0.<init>()
            a((com.yandex.metrica.impl.ob.fu<?>) r12, (com.yandex.metrica.impl.ob.fr) r0)
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.fq.a(com.yandex.metrica.impl.ob.fu):com.yandex.metrica.impl.ob.ft");
    }

    private static void a(fu<?> fuVar, fr frVar) throws fr {
        try {
            fuVar.o().a(frVar);
        } catch (fr e) {
            throw e;
        }
    }

    private static byte[] a(HttpEntity httpEntity) throws IOException, fr {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max((int) httpEntity.getContentLength(), 256));
        try {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                byte[] bArr = new byte[d.fb];
                while (true) {
                    int read = content.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                return byteArrayOutputStream.toByteArray();
            }
            fr.a aVar = fr.a.SERVER;
            throw new fr();
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException unused) {
            }
            byteArrayOutputStream.close();
        }
    }

    public static String a(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }
}
