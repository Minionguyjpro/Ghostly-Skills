package com.tappx.a;

import android.os.AsyncTask;
import android.os.Build;

class u4 {
    public static <P> void a(AsyncTask<P, ?, ?> asyncTask, P... pArr) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pArr);
        } else {
            asyncTask.execute(pArr);
        }
    }

    public static boolean a(int i, int i2) {
        return (i & i2) != 0;
    }
}
