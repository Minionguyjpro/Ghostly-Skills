package androidx.media2.exoplayer.external.drm;

import androidx.media2.exoplayer.external.drm.ExoMediaCrypto;
import java.util.Map;

public interface DrmSession<T extends ExoMediaCrypto> {
    DrmSessionException getError();

    T getMediaCrypto();

    int getState();

    Map<String, String> queryKeyStatus();

    public static class DrmSessionException extends Exception {
        public DrmSessionException(Throwable th) {
            super(th);
        }
    }
}
