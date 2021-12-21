package com.mopub.mobileads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import com.mopub.mobileads.VastTrackerTwo;
import java.util.regex.Pattern;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: VastFractionalProgressTrackerTwo.kt */
public final class VastFractionalProgressTrackerTwo extends VastTrackerTwo implements Comparable<VastFractionalProgressTrackerTwo> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pattern percentagePattern = Pattern.compile("((\\d{1,2})|(100))%");
    private static final long serialVersionUID = 1;
    @SerializedName("tracking_fraction")
    @Expose
    private final float trackingFraction;

    public final float getTrackingFraction() {
        return this.trackingFraction;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VastFractionalProgressTrackerTwo(float f, String str, VastTrackerTwo.MessageType messageType, boolean z) {
        super(str, messageType, z);
        Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
        Intrinsics.checkParameterIsNotNull(messageType, "messageType");
        this.trackingFraction = f;
    }

    /* compiled from: VastFractionalProgressTrackerTwo.kt */
    public static final class Builder {
        private final String content;
        private boolean isRepeatable;
        private VastTrackerTwo.MessageType messageType = VastTrackerTwo.MessageType.TRACKING_URL;
        private final float trackingFraction;

        private final String component1() {
            return this.content;
        }

        private final float component2() {
            return this.trackingFraction;
        }

        public static /* synthetic */ Builder copy$default(Builder builder, String str, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                str = builder.content;
            }
            if ((i & 2) != 0) {
                f = builder.trackingFraction;
            }
            return builder.copy(str, f);
        }

        public final Builder copy(String str, float f) {
            Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
            return new Builder(str, f);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) obj;
            return Intrinsics.areEqual(this.content, builder.content) && Float.compare(this.trackingFraction, builder.trackingFraction) == 0;
        }

        public int hashCode() {
            String str = this.content;
            return ((str != null ? str.hashCode() : 0) * 31) + Float.floatToIntBits(this.trackingFraction);
        }

        public String toString() {
            return "Builder(content=" + this.content + ", trackingFraction=" + this.trackingFraction + ")";
        }

        public Builder(String str, float f) {
            Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
            this.content = str;
            this.trackingFraction = f;
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

        public final VastFractionalProgressTrackerTwo build() {
            return new VastFractionalProgressTrackerTwo(this.trackingFraction, this.content, this.messageType, this.isRepeatable);
        }
    }

    /* compiled from: VastFractionalProgressTrackerTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isPercentageTracker(String str) {
            CharSequence charSequence = str;
            if ((charSequence == null || charSequence.length() == 0) || !VastFractionalProgressTrackerTwo.percentagePattern.matcher(charSequence).matches()) {
                return false;
            }
            return true;
        }

        public final Integer parsePercentageOffset(String str, int i) {
            String replace$default;
            if (str == null || (replace$default = StringsKt.replace$default(str, "%", "", false, 4, (Object) null)) == null) {
                return null;
            }
            return Integer.valueOf((int) ((float) Math.rint((double) ((((float) i) * Float.parseFloat(replace$default)) / 100.0f))));
        }
    }

    public int compareTo(VastFractionalProgressTrackerTwo vastFractionalProgressTrackerTwo) {
        Intrinsics.checkParameterIsNotNull(vastFractionalProgressTrackerTwo, "other");
        return Float.compare(this.trackingFraction, vastFractionalProgressTrackerTwo.trackingFraction);
    }

    public String toString() {
        return this.trackingFraction + ": " + getContent();
    }
}
