package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.cn;
import com.yandex.metrica.impl.ob.cq;
import com.yandex.metrica.impl.ob.r;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

class aj extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f718a;
    private Executor b;
    private final BlockingQueue<b> c = new LinkedBlockingQueue();
    private final Object d = new Object();
    private volatile b e;

    public aj(Executor executor, r rVar) {
        this.f718a = executor;
        this.b = new cn();
        String.format(Locale.US, "[%s:%s]", new Object[]{"NetworkTaskQueue", rVar.toString()});
    }

    public void a(ak akVar) {
        synchronized (this.d) {
            b bVar = new b(akVar, (byte) 0);
            if (!a(bVar)) {
                this.c.offer(bVar);
            }
        }
    }

    public void a() {
        this.e = null;
        this.c.clear();
        interrupt();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:10|11|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        r5.e = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0032 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r5 = this;
        L_0x0000:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r0 = r0.isInterrupted()
            if (r0 != 0) goto L_0x003f
            r0 = 0
            java.util.concurrent.BlockingQueue<com.yandex.metrica.impl.aj$b> r1 = r5.c     // Catch:{ InterruptedException -> 0x0032 }
            java.lang.Object r1 = r1.take()     // Catch:{ InterruptedException -> 0x0032 }
            com.yandex.metrica.impl.aj$b r1 = (com.yandex.metrica.impl.aj.b) r1     // Catch:{ InterruptedException -> 0x0032 }
            r5.e = r1     // Catch:{ InterruptedException -> 0x0032 }
            com.yandex.metrica.impl.aj$b r1 = r5.e     // Catch:{ InterruptedException -> 0x0032 }
            com.yandex.metrica.impl.ak r1 = r1.f720a     // Catch:{ InterruptedException -> 0x0032 }
            boolean r2 = r1.n()     // Catch:{ InterruptedException -> 0x0032 }
            if (r2 == 0) goto L_0x0024
            java.util.concurrent.Executor r2 = r5.f718a     // Catch:{ InterruptedException -> 0x0032 }
            goto L_0x0026
        L_0x0024:
            java.util.concurrent.Executor r2 = r5.b     // Catch:{ InterruptedException -> 0x0032 }
        L_0x0026:
            com.yandex.metrica.impl.aj$a r3 = new com.yandex.metrica.impl.aj$a     // Catch:{ InterruptedException -> 0x0032 }
            r4 = 0
            r3.<init>(r5, r1, r4)     // Catch:{ InterruptedException -> 0x0032 }
            r2.execute(r3)     // Catch:{ InterruptedException -> 0x0032 }
            goto L_0x0039
        L_0x0030:
            r1 = move-exception
            goto L_0x003c
        L_0x0032:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0030 }
            r1.interrupt()     // Catch:{ all -> 0x0030 }
        L_0x0039:
            r5.e = r0
            goto L_0x0000
        L_0x003c:
            r5.e = r0
            throw r1
        L_0x003f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.aj.run():void");
    }

    public boolean b(ak akVar) {
        return a(new b(akVar, (byte) 0));
    }

    private boolean a(b bVar) {
        return this.c.contains(bVar) || bVar.equals(this.e);
    }

    /* access modifiers changed from: package-private */
    public void c(ak akVar) throws InterruptedException {
        boolean b2 = akVar.b();
        cq d2 = akVar.d();
        if (b2 && !d2.b()) {
            b2 = false;
        }
        while (!Thread.currentThread().isInterrupted() && r0) {
            d(akVar);
            boolean z = !akVar.c() && akVar.o();
            if (z) {
                Thread.sleep(akVar.p());
            }
        }
        akVar.f();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.io.OutputStream} */
    /* JADX WARNING: type inference failed for: r4v6, types: [java.io.BufferedInputStream, java.io.InputStream] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(com.yandex.metrica.impl.ak r8) {
        /*
            r7 = this;
            r0 = 0
            r8.e()     // Catch:{ all -> 0x0095 }
            com.yandex.metrica.impl.ob.cq r1 = r8.d()     // Catch:{ all -> 0x0095 }
            java.net.HttpURLConnection r1 = r1.a()     // Catch:{ all -> 0x0095 }
            r2 = 2
            int r3 = r8.i()     // Catch:{ all -> 0x0093 }
            r4 = 1
            if (r2 != r3) goto L_0x0049
            byte[] r2 = r8.j()     // Catch:{ all -> 0x0093 }
            if (r2 == 0) goto L_0x0049
            int r3 = r2.length     // Catch:{ all -> 0x0093 }
            if (r3 <= 0) goto L_0x0049
            java.lang.String r3 = r8.m()     // Catch:{ all -> 0x0093 }
            r1.setDoOutput(r4)     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "Accept-Encoding"
            r1.setRequestProperty(r5, r3)     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "Content-Encoding"
            r1.setRequestProperty(r5, r3)     // Catch:{ all -> 0x0093 }
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch:{ all -> 0x0093 }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0046 }
            int r2 = r2.length     // Catch:{ all -> 0x0046 }
            r5.<init>(r3, r2)     // Catch:{ all -> 0x0046 }
            byte[] r2 = r8.j()     // Catch:{ all -> 0x008f }
            r5.write(r2)     // Catch:{ all -> 0x008f }
            r5.flush()     // Catch:{ all -> 0x008f }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r3)     // Catch:{ all -> 0x008f }
            goto L_0x004b
        L_0x0046:
            r2 = r0
            r4 = r2
            goto L_0x0099
        L_0x0049:
            r3 = r0
            r5 = r3
        L_0x004b:
            int r2 = r1.getResponseCode()     // Catch:{ all -> 0x008f }
            r8.a((int) r2)     // Catch:{ all -> 0x008f }
            java.util.Map r6 = r1.getHeaderFields()     // Catch:{ all -> 0x008f }
            r8.a((java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r6)     // Catch:{ all -> 0x008f }
            r6 = 400(0x190, float:5.6E-43)
            if (r2 == r6) goto L_0x0062
            r6 = 500(0x1f4, float:7.0E-43)
            if (r2 == r6) goto L_0x0062
            goto L_0x0063
        L_0x0062:
            r4 = 0
        L_0x0063:
            if (r4 == 0) goto L_0x007e
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ all -> 0x008f }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ all -> 0x007c }
            r6 = 8000(0x1f40, float:1.121E-41)
            r4.<init>(r2, r6)     // Catch:{ all -> 0x007c }
            byte[] r0 = com.yandex.metrica.impl.r.b((java.io.InputStream) r4)     // Catch:{ all -> 0x0091 }
            r8.b((byte[]) r0)     // Catch:{ all -> 0x0091 }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r2)     // Catch:{ all -> 0x0091 }
            r0 = r4
            goto L_0x007f
        L_0x007c:
            r4 = r0
            goto L_0x0091
        L_0x007e:
            r2 = r0
        L_0x007f:
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r5)
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r0)
        L_0x0085:
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r3)
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r2)
            com.yandex.metrica.impl.bk.a((java.net.HttpURLConnection) r1)
            return
        L_0x008f:
            r2 = r0
            r4 = r2
        L_0x0091:
            r0 = r5
            goto L_0x0099
        L_0x0093:
            r2 = r0
            goto L_0x0097
        L_0x0095:
            r1 = r0
            r2 = r1
        L_0x0097:
            r3 = r2
            r4 = r3
        L_0x0099:
            r8.g()     // Catch:{ all -> 0x00a3 }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r0)
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r4)
            goto L_0x0085
        L_0x00a3:
            r8 = move-exception
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r0)
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r4)
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r3)
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r2)
            com.yandex.metrica.impl.bk.a((java.net.HttpURLConnection) r1)
            goto L_0x00b5
        L_0x00b4:
            throw r8
        L_0x00b5:
            goto L_0x00b4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.aj.d(com.yandex.metrica.impl.ak):void");
    }

    private static class b {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final ak f720a;
        private final String b;

        /* synthetic */ b(ak akVar, byte b2) {
            this(akVar);
        }

        private b(ak akVar) {
            this.f720a = akVar;
            this.b = akVar.a();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.b.equals(((b) obj).b);
        }

        public int hashCode() {
            return this.b.hashCode();
        }
    }

    private class a implements Runnable {
        private final ak b;

        /* synthetic */ a(aj ajVar, ak akVar, byte b2) {
            this(akVar);
        }

        private a(ak akVar) {
            this.b = akVar;
        }

        public void run() {
            try {
                aj.this.c(this.b);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
