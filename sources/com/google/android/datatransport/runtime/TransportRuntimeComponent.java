package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import java.io.Closeable;
import java.io.IOException;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
abstract class TransportRuntimeComponent implements Closeable {

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    interface Builder {
        TransportRuntimeComponent build();

        Builder setApplicationContext(Context context);
    }

    /* access modifiers changed from: package-private */
    public abstract EventStore getEventStore();

    /* access modifiers changed from: package-private */
    public abstract TransportRuntime getTransportRuntime();

    TransportRuntimeComponent() {
    }

    public void close() throws IOException {
        getEventStore().close();
    }
}
