package androidx.media2.session;

import androidx.media2.session.SessionToken;
import androidx.versionedparcelable.VersionedParcel;

public final class SessionTokenParcelizer {
    public static SessionToken read(VersionedParcel versionedParcel) {
        SessionToken sessionToken = new SessionToken();
        sessionToken.mImpl = (SessionToken.SessionTokenImpl) versionedParcel.readVersionedParcelable(sessionToken.mImpl, 1);
        return sessionToken;
    }

    public static void write(SessionToken sessionToken, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeVersionedParcelable(sessionToken.mImpl, 1);
    }
}
