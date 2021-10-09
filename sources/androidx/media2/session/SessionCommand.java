package androidx.media2.session;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.core.util.ObjectsCompat;
import androidx.versionedparcelable.VersionedParcelable;

public final class SessionCommand implements VersionedParcelable {
    static final ArrayMap<Integer, Range> VERSION_LIBRARY_COMMANDS_MAP;
    static final ArrayMap<Integer, Range> VERSION_PLAYER_BASIC_COMMANDS_MAP = new ArrayMap<>();
    static final ArrayMap<Integer, Range> VERSION_PLAYER_HIDDEN_COMMANDS_MAP = new ArrayMap<>();
    static final ArrayMap<Integer, Range> VERSION_PLAYER_PLAYLIST_COMMANDS_MAP = new ArrayMap<>();
    static final ArrayMap<Integer, Range> VERSION_SESSION_COMMANDS_MAP;
    static final ArrayMap<Integer, Range> VERSION_VOLUME_COMMANDS_MAP;
    int mCommandCode;
    String mCustomAction;
    Bundle mCustomExtras;

    static {
        VERSION_PLAYER_BASIC_COMMANDS_MAP.put(1, new Range(10000, 10004));
        VERSION_PLAYER_PLAYLIST_COMMANDS_MAP.put(1, new Range(10005, 10018));
        VERSION_PLAYER_HIDDEN_COMMANDS_MAP.put(1, new Range(11000, 11002));
        ArrayMap<Integer, Range> arrayMap = new ArrayMap<>();
        VERSION_VOLUME_COMMANDS_MAP = arrayMap;
        arrayMap.put(1, new Range(30000, 30001));
        ArrayMap<Integer, Range> arrayMap2 = new ArrayMap<>();
        VERSION_SESSION_COMMANDS_MAP = arrayMap2;
        arrayMap2.put(1, new Range(40000, 40010));
        ArrayMap<Integer, Range> arrayMap3 = new ArrayMap<>();
        VERSION_LIBRARY_COMMANDS_MAP = arrayMap3;
        arrayMap3.put(1, new Range(50000, 50006));
    }

    SessionCommand() {
    }

    public SessionCommand(int i) {
        if (i != 0) {
            this.mCommandCode = i;
            this.mCustomAction = null;
            this.mCustomExtras = null;
            return;
        }
        throw new IllegalArgumentException("commandCode shouldn't be COMMAND_CODE_CUSTOM");
    }

    public int getCommandCode() {
        return this.mCommandCode;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionCommand)) {
            return false;
        }
        SessionCommand sessionCommand = (SessionCommand) obj;
        if (this.mCommandCode != sessionCommand.mCommandCode || !TextUtils.equals(this.mCustomAction, sessionCommand.mCustomAction)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.mCustomAction, Integer.valueOf(this.mCommandCode));
    }

    static final class Range {
        public final int lower;
        public final int upper;

        Range(int i, int i2) {
            this.lower = i;
            this.upper = i2;
        }
    }
}
