package androidx.media2.common;

import androidx.versionedparcelable.VersionedParcel;

public final class UriMediaItemParcelizer {
    public static UriMediaItem read(VersionedParcel versionedParcel) {
        UriMediaItem uriMediaItem = new UriMediaItem();
        uriMediaItem.mMetadata = (MediaMetadata) versionedParcel.readVersionedParcelable(uriMediaItem.mMetadata, 1);
        uriMediaItem.mStartPositionMs = versionedParcel.readLong(uriMediaItem.mStartPositionMs, 2);
        uriMediaItem.mEndPositionMs = versionedParcel.readLong(uriMediaItem.mEndPositionMs, 3);
        uriMediaItem.onPostParceling();
        return uriMediaItem;
    }

    public static void write(UriMediaItem uriMediaItem, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        uriMediaItem.onPreParceling(versionedParcel.isStream());
        versionedParcel.writeVersionedParcelable(uriMediaItem.mMetadata, 1);
        versionedParcel.writeLong(uriMediaItem.mStartPositionMs, 2);
        versionedParcel.writeLong(uriMediaItem.mEndPositionMs, 3);
    }
}
