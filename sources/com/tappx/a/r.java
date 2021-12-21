package com.tappx.a;

public class r {
    public static String a(String str) {
        return a(str, 256);
    }

    private static String a(String str, int i) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        return str.length() > i ? str.substring(0, i) : str;
    }
}
