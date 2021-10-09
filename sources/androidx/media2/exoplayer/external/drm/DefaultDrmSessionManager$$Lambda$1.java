package androidx.media2.exoplayer.external.drm;

import androidx.media2.exoplayer.external.drm.DefaultDrmSession;

final /* synthetic */ class DefaultDrmSessionManager$$Lambda$1 implements DefaultDrmSession.ReleaseCallback {
    private final DefaultDrmSessionManager arg$1;

    DefaultDrmSessionManager$$Lambda$1(DefaultDrmSessionManager defaultDrmSessionManager) {
        this.arg$1 = defaultDrmSessionManager;
    }

    public void onSessionReleased(DefaultDrmSession defaultDrmSession) {
        this.arg$1.bridge$lambda$0$DefaultDrmSessionManager(defaultDrmSession);
    }
}
