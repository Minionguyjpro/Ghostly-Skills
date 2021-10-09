package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import com.mopub.common.Constants;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SQLiteEventStore$$Lambda$13 implements SQLiteEventStore.Function {
    private final long arg$1;

    private SQLiteEventStore$$Lambda$13(long j) {
        this.arg$1 = j;
    }

    public static SQLiteEventStore.Function lambdaFactory$(long j) {
        return new SQLiteEventStore$$Lambda$13(j);
    }

    public Object apply(Object obj) {
        return Integer.valueOf(((SQLiteDatabase) obj).delete(Constants.VIDEO_TRACKING_EVENTS_KEY, "timestamp_ms < ?", new String[]{String.valueOf(this.arg$1)}));
    }
}
