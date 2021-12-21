package com.truenet.android.a;

import a.a.b.b.h;
import android.content.Context;
import android.net.NetworkInfo;

/* compiled from: StartAppSDK */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    private final NetworkInfo f667a;
    private final boolean b;
    private final boolean c;
    private final boolean d;
    private final String e;
    private final Context f;

    public e(Context context) {
        NetworkInfo networkInfo;
        String typeName;
        NetworkInfo networkInfo2;
        h.b(context, "context");
        this.f = context;
        NetworkInfo activeNetworkInfo = h.a(context, "android.permission.ACCESS_NETWORK_STATE") ? d.a(this.f).getActiveNetworkInfo() : null;
        this.f667a = activeNetworkInfo;
        boolean z = false;
        boolean isConnected = activeNetworkInfo != null ? activeNetworkInfo.isConnected() : false;
        this.b = isConnected;
        NetworkInfo networkInfo3 = this.f667a;
        this.c = networkInfo3 != null && isConnected && networkInfo3.getType() == 1;
        NetworkInfo networkInfo4 = this.f667a;
        if (networkInfo4 != null && this.b && networkInfo4.getType() == 0) {
            z = true;
        }
        this.d = z;
        String str = "";
        if (!z ? !(!this.c || (networkInfo = this.f667a) == null || (typeName = networkInfo.getTypeName()) == null) : !((networkInfo2 = this.f667a) == null || (typeName = networkInfo2.getSubtypeName()) == null)) {
            str = typeName;
        }
        this.e = str;
    }

    public final String a() {
        return this.e;
    }
}
