package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.ah;
import com.google.android.play.core.internal.t;
import com.google.android.play.core.tasks.i;
import java.util.List;

final class y extends ah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f1108a;
    final /* synthetic */ i b;
    final /* synthetic */ an c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    y(an anVar, i iVar, List list, i iVar2) {
        super(iVar);
        this.c = anVar;
        this.f1108a = list;
        this.b = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            ((t) this.c.e.c()).d(this.c.c, an.k(this.f1108a), an.C(), new ag(this.c, this.b, (byte[]) null));
        } catch (RemoteException e) {
            an.f1029a.c(e, "cancelDownloads(%s)", this.f1108a);
        }
    }
}
