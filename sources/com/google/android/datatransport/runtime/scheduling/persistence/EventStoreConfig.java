package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.AutoValue_EventStoreConfig;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
abstract class EventStoreConfig {
    static final EventStoreConfig DEFAULT = builder().setMaxStorageSizeInBytes(10485760).setLoadBatchSize(200).setCriticalSectionEnterTimeoutMs(10000).setEventCleanUpAge(604800000).build();

    /* access modifiers changed from: package-private */
    public abstract int getCriticalSectionEnterTimeoutMs();

    /* access modifiers changed from: package-private */
    public abstract long getEventCleanUpAge();

    /* access modifiers changed from: package-private */
    public abstract int getLoadBatchSize();

    /* access modifiers changed from: package-private */
    public abstract long getMaxStorageSizeInBytes();

    EventStoreConfig() {
    }

    static Builder builder() {
        return new AutoValue_EventStoreConfig.Builder();
    }

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static abstract class Builder {
        /* access modifiers changed from: package-private */
        public abstract EventStoreConfig build();

        /* access modifiers changed from: package-private */
        public abstract Builder setCriticalSectionEnterTimeoutMs(int i);

        /* access modifiers changed from: package-private */
        public abstract Builder setEventCleanUpAge(long j);

        /* access modifiers changed from: package-private */
        public abstract Builder setLoadBatchSize(int i);

        /* access modifiers changed from: package-private */
        public abstract Builder setMaxStorageSizeInBytes(long j);

        Builder() {
        }
    }
}
