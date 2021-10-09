package com.yandex.metrica.impl;

import android.content.Context;
import android.util.SparseArray;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.ob.dg;

public abstract class ai {

    interface a {
        void a(Context context);
    }

    /* access modifiers changed from: protected */
    public abstract int a(dg dgVar);

    /* access modifiers changed from: package-private */
    public abstract SparseArray<a> a();

    /* access modifiers changed from: protected */
    public abstract void a(dg dgVar, int i);

    public void a(Context context) {
        dg dgVar = new dg(context);
        int a2 = a(dgVar);
        int b = b();
        if (a2 < b) {
            SparseArray<a> a3 = a();
            while (a2 <= b) {
                a aVar = a3.get(a2);
                if (aVar != null) {
                    aVar.a(context);
                }
                a2++;
            }
            a(dgVar, b);
            dgVar.k();
        }
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return YandexMetrica.getLibraryApiLevel();
    }
}
