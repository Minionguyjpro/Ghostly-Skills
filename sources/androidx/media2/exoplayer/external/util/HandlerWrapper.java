package androidx.media2.exoplayer.external.util;

import android.os.Looper;
import android.os.Message;

public interface HandlerWrapper {
    Looper getLooper();

    Message obtainMessage(int i, int i2, int i3);

    Message obtainMessage(int i, int i2, int i3, Object obj);

    Message obtainMessage(int i, Object obj);

    void removeMessages(int i);

    boolean sendEmptyMessage(int i);

    boolean sendEmptyMessageAtTime(int i, long j);
}
