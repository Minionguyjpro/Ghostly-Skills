package androidx.media2.exoplayer.external.drm;

import android.os.Looper;
import androidx.media2.exoplayer.external.drm.ExoMediaCrypto;

public interface DrmSessionManager<T extends ExoMediaCrypto> {
    DrmSession<T> acquireSession(Looper looper, DrmInitData drmInitData);

    boolean canAcquireSession(DrmInitData drmInitData);

    void releaseSession(DrmSession<T> drmSession);
}
