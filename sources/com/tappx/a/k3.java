package com.tappx.a;

public enum k3 {
    NONE,
    FROM_RIGHT,
    FROM_LEFT,
    FROM_LEFT_BOUNCE,
    FROM_RIGHT_BOUNCE,
    RANDOM;

    public static k3 a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(k3 k3Var) {
        if (k3Var == null) {
            return null;
        }
        return k3Var.name();
    }
}
