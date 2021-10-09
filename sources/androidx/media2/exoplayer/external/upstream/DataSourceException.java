package androidx.media2.exoplayer.external.upstream;

import java.io.IOException;

public final class DataSourceException extends IOException {
    public final int reason;

    public DataSourceException(int i) {
        this.reason = i;
    }
}
