package com.tappx.a;

import android.util.Base64;
import android.webkit.URLUtil;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class x {
    private static final String c = f.b("alkSd/cjeMOnRhI1+y0Fgw");
    /* access modifiers changed from: private */
    public static final String d = f.b("7lduO4sGkp1tZzDJbYS8pw");
    /* access modifiers changed from: private */
    public static final String e = f.b("HnTZ8Ox2OnyDxdD5hsZrLQ");
    /* access modifiers changed from: private */
    public static final String f = f.b("AkEIgV9lhHi/w7lKhAfWaA");
    /* access modifiers changed from: private */
    public static final String g = f.b("Vj56qeUSUIexpZcT0zkYTw");
    /* access modifiers changed from: private */
    public static final String h = f.b("/spXhZX81bJqCykir0NEjw");
    /* access modifiers changed from: private */
    public static final String i = f.b("ju52neF4RY1ixmfCi1hgfA");
    /* access modifiers changed from: private */
    public static final String j = f.b("lZVtXyq5ZD6wNwR+ZIHPqA");
    /* access modifiers changed from: private */
    public static final String k = f.b("yzM431Q8gFGgERkBwgxdJg");
    /* access modifiers changed from: private */
    public static final String l = f.b("oRIavRQJX4wGvDJt1kOzlg");
    /* access modifiers changed from: private */
    public static final String m = f.b("5/KYAj40HA8DjDIwU73ybw");
    /* access modifiers changed from: private */
    public static final String n = f.b("Ku0dXCSVFeVQPviFMELOqg");
    /* access modifiers changed from: private */
    public static final String o = f.b("7nlBnTgzxYha5wGn2VLJtw");
    /* access modifiers changed from: private */
    public static final String p = f.b("2mDdVFzY5fPJSVCM3S7xnA");
    /* access modifiers changed from: private */
    public static final String q = f.b("Dgw5Kh1C4hRzacbFtYeP2Q");
    private static final String r = f.b("kZlsOHt4BykwnGQBWo5ciQ");
    private static final String s = f.b("vDgIOL0Ac/85bIB4sUn2dg");
    private static final String t = f.b("VbMgRD4jVg4hNau0Ow7yWHX5dBZylyqDyPIjra0JMrA");
    private static final String u = f.b("Rn7//KdU5SMi4HFSPVjQzQ");
    private static final String v = f.b("CJcKTQGQcFh0cXOvBHlbc1De0+8fG8Rx/CGjhGnsKs8");
    private static final String w = f.b("OwINE7I1OlDbXaPClfMqJg");
    private static final String x = f.b("J6xwyZwxZoE3V4vmDtoW2w");
    private static final String y = f.b("93poZZjBiuurmpEnoLn+8A");

    /* renamed from: a  reason: collision with root package name */
    private final c f618a;
    private final b b;

    static class b extends a {
        b() {
        }

        public w1 b(c0 c0Var) {
            w1 a2 = a(c0Var);
            a2.b().add(a(c0Var, c0Var.a()));
            return a2;
        }

        private y1 a(c0 c0Var, String str) {
            return a(c0Var.f388a, str);
        }

        /* access modifiers changed from: private */
        public y1 a(Map<String, String> map, String str) {
            y1 y1Var = new y1(str);
            boolean z = false;
            y1Var.b(a(map, x.f, 0) == 1);
            y1Var.a(a(map, x.g, 0) == 1);
            int a2 = a(map, x.h, 0);
            int a3 = a(map, x.i, 0);
            y1Var.e(a2);
            y1Var.c(a3);
            y1Var.b(a(map, x.j, 0));
            if (a(map, x.k, 1) > 0) {
                z = true;
            }
            y1Var.c(z);
            y1Var.d(a(map, x.q, n.d));
            a((u1) y1Var, map, true);
            return y1Var;
        }
    }

    x(c cVar, b bVar) {
        this.f618a = cVar;
        this.b = bVar;
    }

    public w1 a(c0 c0Var) {
        String str = c0Var.f388a.get(c);
        int a2 = a(c0Var.f388a.get(u));
        boolean equals = "1".equals(c0Var.f388a.get(r));
        String str2 = c0Var.f388a.get(v);
        if (w.equals(str)) {
            w1 w1Var = new w1();
            w1Var.a(equals, a2, str2);
            return w1Var;
        } else if (x.equals(str)) {
            w1 b2 = this.b.b(c0Var);
            b2.a(equals, a2, str2);
            return b2;
        } else if (y.equals(str)) {
            w1 b3 = this.f618a.b(c0Var);
            b3.a(equals, a2, str2);
            return b3;
        } else {
            throw new c2();
        }
    }

    public j2 b(c0 c0Var) {
        String str = c0Var.f388a.get(s);
        if (URLUtil.isValidUrl(str)) {
            boolean equalsIgnoreCase = "1".equalsIgnoreCase(c0Var.f388a.get(r));
            String a2 = c0Var.a();
            if (a2 != null && a2.length() < 10) {
                a2 = null;
            }
            return new j2(equalsIgnoreCase, str, a2, c0Var.f388a.get(t));
        }
        throw new c2();
    }

    static class a {

        /* renamed from: a  reason: collision with root package name */
        static final String f619a = f.b("Z0s98+TEac+mapO900zQZA");
        static final String b = f.b("UjhhaCwcEZ+voViDfkR/pA");
        static final String c = f.b("SNfY2H1acX2p46/zyMOc/g");
        static final String d = f.b("On2W1poIktAVirYlBse78g");
        static final String e = f.b("LC4el1lDkKxbZdxa4Qatkw");
        static final String f = f.b("rj1rf34CVwuKwyr8EiViZg");

        a() {
        }

        private int b(Map<String, String> map, String str, int i) {
            String str2 = map.get(x.p);
            if (str2 == null) {
                return i;
            }
            String lowerCase = str2.toLowerCase(Locale.US);
            char c2 = 65535;
            int hashCode = lowerCase.hashCode();
            if (hashCode != 108) {
                if (hashCode == 112 && lowerCase.equals("p")) {
                    c2 = 1;
                }
            } else if (lowerCase.equals("l")) {
                c2 = 0;
            }
            if (c2 != 0) {
                return c2 != 1 ? 0 : 1;
            }
            return 2;
        }

        /* access modifiers changed from: package-private */
        public w1 a(c0 c0Var) {
            w1 w1Var = new w1();
            int a2 = a(c0Var.f388a, x.d, -1);
            if (a2 >= 0) {
                w1Var.a(a2);
            }
            return w1Var;
        }

        /* access modifiers changed from: package-private */
        public void a(u1 u1Var, Map<String, String> map, boolean z) {
            u1Var.a(System.currentTimeMillis() + ((long) a(map, x.e, Integer.MAX_VALUE)));
            if (z) {
                u1Var.c(map.get(x.l));
                u1Var.d(map.get(x.m));
                u1Var.b(map.get(x.n));
            }
            u1Var.a(a(map.get(x.o)));
            u1Var.a(b(map, x.p, 0));
        }

        private t1 a(String str) {
            if (str == null) {
                str = e;
            }
            if (c.equals(str)) {
                return t1.LEFT_TO_RIGHT;
            }
            if (d.equals(str)) {
                return t1.LEFT_TO_RIGHT_BOUNCE;
            }
            if (f619a.equals(str)) {
                return t1.RIGHT_TO_LEFT;
            }
            if (b.equals(str)) {
                return t1.RIGHT_TO_LEFT_BOUNCE;
            }
            if (f.equals(str)) {
                return t1.RANDOM;
            }
            if (e.equals(str)) {
                return t1.NONE;
            }
            return t1.NONE;
        }

        /* access modifiers changed from: package-private */
        public int a(Map<String, String> map, String str, int i) {
            if (map != null && map.containsKey(str)) {
                try {
                    return Integer.parseInt(map.get(str));
                } catch (NumberFormatException unused) {
                }
            }
            return i;
        }
    }

    public x() {
        this(new c(new b()), new b());
    }

    static class c extends a {
        private static final String h = f.b("93poZZjBiuurmpEnoLn+8A");
        private static final String i = f.b("z1WSfDFTLljcsXuTNgQuhw");
        private static final String j = f.b("TpIeqY5wTOn5nxfmmaemDg");
        private static final String k = f.b("HdGvihwA9tMW9jAtG5Z60Q");
        private static final String l = f.b("YESpEXhzZPY6QF+iX8uR8g");
        private static final String m = f.b("I/pQEmQKlNm3lpGS1bYL+Q");
        private static final String n = f.b("iG+mgBqNtiuZUD3OEMyj7g");
        private static final String o = f.b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTgAyMZUaugwtGwADx5ljGYC");
        private static final String p = f.b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTjG9Fb6F0/lw1+DOmSdANB4awFmKet7rldflQ3Vwu0LPQ");
        private static final String q = f.b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTgCFo4ZNldq2wwXprZPFh7t");
        private static final String r = f.b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtThB5bdhPbTjs1czbisynSJYtqmQfNBs4mU/7S8aPfuZvQ");
        private static final String s = f.b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTh1OnzYsahK/T3EEh8sxhNj");
        private static final String t = f.b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTjW2bWAjnZ1cI+e8UDyUDXA7/OH75yQRbt4jtPR7dvcLA");
        private static final String u = f.b("WbAHWTB1FBrtYHVp67i8eA");
        private static final String v = f.b("sjSpR32rYnS73sNt0yqplA");
        private static final String w = f.b("wD7tiqYisob3SCbvprbs4Q");
        private static final String x = f.b("KkIRPS49FJ604X5/25Zilw");
        private final b g;

        c(b bVar) {
            this.g = bVar;
        }

        private u1 a(Map<String, String> map, JSONObject jSONObject) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(j);
            try {
                y1 a2 = this.g.a(map, new String(Base64.decode(jSONObject2.optString(k), 0), "utf-8"));
                a(jSONObject2.optJSONObject(m), (u1) a2);
                return a2;
            } catch (UnsupportedEncodingException unused) {
                throw new c2();
            }
        }

        private z1 c(Map<String, String> map, JSONObject jSONObject, int i2) {
            z1 z1Var = new z1();
            a((u1) z1Var, map, false);
            JSONObject jSONObject2 = jSONObject.getJSONObject(j);
            a(jSONObject2.optJSONObject(m), (u1) z1Var);
            int optInt = jSONObject2.optInt(i, i2);
            String string = jSONObject2.getString(k);
            z1Var.b(optInt);
            z1Var.e(string);
            return z1Var;
        }

        public w1 b(c0 c0Var) {
            try {
                w1 a2 = a(c0Var);
                JSONObject jSONObject = new JSONObject(c0Var.a());
                int optInt = jSONObject.optInt(i);
                JSONArray jSONArray = jSONObject.getJSONArray(h);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    u1 b = b(c0Var.f388a, jSONObject2, optInt);
                    if (b != null) {
                        b.a(jSONObject2.optString(n));
                        a2.b().add(b);
                    }
                }
                return a2;
            } catch (JSONException unused) {
                throw new c2();
            }
        }

        private x1 a(Map<String, String> map, JSONObject jSONObject, int i2) {
            x1 x1Var = new x1();
            a((u1) x1Var, map, true);
            JSONObject jSONObject2 = jSONObject.getJSONObject(j);
            String string = jSONObject2.getString(l);
            x1Var.c(jSONObject2.optInt(i, i2));
            x1Var.e(string);
            a(jSONObject2.optJSONObject(m), (u1) x1Var);
            try {
                int parseInt = Integer.parseInt(map.get(x.h));
                int parseInt2 = Integer.parseInt(map.get(x.i));
                x1Var.d(parseInt);
                x1Var.b(parseInt2);
                return x1Var;
            } catch (Exception unused) {
                throw new c2();
            }
        }

        private u1 b(Map<String, String> map, JSONObject jSONObject, int i2) {
            try {
                String string = jSONObject.getString(x);
                if (!o.equals(string) && !p.equals(string)) {
                    if (!u.equals(string)) {
                        if (!q.equals(string) && !r.equals(string)) {
                            if (!v.equals(string)) {
                                if (!s.equals(string) && !t.equals(string)) {
                                    if (!w.equals(string)) {
                                        return null;
                                    }
                                }
                                return a(map, jSONObject);
                            }
                        }
                        return c(map, jSONObject, i2);
                    }
                }
                return a(map, jSONObject, i2);
            } catch (c2 | JSONException unused) {
                return null;
            }
        }

        private void a(u1 u1Var, String str, String str2) {
            String f = u1Var.f();
            if (f != null) {
                u1Var.c(f.replaceAll(Pattern.quote(str), str2));
            }
            String g2 = u1Var.g();
            if (g2 != null) {
                u1Var.d(g2.replaceAll(Pattern.quote(str), str2));
            }
            String e = u1Var.e();
            if (e != null) {
                u1Var.b(e.replaceAll(Pattern.quote(str), str2));
            }
        }

        private void a(JSONObject jSONObject, u1 u1Var) {
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = jSONObject.optString(next, (String) null);
                    if (optString != null) {
                        a(u1Var, next, optString);
                    }
                }
            }
        }
    }

    private int a(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }
}
