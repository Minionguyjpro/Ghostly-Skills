package androidx.media2.exoplayer.external.drm;

public interface DefaultDrmSessionEventListener {
    void onDrmKeysLoaded();

    void onDrmKeysRestored();

    void onDrmSessionAcquired();

    void onDrmSessionManagerError(Exception exc);

    void onDrmSessionReleased();
}
