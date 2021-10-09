package com.appnext.base.operations.imp;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.appnext.base.a.b.b;
import com.appnext.base.b.d;
import com.appnext.base.b.e;
import com.appnext.base.b.f;
import com.appnext.base.b.h;
import com.appnext.base.b.j;
import com.appnext.base.operations.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class acap extends c {
    private static final long et = 1;
    private static final String eu = "ft";
    private static final String ev = "nfga";
    private static final String ew = "android";

    public acap(com.appnext.base.a.b.c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    public final boolean aF() {
        if (Build.VERSION.SDK_INT < 21) {
            return f.a(e.getContext(), "android.permission.GET_TASKS");
        }
        return j.f(e.getContext().getApplicationContext());
    }

    /* access modifiers changed from: protected */
    public List<b> getData() {
        try {
            List<String> a2 = j.a(e.getContext(), (long) j.g(ay().al(), ay().am()), ay().a(eu, 1));
            if (a2 == null) {
                a2 = null;
            } else {
                Iterator<String> it = a2.iterator();
                while (it.hasNext()) {
                    if (it.next().equalsIgnoreCase(ew)) {
                        it.remove();
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            if (a2 == null || a2.isEmpty()) {
                arrayList.add(new b(getKey(), ev, d.a.String.toString()));
            } else {
                for (String bVar : a2) {
                    arrayList.add(new b(getKey(), bVar, d.a.String.toString()));
                }
            }
            return arrayList;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public List<b> b(List<b> list) {
        if (list == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (b next : list) {
            String L = h.aO().L(next.ai());
            if (!TextUtils.isEmpty(L) && !L.equals(ev) && !hashMap.containsKey(L)) {
                hashMap.put(L, next);
            }
        }
        if (hashMap.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(hashMap.values());
        if (arrayList.isEmpty()) {
            return null;
        }
        Collections.sort(arrayList, new CollectedDataModelByDateComparator());
        return arrayList;
    }

    private static List<String> f(List<String> list) {
        if (list == null) {
            return null;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(ew)) {
                it.remove();
            }
        }
        return list;
    }

    private class CollectedDataModelByDateComparator implements Comparator<b> {
        private CollectedDataModelByDateComparator() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return ((b) obj).aj().getTime() > ((b) obj2).aj().getTime() ? 1 : 0;
        }

        public static int a(b bVar, b bVar2) {
            return bVar.aj().getTime() > bVar2.aj().getTime() ? 1 : 0;
        }
    }

    /* access modifiers changed from: protected */
    public String getKey() {
        return acap.class.getSimpleName();
    }
}
