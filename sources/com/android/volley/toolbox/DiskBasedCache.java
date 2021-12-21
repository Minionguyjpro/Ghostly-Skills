package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.VolleyLog;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiskBasedCache implements Cache {
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;

    public DiskBasedCache(File file, int i) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0;
        this.mRootDirectory = file;
        this.mMaxCacheSizeInBytes = i;
    }

    public DiskBasedCache(File file) {
        this(file, 5242880);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0062 A[SYNTHETIC, Splitter:B:31:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0084 A[SYNTHETIC, Splitter:B:44:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x008f A[SYNTHETIC, Splitter:B:53:0x008f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.android.volley.Cache.Entry get(java.lang.String r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            java.util.Map<java.lang.String, com.android.volley.toolbox.DiskBasedCache$CacheHeader> r0 = r11.mEntries     // Catch:{ all -> 0x0096 }
            java.lang.Object r0 = r0.get(r12)     // Catch:{ all -> 0x0096 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r0 = (com.android.volley.toolbox.DiskBasedCache.CacheHeader) r0     // Catch:{ all -> 0x0096 }
            r1 = 0
            if (r0 != 0) goto L_0x000e
            monitor-exit(r11)
            return r1
        L_0x000e:
            java.io.File r2 = r11.getFileForKey(r12)     // Catch:{ all -> 0x0096 }
            r3 = 1
            r4 = 0
            r5 = 2
            com.android.volley.toolbox.DiskBasedCache$CountingInputStream r6 = new com.android.volley.toolbox.DiskBasedCache$CountingInputStream     // Catch:{ IOException -> 0x006a, NegativeArraySizeException -> 0x0048, all -> 0x0045 }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x006a, NegativeArraySizeException -> 0x0048, all -> 0x0045 }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ IOException -> 0x006a, NegativeArraySizeException -> 0x0048, all -> 0x0045 }
            r8.<init>(r2)     // Catch:{ IOException -> 0x006a, NegativeArraySizeException -> 0x0048, all -> 0x0045 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x006a, NegativeArraySizeException -> 0x0048, all -> 0x0045 }
            r6.<init>(r7)     // Catch:{ IOException -> 0x006a, NegativeArraySizeException -> 0x0048, all -> 0x0045 }
            com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r6)     // Catch:{ IOException -> 0x0043, NegativeArraySizeException -> 0x0041 }
            long r7 = r2.length()     // Catch:{ IOException -> 0x0043, NegativeArraySizeException -> 0x0041 }
            int r9 = r6.bytesRead     // Catch:{ IOException -> 0x0043, NegativeArraySizeException -> 0x0041 }
            long r9 = (long) r9     // Catch:{ IOException -> 0x0043, NegativeArraySizeException -> 0x0041 }
            long r7 = r7 - r9
            int r8 = (int) r7     // Catch:{ IOException -> 0x0043, NegativeArraySizeException -> 0x0041 }
            byte[] r7 = streamToBytes(r6, r8)     // Catch:{ IOException -> 0x0043, NegativeArraySizeException -> 0x0041 }
            com.android.volley.Cache$Entry r12 = r0.toCacheEntry(r7)     // Catch:{ IOException -> 0x0043, NegativeArraySizeException -> 0x0041 }
            r6.close()     // Catch:{ IOException -> 0x003f }
            monitor-exit(r11)
            return r12
        L_0x003f:
            monitor-exit(r11)
            return r1
        L_0x0041:
            r0 = move-exception
            goto L_0x004a
        L_0x0043:
            r0 = move-exception
            goto L_0x006c
        L_0x0045:
            r12 = move-exception
            r6 = r1
            goto L_0x008d
        L_0x0048:
            r0 = move-exception
            r6 = r1
        L_0x004a:
            java.lang.String r7 = "%s: %s"
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x008c }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ all -> 0x008c }
            r5[r4] = r2     // Catch:{ all -> 0x008c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008c }
            r5[r3] = r0     // Catch:{ all -> 0x008c }
            com.android.volley.VolleyLog.d(r7, r5)     // Catch:{ all -> 0x008c }
            r11.remove(r12)     // Catch:{ all -> 0x008c }
            if (r6 == 0) goto L_0x0068
            r6.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x0068
        L_0x0066:
            monitor-exit(r11)
            return r1
        L_0x0068:
            monitor-exit(r11)
            return r1
        L_0x006a:
            r0 = move-exception
            r6 = r1
        L_0x006c:
            java.lang.String r7 = "%s: %s"
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x008c }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ all -> 0x008c }
            r5[r4] = r2     // Catch:{ all -> 0x008c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008c }
            r5[r3] = r0     // Catch:{ all -> 0x008c }
            com.android.volley.VolleyLog.d(r7, r5)     // Catch:{ all -> 0x008c }
            r11.remove(r12)     // Catch:{ all -> 0x008c }
            if (r6 == 0) goto L_0x008a
            r6.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x008a
        L_0x0088:
            monitor-exit(r11)
            return r1
        L_0x008a:
            monitor-exit(r11)
            return r1
        L_0x008c:
            r12 = move-exception
        L_0x008d:
            if (r6 == 0) goto L_0x0095
            r6.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x0095
        L_0x0093:
            monitor-exit(r11)
            return r1
        L_0x0095:
            throw r12     // Catch:{ all -> 0x0096 }
        L_0x0096:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.get(java.lang.String):com.android.volley.Cache$Entry");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:25|26|(2:35|36)|37|38) */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0064 */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005b A[SYNTHETIC, Splitter:B:32:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0067 A[SYNTHETIC, Splitter:B:40:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x006a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initialize() {
        /*
            r8 = this;
            monitor-enter(r8)
            java.io.File r0 = r8.mRootDirectory     // Catch:{ all -> 0x006f }
            boolean r0 = r0.exists()     // Catch:{ all -> 0x006f }
            r1 = 0
            if (r0 != 0) goto L_0x0024
            java.io.File r0 = r8.mRootDirectory     // Catch:{ all -> 0x006f }
            boolean r0 = r0.mkdirs()     // Catch:{ all -> 0x006f }
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "Unable to create cache dir %s"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x006f }
            java.io.File r3 = r8.mRootDirectory     // Catch:{ all -> 0x006f }
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ all -> 0x006f }
            r2[r1] = r3     // Catch:{ all -> 0x006f }
            com.android.volley.VolleyLog.e(r0, r2)     // Catch:{ all -> 0x006f }
        L_0x0022:
            monitor-exit(r8)
            return
        L_0x0024:
            java.io.File r0 = r8.mRootDirectory     // Catch:{ all -> 0x006f }
            java.io.File[] r0 = r0.listFiles()     // Catch:{ all -> 0x006f }
            if (r0 != 0) goto L_0x002e
            monitor-exit(r8)
            return
        L_0x002e:
            int r2 = r0.length     // Catch:{ all -> 0x006f }
        L_0x002f:
            if (r1 >= r2) goto L_0x006d
            r3 = r0[r1]     // Catch:{ all -> 0x006f }
            r4 = 0
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0058 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0058 }
            r6.<init>(r3)     // Catch:{ IOException -> 0x0058 }
            r5.<init>(r6)     // Catch:{ IOException -> 0x0058 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r4 = com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r5)     // Catch:{ IOException -> 0x0054, all -> 0x0051 }
            long r6 = r3.length()     // Catch:{ IOException -> 0x0054, all -> 0x0051 }
            r4.size = r6     // Catch:{ IOException -> 0x0054, all -> 0x0051 }
            java.lang.String r6 = r4.key     // Catch:{ IOException -> 0x0054, all -> 0x0051 }
            r8.putEntry(r6, r4)     // Catch:{ IOException -> 0x0054, all -> 0x0051 }
            r5.close()     // Catch:{ IOException -> 0x006a }
            goto L_0x006a
        L_0x0051:
            r0 = move-exception
            r4 = r5
            goto L_0x005f
        L_0x0054:
            r4 = r5
            goto L_0x0059
        L_0x0056:
            r0 = move-exception
            goto L_0x005f
        L_0x0058:
        L_0x0059:
            if (r3 == 0) goto L_0x0065
            r3.delete()     // Catch:{ all -> 0x0056 }
            goto L_0x0065
        L_0x005f:
            if (r4 == 0) goto L_0x0064
            r4.close()     // Catch:{ IOException -> 0x0064 }
        L_0x0064:
            throw r0     // Catch:{ all -> 0x006f }
        L_0x0065:
            if (r4 == 0) goto L_0x006a
            r4.close()     // Catch:{ IOException -> 0x006a }
        L_0x006a:
            int r1 = r1 + 1
            goto L_0x002f
        L_0x006d:
            monitor-exit(r8)
            return
        L_0x006f:
            r0 = move-exception
            monitor-exit(r8)
            goto L_0x0073
        L_0x0072:
            throw r0
        L_0x0073:
            goto L_0x0072
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.initialize():void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|(1:16)|17|18) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        if (r0.delete() == false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004b, code lost:
        com.android.volley.VolleyLog.d("Could not clean up file %s", r0.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0045 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r7, com.android.volley.Cache.Entry r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            byte[] r0 = r8.data     // Catch:{ all -> 0x005a }
            int r0 = r0.length     // Catch:{ all -> 0x005a }
            r6.pruneIfNeeded(r0)     // Catch:{ all -> 0x005a }
            java.io.File r0 = r6.getFileForKey(r7)     // Catch:{ all -> 0x005a }
            r1 = 0
            r2 = 1
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0045 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0045 }
            r4.<init>(r0)     // Catch:{ IOException -> 0x0045 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0045 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r4 = new com.android.volley.toolbox.DiskBasedCache$CacheHeader     // Catch:{ IOException -> 0x0045 }
            r4.<init>(r7, r8)     // Catch:{ IOException -> 0x0045 }
            boolean r5 = r4.writeHeader(r3)     // Catch:{ IOException -> 0x0045 }
            if (r5 == 0) goto L_0x002f
            byte[] r8 = r8.data     // Catch:{ IOException -> 0x0045 }
            r3.write(r8)     // Catch:{ IOException -> 0x0045 }
            r3.close()     // Catch:{ IOException -> 0x0045 }
            r6.putEntry(r7, r4)     // Catch:{ IOException -> 0x0045 }
            monitor-exit(r6)
            return
        L_0x002f:
            r3.close()     // Catch:{ IOException -> 0x0045 }
            java.lang.String r7 = "Failed to write header for %s"
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0045 }
            java.lang.String r3 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x0045 }
            r8[r1] = r3     // Catch:{ IOException -> 0x0045 }
            com.android.volley.VolleyLog.d(r7, r8)     // Catch:{ IOException -> 0x0045 }
            java.io.IOException r7 = new java.io.IOException     // Catch:{ IOException -> 0x0045 }
            r7.<init>()     // Catch:{ IOException -> 0x0045 }
            throw r7     // Catch:{ IOException -> 0x0045 }
        L_0x0045:
            boolean r7 = r0.delete()     // Catch:{ all -> 0x005a }
            if (r7 != 0) goto L_0x0058
            java.lang.String r7 = "Could not clean up file %s"
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ all -> 0x005a }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x005a }
            r8[r1] = r0     // Catch:{ all -> 0x005a }
            com.android.volley.VolleyLog.d(r7, r8)     // Catch:{ all -> 0x005a }
        L_0x0058:
            monitor-exit(r6)
            return
        L_0x005a:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.put(java.lang.String, com.android.volley.Cache$Entry):void");
    }

    public synchronized void remove(String str) {
        boolean delete = getFileForKey(str).delete();
        removeEntry(str);
        if (!delete) {
            VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", str, getFilenameForKey(str));
        }
    }

    private String getFilenameForKey(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode());
        return valueOf + String.valueOf(str.substring(length).hashCode());
    }

    public File getFileForKey(String str) {
        return new File(this.mRootDirectory, getFilenameForKey(str));
    }

    private void pruneIfNeeded(int i) {
        long j;
        long j2 = (long) i;
        if (this.mTotalSize + j2 >= ((long) this.mMaxCacheSizeInBytes)) {
            if (VolleyLog.DEBUG) {
                VolleyLog.v("Pruning old cache entries.", new Object[0]);
            }
            long j3 = this.mTotalSize;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, CacheHeader>> it = this.mEntries.entrySet().iterator();
            int i2 = 0;
            while (it.hasNext()) {
                CacheHeader cacheHeader = (CacheHeader) it.next().getValue();
                if (getFileForKey(cacheHeader.key).delete()) {
                    j = j2;
                    this.mTotalSize -= cacheHeader.size;
                } else {
                    j = j2;
                    VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", cacheHeader.key, getFilenameForKey(cacheHeader.key));
                }
                it.remove();
                i2++;
                if (((float) (this.mTotalSize + j)) < ((float) this.mMaxCacheSizeInBytes) * 0.9f) {
                    break;
                }
                j2 = j;
            }
            if (VolleyLog.DEBUG) {
                VolleyLog.v("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.mTotalSize - j3), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    private void putEntry(String str, CacheHeader cacheHeader) {
        if (!this.mEntries.containsKey(str)) {
            this.mTotalSize += cacheHeader.size;
        } else {
            this.mTotalSize += cacheHeader.size - this.mEntries.get(str).size;
        }
        this.mEntries.put(str, cacheHeader);
    }

    private void removeEntry(String str) {
        CacheHeader cacheHeader = this.mEntries.get(str);
        if (cacheHeader != null) {
            this.mTotalSize -= cacheHeader.size;
            this.mEntries.remove(str);
        }
    }

    private static byte[] streamToBytes(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Expected " + i + " bytes, read " + i2 + " bytes");
    }

    static class CacheHeader {
        public String etag;
        public String key;
        public long lastModified;
        public Map<String, String> responseHeaders;
        public long serverDate;
        public long size;
        public long softTtl;
        public long ttl;

        private CacheHeader() {
        }

        public CacheHeader(String str, Cache.Entry entry) {
            this.key = str;
            this.size = (long) entry.data.length;
            this.etag = entry.etag;
            this.serverDate = entry.serverDate;
            this.lastModified = entry.lastModified;
            this.ttl = entry.ttl;
            this.softTtl = entry.softTtl;
            this.responseHeaders = entry.responseHeaders;
        }

        public static CacheHeader readHeader(InputStream inputStream) throws IOException {
            CacheHeader cacheHeader = new CacheHeader();
            if (DiskBasedCache.readInt(inputStream) == 538247942) {
                cacheHeader.key = DiskBasedCache.readString(inputStream);
                String readString = DiskBasedCache.readString(inputStream);
                cacheHeader.etag = readString;
                if (readString.equals("")) {
                    cacheHeader.etag = null;
                }
                cacheHeader.serverDate = DiskBasedCache.readLong(inputStream);
                cacheHeader.lastModified = DiskBasedCache.readLong(inputStream);
                cacheHeader.ttl = DiskBasedCache.readLong(inputStream);
                cacheHeader.softTtl = DiskBasedCache.readLong(inputStream);
                cacheHeader.responseHeaders = DiskBasedCache.readStringStringMap(inputStream);
                return cacheHeader;
            }
            throw new IOException();
        }

        public Cache.Entry toCacheEntry(byte[] bArr) {
            Cache.Entry entry = new Cache.Entry();
            entry.data = bArr;
            entry.etag = this.etag;
            entry.serverDate = this.serverDate;
            entry.lastModified = this.lastModified;
            entry.ttl = this.ttl;
            entry.softTtl = this.softTtl;
            entry.responseHeaders = this.responseHeaders;
            return entry;
        }

        public boolean writeHeader(OutputStream outputStream) {
            try {
                DiskBasedCache.writeInt(outputStream, 538247942);
                DiskBasedCache.writeString(outputStream, this.key);
                DiskBasedCache.writeString(outputStream, this.etag == null ? "" : this.etag);
                DiskBasedCache.writeLong(outputStream, this.serverDate);
                DiskBasedCache.writeLong(outputStream, this.lastModified);
                DiskBasedCache.writeLong(outputStream, this.ttl);
                DiskBasedCache.writeLong(outputStream, this.softTtl);
                DiskBasedCache.writeStringStringMap(this.responseHeaders, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                VolleyLog.d("%s", e.toString());
                return false;
            }
        }
    }

    private static class CountingInputStream extends FilterInputStream {
        /* access modifiers changed from: private */
        public int bytesRead;

        private CountingInputStream(InputStream inputStream) {
            super(inputStream);
            this.bytesRead = 0;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.bytesRead++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.bytesRead += read;
            }
            return read;
        }
    }

    private static int read(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void writeInt(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static int readInt(InputStream inputStream) throws IOException {
        return (read(inputStream) << 24) | (read(inputStream) << 0) | 0 | (read(inputStream) << 8) | (read(inputStream) << 16);
    }

    static void writeLong(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) (j >>> 0)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static long readLong(InputStream inputStream) throws IOException {
        return ((((long) read(inputStream)) & 255) << 0) | 0 | ((((long) read(inputStream)) & 255) << 8) | ((((long) read(inputStream)) & 255) << 16) | ((((long) read(inputStream)) & 255) << 24) | ((((long) read(inputStream)) & 255) << 32) | ((((long) read(inputStream)) & 255) << 40) | ((((long) read(inputStream)) & 255) << 48) | ((255 & ((long) read(inputStream))) << 56);
    }

    static void writeString(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        writeLong(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String readString(InputStream inputStream) throws IOException {
        return new String(streamToBytes(inputStream, (int) readLong(inputStream)), "UTF-8");
    }

    static void writeStringStringMap(Map<String, String> map, OutputStream outputStream) throws IOException {
        if (map != null) {
            writeInt(outputStream, map.size());
            for (Map.Entry next : map.entrySet()) {
                writeString(outputStream, (String) next.getKey());
                writeString(outputStream, (String) next.getValue());
            }
            return;
        }
        writeInt(outputStream, 0);
    }

    static Map<String, String> readStringStringMap(InputStream inputStream) throws IOException {
        int readInt = readInt(inputStream);
        Map<String, String> emptyMap = readInt == 0 ? Collections.emptyMap() : new HashMap<>(readInt);
        for (int i = 0; i < readInt; i++) {
            emptyMap.put(readString(inputStream).intern(), readString(inputStream).intern());
        }
        return emptyMap;
    }
}
