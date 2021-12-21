package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ew implements fk {

    /* renamed from: a  reason: collision with root package name */
    private final a f890a;

    public ew(Context context, fd fdVar) {
        this(new fe(context), fdVar);
    }

    ew(fe feVar, fd fdVar) {
        if (fdVar.d() != null) {
            this.f890a = new a(feVar, fdVar);
            return;
        }
        throw new IllegalArgumentException("UUID provider must be set");
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new UnsupportedOperationException();
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr == null || x509CertificateArr.length == 0 || str == null || str.length() == 0) {
            throw new IllegalArgumentException("null or zero-length parameter");
        } else if (!b(a(x509CertificateArr))) {
            throw new CertificateException("Can't trust certificate chain");
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.f890a.d().a();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0020 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0026 A[SYNTHETIC, Splitter:B:12:0x0026] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean c(java.security.cert.X509Certificate[] r4) {
        /*
            r3 = this;
            com.yandex.metrica.impl.ob.ew$a r0 = r3.f890a
            com.yandex.metrica.impl.ob.eq r0 = r0.a()
            com.yandex.metrica.impl.ob.fh r0 = r0.d()
            if (r0 == 0) goto L_0x0036
            java.util.concurrent.locks.ReentrantLock r1 = r0.a()
            r1.lock()
            boolean r2 = r3.d(r4)     // Catch:{ CertificateException -> 0x0020 }
            if (r2 == 0) goto L_0x0020
            r1.unlock()
            r4 = 1
            return r4
        L_0x001e:
            r4 = move-exception
            goto L_0x0032
        L_0x0020:
            boolean r0 = r0.b()     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x002e
            boolean r4 = r3.d(r4)     // Catch:{ CertificateException -> 0x002e }
            r1.unlock()
            return r4
        L_0x002e:
            r1.unlock()
            goto L_0x0036
        L_0x0032:
            r1.unlock()
            throw r4
        L_0x0036:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.ew.c(java.security.cert.X509Certificate[]):boolean");
    }

    private boolean e(X509Certificate[] x509CertificateArr) throws CertificateException {
        for (er c : this.f890a.e()) {
            if (c.c(x509CertificateArr)) {
                return true;
            }
        }
        eu unused = this.f890a.c();
        throw new el(new ff(x509CertificateArr));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.security.cert.X509Certificate[]} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.cert.X509Certificate[] a(java.security.cert.X509Certificate[] r9) {
        /*
            r8 = this;
            r0 = 0
            r2 = r9
            r1 = 0
        L_0x0003:
            int r3 = r2.length
            if (r1 >= r3) goto L_0x0045
            int r3 = r1 + 1
            r4 = r3
        L_0x0009:
            int r5 = r2.length
            r6 = 1
            if (r4 >= r5) goto L_0x0036
            r5 = r2[r1]
            java.security.Principal r5 = r5.getIssuerDN()
            r7 = r2[r4]
            java.security.Principal r7 = r7.getSubjectDN()
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0033
            if (r4 == r3) goto L_0x0037
            if (r2 != r9) goto L_0x002a
            java.lang.Object r1 = r9.clone()
            r2 = r1
            java.security.cert.X509Certificate[] r2 = (java.security.cert.X509Certificate[]) r2
        L_0x002a:
            r1 = r2[r4]
            r5 = r2[r3]
            r2[r4] = r5
            r2[r3] = r1
            goto L_0x0037
        L_0x0033:
            int r4 = r4 + 1
            goto L_0x0009
        L_0x0036:
            r6 = 0
        L_0x0037:
            if (r6 != 0) goto L_0x0043
            int r9 = r2.length
            if (r3 == r9) goto L_0x0045
            java.security.cert.X509Certificate[] r9 = new java.security.cert.X509Certificate[r3]
            java.lang.System.arraycopy(r2, r0, r9, r0, r3)
            r2 = r9
            goto L_0x0045
        L_0x0043:
            r1 = r3
            goto L_0x0003
        L_0x0045:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.ew.a(java.security.cert.X509Certificate[]):java.security.cert.X509Certificate[]");
    }

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private final fe f891a;
        private final fd b;
        private volatile er[] c;
        private volatile fc d;
        private volatile eu e;
        private volatile ej f;
        private volatile eq g;

        public a(fe feVar, fd fdVar) {
            this.f891a = feVar;
            this.b = fdVar;
        }

        /* access modifiers changed from: private */
        public eq a() {
            if (this.g == null) {
                synchronized (this) {
                    if (this.g == null) {
                        this.g = new eq(this.f891a, this.b);
                    }
                }
            }
            return this.g;
        }

        private ej b() {
            if (this.f == null) {
                synchronized (this) {
                    if (this.f == null) {
                        this.f = new ej();
                    }
                }
            }
            return this.f;
        }

        /* access modifiers changed from: private */
        public eu c() {
            if (this.e == null) {
                synchronized (this) {
                    if (this.e == null) {
                        this.e = new eu(b().b());
                    }
                }
            }
            return this.e;
        }

        /* access modifiers changed from: private */
        public fc d() {
            if (this.d == null) {
                synchronized (this) {
                    if (this.d == null) {
                        try {
                            this.d = new fc();
                        } catch (GeneralSecurityException e2) {
                            throw new IllegalStateException("Can't get system trust manager", e2);
                        }
                    }
                }
            }
            return this.d;
        }

        /* access modifiers changed from: private */
        public er[] e() {
            if (this.c == null) {
                synchronized (this) {
                    if (this.c == null) {
                        ep epVar = new ep(a());
                        this.c = new er[]{new ei(b()), epVar};
                    }
                }
            }
            return this.c;
        }
    }

    private boolean b(X509Certificate[] x509CertificateArr) throws CertificateException {
        try {
            if (this.f890a.d().a(x509CertificateArr)) {
                boolean d = d(x509CertificateArr);
                this.f890a.a().e();
                return d;
            }
            throw new CertificateException("System doesn't trust certificate chain");
        } catch (el unused) {
            boolean c = c(x509CertificateArr);
            if (c) {
                return c;
            }
            this.f890a.c().a(x509CertificateArr);
            return d(x509CertificateArr);
        }
    }

    private boolean d(X509Certificate[] x509CertificateArr) throws CertificateException {
        boolean z;
        er[] d = this.f890a.e();
        int length = d.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (d[i].b(x509CertificateArr)) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            er[] d2 = this.f890a.e();
            int length2 = d2.length;
            int i2 = 0;
            while (i2 < length2) {
                if (!d2[i2].a(x509CertificateArr)) {
                    i2++;
                } else {
                    throw new CertificateException("There is blacklisted certificate in chain");
                }
            }
            return e(x509CertificateArr);
        }
    }
}
