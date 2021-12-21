package com.appsgeyser.multiTabApp.utils;

public class WildcardMatcher {
    public static boolean match(String str, String str2) {
        for (String str3 : str2.split("\\*")) {
            int indexOf = str.indexOf(str3);
            if (indexOf == -1) {
                return false;
            }
            str = str.substring(indexOf + str3.length());
        }
        return true;
    }
}
