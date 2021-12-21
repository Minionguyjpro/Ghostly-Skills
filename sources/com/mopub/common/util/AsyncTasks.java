package com.mopub.common.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.util.concurrent.Executor;

public class AsyncTasks {
    /* access modifiers changed from: private */
    public static Executor sExecutor;
    private static Handler sUiThreadHandler;

    static {
        init();
    }

    private static void init() {
        sExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
        sUiThreadHandler = new Handler(Looper.getMainLooper());
    }

    public static void setExecutor(Executor executor) {
        sExecutor = executor;
    }

    public static <P> void safeExecuteOnExecutor(final AsyncTask<P, ?, ?> asyncTask, final P... pArr) {
        Preconditions.checkNotNull(asyncTask, "Unable to execute null AsyncTask.");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            asyncTask.executeOnExecutor(sExecutor, pArr);
            return;
        }
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Posting AsyncTask to main thread for execution.");
        sUiThreadHandler.post(new Runnable() {
            public void run() {
                asyncTask.executeOnExecutor(AsyncTasks.sExecutor, pArr);
            }
        });
    }
}
