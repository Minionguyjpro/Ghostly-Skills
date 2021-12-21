package androidx.media2.session;

import androidx.versionedparcelable.VersionedParcel;

public final class StarRatingParcelizer {
    public static StarRating read(VersionedParcel versionedParcel) {
        StarRating starRating = new StarRating();
        starRating.mMaxStars = versionedParcel.readInt(starRating.mMaxStars, 1);
        starRating.mStarRating = versionedParcel.readFloat(starRating.mStarRating, 2);
        return starRating;
    }

    public static void write(StarRating starRating, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeInt(starRating.mMaxStars, 1);
        versionedParcel.writeFloat(starRating.mStarRating, 2);
    }
}
