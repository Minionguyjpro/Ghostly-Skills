package com.mopub.mobileads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Preconditions;
import java.io.Serializable;

public class VastTracker implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean mCalled;
    @SerializedName("content")
    @Expose
    private final String mContent;
    @SerializedName("is_repeatable")
    @Expose
    private boolean mIsRepeatable;
    @SerializedName("message_type")
    @Expose
    private final MessageType mMessageType;

    enum MessageType {
        TRACKING_URL,
        QUARTILE_EVENT
    }

    public VastTracker(MessageType messageType, String str) {
        Preconditions.checkNotNull(messageType);
        Preconditions.checkNotNull(str);
        this.mMessageType = messageType;
        this.mContent = str;
    }

    public VastTracker(String str) {
        this(MessageType.TRACKING_URL, str);
    }

    public VastTracker(String str, boolean z) {
        this(str);
        this.mIsRepeatable = z;
    }

    public MessageType getMessageType() {
        return this.mMessageType;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setTracked() {
        this.mCalled = true;
    }

    public boolean isTracked() {
        return this.mCalled;
    }

    public boolean isRepeatable() {
        return this.mIsRepeatable;
    }
}
