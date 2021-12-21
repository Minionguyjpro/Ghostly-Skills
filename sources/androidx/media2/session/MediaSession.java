package androidx.media2.session;

import android.os.Bundle;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.HashMap;

public class MediaSession implements AutoCloseable {
    private static final HashMap<String, MediaSession> SESSION_ID_TO_SESSION_MAP = new HashMap<>();
    private static final Object STATIC_LOCK = new Object();
    private final MediaSessionImpl mImpl;

    interface MediaSessionImpl extends AutoCloseable {
        String getId();
    }

    public void close() {
        try {
            synchronized (STATIC_LOCK) {
                SESSION_ID_TO_SESSION_MAP.remove(this.mImpl.getId());
            }
            this.mImpl.close();
        } catch (Exception unused) {
        }
    }

    public static final class CommandButton implements VersionedParcelable {
        SessionCommand mCommand;
        CharSequence mDisplayName;
        boolean mEnabled;
        Bundle mExtras;
        int mIconResId;

        CommandButton() {
        }
    }
}
