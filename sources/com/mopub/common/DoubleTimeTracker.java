package com.mopub.common;

import android.os.SystemClock;
import com.mopub.common.logging.MoPubLog;

public class DoubleTimeTracker {
    private long interval;
    private final Clock mClock;
    private long startedTimestamp;
    private volatile State state;

    public interface Clock {
        long elapsedRealTime();
    }

    private enum State {
        STARTED,
        PAUSED
    }

    public DoubleTimeTracker() {
        this(new SystemClockClock());
    }

    public DoubleTimeTracker(Clock clock) {
        this.mClock = clock;
        this.state = State.PAUSED;
    }

    public synchronized void start() {
        if (this.state == State.STARTED) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "DoubleTimeTracker already started.");
            return;
        }
        this.state = State.STARTED;
        this.startedTimestamp = this.mClock.elapsedRealTime();
    }

    public synchronized void pause() {
        if (this.state == State.PAUSED) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "DoubleTimeTracker already paused.");
            return;
        }
        this.interval += computeIntervalDiff();
        this.startedTimestamp = 0;
        this.state = State.PAUSED;
    }

    public synchronized double getInterval() {
        return (double) (this.interval + computeIntervalDiff());
    }

    private synchronized long computeIntervalDiff() {
        if (this.state == State.PAUSED) {
            return 0;
        }
        return this.mClock.elapsedRealTime() - this.startedTimestamp;
    }

    private static class SystemClockClock implements Clock {
        private SystemClockClock() {
        }

        public long elapsedRealTime() {
            return SystemClock.elapsedRealtime();
        }
    }
}
