package com.mopub.common.util;

import java.util.concurrent.TimeUnit;

public class Timer {
    private long mStartTimeNanos;
    private State mState = State.STOPPED;
    private long mStopTimeNanos;

    private enum State {
        STARTED,
        STOPPED
    }

    public void start() {
        this.mStartTimeNanos = System.nanoTime();
        this.mState = State.STARTED;
    }

    public void stop() {
        if (this.mState == State.STARTED) {
            this.mState = State.STOPPED;
            this.mStopTimeNanos = System.nanoTime();
            return;
        }
        throw new IllegalStateException("EventTimer was not started.");
    }

    public long getTime() {
        long j;
        if (this.mState == State.STARTED) {
            j = System.nanoTime();
        } else {
            j = this.mStopTimeNanos;
        }
        return TimeUnit.MILLISECONDS.convert(j - this.mStartTimeNanos, TimeUnit.NANOSECONDS);
    }
}
