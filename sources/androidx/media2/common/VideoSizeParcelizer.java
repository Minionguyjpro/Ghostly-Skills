package androidx.media2.common;

import androidx.versionedparcelable.VersionedParcel;

public final class VideoSizeParcelizer {
    public static VideoSize read(VersionedParcel versionedParcel) {
        VideoSize videoSize = new VideoSize();
        videoSize.mWidth = versionedParcel.readInt(videoSize.mWidth, 1);
        videoSize.mHeight = versionedParcel.readInt(videoSize.mHeight, 2);
        return videoSize;
    }

    public static void write(VideoSize videoSize, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeInt(videoSize.mWidth, 1);
        versionedParcel.writeInt(videoSize.mHeight, 2);
    }
}
