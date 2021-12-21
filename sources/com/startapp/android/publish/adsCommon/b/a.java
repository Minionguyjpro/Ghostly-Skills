package com.startapp.android.publish.adsCommon.b;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class a implements Serializable {
    private static final long serialVersionUID = 1;
    private int adAttempt = 0;
    private boolean appPresence = false;
    private boolean isShown = true;
    private int minAppVersion = 0;
    private String packageName;
    private String trackingUrl;

    public a(String str, String str2, int i, int i2) {
        this.trackingUrl = str;
        this.packageName = str2;
        this.adAttempt = i;
        this.minAppVersion = i2;
    }

    public String a() {
        return this.trackingUrl;
    }

    public void a(String str) {
        this.trackingUrl = str;
    }

    public String b() {
        return this.packageName;
    }

    public boolean c() {
        return this.isShown;
    }

    public void a(boolean z) {
        this.isShown = z;
    }

    public boolean d() {
        return this.appPresence;
    }

    public void b(boolean z) {
        this.appPresence = z;
    }

    public int e() {
        return this.minAppVersion;
    }
}
