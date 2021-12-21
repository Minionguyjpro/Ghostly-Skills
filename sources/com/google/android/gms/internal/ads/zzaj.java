package com.google.android.gms.internal.ads;

import com.appnext.base.b.d;
import java.io.IOException;
import java.io.InputStream;

public class zzaj implements zzm {
    private static final boolean DEBUG = zzaf.DEBUG;
    @Deprecated
    private final zzar zzbo;
    private final zzai zzbp;
    private final zzak zzbq;

    public zzaj(zzai zzai) {
        this(zzai, new zzak(4096));
    }

    private zzaj(zzai zzai, zzak zzak) {
        this.zzbp = zzai;
        this.zzbo = zzai;
        this.zzbq = zzak;
    }

    @Deprecated
    public zzaj(zzar zzar) {
        this(zzar, new zzak(4096));
    }

    @Deprecated
    private zzaj(zzar zzar, zzak zzak) {
        this.zzbo = zzar;
        this.zzbp = new zzah(zzar);
        this.zzbq = zzak;
    }

    private static void zza(String str, zzr<?> zzr, zzae zzae) throws zzae {
        zzab zzj = zzr.zzj();
        int zzi = zzr.zzi();
        try {
            zzj.zza(zzae);
            zzr.zzb(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(zzi)}));
        } catch (zzae e) {
            zzr.zzb(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(zzi)}));
            throw e;
        }
    }

    private final byte[] zza(InputStream inputStream, int i) throws IOException, zzac {
        zzau zzau = new zzau(this.zzbq, i);
        byte[] bArr = null;
        if (inputStream != null) {
            try {
                bArr = this.zzbq.zzb(d.fb);
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    zzau.write(bArr, 0, read);
                }
                return zzau.toByteArray();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                        zzaf.v("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.zzbq.zza(bArr);
                zzau.close();
            }
        } else {
            throw new zzac();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0201, code lost:
        throw new com.google.android.gms.internal.ads.zzq(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0202, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0203, code lost:
        r2 = java.lang.String.valueOf(r22.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0213, code lost:
        if (r2.length() != 0) goto L_0x0215;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0215, code lost:
        r2 = "Bad URL ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x021a, code lost:
        r2 = new java.lang.String("Bad URL ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0222, code lost:
        throw new java.lang.RuntimeException(r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0223, code lost:
        r0 = new com.google.android.gms.internal.ads.zzad();
        r5 = "socket";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x018d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x018e, code lost:
        r17 = r5;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0192, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0193, code lost:
        r17 = r5;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0196, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0199, code lost:
        r0 = r8.getStatusCode();
        com.google.android.gms.internal.ads.zzaf.e("Unexpected response code %d for %s", java.lang.Integer.valueOf(r0), r22.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01b0, code lost:
        if (r13 != null) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01b2, code lost:
        r11 = new com.google.android.gms.internal.ads.zzp(r0, r13, false, android.os.SystemClock.elapsedRealtime() - r3, r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01c2, code lost:
        if (r0 == 401) goto L_0x01ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01cb, code lost:
        if (r0 < 400) goto L_0x01d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01d7, code lost:
        throw new com.google.android.gms.internal.ads.zzg(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01da, code lost:
        if (r0 < 500) goto L_0x01e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01e5, code lost:
        throw new com.google.android.gms.internal.ads.zzac(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01eb, code lost:
        throw new com.google.android.gms.internal.ads.zzac(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01ec, code lost:
        r0 = new com.google.android.gms.internal.ads.zza(r11);
        r5 = "auth";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01f4, code lost:
        r0 = new com.google.android.gms.internal.ads.zzo();
        r5 = "network";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0202 A[ExcHandler: MalformedURLException (r0v2 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:110:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01fc A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0199  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.ads.zzp zzc(com.google.android.gms.internal.ads.zzr<?> r22) throws com.google.android.gms.internal.ads.zzae {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            long r3 = android.os.SystemClock.elapsedRealtime()
        L_0x0008:
            java.util.List r5 = java.util.Collections.emptyList()
            r6 = 1
            r7 = 2
            r9 = 0
            com.google.android.gms.internal.ads.zzc r0 = r22.zzf()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            if (r0 != 0) goto L_0x001a
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            goto L_0x003e
        L_0x001a:
            java.util.HashMap r10 = new java.util.HashMap     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            r10.<init>()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            java.lang.String r11 = r0.zza     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            if (r11 == 0) goto L_0x002a
            java.lang.String r11 = "If-None-Match"
            java.lang.String r12 = r0.zza     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            r10.put(r11, r12)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
        L_0x002a:
            long r11 = r0.zzc     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            r13 = 0
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 <= 0) goto L_0x003d
            java.lang.String r11 = "If-Modified-Since"
            long r12 = r0.zzc     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            java.lang.String r0 = com.google.android.gms.internal.ads.zzap.zzb((long) r12)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            r10.put(r11, r0)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
        L_0x003d:
            r0 = r10
        L_0x003e:
            com.google.android.gms.internal.ads.zzai r10 = r1.zzbp     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            com.google.android.gms.internal.ads.zzaq r10 = r10.zza(r2, r0)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0192 }
            int r12 = r10.getStatusCode()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.util.List r5 = r10.zzq()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r0 = 304(0x130, float:4.26E-43)
            if (r12 != r0) goto L_0x0116
            com.google.android.gms.internal.ads.zzc r0 = r22.zzf()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r0 != 0) goto L_0x006a
            com.google.android.gms.internal.ads.zzp r0 = new com.google.android.gms.internal.ads.zzp     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r14 = 304(0x130, float:4.26E-43)
            r15 = 0
            r16 = 1
            long r11 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            long r17 = r11 - r3
            r13 = r0
            r19 = r5
            r13.<init>((int) r14, (byte[]) r15, (boolean) r16, (long) r17, (java.util.List<com.google.android.gms.internal.ads.zzl>) r19)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            return r0
        L_0x006a:
            java.util.TreeSet r11 = new java.util.TreeSet     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.util.Comparator r12 = java.lang.String.CASE_INSENSITIVE_ORDER     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r11.<init>(r12)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            boolean r12 = r5.isEmpty()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r12 != 0) goto L_0x008f
            java.util.Iterator r12 = r5.iterator()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
        L_0x007b:
            boolean r13 = r12.hasNext()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r13 == 0) goto L_0x008f
            java.lang.Object r13 = r12.next()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            com.google.android.gms.internal.ads.zzl r13 = (com.google.android.gms.internal.ads.zzl) r13     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.lang.String r13 = r13.getName()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r11.add(r13)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            goto L_0x007b
        L_0x008f:
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r12.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.util.List<com.google.android.gms.internal.ads.zzl> r13 = r0.zzg     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r13 == 0) goto L_0x00c0
            java.util.List<com.google.android.gms.internal.ads.zzl> r13 = r0.zzg     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            boolean r13 = r13.isEmpty()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r13 != 0) goto L_0x00ff
            java.util.List<com.google.android.gms.internal.ads.zzl> r13 = r0.zzg     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
        L_0x00a6:
            boolean r14 = r13.hasNext()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r14 == 0) goto L_0x00ff
            java.lang.Object r14 = r13.next()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            com.google.android.gms.internal.ads.zzl r14 = (com.google.android.gms.internal.ads.zzl) r14     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.lang.String r15 = r14.getName()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            boolean r15 = r11.contains(r15)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r15 != 0) goto L_0x00a6
            r12.add(r14)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            goto L_0x00a6
        L_0x00c0:
            java.util.Map<java.lang.String, java.lang.String> r13 = r0.zzf     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            boolean r13 = r13.isEmpty()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r13 != 0) goto L_0x00ff
            java.util.Map<java.lang.String, java.lang.String> r13 = r0.zzf     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.util.Set r13 = r13.entrySet()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
        L_0x00d2:
            boolean r14 = r13.hasNext()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r14 == 0) goto L_0x00ff
            java.lang.Object r14 = r13.next()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.lang.Object r15 = r14.getKey()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            boolean r15 = r11.contains(r15)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r15 != 0) goto L_0x00d2
            com.google.android.gms.internal.ads.zzl r15 = new com.google.android.gms.internal.ads.zzl     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.lang.Object r16 = r14.getKey()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r8 = r16
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.lang.Object r14 = r14.getValue()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r15.<init>(r8, r14)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r12.add(r15)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            goto L_0x00d2
        L_0x00ff:
            com.google.android.gms.internal.ads.zzp r8 = new com.google.android.gms.internal.ads.zzp     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r15 = 304(0x130, float:4.26E-43)
            byte[] r0 = r0.data     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            r17 = 1
            long r13 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            long r18 = r13 - r3
            r14 = r8
            r16 = r0
            r20 = r12
            r14.<init>((int) r15, (byte[]) r16, (boolean) r17, (long) r18, (java.util.List<com.google.android.gms.internal.ads.zzl>) r20)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            return r8
        L_0x0116:
            java.io.InputStream r0 = r10.getContent()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            if (r0 == 0) goto L_0x0125
            int r8 = r10.getContentLength()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            byte[] r0 = r1.zza(r0, r8)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
            goto L_0x0127
        L_0x0125:
            byte[] r0 = new byte[r9]     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x018d }
        L_0x0127:
            r8 = r0
            long r13 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            long r13 = r13 - r3
            boolean r0 = DEBUG     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            if (r0 != 0) goto L_0x0137
            r15 = 3000(0xbb8, double:1.482E-320)
            int r0 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r0 <= 0) goto L_0x0169
        L_0x0137:
            java.lang.String r0 = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]"
            r11 = 5
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            r11[r9] = r2     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            r11[r6] = r13     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            if (r8 == 0) goto L_0x014c
            int r13 = r8.length     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            goto L_0x014e
        L_0x014c:
            java.lang.String r13 = "null"
        L_0x014e:
            r11[r7] = r13     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            r13 = 3
            java.lang.Integer r14 = java.lang.Integer.valueOf(r12)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            r11[r13] = r14     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            r13 = 4
            com.google.android.gms.internal.ads.zzab r14 = r22.zzj()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            int r14 = r14.zzd()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            r11[r13] = r14     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            com.google.android.gms.internal.ads.zzaf.d(r0, r11)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
        L_0x0169:
            r0 = 200(0xc8, float:2.8E-43)
            if (r12 < r0) goto L_0x0181
            r0 = 299(0x12b, float:4.19E-43)
            if (r12 > r0) goto L_0x0181
            com.google.android.gms.internal.ads.zzp r0 = new com.google.android.gms.internal.ads.zzp     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            r14 = 0
            long r15 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            long r15 = r15 - r3
            r11 = r0
            r13 = r8
            r17 = r5
            r11.<init>((int) r12, (byte[]) r13, (boolean) r14, (long) r15, (java.util.List<com.google.android.gms.internal.ads.zzl>) r17)     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            return r0
        L_0x0181:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
            throw r0     // Catch:{ SocketTimeoutException -> 0x0223, MalformedURLException -> 0x0202, IOException -> 0x0187 }
        L_0x0187:
            r0 = move-exception
            r17 = r5
            r13 = r8
            r8 = r10
            goto L_0x0197
        L_0x018d:
            r0 = move-exception
            r17 = r5
            r8 = r10
            goto L_0x0196
        L_0x0192:
            r0 = move-exception
            r17 = r5
            r8 = 0
        L_0x0196:
            r13 = 0
        L_0x0197:
            if (r8 == 0) goto L_0x01fc
            int r0 = r8.getStatusCode()
            java.lang.Object[] r5 = new java.lang.Object[r7]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            r5[r9] = r7
            java.lang.String r7 = r22.getUrl()
            r5[r6] = r7
            java.lang.String r6 = "Unexpected response code %d for %s"
            com.google.android.gms.internal.ads.zzaf.e(r6, r5)
            if (r13 == 0) goto L_0x01f4
            com.google.android.gms.internal.ads.zzp r5 = new com.google.android.gms.internal.ads.zzp
            r14 = 0
            long r6 = android.os.SystemClock.elapsedRealtime()
            long r15 = r6 - r3
            r11 = r5
            r12 = r0
            r11.<init>((int) r12, (byte[]) r13, (boolean) r14, (long) r15, (java.util.List<com.google.android.gms.internal.ads.zzl>) r17)
            r6 = 401(0x191, float:5.62E-43)
            if (r0 == r6) goto L_0x01ec
            r6 = 403(0x193, float:5.65E-43)
            if (r0 != r6) goto L_0x01c9
            goto L_0x01ec
        L_0x01c9:
            r2 = 400(0x190, float:5.6E-43)
            if (r0 < r2) goto L_0x01d8
            r2 = 499(0x1f3, float:6.99E-43)
            if (r0 <= r2) goto L_0x01d2
            goto L_0x01d8
        L_0x01d2:
            com.google.android.gms.internal.ads.zzg r0 = new com.google.android.gms.internal.ads.zzg
            r0.<init>(r5)
            throw r0
        L_0x01d8:
            r2 = 500(0x1f4, float:7.0E-43)
            if (r0 < r2) goto L_0x01e6
            r2 = 599(0x257, float:8.4E-43)
            if (r0 > r2) goto L_0x01e6
            com.google.android.gms.internal.ads.zzac r0 = new com.google.android.gms.internal.ads.zzac
            r0.<init>(r5)
            throw r0
        L_0x01e6:
            com.google.android.gms.internal.ads.zzac r0 = new com.google.android.gms.internal.ads.zzac
            r0.<init>(r5)
            throw r0
        L_0x01ec:
            com.google.android.gms.internal.ads.zza r0 = new com.google.android.gms.internal.ads.zza
            r0.<init>(r5)
            java.lang.String r5 = "auth"
            goto L_0x022a
        L_0x01f4:
            com.google.android.gms.internal.ads.zzo r0 = new com.google.android.gms.internal.ads.zzo
            r0.<init>()
            java.lang.String r5 = "network"
            goto L_0x022a
        L_0x01fc:
            com.google.android.gms.internal.ads.zzq r2 = new com.google.android.gms.internal.ads.zzq
            r2.<init>(r0)
            throw r2
        L_0x0202:
            r0 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException
            java.lang.String r4 = "Bad URL "
            java.lang.String r2 = r22.getUrl()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r5 = r2.length()
            if (r5 == 0) goto L_0x021a
            java.lang.String r2 = r4.concat(r2)
            goto L_0x021f
        L_0x021a:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r4)
        L_0x021f:
            r3.<init>(r2, r0)
            throw r3
        L_0x0223:
            com.google.android.gms.internal.ads.zzad r0 = new com.google.android.gms.internal.ads.zzad
            r0.<init>()
            java.lang.String r5 = "socket"
        L_0x022a:
            zza(r5, r2, r0)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaj.zzc(com.google.android.gms.internal.ads.zzr):com.google.android.gms.internal.ads.zzp");
    }
}
