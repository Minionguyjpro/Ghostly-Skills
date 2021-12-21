package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.util.Assertions;
import java.io.IOException;

public final class ExoPlaybackException extends Exception {
    private final Throwable cause;
    public final int rendererIndex;
    public final int type;

    public static ExoPlaybackException createForSource(IOException iOException) {
        return new ExoPlaybackException(0, iOException, -1);
    }

    public static ExoPlaybackException createForRenderer(Exception exc, int i) {
        return new ExoPlaybackException(1, exc, i);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException) {
        return new ExoPlaybackException(2, runtimeException, -1);
    }

    public static ExoPlaybackException createForOutOfMemoryError(OutOfMemoryError outOfMemoryError) {
        return new ExoPlaybackException(4, outOfMemoryError, -1);
    }

    private ExoPlaybackException(int i, Throwable th, int i2) {
        super(th);
        this.type = i;
        this.cause = th;
        this.rendererIndex = i2;
    }

    public IOException getSourceException() {
        Assertions.checkState(this.type == 0);
        return (IOException) Assertions.checkNotNull(this.cause);
    }
}
