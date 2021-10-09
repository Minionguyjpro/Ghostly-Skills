package com.tappx.a;

import java.util.regex.Pattern;

public class o4 {

    /* renamed from: a  reason: collision with root package name */
    static String f539a = "<script\\s+[^>]*\\bsrc\\s*=\\s*([\\\"\\'])mraid\\.js\\1[^>]*>\\s*</script>\\n*";
    static String b = "<script src=\"mraid.js\"></script>";

    public static boolean a(String str) {
        return Pattern.compile(f539a, 2).matcher(str).find();
    }
}
