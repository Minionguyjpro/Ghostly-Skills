package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TeeDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.ContentMetadata;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class CacheDataSource implements DataSource {
    private Uri actualUri;
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final CacheKeyFactory cacheKeyFactory;
    private final DataSource cacheReadDataSource;
    private final DataSource cacheWriteDataSource;
    private long checkCachePosition;
    private DataSource currentDataSource;
    private boolean currentDataSpecLengthUnset;
    private CacheSpan currentHoleSpan;
    private boolean currentRequestIgnoresCache;
    private final EventListener eventListener;
    private int flags;
    private byte[] httpBody;
    private int httpMethod;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    private String key;
    private long readPosition;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;
    private Uri uri;

    public interface EventListener {
        void onCacheIgnored(int i);

        void onCachedBytesRead(long j, long j2);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource) {
        this(cache2, dataSource, 0);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, int i) {
        this(cache2, dataSource, new FileDataSource(), new CacheDataSink(cache2, 5242880), i, (EventListener) null);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, EventListener eventListener2) {
        this(cache2, dataSource, dataSource2, dataSink, i, eventListener2, (CacheKeyFactory) null);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, EventListener eventListener2, CacheKeyFactory cacheKeyFactory2) {
        this.cache = cache2;
        this.cacheReadDataSource = dataSource2;
        this.cacheKeyFactory = cacheKeyFactory2 == null ? CacheUtil.DEFAULT_CACHE_KEY_FACTORY : cacheKeyFactory2;
        boolean z = false;
        this.blockOnCache = (i & 1) != 0;
        this.ignoreCacheOnError = (i & 2) != 0;
        this.ignoreCacheForUnsetLengthRequests = (i & 4) != 0 ? true : z;
        this.upstreamDataSource = dataSource;
        if (dataSink != null) {
            this.cacheWriteDataSource = new TeeDataSource(dataSource, dataSink);
        } else {
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener2;
    }

    public void addTransferListener(TransferListener transferListener) {
        this.cacheReadDataSource.addTransferListener(transferListener);
        this.upstreamDataSource.addTransferListener(transferListener);
    }

    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.key = this.cacheKeyFactory.buildCacheKey(dataSpec);
            Uri uri2 = dataSpec.uri;
            this.uri = uri2;
            this.actualUri = getRedirectedUriOrDefault(this.cache, this.key, uri2);
            this.httpMethod = dataSpec.httpMethod;
            this.httpBody = dataSpec.httpBody;
            this.flags = dataSpec.flags;
            this.readPosition = dataSpec.position;
            int shouldIgnoreCacheForRequest = shouldIgnoreCacheForRequest(dataSpec);
            boolean z = shouldIgnoreCacheForRequest != -1;
            this.currentRequestIgnoresCache = z;
            if (z) {
                notifyCacheIgnored(shouldIgnoreCacheForRequest);
            }
            if (dataSpec.length == -1) {
                if (!this.currentRequestIgnoresCache) {
                    long contentLength = ContentMetadata.CC.getContentLength(this.cache.getContentMetadata(this.key));
                    this.bytesRemaining = contentLength;
                    if (contentLength != -1) {
                        long j = contentLength - dataSpec.position;
                        this.bytesRemaining = j;
                        if (j <= 0) {
                            throw new DataSourceException(0);
                        }
                    }
                    openNextSource(false);
                    return this.bytesRemaining;
                }
            }
            this.bytesRemaining = dataSpec.length;
            openNextSource(false);
            return this.bytesRemaining;
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            if (this.readPosition >= this.checkCachePosition) {
                openNextSource(true);
            }
            int read = this.currentDataSource.read(bArr, i, i2);
            if (read != -1) {
                if (isReadingFromCache()) {
                    this.totalCachedBytesRead += (long) read;
                }
                long j = (long) read;
                this.readPosition += j;
                if (this.bytesRemaining != -1) {
                    this.bytesRemaining -= j;
                }
            } else if (this.currentDataSpecLengthUnset) {
                setNoBytesRemainingAndMaybeStoreLength();
            } else {
                if (this.bytesRemaining <= 0) {
                    if (this.bytesRemaining == -1) {
                    }
                }
                closeCurrentSource();
                openNextSource(false);
                return read(bArr, i, i2);
            }
            return read;
        } catch (IOException e) {
            if (!this.currentDataSpecLengthUnset || !CacheUtil.isCausedByPositionOutOfRange(e)) {
                handleBeforeThrow(e);
                throw e;
            }
            setNoBytesRemainingAndMaybeStoreLength();
            return -1;
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    public Uri getUri() {
        return this.actualUri;
    }

    public Map<String, List<String>> getResponseHeaders() {
        if (isReadingFromUpstream()) {
            return this.upstreamDataSource.getResponseHeaders();
        }
        return Collections.emptyMap();
    }

    public void close() throws IOException {
        this.uri = null;
        this.actualUri = null;
        this.httpMethod = 1;
        this.httpBody = null;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void openNextSource(boolean r21) throws java.io.IOException {
        /*
            r20 = this;
            r1 = r20
            boolean r0 = r1.currentRequestIgnoresCache
            r2 = 0
            if (r0 == 0) goto L_0x0009
            r0 = r2
            goto L_0x002f
        L_0x0009:
            boolean r0 = r1.blockOnCache
            if (r0 == 0) goto L_0x0025
            com.google.android.exoplayer2.upstream.cache.Cache r0 = r1.cache     // Catch:{ InterruptedException -> 0x0018 }
            java.lang.String r3 = r1.key     // Catch:{ InterruptedException -> 0x0018 }
            long r4 = r1.readPosition     // Catch:{ InterruptedException -> 0x0018 }
            com.google.android.exoplayer2.upstream.cache.CacheSpan r0 = r0.startReadWrite(r3, r4)     // Catch:{ InterruptedException -> 0x0018 }
            goto L_0x002f
        L_0x0018:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
            java.io.InterruptedIOException r0 = new java.io.InterruptedIOException
            r0.<init>()
            throw r0
        L_0x0025:
            com.google.android.exoplayer2.upstream.cache.Cache r0 = r1.cache
            java.lang.String r3 = r1.key
            long r4 = r1.readPosition
            com.google.android.exoplayer2.upstream.cache.CacheSpan r0 = r0.startReadWriteNonBlocking(r3, r4)
        L_0x002f:
            r3 = -1
            if (r0 != 0) goto L_0x0055
            com.google.android.exoplayer2.upstream.DataSource r5 = r1.upstreamDataSource
            com.google.android.exoplayer2.upstream.DataSpec r18 = new com.google.android.exoplayer2.upstream.DataSpec
            android.net.Uri r7 = r1.uri
            int r8 = r1.httpMethod
            byte[] r9 = r1.httpBody
            long r12 = r1.readPosition
            long r14 = r1.bytesRemaining
            java.lang.String r10 = r1.key
            int r11 = r1.flags
            r6 = r18
            r16 = r10
            r17 = r11
            r10 = r12
            r6.<init>(r7, r8, r9, r10, r12, r14, r16, r17)
        L_0x004f:
            r6 = r5
            r5 = r0
            r0 = r18
            goto L_0x00c3
        L_0x0055:
            boolean r5 = r0.isCached
            if (r5 == 0) goto L_0x0083
            java.io.File r5 = r0.file
            android.net.Uri r7 = android.net.Uri.fromFile(r5)
            long r5 = r1.readPosition
            long r8 = r0.position
            long r10 = r5 - r8
            long r5 = r0.length
            long r5 = r5 - r10
            long r8 = r1.bytesRemaining
            int r12 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r12 == 0) goto L_0x0072
            long r5 = java.lang.Math.min(r5, r8)
        L_0x0072:
            r12 = r5
            com.google.android.exoplayer2.upstream.DataSpec r18 = new com.google.android.exoplayer2.upstream.DataSpec
            long r8 = r1.readPosition
            java.lang.String r14 = r1.key
            int r15 = r1.flags
            r6 = r18
            r6.<init>((android.net.Uri) r7, (long) r8, (long) r10, (long) r12, (java.lang.String) r14, (int) r15)
            com.google.android.exoplayer2.upstream.DataSource r5 = r1.cacheReadDataSource
            goto L_0x004f
        L_0x0083:
            boolean r5 = r0.isOpenEnded()
            if (r5 == 0) goto L_0x008c
            long r5 = r1.bytesRemaining
            goto L_0x0098
        L_0x008c:
            long r5 = r0.length
            long r7 = r1.bytesRemaining
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 == 0) goto L_0x0098
            long r5 = java.lang.Math.min(r5, r7)
        L_0x0098:
            r15 = r5
            com.google.android.exoplayer2.upstream.DataSpec r5 = new com.google.android.exoplayer2.upstream.DataSpec
            android.net.Uri r8 = r1.uri
            int r9 = r1.httpMethod
            byte[] r10 = r1.httpBody
            long r13 = r1.readPosition
            java.lang.String r6 = r1.key
            int r11 = r1.flags
            r7 = r5
            r18 = r11
            r11 = r13
            r17 = r6
            r7.<init>(r8, r9, r10, r11, r13, r15, r17, r18)
            com.google.android.exoplayer2.upstream.DataSource r6 = r1.cacheWriteDataSource
            if (r6 == 0) goto L_0x00ba
            r19 = r5
            r5 = r0
            r0 = r19
            goto L_0x00c3
        L_0x00ba:
            com.google.android.exoplayer2.upstream.DataSource r6 = r1.upstreamDataSource
            com.google.android.exoplayer2.upstream.cache.Cache r7 = r1.cache
            r7.releaseHoleSpan(r0)
            r0 = r5
            r5 = r2
        L_0x00c3:
            boolean r7 = r1.currentRequestIgnoresCache
            if (r7 != 0) goto L_0x00d2
            com.google.android.exoplayer2.upstream.DataSource r7 = r1.upstreamDataSource
            if (r6 != r7) goto L_0x00d2
            long r7 = r1.readPosition
            r9 = 102400(0x19000, double:5.05923E-319)
            long r7 = r7 + r9
            goto L_0x00d7
        L_0x00d2:
            r7 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L_0x00d7:
            r1.checkCachePosition = r7
            if (r21 == 0) goto L_0x00f9
            boolean r7 = r20.isBypassingCache()
            com.google.android.exoplayer2.util.Assertions.checkState(r7)
            com.google.android.exoplayer2.upstream.DataSource r7 = r1.upstreamDataSource
            if (r6 != r7) goto L_0x00e7
            return
        L_0x00e7:
            r20.closeCurrentSource()     // Catch:{ all -> 0x00eb }
            goto L_0x00f9
        L_0x00eb:
            r0 = move-exception
            r2 = r0
            boolean r0 = r5.isHoleSpan()
            if (r0 == 0) goto L_0x00f8
            com.google.android.exoplayer2.upstream.cache.Cache r0 = r1.cache
            r0.releaseHoleSpan(r5)
        L_0x00f8:
            throw r2
        L_0x00f9:
            if (r5 == 0) goto L_0x0103
            boolean r7 = r5.isHoleSpan()
            if (r7 == 0) goto L_0x0103
            r1.currentHoleSpan = r5
        L_0x0103:
            r1.currentDataSource = r6
            long r7 = r0.length
            r5 = 1
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 != 0) goto L_0x010e
            r7 = 1
            goto L_0x010f
        L_0x010e:
            r7 = 0
        L_0x010f:
            r1.currentDataSpecLengthUnset = r7
            long r6 = r6.open(r0)
            com.google.android.exoplayer2.upstream.cache.ContentMetadataMutations r0 = new com.google.android.exoplayer2.upstream.cache.ContentMetadataMutations
            r0.<init>()
            boolean r8 = r1.currentDataSpecLengthUnset
            if (r8 == 0) goto L_0x012a
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 == 0) goto L_0x012a
            r1.bytesRemaining = r6
            long r3 = r1.readPosition
            long r3 = r3 + r6
            com.google.android.exoplayer2.upstream.cache.ContentMetadataMutations.setContentLength(r0, r3)
        L_0x012a:
            boolean r3 = r20.isReadingFromUpstream()
            if (r3 == 0) goto L_0x0146
            com.google.android.exoplayer2.upstream.DataSource r3 = r1.currentDataSource
            android.net.Uri r3 = r3.getUri()
            r1.actualUri = r3
            android.net.Uri r4 = r1.uri
            boolean r3 = r4.equals(r3)
            r3 = r3 ^ r5
            if (r3 == 0) goto L_0x0143
            android.net.Uri r2 = r1.actualUri
        L_0x0143:
            com.google.android.exoplayer2.upstream.cache.ContentMetadataMutations.setRedirectedUri(r0, r2)
        L_0x0146:
            boolean r2 = r20.isWritingToCache()
            if (r2 == 0) goto L_0x0153
            com.google.android.exoplayer2.upstream.cache.Cache r2 = r1.cache
            java.lang.String r3 = r1.key
            r2.applyContentMetadataMutations(r3, r0)
        L_0x0153:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CacheDataSource.openNextSource(boolean):void");
    }

    private void setNoBytesRemainingAndMaybeStoreLength() throws IOException {
        this.bytesRemaining = 0;
        if (isWritingToCache()) {
            ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
            ContentMetadataMutations.setContentLength(contentMetadataMutations, this.readPosition);
            this.cache.applyContentMetadataMutations(this.key, contentMetadataMutations);
        }
    }

    private static Uri getRedirectedUriOrDefault(Cache cache2, String str, Uri uri2) {
        Uri redirectedUri = ContentMetadata.CC.getRedirectedUri(cache2.getContentMetadata(str));
        return redirectedUri != null ? redirectedUri : uri2;
    }

    private boolean isReadingFromUpstream() {
        return !isReadingFromCache();
    }

    private boolean isBypassingCache() {
        return this.currentDataSource == this.upstreamDataSource;
    }

    private boolean isReadingFromCache() {
        return this.currentDataSource == this.cacheReadDataSource;
    }

    private boolean isWritingToCache() {
        return this.currentDataSource == this.cacheWriteDataSource;
    }

    private void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource != null) {
            try {
                dataSource.close();
            } finally {
                this.currentDataSource = null;
                this.currentDataSpecLengthUnset = false;
                CacheSpan cacheSpan = this.currentHoleSpan;
                if (cacheSpan != null) {
                    this.cache.releaseHoleSpan(cacheSpan);
                    this.currentHoleSpan = null;
                }
            }
        }
    }

    private void handleBeforeThrow(Throwable th) {
        if (isReadingFromCache() || (th instanceof Cache.CacheException)) {
            this.seenCacheError = true;
        }
    }

    private int shouldIgnoreCacheForRequest(DataSpec dataSpec) {
        if (!this.ignoreCacheOnError || !this.seenCacheError) {
            return (!this.ignoreCacheForUnsetLengthRequests || dataSpec.length != -1) ? -1 : 1;
        }
        return 0;
    }

    private void notifyCacheIgnored(int i) {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null) {
            eventListener2.onCacheIgnored(i);
        }
    }

    private void notifyBytesRead() {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null && this.totalCachedBytesRead > 0) {
            eventListener2.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
            this.totalCachedBytesRead = 0;
        }
    }
}
