package com.startapp.android.publish.adsCommon.f;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class a implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f240a = "https://imp.startappservice.com/tracking/infoEvent";
    private static final long serialVersionUID = 1;
    private boolean dns = false;
    public String hostPeriodic;
    public String hostSecured;
    private int retryNum = 3;
    private int retryTime = 10;
    private boolean sendHopsOnFirstSucceededSmartRedirect = false;
    private float succeededSmartRedirectInfoProbability = 0.01f;

    public a() {
        String str = f240a;
        this.hostSecured = str;
        this.hostPeriodic = str;
    }

    public String a() {
        return this.hostSecured;
    }

    public String b() {
        String str = this.hostPeriodic;
        return str != null ? str : f240a;
    }

    public boolean c() {
        return this.dns;
    }

    public int d() {
        return this.retryNum;
    }

    public long e() {
        return TimeUnit.SECONDS.toMillis((long) this.retryTime);
    }

    public float f() {
        return this.succeededSmartRedirectInfoProbability;
    }

    public boolean g() {
        return this.sendHopsOnFirstSucceededSmartRedirect;
    }
}
