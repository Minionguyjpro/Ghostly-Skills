package com.mopub.common;

import android.os.Handler;
import android.os.Looper;

class CompositeSdkInitializationListener implements SdkInitializationListener {
    /* access modifiers changed from: private */
    public SdkInitializationListener mSdkInitializationListener;
    private int mTimes;

    public CompositeSdkInitializationListener(SdkInitializationListener sdkInitializationListener, int i) {
        Preconditions.checkNotNull(sdkInitializationListener);
        this.mSdkInitializationListener = sdkInitializationListener;
        this.mTimes = i;
    }

    public void onInitializationFinished() {
        int i = this.mTimes - 1;
        this.mTimes = i;
        if (i <= 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (CompositeSdkInitializationListener.this.mSdkInitializationListener != null) {
                        CompositeSdkInitializationListener.this.mSdkInitializationListener.onInitializationFinished();
                        SdkInitializationListener unused = CompositeSdkInitializationListener.this.mSdkInitializationListener = null;
                    }
                }
            });
        }
    }
}
