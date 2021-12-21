package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.util.Assertions;

public final class ErrorStateDrmSession<T extends ExoMediaCrypto> implements DrmSession<T> {
    private final DrmSession.DrmSessionException error;

    public void acquire() {
    }

    public T getMediaCrypto() {
        return null;
    }

    public int getState() {
        return 1;
    }

    public boolean playClearSamplesWithoutKeys() {
        return false;
    }

    public void release() {
    }

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        this.error = (DrmSession.DrmSessionException) Assertions.checkNotNull(drmSessionException);
    }

    public DrmSession.DrmSessionException getError() {
        return this.error;
    }
}
