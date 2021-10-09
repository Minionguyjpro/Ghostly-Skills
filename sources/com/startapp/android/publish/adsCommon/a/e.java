package com.startapp.android.publish.adsCommon.a;

import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.a.g;
import com.startapp.common.c.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
public class e implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a  reason: collision with root package name */
    private transient Set<Class<? extends c>> f190a = new HashSet();
    private boolean applyOnBannerRefresh = true;
    @f(b = HashMap.class, c = ArrayList.class, d = AdPreferences.Placement.class, e = c.class)
    private Map<AdPreferences.Placement, List<c>> placements = new HashMap();
    @f(b = ArrayList.class, c = c.class)
    private List<c> session = new ArrayList();
    @f(b = HashMap.class, c = ArrayList.class, e = c.class)
    private Map<String, List<c>> tags = new HashMap();

    public boolean a() {
        return this.applyOnBannerRefresh;
    }

    public synchronized f a(AdPreferences.Placement placement, String str) {
        f a2;
        String str2;
        this.f190a.clear();
        a2 = a(this.tags.get(str), b.a().a(str), d.TAG, str);
        if (a2.a()) {
            a2 = a(this.placements.get(placement), b.a().a(placement), d.PLACEMENT, placement.toString());
            if (a2.a()) {
                a2 = a(this.session, b.a().c(), d.SESSION, "session");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("shouldDisplayAd result: ");
        sb.append(a2.a());
        if (a2.a()) {
            str2 = "";
        } else {
            str2 = " because of rule " + a2.b();
        }
        sb.append(str2);
        g.a("AdRules", 3, sb.toString());
        return a2;
    }

    private f a(List<c> list, List<a> list2, d dVar, String str) {
        String str2;
        if (list == null) {
            return new f(true);
        }
        for (c next : list) {
            if (next.a() || !this.f190a.contains(next.getClass())) {
                if (!next.a(list2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(next.getClass().getSimpleName());
                    sb.append("_");
                    sb.append(dVar);
                    if (Constants.a().booleanValue()) {
                        str2 = " " + str + ":" + next;
                    } else {
                        str2 = "";
                    }
                    sb.append(str2);
                    return new f(false, sb.toString());
                }
                this.f190a.add(next.getClass());
            }
        }
        return new f(true);
    }

    public void b() {
        this.f190a = new HashSet();
    }
}
