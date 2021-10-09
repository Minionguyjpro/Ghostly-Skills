package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.appnext.base.b.i;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.network.ImpressionData;
import com.yandex.metrica.c;
import com.yandex.metrica.impl.bm;
import com.yandex.metrica.impl.ob.bl;
import com.yandex.metrica.impl.ob.ee;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.g;
import com.yandex.metrica.impl.utils.n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ap {

    /* renamed from: a  reason: collision with root package name */
    private static Map<bl, Integer> f724a;
    private static SparseArray<bl> b;

    public static void a() {
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(bl.FOREGROUND, 0);
        hashMap.put(bl.BACKGROUND, 1);
        f724a = Collections.unmodifiableMap(hashMap);
        SparseArray<bl> sparseArray = new SparseArray<>();
        sparseArray.put(0, bl.FOREGROUND);
        sparseArray.put(1, bl.BACKGROUND);
        b = sparseArray;
    }

    public static c.a.f a(ContentValues contentValues) {
        return a(contentValues.getAsLong("start_time"), contentValues.getAsLong("server_time_offset"));
    }

    public static c.a.e a(ee eeVar) {
        c.a.e eVar = new c.a.e();
        if (eeVar.a() != null) {
            eVar.b = eeVar.a().intValue();
        }
        if (eeVar.b() != null) {
            eVar.c = eeVar.b().intValue();
        }
        if (!TextUtils.isEmpty(eeVar.d())) {
            eVar.d = eeVar.d();
        }
        eVar.e = eeVar.c();
        if (!TextUtils.isEmpty(eeVar.e())) {
            eVar.f = eeVar.e();
        }
        return eVar;
    }

    public static bl a(int i) {
        return b.get(i);
    }

    public static List<c.a.d.C0029c> a(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(a(jSONArray.getJSONObject(i)));
                } catch (Exception unused) {
                }
            }
            return arrayList;
        } catch (Exception unused2) {
            return new ArrayList();
        }
    }

    public static c.a.d.C0029c a(JSONObject jSONObject) throws JSONException {
        try {
            c.a.d.C0029c cVar = new c.a.d.C0029c();
            cVar.b = jSONObject.getString("mac");
            cVar.c = jSONObject.getInt("signal_strength");
            cVar.d = jSONObject.getString("ssid");
            cVar.e = jSONObject.optBoolean("is_connected");
            return cVar;
        } catch (Exception unused) {
            c.a.d.C0029c cVar2 = new c.a.d.C0029c();
            cVar2.b = jSONObject.getString("mac");
            return cVar2;
        }
    }

    static c.a.d.C0025a.b.C0028b b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            c.a.d.C0025a.b.C0028b bVar = new c.a.d.C0025a.b.C0028b();
            bVar.b = jSONObject.optString("ssid");
            int optInt = jSONObject.optInt("state", -1);
            if (!(optInt == 0 || optInt == 1 || optInt == 2)) {
                if (optInt == 3) {
                    bVar.c = 2;
                } else if (optInt != 4) {
                }
                return bVar;
            }
            bVar.c = 1;
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public static c.a.f a(Long l, Long l2) {
        long longValue = l.longValue();
        c.a.f fVar = new c.a.f();
        fVar.b = longValue;
        fVar.c = ((GregorianCalendar) GregorianCalendar.getInstance()).getTimeZone().getOffset(longValue * 1000) / 1000;
        if (l2 != null) {
            fVar.d = l2.longValue();
        }
        return fVar;
    }

    public static c.a.d.b a(String str, int i, c.a.f fVar) {
        c.a.d.b bVar = new c.a.d.b();
        bVar.b = fVar;
        bVar.c = str;
        bVar.d = i;
        return bVar;
    }

    static int a(bl blVar) {
        return f724a.get(blVar).intValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        return new com.yandex.metrica.c.a.d.C0025a.b.C0027a[]{b(new org.json.JSONObject(r5))};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0029 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.yandex.metrica.c.a.d.C0025a.b.C0027a[] c(java.lang.String r5) {
        /*
            r0 = 0
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x0029 }
            if (r1 != 0) goto L_0x0038
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0029 }
            r1.<init>(r5)     // Catch:{ JSONException -> 0x0029 }
            int r2 = r1.length()     // Catch:{ JSONException -> 0x0029 }
            com.yandex.metrica.c$a$d$a$b$a[] r2 = new com.yandex.metrica.c.a.d.C0025a.b.C0027a[r2]     // Catch:{ JSONException -> 0x0029 }
            r3 = 0
        L_0x0013:
            int r4 = r1.length()     // Catch:{ JSONException -> 0x0029 }
            if (r3 >= r4) goto L_0x0028
            org.json.JSONObject r4 = r1.getJSONObject(r3)     // Catch:{ JSONException -> 0x0029 }
            if (r4 == 0) goto L_0x0025
            com.yandex.metrica.c$a$d$a$b$a r4 = b((org.json.JSONObject) r4)     // Catch:{ JSONException -> 0x0029 }
            r2[r3] = r4     // Catch:{ JSONException -> 0x0029 }
        L_0x0025:
            int r3 = r3 + 1
            goto L_0x0013
        L_0x0028:
            return r2
        L_0x0029:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0038 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0038 }
            r5 = 1
            com.yandex.metrica.c$a$d$a$b$a[] r5 = new com.yandex.metrica.c.a.d.C0025a.b.C0027a[r5]     // Catch:{ Exception -> 0x0038 }
            com.yandex.metrica.c$a$d$a$b$a r1 = b((org.json.JSONObject) r1)     // Catch:{ Exception -> 0x0038 }
            r5[r0] = r1     // Catch:{ Exception -> 0x0038 }
            return r5
        L_0x0038:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ap.c(java.lang.String):com.yandex.metrica.c$a$d$a$b$a[]");
    }

    static c.a.d.C0025a.b.C0027a b(JSONObject jSONObject) {
        int optInt;
        c.a.d.C0025a.b.C0027a aVar = new c.a.d.C0025a.b.C0027a();
        if (jSONObject.has("signal_strength") && (optInt = jSONObject.optInt("signal_strength")) != -1) {
            aVar.c = optInt;
        }
        if (jSONObject.has("cell_id")) {
            aVar.b = jSONObject.optInt("cell_id");
        }
        if (jSONObject.has("lac")) {
            aVar.d = jSONObject.optInt("lac");
        }
        if (jSONObject.has("country_code")) {
            aVar.e = jSONObject.optInt("country_code");
        }
        if (jSONObject.has("operator_id")) {
            aVar.f = jSONObject.optInt("operator_id");
        }
        if (jSONObject.has("operator_name")) {
            aVar.g = jSONObject.optString("operator_name");
        }
        if (jSONObject.has("is_connected")) {
            aVar.h = jSONObject.optBoolean("is_connected");
        }
        aVar.i = jSONObject.optInt("cell_type", 0);
        if (jSONObject.has("pci")) {
            aVar.j = jSONObject.optInt("pci");
        }
        return aVar;
    }

    public static c.a.b d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            g.a aVar = new g.a(str);
            c.a.b bVar = new c.a.b();
            bVar.c = aVar.getDouble("lon");
            bVar.b = aVar.getDouble(i.fC);
            if (aVar.b("altitude")) {
                bVar.h = aVar.getInt("altitude");
            }
            if (aVar.b("direction")) {
                bVar.f = aVar.getInt("direction");
            }
            if (aVar.b(ImpressionData.PRECISION)) {
                bVar.e = aVar.getInt(ImpressionData.PRECISION);
            }
            if (aVar.b("speed")) {
                bVar.g = aVar.getInt("speed");
            }
            if (aVar.b(AvidJSONUtil.KEY_TIMESTAMP)) {
                bVar.d = aVar.getLong(AvidJSONUtil.KEY_TIMESTAMP) / 1000;
            }
            if (aVar.b("provider")) {
                String a2 = aVar.a("provider");
                if ("gps".equals(a2)) {
                    bVar.i = 1;
                } else if ("network".equals(a2)) {
                    bVar.i = 2;
                }
            }
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public static c.a.d.C0025a.b a(int i, String str, String str2, String str3, String str4) {
        c.a.d.C0025a.b bVar = new c.a.d.C0025a.b();
        bVar.d = i;
        if (str != null) {
            bVar.e = str;
        }
        c.a.d.C0025a.b.C0027a[] c2 = c(str3);
        List<c.a.d.C0029c> a2 = a(str2);
        if (c2 != null) {
            bVar.b = c2;
        }
        if (a2 != null) {
            bVar.c = (c.a.d.C0029c[]) a2.toArray(new c.a.d.C0029c[a2.size()]);
        }
        if (!TextUtils.isEmpty(str4)) {
            bVar.f = b(str4);
        }
        return bVar;
    }

    public static c.a.d.C0025a.C0026a e(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            com.yandex.metrica.d a2 = n.a(str);
            c.a.d.C0025a.C0026a aVar = new c.a.d.C0025a.C0026a();
            aVar.b = a2.a();
            if (!TextUtils.isEmpty(a2.b())) {
                aVar.c = a2.b();
            }
            if (!bk.a((Map) a2.c())) {
                aVar.d = com.yandex.metrica.impl.utils.g.a((Map) a2.c());
            }
            return aVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public static c.a.C0024c[] a(Context context) {
        List<bm.a> b2 = bm.a(context).b();
        if (bk.a((Collection) b2)) {
            return null;
        }
        c.a.C0024c[] cVarArr = new c.a.C0024c[b2.size()];
        for (int i = 0; i < b2.size(); i++) {
            c.a.C0024c cVar = new c.a.C0024c();
            bm.a aVar = b2.get(i);
            cVar.b = aVar.f756a;
            cVar.c = aVar.b;
            cVarArr[i] = cVar;
        }
        return cVarArr;
    }

    static class b {
        private static final Map<p.a, Class<?>> p;
        private static final Map<p.a, Integer> q;

        /* renamed from: a  reason: collision with root package name */
        protected String f726a;
        protected String b;
        protected int c;
        protected long d;
        protected String e;
        protected String f;
        protected String g;
        protected Integer h;
        protected Integer i;
        protected String j;
        protected String k;
        protected int l;
        protected int m;
        protected String n;
        protected String o;

        /* access modifiers changed from: protected */
        public String a() {
            return "";
        }

        /* access modifiers changed from: protected */
        public byte[] b() {
            return new byte[0];
        }

        /* access modifiers changed from: protected */
        public int f() {
            return 0;
        }

        static {
            Class<b> cls = b.class;
            HashMap hashMap = new HashMap();
            hashMap.put(p.a.EVENT_TYPE_REGULAR, e.class);
            hashMap.put(p.a.EVENT_TYPE_REFERRER_DEPRECATED, f.class);
            hashMap.put(p.a.EVENT_TYPE_ACTIVITY_START_DEPRECATED, cls);
            hashMap.put(p.a.EVENT_TYPE_ALIVE, cls);
            hashMap.put(p.a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, f.class);
            hashMap.put(p.a.EVENT_TYPE_NATIVE_CRASH, h.class);
            hashMap.put(p.a.EVENT_TYPE_EXCEPTION_USER, e.class);
            hashMap.put(p.a.EVENT_TYPE_IDENTITY, g.class);
            hashMap.put(p.a.EVENT_TYPE_STATBOX, e.class);
            hashMap.put(p.a.EVENT_TYPE_SET_USER_INFO, e.class);
            hashMap.put(p.a.EVENT_TYPE_REPORT_USER_INFO, e.class);
            hashMap.put(p.a.EVENT_TYPE_EXCEPTION_UNHANDLED, e.class);
            hashMap.put(p.a.EVENT_TYPE_START, cls);
            hashMap.put(p.a.EVENT_TYPE_CUSTOM_EVENT, c.class);
            hashMap.put(p.a.EVENT_TYPE_APP_OPEN, e.class);
            hashMap.put(p.a.EVENT_TYPE_PERMISSIONS, a.class);
            hashMap.put(p.a.EVENT_TYPE_APP_FEATURES, a.class);
            p = Collections.unmodifiableMap(hashMap);
            HashMap hashMap2 = new HashMap();
            hashMap2.put(p.a.EVENT_TYPE_INIT, 1);
            hashMap2.put(p.a.EVENT_TYPE_REGULAR, 4);
            hashMap2.put(p.a.EVENT_TYPE_REFERRER_DEPRECATED, 5);
            hashMap2.put(p.a.EVENT_TYPE_ACTIVITY_START_DEPRECATED, 2);
            hashMap2.put(p.a.EVENT_TYPE_ALIVE, 7);
            hashMap2.put(p.a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, 3);
            hashMap2.put(p.a.EVENT_TYPE_EXCEPTION_UNHANDLED, 3);
            hashMap2.put(p.a.EVENT_TYPE_NATIVE_CRASH, 3);
            hashMap2.put(p.a.EVENT_TYPE_EXCEPTION_USER, 6);
            hashMap2.put(p.a.EVENT_TYPE_IDENTITY, 8);
            hashMap2.put(p.a.EVENT_TYPE_STATBOX, 11);
            hashMap2.put(p.a.EVENT_TYPE_SET_USER_INFO, 12);
            hashMap2.put(p.a.EVENT_TYPE_REPORT_USER_INFO, 12);
            hashMap2.put(p.a.EVENT_TYPE_FIRST_ACTIVATION, 13);
            hashMap2.put(p.a.EVENT_TYPE_START, 2);
            hashMap2.put(p.a.EVENT_TYPE_APP_OPEN, 16);
            hashMap2.put(p.a.EVENT_TYPE_APP_UPDATE, 17);
            hashMap2.put(p.a.EVENT_TYPE_PERMISSIONS, 18);
            hashMap2.put(p.a.EVENT_TYPE_APP_FEATURES, 19);
            q = Collections.unmodifiableMap(hashMap2);
        }

        static b a(int i2, boolean z) {
            b bVar;
            p.a a2 = p.a.a(i2);
            int i3 = AnonymousClass1.f725a[a2.ordinal()];
            Class cls = (i3 == 1 || i3 == 2 || i3 == 3) ? z ? e.class : d.class : p.get(a2);
            Integer num = q.get(a2);
            try {
                bVar = (b) cls.newInstance();
            } catch (Exception unused) {
                bVar = new b();
            }
            return bVar.a(num);
        }

        /* access modifiers changed from: package-private */
        public b a(String str) {
            this.f726a = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b b(String str) {
            this.b = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b a(int i2) {
            this.c = i2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b a(long j2) {
            this.d = j2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b c(String str) {
            this.e = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b d(String str) {
            this.g = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b e(String str) {
            this.f = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b a(Integer num) {
            this.h = num;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b f(String str) {
            this.o = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b b(Integer num) {
            this.i = num;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b g(String str) {
            this.j = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b h(String str) {
            this.k = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b b(int i2) {
            this.l = i2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b c(int i2) {
            this.m = i2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b i(String str) {
            this.n = str;
            return this;
        }

        /* access modifiers changed from: protected */
        public Integer c() {
            return this.h;
        }

        /* access modifiers changed from: protected */
        public String d() {
            return this.j;
        }

        /* access modifiers changed from: package-private */
        public c.a.d.C0025a e() {
            c.a.d.C0025a aVar = new c.a.d.C0025a();
            c.a.d.C0025a.b a2 = ap.a(this.m, this.n, this.g, this.f, this.o);
            c.a.b d2 = ap.d(this.e);
            c.a.d.C0025a.C0026a e2 = ap.e(this.k);
            if (a2 != null) {
                aVar.h = a2;
            }
            if (d2 != null) {
                aVar.g = d2;
            }
            if (a() != null) {
                aVar.e = a();
            }
            if (b() != null) {
                aVar.f = b();
            }
            if (d() != null) {
                aVar.i = d();
            }
            if (e2 != null) {
                aVar.j = e2;
            }
            aVar.d = c().intValue();
            aVar.b = (long) this.c;
            aVar.c = this.d;
            aVar.k = this.l;
            aVar.l = f();
            return aVar;
        }
    }

    /* renamed from: com.yandex.metrica.impl.ap$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f725a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.yandex.metrica.impl.p$a[] r0 = com.yandex.metrica.impl.p.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f725a = r0
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_INIT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f725a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_FIRST_ACTIVATION     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f725a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_APP_UPDATE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ap.AnonymousClass1.<clinit>():void");
        }
    }

    static class f extends b {
        f() {
        }

        /* access modifiers changed from: protected */
        public byte[] b() {
            return bi.c(this.f726a);
        }
    }

    static class c extends e {
        c() {
        }

        /* access modifiers changed from: protected */
        public Integer c() {
            return this.i;
        }
    }

    static class e extends b {
        e() {
        }

        /* access modifiers changed from: protected */
        public String a() {
            return this.f726a;
        }

        /* access modifiers changed from: protected */
        public byte[] b() {
            if (this.b != null) {
                return bi.c(this.b);
            }
            return super.b();
        }
    }

    static class a extends e {
        /* access modifiers changed from: protected */
        public String a() {
            return "";
        }

        a() {
        }
    }

    static class d extends b {
        d() {
        }

        /* access modifiers changed from: protected */
        public String a() {
            return this.f726a;
        }
    }

    static class h extends b {
        h() {
        }

        /* access modifiers changed from: protected */
        public byte[] b() {
            return bi.c(r.c(this.b));
        }
    }

    static class g extends b {
        public int f() {
            return 1;
        }

        g() {
        }

        /* access modifiers changed from: protected */
        public byte[] b() {
            return Base64.decode(this.b, 0);
        }
    }
}
