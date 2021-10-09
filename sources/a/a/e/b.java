package a.a.e;

import a.a.b.b.h;
import java.io.Serializable;
import java.util.regex.Pattern;

/* compiled from: StartAppSDK */
public final class b implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public static final a f972a = new a((e) null);
    private final Pattern nativePattern;

    public b(Pattern pattern) {
        h.b(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public b(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "pattern"
            a.a.b.b.h.b(r2, r0)
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.lang.String r0 = "Pattern.compile(pattern)"
            a.a.b.b.h.a((java.lang.Object) r2, (java.lang.String) r0)
            r1.<init>((java.util.regex.Pattern) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.e.b.<init>(java.lang.String):void");
    }

    public final boolean a(CharSequence charSequence) {
        h.b(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public String toString() {
        String pattern = this.nativePattern.toString();
        h.a((Object) pattern, "nativePattern.toString()");
        return pattern;
    }

    /* compiled from: StartAppSDK */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(e eVar) {
            this();
        }
    }
}
