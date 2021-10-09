package com.tappx.a;

import android.os.SystemClock;
import android.text.TextUtils;
import com.tappx.a.h5;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class e6 implements h5 {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, a> f431a;
    private long b;
    private final c c;
    private final int d;

    public interface c {
        File a();
    }

    public e6(c cVar, int i) {
        this.f431a = new LinkedHashMap(16, 0.75f, true);
        this.b = 0;
        this.c = cVar;
        this.d = i;
    }

    private String d(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode());
        return valueOf + String.valueOf(str.substring(length).hashCode());
    }

    private void e(String str) {
        a remove = this.f431a.remove(str);
        if (remove != null) {
            this.b -= remove.f432a;
        }
    }

    public synchronized h5.a a(String str) {
        b bVar;
        a aVar = this.f431a.get(str);
        if (aVar == null) {
            return null;
        }
        File b2 = b(str);
        try {
            bVar = new b(new BufferedInputStream(a(b2)), b2.length());
            a a2 = a.a(bVar);
            if (!TextUtils.equals(str, a2.b)) {
                a6.b("%s: key=%s, found=%s", b2.getAbsolutePath(), str, a2.b);
                e(str);
                bVar.close();
                return null;
            }
            h5.a a3 = aVar.a(a(bVar, bVar.a()));
            bVar.close();
            return a3;
        } catch (IOException e) {
            a6.b("%s: %s", b2.getAbsolutePath(), e.toString());
            c(str);
            return null;
        } catch (Throwable th) {
            bVar.close();
            throw th;
        }
    }

    public File b(String str) {
        return new File(this.c.a(), d(str));
    }

    public synchronized void c(String str) {
        boolean delete = b(str).delete();
        e(str);
        if (!delete) {
            a6.b("Could not delete cache entry for key=%s, filename=%s", str, d(str));
        }
    }

    private void b() {
        if (!this.c.a().exists()) {
            a6.b("Re-initializing cache after external clearing.", new Object[0]);
            this.f431a.clear();
            this.b = 0;
            a();
        }
    }

    static class b extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private final long f433a;
        private long b;

        b(InputStream inputStream, long j) {
            super(inputStream);
            this.f433a = j;
        }

        /* access modifiers changed from: package-private */
        public long a() {
            return this.f433a - this.b;
        }

        public int read() {
            int read = super.read();
            if (read != -1) {
                this.b++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.b += (long) read;
            }
            return read;
        }
    }

    static class a {

        /* renamed from: a  reason: collision with root package name */
        long f432a;
        final String b;
        final String c;
        final long d;
        final long e;
        final long f;
        final long g;
        final List<m5> h;

        private a(String str, String str2, long j, long j2, long j3, long j4, List<m5> list) {
            this.b = str;
            this.c = "".equals(str2) ? null : str2;
            this.d = j;
            this.e = j2;
            this.f = j3;
            this.g = j4;
            this.h = list;
        }

        private static List<m5> a(h5.a aVar) {
            List<m5> list = aVar.h;
            if (list != null) {
                return list;
            }
            return f6.a(aVar.g);
        }

        static a a(b bVar) {
            if (e6.b((InputStream) bVar) == 538247942) {
                return new a(e6.b(bVar), e6.b(bVar), e6.c((InputStream) bVar), e6.c((InputStream) bVar), e6.c((InputStream) bVar), e6.c((InputStream) bVar), e6.a(bVar));
            }
            throw new IOException();
        }

        a(String str, h5.a aVar) {
            this(str, aVar.b, aVar.c, aVar.d, aVar.e, aVar.f, a(aVar));
        }

        /* access modifiers changed from: package-private */
        public h5.a a(byte[] bArr) {
            h5.a aVar = new h5.a();
            aVar.f460a = bArr;
            aVar.b = this.c;
            aVar.c = this.d;
            aVar.d = this.e;
            aVar.e = this.f;
            aVar.f = this.g;
            aVar.g = f6.a(this.h);
            aVar.h = Collections.unmodifiableList(this.h);
            return aVar;
        }

        /* access modifiers changed from: package-private */
        public boolean a(OutputStream outputStream) {
            try {
                e6.a(outputStream, 538247942);
                e6.a(outputStream, this.b);
                e6.a(outputStream, this.c == null ? "" : this.c);
                e6.a(outputStream, this.d);
                e6.a(outputStream, this.e);
                e6.a(outputStream, this.f);
                e6.a(outputStream, this.g);
                e6.a(this.h, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e2) {
                a6.b("%s", e2.toString());
                return false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public OutputStream b(File file) {
        return new FileOutputStream(file);
    }

    static int b(InputStream inputStream) {
        return (a(inputStream) << 24) | (a(inputStream) << 0) | 0 | (a(inputStream) << 8) | (a(inputStream) << 16);
    }

    private void c() {
        if (this.b >= ((long) this.d)) {
            if (a6.b) {
                a6.d("Pruning old cache entries.", new Object[0]);
            }
            long j = this.b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, a>> it = this.f431a.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                a aVar = (a) it.next().getValue();
                if (b(aVar.b).delete()) {
                    this.b -= aVar.f432a;
                } else {
                    String str = aVar.b;
                    a6.b("Could not delete cache entry for key=%s, filename=%s", str, d(str));
                }
                it.remove();
                i++;
                if (((float) this.b) < ((float) this.d) * 0.9f) {
                    break;
                }
            }
            if (a6.b) {
                a6.d("pruned %d files, %d bytes, %d ms", Integer.valueOf(i), Long.valueOf(this.b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    static String b(b bVar) {
        return new String(a(bVar, c((InputStream) bVar)), "UTF-8");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0057 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a() {
        /*
            r9 = this;
            monitor-enter(r9)
            com.tappx.a.e6$c r0 = r9.c     // Catch:{ all -> 0x005f }
            java.io.File r0 = r0.a()     // Catch:{ all -> 0x005f }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x005f }
            r2 = 0
            if (r1 != 0) goto L_0x0024
            boolean r1 = r0.mkdirs()     // Catch:{ all -> 0x005f }
            if (r1 != 0) goto L_0x0022
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x005f }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x005f }
            r1[r2] = r0     // Catch:{ all -> 0x005f }
            java.lang.String r0 = "Unable to create cache dir %s"
            com.tappx.a.a6.c(r0, r1)     // Catch:{ all -> 0x005f }
        L_0x0022:
            monitor-exit(r9)
            return
        L_0x0024:
            java.io.File[] r0 = r0.listFiles()     // Catch:{ all -> 0x005f }
            if (r0 != 0) goto L_0x002c
            monitor-exit(r9)
            return
        L_0x002c:
            int r1 = r0.length     // Catch:{ all -> 0x005f }
        L_0x002d:
            if (r2 >= r1) goto L_0x005d
            r3 = r0[r2]     // Catch:{ all -> 0x005f }
            long r4 = r3.length()     // Catch:{ IOException -> 0x0057 }
            com.tappx.a.e6$b r6 = new com.tappx.a.e6$b     // Catch:{ IOException -> 0x0057 }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0057 }
            java.io.InputStream r8 = r9.a((java.io.File) r3)     // Catch:{ IOException -> 0x0057 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x0057 }
            r6.<init>(r7, r4)     // Catch:{ IOException -> 0x0057 }
            com.tappx.a.e6$a r7 = com.tappx.a.e6.a.a((com.tappx.a.e6.b) r6)     // Catch:{ all -> 0x0052 }
            r7.f432a = r4     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = r7.b     // Catch:{ all -> 0x0052 }
            r9.a((java.lang.String) r4, (com.tappx.a.e6.a) r7)     // Catch:{ all -> 0x0052 }
            r6.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x005a
        L_0x0052:
            r4 = move-exception
            r6.close()     // Catch:{ IOException -> 0x0057 }
            throw r4     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            r3.delete()     // Catch:{ all -> 0x005f }
        L_0x005a:
            int r2 = r2 + 1
            goto L_0x002d
        L_0x005d:
            monitor-exit(r9)
            return
        L_0x005f:
            r0 = move-exception
            monitor-exit(r9)
            goto L_0x0063
        L_0x0062:
            throw r0
        L_0x0063:
            goto L_0x0062
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.e6.a():void");
    }

    static long c(InputStream inputStream) {
        return ((((long) a(inputStream)) & 255) << 0) | 0 | ((((long) a(inputStream)) & 255) << 8) | ((((long) a(inputStream)) & 255) << 16) | ((((long) a(inputStream)) & 255) << 24) | ((((long) a(inputStream)) & 255) << 32) | ((((long) a(inputStream)) & 255) << 40) | ((((long) a(inputStream)) & 255) << 48) | ((255 & ((long) a(inputStream))) << 56);
    }

    public e6(c cVar) {
        this(cVar, 5242880);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0066, code lost:
        if (r0.delete() == false) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0068, code lost:
        com.tappx.a.a6.b("Could not clean up file %s", r0.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0075, code lost:
        b();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0062 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.String r8, com.tappx.a.h5.a r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            long r0 = r7.b     // Catch:{ all -> 0x007a }
            byte[] r2 = r9.f460a     // Catch:{ all -> 0x007a }
            int r3 = r2.length     // Catch:{ all -> 0x007a }
            long r3 = (long) r3     // Catch:{ all -> 0x007a }
            long r0 = r0 + r3
            int r3 = r7.d     // Catch:{ all -> 0x007a }
            long r4 = (long) r3     // Catch:{ all -> 0x007a }
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x001d
            int r0 = r2.length     // Catch:{ all -> 0x007a }
            float r0 = (float) r0
            float r1 = (float) r3
            r2 = 1063675494(0x3f666666, float:0.9)
            float r1 = r1 * r2
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x001d
            monitor-exit(r7)
            return
        L_0x001d:
            java.io.File r0 = r7.b((java.lang.String) r8)     // Catch:{ all -> 0x007a }
            r1 = 0
            r2 = 1
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0062 }
            java.io.OutputStream r4 = r7.b((java.io.File) r0)     // Catch:{ IOException -> 0x0062 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0062 }
            com.tappx.a.e6$a r4 = new com.tappx.a.e6$a     // Catch:{ IOException -> 0x0062 }
            r4.<init>(r8, r9)     // Catch:{ IOException -> 0x0062 }
            boolean r5 = r4.a((java.io.OutputStream) r3)     // Catch:{ IOException -> 0x0062 }
            if (r5 == 0) goto L_0x004c
            byte[] r9 = r9.f460a     // Catch:{ IOException -> 0x0062 }
            r3.write(r9)     // Catch:{ IOException -> 0x0062 }
            r3.close()     // Catch:{ IOException -> 0x0062 }
            long r5 = r0.length()     // Catch:{ IOException -> 0x0062 }
            r4.f432a = r5     // Catch:{ IOException -> 0x0062 }
            r7.a((java.lang.String) r8, (com.tappx.a.e6.a) r4)     // Catch:{ IOException -> 0x0062 }
            r7.c()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0078
        L_0x004c:
            r3.close()     // Catch:{ IOException -> 0x0062 }
            java.lang.String r8 = "Failed to write header for %s"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0062 }
            java.lang.String r3 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x0062 }
            r9[r1] = r3     // Catch:{ IOException -> 0x0062 }
            com.tappx.a.a6.b(r8, r9)     // Catch:{ IOException -> 0x0062 }
            java.io.IOException r8 = new java.io.IOException     // Catch:{ IOException -> 0x0062 }
            r8.<init>()     // Catch:{ IOException -> 0x0062 }
            throw r8     // Catch:{ IOException -> 0x0062 }
        L_0x0062:
            boolean r8 = r0.delete()     // Catch:{ all -> 0x007a }
            if (r8 != 0) goto L_0x0075
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ all -> 0x007a }
            java.lang.String r9 = r0.getAbsolutePath()     // Catch:{ all -> 0x007a }
            r8[r1] = r9     // Catch:{ all -> 0x007a }
            java.lang.String r9 = "Could not clean up file %s"
            com.tappx.a.a6.b(r9, r8)     // Catch:{ all -> 0x007a }
        L_0x0075:
            r7.b()     // Catch:{ all -> 0x007a }
        L_0x0078:
            monitor-exit(r7)
            return
        L_0x007a:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.e6.a(java.lang.String, com.tappx.a.h5$a):void");
    }

    private void a(String str, a aVar) {
        if (!this.f431a.containsKey(str)) {
            this.b += aVar.f432a;
        } else {
            this.b += aVar.f432a - this.f431a.get(str).f432a;
        }
        this.f431a.put(str, aVar);
    }

    static byte[] a(b bVar, long j) {
        long a2 = bVar.a();
        if (j >= 0 && j <= a2) {
            int i = (int) j;
            if (((long) i) == j) {
                byte[] bArr = new byte[i];
                new DataInputStream(bVar).readFully(bArr);
                return bArr;
            }
        }
        throw new IOException("streamToBytes length=" + j + ", maxLength=" + a2);
    }

    /* access modifiers changed from: package-private */
    public InputStream a(File file) {
        return new FileInputStream(file);
    }

    private static int a(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void a(OutputStream outputStream, int i) {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static void a(OutputStream outputStream, long j) {
        outputStream.write((byte) ((int) (j >>> 0)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void a(OutputStream outputStream, String str) {
        byte[] bytes = str.getBytes("UTF-8");
        a(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static void a(List<m5> list, OutputStream outputStream) {
        if (list != null) {
            a(outputStream, list.size());
            for (m5 next : list) {
                a(outputStream, next.a());
                a(outputStream, next.b());
            }
            return;
        }
        a(outputStream, 0);
    }

    static List<m5> a(b bVar) {
        int b2 = b((InputStream) bVar);
        if (b2 >= 0) {
            List<m5> emptyList = b2 == 0 ? Collections.emptyList() : new ArrayList<>();
            for (int i = 0; i < b2; i++) {
                emptyList.add(new m5(b(bVar).intern(), b(bVar).intern()));
            }
            return emptyList;
        }
        throw new IOException("readHeaderList size=" + b2);
    }
}
