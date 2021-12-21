package com.mopub.mobileads;

import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.VastTracker;
import java.io.Serializable;
import java.util.Locale;
import java.util.regex.Pattern;

public class VastAbsoluteProgressTracker extends VastTracker implements Serializable, Comparable<VastAbsoluteProgressTracker> {
    private static Pattern absolutePattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}(.\\d{3})?");
    private static final long serialVersionUID = 0;
    @SerializedName("tracking_ms")
    @Expose
    private final int mTrackingMilliseconds;

    public VastAbsoluteProgressTracker(VastTracker.MessageType messageType, String str, int i) {
        super(messageType, str);
        Preconditions.checkArgument(i >= 0);
        this.mTrackingMilliseconds = i;
    }

    public VastAbsoluteProgressTracker(String str, int i) {
        this(VastTracker.MessageType.TRACKING_URL, str, i);
    }

    public int getTrackingMilliseconds() {
        return this.mTrackingMilliseconds;
    }

    public int compareTo(VastAbsoluteProgressTracker vastAbsoluteProgressTracker) {
        return getTrackingMilliseconds() - vastAbsoluteProgressTracker.getTrackingMilliseconds();
    }

    public String toString() {
        return String.format(Locale.US, "%dms: %s", new Object[]{Integer.valueOf(this.mTrackingMilliseconds), getContent()});
    }

    public static boolean isAbsoluteTracker(String str) {
        return !TextUtils.isEmpty(str) && absolutePattern.matcher(str).matches();
    }

    public static Integer parseAbsoluteOffset(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length != 3) {
            return null;
        }
        return Integer.valueOf((Integer.parseInt(split[0]) * 60 * 60 * 1000) + (Integer.parseInt(split[1]) * 60 * 1000) + ((int) (Float.parseFloat(split[2]) * 1000.0f)));
    }
}
