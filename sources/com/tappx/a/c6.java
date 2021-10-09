package com.tappx.a;

import com.appnext.base.b.d;
import com.tappx.a.h5;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class c6 implements n5 {
    protected static final boolean c = a6.b;

    /* renamed from: a  reason: collision with root package name */
    private final b6 f402a;
    protected final d6 b;

    public c6(b6 b6Var) {
        this(b6Var, new d6(4096));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005e, code lost:
        r15 = null;
        r2 = r12;
        r19 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00aa, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b5, code lost:
        r1 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b8, code lost:
        r19 = r1;
        r15 = null;
        r2 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bd, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00be, code lost:
        r19 = r1;
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00c3, code lost:
        r0 = r2.d();
        com.tappx.a.a6.c("Unexpected response code %d for %s", java.lang.Integer.valueOf(r0), r29.r());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00dc, code lost:
        if (r15 != null) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00de, code lost:
        r13 = new com.tappx.a.q5(r0, r15, false, android.os.SystemClock.elapsedRealtime() - r9, r19);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ef, code lost:
        if (r0 == 401) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f8, code lost:
        if (r0 < 400) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0104, code lost:
        throw new com.tappx.a.j5(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0107, code lost:
        if (r0 < 500) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0111, code lost:
        if (r29.x() != false) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0113, code lost:
        a("server", r8, new com.tappx.a.x5(r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0124, code lost:
        throw new com.tappx.a.x5(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x012a, code lost:
        throw new com.tappx.a.x5(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x012b, code lost:
        a("auth", r8, new com.tappx.a.g5(r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0137, code lost:
        a("network", r8, new com.tappx.a.p5());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0148, code lost:
        throw new com.tappx.a.r5(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0149, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0164, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r29.r(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0165, code lost:
        a("socket", r8, new com.tappx.a.y5());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0149 A[ExcHandler: MalformedURLException (r0v1 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:79:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0143 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.tappx.a.q5 a(com.tappx.a.s5<?> r29) {
        /*
            r28 = this;
            r7 = r28
            r8 = r29
            long r9 = android.os.SystemClock.elapsedRealtime()
        L_0x0008:
            java.util.List r1 = java.util.Collections.emptyList()
            r11 = 0
            r2 = 0
            com.tappx.a.h5$a r0 = r29.d()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00bd }
            java.util.Map r0 = r7.a((com.tappx.a.h5.a) r0)     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00bd }
            com.tappx.a.b6 r3 = r7.f402a     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00bd }
            com.tappx.a.g6 r12 = r3.a(r8, r0)     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00bd }
            int r14 = r12.d()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00b7 }
            java.util.List r13 = r12.c()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00b7 }
            r0 = 304(0x130, float:4.26E-43)
            if (r14 != r0) goto L_0x0064
            com.tappx.a.h5$a r0 = r29.d()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            if (r0 != 0) goto L_0x0043
            com.tappx.a.q5 r0 = new com.tappx.a.q5     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            r16 = 304(0x130, float:4.26E-43)
            r17 = 0
            r18 = 1
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            long r19 = r3 - r9
            r15 = r0
            r21 = r13
            r15.<init>((int) r16, (byte[]) r17, (boolean) r18, (long) r19, (java.util.List<com.tappx.a.m5>) r21)     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            return r0
        L_0x0043:
            java.util.List r27 = a((java.util.List<com.tappx.a.m5>) r13, (com.tappx.a.h5.a) r0)     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            com.tappx.a.q5 r1 = new com.tappx.a.q5     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            r22 = 304(0x130, float:4.26E-43)
            byte[] r0 = r0.f460a     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            r24 = 1
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            long r25 = r3 - r9
            r21 = r1
            r23 = r0
            r21.<init>((int) r22, (byte[]) r23, (boolean) r24, (long) r25, (java.util.List<com.tappx.a.m5>) r27)     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            return r1
        L_0x005d:
            r0 = move-exception
            r15 = r2
            r2 = r12
            r19 = r13
            goto L_0x00c1
        L_0x0064:
            java.io.InputStream r0 = r12.a()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00b4 }
            if (r0 == 0) goto L_0x0073
            int r1 = r12.b()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            byte[] r0 = r7.a((java.io.InputStream) r0, (int) r1)     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x005d }
            goto L_0x0075
        L_0x0073:
            byte[] r0 = new byte[r11]     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00b4 }
        L_0x0075:
            r20 = r0
            long r0 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00ac }
            long r2 = r0 - r9
            r1 = r28
            r4 = r29
            r5 = r20
            r6 = r14
            r1.a(r2, r4, r5, r6)     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00ac }
            r0 = 200(0xc8, float:2.8E-43)
            if (r14 < r0) goto L_0x00a3
            r0 = 299(0x12b, float:4.19E-43)
            if (r14 > r0) goto L_0x00a3
            com.tappx.a.q5 r0 = new com.tappx.a.q5     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00ac }
            r16 = 0
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00ac }
            long r17 = r1 - r9
            r1 = r13
            r13 = r0
            r15 = r20
            r19 = r1
            r13.<init>((int) r14, (byte[]) r15, (boolean) r16, (long) r17, (java.util.List<com.tappx.a.m5>) r19)     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00aa }
            return r0
        L_0x00a3:
            r1 = r13
            java.io.IOException r0 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00aa }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00aa }
            throw r0     // Catch:{ SocketTimeoutException -> 0x0165, MalformedURLException -> 0x0149, IOException -> 0x00aa }
        L_0x00aa:
            r0 = move-exception
            goto L_0x00ae
        L_0x00ac:
            r0 = move-exception
            r1 = r13
        L_0x00ae:
            r19 = r1
            r2 = r12
            r15 = r20
            goto L_0x00c1
        L_0x00b4:
            r0 = move-exception
            r1 = r13
            goto L_0x00b8
        L_0x00b7:
            r0 = move-exception
        L_0x00b8:
            r19 = r1
            r15 = r2
            r2 = r12
            goto L_0x00c1
        L_0x00bd:
            r0 = move-exception
            r19 = r1
            r15 = r2
        L_0x00c1:
            if (r2 == 0) goto L_0x0143
            int r0 = r2.d()
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            r1[r11] = r2
            java.lang.String r2 = r29.r()
            r3 = 1
            r1[r3] = r2
            java.lang.String r2 = "Unexpected response code %d for %s"
            com.tappx.a.a6.c(r2, r1)
            if (r15 == 0) goto L_0x0137
            com.tappx.a.q5 r1 = new com.tappx.a.q5
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r17 = r2 - r9
            r16 = 0
            r13 = r1
            r14 = r0
            r13.<init>((int) r14, (byte[]) r15, (boolean) r16, (long) r17, (java.util.List<com.tappx.a.m5>) r19)
            r2 = 401(0x191, float:5.62E-43)
            if (r0 == r2) goto L_0x012b
            r2 = 403(0x193, float:5.65E-43)
            if (r0 != r2) goto L_0x00f6
            goto L_0x012b
        L_0x00f6:
            r2 = 400(0x190, float:5.6E-43)
            if (r0 < r2) goto L_0x0105
            r2 = 499(0x1f3, float:6.99E-43)
            if (r0 <= r2) goto L_0x00ff
            goto L_0x0105
        L_0x00ff:
            com.tappx.a.j5 r0 = new com.tappx.a.j5
            r0.<init>(r1)
            throw r0
        L_0x0105:
            r2 = 500(0x1f4, float:7.0E-43)
            if (r0 < r2) goto L_0x0125
            r2 = 599(0x257, float:8.4E-43)
            if (r0 > r2) goto L_0x0125
            boolean r0 = r29.x()
            if (r0 == 0) goto L_0x011f
            com.tappx.a.x5 r0 = new com.tappx.a.x5
            r0.<init>(r1)
            java.lang.String r1 = "server"
            a(r1, r8, r0)
            goto L_0x0008
        L_0x011f:
            com.tappx.a.x5 r0 = new com.tappx.a.x5
            r0.<init>(r1)
            throw r0
        L_0x0125:
            com.tappx.a.x5 r0 = new com.tappx.a.x5
            r0.<init>(r1)
            throw r0
        L_0x012b:
            com.tappx.a.g5 r0 = new com.tappx.a.g5
            r0.<init>(r1)
            java.lang.String r1 = "auth"
            a(r1, r8, r0)
            goto L_0x0008
        L_0x0137:
            com.tappx.a.p5 r0 = new com.tappx.a.p5
            r0.<init>()
            java.lang.String r1 = "network"
            a(r1, r8, r0)
            goto L_0x0008
        L_0x0143:
            com.tappx.a.r5 r1 = new com.tappx.a.r5
            r1.<init>(r0)
            throw r1
        L_0x0149:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Bad URL "
            r2.append(r3)
            java.lang.String r3 = r29.r()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0165:
            com.tappx.a.y5 r0 = new com.tappx.a.y5
            r0.<init>()
            java.lang.String r1 = "socket"
            a(r1, r8, r0)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.c6.a(com.tappx.a.s5):com.tappx.a.q5");
    }

    public c6(b6 b6Var, d6 d6Var) {
        this.f402a = b6Var;
        this.b = d6Var;
    }

    private void a(long j, s5<?> s5Var, byte[] bArr, int i) {
        if (c || j > 3000) {
            Object[] objArr = new Object[5];
            objArr[0] = s5Var;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(i);
            objArr[4] = Integer.valueOf(s5Var.n().b());
            a6.b("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void a(String str, s5<?> s5Var, z5 z5Var) {
        w5 n = s5Var.n();
        int p = s5Var.p();
        try {
            n.a(z5Var);
            s5Var.a(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(p)}));
        } catch (z5 e) {
            s5Var.a(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(p)}));
            throw e;
        }
    }

    private Map<String, String> a(h5.a aVar) {
        if (aVar == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        String str = aVar.b;
        if (str != null) {
            hashMap.put("If-None-Match", str);
        }
        long j = aVar.d;
        if (j > 0) {
            hashMap.put("If-Modified-Since", f6.a(j));
        }
        return hashMap;
    }

    private byte[] a(InputStream inputStream, int i) {
        i6 i6Var = new i6(this.b, i);
        byte[] bArr = null;
        if (inputStream != null) {
            try {
                bArr = this.b.a((int) d.fb);
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    i6Var.write(bArr, 0, read);
                }
                return i6Var.toByteArray();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                        a6.d("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.b.a(bArr);
                i6Var.close();
            }
        } else {
            throw new x5();
        }
    }

    private static List<m5> a(List<m5> list, h5.a aVar) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        if (!list.isEmpty()) {
            for (m5 a2 : list) {
                treeSet.add(a2.a());
            }
        }
        ArrayList arrayList = new ArrayList(list);
        List<m5> list2 = aVar.h;
        if (list2 != null) {
            if (!list2.isEmpty()) {
                for (m5 next : aVar.h) {
                    if (!treeSet.contains(next.a())) {
                        arrayList.add(next);
                    }
                }
            }
        } else if (!aVar.g.isEmpty()) {
            for (Map.Entry next2 : aVar.g.entrySet()) {
                if (!treeSet.contains(next2.getKey())) {
                    arrayList.add(new m5((String) next2.getKey(), (String) next2.getValue()));
                }
            }
        }
        return arrayList;
    }
}
