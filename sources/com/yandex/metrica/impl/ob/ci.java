package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.LocalServerSocket;
import android.net.Uri;
import android.text.TextUtils;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.r;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ci {

    /* renamed from: a  reason: collision with root package name */
    private final Object f823a;
    private final a b;
    private final ck c;
    private ch d;

    private static class b {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final ci f825a = new ci((byte) 0);
    }

    /* synthetic */ ci(byte b2) {
        this();
    }

    public static ci a() {
        return b.f825a;
    }

    private ci() {
        this.f823a = new Object();
        this.b = new a(this, (byte) 0);
        this.c = new ck(this);
    }

    /* access modifiers changed from: package-private */
    public a b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public ch c() {
        return this.d;
    }

    public String d() {
        ch c2 = c();
        if (c2 == null) {
            return null;
        }
        return c2.c();
    }

    /* access modifiers changed from: package-private */
    public ch a(Context context, String str) {
        return a(context, str, context.getFileStreamPath("credentials.dat"));
    }

    /* access modifiers changed from: package-private */
    public ch b(Context context, String str) {
        return a(context, str, new File(context.getNoBackupFilesDir(), "credentials.dat"));
    }

    private ch a(Context context, String str, File file) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (applicationInfo == null) {
                return null;
            }
            return h(context, file.getAbsolutePath().replace(context.getApplicationInfo().dataDir, applicationInfo.dataDir));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private ch h(Context context, String str) {
        String a2;
        try {
            File file = new File(str);
            if (file.exists()) {
                synchronized (this.f823a) {
                    a2 = r.a(context, file);
                }
                if (a2 == null) {
                    return null;
                }
                return new ch(new JSONObject(a2), file.lastModified());
            }
        } catch (Exception | JSONException unused) {
        }
        return null;
    }

    public String c(Context context, String str) {
        return i(context, str);
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return bk.a(21);
    }

    /* access modifiers changed from: package-private */
    public void d(Context context, String str) {
        try {
            synchronized (this.f823a) {
                ch chVar = new ch(str, new cj(context), System.currentTimeMillis());
                this.d = chVar;
                String a2 = chVar.a();
                if (e()) {
                    e(context, a2);
                }
                synchronized (this.f823a) {
                    r.a(context, "credentials.dat", a2);
                }
            }
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void e(Context context, String str) {
        synchronized (this.f823a) {
            r.b(context, "credentials.dat", str);
        }
    }

    public String a(Context context) {
        return i(context, (String) null);
    }

    private String i(Context context, String str) {
        synchronized (this.f823a) {
            if (c() == null) {
                ch a2 = a(context, context.getPackageName());
                if (a2 == null) {
                    String a3 = b().a(context, str);
                    return a3;
                } else if (e()) {
                    ch b2 = b(context, context.getPackageName());
                    if (!a2.a(b2) || !b2.e()) {
                        String a4 = b().a(context, a2.c());
                        return a4;
                    }
                    this.d = a2;
                    String c2 = b2.c();
                    return c2;
                } else if (a2.e()) {
                    this.d = a2;
                    String c3 = a2.c();
                    return c3;
                } else {
                    String a5 = b().a(context, a2.c());
                    return a5;
                }
            } else {
                String c4 = c().c();
                return c4;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String f(android.content.Context r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            java.lang.String r1 = ".MetricaContentProvider"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.content.pm.PackageManager r1 = r9.getPackageManager()
            r2 = 0
            android.content.pm.ProviderInfo r0 = r1.resolveContentProvider(r0, r2)
            r1 = 0
            if (r0 == 0) goto L_0x005e
            boolean r0 = r0.enabled
            if (r0 != 0) goto L_0x0022
            goto L_0x005e
        L_0x0022:
            java.util.Locale r0 = java.util.Locale.US
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r2] = r10
            java.lang.String r10 = "content://%s.MetricaContentProvider/DEVICE_ID"
            java.lang.String r10 = java.lang.String.format(r0, r10, r3)
            android.content.ContentResolver r2 = r9.getContentResolver()     // Catch:{ Exception -> 0x005a, all -> 0x0055 }
            android.net.Uri r3 = android.net.Uri.parse(r10)     // Catch:{ Exception -> 0x005a, all -> 0x0055 }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x005a, all -> 0x0055 }
            if (r9 == 0) goto L_0x005b
            boolean r10 = r9.moveToFirst()     // Catch:{ Exception -> 0x005b, all -> 0x0052 }
            if (r10 == 0) goto L_0x005b
            java.lang.String r10 = "DEVICE_ID"
            int r10 = r9.getColumnIndex(r10)     // Catch:{ Exception -> 0x005b, all -> 0x0052 }
            java.lang.String r1 = r9.getString(r10)     // Catch:{ Exception -> 0x005b, all -> 0x0052 }
            goto L_0x005b
        L_0x0052:
            r10 = move-exception
            r1 = r9
            goto L_0x0056
        L_0x0055:
            r10 = move-exception
        L_0x0056:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r1)
            throw r10
        L_0x005a:
            r9 = r1
        L_0x005b:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r9)
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.ci.f(android.content.Context, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public ck f() {
        return this.c;
    }

    static class a {

        /* renamed from: a  reason: collision with root package name */
        ci f824a;
        private LocalServerSocket b;

        /* synthetic */ a(ci ciVar, byte b2) {
            this(ciVar);
        }

        private a(ci ciVar) {
            this.f824a = ciVar;
        }

        /* access modifiers changed from: package-private */
        public ci a() {
            return this.f824a;
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            try {
                this.b = new LocalServerSocket("com.yandex.metrica.synchronization.deviceid");
                return true;
            } catch (IOException unused) {
                return false;
            }
        }

        public String a(Context context, String str) {
            TextUtils.isEmpty(str);
            a().f().a(context);
            dn dnVar = new dn(12);
            String str2 = null;
            do {
                if (b()) {
                    str2 = a(context, str, a().f().a(context));
                    LocalServerSocket localServerSocket = this.b;
                    if (localServerSocket != null) {
                        try {
                            localServerSocket.close();
                            this.b = null;
                        } catch (IOException unused) {
                        }
                    }
                } else {
                    dnVar.a();
                    dnVar.c();
                }
            } while (dnVar.b());
            return str2;
        }

        /* access modifiers changed from: package-private */
        public String a(Context context, String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    return null;
                }
                ci.a(a(), context, str2);
                return str2;
            } else if (str.equals(str2)) {
                ci.a(a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("update_snapshot", new c(context, str2, str));
                return str;
            } else if (TextUtils.isEmpty(str2)) {
                ci.a(a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("wtf_situation. App has id and elector hasn't", new c(context, str2, str));
                return str;
            } else {
                ci.a(a(), context, str2);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("overlapping_device_id", new c(context, str2, str));
                return str2;
            }
        }
    }

    private static class c extends HashMap<String, Object> {
        public c(Context context, String str) {
            String packageName = context.getPackageName();
            put("passed_id", str);
            put("package_name", packageName);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                put("version_code", Integer.valueOf(packageInfo.versionCode));
                put("version_name", packageInfo.versionName);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }

        public c(Context context, String str, String str2) {
            this(context, str);
            put("stored_device_id", str2);
        }
    }

    public static String g(Context context, String str) {
        String a2 = b.f825a.a(context);
        if (!bi.a(a2)) {
            Intent a3 = be.a(context);
            a3.setPackage(str);
            for (ResolveInfo resolveInfo : be.a(context, a3)) {
                int a4 = be.a((PackageItemInfo) resolveInfo.serviceInfo);
                if (a4 > 0 && a4 < 29) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("DEVICE_ID", a2);
                        if (!bi.a(a2)) {
                            context.getContentResolver().update(Uri.parse(String.format(Locale.US, "content://%s.MetricaContentProvider/DEVICE_ID", new Object[]{str})), contentValues, (String) null, (String[]) null);
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return a2;
    }

    static /* synthetic */ void a(ci ciVar, Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("saving_empty_device_id", new c(context, str));
        } else {
            ciVar.d(context, str);
        }
    }
}
