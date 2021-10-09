package androidx.media2.session;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import androidx.media2.session.SessionToken;

final class SessionTokenImplBase implements SessionToken.SessionTokenImpl {
    ComponentName mComponentName;
    Bundle mExtras;
    IBinder mISession;
    String mPackageName;
    String mServiceName;
    int mType;
    int mUid;

    SessionTokenImplBase() {
    }

    public int hashCode() {
        return ObjectsCompat.hash(Integer.valueOf(this.mType), Integer.valueOf(this.mUid), this.mPackageName, this.mServiceName);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionTokenImplBase)) {
            return false;
        }
        SessionTokenImplBase sessionTokenImplBase = (SessionTokenImplBase) obj;
        if (this.mUid != sessionTokenImplBase.mUid || !TextUtils.equals(this.mPackageName, sessionTokenImplBase.mPackageName) || !TextUtils.equals(this.mServiceName, sessionTokenImplBase.mServiceName) || this.mType != sessionTokenImplBase.mType || !ObjectsCompat.equals(this.mISession, sessionTokenImplBase.mISession)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "SessionToken {pkg=" + this.mPackageName + " type=" + this.mType + " service=" + this.mServiceName + " IMediaSession=" + this.mISession + " extras=" + this.mExtras + "}";
    }
}
