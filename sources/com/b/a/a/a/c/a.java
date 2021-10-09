package com.b.a.a.a.c;

import com.b.a.a.a.b.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f989a = new a();
    private final ArrayList<i> b = new ArrayList<>();
    private final ArrayList<i> c = new ArrayList<>();

    private a() {
    }

    public static a a() {
        return f989a;
    }

    public void a(i iVar) {
        this.b.add(iVar);
    }

    public Collection<i> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public void b(i iVar) {
        boolean d = d();
        this.c.add(iVar);
        if (!d) {
            e.a().b();
        }
    }

    public Collection<i> c() {
        return Collections.unmodifiableCollection(this.c);
    }

    public void c(i iVar) {
        boolean d = d();
        this.b.remove(iVar);
        this.c.remove(iVar);
        if (d && !d()) {
            e.a().c();
        }
    }

    public boolean d() {
        return this.c.size() > 0;
    }
}
