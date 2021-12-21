package com.integralads.avid.library.mopub;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import com.integralads.avid.library.mopub.DownloadAvidTask;
import com.integralads.avid.library.mopub.utils.NetworkUtils;

public class AvidLoader implements DownloadAvidTask.DownloadAvidTaskListener {
    private static AvidLoader avidLoader = new AvidLoader();
    /* access modifiers changed from: private */
    public DownloadAvidTask activeTask;
    /* access modifiers changed from: private */
    public final Runnable avidDownloadRunnable = new Runnable() {
        public void run() {
            if (AvidLoader.this.context == null || !NetworkUtils.isNetworkAvailable(AvidLoader.this.context)) {
                AvidLoader.this.repeatLoading();
            } else {
                AvidLoader.this.loadAvidJSFromUrl();
            }
        }
    };
    /* access modifiers changed from: private */
    public Context context;
    private AvidLoaderListener listener;
    private TaskExecutor taskExecutor = new TaskExecutor();
    private TaskRepeater taskRepeater;

    public interface AvidLoaderListener {
        void onAvidLoaded();
    }

    public static AvidLoader getInstance() {
        return avidLoader;
    }

    public void registerAvidLoader(Context context2) {
        this.context = context2;
        this.taskRepeater = new TaskRepeater();
        loadAvidJSFromUrl();
    }

    public void unregisterAvidLoader() {
        TaskRepeater taskRepeater2 = this.taskRepeater;
        if (taskRepeater2 != null) {
            taskRepeater2.cleanup();
            this.taskRepeater = null;
        }
        this.listener = null;
        this.context = null;
    }

    public void setListener(AvidLoaderListener avidLoaderListener) {
        this.listener = avidLoaderListener;
    }

    public AvidLoaderListener getListener() {
        return this.listener;
    }

    /* access modifiers changed from: private */
    public void loadAvidJSFromUrl() {
        if (!AvidBridge.isAvidJsReady() && this.activeTask == null) {
            DownloadAvidTask downloadAvidTask = new DownloadAvidTask();
            this.activeTask = downloadAvidTask;
            downloadAvidTask.setListener(this);
            this.taskExecutor.executeTask(this.activeTask);
        }
    }

    /* access modifiers changed from: private */
    public void repeatLoading() {
        TaskRepeater taskRepeater2 = this.taskRepeater;
        if (taskRepeater2 != null) {
            taskRepeater2.repeatLoading();
        }
    }

    public void onLoadAvid(String str) {
        this.activeTask = null;
        AvidBridge.setAvidJs(str);
        AvidLoaderListener avidLoaderListener = this.listener;
        if (avidLoaderListener != null) {
            avidLoaderListener.onAvidLoaded();
        }
    }

    public void failedToLoadAvid() {
        this.activeTask = null;
        repeatLoading();
    }

    public class TaskExecutor {
        public TaskExecutor() {
        }

        public void executeTask(DownloadAvidTask downloadAvidTask) {
            if (Build.VERSION.SDK_INT >= 11) {
                AvidLoader.this.activeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"https://mobile-static.adsafeprotected.com/avid-v2.js"});
            } else {
                AvidLoader.this.activeTask.execute(new String[]{"https://mobile-static.adsafeprotected.com/avid-v2.js"});
            }
        }
    }

    public class TaskRepeater {
        private Handler handler = new Handler();

        public TaskRepeater() {
        }

        public void repeatLoading() {
            this.handler.postDelayed(AvidLoader.this.avidDownloadRunnable, 2000);
        }

        public void cleanup() {
            this.handler.removeCallbacks(AvidLoader.this.avidDownloadRunnable);
        }
    }
}
