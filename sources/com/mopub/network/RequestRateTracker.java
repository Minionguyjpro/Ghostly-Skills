package com.mopub.network;

import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RequestRateTracker {
    private Map<String, TimeRecord> mTimeRecordMap = Collections.synchronizedMap(new HashMap());

    public static class TimeRecord {
        public final int mBlockIntervalMs;
        final long mBlockStartTime = RequestRateTracker.currentTimeMs();
        public final String mReason;

        TimeRecord(int i, String str) {
            this.mBlockIntervalMs = i;
            this.mReason = str == null ? "unknown" : str;
        }

        /* access modifiers changed from: package-private */
        public long getTargetTime() {
            return this.mBlockStartTime + ((long) this.mBlockIntervalMs);
        }
    }

    private static class Helper {
        /* access modifiers changed from: private */
        public static RequestRateTracker sInstance = new RequestRateTracker();

        private Helper() {
        }
    }

    RequestRateTracker() {
    }

    public static RequestRateTracker getInstance() {
        return Helper.sInstance;
    }

    /* access modifiers changed from: package-private */
    public void registerRateLimit(String str, Integer num, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (num == null || num.intValue() <= 0) {
                this.mTimeRecordMap.remove(str);
            } else {
                this.mTimeRecordMap.put(str, new TimeRecord(num.intValue(), str2));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isBlockedByRateLimit(String str) {
        return getTimeUntilLimitEnds(str) > 0;
    }

    public TimeRecord getRecordForAdUnit(String str) {
        return this.mTimeRecordMap.get(str);
    }

    private long getTimeUntilLimitEnds(String str) {
        TimeRecord timeRecord = this.mTimeRecordMap.get(str);
        if (timeRecord == null) {
            return 0;
        }
        return timeRecord.getTargetTime() - currentTimeMs();
    }

    /* access modifiers changed from: private */
    public static long currentTimeMs() {
        return SystemClock.elapsedRealtime();
    }

    @Deprecated
    static void setInstance(RequestRateTracker requestRateTracker) {
        RequestRateTracker unused = Helper.sInstance = requestRateTracker;
    }
}
