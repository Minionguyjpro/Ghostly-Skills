package com.yandex.metrica.impl.ob;

import java.io.IOException;

class fa {

    /* renamed from: a  reason: collision with root package name */
    private static fh f895a;
    private static ey b;
    private static fs c;

    static synchronized fh a(fe feVar) {
        fh fhVar;
        synchronized (fa.class) {
            if (f895a == null) {
                f895a = new fh(feVar, b(feVar), c(feVar), new fd());
            }
            fhVar = f895a;
        }
        return fhVar;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:5|6|7|8) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized com.yandex.metrica.impl.ob.ey b(com.yandex.metrica.impl.ob.fe r3) {
        /*
            java.lang.Class<com.yandex.metrica.impl.ob.fa> r0 = com.yandex.metrica.impl.ob.fa.class
            monitor-enter(r0)
            com.yandex.metrica.impl.ob.ey r1 = b     // Catch:{ all -> 0x001b }
            if (r1 != 0) goto L_0x0017
            com.yandex.metrica.impl.ob.fa$a r1 = new com.yandex.metrica.impl.ob.fa$a     // Catch:{ IOException -> 0x0010 }
            r2 = 0
            r1.<init>(r3, r2)     // Catch:{ IOException -> 0x0010 }
            b = r1     // Catch:{ IOException -> 0x0010 }
            goto L_0x0017
        L_0x0010:
            com.yandex.metrica.impl.ob.et r3 = new com.yandex.metrica.impl.ob.et     // Catch:{ all -> 0x001b }
            r3.<init>()     // Catch:{ all -> 0x001b }
            b = r3     // Catch:{ all -> 0x001b }
        L_0x0017:
            com.yandex.metrica.impl.ob.ey r3 = b     // Catch:{ all -> 0x001b }
            monitor-exit(r0)
            return r3
        L_0x001b:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.fa.b(com.yandex.metrica.impl.ob.fe):com.yandex.metrica.impl.ob.ey");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:2|3|(2:5|6)|7|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized com.yandex.metrica.impl.ob.fs c(com.yandex.metrica.impl.ob.fe r2) {
        /*
            java.lang.Class<com.yandex.metrica.impl.ob.fa> r0 = com.yandex.metrica.impl.ob.fa.class
            monitor-enter(r0)
            com.yandex.metrica.impl.ob.fs r1 = c     // Catch:{ all -> 0x0011 }
            if (r1 != 0) goto L_0x000d
            com.yandex.metrica.impl.ob.fs r2 = r2.d()     // Catch:{ IOException | GeneralSecurityException -> 0x000d }
            c = r2     // Catch:{ IOException | GeneralSecurityException -> 0x000d }
        L_0x000d:
            com.yandex.metrica.impl.ob.fs r2 = c     // Catch:{ all -> 0x0011 }
            monitor-exit(r0)
            return r2
        L_0x0011:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.fa.c(com.yandex.metrica.impl.ob.fe):com.yandex.metrica.impl.ob.fs");
    }

    private static class a implements ey {

        /* renamed from: a  reason: collision with root package name */
        private static final String[] f896a = {"LNFe+yc4/NZbJVynpxAeAd+brU3EPwGbtwF6VeUjI/Y=", "PL1/TTDEe9Cm2lb2X0tixyQC7zaPREm/V0IHJscTCmw=", "+B0DgmKB5hWEuHib00m2jvCJWBlOYI0NGTMmVjaVrJA=", "dy/Myn0WRtYGKBNP8ubn9boJWJi+WWmLzp0V+W9pqfM=", "OB84k4abNNzWpMVBdhI+TSgQmCqTKdPPQrwq6j4YdMU=", "AZQG1XXPKFo8LYu/gTPgz65IOcmcwYFb3yREhyWefNI=", "iZEDYF5LpvyxpOX9+x3+qDBXhdByZOUFatBA3JgW7sY=", "IQBnNBEiFuhj+8x6X8XLgh01V9Ic5/V3IRQLNFFc7v4=", "LvRiGEjRqfzurezaWuj8Wie2gyHMrW5Q06LspMnox7A="};
        private final fb b;
        private final fb c;

        /* synthetic */ a(fe feVar, byte b2) throws IOException {
            this(feVar);
        }

        private a(fe feVar) throws IOException {
            en enVar = new en(feVar.b(), "lib");
            this.b = new fb(enVar, "LIB-BLACK");
            this.c = new fb(enVar, "LIB-TRUST", f896a);
        }

        public fb a() {
            return this.b;
        }

        public fb b() {
            throw new UnsupportedOperationException("white list isn't supported in shared container");
        }

        public fb c() {
            return this.c;
        }
    }
}
