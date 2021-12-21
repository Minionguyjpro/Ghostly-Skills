package com.mopub.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebSettings;
import androidx.collection.LruCache;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.volley.toolbox.BasicNetwork;
import com.mopub.volley.toolbox.DiskBasedCache;
import com.mopub.volley.toolbox.HurlStack;
import com.mopub.volley.toolbox.ImageLoader;
import java.io.File;

public class Networking {
    static final String CACHE_DIRECTORY_NAME = "mopub-volley-cache";
    private static final String DEFAULT_USER_AGENT;
    private static volatile MaxWidthImageLoader sMaxWidthImageLoader;
    private static volatile MoPubRequestQueue sRequestQueue;
    private static HurlStack.UrlRewriter sUrlRewriter;
    private static boolean sUseHttps = false;
    private static volatile String sUserAgent;

    public static String getScheme() {
        return Constants.HTTPS;
    }

    static {
        String str;
        String str2 = "";
        try {
            str = System.getProperty("http.agent", str2);
        } catch (SecurityException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unable to get system user agent.");
            str = str2;
        }
        if (str != null) {
            str2 = str;
        }
        DEFAULT_USER_AGENT = str2;
    }

    public static MoPubRequestQueue getRequestQueue() {
        return sRequestQueue;
    }

    public static HurlStack.UrlRewriter getUrlRewriter(Context context) {
        Preconditions.checkNotNull(context);
        if (sUrlRewriter == null) {
            sUrlRewriter = new PlayServicesUrlRewriter();
        }
        return sUrlRewriter;
    }

    public static MoPubRequestQueue getRequestQueue(Context context) {
        MoPubRequestQueue moPubRequestQueue = sRequestQueue;
        if (moPubRequestQueue == null) {
            synchronized (Networking.class) {
                moPubRequestQueue = sRequestQueue;
                if (moPubRequestQueue == null) {
                    BasicNetwork basicNetwork = new BasicNetwork(new RequestQueueHttpStack(getUserAgent(context.getApplicationContext()), getUrlRewriter(context), CustomSSLSocketFactory.getDefault(10000)));
                    File file = new File(context.getCacheDir().getPath() + File.separator + CACHE_DIRECTORY_NAME);
                    MoPubRequestQueue moPubRequestQueue2 = new MoPubRequestQueue(new DiskBasedCache(file, (int) DeviceUtils.diskCacheSizeBytes(file, 10485760)), basicNetwork);
                    sRequestQueue = moPubRequestQueue2;
                    moPubRequestQueue2.start();
                    moPubRequestQueue = moPubRequestQueue2;
                }
            }
        }
        return moPubRequestQueue;
    }

    public static ImageLoader getImageLoader(Context context) {
        MaxWidthImageLoader maxWidthImageLoader = sMaxWidthImageLoader;
        if (maxWidthImageLoader == null) {
            synchronized (Networking.class) {
                maxWidthImageLoader = sMaxWidthImageLoader;
                if (maxWidthImageLoader == null) {
                    MoPubRequestQueue requestQueue = getRequestQueue(context);
                    final AnonymousClass1 r3 = new LruCache<String, Bitmap>(DeviceUtils.memoryCacheSizeBytes(context)) {
                        /* access modifiers changed from: protected */
                        public int sizeOf(String str, Bitmap bitmap) {
                            if (bitmap != null) {
                                return bitmap.getRowBytes() * bitmap.getHeight();
                            }
                            return super.sizeOf(str, bitmap);
                        }
                    };
                    MaxWidthImageLoader maxWidthImageLoader2 = new MaxWidthImageLoader(requestQueue, context, new ImageLoader.ImageCache() {
                        public Bitmap getBitmap(String str) {
                            return (Bitmap) r3.get(str);
                        }

                        public void putBitmap(String str, Bitmap bitmap) {
                            r3.put(str, bitmap);
                        }
                    });
                    sMaxWidthImageLoader = maxWidthImageLoader2;
                    maxWidthImageLoader = maxWidthImageLoader2;
                }
            }
        }
        return maxWidthImageLoader;
    }

    public static String getUserAgent(Context context) {
        Preconditions.checkNotNull(context);
        String str = sUserAgent;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return DEFAULT_USER_AGENT;
        }
        String str2 = DEFAULT_USER_AGENT;
        try {
            str2 = WebSettings.getDefaultUserAgent(context);
        } catch (Exception unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Failed to get a user agent. Defaulting to the system user agent.");
        }
        sUserAgent = str2;
        return str2;
    }

    public static String getCachedUserAgent() {
        String str = sUserAgent;
        return str == null ? DEFAULT_USER_AGENT : str;
    }

    public static synchronized void clearForTesting() {
        synchronized (Networking.class) {
            sRequestQueue = null;
            sMaxWidthImageLoader = null;
            sUserAgent = null;
        }
    }

    public static synchronized void setRequestQueueForTesting(MoPubRequestQueue moPubRequestQueue) {
        synchronized (Networking.class) {
            sRequestQueue = moPubRequestQueue;
        }
    }

    public static synchronized void setImageLoaderForTesting(MaxWidthImageLoader maxWidthImageLoader) {
        synchronized (Networking.class) {
            sMaxWidthImageLoader = maxWidthImageLoader;
        }
    }

    @Deprecated
    public static synchronized void setUserAgentForTesting(String str) {
        synchronized (Networking.class) {
            sUserAgent = str;
        }
    }

    public static void useHttps(boolean z) {
        sUseHttps = z;
    }

    public static boolean shouldUseHttps() {
        return sUseHttps;
    }

    public static String getBaseUrlScheme() {
        return shouldUseHttps() ? Constants.HTTPS : Constants.HTTP;
    }
}
