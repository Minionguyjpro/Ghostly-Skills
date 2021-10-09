package androidx.media2.common;

import androidx.versionedparcelable.VersionedParcel;

public final class MediaMetadataParcelizer {
    public static MediaMetadata read(VersionedParcel versionedParcel) {
        MediaMetadata mediaMetadata = new MediaMetadata();
        mediaMetadata.mBundle = versionedParcel.readBundle(mediaMetadata.mBundle, 1);
        mediaMetadata.mBitmapListSlice = (ParcelImplListSlice) versionedParcel.readParcelable(mediaMetadata.mBitmapListSlice, 2);
        mediaMetadata.onPostParceling();
        return mediaMetadata;
    }

    public static void write(MediaMetadata mediaMetadata, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        mediaMetadata.onPreParceling(versionedParcel.isStream());
        versionedParcel.writeBundle(mediaMetadata.mBundle, 1);
        versionedParcel.writeParcelable(mediaMetadata.mBitmapListSlice, 2);
    }
}
