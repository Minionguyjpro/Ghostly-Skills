package androidx.media2.exoplayer.external.upstream;

import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.upstream.HttpDataSource;
import androidx.media2.exoplayer.external.upstream.Loader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DefaultLoadErrorHandlingPolicy implements LoadErrorHandlingPolicy {
    private final int minimumLoadableRetryCount;

    public DefaultLoadErrorHandlingPolicy() {
        this(-1);
    }

    public DefaultLoadErrorHandlingPolicy(int i) {
        this.minimumLoadableRetryCount = i;
    }

    public long getBlacklistDurationMsFor(int i, long j, IOException iOException, int i2) {
        if (!(iOException instanceof HttpDataSource.InvalidResponseCodeException)) {
            return -9223372036854775807L;
        }
        int i3 = ((HttpDataSource.InvalidResponseCodeException) iOException).responseCode;
        if (i3 == 404 || i3 == 410) {
            return 60000;
        }
        return -9223372036854775807L;
    }

    public long getRetryDelayMsFor(int i, long j, IOException iOException, int i2) {
        if ((iOException instanceof ParserException) || (iOException instanceof FileNotFoundException) || (iOException instanceof Loader.UnexpectedLoaderException)) {
            return -9223372036854775807L;
        }
        return (long) Math.min((i2 - 1) * 1000, 5000);
    }

    public int getMinimumLoadableRetryCount(int i) {
        int i2 = this.minimumLoadableRetryCount;
        if (i2 == -1) {
            return i == 7 ? 6 : 3;
        }
        return i2;
    }
}
