package com.mopub.mobileads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import com.mopub.mobileads.VastTrackerTwo;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: VastAbsoluteProgressTrackerTwo.kt */
public class VastAbsoluteProgressTrackerTwo extends VastTrackerTwo implements Comparable<VastAbsoluteProgressTrackerTwo> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pattern absolutePattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}(.\\d{3})?");
    private static final long serialVersionUID = 1;
    @SerializedName("tracking_ms")
    @Expose
    private final int trackingMilliseconds;

    public final int getTrackingMilliseconds() {
        return this.trackingMilliseconds;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VastAbsoluteProgressTrackerTwo(int i, String str, VastTrackerTwo.MessageType messageType, boolean z) {
        super(str, messageType, z);
        Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
        Intrinsics.checkParameterIsNotNull(messageType, "messageType");
        this.trackingMilliseconds = i;
    }

    /* compiled from: VastAbsoluteProgressTrackerTwo.kt */
    public static final class Builder {
        private final String content;
        private boolean isRepeatable;
        private VastTrackerTwo.MessageType messageType = VastTrackerTwo.MessageType.TRACKING_URL;
        private final int trackingMilliseconds;

        private final String component1() {
            return this.content;
        }

        private final int component2() {
            return this.trackingMilliseconds;
        }

        public static /* synthetic */ Builder copy$default(Builder builder, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = builder.content;
            }
            if ((i2 & 2) != 0) {
                i = builder.trackingMilliseconds;
            }
            return builder.copy(str, i);
        }

        public final Builder copy(String str, int i) {
            Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
            return new Builder(str, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) obj;
            return Intrinsics.areEqual(this.content, builder.content) && this.trackingMilliseconds == builder.trackingMilliseconds;
        }

        public int hashCode() {
            String str = this.content;
            return ((str != null ? str.hashCode() : 0) * 31) + this.trackingMilliseconds;
        }

        public String toString() {
            return "Builder(content=" + this.content + ", trackingMilliseconds=" + this.trackingMilliseconds + ")";
        }

        public Builder(String str, int i) {
            Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
            this.content = str;
            this.trackingMilliseconds = i;
        }

        public final Builder messageType(VastTrackerTwo.MessageType messageType2) {
            Intrinsics.checkParameterIsNotNull(messageType2, "messageType");
            Builder builder = this;
            builder.messageType = messageType2;
            return builder;
        }

        public final Builder isRepeatable(boolean z) {
            Builder builder = this;
            builder.isRepeatable = z;
            return builder;
        }

        public final VastAbsoluteProgressTrackerTwo build() {
            return new VastAbsoluteProgressTrackerTwo(this.trackingMilliseconds, this.content, this.messageType, this.isRepeatable);
        }
    }

    /* compiled from: VastAbsoluteProgressTrackerTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isAbsoluteTracker(String str) {
            CharSequence charSequence = str;
            if ((charSequence == null || charSequence.length() == 0) || !VastAbsoluteProgressTrackerTwo.absolutePattern.matcher(charSequence).matches()) {
                return false;
            }
            return true;
        }

        public final Integer parseAbsoluteOffset(String str) {
            List split$default;
            if (str == null || (split$default = StringsKt.split$default(str, new String[]{":"}, false, 0, 6, (Object) null)) == null) {
                return null;
            }
            if (!(split$default.size() == 3)) {
                split$default = null;
            }
            if (split$default == null) {
                return null;
            }
            Integer.parseInt((String) split$default.get(0));
            Integer.parseInt((String) split$default.get(1));
            return Integer.valueOf((int) (Float.parseFloat((String) split$default.get(2)) * ((float) 1000)));
        }
    }

    public int compareTo(VastAbsoluteProgressTrackerTwo vastAbsoluteProgressTrackerTwo) {
        Intrinsics.checkParameterIsNotNull(vastAbsoluteProgressTrackerTwo, "other");
        return Intrinsics.compare(this.trackingMilliseconds, vastAbsoluteProgressTrackerTwo.trackingMilliseconds);
    }

    public String toString() {
        return this.trackingMilliseconds + "ms: " + getContent();
    }
}
