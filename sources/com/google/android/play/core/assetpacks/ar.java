package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.ca;
import com.google.android.play.core.listener.b;
import java.util.ArrayList;
import java.util.concurrent.Executor;

final class ar extends b<AssetPackState> {
    private final ca c;
    private final bl d;
    private final ca<t> e;
    private final be f;
    private final bo g;
    private final ca<Executor> h;
    private final ca<Executor> i;
    private final Handler j = new Handler(Looper.getMainLooper());

    ar(Context context, ca caVar, bl blVar, ca<t> caVar2, bo boVar, be beVar, ca<Executor> caVar3, ca<Executor> caVar4) {
        super(new ag("AssetPackServiceListenerRegistry"), new IntentFilter("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE"), context);
        this.c = caVar;
        this.d = blVar;
        this.e = caVar2;
        this.g = boVar;
        this.f = beVar;
        this.h = caVar3;
        this.i = caVar4;
    }

    /* access modifiers changed from: protected */
    public final void a(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE");
        if (bundleExtra != null) {
            ArrayList<String> stringArrayList = bundleExtra.getStringArrayList("pack_names");
            if (stringArrayList == null || stringArrayList.size() != 1) {
                this.f1133a.b("Corrupt bundle received from broadcast.", new Object[0]);
                return;
            }
            AssetPackState d2 = AssetPackState.d(bundleExtra, stringArrayList.get(0), this.g, at.b);
            this.f1133a.a("ListenerRegistryBroadcastReceiver.onReceive: %s", d2);
            PendingIntent pendingIntent = (PendingIntent) bundleExtra.getParcelable("confirmation_intent");
            if (pendingIntent != null) {
                this.f.a(pendingIntent);
            }
            this.i.a().execute(new ap(this, bundleExtra, d2));
            this.h.a().execute(new aq(this, bundleExtra));
            return;
        }
        this.f1133a.b("Empty bundle received from broadcast.", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public final void b(AssetPackState assetPackState) {
        this.j.post(new ao(this, assetPackState));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void c(Bundle bundle) {
        if (this.c.d(bundle)) {
            this.d.a();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void d(Bundle bundle, AssetPackState assetPackState) {
        if (this.c.e(bundle)) {
            b(assetPackState);
            this.e.a().j();
        }
    }
}
