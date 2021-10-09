package com.mopub.mobileads;

import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.VastTracker;
import java.io.Serializable;
import java.util.Locale;
import java.util.regex.Pattern;

public class VastFractionalProgressTracker extends VastTracker implements Serializable, Comparable<VastFractionalProgressTracker> {
    private static Pattern percentagePattern = Pattern.compile("((\\d{1,2})|(100))%");
    private static final long serialVersionUID = 0;
    @SerializedName("tracking_fraction")
    @Expose
    private final float mFraction;

    public VastFractionalProgressTracker(VastTracker.MessageType messageType, String str, float f) {
        super(messageType, str);
        Preconditions.checkArgument(f >= 0.0f);
        this.mFraction = f;
    }

    public VastFractionalProgressTracker(String str, float f) {
        this(VastTracker.MessageType.TRACKING_URL, str, f);
    }

    public float trackingFraction() {
        return this.mFraction;
    }

    public int compareTo(VastFractionalProgressTracker vastFractionalProgressTracker) {
        return Double.compare((double) trackingFraction(), (double) vastFractionalProgressTracker.trackingFraction());
    }

    public String toString() {
        return String.format(Locale.US, "%2f: %s", new Object[]{Float.valueOf(this.mFraction), getContent()});
    }

    public static boolean isPercentageTracker(String str) {
        Preconditions.checkNotNull(str);
        return !TextUtils.isEmpty(str) && percentagePattern.matcher(str).matches();
    }

    public static int parsePercentageOffset(String str, int i) {
        Preconditions.checkNotNull(str);
        return Math.round((((float) i) * Float.parseFloat(str.replace("%", ""))) / 100.0f);
    }
}
