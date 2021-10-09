package com.yandex.metrica.impl.ob;

import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.ob.cl;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ag extends af {
    protected ag(t tVar) {
        super(tVar);
    }

    public boolean a(h hVar) {
        t a2 = a();
        if (!a2.C().d() || !a2.B()) {
            return false;
        }
        ca I = a2.I();
        HashSet<cm> b = b();
        try {
            ArrayList<cm> c = c();
            if (d.a(b, c)) {
                a2.r();
                return false;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<cm> it = c.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().a());
            }
            a2.g(new h(hVar).a(p.a.EVENT_TYPE_APP_FEATURES.a()).c(new JSONObject().put("features", jSONArray).toString()));
            I.c(jSONArray.toString());
            return false;
        } catch (JSONException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public HashSet<cm> b() {
        String b = a().I().b();
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            HashSet<cm> hashSet = new HashSet<>();
            JSONArray jSONArray = new JSONArray(b);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(new cm(jSONArray.getJSONObject(i)));
            }
            return hashSet;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<cm> c() {
        cl clVar;
        try {
            t a2 = a();
            PackageInfo packageInfo = a2.m().getPackageManager().getPackageInfo(a2.m().getPackageName(), 16384);
            ArrayList<cm> arrayList = new ArrayList<>();
            if (bk.a(24)) {
                clVar = new cl.a();
            } else {
                clVar = new cl.b();
            }
            if (packageInfo.reqFeatures != null) {
                for (FeatureInfo b : packageInfo.reqFeatures) {
                    arrayList.add(clVar.b(b));
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }
}
