package androidx.media2.session;

import androidx.versionedparcelable.VersionedParcel;

public final class PercentageRatingParcelizer {
    public static PercentageRating read(VersionedParcel versionedParcel) {
        PercentageRating percentageRating = new PercentageRating();
        percentageRating.mPercent = versionedParcel.readFloat(percentageRating.mPercent, 1);
        return percentageRating;
    }

    public static void write(PercentageRating percentageRating, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeFloat(percentageRating.mPercent, 1);
    }
}
