package com.tappx.a;

public class b3 {

    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f379a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.tappx.a.t1[] r0 = com.tappx.a.t1.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f379a = r0
                com.tappx.a.t1 r1 = com.tappx.a.t1.LEFT_TO_RIGHT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f379a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.t1 r1 = com.tappx.a.t1.LEFT_TO_RIGHT_BOUNCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f379a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.t1 r1 = com.tappx.a.t1.RANDOM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f379a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.t1 r1 = com.tappx.a.t1.RIGHT_TO_LEFT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f379a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.tappx.a.t1 r1 = com.tappx.a.t1.RIGHT_TO_LEFT_BOUNCE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f379a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.tappx.a.t1 r1 = com.tappx.a.t1.NONE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.b3.a.<clinit>():void");
        }
    }

    public static k3 a(t1 t1Var) {
        if (t1Var == null) {
            t1Var = t1.NONE;
        }
        int i = a.f379a[t1Var.ordinal()];
        if (i == 1) {
            return k3.FROM_LEFT;
        }
        if (i == 2) {
            return k3.FROM_LEFT_BOUNCE;
        }
        if (i == 3) {
            return k3.RANDOM;
        }
        if (i == 4) {
            return k3.FROM_RIGHT;
        }
        if (i != 5) {
            return k3.NONE;
        }
        return k3.FROM_RIGHT_BOUNCE;
    }
}
