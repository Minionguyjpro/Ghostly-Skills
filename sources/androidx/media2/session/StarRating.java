package androidx.media2.session;

import androidx.core.util.ObjectsCompat;
import androidx.media2.common.Rating;

public final class StarRating implements Rating {
    int mMaxStars;
    float mStarRating;

    StarRating() {
    }

    public boolean isRated() {
        return this.mStarRating >= 0.0f;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Integer.valueOf(this.mMaxStars), Float.valueOf(this.mStarRating));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StarRating)) {
            return false;
        }
        StarRating starRating = (StarRating) obj;
        if (this.mMaxStars == starRating.mMaxStars && this.mStarRating == starRating.mStarRating) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("StarRating: maxStars=");
        sb.append(this.mMaxStars);
        if (isRated()) {
            str = ", starRating=" + this.mStarRating;
        } else {
            str = ", unrated";
        }
        sb.append(str);
        return sb.toString();
    }
}
