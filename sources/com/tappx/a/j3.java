package com.tappx.a;

public enum j3 {
    ANY,
    PORTRAIT,
    LANDSCAPE;

    public static j3 a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str);
        } catch (Exception unused) {
            return ANY;
        }
    }

    public static String a(j3 j3Var) {
        if (j3Var == null) {
            return null;
        }
        return j3Var.name();
    }
}
