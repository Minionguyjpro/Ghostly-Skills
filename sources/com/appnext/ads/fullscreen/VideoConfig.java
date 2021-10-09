package com.appnext.ads.fullscreen;

import com.appnext.core.Configuration;
import com.appnext.core.p;
import java.io.Serializable;

public class VideoConfig extends Configuration implements Serializable {
    private static final long serialVersionUID = 1;
    public int rollCaptionTime = -2;
    public Boolean showCta;
    public String videoLength = "15";

    public String getVideoLength() {
        return this.videoLength;
    }

    public void setVideoLength(String str) {
        if (str.equals("15") || str.equals("30")) {
            this.videoLength = str;
            return;
        }
        throw new IllegalArgumentException("Wrong video length");
    }

    /* access modifiers changed from: protected */
    public p l() {
        return c.m();
    }

    /* access modifiers changed from: protected */
    public final boolean t() {
        return this.mute != null;
    }

    public int getRollCaptionTime() {
        return this.rollCaptionTime;
    }

    public void setRollCaptionTime(int i) {
        this.rollCaptionTime = i;
    }

    /* access modifiers changed from: protected */
    public final boolean u() {
        return this.showCta != null;
    }

    public boolean isShowCta() {
        Boolean bool = this.showCta;
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void setShowCta(boolean z) {
        this.showCta = Boolean.valueOf(z);
    }
}
