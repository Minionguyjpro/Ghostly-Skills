package com.b.a.a.a.h;

import android.view.View;
import android.view.ViewParent;
import com.b.a.a.a.b.i;
import com.b.a.a.a.e.f;
import com.b.a.a.a.f.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<View, String> f1011a = new HashMap<>();
    private final HashMap<View, ArrayList<String>> b = new HashMap<>();
    private final HashSet<View> c = new HashSet<>();
    private final HashSet<String> d = new HashSet<>();
    private final HashSet<String> e = new HashSet<>();
    private boolean f;

    private void a(View view, i iVar) {
        ArrayList arrayList = this.b.get(view);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.b.put(view, arrayList);
        }
        arrayList.add(iVar.g());
    }

    private void a(i iVar) {
        for (a aVar : iVar.d()) {
            View view = (View) aVar.get();
            if (view != null) {
                a(view, iVar);
            }
        }
    }

    private boolean d(View view) {
        if (!view.hasWindowFocus()) {
            return false;
        }
        HashSet hashSet = new HashSet();
        while (view != null) {
            if (!f.d(view)) {
                return false;
            }
            hashSet.add(view);
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        this.c.addAll(hashSet);
        return true;
    }

    public String a(View view) {
        if (this.f1011a.size() == 0) {
            return null;
        }
        String str = this.f1011a.get(view);
        if (str != null) {
            this.f1011a.remove(view);
        }
        return str;
    }

    public HashSet<String> a() {
        return this.d;
    }

    public ArrayList<String> b(View view) {
        if (this.b.size() == 0) {
            return null;
        }
        ArrayList<String> arrayList = this.b.get(view);
        if (arrayList != null) {
            this.b.remove(view);
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public HashSet<String> b() {
        return this.e;
    }

    public d c(View view) {
        return this.c.contains(view) ? d.PARENT_VIEW : this.f ? d.OBSTRUCTION_VIEW : d.UNDERLYING_VIEW;
    }

    public void c() {
        com.b.a.a.a.c.a a2 = com.b.a.a.a.c.a.a();
        if (a2 != null) {
            for (i next : a2.c()) {
                View h = next.h();
                if (next.i()) {
                    if (h == null || !d(h)) {
                        this.e.add(next.g());
                    } else {
                        this.d.add(next.g());
                        this.f1011a.put(h, next.g());
                        a(next);
                    }
                }
            }
        }
    }

    public void d() {
        this.f1011a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.f = false;
    }

    public void e() {
        this.f = true;
    }
}
