package androidx.media2.exoplayer.external.drm;

import androidx.media2.exoplayer.external.drm.DrmSession;
import androidx.media2.exoplayer.external.drm.ExoMediaCrypto;
import androidx.media2.exoplayer.external.util.Assertions;
import java.util.Map;

public final class ErrorStateDrmSession<T extends ExoMediaCrypto> implements DrmSession<T> {
    private final DrmSession.DrmSessionException error;

    public T getMediaCrypto() {
        return null;
    }

    public int getState() {
        return 1;
    }

    public Map<String, String> queryKeyStatus() {
        return null;
    }

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        this.error = (DrmSession.DrmSessionException) Assertions.checkNotNull(drmSessionException);
    }

    public DrmSession.DrmSessionException getError() {
        return this.error;
    }
}
