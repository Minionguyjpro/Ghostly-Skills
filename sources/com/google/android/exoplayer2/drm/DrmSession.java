package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import java.io.IOException;

public interface DrmSession<T extends ExoMediaCrypto> {
    void acquire();

    DrmSessionException getError();

    T getMediaCrypto();

    int getState();

    boolean playClearSamplesWithoutKeys();

    void release();

    /* renamed from: com.google.android.exoplayer2.drm.DrmSession$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$playClearSamplesWithoutKeys(DrmSession drmSession) {
            return false;
        }

        public static <T extends ExoMediaCrypto> void replaceSession(DrmSession<T> drmSession, DrmSession<T> drmSession2) {
            if (drmSession != drmSession2) {
                if (drmSession2 != null) {
                    drmSession2.acquire();
                }
                if (drmSession != null) {
                    drmSession.release();
                }
            }
        }
    }

    public static class DrmSessionException extends IOException {
        public DrmSessionException(Throwable th) {
            super(th);
        }
    }
}
