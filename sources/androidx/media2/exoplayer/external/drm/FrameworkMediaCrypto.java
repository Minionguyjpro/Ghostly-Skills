package androidx.media2.exoplayer.external.drm;

import java.util.UUID;

public final class FrameworkMediaCrypto implements ExoMediaCrypto {
    public final boolean forceAllowInsecureDecoderComponents;
    public final byte[] sessionId;
    public final UUID uuid;
}
