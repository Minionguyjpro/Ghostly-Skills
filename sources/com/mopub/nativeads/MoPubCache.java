package com.mopub.nativeads;

import android.content.Context;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.mopub.common.Preconditions;
import com.mopub.common.util.DeviceUtils;
import java.io.File;

class MoPubCache {
    private static final String NATIVE_CACHE_NAME = "mopub-native-cache";
    private static volatile Cache sInstance;

    MoPubCache() {
    }

    static Cache getCacheInstance(Context context) {
        File cacheDir;
        Preconditions.checkNotNull(context);
        Cache cache = sInstance;
        if (cache == null) {
            synchronized (MoPubCache.class) {
                cache = sInstance;
                if (cache == null && (cacheDir = context.getApplicationContext().getCacheDir()) != null) {
                    SimpleCache simpleCache = new SimpleCache(new File(cacheDir.getPath() + File.separator + NATIVE_CACHE_NAME), new LeastRecentlyUsedCacheEvictor(DeviceUtils.diskCacheSizeBytes(cacheDir)));
                    sInstance = simpleCache;
                    cache = simpleCache;
                }
            }
        }
        return cache;
    }

    static void resetInstance() {
        if (sInstance != null) {
            sInstance.release();
            sInstance = null;
        }
    }
}
