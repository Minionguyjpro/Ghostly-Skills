package com.startapp.android.publish.adsCommon.Utils;

import java.util.Set;

/* compiled from: StartAppSDK */
public abstract class e {
    public abstract void a(String str, Object obj, boolean z, boolean z2);

    public abstract void a(String str, Set<String> set, boolean z, boolean z2);

    public void a(String str, Object obj, boolean z) {
        a(str, obj, z, true);
    }

    public void a(String str, Set<String> set, boolean z) {
        a(str, set, z, true);
    }
}
