package com.startapp.android.publish.common.metaData;

import android.content.Context;
import com.startapp.android.publish.adsCommon.k;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class h implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean enabled = false;

    public boolean a() {
        return this.enabled;
    }

    public boolean a(Context context) {
        return k.a(context, "userDisabledSimpleToken", (Boolean) false).booleanValue();
    }

    public boolean b(Context context) {
        return !a(context) && a();
    }

    public void a(Context context, boolean z) {
        k.b(context, "userDisabledSimpleToken", Boolean.valueOf(!z));
    }
}
