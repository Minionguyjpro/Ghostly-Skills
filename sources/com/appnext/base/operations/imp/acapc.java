package com.appnext.base.operations.imp;

import android.os.Bundle;
import android.text.TextUtils;
import com.appnext.base.a.b.a;
import com.appnext.base.a.b.b;
import com.appnext.base.a.b.c;
import com.appnext.base.b.d;
import com.appnext.base.b.h;
import com.startapp.android.mediation.admob.StartAppNative;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class acapc extends acap {
    public acapc(c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    /* access modifiers changed from: protected */
    public final List<b> b(List<b> list) {
        Class<acapc> cls = acapc.class;
        List<b> b = super.b(list);
        if (b == null || b.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (b ai : b) {
            String L = h.aO().L(ai.ai());
            if (TextUtils.isEmpty(L)) {
                return null;
            }
            List<a> r = com.appnext.base.a.a.X().Z().r(L);
            if (r.size() > 0) {
                Integer ag = r.get(0).ag();
                if (!hashMap.containsKey(ag)) {
                    hashMap.put(ag, 1);
                } else {
                    hashMap.put(ag, Integer.valueOf(((Integer) hashMap.get(ag)).intValue() + 1));
                }
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry entry : hashMap.entrySet()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(StartAppNative.EXTRAS_CATEGORY, entry.getKey());
                jSONObject.put("appcount", entry.getValue());
            } catch (Throwable unused) {
            }
            jSONArray.put(jSONObject);
        }
        String K = h.aO().K(jSONArray.toString());
        if (TextUtils.isEmpty(K)) {
            return null;
        }
        b bVar = new b(cls.getSimpleName(), cls.getSimpleName(), K, new Date(), d.a.JSONArray.getType());
        ArrayList arrayList = new ArrayList();
        arrayList.add(bVar);
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final d.a aG() {
        return d.a.JSONArray;
    }

    /* access modifiers changed from: protected */
    public final String getKey() {
        return acapc.class.getSimpleName();
    }
}
