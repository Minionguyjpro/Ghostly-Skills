package com.tappx.sdk.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tappx.a.a3;
import com.tappx.a.j0;

public class TrackInstallReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private final a3 f655a = new a3();

    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("ilp")) {
            j0.a(intent.getStringExtra("ilp"));
        } else if ("com.android.vending.INSTALL_REFERRER".equalsIgnoreCase(intent.getAction())) {
            this.f655a.a(context, intent);
        }
    }
}
