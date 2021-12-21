package com.google.android.exoplayer2.drm;

import android.os.Looper;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;

public interface DrmSessionManager<T extends ExoMediaCrypto> {
    public static final DrmSessionManager<ExoMediaCrypto> DUMMY = new DrmSessionManager<ExoMediaCrypto>() {
        public /* synthetic */ DrmSession<T> acquirePlaceholderSession(Looper looper, int i) {
            return CC.$default$acquirePlaceholderSession(this, looper, i);
        }

        public boolean canAcquireSession(DrmInitData drmInitData) {
            return false;
        }

        public Class<ExoMediaCrypto> getExoMediaCryptoType(DrmInitData drmInitData) {
            return null;
        }

        public /* synthetic */ void prepare() {
            CC.$default$prepare(this);
        }

        public /* synthetic */ void release() {
            CC.$default$release(this);
        }

        public DrmSession<ExoMediaCrypto> acquireSession(Looper looper, DrmInitData drmInitData) {
            return new ErrorStateDrmSession(new DrmSession.DrmSessionException(new UnsupportedDrmException(1)));
        }
    };

    DrmSession<T> acquirePlaceholderSession(Looper looper, int i);

    DrmSession<T> acquireSession(Looper looper, DrmInitData drmInitData);

    boolean canAcquireSession(DrmInitData drmInitData);

    Class<? extends ExoMediaCrypto> getExoMediaCryptoType(DrmInitData drmInitData);

    void prepare();

    void release();

    /* renamed from: com.google.android.exoplayer2.drm.DrmSessionManager$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static DrmSession $default$acquirePlaceholderSession(DrmSessionManager drmSessionManager, Looper looper, int i) {
            return null;
        }

        public static void $default$prepare(DrmSessionManager drmSessionManager) {
        }

        public static void $default$release(DrmSessionManager drmSessionManager) {
        }

        public static <T extends ExoMediaCrypto> DrmSessionManager<T> getDummyDrmSessionManager() {
            return DrmSessionManager.DUMMY;
        }
    }
}
