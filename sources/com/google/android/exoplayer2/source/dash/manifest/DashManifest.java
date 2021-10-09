package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.C;
import java.util.List;

public class DashManifest {
    public final long availabilityStartTimeMs;
    public final long durationMs;
    public final boolean dynamic;
    public final Uri location;
    public final long minUpdatePeriodMs;
    private final List<Period> periods;
    public final long publishTimeMs;
    public final long suggestedPresentationDelayMs;
    public final long timeShiftBufferDepthMs;
    public final UtcTimingElement utcTiming;

    public final int getPeriodCount() {
        return this.periods.size();
    }

    public final Period getPeriod(int i) {
        return this.periods.get(i);
    }

    public final long getPeriodDurationMs(int i) {
        long j;
        if (i == this.periods.size() - 1) {
            long j2 = this.durationMs;
            if (j2 == -9223372036854775807L) {
                return -9223372036854775807L;
            }
            j = j2 - this.periods.get(i).startMs;
        } else {
            j = this.periods.get(i + 1).startMs - this.periods.get(i).startMs;
        }
        return j;
    }

    public final long getPeriodDurationUs(int i) {
        return C.msToUs(getPeriodDurationMs(i));
    }
}
