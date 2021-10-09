package com.appnext.base;

public final class a {
    private int ds;

    /* renamed from: com.appnext.base.a$a  reason: collision with other inner class name */
    public enum C0036a {
        ;
        
        public static final int Internal$1d8b5b4a = 1;
        public static final int NoInternet$1d8b5b4a = 2;
        public static final int NoPermission$1d8b5b4a = 3;

        static {
            $VALUES$47a19cef = new int[]{1, 2, 3};
        }

        public static int[] W() {
            return (int[]) $VALUES$47a19cef.clone();
        }
    }

    public a(int i) {
        this.ds = i;
    }

    public final int V() {
        return this.ds;
    }
}
