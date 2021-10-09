package androidx.media2.session;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.core.util.ObjectsCompat;
import androidx.media2.session.SessionToken;
import androidx.versionedparcelable.CustomVersionedParcelable;
import androidx.versionedparcelable.VersionedParcelable;

final class SessionTokenImplLegacy extends CustomVersionedParcelable implements SessionToken.SessionTokenImpl {
    ComponentName mComponentName;
    Bundle mExtras;
    private MediaSessionCompat.Token mLegacyToken;
    Bundle mLegacyTokenBundle;
    String mPackageName;
    int mType;
    int mUid;

    SessionTokenImplLegacy() {
    }

    public int hashCode() {
        return ObjectsCompat.hash(Integer.valueOf(this.mType), this.mComponentName, this.mLegacyToken);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionTokenImplLegacy)) {
            return false;
        }
        SessionTokenImplLegacy sessionTokenImplLegacy = (SessionTokenImplLegacy) obj;
        int i = this.mType;
        if (i != sessionTokenImplLegacy.mType) {
            return false;
        }
        if (i == 100) {
            return ObjectsCompat.equals(this.mLegacyToken, sessionTokenImplLegacy.mLegacyToken);
        }
        if (i != 101) {
            return false;
        }
        return ObjectsCompat.equals(this.mComponentName, sessionTokenImplLegacy.mComponentName);
    }

    public String toString() {
        return "SessionToken {legacyToken=" + this.mLegacyToken + "}";
    }

    public void onPreParceling(boolean z) {
        MediaSessionCompat.Token token = this.mLegacyToken;
        if (token != null) {
            VersionedParcelable session2Token = token.getSession2Token();
            this.mLegacyToken.setSession2Token((VersionedParcelable) null);
            this.mLegacyTokenBundle = this.mLegacyToken.toBundle();
            this.mLegacyToken.setSession2Token(session2Token);
            return;
        }
        this.mLegacyTokenBundle = null;
    }

    public void onPostParceling() {
        this.mLegacyToken = MediaSessionCompat.Token.fromBundle(this.mLegacyTokenBundle);
        this.mLegacyTokenBundle = null;
    }
}
