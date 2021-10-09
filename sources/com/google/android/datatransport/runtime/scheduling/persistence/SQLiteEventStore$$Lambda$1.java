package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SQLiteEventStore$$Lambda$1 implements SQLiteEventStore.Producer {
    private final SchemaManager arg$1;

    private SQLiteEventStore$$Lambda$1(SchemaManager schemaManager) {
        this.arg$1 = schemaManager;
    }

    public static SQLiteEventStore.Producer lambdaFactory$(SchemaManager schemaManager) {
        return new SQLiteEventStore$$Lambda$1(schemaManager);
    }

    public Object produce() {
        return this.arg$1.getWritableDatabase();
    }
}
