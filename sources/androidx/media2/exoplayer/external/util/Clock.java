package androidx.media2.exoplayer.external.util;

import android.os.Handler;
import android.os.Looper;

public interface Clock {
    public static final Clock DEFAULT = new SystemClock();

    HandlerWrapper createHandler(Looper looper, Handler.Callback callback);

    long elapsedRealtime();

    long uptimeMillis();
}
