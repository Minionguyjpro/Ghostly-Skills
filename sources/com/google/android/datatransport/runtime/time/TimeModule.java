package com.google.android.datatransport.runtime.time;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public abstract class TimeModule {
    static Clock eventClock() {
        return new WallTimeClock();
    }

    static Clock uptimeClock() {
        return new UptimeClock();
    }
}
