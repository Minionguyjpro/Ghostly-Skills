package com.google.android.datatransport.runtime.scheduling.persistence;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public abstract class EventStoreModule {
    static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }

    static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }
}
