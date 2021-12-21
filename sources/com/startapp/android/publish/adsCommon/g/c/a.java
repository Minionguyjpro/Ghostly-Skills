package com.startapp.android.publish.adsCommon.g.c;

import com.appnext.core.Ad;
import java.util.Arrays;
import java.util.List;

/* compiled from: StartAppSDK */
public final class a {
    private static final List<String> c = Arrays.asList(new String[]{Ad.ORIENTATION_PORTRAIT, Ad.ORIENTATION_LANDSCAPE, "none"});

    /* renamed from: a  reason: collision with root package name */
    public boolean f251a;
    public int b;

    public a() {
        this(true, 2);
    }

    public a(boolean z, int i) {
        this.f251a = z;
        this.b = i;
    }

    public static int a(String str) {
        int indexOf = c.indexOf(str);
        if (indexOf != -1) {
            return indexOf;
        }
        return 2;
    }
}
