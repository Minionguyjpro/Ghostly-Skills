package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.ob.du;
import com.yandex.metrica.impl.utils.g;
import com.yandex.metrica.impl.utils.l;
import com.yandex.metrica.impl.utils.m;
import java.util.Map;

public class j extends ResultReceiver {

    /* renamed from: a  reason: collision with root package name */
    private a f771a;

    interface a {
        void a(int i, Bundle bundle);
    }

    public j(Handler handler) {
        super(handler);
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar) {
        this.f771a = aVar;
    }

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        a aVar = this.f771a;
        if (aVar != null) {
            aVar.a(i, bundle);
        }
    }

    public static void a(ResultReceiver resultReceiver, ba baVar, bg.a aVar) {
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putString("UuId", baVar.b());
            bundle.putString("DeviceId", baVar.c());
            bundle.putString("AdUrlGet", baVar.A());
            bundle.putString("AdUrlReport", baVar.B());
            bundle.putLong("ServerTimeOffset", m.a());
            bundle.putString("Clids", g.a((Map) l.a(baVar.y())));
            bundle.putString("CookieBrowsers", aVar.l().a());
            bundle.putString("BindIdUrl", aVar.m());
            resultReceiver.send(1, bundle);
        }
    }

    public static void a(ResultReceiver resultReceiver, du duVar) {
        if (resultReceiver != null) {
            resultReceiver.send(2, duVar.a(new Bundle()));
        }
    }
}
