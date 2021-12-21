package com.google.android.exoplayer2;

import android.os.SystemClock;
import java.io.IOException;

public final class ExoPlaybackException extends Exception {
    private final Throwable cause;
    public final Format rendererFormat;
    public final int rendererFormatSupport;
    public final int rendererIndex;
    public final long timestampMs;
    public final int type;

    public static ExoPlaybackException createForSource(IOException iOException) {
        return new ExoPlaybackException(0, iOException);
    }

    public static ExoPlaybackException createForRenderer(Exception exc, int i, Format format, int i2) {
        return new ExoPlaybackException(1, exc, i, format, format == null ? 4 : i2);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException) {
        return new ExoPlaybackException(2, runtimeException);
    }

    public static ExoPlaybackException createForOutOfMemoryError(OutOfMemoryError outOfMemoryError) {
        return new ExoPlaybackException(4, outOfMemoryError);
    }

    private ExoPlaybackException(int i, Throwable th) {
        this(i, th, -1, (Format) null, 4);
    }

    private ExoPlaybackException(int i, Throwable th, int i2, Format format, int i3) {
        super(th);
        this.type = i;
        this.cause = th;
        this.rendererIndex = i2;
        this.rendererFormat = format;
        this.rendererFormatSupport = i3;
        this.timestampMs = SystemClock.elapsedRealtime();
    }
}
