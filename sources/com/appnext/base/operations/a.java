package com.appnext.base.operations;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.appnext.base.a;
import com.appnext.base.a.b.b;
import com.appnext.base.a.b.c;
import com.appnext.base.b.d;
import com.appnext.base.b.i;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class a {
    private static final String em = "collectedData";
    private static final String en = "collectedDataType";
    private static final String eo = "lastCollectedData";
    private C0038a el;
    protected c ep;

    /* renamed from: com.appnext.base.operations.a$a  reason: collision with other inner class name */
    public interface C0038a {
        void aH();

        void b(com.appnext.base.a aVar);
    }

    protected static boolean az() {
        return true;
    }

    protected static HashMap<Pair<String, String>, JSONArray> c(HashMap<Pair<String, String>, JSONArray> hashMap) {
        return hashMap;
    }

    public static void cancel() {
    }

    public abstract void aC();

    public abstract void aD();

    public boolean aF() {
        return true;
    }

    /* access modifiers changed from: protected */
    public List<b> b(List<b> list) {
        return list;
    }

    /* access modifiers changed from: protected */
    public abstract List<b> getData();

    /* access modifiers changed from: protected */
    public abstract String getKey();

    public a(c cVar, Bundle bundle, Object obj) {
        this.ep = cVar;
    }

    public final void a(C0038a aVar) {
        this.el = aVar;
    }

    /* access modifiers changed from: protected */
    public final long a(List<b> list) {
        try {
            JSONArray a2 = com.appnext.base.b.b.a(list, true);
            if (a2 == null || a2.length() <= 0) {
                return -1;
            }
            return com.appnext.base.a.a.X().aa().a(a2);
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public final void av() {
        Map<String, String> ax;
        try {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            String key = this.ep.getKey();
            i.aR().putLong(key + i.fz, valueOf.longValue());
            List<b> data = getData();
            if (data != null && !data.isEmpty()) {
                a(data);
            }
            String key2 = getKey();
            if (data != null) {
                JSONArray d = d(data);
                if (d != null) {
                    i.aR().putString(A(key2), d.toString());
                }
            }
        } catch (Throwable unused) {
            return;
        }
        String key3 = this.ep.getKey();
        try {
            i.aR().putLong(key3 + i.fy, System.currentTimeMillis());
            String str = key3 + i.fA;
            i.aR().putInt(str, i.aR().getInt(str, 0) + 1);
        } catch (Throwable unused2) {
        }
        com.appnext.base.a aVar = null;
        if (aA() && (ax = ax()) != null && !ax.isEmpty()) {
            Long valueOf2 = Long.valueOf(System.currentTimeMillis());
            String key4 = this.ep.getKey();
            Long.valueOf(i.aR().getLong(key4 + i.fz, -1));
            i.aR().putLong(key4 + i.fz, valueOf2.longValue());
            if (!com.appnext.base.b.b.a(key4, ax)) {
                aVar = new com.appnext.base.a(a.C0036a.NoInternet$1d8b5b4a);
            }
        }
        a(aVar);
    }

    private boolean a(Map<String, String> map) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        String key = this.ep.getKey();
        i aR = i.aR();
        Long.valueOf(aR.getLong(key + i.fz, -1));
        i aR2 = i.aR();
        aR2.putLong(key + i.fz, valueOf.longValue());
        return com.appnext.base.b.b.a(key, map);
    }

    /* access modifiers changed from: protected */
    public final c ay() {
        return this.ep;
    }

    private static JSONObject d(b bVar) {
        return com.appnext.base.b.b.a(bVar.ai(), bVar.aj(), d.a.valueOf(bVar.getDataType()));
    }

    public final void a(com.appnext.base.a aVar) {
        b.aI().a(this);
        C0038a aVar2 = this.el;
        if (aVar2 == null) {
            return;
        }
        if (aVar != null) {
            aVar2.b(aVar);
        } else {
            aVar2.aH();
        }
    }

    /* access modifiers changed from: protected */
    public boolean aA() {
        return com.appnext.base.b.b.d(this.ep);
    }

    protected static com.appnext.base.a.c.d aB() {
        return com.appnext.base.a.a.X().aa();
    }

    private void c(List<String> list) {
        if (!list.isEmpty()) {
            com.appnext.base.a.c.b aa = com.appnext.base.a.a.X().aa();
            for (String u : list) {
                aa.u(u);
            }
        }
    }

    public final boolean aE() {
        try {
            String key = this.ep.getKey();
            i aR = i.aR();
            if (System.currentTimeMillis() - Long.valueOf(aR.getLong(key + i.fz, -1)).longValue() >= 900000) {
                return aF();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(key);
            sb.append(" less then interval");
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public d.a aG() {
        return d.a.String;
    }

    protected static Date getDate() {
        return new Date();
    }

    private void a(String str, List<b> list) {
        if (list != null) {
            try {
                JSONArray d = d(list);
                if (d != null) {
                    i.aR().putString(A(str), d.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static String A(String str) {
        return eo + "_" + str;
    }

    private static JSONArray d(List<b> list) {
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    JSONArray jSONArray = new JSONArray();
                    for (b next : list) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(em, next.ai());
                        jSONObject.put(en, next.getType());
                        jSONArray.put(jSONObject);
                    }
                    return jSONArray;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final boolean e(List<b> list) {
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    JSONArray d = d(list);
                    if (d != null) {
                        String jSONArray = d.toString();
                        String string = i.aR().getString(A(getKey()), (String) null);
                        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(jSONArray) || !string.equals(jSONArray)) {
                            return true;
                        }
                        return false;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final List<b> aw() {
        return com.appnext.base.a.a.X().aa().w(this.ep.getKey());
    }

    /* access modifiers changed from: protected */
    public final Map<String, String> ax() {
        List<b> b;
        List<b> w = com.appnext.base.a.a.X().aa().w(this.ep.getKey());
        if (w == null || w.isEmpty() || (b = b(w)) == null || b.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (b next : b) {
            Pair pair = new Pair(next.ah(), next.getType());
            if (hashMap.containsKey(pair)) {
                ((JSONArray) hashMap.get(pair)).put(d(next));
            } else {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(d(next));
                hashMap.put(pair, jSONArray);
            }
        }
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            String str = (String) ((Pair) entry.getKey()).second;
            hashMap2.put(str, ((JSONArray) entry.getValue()).toString());
            arrayList.add(str);
        }
        String key = this.ep.getKey();
        if (key != null) {
            try {
                i.aR().putInt(key + i.fA, 0);
            } catch (Throwable unused) {
            }
        }
        c((List<String>) arrayList);
        com.appnext.base.b.b.F(this.ep.getKey());
        return hashMap2;
    }
}
