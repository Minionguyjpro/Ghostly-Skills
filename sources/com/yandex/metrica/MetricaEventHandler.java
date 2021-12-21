package com.yandex.metrica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bo;
import com.yandex.metrica.impl.utils.j;
import java.util.HashSet;
import java.util.Set;

public final class MetricaEventHandler extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public static final Set<BroadcastReceiver> f683a = new HashSet();

    public void onReceive(Context context, Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("referrer");
            if (!bi.a(stringExtra)) {
                bo.b(context).b(stringExtra);
            }
        }
        for (BroadcastReceiver next : f683a) {
            j.f().a(String.format("Sending referrer to %s", new Object[]{next.getClass().getName()}));
            next.onReceive(context, intent);
        }
    }
}
