package com.mopub.common;

import android.content.Context;
import android.os.AsyncTask;
import com.mopub.common.DiskLruCache;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Streams;
import com.mopub.common.util.Utils;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CacheService {
    private static final int APP_VERSION = 1;
    private static final int DISK_CACHE_INDEX = 0;
    static final String UNIQUE_CACHE_NAME = "mopub-cache";
    private static final int VALUE_COUNT = 1;
    private static DiskLruCache sDiskLruCache;

    public interface DiskLruCacheGetListener {
        void onComplete(String str, byte[] bArr);
    }

    public static boolean initializeDiskCache(Context context) {
        if (context == null) {
            return false;
        }
        if (sDiskLruCache == null) {
            File diskCacheDirectory = getDiskCacheDirectory(context);
            if (diskCacheDirectory == null) {
                return false;
            }
            try {
                sDiskLruCache = DiskLruCache.open(diskCacheDirectory, 1, 1, DeviceUtils.diskCacheSizeBytes(diskCacheDirectory));
            } catch (IOException e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unable to create DiskLruCache", e);
                return false;
            }
        }
        return true;
    }

    public static void initialize(Context context) {
        initializeDiskCache(context);
    }

    public static String createValidDiskCacheKey(String str) {
        return Utils.sha1(str);
    }

    public static File getDiskCacheDirectory(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        String path = cacheDir.getPath();
        return new File(path + File.separator + UNIQUE_CACHE_NAME);
    }

    public static boolean containsKeyDiskCache(String str) {
        DiskLruCache diskLruCache = sDiskLruCache;
        if (diskLruCache == null) {
            return false;
        }
        try {
            if (diskLruCache.get(createValidDiskCacheKey(str)) != null) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getFilePathDiskCache(String str) {
        if (sDiskLruCache == null) {
            return null;
        }
        return sDiskLruCache.getDirectory() + File.separator + createValidDiskCacheKey(str) + "." + 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.mopub.common.DiskLruCache$Snapshot} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] getFromDiskCache(java.lang.String r7) {
        /*
            com.mopub.common.DiskLruCache r0 = sDiskLruCache
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            r2 = 0
            java.lang.String r7 = createValidDiskCacheKey(r7)     // Catch:{ Exception -> 0x0045 }
            com.mopub.common.DiskLruCache$Snapshot r7 = r0.get(r7)     // Catch:{ Exception -> 0x0045 }
            if (r7 != 0) goto L_0x0017
            if (r7 == 0) goto L_0x0016
            r7.close()
        L_0x0016:
            return r1
        L_0x0017:
            java.io.InputStream r0 = r7.getInputStream(r2)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            if (r0 == 0) goto L_0x0035
            long r3 = r7.getLength(r2)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            int r4 = (int) r3     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            byte[] r1 = new byte[r4]     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r3.<init>(r0)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            com.mopub.common.util.Streams.readStream(r3, r1)     // Catch:{ all -> 0x0030 }
            com.mopub.common.util.Streams.closeStream(r3)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            goto L_0x0035
        L_0x0030:
            r0 = move-exception
            com.mopub.common.util.Streams.closeStream(r3)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            throw r0     // Catch:{ Exception -> 0x003e, all -> 0x003b }
        L_0x0035:
            if (r7 == 0) goto L_0x005c
            r7.close()
            goto L_0x005c
        L_0x003b:
            r0 = move-exception
            r1 = r7
            goto L_0x005d
        L_0x003e:
            r0 = move-exception
            r6 = r1
            r1 = r7
            r7 = r6
            goto L_0x0047
        L_0x0043:
            r0 = move-exception
            goto L_0x005d
        L_0x0045:
            r0 = move-exception
            r7 = r1
        L_0x0047:
            com.mopub.common.logging.MoPubLog$SdkLogEvent r3 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM     // Catch:{ all -> 0x0043 }
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0043 }
            java.lang.String r5 = "Unable to get from DiskLruCache"
            r4[r2] = r5     // Catch:{ all -> 0x0043 }
            r2 = 1
            r4[r2] = r0     // Catch:{ all -> 0x0043 }
            com.mopub.common.logging.MoPubLog.log(r3, r4)     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x005b
            r1.close()
        L_0x005b:
            r1 = r7
        L_0x005c:
            return r1
        L_0x005d:
            if (r1 == 0) goto L_0x0062
            r1.close()
        L_0x0062:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.CacheService.getFromDiskCache(java.lang.String):byte[]");
    }

    public static void getFromDiskCacheAsync(String str, DiskLruCacheGetListener diskLruCacheGetListener) {
        new DiskLruCacheGetTask(str, diskLruCacheGetListener).execute(new Void[0]);
    }

    public static boolean putToDiskCache(String str, byte[] bArr) {
        return putToDiskCache(str, (InputStream) new ByteArrayInputStream(bArr));
    }

    public static boolean putToDiskCache(String str, InputStream inputStream) {
        DiskLruCache diskLruCache = sDiskLruCache;
        if (diskLruCache == null) {
            return false;
        }
        DiskLruCache.Editor editor = null;
        try {
            DiskLruCache.Editor edit = diskLruCache.edit(createValidDiskCacheKey(str));
            if (edit == null) {
                return false;
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(edit.newOutputStream(0));
            Streams.copyContent(inputStream, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            sDiskLruCache.flush();
            edit.commit();
            return true;
        } catch (Exception e) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unable to put to DiskLruCache", e);
            if (editor != null) {
                try {
                    editor.abort();
                } catch (IOException unused) {
                }
            }
            return false;
        }
    }

    public static void putToDiskCacheAsync(String str, byte[] bArr) {
        new DiskLruCachePutTask(str, bArr).execute(new Void[0]);
    }

    private static class DiskLruCacheGetTask extends AsyncTask<Void, Void, byte[]> {
        private final DiskLruCacheGetListener mDiskLruCacheGetListener;
        private final String mKey;

        DiskLruCacheGetTask(String str, DiskLruCacheGetListener diskLruCacheGetListener) {
            this.mDiskLruCacheGetListener = diskLruCacheGetListener;
            this.mKey = str;
        }

        /* access modifiers changed from: protected */
        public byte[] doInBackground(Void... voidArr) {
            return CacheService.getFromDiskCache(this.mKey);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(byte[] bArr) {
            if (isCancelled()) {
                onCancelled();
                return;
            }
            DiskLruCacheGetListener diskLruCacheGetListener = this.mDiskLruCacheGetListener;
            if (diskLruCacheGetListener != null) {
                diskLruCacheGetListener.onComplete(this.mKey, bArr);
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled() {
            DiskLruCacheGetListener diskLruCacheGetListener = this.mDiskLruCacheGetListener;
            if (diskLruCacheGetListener != null) {
                diskLruCacheGetListener.onComplete(this.mKey, (byte[]) null);
            }
        }
    }

    private static class DiskLruCachePutTask extends AsyncTask<Void, Void, Void> {
        private final byte[] mContent;
        private final String mKey;

        DiskLruCachePutTask(String str, byte[] bArr) {
            this.mKey = str;
            this.mContent = bArr;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            CacheService.putToDiskCache(this.mKey, this.mContent);
            return null;
        }
    }

    @Deprecated
    public static void clearAndNullCaches() {
        DiskLruCache diskLruCache = sDiskLruCache;
        if (diskLruCache != null) {
            try {
                diskLruCache.delete();
                sDiskLruCache = null;
            } catch (IOException unused) {
                sDiskLruCache = null;
            }
        }
    }

    @Deprecated
    public static DiskLruCache getDiskLruCache() {
        return sDiskLruCache;
    }
}
