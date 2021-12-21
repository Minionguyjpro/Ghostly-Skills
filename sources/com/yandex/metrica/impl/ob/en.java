package com.yandex.metrica.impl.ob;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class en implements ev {

    /* renamed from: a  reason: collision with root package name */
    private static final String f883a = en.class.getSimpleName();
    private final es b = new es();
    private File c;

    public en(String str, String str2) throws IOException {
        b(str, str2);
    }

    public synchronized Set<String> a(String str) {
        return this.b.a(str);
    }

    public synchronized void a(String str, String[] strArr) {
        if (this.b.a(str) == null) {
            long lastModified = this.c.lastModified();
            a(str, (Set<String>) new HashSet(Arrays.asList(strArr)));
            this.c.setLastModified(lastModified);
        }
    }

    public synchronized boolean a(String str, String str2) {
        boolean a2;
        a2 = this.b.a(str, str2);
        d();
        return a2;
    }

    public synchronized void a(String str, Set<String> set) {
        this.b.a(str, set);
        d();
    }

    public synchronized long a() {
        return this.c.lastModified();
    }

    public void b() {
        this.c.setLastModified(System.currentTimeMillis());
    }

    private synchronized void b(String str, String str2) throws IOException {
        Map map;
        File file = new File(str, "sslpinningv1-" + str2);
        this.c = file;
        if (file.createNewFile()) {
            map = new HashMap();
            a((Map<String, Set<String>>) map);
            this.c.setLastModified(0);
        } else {
            map = c();
        }
        this.b.a((Map<String, Set<String>>) map);
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5, types: [java.util.Set] */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059 A[SYNTHETIC, Splitter:B:27:0x0059] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.Map<java.lang.String, java.util.Set<java.lang.String>> c() throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x0068 }
            r0.<init>()     // Catch:{ all -> 0x0068 }
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0056 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0056 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x0056 }
            java.io.File r5 = r6.c     // Catch:{ all -> 0x0056 }
            r4.<init>(r5)     // Catch:{ all -> 0x0056 }
            r3.<init>(r4)     // Catch:{ all -> 0x0056 }
            r2.<init>(r3)     // Catch:{ all -> 0x0056 }
            java.lang.String r3 = r2.readLine()     // Catch:{ all -> 0x0053 }
        L_0x001c:
            if (r3 == 0) goto L_0x0043
            java.lang.String r4 = "type-"
            boolean r4 = r3.contains(r4)     // Catch:{ all -> 0x0053 }
            if (r4 == 0) goto L_0x0035
            r1 = 5
            java.lang.String r1 = r3.substring(r1)     // Catch:{ all -> 0x0053 }
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x0053 }
            r3.<init>()     // Catch:{ all -> 0x0053 }
            r0.put(r1, r3)     // Catch:{ all -> 0x0053 }
            r1 = r3
            goto L_0x003e
        L_0x0035:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0053 }
            if (r4 != 0) goto L_0x003e
            r1.add(r3)     // Catch:{ all -> 0x0053 }
        L_0x003e:
            java.lang.String r3 = r2.readLine()     // Catch:{ all -> 0x0053 }
            goto L_0x001c
        L_0x0043:
            r2.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x0051
        L_0x0047:
            r1 = move-exception
            java.lang.String r2 = f883a     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0068 }
            android.util.Log.e(r2, r1)     // Catch:{ all -> 0x0068 }
        L_0x0051:
            monitor-exit(r6)
            return r0
        L_0x0053:
            r0 = move-exception
            r1 = r2
            goto L_0x0057
        L_0x0056:
            r0 = move-exception
        L_0x0057:
            if (r1 == 0) goto L_0x0067
            r1.close()     // Catch:{ IOException -> 0x005d }
            goto L_0x0067
        L_0x005d:
            r1 = move-exception
            java.lang.String r2 = f883a     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0068 }
            android.util.Log.e(r2, r1)     // Catch:{ all -> 0x0068 }
        L_0x0067:
            throw r0     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r0 = move-exception
            monitor-exit(r6)
            goto L_0x006c
        L_0x006b:
            throw r0
        L_0x006c:
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.en.c():java.util.Map");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007d A[SYNTHETIC, Splitter:B:30:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008e A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0092 A[SYNTHETIC, Splitter:B:42:0x0092] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(java.util.Map<java.lang.String, java.util.Set<java.lang.String>> r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x0071 }
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x0071 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0071 }
            java.io.File r4 = r5.c     // Catch:{ IOException -> 0x0071 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0071 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0071 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0071 }
            java.util.Set r0 = r6.keySet()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
        L_0x001b:
            boolean r2 = r0.hasNext()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            if (r2 == 0) goto L_0x0058
            java.lang.Object r2 = r0.next()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r4 = "type-"
            r3.<init>(r4)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r3.append(r2)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r1.write(r3)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r1.newLine()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.Object r2 = r6.get(r2)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.util.Set r2 = (java.util.Set) r2     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
        L_0x0045:
            boolean r3 = r2.hasNext()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            if (r3 == 0) goto L_0x001b
            java.lang.Object r3 = r2.next()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r1.write(r3)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r1.newLine()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            goto L_0x0045
        L_0x0058:
            r1.close()     // Catch:{ IOException -> 0x005d }
            monitor-exit(r5)
            return
        L_0x005d:
            r6 = move-exception
            java.lang.String r0 = f883a     // Catch:{ all -> 0x00a1 }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x00a1 }
            android.util.Log.e(r0, r6)     // Catch:{ all -> 0x00a1 }
            monitor-exit(r5)
            return
        L_0x0069:
            r6 = move-exception
            r0 = r1
            goto L_0x0090
        L_0x006c:
            r6 = move-exception
            r0 = r1
            goto L_0x0072
        L_0x006f:
            r6 = move-exception
            goto L_0x0090
        L_0x0071:
            r6 = move-exception
        L_0x0072:
            java.lang.String r1 = f883a     // Catch:{ all -> 0x006f }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x006f }
            android.util.Log.e(r1, r6)     // Catch:{ all -> 0x006f }
            if (r0 == 0) goto L_0x008e
            r0.close()     // Catch:{ IOException -> 0x0082 }
            monitor-exit(r5)
            return
        L_0x0082:
            r6 = move-exception
            java.lang.String r0 = f883a     // Catch:{ all -> 0x00a1 }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x00a1 }
            android.util.Log.e(r0, r6)     // Catch:{ all -> 0x00a1 }
            monitor-exit(r5)
            return
        L_0x008e:
            monitor-exit(r5)
            return
        L_0x0090:
            if (r0 == 0) goto L_0x00a0
            r0.close()     // Catch:{ IOException -> 0x0096 }
            goto L_0x00a0
        L_0x0096:
            r0 = move-exception
            java.lang.String r1 = f883a     // Catch:{ all -> 0x00a1 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00a1 }
            android.util.Log.e(r1, r0)     // Catch:{ all -> 0x00a1 }
        L_0x00a0:
            throw r6     // Catch:{ all -> 0x00a1 }
        L_0x00a1:
            r6 = move-exception
            monitor-exit(r5)
            goto L_0x00a5
        L_0x00a4:
            throw r6
        L_0x00a5:
            goto L_0x00a4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.en.a(java.util.Map):void");
    }

    private synchronized void d() {
        a(this.b.c());
    }
}
