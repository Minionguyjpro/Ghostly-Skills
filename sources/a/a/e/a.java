package a.a.e;

import a.a.b.b.h;
import java.nio.charset.Charset;

/* compiled from: StartAppSDK */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f971a;
    public static final Charset b;
    public static final Charset c;
    public static final Charset d;
    public static final Charset e;
    public static final Charset f;
    public static final a g = new a();

    static {
        Charset forName = Charset.forName("UTF-8");
        h.a((Object) forName, "Charset.forName(\"UTF-8\")");
        f971a = forName;
        Charset forName2 = Charset.forName("UTF-16");
        h.a((Object) forName2, "Charset.forName(\"UTF-16\")");
        b = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        h.a((Object) forName3, "Charset.forName(\"UTF-16BE\")");
        c = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        h.a((Object) forName4, "Charset.forName(\"UTF-16LE\")");
        d = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        h.a((Object) forName5, "Charset.forName(\"US-ASCII\")");
        e = forName5;
        Charset forName6 = Charset.forName("ISO-8859-1");
        h.a((Object) forName6, "Charset.forName(\"ISO-8859-1\")");
        f = forName6;
    }

    private a() {
    }
}
