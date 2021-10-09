package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.ah;
import com.google.android.play.core.internal.t;
import com.google.android.play.core.tasks.i;

final class ac extends ah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f1024a;
    final /* synthetic */ String b;
    final /* synthetic */ i c;
    final /* synthetic */ int d;
    final /* synthetic */ an e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ac(an anVar, i iVar, int i, String str, i iVar2, int i2) {
        super(iVar);
        this.e = anVar;
        this.f1024a = i;
        this.b = str;
        this.c = iVar2;
        this.d = i2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            ((t) this.e.e.c()).g(this.e.c, an.A(this.f1024a, this.b), an.C(), new ak(this.e, this.c, this.f1024a, this.b, this.d));
        } catch (RemoteException e2) {
            an.f1029a.c(e2, "notifyModuleCompleted", new Object[0]);
        }
    }
}
