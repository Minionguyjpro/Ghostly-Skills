package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
class CreationContextFactory {
    private final Context applicationContext;
    private final Clock monotonicClock;
    private final Clock wallClock;

    CreationContextFactory(Context context, Clock clock, Clock clock2) {
        this.applicationContext = context;
        this.wallClock = clock;
        this.monotonicClock = clock2;
    }

    /* access modifiers changed from: package-private */
    public CreationContext create(String str) {
        return CreationContext.create(this.applicationContext, this.wallClock, this.monotonicClock, str);
    }
}
