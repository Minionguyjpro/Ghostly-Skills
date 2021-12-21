package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class bw implements bv {

    /* renamed from: a  reason: collision with root package name */
    private final Context f809a;
    private final String b;
    private File c;
    private bo d;
    private FileLock e;
    private RandomAccessFile f;
    private FileChannel g;

    public bw(Context context, String str) {
        this.f809a = context;
        this.b = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x005b, code lost:
        return null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.database.sqlite.SQLiteDatabase a() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            android.content.Context r1 = r5.f809a     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.io.File r1 = r1.getFilesDir()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r2.<init>()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.lang.String r4 = r5.b     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.lang.String r3 = r3.getName()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r2.append(r3)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.lang.String r3 = ".lock"
            r2.append(r3)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r5.c = r0     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.io.File r1 = r5.c     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.lang.String r2 = "rw"
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r5.f = r0     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.nio.channels.FileChannel r0 = r0.getChannel()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r5.g = r0     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.nio.channels.FileLock r0 = r0.lock()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r5.e = r0     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            com.yandex.metrica.impl.ob.bo r0 = new com.yandex.metrica.impl.ob.bo     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            android.content.Context r1 = r5.f809a     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.lang.String r2 = r5.b     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            com.yandex.metrica.impl.ob.bs r3 = com.yandex.metrica.impl.ob.bm.c()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r0.<init>(r1, r2, r3)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r5.d = r0     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            monitor-exit(r5)
            return r0
        L_0x0056:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        L_0x0059:
            r0 = 0
            monitor-exit(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bw.a():android.database.sqlite.SQLiteDatabase");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.database.sqlite.SQLiteDatabase r1) {
        /*
            r0 = this;
            monitor-enter(r0)
            if (r1 == 0) goto L_0x0009
            r1.close()     // Catch:{ Exception -> 0x0009 }
            goto L_0x0009
        L_0x0007:
            r1 = move-exception
            goto L_0x002d
        L_0x0009:
            com.yandex.metrica.impl.ob.bo r1 = r0.d     // Catch:{ all -> 0x0007 }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r1)     // Catch:{ all -> 0x0007 }
            java.io.File r1 = r0.c     // Catch:{ all -> 0x0007 }
            r1.getAbsolutePath()     // Catch:{ all -> 0x0007 }
            java.nio.channels.FileLock r1 = r0.e     // Catch:{ all -> 0x0007 }
            com.yandex.metrica.impl.r.a((java.nio.channels.FileLock) r1)     // Catch:{ all -> 0x0007 }
            java.io.RandomAccessFile r1 = r0.f     // Catch:{ all -> 0x0007 }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r1)     // Catch:{ all -> 0x0007 }
            java.nio.channels.FileChannel r1 = r0.g     // Catch:{ all -> 0x0007 }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r1)     // Catch:{ all -> 0x0007 }
            r1 = 0
            r0.d = r1     // Catch:{ all -> 0x0007 }
            r0.f = r1     // Catch:{ all -> 0x0007 }
            r0.e = r1     // Catch:{ all -> 0x0007 }
            r0.g = r1     // Catch:{ all -> 0x0007 }
            monitor-exit(r0)
            return
        L_0x002d:
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bw.a(android.database.sqlite.SQLiteDatabase):void");
    }
}
