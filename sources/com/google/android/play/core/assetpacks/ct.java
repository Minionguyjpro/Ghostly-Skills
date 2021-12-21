package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.a;
import com.google.android.play.core.internal.ca;
import java.io.File;
import java.util.concurrent.Executor;

final class ct {

    /* renamed from: a  reason: collision with root package name */
    private final au f1080a;
    private final ca<t> b;
    private final ca c;
    private final ca<Executor> d;
    private final bo e;
    private final a f;

    ct(au auVar, ca<t> caVar, ca caVar2, ca<Executor> caVar3, bo boVar, a aVar) {
        this.f1080a = auVar;
        this.b = caVar;
        this.c = caVar2;
        this.d = caVar3;
        this.e = boVar;
        this.f = aVar;
    }

    public final void a(cq cqVar) {
        Runnable runnable;
        Executor executor;
        File j = this.f1080a.j(cqVar.k, cqVar.f1077a, cqVar.b);
        File p = this.f1080a.p(cqVar.k, cqVar.f1077a, cqVar.b);
        if (!j.exists() || !p.exists()) {
            throw new bk(String.format("Cannot find pack files to move for pack %s.", new Object[]{cqVar.k}), cqVar.j);
        }
        File f2 = this.f1080a.f(cqVar.k, cqVar.f1077a, cqVar.b);
        f2.mkdirs();
        if (j.renameTo(f2)) {
            new File(this.f1080a.f(cqVar.k, cqVar.f1077a, cqVar.b), "merge.tmp").delete();
            File g = this.f1080a.g(cqVar.k, cqVar.f1077a, cqVar.b);
            g.mkdirs();
            if (p.renameTo(g)) {
                if (this.f.a()) {
                    executor = this.d.a();
                    runnable = new cr(this, cqVar);
                } else {
                    executor = this.d.a();
                    au auVar = this.f1080a;
                    auVar.getClass();
                    runnable = cs.a(auVar);
                }
                executor.execute(runnable);
                this.c.f(cqVar.k, cqVar.f1077a, cqVar.b);
                this.e.a(cqVar.k);
                this.b.a().f(cqVar.j, cqVar.k);
                return;
            }
            throw new bk("Cannot move metadata files to final location.", cqVar.j);
        }
        throw new bk("Cannot move merged pack files to final location.", cqVar.j);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void b(cq cqVar) {
        this.f1080a.r(cqVar.k, cqVar.f1077a, cqVar.b);
    }
}
