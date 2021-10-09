package androidx.media;

import android.media.session.MediaSessionManager;
import androidx.media.MediaSessionManagerImplBase;

class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {

    static final class RemoteUserInfoImplApi28 extends MediaSessionManagerImplBase.RemoteUserInfoImplBase {
        final MediaSessionManager.RemoteUserInfo mObject;

        RemoteUserInfoImplApi28(String str, int i, int i2) {
            super(str, i, i2);
            this.mObject = new MediaSessionManager.RemoteUserInfo(str, i, i2);
        }
    }
}
