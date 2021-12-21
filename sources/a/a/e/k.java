package a.a.e;

import a.a.b.b.h;

/* compiled from: StartAppSDK */
class k extends j {
    public static /* bridge */ /* synthetic */ boolean a(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return c.a(str, str2, z);
    }

    public static final boolean a(String str, String str2, boolean z) {
        h.b(str, "$receiver");
        h.b(str2, "prefix");
        if (!z) {
            return str.startsWith(str2);
        }
        return c.a(str, 0, str2, 0, str2.length(), z);
    }

    public static final boolean a(String str, int i, String str2, int i2, int i3, boolean z) {
        h.b(str, "$receiver");
        h.b(str2, "other");
        if (!z) {
            return str.regionMatches(i, str2, i2, i3);
        }
        return str.regionMatches(z, i, str2, i2, i3);
    }
}
