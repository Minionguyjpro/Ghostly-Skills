package com.mopub.mobileads;

import android.os.Handler;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mraid.MraidController;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebViewCacheService {
    static final int MAX_SIZE = 50;
    static final long TRIM_CACHE_FREQUENCY_MILLIS = 900000;
    private static Handler sHandler = new Handler();
    static final TrimCacheRunnable sTrimCacheRunnable = new TrimCacheRunnable();
    private static final Map<Long, Config> sWebViewConfigs = Collections.synchronizedMap(new HashMap());

    public static class Config {
        private final MraidController mController;
        private final ExternalViewabilitySessionManager mViewabilityManager;
        private final WeakReference<Interstitial> mWeakInterstitial;
        private final BaseWebView mWebView;

        Config(BaseWebView baseWebView, Interstitial interstitial, ExternalViewabilitySessionManager externalViewabilitySessionManager, MraidController mraidController) {
            this.mWebView = baseWebView;
            this.mWeakInterstitial = new WeakReference<>(interstitial);
            this.mViewabilityManager = externalViewabilitySessionManager;
            this.mController = mraidController;
        }

        public BaseWebView getWebView() {
            return this.mWebView;
        }

        public WeakReference<Interstitial> getWeakInterstitial() {
            return this.mWeakInterstitial;
        }

        public ExternalViewabilitySessionManager getViewabilityManager() {
            return this.mViewabilityManager;
        }

        public MraidController getController() {
            return this.mController;
        }
    }

    private WebViewCacheService() {
    }

    public static void storeWebViewConfig(Long l, Interstitial interstitial, BaseWebView baseWebView, ExternalViewabilitySessionManager externalViewabilitySessionManager, MraidController mraidController) {
        Preconditions.checkNotNull(l);
        Preconditions.checkNotNull(interstitial);
        Preconditions.checkNotNull(baseWebView);
        trimCache();
        if (sWebViewConfigs.size() >= 50) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unable to cache web view. Please destroy some via MoPubInterstitial#destroy() and try again.");
            return;
        }
        sWebViewConfigs.put(l, new Config(baseWebView, interstitial, externalViewabilitySessionManager, mraidController));
    }

    public static Config popWebViewConfig(Long l) {
        Preconditions.checkNotNull(l);
        return sWebViewConfigs.remove(l);
    }

    static synchronized void trimCache() {
        synchronized (WebViewCacheService.class) {
            Iterator<Map.Entry<Long, Config>> it = sWebViewConfigs.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                if (((Config) next.getValue()).getWeakInterstitial().get() == null) {
                    ((Config) next.getValue()).getViewabilityManager().endDisplaySession();
                    it.remove();
                }
            }
            if (!sWebViewConfigs.isEmpty()) {
                sHandler.removeCallbacks(sTrimCacheRunnable);
                sHandler.postDelayed(sTrimCacheRunnable, TRIM_CACHE_FREQUENCY_MILLIS);
            }
        }
    }

    private static class TrimCacheRunnable implements Runnable {
        private TrimCacheRunnable() {
        }

        public void run() {
            WebViewCacheService.trimCache();
        }
    }

    @Deprecated
    public static void clearAll() {
        sWebViewConfigs.clear();
        sHandler.removeCallbacks(sTrimCacheRunnable);
    }

    @Deprecated
    static Map<Long, Config> getWebViewConfigs() {
        return sWebViewConfigs;
    }

    @Deprecated
    static void setHandler(Handler handler) {
        sHandler = handler;
    }
}
