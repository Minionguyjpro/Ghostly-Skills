package com.startapp.android.publish.common.metaData;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.adsCommon.k.a;
import com.startapp.android.publish.adsCommon.l;
import com.startapp.android.publish.common.metaData.MetaDataRequest;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a.g;
import com.startapp.common.b.a.b;
import java.util.Map;

/* compiled from: StartAppSDK */
public class f implements b {
    public void a(Context context, int i, Map<String, String> map, b.C0011b bVar) {
        try {
            MetaData.init(context);
            if (MetaData.getInstance().isPeriodicMetaDataEnabled()) {
                a(context, bVar);
            }
        } catch (Exception e) {
            com.startapp.android.publish.adsCommon.f.f.a(context, d.EXCEPTION, "PeriodicMetaData.execute", e.getMessage(), "");
        }
    }

    private static void a(Context context, b.C0011b bVar) {
        final AdPreferences adPreferences = new AdPreferences(k.a(context, "shared_prefs_devId", (String) null), k.a(context, "shared_prefs_appId", ""));
        final Context context2 = context;
        final b.C0011b bVar2 = bVar;
        new c(context, adPreferences, MetaDataRequest.a.PERIODIC) {
            private MetaData d = null;

            /* access modifiers changed from: protected */
            public Boolean c() {
                g.a(3, "Loading MetaData");
                try {
                    l.b(context2);
                    MetaDataRequest metaDataRequest = new MetaDataRequest(context2, MetaDataRequest.a.PERIODIC);
                    metaDataRequest.fillApplicationDetails(context2, adPreferences, false);
                    metaDataRequest.fillLocationDetails(adPreferences, context2);
                    this.d = (MetaData) i.a(a.a(context2, AdsConstants.a(AdsConstants.ServiceApiType.METADATA), metaDataRequest, (Map<String, String>) null).a(), MetaData.class);
                    return Boolean.TRUE;
                } catch (Exception e) {
                    g.a(6, "Unable to handle GetMetaData command!!!!", (Throwable) e);
                    return Boolean.FALSE;
                }
            }

            /* access modifiers changed from: protected */
            public void a(Boolean bool) {
                try {
                    if (!(!bool.booleanValue() || this.d == null || context2 == null)) {
                        MetaData.update(context2, this.d);
                    }
                    com.startapp.android.publish.adsCommon.Utils.b.c(context2);
                    if (bVar2 != null) {
                        bVar2.a(b.a.SUCCESS);
                    }
                } catch (Exception e) {
                    com.startapp.android.publish.adsCommon.f.f.a(context2, d.EXCEPTION, "PeriodicMetaData.onPostExecute", e.getMessage(), "");
                }
            }
        }.a();
    }
}
