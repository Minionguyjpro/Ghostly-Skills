package com.startapp.android.publish.adsCommon.b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.common.g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private List<a> f218a;
    private Context b;
    private List<String> c = new ArrayList();

    public b(Context context, List<a> list) {
        this.f218a = list;
        this.b = context;
    }

    public void a() {
        g.a(g.a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                b.this.b();
            }
        });
    }

    /* access modifiers changed from: protected */
    public Boolean b() {
        boolean z;
        com.startapp.common.a.g.a(3, "in doInBackground handler");
        try {
            c();
            z = true;
        } catch (Exception e) {
            f.a(this.b, d.EXCEPTION, "AppPresenceHandler.doInBackground - sendAdImpressions failed", e.getMessage(), "");
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private void c() {
        a(this.f218a, this.c);
        for (int i = 0; i < this.c.size(); i++) {
            String str = this.c.get(i);
            if (str.length() != 0) {
                c.a(this.b, str, new com.startapp.android.publish.adsCommon.d.b().setNonImpressionReason("APP_PRESENCE"));
            }
        }
    }

    private void a(List<a> list, List<String> list2) {
        com.startapp.common.a.g.a(3, "in getAppPresenceDParameter()");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (a next : list) {
            if (!next.c()) {
                String a2 = a(next.a());
                if (next.d()) {
                    arrayList.add("d=" + a2);
                } else {
                    arrayList2.add("d=" + a2);
                }
            }
        }
        com.startapp.common.a.g.a(3, "appPresence tracking size = " + arrayList.size() + " normal size = " + arrayList2.size());
        if (!arrayList.isEmpty()) {
            list2.addAll(c.a((List<String>) arrayList, "false", "true"));
        }
        if (!arrayList2.isEmpty()) {
            list2.addAll(c.a((List<String>) arrayList2, "false", "false"));
        }
    }

    private String a(String str) {
        return str.split("tracking/adImpression[?]d=")[1];
    }
}
