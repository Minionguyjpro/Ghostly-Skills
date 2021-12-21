package androidx.media2.exoplayer.external.drm;

import androidx.media2.exoplayer.external.drm.ExoMediaDrm;
import java.util.UUID;

public interface MediaDrmCallback {
    byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws Exception;

    byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws Exception;
}
