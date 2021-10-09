package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public abstract class h extends Ad {
    private static final long serialVersionUID = 1;
    private List<AdDetails> adsDetails = null;

    public h(Context context, AdPreferences.Placement placement) {
        super(context, placement);
    }

    public void a(List<AdDetails> list) {
        this.adsDetails = list;
        b();
        a();
    }

    private void a() {
        boolean z = true;
        for (AdDetails isBelowMinCPM : this.adsDetails) {
            if (!isBelowMinCPM.getIsBelowMinCPM()) {
                z = false;
            }
        }
        this.belowMinCPM = z;
    }

    public List<AdDetails> d() {
        return this.adsDetails;
    }

    private void b() {
        List<AdDetails> list = this.adsDetails;
        Long l = null;
        if (list != null) {
            for (AdDetails next : list) {
                if (!(next == null || next.getTtl() == null)) {
                    if (l == null || next.getTtl().longValue() < l.longValue()) {
                        l = next.getTtl();
                    }
                }
            }
        }
        if (l != null) {
            this.adCacheTtl = Long.valueOf(TimeUnit.SECONDS.toMillis(l.longValue()));
        }
    }
}
