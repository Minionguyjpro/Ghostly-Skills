package androidx.media2.common;

import androidx.media2.common.SessionPlayer;
import androidx.versionedparcelable.VersionedParcel;

public final class TrackInfoParcelizer {
    public static SessionPlayer.TrackInfo read(VersionedParcel versionedParcel) {
        SessionPlayer.TrackInfo trackInfo = new SessionPlayer.TrackInfo();
        trackInfo.mId = versionedParcel.readInt(trackInfo.mId, 1);
        trackInfo.mUpCastMediaItem = (MediaItem) versionedParcel.readVersionedParcelable(trackInfo.mUpCastMediaItem, 2);
        trackInfo.mTrackType = versionedParcel.readInt(trackInfo.mTrackType, 3);
        trackInfo.mParcelledFormat = versionedParcel.readBundle(trackInfo.mParcelledFormat, 4);
        trackInfo.onPostParceling();
        return trackInfo;
    }

    public static void write(SessionPlayer.TrackInfo trackInfo, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        trackInfo.onPreParceling(versionedParcel.isStream());
        versionedParcel.writeInt(trackInfo.mId, 1);
        versionedParcel.writeVersionedParcelable(trackInfo.mUpCastMediaItem, 2);
        versionedParcel.writeInt(trackInfo.mTrackType, 3);
        versionedParcel.writeBundle(trackInfo.mParcelledFormat, 4);
    }
}
