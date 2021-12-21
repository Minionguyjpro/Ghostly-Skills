package com.appnext.base.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.appnext.base.a.a;
import com.appnext.base.a.b.c;
import com.appnext.base.b.d;
import com.appnext.base.b.e;
import com.appnext.base.b.g;
import com.appnext.base.operations.imp.scdle;

public class AppnextBootReciever extends BroadcastReceiver {
    public void onReceive(final Context context, Intent intent) {
        try {
            e.init(context);
            if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                g.aN().b(new Runnable() {
                    public final void run() {
                        try {
                            String simpleName = scdle.class.getSimpleName();
                            c cVar = new c(d.fe, "", "", "1", d.fm, simpleName, simpleName + System.currentTimeMillis(), (String) null);
                            a.X().ab().a(cVar);
                            com.appnext.base.services.b.a.d(context).a(cVar, true);
                        } catch (Throwable unused) {
                        }
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }
}
