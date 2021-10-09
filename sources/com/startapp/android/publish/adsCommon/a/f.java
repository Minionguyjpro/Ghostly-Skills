package com.startapp.android.publish.adsCommon.a;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class f implements Serializable {
    private static final long serialVersionUID = 1;
    private String reason;
    private boolean shouldDisplayAd;

    public f(boolean z, String str) {
        this.shouldDisplayAd = z;
        this.reason = str;
    }

    public f(boolean z) {
        this(z, "");
    }

    public boolean a() {
        return this.shouldDisplayAd;
    }

    public String b() {
        return this.reason;
    }

    public String c() {
        String str = this.reason;
        return str != null ? str.split(" ")[0] : "";
    }
}
