package com.google.android.play.core.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public abstract class br implements Closeable {
    public abstract long a();

    /* access modifiers changed from: protected */
    public abstract InputStream b(long j, long j2) throws IOException;

    public final synchronized InputStream c() throws IOException {
        return b(0, a());
    }
}
