package androidx.media2.common;

import androidx.versionedparcelable.VersionedParcel;

public final class CallbackMediaItemParcelizer {
    public static CallbackMediaItem read(VersionedParcel versionedParcel) {
        CallbackMediaItem callbackMediaItem = new CallbackMediaItem();
        callbackMediaItem.mMetadata = (MediaMetadata) versionedParcel.readVersionedParcelable(callbackMediaItem.mMetadata, 1);
        callbackMediaItem.mStartPositionMs = versionedParcel.readLong(callbackMediaItem.mStartPositionMs, 2);
        callbackMediaItem.mEndPositionMs = versionedParcel.readLong(callbackMediaItem.mEndPositionMs, 3);
        callbackMediaItem.onPostParceling();
        return callbackMediaItem;
    }

    public static void write(CallbackMediaItem callbackMediaItem, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        callbackMediaItem.onPreParceling(versionedParcel.isStream());
        versionedParcel.writeVersionedParcelable(callbackMediaItem.mMetadata, 1);
        versionedParcel.writeLong(callbackMediaItem.mStartPositionMs, 2);
        versionedParcel.writeLong(callbackMediaItem.mEndPositionMs, 3);
    }
}
