package com.mopub.nativeads;

import android.os.SystemClock;

class TimestampWrapper<T> {
    long mCreatedTimestamp = SystemClock.uptimeMillis();
    final T mInstance;

    TimestampWrapper(T t) {
        this.mInstance = t;
    }
}
