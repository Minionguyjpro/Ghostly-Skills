package androidx.media2.common;

import androidx.versionedparcelable.VersionedParcel;

public final class FileMediaItemParcelizer {
    public static FileMediaItem read(VersionedParcel versionedParcel) {
        FileMediaItem fileMediaItem = new FileMediaItem();
        fileMediaItem.mMetadata = (MediaMetadata) versionedParcel.readVersionedParcelable(fileMediaItem.mMetadata, 1);
        fileMediaItem.mStartPositionMs = versionedParcel.readLong(fileMediaItem.mStartPositionMs, 2);
        fileMediaItem.mEndPositionMs = versionedParcel.readLong(fileMediaItem.mEndPositionMs, 3);
        fileMediaItem.onPostParceling();
        return fileMediaItem;
    }

    public static void write(FileMediaItem fileMediaItem, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        fileMediaItem.onPreParceling(versionedParcel.isStream());
        versionedParcel.writeVersionedParcelable(fileMediaItem.mMetadata, 1);
        versionedParcel.writeLong(fileMediaItem.mStartPositionMs, 2);
        versionedParcel.writeLong(fileMediaItem.mEndPositionMs, 3);
    }
}
