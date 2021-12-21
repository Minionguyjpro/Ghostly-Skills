package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

class cz implements cv {

    /* renamed from: a  reason: collision with root package name */
    private final Context f841a;

    public cz(Context context) {
        this.f841a = context;
    }

    public List<cw> a() {
        ArrayList arrayList = new ArrayList();
        try {
            for (String cwVar : this.f841a.getPackageManager().getPackageInfo(this.f841a.getPackageName(), 4096).requestedPermissions) {
                arrayList.add(new cw(cwVar, true));
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }
}
