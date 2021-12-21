package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.ah;
import com.google.android.play.core.internal.t;
import com.google.android.play.core.tasks.i;

final class ae extends ah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f1026a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ i e;
    final /* synthetic */ an f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ae(an anVar, i iVar, int i, String str, String str2, int i2, i iVar2) {
        super(iVar);
        this.f = anVar;
        this.f1026a = i;
        this.b = str;
        this.c = str2;
        this.d = i2;
        this.e = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            ((t) this.f.e.c()).j(this.f.c, an.r(this.f1026a, this.b, this.c, this.d), an.C(), new ah(this.f, this.e));
        } catch (RemoteException e2) {
            an.f1029a.b("getChunkFileDescriptor(%s, %s, %d, session=%d)", this.b, this.c, Integer.valueOf(this.d), Integer.valueOf(this.f1026a));
            this.e.d(new RuntimeException(e2));
        }
    }
}
