package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

class cy implements cv {

    /* renamed from: a  reason: collision with root package name */
    private final Context f840a;

    public cy(Context context) {
        this.f840a = context;
    }

    public List<cw> a() {
        ArrayList arrayList = new ArrayList();
        try {
            PackageInfo packageInfo = this.f840a.getPackageManager().getPackageInfo(this.f840a.getPackageName(), 4096);
            for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                String str = packageInfo.requestedPermissions[i];
                if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                    arrayList.add(new cw(str, true));
                } else {
                    arrayList.add(new cw(str, false));
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }
}
