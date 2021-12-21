package com.startapp.android.mediation.admob;

public class StartAppExtras {
    private String adTag;

    protected StartAppExtras() {
    }

    public String getAdTag() {
        return this.adTag;
    }

    public StartAppExtras setAdTag(String str) {
        this.adTag = str;
        return this;
    }
}
