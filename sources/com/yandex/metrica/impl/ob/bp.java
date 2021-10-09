package com.yandex.metrica.impl.ob;

import android.content.Context;
import com.yandex.metrica.impl.bk;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class bp {

    /* renamed from: a  reason: collision with root package name */
    private static volatile bp f803a;
    private final Map<String, bo> b = new HashMap();
    private final Map<String, bq> c = new HashMap();
    private final Context d;
    private bo e;
    private bq f;
    private bq g;
    private bq h;
    private br i;

    public static bp a(Context context) {
        if (f803a == null) {
            synchronized (bp.class) {
                if (f803a == null) {
                    f803a = new bp(context);
                }
            }
        }
        return f803a;
    }

    public bp(Context context) {
        this.d = context;
    }

    public synchronized bo a() {
        if (this.e == null) {
            this.e = a("metrica_data.db", bm.b());
        }
        return this.e;
    }

    public synchronized bq b(r rVar) {
        bq bqVar;
        String rVar2 = rVar.toString();
        bqVar = this.c.get(rVar2);
        if (bqVar == null) {
            bqVar = new bq(a(rVar), "preferences");
            this.c.put(rVar2, bqVar);
        }
        return bqVar;
    }

    public synchronized bq b() {
        if (this.f == null) {
            this.f = new bq(a(), "preferences");
        }
        return this.f;
    }

    public synchronized br c() {
        if (this.i == null) {
            this.i = new br(a(), "permissions");
        }
        return this.i;
    }

    public synchronized bq d() {
        if (this.g == null) {
            this.g = new bq(a(), "startup");
        }
        return this.g;
    }

    public synchronized bq e() {
        if (this.h == null) {
            this.h = new bq("preferences", (bv) new bw(this.d, a("metrica_client_data.db")));
        }
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public bo a(String str, bs bsVar) {
        return new bo(this.d, a(str), bsVar);
    }

    private String a(String str) {
        return bk.a(21) ? b(str) : str;
    }

    private String b(String str) {
        try {
            File noBackupFilesDir = this.d.getNoBackupFilesDir();
            File file = new File(noBackupFilesDir, str);
            if (!file.exists()) {
                File databasePath = this.d.getDatabasePath(str);
                if (databasePath.exists() && databasePath.renameTo(file)) {
                    String str2 = str + "-journal";
                    this.d.getDatabasePath(str2).renameTo(new File(noBackupFilesDir, str2));
                }
            }
            return file.getAbsolutePath();
        } catch (Exception unused) {
            return str;
        }
    }

    public synchronized bo a(r rVar) {
        bo boVar;
        String str = "db_metrica_" + rVar;
        boVar = this.b.get(str);
        if (boVar == null) {
            boVar = a(str, bm.a());
            this.b.put(str, boVar);
        }
        return boVar;
    }
}
