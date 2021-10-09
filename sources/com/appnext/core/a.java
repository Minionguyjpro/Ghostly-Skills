package com.appnext.core;

import android.os.Handler;
import com.appnext.core.d;
import java.util.ArrayList;
import java.util.Iterator;

public final class a {
    private ArrayList<?> ads = null;
    private Long fH = 0L;
    /* access modifiers changed from: private */
    public ArrayList<d.a> fI = new ArrayList<>();
    private String fJ = "";
    private String placementID;
    private int state = 0;

    public final void a(d.a aVar) {
        if (aVar != null) {
            this.fI.add(aVar);
        }
    }

    public final void a(a aVar) {
        ArrayList<d.a> arrayList;
        if (aVar != null && (arrayList = aVar.fI) != null) {
            this.fI.addAll(arrayList);
        }
    }

    public final void b(d.a aVar) {
        if (aVar != null) {
            this.fI.remove(aVar);
        }
    }

    public final Long aU() {
        return this.fH;
    }

    public final ArrayList<?> getAds() {
        if (this.ads == null) {
            return null;
        }
        ArrayList<?> arrayList = new ArrayList<>();
        Iterator<?> it = this.ads.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public final void setPlacementID(String str) {
        this.placementID = str;
    }

    public final int getState() {
        return this.state;
    }

    public final String getPlacementID() {
        return this.placementID;
    }

    public final void setState(int i) {
        this.state = i;
    }

    public final void a(Long l) {
        this.fH = l;
    }

    public final void d(ArrayList<?> arrayList) {
        a(arrayList, true);
    }

    public final void a(ArrayList<?> arrayList, boolean z) {
        this.ads = arrayList;
        if (z) {
            this.fH = Long.valueOf(System.currentTimeMillis());
        }
    }

    public final String A() {
        return this.fJ;
    }

    public final void N(String str) {
        this.fJ = str;
    }

    public final synchronized void e(final ArrayList<?> arrayList) {
        new Handler().post(new Runnable() {
            public final void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(a.this.fI);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    d.a aVar = (d.a) it.next();
                    if (aVar != null) {
                        aVar.a(arrayList);
                    }
                }
                a.this.fI.clear();
            }
        });
    }

    public final synchronized void O(final String str) {
        new Handler().post(new Runnable() {
            public final void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(a.this.fI);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    d.a aVar = (d.a) it.next();
                    if (aVar != null) {
                        aVar.error(str);
                    }
                }
                a.this.fI.clear();
            }
        });
    }
}
