package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import android.util.Base64;
import com.yandex.metrica.impl.utils.b;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class t {

    /* renamed from: a  reason: collision with root package name */
    JSONObject f950a;

    public t(String str) {
        try {
            this.f950a = new JSONObject(str);
        } catch (Exception unused) {
            this.f950a = new JSONObject();
        }
    }

    public t a() {
        try {
            c();
            b();
        } catch (Exception unused) {
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public t b() throws JSONException {
        ((JSONObject) a(this.f950a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000);
        return this;
    }

    /* access modifiers changed from: package-private */
    public t a(Context context, boolean z) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = (JSONObject) a((JSONObject) a(this.f950a, "dfid", new JSONObject()), "au", new JSONObject());
        JSONArray jSONArray = (JSONArray) a(jSONObject, "aun", new JSONArray());
        JSONArray jSONArray2 = (JSONArray) a(jSONObject, "ausf", new JSONArray());
        JSONArray jSONArray3 = (JSONArray) a(jSONObject, "audf", new JSONArray());
        JSONArray jSONArray4 = (JSONArray) a(jSONObject, "aulu", new JSONArray());
        JSONArray jSONArray5 = new JSONArray();
        if (z) {
            a(jSONObject, "aufi", jSONArray5);
        }
        HashSet hashSet = new HashSet();
        for (ResolveInfo resolveInfo : bk.a(context, new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuYWN0aW9uLk1BSU4=", 0), "UTF-8"), new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuY2F0ZWdvcnkuTEFVTkNIRVI=", 0), "UTF-8"))) {
            ApplicationInfo applicationInfo = resolveInfo.activityInfo.applicationInfo;
            if (hashSet.add(applicationInfo.packageName)) {
                jSONArray.put(applicationInfo.packageName);
                boolean z2 = (applicationInfo.flags & 1) == 1;
                jSONArray2.put(z2);
                jSONArray4.put(new File(applicationInfo.sourceDir).lastModified());
                jSONArray3.put(true ^ applicationInfo.enabled);
                if (z) {
                    if (z2) {
                        jSONArray5.put(0);
                    } else if (bk.c(context, applicationInfo.packageName) == null) {
                        jSONArray5.put(0);
                    } else {
                        jSONArray5.put(bk.c(context, applicationInfo.packageName).firstInstallTime / 1000);
                    }
                }
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public t a(Context context) throws JSONException {
        JSONObject jSONObject = (JSONObject) a((JSONObject) a(this.f950a, "dfid", new JSONObject()), "apps", new JSONObject());
        JSONArray jSONArray = (JSONArray) a(jSONObject, "names", new JSONArray());
        JSONArray jSONArray2 = (JSONArray) a(jSONObject, "system_flags", new JSONArray());
        JSONArray jSONArray3 = (JSONArray) a(jSONObject, "disabled_flags", new JSONArray());
        JSONArray jSONArray4 = (JSONArray) a(jSONObject, "first_install_time", new JSONArray());
        JSONArray jSONArray5 = (JSONArray) a(jSONObject, "last_update_time", new JSONArray());
        jSONObject.put("version", 0);
        for (PackageInfo next : context.getPackageManager().getInstalledPackages(0)) {
            jSONArray.put(next.packageName);
            jSONArray2.put((next.applicationInfo.flags & 1) == 1);
            jSONArray3.put(!next.applicationInfo.enabled);
            jSONArray4.put(next.firstInstallTime / 1000);
            jSONArray5.put(next.lastUpdateTime / 1000);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public t c() throws JSONException {
        JSONObject jSONObject = (JSONObject) a(this.f950a, "dfid", new JSONObject());
        long a2 = am.a(true);
        long a3 = am.a(false);
        long c = am.c(true);
        long c2 = am.c(false);
        jSONObject.put("tds", a2 + a3);
        jSONObject.put("fds", c + c2);
        return this;
    }

    static <T> T a(JSONObject jSONObject, String str, T t) throws JSONException {
        if (!jSONObject.has(str)) {
            jSONObject.put(str, t);
        }
        return jSONObject.get(str);
    }

    public String toString() {
        return this.f950a.toString();
    }

    public String d() {
        return Base64.encodeToString(new b().a(bi.c(this.f950a.toString())), 0);
    }
}
