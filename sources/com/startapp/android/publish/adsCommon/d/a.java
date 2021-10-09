package com.startapp.android.publish.adsCommon.d;

/* compiled from: StartAppSDK */
public class a extends b {
    private static final long serialVersionUID = 1;
    private final String DURATION_PARAM_NAME = "&displayDuration=";
    private String duration;

    public a(String str, String str2) {
        super(str2);
        this.duration = str;
    }

    public String getQueryString() {
        return super.getQueryString() + "&displayDuration=" + encode(this.duration);
    }
}
