package androidx.media2.common;

import androidx.versionedparcelable.VersionedParcel;

public final class SubtitleDataParcelizer {
    public static SubtitleData read(VersionedParcel versionedParcel) {
        SubtitleData subtitleData = new SubtitleData();
        subtitleData.mStartTimeUs = versionedParcel.readLong(subtitleData.mStartTimeUs, 1);
        subtitleData.mDurationUs = versionedParcel.readLong(subtitleData.mDurationUs, 2);
        subtitleData.mData = versionedParcel.readByteArray(subtitleData.mData, 3);
        return subtitleData;
    }

    public static void write(SubtitleData subtitleData, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeLong(subtitleData.mStartTimeUs, 1);
        versionedParcel.writeLong(subtitleData.mDurationUs, 2);
        versionedParcel.writeByteArray(subtitleData.mData, 3);
    }
}
