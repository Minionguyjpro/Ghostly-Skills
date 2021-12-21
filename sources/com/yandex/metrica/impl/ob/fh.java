package com.yandex.metrica.impl.ob;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.mopub.network.ImpressionData;
import com.yandex.metrica.impl.ob.fu;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class fh {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f902a = fh.class.getSimpleName();
    private fb b;
    private fb c;
    private fs d;
    private Map<String, String> e;
    /* access modifiers changed from: private */
    public fi f;
    private String g;
    private fj h;
    private long i;
    private final ReentrantLock j = new ReentrantLock();

    fh(fe feVar, ey eyVar, fs fsVar, fd fdVar) {
        this.b = eyVar.c();
        this.c = eyVar.a();
        this.d = fsVar;
        this.g = fdVar.b();
        HashMap hashMap = new HashMap();
        this.e = hashMap;
        hashMap.put("app_id", feVar.c());
        Map<String, String> map = this.e;
        map.put("app_platform", "android_" + Build.VERSION.RELEASE);
        this.e.put("manufacturer", Build.MANUFACTURER);
        this.e.put("model", Build.MODEL);
        this.e.put(ImpressionData.APP_VERSION, feVar.a());
        this.i = fdVar.a();
    }

    /* access modifiers changed from: package-private */
    public ReentrantLock a() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean b() {
        if (j()) {
            Log.i(f902a, "starting pins update on error");
            JSONObject g2 = g();
            if (g2 != null) {
                return a(g2);
            }
            h();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public synchronized void c() {
        if (d() && j()) {
            Log.i(f902a, "starting pins update on schedule");
            fi i2 = i();
            this.f = i2;
            this.d.a(i2, new fu.b<JSONObject>() {
                public void a(JSONObject jSONObject) {
                    boolean unused = fh.this.a(jSONObject);
                    fi unused2 = fh.this.f = null;
                }
            }, new fu.a() {
                public void a(fr frVar) {
                    String f = fh.f902a;
                    Log.i(f, "can't update pins on schedule: " + frVar.getMessage());
                    fh.this.h();
                    fi unused = fh.this.f = null;
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(fj fjVar) {
        this.h = fjVar;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        if (!e()) {
            return a(this.b, this.i) || a(this.c, this.i);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.f != null;
    }

    private JSONObject g() {
        try {
            fv a2 = fv.a();
            this.d.a(i(), a2, a2);
            return (JSONObject) a2.get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e2) {
            String str = f902a;
            Log.i(str, "can't update pins on error: " + e2.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean a(JSONObject jSONObject) {
        try {
            a(jSONObject.getJSONArray("pins-sha256"), this.b);
            a(jSONObject.getJSONArray("blacklist"), this.c);
            Log.i(f902a, "pins have been updated");
            return true;
        } catch (JSONException e2) {
            String str = f902a;
            Log.i(str, "can't update pins: " + e2.getMessage());
            return false;
        }
    }

    private static void a(JSONArray jSONArray, fb fbVar) throws JSONException {
        fbVar.a();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            fbVar.a(jSONArray.getString(i2));
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        this.b.d();
        this.c.d();
    }

    private static boolean a(fb fbVar, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis - fbVar.c() >= j2 || currentTimeMillis < fbVar.c();
    }

    private fi i() {
        String a2 = this.h.a();
        if (TextUtils.isEmpty(a2)) {
            this.e.remove("uuid");
        } else {
            this.e.put("uuid", a2);
        }
        return new fi(this.g, this.e);
    }

    private boolean j() {
        return this.d != null;
    }
}
