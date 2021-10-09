package androidx.media2.common;

import java.io.Closeable;
import java.io.IOException;

public abstract class DataSourceCallback implements Closeable {
    public abstract long getSize() throws IOException;

    public abstract int readAt(long j, byte[] bArr, int i, int i2) throws IOException;
}
