package com.startapp.android.publish.ads.video.tracking;

import com.startapp.common.c.e;
import java.io.Serializable;

@e(c = true)
/* compiled from: StartAppSDK */
public class FractionTrackingLink extends VideoTrackingLink implements Serializable {
    private static final long serialVersionUID = 1;
    private int fraction;

    public int getFraction() {
        return this.fraction;
    }

    public void setFraction(int i) {
        this.fraction = i;
    }

    public String toString() {
        return super.toString() + ", fraction=" + this.fraction;
    }
}
