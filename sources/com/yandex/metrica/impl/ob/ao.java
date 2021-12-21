package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;
import java.util.ArrayList;
import java.util.Iterator;

public class ao extends af {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<af> f783a;

    public ao(t tVar) {
        super(tVar);
        ArrayList<af> arrayList = new ArrayList<>();
        this.f783a = arrayList;
        arrayList.add(new ai(tVar));
        if (tVar.l().d() && tVar.m().getPackageName().equals(tVar.l().b())) {
            this.f783a.add(new al(tVar, bp.a(a().m()), new cx()));
            this.f783a.add(new ag(tVar));
        }
    }

    public boolean a(h hVar) {
        Iterator<af> it = this.f783a.iterator();
        while (it.hasNext()) {
            it.next().a(hVar);
        }
        return false;
    }
}
