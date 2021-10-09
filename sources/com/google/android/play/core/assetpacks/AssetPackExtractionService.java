package com.google.android.play.core.assetpacks;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AssetPackExtractionService extends Service {

    /* renamed from: a  reason: collision with root package name */
    b f1020a;

    public final IBinder onBind(Intent intent) {
        return this.f1020a;
    }

    public final void onCreate() {
        super.onCreate();
        ck.j(getApplicationContext()).b(this);
    }
}
