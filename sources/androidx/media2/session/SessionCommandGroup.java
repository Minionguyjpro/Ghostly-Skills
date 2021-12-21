package androidx.media2.session;

import androidx.collection.ArrayMap;
import androidx.core.util.ObjectsCompat;
import androidx.media2.session.SessionCommand;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class SessionCommandGroup implements VersionedParcelable {
    Set<SessionCommand> mCommands;

    public SessionCommandGroup() {
        this.mCommands = new HashSet();
    }

    public SessionCommandGroup(Collection<SessionCommand> collection) {
        HashSet hashSet = new HashSet();
        this.mCommands = hashSet;
        if (collection != null) {
            hashSet.addAll(collection);
        }
    }

    public boolean hasCommand(int i) {
        if (i != 0) {
            for (SessionCommand commandCode : this.mCommands) {
                if (commandCode.getCommandCode() == i) {
                    return true;
                }
            }
            return false;
        }
        throw new IllegalArgumentException("Use hasCommand(Command) for custom command");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionCommandGroup)) {
            return false;
        }
        SessionCommandGroup sessionCommandGroup = (SessionCommandGroup) obj;
        Set<SessionCommand> set = this.mCommands;
        if (set != null) {
            return set.equals(sessionCommandGroup.mCommands);
        }
        if (sessionCommandGroup.mCommands == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ObjectsCompat.hashCode(this.mCommands);
    }

    public static final class Builder {
        private Set<SessionCommand> mCommands = new HashSet();

        public Builder addCommand(SessionCommand sessionCommand) {
            if (sessionCommand != null) {
                this.mCommands.add(sessionCommand);
                return this;
            }
            throw new NullPointerException("command shouldn't be null");
        }

        public Builder addAllPredefinedCommands(int i) {
            if (i == 1) {
                addAllPlayerCommands(i, true);
                addAllVolumeCommands(i);
                addAllSessionCommands(i);
                addAllLibraryCommands(i);
                return this;
            }
            throw new IllegalArgumentException("Unknown command version " + i);
        }

        /* access modifiers changed from: package-private */
        public Builder addAllPlayerCommands(int i, boolean z) {
            addAllPlayerBasicCommands(i);
            addAllPlayerPlaylistCommands(i);
            if (z) {
                addAllPlayerHiddenCommands(i);
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllPlayerBasicCommands(int i) {
            addCommands(i, SessionCommand.VERSION_PLAYER_BASIC_COMMANDS_MAP);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllPlayerPlaylistCommands(int i) {
            addCommands(i, SessionCommand.VERSION_PLAYER_PLAYLIST_COMMANDS_MAP);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllPlayerHiddenCommands(int i) {
            addCommands(i, SessionCommand.VERSION_PLAYER_HIDDEN_COMMANDS_MAP);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllVolumeCommands(int i) {
            addCommands(i, SessionCommand.VERSION_VOLUME_COMMANDS_MAP);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllSessionCommands(int i) {
            addCommands(i, SessionCommand.VERSION_SESSION_COMMANDS_MAP);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllLibraryCommands(int i) {
            addCommands(i, SessionCommand.VERSION_LIBRARY_COMMANDS_MAP);
            return this;
        }

        private void addCommands(int i, ArrayMap<Integer, SessionCommand.Range> arrayMap) {
            for (int i2 = 1; i2 <= i; i2++) {
                SessionCommand.Range range = arrayMap.get(Integer.valueOf(i2));
                for (int i3 = range.lower; i3 <= range.upper; i3++) {
                    addCommand(new SessionCommand(i3));
                }
            }
        }

        public SessionCommandGroup build() {
            return new SessionCommandGroup(this.mCommands);
        }
    }
}
