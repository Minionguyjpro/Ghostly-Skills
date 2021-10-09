package com.google.android.exoplayer2.upstream.cache;

import java.io.File;
import java.io.IOException;

public interface Cache {

    public interface Listener {
        void onSpanAdded(Cache cache, CacheSpan cacheSpan);

        void onSpanRemoved(Cache cache, CacheSpan cacheSpan);

        void onSpanTouched(Cache cache, CacheSpan cacheSpan, CacheSpan cacheSpan2);
    }

    void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) throws CacheException;

    void commitFile(File file, long j) throws CacheException;

    long getCacheSpace();

    ContentMetadata getContentMetadata(String str);

    void release();

    void releaseHoleSpan(CacheSpan cacheSpan);

    void removeSpan(CacheSpan cacheSpan) throws CacheException;

    File startFile(String str, long j, long j2) throws CacheException;

    CacheSpan startReadWrite(String str, long j) throws InterruptedException, CacheException;

    CacheSpan startReadWriteNonBlocking(String str, long j) throws CacheException;

    public static class CacheException extends IOException {
        public CacheException(String str) {
            super(str);
        }

        public CacheException(Throwable th) {
            super(th);
        }

        public CacheException(String str, Throwable th) {
            super(str, th);
        }
    }
}
