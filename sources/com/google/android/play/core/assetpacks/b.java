package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.bp;
import com.google.android.play.core.internal.w;
import com.google.android.play.core.internal.z;

final class b extends w {

    /* renamed from: a  reason: collision with root package name */
    private final ag f1039a = new ag("AssetPackExtractionService");
    private final Context b;
    private final au c;

    b(Context context, au auVar) {
        this.b = context;
        this.c = auVar;
    }

    private final synchronized void d(Bundle bundle) {
        ComponentName componentName;
        Intent intent = new Intent(this.b, ExtractionForegroundService.class);
        int i = bundle.getInt("action_type");
        intent.putExtra("action_type", i);
        if (i == 1) {
            intent.putExtra("notification_channel_name", bundle.getString("notification_channel_name"));
            intent.putExtra("notification_title", bundle.getString("notification_title"));
            intent.putExtra("notification_subtext", bundle.getString("notification_subtext"));
            intent.putExtra("notification_timeout", bundle.getLong("notification_timeout"));
            Parcelable parcelable = bundle.getParcelable("notification_on_click_intent");
            if (parcelable instanceof PendingIntent) {
                intent.putExtra("notification_on_click_intent", parcelable);
            }
            intent.putExtra("notification_color", bundle.getInt("notification_color"));
        }
        try {
            componentName = Build.VERSION.SDK_INT >= 26 ? this.b.startForegroundService(intent) : this.b.startService(intent);
        } catch (IllegalStateException | SecurityException e) {
            this.f1039a.c(e, "Failed starting installation service.", new Object[0]);
            componentName = null;
        }
        if (componentName == null) {
            this.f1039a.b("Failed starting installation service.", new Object[0]);
        }
    }

    public final void b(Bundle bundle, z zVar) throws RemoteException {
        this.f1039a.a("updateServiceState AIDL call", new Object[0]);
        if (!bp.a(this.b) || !bp.b(this.b)) {
            zVar.d(new Bundle());
            return;
        }
        d(bundle);
        zVar.c(new Bundle(), new Bundle());
    }

    public final void c(z zVar) throws RemoteException {
        this.f1039a.a("clearAssetPackStorage AIDL call", new Object[0]);
        if (!bp.a(this.b) || !bp.b(this.b)) {
            zVar.d(new Bundle());
            return;
        }
        this.c.x();
        zVar.e(new Bundle());
    }
}
