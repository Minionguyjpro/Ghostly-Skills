package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import com.yandex.metrica.IMetricaService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class be {

    public static class a implements Comparable<a> {

        /* renamed from: a  reason: collision with root package name */
        public final int f748a;
        public final int b;
        public final long c;
        public final ServiceInfo d;
        public final String e;

        public a(ServiceInfo serviceInfo, int i, int i2, long j) {
            this.f748a = i2;
            this.b = i;
            this.d = serviceInfo;
            this.c = j;
            this.e = serviceInfo.applicationInfo.packageName;
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            int i = this.b;
            if (i != aVar.b) {
                return Integer.valueOf(i).compareTo(Integer.valueOf(aVar.b));
            }
            long j = this.c;
            if (j != aVar.c) {
                return Long.valueOf(j).compareTo(Long.valueOf(aVar.c));
            }
            return 0;
        }

        public String toString() {
            return "MetricaServiceDescriptor{apiLevel=" + this.f748a + ", score=" + this.b + ", timeInstalled=" + this.c + '}';
        }
    }

    public static Intent a(Context context) {
        Intent intent = new Intent(IMetricaService.class.getName(), Uri.parse("metrica://" + context.getPackageName()));
        if (bk.b(11)) {
            intent.addFlags(32);
        }
        return intent;
    }

    public static List<ResolveInfo> a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        if (queryIntentServices != null) {
            return queryIntentServices;
        }
        return new ArrayList();
    }

    public static List<a> b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : a(context, a(context))) {
            ServiceInfo serviceInfo = next.serviceInfo;
            if (!((!serviceInfo.enabled) | (!serviceInfo.exported)) && !(!bi.a(serviceInfo.permission))) {
                long a2 = a(packageManager, serviceInfo.packageName);
                if (a(packageManager, serviceInfo.packageName, "android.permission.INTERNET")) {
                    int a3 = a((PackageItemInfo) serviceInfo);
                    arrayList.add(new a(next.serviceInfo, (a3 << 5) + ((a(packageManager, serviceInfo.packageName, "android.permission.ACCESS_COARSE_LOCATION") ? 1 : 0) * true) + ((a(packageManager, serviceInfo.packageName, "android.permission.ACCESS_FINE_LOCATION") ? 1 : 0) * true) + ((a(packageManager, serviceInfo.packageName, "android.permission.ACCESS_WIFI_STATE") ? 1 : 0) * true) + ((a(packageManager, serviceInfo.packageName, "android.permission.ACCESS_NETWORK_STATE") ? 1 : 0) * true) + ((a(packageManager, serviceInfo.packageName, "android.permission.READ_PHONE_STATE") ? 1 : 0) * true), a3, a2));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a A[Catch:{ Exception -> 0x002e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long a(android.content.pm.PackageManager r8, java.lang.String r9) {
        /*
            r0 = 8
            r1 = 0
            r2 = -1
            boolean r0 = com.yandex.metrica.impl.bk.b((int) r0)     // Catch:{ Exception -> 0x0018 }
            if (r0 == 0) goto L_0x0018
            android.content.pm.PackageInfo r0 = r8.getPackageInfo(r9, r1)     // Catch:{ Exception -> 0x0018 }
            long r4 = r0.firstInstallTime     // Catch:{ Exception -> 0x0018 }
            long r6 = r0.lastUpdateTime     // Catch:{ Exception -> 0x0018 }
            long r4 = java.lang.Math.max(r4, r6)     // Catch:{ Exception -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r4 = r2
        L_0x0019:
            android.content.pm.ApplicationInfo r8 = r8.getApplicationInfo(r9, r1)     // Catch:{ Exception -> 0x002e }
            java.lang.String r8 = r8.sourceDir     // Catch:{ Exception -> 0x002e }
            java.io.File r9 = new java.io.File     // Catch:{ Exception -> 0x002e }
            r9.<init>(r8)     // Catch:{ Exception -> 0x002e }
            boolean r8 = r9.exists()     // Catch:{ Exception -> 0x002e }
            if (r8 == 0) goto L_0x002e
            long r2 = r9.lastModified()     // Catch:{ Exception -> 0x002e }
        L_0x002e:
            long r8 = java.lang.Math.max(r4, r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.be.a(android.content.pm.PackageManager, java.lang.String):long");
    }

    private static boolean a(PackageManager packageManager, String str, String str2) {
        return str2 == null || packageManager.checkPermission(str2, str) == 0;
    }

    public static int a(PackageItemInfo packageItemInfo) {
        if (packageItemInfo.metaData != null) {
            try {
                return packageItemInfo.metaData.getInt("metrica:api:level");
            } catch (Exception unused) {
            }
        }
        return -1;
    }

    public static Intent c(Context context) {
        return a(context).putExtras(e(context)).setPackage(context.getApplicationContext().getPackageName());
    }

    private static Bundle e(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            return bundle == null ? new Bundle() : bundle;
        } catch (Exception unused) {
            return new Bundle();
        }
    }
}
