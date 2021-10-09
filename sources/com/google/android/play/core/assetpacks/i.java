package com.google.android.play.core.assetpacks;

import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.a;
import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.ca;
import com.google.android.play.core.splitinstall.p;
import com.google.android.play.core.tasks.Task;
import java.util.List;
import java.util.concurrent.Executor;

final class i {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f1095a = new ag("AssetPackManager");
    private final au b;
    private final ca<t> c;
    private final ar d;
    private final p e;
    private final ca f;
    private final bo g;
    private final be h;
    private final ca<Executor> i;
    private final a j;
    private final Handler k = new Handler(Looper.getMainLooper());

    i(au auVar, ca<t> caVar, ar arVar, p pVar, ca caVar2, bo boVar, be beVar, ca<Executor> caVar3, a aVar) {
        this.b = auVar;
        this.c = caVar;
        this.d = arVar;
        this.e = pVar;
        this.f = caVar2;
        this.g = boVar;
        this.h = beVar;
        this.i = caVar3;
        this.j = aVar;
    }

    private final void h() {
        this.i.a().execute(new e(this, (byte[]) null));
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z) {
        boolean j2 = this.d.j();
        this.d.e(z);
        if (z && !j2) {
            h();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void c() {
        this.b.v();
        this.b.s();
        this.b.w();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void d() {
        Task<List<String>> c2 = this.c.a().c(this.b.c());
        au auVar = this.b;
        auVar.getClass();
        c2.addOnSuccessListener(this.i.a(), f.a(auVar));
        c2.addOnFailureListener(this.i.a(), g.f1094a);
    }
}
