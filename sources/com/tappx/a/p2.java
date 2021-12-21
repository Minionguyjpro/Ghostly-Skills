package com.tappx.a;

public enum p2 {
    GRANTED_DEVELOPER,
    DENIED_DEVELOPER,
    GRANTED_USER,
    DENIED_USER,
    MISSING_ANSWER;

    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f550a = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.tappx.a.p2[] r0 = com.tappx.a.p2.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f550a = r0
                com.tappx.a.p2 r1 = com.tappx.a.p2.GRANTED_USER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f550a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.p2 r1 = com.tappx.a.p2.DENIED_USER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f550a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.p2 r1 = com.tappx.a.p2.GRANTED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f550a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.p2 r1 = com.tappx.a.p2.DENIED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.p2.a.<clinit>():void");
        }
    }

    public boolean a() {
        int i = a.f550a[ordinal()];
        return i == 2 || i == 4;
    }

    public boolean b() {
        int i = a.f550a[ordinal()];
        return i == 1 || i == 2;
    }

    public boolean c() {
        int i = a.f550a[ordinal()];
        return i == 1 || i == 3;
    }
}
