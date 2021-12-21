package androidx.media2.session;

import android.os.Bundle;
import androidx.versionedparcelable.VersionedParcelable;

public abstract class MediaLibraryService extends MediaSessionService {

    public static final class LibraryParams implements VersionedParcelable {
        Bundle mBundle;
        int mOffline;
        int mRecent;
        int mSuggested;

        LibraryParams() {
        }
    }
}
