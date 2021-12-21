package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.bi;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class f {

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final int f954a;
        private final int b;
        private final int c;

        public a(int i, int i2, int i3) {
            this.f954a = i;
            this.b = i2;
            this.c = i3;
        }

        public int a() {
            return this.f954a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public static a d() {
            return new a(30, 50, 100);
        }
    }

    public boolean a(String str, String str2) {
        return !bi.a(str, str2);
    }

    public Map<String, String> a(Map<String, String> map, String str, String str2, a aVar, String str3) {
        if (map != null) {
            String a2 = a(str, aVar.b(), str3);
            String a3 = a(str2, aVar.c(), str3);
            if (map.size() < aVar.a() || map.containsKey(a2)) {
                map.put(a2, a3);
            } else {
                b(a2, aVar.a(), str3);
            }
        }
        return map;
    }

    public String a(String str, int i, String str2) {
        if (str == null || str.length() <= i) {
            return str;
        }
        String substring = str.substring(0, i);
        j.f().b("\"%s\"'s parameter %s size exceeded limit of %d characters", str2, str, Integer.valueOf(i));
        return substring;
    }

    public String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return bytes.length > i ? new String(bytes, 0, i, "UTF-8") : str;
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public byte[] a(byte[] bArr, int i) {
        if (bArr.length <= i) {
            return bArr;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    public void b(String str, int i, String str2) {
        j.f().b("The %s has reached the limit of %d items. Item with key %s will be ignored", str2, Integer.valueOf(i), str);
    }
}
