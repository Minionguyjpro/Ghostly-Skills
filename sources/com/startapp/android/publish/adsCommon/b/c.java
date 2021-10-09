package com.startapp.android.publish.adsCommon.b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.b;
import com.startapp.android.publish.adsCommon.e;
import com.startapp.android.publish.adsCommon.h;
import com.startapp.android.publish.adsCommon.l;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.common.a.g;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: StartAppSDK */
public class c {
    public static boolean a(Context context, Ad ad) {
        if (ad == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        if (ad instanceof e) {
            return a(context, a(((e) ad).f(), 0), 0, (Set<String>) hashSet, (List<a>) new ArrayList()).booleanValue();
        }
        if (!(ad instanceof h)) {
            return false;
        }
        List<AdDetails> a2 = a(context, ((h) ad).d(), 0, (Set<String>) hashSet, false);
        if (a2 == null || a2.size() == 0) {
            return true;
        }
        return false;
    }

    public static List<AdDetails> a(Context context, List<AdDetails> list, int i, Set<String> set) {
        return a(context, list, i, set, true);
    }

    public static List<AdDetails> a(Context context, List<AdDetails> list, int i, Set<String> set, boolean z) {
        Context context2 = context;
        int i2 = i;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        boolean z2 = false;
        for (AdDetails next : list) {
            a aVar = new a(next.getTrackingUrl(), next.getAppPresencePackage(), i2, next.getMinAppVersion());
            boolean z3 = next.getAppPresencePackage() != null && next.getAppPresencePackage().startsWith("!");
            String appPresencePackage = next.getAppPresencePackage();
            if (z3) {
                appPresencePackage = appPresencePackage.substring(1);
            }
            boolean a2 = com.startapp.common.a.c.a(context2, appPresencePackage, next.getMinAppVersion());
            boolean z4 = b.a().E() && ((a2 && !z3) || (!a2 && z3));
            arrayList3.add(aVar);
            if (z4) {
                aVar.b(a2);
                aVar.a(false);
                if (!z3) {
                    arrayList2.add(next);
                    arrayList4.add(aVar);
                }
                set.add(next.getPackageName());
                g.a("AppPresenceUtil", 3, "App Presence:[" + next.getPackageName() + "]");
                z2 = true;
            } else {
                Set<String> set2 = set;
                arrayList.add(next);
            }
            g.a("AppPresenceUtil", 3, "App Not Presence:[" + next.getPackageName() + "]");
        }
        if (arrayList.size() < 5 && (list.size() != 1 || i2 > 0)) {
            int min = Math.min(5 - arrayList.size(), arrayList2.size());
            arrayList.addAll(arrayList2.subList(0, min));
            for (a a3 : arrayList4.subList(0, min)) {
                a3.a(true);
            }
        }
        if (z2) {
            l.c(context);
            if (z) {
                new b(context2, arrayList3).a();
            }
        }
        return arrayList;
    }

    public static List<a> a(String str, int i) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = new String[0];
        String a2 = i.a(str, "@tracking@", "@tracking@");
        if (a2 != null) {
            strArr = a2.split(",");
        }
        String[] strArr2 = new String[0];
        String a3 = i.a(str, "@appPresencePackage@", "@appPresencePackage@");
        if (a3 != null) {
            strArr2 = a3.split(",");
        }
        String[] strArr3 = new String[0];
        String a4 = i.a(str, "@minAppVersion@", "@minAppVersion@");
        if (a4 != null) {
            strArr3 = a4.split(",");
        }
        int i2 = 0;
        while (i2 < strArr2.length) {
            arrayList.add(new a(strArr.length > i2 ? strArr[i2] : null, strArr2[i2], i, strArr3.length > i2 ? Integer.valueOf(strArr3[i2]).intValue() : 0));
            i2++;
        }
        while (i2 < strArr.length) {
            arrayList.add(new a(strArr[i2], "", i, strArr3.length > i2 ? Integer.valueOf(strArr3[i2]).intValue() : 0));
            i2++;
        }
        return arrayList;
    }

    public static Boolean a(Context context, List<a> list, int i, Set<String> set, List<a> list2) {
        boolean z = false;
        for (a next : list) {
            boolean startsWith = next.b().startsWith("!");
            String b = next.b();
            if (startsWith) {
                b = b.substring(1);
            }
            boolean a2 = com.startapp.common.a.c.a(context, b, next.e());
            if ((!startsWith && a2) || (startsWith && !a2)) {
                g.a("AppPresenceUtil", 3, "in isAppPresent, skipAd is true");
                next.b(a2);
                z = i == 0;
                if (z && !startsWith) {
                    set.add(next.b());
                } else if (!z && next.a() != null) {
                    next.a(next.a() + "&isShown=" + next.c() + "&appPresence=" + next.d());
                }
            }
            list2.add(next);
        }
        if (z) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                list2.get(i2).a(false);
            }
        }
        return Boolean.valueOf(z);
    }
}
