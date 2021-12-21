package com.mopub.mobileads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastTrackerTwo.kt */
public class VastTrackerTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 3;
    @SerializedName("content")
    @Expose
    private final String content;
    @SerializedName("is_repeatable")
    @Expose
    private final boolean isRepeatable;
    private boolean isTracked;
    @SerializedName("message_type")
    @Expose
    private final MessageType messageType;

    /* compiled from: VastTrackerTwo.kt */
    public enum MessageType {
        TRACKING_URL,
        QUARTILE_EVENT
    }

    public VastTrackerTwo(String str, MessageType messageType2, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
        Intrinsics.checkParameterIsNotNull(messageType2, "messageType");
        this.content = str;
        this.messageType = messageType2;
        this.isRepeatable = z;
    }

    public final String getContent() {
        return this.content;
    }

    public final MessageType getMessageType() {
        return this.messageType;
    }

    public final boolean isRepeatable() {
        return this.isRepeatable;
    }

    /* compiled from: VastTrackerTwo.kt */
    public static final class Builder {
        private final String content;
        private boolean isRepeatable;
        private MessageType messageType = MessageType.TRACKING_URL;

        private final String component1() {
            return this.content;
        }

        public static /* synthetic */ Builder copy$default(Builder builder, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = builder.content;
            }
            return builder.copy(str);
        }

        public final Builder copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
            return new Builder(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof Builder) && Intrinsics.areEqual(this.content, ((Builder) obj).content);
            }
            return true;
        }

        public int hashCode() {
            String str = this.content;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Builder(content=" + this.content + ")";
        }

        public Builder(String str) {
            Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
            this.content = str;
        }

        public final Builder messageType(MessageType messageType2) {
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

        public final VastTrackerTwo build() {
            return new VastTrackerTwo(this.content, this.messageType, this.isRepeatable);
        }
    }

    /* compiled from: VastTrackerTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final boolean isTracked() {
        return this.isTracked;
    }

    public final void setTracked() {
        this.isTracked = true;
    }
}
