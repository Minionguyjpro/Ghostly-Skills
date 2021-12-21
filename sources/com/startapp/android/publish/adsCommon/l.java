package com.startapp.android.publish.adsCommon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Pair;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.d;
import com.startapp.common.Constants;
import com.startapp.common.a.c;
import com.startapp.common.a.g;
import com.startapp.common.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: StartAppSDK */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private static List<PackageInfo> f268a = null;
    private static List<PackageInfo> b = null;
    private static long c = 0;
    private static Pair<a, String> d = null;
    private static Pair<a, String> e = null;
    private static boolean f = true;
    private static boolean g = false;
    private static a h = a.UNDEFINED;

    /* compiled from: StartAppSDK */
    private enum a {
        T1("token"),
        T2("token2"),
        UNDEFINED("");
        
        private final String text;

        private a(String str) {
            this.text = str;
        }

        public String toString() {
            return this.text;
        }
    }

    public static long a() {
        return c;
    }

    public static void a(final Context context) {
        c(context);
        f = true;
        g = false;
        h = a.UNDEFINED;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        context.getApplicationContext().registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                l.b();
                l.c(context);
            }
        }, intentFilter);
        MetaData.getInstance().addMetaDataListener(new d() {
            public void b() {
            }

            public void a() {
                l.b();
                l.c(context);
            }
        });
    }

    public static void b(Context context) {
        a(context, MetaData.getInstance().getSimpleTokenConfig().b(context));
    }

    public static void c(final Context context) {
        g.a("SimpleToken", 3, "initSimpleTokenAsync entered");
        try {
            if ((d == null || e == null) && MetaData.getInstance().getSimpleTokenConfig().b(context)) {
                com.startapp.common.g.a(g.a.HIGH, (Runnable) new Runnable() {
                    public void run() {
                        l.b(context);
                    }
                });
            }
        } catch (Exception e2) {
            com.startapp.common.a.g.a("SimpleToken", 6, "initSimpleTokenAsync failed", e2);
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "initSimpleTokenAsync", e2.getMessage(), "");
        }
    }

    public static void a(Context context, boolean z) {
        com.startapp.common.a.g.a("SimpleToken", 3, "initSimpleToken entered");
        if ((d == null || e == null) && z) {
            try {
                g(context);
                d = new Pair<>(a.T1, com.startapp.common.f.a(a(f268a)));
                e = new Pair<>(a.T2, com.startapp.common.f.a(a(b)));
                com.startapp.common.a.g.a("SimpleToken", 3, "simpleToken : [" + d + "]");
                com.startapp.common.a.g.a("SimpleToken", 3, "simpleToken2 : [" + e + "]");
            } catch (Exception e2) {
                com.startapp.common.a.g.a("SimpleToken", 6, "initSimpleToken failed", e2);
                f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "initSimpleToken", e2.getMessage(), "");
            }
        }
    }

    public static void b() {
        d = null;
        e = null;
    }

    public static Pair<String, String> d(Context context) {
        return a(context, MetaData.getInstance().getSimpleTokenConfig().b(context), MetaData.getInstance().isAlwaysSendToken(), MetaData.getInstance().isToken1Mandatory());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004d, code lost:
        if (com.startapp.android.publish.adsCommon.k.a(r4, "shared_prefs_simple_token", "").equals(r2.second) == false) goto L_0x004f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0032 A[Catch:{ Exception -> 0x0062 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033 A[Catch:{ Exception -> 0x0062 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e A[Catch:{ Exception -> 0x0062 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003f A[Catch:{ Exception -> 0x0062 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized android.util.Pair<java.lang.String, java.lang.String> a(android.content.Context r4, boolean r5, boolean r6, boolean r7) {
        /*
            java.lang.Class<com.startapp.android.publish.adsCommon.l> r0 = com.startapp.android.publish.adsCommon.l.class
            monitor-enter(r0)
            java.lang.String r1 = "SimpleToken"
            r2 = 3
            java.lang.String r3 = "getSimpleToken entered"
            com.startapp.common.a.g.a((java.lang.String) r1, (int) r2, (java.lang.String) r3)     // Catch:{ all -> 0x007c }
            android.util.Pair r1 = new android.util.Pair     // Catch:{ all -> 0x007c }
            com.startapp.android.publish.adsCommon.l$a r2 = com.startapp.android.publish.adsCommon.l.a.T1     // Catch:{ all -> 0x007c }
            java.lang.String r3 = ""
            r1.<init>(r2, r3)     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x006b
            com.startapp.android.publish.adsCommon.l$a r5 = h     // Catch:{ Exception -> 0x0062 }
            com.startapp.android.publish.adsCommon.l$a r2 = com.startapp.android.publish.adsCommon.l.a.UNDEFINED     // Catch:{ Exception -> 0x0062 }
            if (r5 != r2) goto L_0x0051
            boolean r5 = f     // Catch:{ Exception -> 0x0062 }
            boolean r2 = g     // Catch:{ Exception -> 0x0062 }
            if (r2 == 0) goto L_0x002c
            boolean r2 = f     // Catch:{ Exception -> 0x0062 }
            if (r2 == 0) goto L_0x0027
            goto L_0x002c
        L_0x0027:
            android.util.Pair r2 = f(r4)     // Catch:{ Exception -> 0x0062 }
            goto L_0x0030
        L_0x002c:
            android.util.Pair r2 = e(r4)     // Catch:{ Exception -> 0x0062 }
        L_0x0030:
            if (r7 == 0) goto L_0x0033
            goto L_0x003a
        L_0x0033:
            boolean r5 = g     // Catch:{ Exception -> 0x0062 }
            if (r5 != 0) goto L_0x0039
            r5 = 1
            goto L_0x003a
        L_0x0039:
            r5 = 0
        L_0x003a:
            g = r5     // Catch:{ Exception -> 0x0062 }
            if (r6 == 0) goto L_0x003f
            goto L_0x004f
        L_0x003f:
            java.lang.String r5 = "shared_prefs_simple_token"
            java.lang.String r6 = ""
            java.lang.String r4 = com.startapp.android.publish.adsCommon.k.a((android.content.Context) r4, (java.lang.String) r5, (java.lang.String) r6)     // Catch:{ Exception -> 0x0062 }
            java.lang.Object r5 = r2.second     // Catch:{ Exception -> 0x0062 }
            boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x0062 }
            if (r4 != 0) goto L_0x006b
        L_0x004f:
            r1 = r2
            goto L_0x006b
        L_0x0051:
            com.startapp.android.publish.adsCommon.l$a r5 = h     // Catch:{ Exception -> 0x0062 }
            com.startapp.android.publish.adsCommon.l$a r6 = com.startapp.android.publish.adsCommon.l.a.T1     // Catch:{ Exception -> 0x0062 }
            if (r5 != r6) goto L_0x005c
            android.util.Pair r4 = e(r4)     // Catch:{ Exception -> 0x0062 }
            goto L_0x0060
        L_0x005c:
            android.util.Pair r4 = f(r4)     // Catch:{ Exception -> 0x0062 }
        L_0x0060:
            r1 = r4
            goto L_0x006b
        L_0x0062:
            r4 = move-exception
            java.lang.String r5 = "SimpleToken"
            r6 = 6
            java.lang.String r7 = "failed to get simpleToken "
            com.startapp.common.a.g.a(r5, r6, r7, r4)     // Catch:{ all -> 0x007c }
        L_0x006b:
            android.util.Pair r4 = new android.util.Pair     // Catch:{ all -> 0x007c }
            java.lang.Object r5 = r1.first     // Catch:{ all -> 0x007c }
            com.startapp.android.publish.adsCommon.l$a r5 = (com.startapp.android.publish.adsCommon.l.a) r5     // Catch:{ all -> 0x007c }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x007c }
            java.lang.Object r6 = r1.second     // Catch:{ all -> 0x007c }
            r4.<init>(r5, r6)     // Catch:{ all -> 0x007c }
            monitor-exit(r0)
            return r4
        L_0x007c:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.l.a(android.content.Context, boolean, boolean, boolean):android.util.Pair");
    }

    public static void a(Pair<String, String> pair) {
        com.startapp.common.a.g.a("SimpleToken", 3, "errorSendingToken entered");
        h = a.valueOf((String) pair.first);
    }

    public static Pair<String, String> c() {
        if (d != null) {
            return new Pair<>(((a) d.first).toString(), d.second);
        }
        return new Pair<>(a.T1.toString(), "");
    }

    public static Pair<String, String> d() {
        if (e != null) {
            return new Pair<>(((a) e.first).toString(), e.second);
        }
        return new Pair<>(a.T2.toString(), "");
    }

    private static Pair<a, String> e(Context context) {
        if (d == null) {
            b(context);
        }
        k.b(context, "shared_prefs_simple_token", (String) d.second);
        f = false;
        h = a.UNDEFINED;
        return new Pair<>(a.T1, d.second);
    }

    private static Pair<a, String> f(Context context) {
        if (e == null) {
            b(context);
        }
        k.b(context, "shared_prefs_simple_token2", (String) e.second);
        f = false;
        h = a.UNDEFINED;
        return new Pair<>(a.T2, e.second);
    }

    private static synchronized void g(Context context) {
        synchronized (l.class) {
            com.startapp.common.a.g.a("SimpleToken", 3, "getPackages entered");
            PackageManager packageManager = context.getPackageManager();
            Set<String> installersList = MetaData.getInstance().getInstallersList();
            Set<String> preInstalledPackages = MetaData.getInstance().getPreInstalledPackages();
            f268a = new CopyOnWriteArrayList();
            b = new CopyOnWriteArrayList();
            try {
                List<PackageInfo> a2 = c.a(packageManager);
                c = Build.VERSION.SDK_INT >= 9 ? Long.MAX_VALUE : 0;
                PackageInfo packageInfo = null;
                for (PackageInfo next : a2) {
                    if (Build.VERSION.SDK_INT >= 9 && c > next.firstInstallTime) {
                        c = next.firstInstallTime;
                    }
                    if (!c.a(next)) {
                        f268a.add(next);
                        a(next, packageManager, installersList);
                    } else if (preInstalledPackages.contains(next.packageName)) {
                        f268a.add(next);
                    } else if (next.packageName.equals(Constants.f326a)) {
                        packageInfo = next;
                    }
                }
                f268a = b(f268a);
                b = b(b);
                if (packageInfo != null) {
                    f268a.add(0, packageInfo);
                }
            } catch (Exception e2) {
                com.startapp.common.a.g.a("SimpleToken", 6, "Could not complete getInstalledPackages", e2);
            }
        }
    }

    private static void a(PackageInfo packageInfo, PackageManager packageManager, Set<String> set) {
        try {
            String installerPackageName = packageManager.getInstallerPackageName(packageInfo.packageName);
            if (set != null && set.contains(installerPackageName)) {
                b.add(packageInfo);
            }
        } catch (Exception e2) {
            com.startapp.common.a.g.a("SimpleToken", 6, "addToPackagesFromInstallers - can't add app to list " + e2.getMessage());
        }
    }

    private static List<String> a(List<PackageInfo> list) {
        com.startapp.common.a.g.a("SimpleToken", 3, "getPackagesNames entered");
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : list) {
            arrayList.add(packageInfo.packageName);
        }
        return arrayList;
    }

    private static List<PackageInfo> b(List<PackageInfo> list) {
        if (list.size() <= 100) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        c((List<PackageInfo>) arrayList);
        return arrayList.subList(0, 100);
    }

    private static void c(List<PackageInfo> list) {
        if (Build.VERSION.SDK_INT >= 9) {
            Collections.sort(list, new Comparator<PackageInfo>() {
                /* renamed from: a */
                public int compare(PackageInfo packageInfo, PackageInfo packageInfo2) {
                    long j = packageInfo.firstInstallTime;
                    long j2 = packageInfo2.firstInstallTime;
                    if (j > j2) {
                        return -1;
                    }
                    return j == j2 ? 0 : 1;
                }
            });
        }
    }
}
