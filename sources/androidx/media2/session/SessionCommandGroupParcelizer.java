package androidx.media2.session;

import androidx.versionedparcelable.VersionedParcel;

public final class SessionCommandGroupParcelizer {
    public static SessionCommandGroup read(VersionedParcel versionedParcel) {
        SessionCommandGroup sessionCommandGroup = new SessionCommandGroup();
        sessionCommandGroup.mCommands = versionedParcel.readSet(sessionCommandGroup.mCommands, 1);
        return sessionCommandGroup;
    }

    public static void write(SessionCommandGroup sessionCommandGroup, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeSet(sessionCommandGroup.mCommands, 1);
    }
}
