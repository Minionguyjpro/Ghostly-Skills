package com.yandex.metrica.impl;

import android.text.TextUtils;
import com.mopub.common.Constants;
import com.yandex.metrica.impl.ob.ds;
import com.yandex.metrica.impl.ob.dt;
import com.yandex.metrica.impl.utils.g;
import com.yandex.metrica.impl.utils.l;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class bg {

    static class a {

        /* renamed from: a  reason: collision with root package name */
        private C0031a f749a;
        private boolean b;
        private boolean c;
        private boolean d;
        private List<String> e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private dt n = new dt();
        private ds o = null;
        private boolean p;
        private boolean q;

        /* renamed from: com.yandex.metrica.impl.bg$a$a  reason: collision with other inner class name */
        public enum C0031a {
            BAD,
            OK
        }

        a() {
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            this.b = z;
        }

        public boolean a() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public void b(boolean z) {
            this.c = z;
        }

        public boolean b() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public void a(List<String> list) {
            this.e = list;
        }

        /* access modifiers changed from: package-private */
        public List<String> c() {
            return this.e;
        }

        /* access modifiers changed from: package-private */
        public void a(String str) {
            this.f = str;
        }

        public String d() {
            return this.f;
        }

        /* access modifiers changed from: package-private */
        public void b(String str) {
            this.g = str;
        }

        public String e() {
            return this.g;
        }

        /* access modifiers changed from: package-private */
        public void c(String str) {
            this.h = str;
        }

        public String f() {
            return this.h;
        }

        /* access modifiers changed from: package-private */
        public void d(String str) {
            this.j = str;
        }

        public String g() {
            return this.j;
        }

        /* access modifiers changed from: package-private */
        public void e(String str) {
            this.k = str;
        }

        public String h() {
            return this.k;
        }

        /* access modifiers changed from: package-private */
        public void f(String str) {
            this.l = str;
        }

        public String i() {
            return this.l;
        }

        /* access modifiers changed from: package-private */
        public void g(String str) {
            this.m = str;
        }

        public String j() {
            return this.m;
        }

        /* access modifiers changed from: package-private */
        public void a(C0031a aVar) {
            this.f749a = aVar;
        }

        public C0031a k() {
            return this.f749a;
        }

        public void a(dt dtVar) {
            this.n = dtVar;
        }

        public dt l() {
            return this.n;
        }

        public String m() {
            return this.i;
        }

        public void h(String str) {
            this.i = str;
        }

        public boolean n() {
            return this.d;
        }

        public void c(boolean z) {
            this.d = z;
        }

        /* access modifiers changed from: package-private */
        public void a(ds dsVar) {
            this.o = dsVar;
        }

        public ds o() {
            return this.o;
        }

        public void d(boolean z) {
            this.p = z;
        }

        public boolean p() {
            return this.p;
        }

        public void e(boolean z) {
            this.q = z;
        }

        public boolean q() {
            return this.q;
        }
    }

    private static String a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str).getString("value");
        } catch (Exception unused) {
            return "";
        }
    }

    private static String b(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str).getJSONArray(Constants.VIDEO_TRACKING_URLS_KEY).getString(0);
        } catch (Exception unused) {
            return "";
        }
    }

    private static List<String> c(JSONObject jSONObject, String str) {
        try {
            JSONArray jSONArray = jSONObject.getJSONObject(str).getJSONArray(Constants.VIDEO_TRACKING_URLS_KEY);
            if (jSONArray == null || jSONArray.length() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static a a(byte[] bArr) {
        a aVar = new a();
        try {
            g.a aVar2 = new g.a(new String(bArr, "UTF-8"));
            aVar.e(a((JSONObject) aVar2, "device_id"));
            aVar.f(a((JSONObject) aVar2, "uuid"));
            JSONObject jSONObject = (JSONObject) aVar2.a("query_hosts", new JSONObject());
            if (jSONObject.has("list")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("list");
                String b = b(jSONObject2, "get_ad");
                if (a(b)) {
                    aVar.a(b);
                }
                String b2 = b(jSONObject2, "report");
                if (a(b2)) {
                    aVar.b(b2);
                }
                String b3 = b(jSONObject2, "report_ad");
                if (a(b3)) {
                    aVar.c(b3);
                }
                String b4 = b(jSONObject2, "ssl_pinning");
                if (a(b4)) {
                    aVar.d(b4);
                }
                String b5 = b(jSONObject2, "bind_id");
                if (a(b5)) {
                    aVar.h(b5);
                }
                List<String> c = c(jSONObject2, "startup");
                if (!bk.a((Collection) c)) {
                    aVar.a(c);
                }
            }
            JSONObject optJSONObject = ((JSONObject) aVar2.a("distribution_customization", new JSONObject())).optJSONObject("clids");
            if (optJSONObject != null) {
                a(aVar, optJSONObject);
            }
            JSONObject jSONObject3 = (JSONObject) aVar2.a("features", new JSONObject());
            aVar.a(false);
            aVar.b(false);
            if (jSONObject3.has("list")) {
                JSONObject jSONObject4 = jSONObject3.getJSONObject("list");
                if (jSONObject4.has("easy_collecting")) {
                    aVar.a(jSONObject4.getJSONObject("easy_collecting").optBoolean("enabled", false));
                }
                if (jSONObject4.has("package_info")) {
                    aVar.b(jSONObject4.getJSONObject("package_info").optBoolean("enabled", false));
                }
                if (jSONObject4.has("socket")) {
                    aVar.c(jSONObject4.getJSONObject("socket").optBoolean("enabled", false));
                }
                if (jSONObject4.has("permissions_collecting")) {
                    aVar.d(jSONObject4.getJSONObject("permissions_collecting").optBoolean("enabled", false));
                }
                if (jSONObject4.has("features_collecting")) {
                    aVar.e(jSONObject4.getJSONObject("features_collecting").optBoolean("enabled", false));
                }
            }
            a(aVar, aVar2);
            if (aVar.n()) {
                b(aVar, aVar2);
            }
            aVar.a(a.C0031a.OK);
            return aVar;
        } catch (Exception unused) {
            a aVar3 = new a();
            aVar3.a(a.C0031a.BAD);
            return aVar3;
        }
    }

    private static void a(a aVar, g.a aVar2) throws JSONException {
        JSONArray optJSONArray;
        JSONObject optJSONObject = aVar2.optJSONObject("browsers");
        if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONArray("list")) != null) {
            dt dtVar = new dt();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject = optJSONArray.getJSONObject(i);
                String optString = jSONObject.optString("package_id");
                if (!TextUtils.isEmpty(optString)) {
                    dtVar.a(optString, jSONObject.optInt("min_interval_seconds"));
                }
            }
            aVar.a(dtVar);
        }
    }

    private static void b(a aVar, g.a aVar2) {
        JSONObject optJSONObject = aVar2.optJSONObject("socket");
        if (optJSONObject != null) {
            long optLong = optJSONObject.optLong("seconds_to_live");
            String optString = optJSONObject.optString("token");
            JSONArray optJSONArray = optJSONObject.optJSONArray("ports");
            if (optLong > 0 && a(optString) && optJSONArray != null && optJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList(optJSONArray.length());
                for (int i = 0; i < optJSONArray.length(); i++) {
                    int optInt = optJSONArray.optInt(i);
                    if (optInt != 0) {
                        arrayList.add(Integer.valueOf(optInt));
                    }
                }
                if (!arrayList.isEmpty()) {
                    aVar.a(new ds(optLong, optString, arrayList));
                }
            }
        }
    }

    private static boolean a(String str) {
        return !bi.a(str);
    }

    private static void a(a aVar, JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject = jSONObject.optJSONObject(next);
            if (optJSONObject != null && optJSONObject.has("value")) {
                hashMap.put(next, optJSONObject.getString("value"));
            }
        }
        aVar.g(l.a((Map<String, String>) hashMap));
    }

    public static Long a(Map<String, List<String>> map) {
        if (!bk.a((Map) map)) {
            List list = map.get("Date");
            if (!bk.a((Collection) list)) {
                try {
                    return Long.valueOf(new SimpleDateFormat("E, d MMM yyyy HH:mm:ss z", Locale.US).parse((String) list.get(0)).getTime());
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }
}
