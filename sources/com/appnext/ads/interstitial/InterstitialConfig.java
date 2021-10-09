package com.appnext.ads.interstitial;

import android.graphics.Color;
import com.appnext.core.Configuration;
import com.appnext.core.p;
import java.io.Serializable;

public class InterstitialConfig extends Configuration implements Serializable {
    private static final long serialVersionUID = 1;
    public Boolean autoPlay;
    public String buttonColor = "";
    protected boolean cm = false;
    public String creativeType = Interstitial.TYPE_MANAGED;
    public String skipText = "";

    @Deprecated
    public void setOrientation(String str) {
    }

    public void setCreativeType(String str) {
        if (str.equals(Interstitial.TYPE_MANAGED) || str.equals("static") || str.equals("video")) {
            this.creativeType = str;
            return;
        }
        throw new IllegalArgumentException("Wrong creative type");
    }

    public String getCreativeType() {
        return this.creativeType;
    }

    /* access modifiers changed from: protected */
    public final p l() {
        return c.K();
    }

    public boolean isAutoPlay() {
        Boolean bool = this.autoPlay;
        return bool == null ? Boolean.parseBoolean(c.K().get("auto_play")) : bool.booleanValue();
    }

    public void setAutoPlay(boolean z) {
        this.autoPlay = Boolean.valueOf(z);
    }

    public void setSkipText(String str) {
        if (str == null) {
            str = "";
        }
        this.skipText = str;
    }

    public String getSkipText() {
        return this.skipText;
    }

    /* access modifiers changed from: protected */
    public final boolean t() {
        return this.mute != null;
    }

    /* access modifiers changed from: protected */
    public final boolean H() {
        return this.autoPlay != null;
    }

    /* access modifiers changed from: protected */
    public final boolean I() {
        return this.backButtonCanClose != null;
    }

    public void setButtonColor(String str) {
        if (str == null) {
            this.buttonColor = "";
            return;
        }
        if (!str.startsWith("#")) {
            str = "#" + str;
        }
        try {
            Color.parseColor(str);
            this.buttonColor = str;
            this.cm = true;
        } catch (Throwable unused) {
            throw new IllegalArgumentException("Unknown color");
        }
    }

    public String getButtonColor() {
        return this.buttonColor;
    }

    @Deprecated
    public void setBackButtonCanClose(boolean z) {
        this.backButtonCanClose = Boolean.valueOf(z);
    }
}
