package com.yandex.metrica.impl;

import android.text.TextUtils;
import java.util.regex.Pattern;

public final class bi {
    public static String b(String str, String str2) {
        return str == null ? str2 : str;
    }

    static {
        Pattern.compile("[^0-9a-zA-Z,`’\\.\\+\\-'\\s\"]");
        Pattern.compile("\\s+");
    }

    public static boolean a(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean a(String... strArr) {
        if (strArr == null) {
            return false;
        }
        for (String a2 : strArr) {
            if (a(a2)) {
                return true;
            }
        }
        return false;
    }

    public static String c(String str, String str2) {
        return a(str) ? str2 : str;
    }

    public static String b(String str) {
        if (a(str)) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }

    public static byte[] c(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    public static final String b(String... strArr) {
        return TextUtils.join(",", strArr);
    }
}
