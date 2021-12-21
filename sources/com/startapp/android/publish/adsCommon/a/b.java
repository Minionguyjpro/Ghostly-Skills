package com.startapp.android.publish.adsCommon.a;

import com.startapp.android.publish.common.model.AdPreferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static b f187a = new b();
    private List<a> b = new ArrayList();
    private Map<AdPreferences.Placement, List<a>> c = new HashMap();
    private Map<String, List<a>> d = new HashMap();

    public static b a() {
        return f187a;
    }

    public void b() {
        this.b.clear();
        this.c.clear();
        this.d.clear();
    }

    public List<a> c() {
        return this.b;
    }

    public List<a> a(AdPreferences.Placement placement) {
        return this.c.get(placement);
    }

    public List<a> a(String str) {
        return this.d.get(str);
    }

    public synchronized void a(a aVar) {
        this.b.add(0, aVar);
        List list = this.c.get(aVar.a());
        if (list == null) {
            list = new ArrayList();
            this.c.put(aVar.a(), list);
        }
        list.add(0, aVar);
        List list2 = this.d.get(aVar.b());
        if (list2 == null) {
            list2 = new ArrayList();
            this.d.put(aVar.b(), list2);
        }
        list2.add(0, aVar);
    }

    public int d() {
        return this.b.size();
    }
}
