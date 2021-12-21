package com.google.firebase.installations;

import android.util.Log;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

class CrossProcessLock {
    private final FileChannel channel;
    private final FileLock lock;

    private CrossProcessLock(FileChannel fileChannel, FileLock fileLock) {
        this.channel = fileChannel;
        this.lock = fileLock;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036 A[SYNTHETIC, Splitter:B:15:0x0036] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d A[SYNTHETIC, Splitter:B:19:0x003d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.firebase.installations.CrossProcessLock acquire(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x002a, Error -> 0x0028 }
            java.io.File r4 = r4.getFilesDir()     // Catch:{ IOException -> 0x002a, Error -> 0x0028 }
            r1.<init>(r4, r5)     // Catch:{ IOException -> 0x002a, Error -> 0x0028 }
            java.io.RandomAccessFile r4 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x002a, Error -> 0x0028 }
            java.lang.String r5 = "rw"
            r4.<init>(r1, r5)     // Catch:{ IOException -> 0x002a, Error -> 0x0028 }
            java.nio.channels.FileChannel r4 = r4.getChannel()     // Catch:{ IOException -> 0x002a, Error -> 0x0028 }
            java.nio.channels.FileLock r5 = r4.lock()     // Catch:{ IOException -> 0x0025, Error -> 0x0023 }
            com.google.firebase.installations.CrossProcessLock r1 = new com.google.firebase.installations.CrossProcessLock     // Catch:{ IOException -> 0x0021, Error -> 0x001f }
            r1.<init>(r4, r5)     // Catch:{ IOException -> 0x0021, Error -> 0x001f }
            return r1
        L_0x001f:
            r1 = move-exception
            goto L_0x002d
        L_0x0021:
            r1 = move-exception
            goto L_0x002d
        L_0x0023:
            r1 = move-exception
            goto L_0x0026
        L_0x0025:
            r1 = move-exception
        L_0x0026:
            r5 = r0
            goto L_0x002d
        L_0x0028:
            r1 = move-exception
            goto L_0x002b
        L_0x002a:
            r1 = move-exception
        L_0x002b:
            r4 = r0
            r5 = r4
        L_0x002d:
            java.lang.String r2 = "CrossProcessLock"
            java.lang.String r3 = "encountered error while creating and acquiring the lock, ignoring"
            android.util.Log.e(r2, r3, r1)
            if (r5 == 0) goto L_0x003b
            r5.release()     // Catch:{ IOException -> 0x003a }
            goto L_0x003b
        L_0x003a:
        L_0x003b:
            if (r4 == 0) goto L_0x0040
            r4.close()     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.CrossProcessLock.acquire(android.content.Context, java.lang.String):com.google.firebase.installations.CrossProcessLock");
    }

    /* access modifiers changed from: package-private */
    public void releaseAndClose() {
        try {
            this.lock.release();
            this.channel.close();
        } catch (IOException e) {
            Log.e("CrossProcessLock", "encountered error while releasing, ignoring", e);
        }
    }
}
