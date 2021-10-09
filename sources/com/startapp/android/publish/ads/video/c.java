package com.startapp.android.publish.ads.video;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.appnext.base.b.d;
import com.startapp.android.publish.ads.video.b.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* compiled from: StartAppSDK */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private boolean f116a;
    /* access modifiers changed from: private */
    public c.C0002c b;
    private String c;

    /* compiled from: StartAppSDK */
    public interface a {
        void a(String str);
    }

    /* compiled from: StartAppSDK */
    private static class b {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final c f130a = new c();
    }

    private c() {
        this.f116a = true;
        this.b = null;
        this.c = null;
    }

    public static c a() {
        return b.f130a;
    }

    public void a(c.C0002c cVar) {
        this.b = cVar;
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [java.io.DataInputStream, java.io.FileOutputStream, java.lang.String, java.io.InputStream] */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0125, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0126, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0128, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0129, code lost:
        r8 = null;
        r10 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0125 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x006a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(android.content.Context r23, java.net.URL r24, java.lang.String r25, com.startapp.android.publish.ads.video.c.a r26) {
        /*
            r22 = this;
            r1 = r22
            r2 = r23
            r3 = r24
            r0 = r25
            r4 = r26
            java.lang.String r5 = ".temp"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Downloading video from "
            r6.append(r7)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "StartAppWall.ProgressiveVideoManager"
            r8 = 3
            com.startapp.common.a.g.a((java.lang.String) r7, (int) r8, (java.lang.String) r6)
            java.lang.String r6 = r24.toString()
            r1.c = r6
            r6 = 1
            r1.f116a = r6
            com.startapp.android.publish.adsCommon.b r9 = com.startapp.android.publish.adsCommon.b.a()
            com.startapp.android.publish.adsCommon.n r9 = r9.H()
            int r9 = r9.l()
            r10 = 0
            java.lang.String r11 = com.startapp.android.publish.ads.video.h.a((android.content.Context) r2, (java.lang.String) r0)     // Catch:{ Exception -> 0x0138, all -> 0x0133 }
            java.io.File r12 = new java.io.File     // Catch:{ Exception -> 0x0138, all -> 0x0133 }
            r12.<init>(r11)     // Catch:{ Exception -> 0x0138, all -> 0x0133 }
            boolean r13 = r12.exists()     // Catch:{ Exception -> 0x0138, all -> 0x0133 }
            if (r13 == 0) goto L_0x0054
            r1.c = r10     // Catch:{ Exception -> 0x0053 }
            r10.close()     // Catch:{ Exception -> 0x0053 }
            r10.close()     // Catch:{ Exception -> 0x0053 }
            r10.close()     // Catch:{ Exception -> 0x0053 }
        L_0x0053:
            return r11
        L_0x0054:
            java.net.URLConnection r13 = r24.openConnection()     // Catch:{ Exception -> 0x0138, all -> 0x0133 }
            r13.connect()     // Catch:{ Exception -> 0x0138, all -> 0x0133 }
            int r14 = r13.getContentLength()     // Catch:{ Exception -> 0x0138, all -> 0x0133 }
            java.io.InputStream r13 = r13.getInputStream()     // Catch:{ Exception -> 0x0138, all -> 0x0133 }
            java.io.DataInputStream r15 = new java.io.DataInputStream     // Catch:{ Exception -> 0x012f, all -> 0x012c }
            r15.<init>(r13)     // Catch:{ Exception -> 0x012f, all -> 0x012c }
            r6 = 4096(0x1000, float:5.74E-42)
            byte[] r6 = new byte[r6]     // Catch:{ Exception -> 0x0128, all -> 0x0125 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0128, all -> 0x0125 }
            r10.<init>()     // Catch:{ Exception -> 0x0128, all -> 0x0125 }
            r10.append(r0)     // Catch:{ Exception -> 0x0128, all -> 0x0125 }
            r10.append(r5)     // Catch:{ Exception -> 0x0128, all -> 0x0125 }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x0128, all -> 0x0125 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0122, all -> 0x0125 }
            r0.<init>()     // Catch:{ Exception -> 0x0122, all -> 0x0125 }
            r0.append(r11)     // Catch:{ Exception -> 0x0122, all -> 0x0125 }
            r0.append(r5)     // Catch:{ Exception -> 0x0122, all -> 0x0125 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0122, all -> 0x0125 }
            r5 = 0
            java.io.FileOutputStream r8 = r2.openFileOutput(r10, r5)     // Catch:{ Exception -> 0x0122, all -> 0x0125 }
            r16 = 0
            r17 = 0
            r18 = 0
        L_0x0095:
            int r5 = r15.read(r6)     // Catch:{ Exception -> 0x0120 }
            if (r5 <= 0) goto L_0x00d8
            r19 = r11
            boolean r11 = r1.f116a     // Catch:{ Exception -> 0x0120 }
            if (r11 == 0) goto L_0x00da
            r11 = 0
            r8.write(r6, r11, r5)     // Catch:{ Exception -> 0x0120 }
            int r5 = r16 + r5
            r16 = r12
            double r11 = (double) r5
            r20 = 4636737291354636288(0x4059000000000000, double:100.0)
            java.lang.Double.isNaN(r11)
            double r11 = r11 * r20
            r21 = r5
            r20 = r6
            double r5 = (double) r14
            java.lang.Double.isNaN(r5)
            double r11 = r11 / r5
            int r5 = (int) r11
            if (r5 < r9) goto L_0x00cf
            if (r17 != 0) goto L_0x00c6
            if (r4 == 0) goto L_0x00c6
            r1.a(r4, r0)     // Catch:{ Exception -> 0x0120 }
            r17 = 1
        L_0x00c6:
            int r6 = r18 + 1
            if (r5 < r6) goto L_0x00cf
            r1.a((int) r5)     // Catch:{ Exception -> 0x0120 }
            r18 = r5
        L_0x00cf:
            r12 = r16
            r11 = r19
            r6 = r20
            r16 = r21
            goto L_0x0095
        L_0x00d8:
            r19 = r11
        L_0x00da:
            r16 = r12
            boolean r0 = r1.f116a     // Catch:{ Exception -> 0x0120 }
            if (r0 != 0) goto L_0x0103
            if (r5 <= 0) goto L_0x0103
            java.lang.String r0 = "Video downloading disabled"
            r4 = 3
            com.startapp.common.a.g.a((java.lang.String) r7, (int) r4, (java.lang.String) r0)     // Catch:{ Exception -> 0x0120 }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0120 }
            java.lang.String r4 = com.startapp.android.publish.ads.video.h.a((android.content.Context) r2, (java.lang.String) r10)     // Catch:{ Exception -> 0x0120 }
            r0.<init>(r4)     // Catch:{ Exception -> 0x0120 }
            r0.delete()     // Catch:{ Exception -> 0x0120 }
            java.lang.String r0 = "downloadInterrupted"
            r2 = 0
            r1.c = r2     // Catch:{ Exception -> 0x0102 }
            r13.close()     // Catch:{ Exception -> 0x0102 }
            r15.close()     // Catch:{ Exception -> 0x0102 }
            r8.close()     // Catch:{ Exception -> 0x0102 }
        L_0x0102:
            return r0
        L_0x0103:
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0120 }
            java.lang.String r4 = com.startapp.android.publish.ads.video.h.a((android.content.Context) r2, (java.lang.String) r10)     // Catch:{ Exception -> 0x0120 }
            r0.<init>(r4)     // Catch:{ Exception -> 0x0120 }
            r4 = r16
            r1.a(r2, r0, r4)     // Catch:{ Exception -> 0x0120 }
            r2 = 0
            r1.c = r2     // Catch:{ Exception -> 0x011d }
            r13.close()     // Catch:{ Exception -> 0x011d }
            r15.close()     // Catch:{ Exception -> 0x011d }
            r8.close()     // Catch:{ Exception -> 0x011d }
        L_0x011d:
            r10 = r19
            goto L_0x016b
        L_0x0120:
            r0 = move-exception
            goto L_0x013d
        L_0x0122:
            r0 = move-exception
            r8 = 0
            goto L_0x013d
        L_0x0125:
            r0 = move-exception
            r8 = 0
            goto L_0x016d
        L_0x0128:
            r0 = move-exception
            r8 = 0
            r10 = 0
            goto L_0x013d
        L_0x012c:
            r0 = move-exception
            r8 = 0
            goto L_0x0136
        L_0x012f:
            r0 = move-exception
            r8 = 0
            r10 = 0
            goto L_0x013c
        L_0x0133:
            r0 = move-exception
            r8 = 0
            r13 = 0
        L_0x0136:
            r15 = 0
            goto L_0x016d
        L_0x0138:
            r0 = move-exception
            r8 = 0
            r10 = 0
            r13 = 0
        L_0x013c:
            r15 = 0
        L_0x013d:
            r4 = 6
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x016c }
            r5.<init>()     // Catch:{ all -> 0x016c }
            java.lang.String r6 = "Error downloading video from "
            r5.append(r6)     // Catch:{ all -> 0x016c }
            r5.append(r3)     // Catch:{ all -> 0x016c }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x016c }
            com.startapp.common.a.g.a(r7, r4, r3, r0)     // Catch:{ all -> 0x016c }
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x016c }
            java.lang.String r2 = com.startapp.android.publish.ads.video.h.a((android.content.Context) r2, (java.lang.String) r10)     // Catch:{ all -> 0x016c }
            r0.<init>(r2)     // Catch:{ all -> 0x016c }
            r0.delete()     // Catch:{ all -> 0x016c }
            r2 = 0
            r1.c = r2     // Catch:{ Exception -> 0x016a }
            r13.close()     // Catch:{ Exception -> 0x016a }
            r15.close()     // Catch:{ Exception -> 0x016a }
            r8.close()     // Catch:{ Exception -> 0x016a }
        L_0x016a:
            r10 = 0
        L_0x016b:
            return r10
        L_0x016c:
            r0 = move-exception
        L_0x016d:
            r2 = 0
            r1.c = r2     // Catch:{ Exception -> 0x0179 }
            r13.close()     // Catch:{ Exception -> 0x0179 }
            r15.close()     // Catch:{ Exception -> 0x0179 }
            r8.close()     // Catch:{ Exception -> 0x0179 }
        L_0x0179:
            goto L_0x017b
        L_0x017a:
            throw r0
        L_0x017b:
            goto L_0x017a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.video.c.a(android.content.Context, java.net.URL, java.lang.String, com.startapp.android.publish.ads.video.c$a):java.lang.String");
    }

    private void a(final a aVar, final String str) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                aVar.a(str);
            }
        });
    }

    private void a(final int i) {
        if (this.b != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (c.this.b != null) {
                        c.this.b.a(i);
                    }
                }
            });
        }
    }

    public void a(String str) {
        if (str != null && str.equals(this.c)) {
            this.f116a = false;
        }
    }

    private void a(Context context, File file, File file2) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Exception unused) {
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                fileInputStream2.close();
                fileOutputStream.close();
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileOutputStream = null;
                th = th3;
                try {
                    fileInputStream.close();
                    fileOutputStream.close();
                } catch (Exception unused2) {
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[d.fb];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (Exception unused3) {
                            return;
                        }
                    }
                }
                fileInputStream.close();
            } catch (Exception unused4) {
                fileInputStream2 = fileInputStream;
                fileInputStream2.close();
                fileOutputStream.close();
            } catch (Throwable th4) {
                th = th4;
                fileInputStream.close();
                fileOutputStream.close();
                throw th;
            }
        } catch (Exception unused5) {
            fileOutputStream = null;
            fileInputStream2.close();
            fileOutputStream.close();
        } catch (Throwable th5) {
            fileInputStream = null;
            th = th5;
            fileOutputStream = null;
            fileInputStream.close();
            fileOutputStream.close();
            throw th;
        }
        fileOutputStream.close();
    }

    public boolean b(String str) {
        return str != null && str.endsWith(".temp");
    }

    public void c(String str) {
        if (b(str)) {
            new File(str).delete();
        }
    }
}
