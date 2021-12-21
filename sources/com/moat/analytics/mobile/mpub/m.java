package com.moat.analytics.mobile.mpub;

import android.os.Build;
import com.appnext.base.b.d;
import com.moat.analytics.mobile.mpub.w;
import org.json.JSONArray;
import org.json.JSONObject;

class m {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1175a = false;
    private boolean b = false;
    private boolean c = false;
    private int d = 200;
    private int e = 10;

    m(String str) {
        a(str);
    }

    private void a(String str) {
        int i;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("sa");
                boolean equals = string.equals("422d7e65812d34458dfd0c5f14e8141470b6e2ae");
                boolean equals2 = string.equals("8f1d08a2d6496191a5ebae8f0590f513e2619489");
                if ((string.equals(d.fe) || equals || equals2) && !a(jSONObject) && !b(jSONObject)) {
                    this.f1175a = true;
                    this.b = equals;
                    this.c = equals2;
                    if (equals2) {
                        this.b = true;
                    }
                }
                if (jSONObject.has("in") && (i = jSONObject.getInt("in")) >= 100 && i <= 1000) {
                    this.d = i;
                }
                if (jSONObject.has("es")) {
                    this.e = jSONObject.getInt("es");
                }
            } catch (Exception e2) {
                this.f1175a = false;
                this.b = false;
                this.d = 200;
                n.a(e2);
            }
        }
    }

    private boolean a(JSONObject jSONObject) {
        try {
            if (16 > Build.VERSION.SDK_INT) {
                return true;
            }
            if (jSONObject.has("ob")) {
                JSONArray jSONArray = jSONObject.getJSONArray("ob");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getInt(i) == Build.VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    private boolean b(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ap")) {
                String b2 = s.c().b();
                JSONArray jSONArray = jSONObject.getJSONArray("ap");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getString(i).contentEquals(b2)) {
                        return true;
                    }
                }
            }
        } catch (Exception e2) {
            n.a(e2);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public w.d e() {
        return this.f1175a ? w.d.ON : w.d.OFF;
    }
}
