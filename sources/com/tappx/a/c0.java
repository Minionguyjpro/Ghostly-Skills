package com.tappx.a;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class c0 {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, String> f388a;
    public final byte[] b;
    public final long c;

    public c0(int i, Map<String, String> map, byte[] bArr, long j) {
        this.b = bArr;
        this.f388a = map;
        this.c = j;
    }

    public String a() {
        if (this.b == null) {
            return null;
        }
        try {
            return new String(this.b, a(this.f388a));
        } catch (UnsupportedEncodingException unused) {
            return new String(this.b);
        }
    }

    static String a(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    public static String a(Map<String, String> map) {
        return a(map, "ISO-8859-1");
    }
}
