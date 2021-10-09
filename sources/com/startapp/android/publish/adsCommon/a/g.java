package com.startapp.android.publish.adsCommon.a;

import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class g implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private static transient g f191a = new g();
    @f(a = true)
    private e adRules = new e();
    private String adaptMetaDataUpdateVersion = AdsConstants.h;

    private g() {
    }

    public static g a() {
        return f191a;
    }

    public e b() {
        return this.adRules;
    }
}
