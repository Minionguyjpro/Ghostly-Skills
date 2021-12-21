package com.startapp.android.publish.ads.video;

import com.startapp.android.publish.ads.video.c.a.e;
import com.startapp.android.publish.ads.video.tracking.VideoTrackingDetails;
import com.startapp.android.publish.omsdk.AdVerification;
import com.startapp.android.publish.omsdk.VerificationDetails;
import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class VideoAdDetails implements Serializable {
    private static final long serialVersionUID = 1;
    @f(b = VerificationDetails.class, f = "adVerifications")
    private VerificationDetails[] adVerifications;
    private String clickUrl;
    private boolean clickable;
    private boolean closeable;
    private boolean isVideoMuted;
    private String localVideoPath;
    @f(b = PostRollType.class)
    private PostRollType postRoll;
    private boolean skippable;
    private int skippableAfter;
    @f(a = true)
    private VideoTrackingDetails videoTrackingDetails;
    private String videoUrl;

    /* compiled from: StartAppSDK */
    public enum PostRollType {
        IMAGE,
        LAST_FRAME,
        NONE
    }

    public VideoAdDetails() {
    }

    public VideoAdDetails(e eVar, boolean z) {
        if (eVar != null) {
            this.videoTrackingDetails = new VideoTrackingDetails(eVar);
            if (eVar.g() != null) {
                this.videoUrl = eVar.g().a();
            }
            boolean z2 = true;
            if (z) {
                int f = eVar.f();
                this.skippableAfter = f;
                this.skippable = f != Integer.MAX_VALUE;
            } else {
                this.skippable = false;
            }
            String a2 = eVar.c().a();
            this.clickUrl = a2;
            this.clickable = a2 == null ? false : z2;
            this.postRoll = PostRollType.NONE;
            setAdVerifications(eVar.h());
        }
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public String getLocalVideoPath() {
        return this.localVideoPath;
    }

    public void setLocalVideoPath(String str) {
        this.localVideoPath = str;
    }

    public PostRollType getPostRollType() {
        return this.postRoll;
    }

    public boolean isCloseable() {
        return this.closeable;
    }

    public boolean isSkippable() {
        return this.skippable;
    }

    public int getSkippableAfter() {
        return this.skippableAfter;
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public VideoTrackingDetails getVideoTrackingDetails() {
        return this.videoTrackingDetails;
    }

    public boolean isVideoMuted() {
        return this.isVideoMuted;
    }

    public void setVideoMuted(boolean z) {
        this.isVideoMuted = z;
    }

    public String getClickUrl() {
        return this.clickUrl;
    }

    public AdVerification getAdVerifications() {
        return new AdVerification(this.adVerifications);
    }

    public void setAdVerifications(AdVerification adVerification) {
        if (adVerification != null && adVerification.getAdVerification() != null) {
            this.adVerifications = (VerificationDetails[]) adVerification.getAdVerification().toArray(new VerificationDetails[adVerification.getAdVerification().size()]);
        }
    }

    public String toString() {
        return "VideoAdDetails [videoUrl=" + this.videoUrl + ", localVideoPath=" + this.localVideoPath + ", postRoll=" + this.postRoll + ", closeable=" + this.closeable + ", skippable=" + this.skippable + ", skippableAfter=" + this.skippableAfter + ", videoTrackingDetails= " + this.videoTrackingDetails + ", isVideoMuted= " + this.isVideoMuted + "]";
    }
}
