package androidx.media2.session;

import android.os.Bundle;
import androidx.versionedparcelable.VersionedParcelable;

class ConnectionRequest implements VersionedParcelable {
    Bundle mConnectionHints;
    String mPackageName;
    int mPid;
    int mVersion;

    ConnectionRequest() {
    }
}
