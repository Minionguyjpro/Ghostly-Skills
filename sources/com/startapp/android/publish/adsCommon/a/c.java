package com.startapp.android.publish.adsCommon.a;

import com.startapp.common.c.e;
import java.io.Serializable;
import java.util.List;

@e(a = "type", b = "com.startapp.android.publish.adsCommon.adrules")
/* compiled from: StartAppSDK */
public abstract class c implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a  reason: collision with root package name */
    private transient boolean f188a;

    public abstract boolean a(List<a> list);

    public boolean a() {
        return this.f188a;
    }
}
