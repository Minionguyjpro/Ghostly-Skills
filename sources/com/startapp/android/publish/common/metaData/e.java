package com.startapp.android.publish.common.metaData;

import android.content.Context;
import com.startapp.android.publish.adsCommon.f.c;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.common.a.g;
import com.startapp.common.b.a.b;
import com.startapp.common.d;
import java.util.Map;

/* compiled from: StartAppSDK */
public class e implements b {
    public void a(final Context context, int i, Map<String, String> map, final b.C0011b bVar) {
        g.a("PeriodicInfoEvent", 3, "PeriodicInfoEvent execute");
        try {
            MetaData.init(context);
            MetaData.getInstance().setReady(true);
            if (MetaData.getInstance().isPeriodicInfoEventEnabled()) {
                new c(context, true, new d() {
                    public void a(Object obj) {
                        if (bVar != null) {
                            com.startapp.android.publish.adsCommon.Utils.b.d(context);
                            bVar.a(b.a.SUCCESS);
                        }
                    }
                }).a();
            } else if (bVar != null) {
                com.startapp.android.publish.adsCommon.Utils.b.d(context);
                bVar.a(b.a.SUCCESS);
            }
        } catch (Exception e) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "PeriodicInfoEvent.execute", e.getMessage(), "");
        }
    }
}
