package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzoq extends zzqp implements zzpc {
    private Bundle mExtras;
    private Object mLock = new Object();
    private String zzbhw;
    private List<zzon> zzbhx;
    private String zzbhy;
    private String zzbia;
    private zzoj zzbie;
    private zzlo zzbif;
    private View zzbig;
    private IObjectWrapper zzbih;
    private String zzbii;
    /* access modifiers changed from: private */
    public zzoz zzbij;
    private zzpw zzbil;
    private String zzbim;

    public zzoq(String str, List<zzon> list, String str2, zzpw zzpw, String str3, String str4, zzoj zzoj, Bundle bundle, zzlo zzlo, View view, IObjectWrapper iObjectWrapper, String str5) {
        this.zzbhw = str;
        this.zzbhx = list;
        this.zzbhy = str2;
        this.zzbil = zzpw;
        this.zzbia = str3;
        this.zzbim = str4;
        this.zzbie = zzoj;
        this.mExtras = bundle;
        this.zzbif = zzlo;
        this.zzbig = view;
        this.zzbih = iObjectWrapper;
        this.zzbii = str5;
    }

    public final void destroy() {
        zzakk.zzcrm.post(new zzor(this));
        this.zzbhw = null;
        this.zzbhx = null;
        this.zzbhy = null;
        this.zzbil = null;
        this.zzbia = null;
        this.zzbim = null;
        this.zzbie = null;
        this.mExtras = null;
        this.mLock = null;
        this.zzbif = null;
        this.zzbig = null;
    }

    public final String getAdvertiser() {
        return this.zzbim;
    }

    public final String getBody() {
        return this.zzbhy;
    }

    public final String getCallToAction() {
        return this.zzbia;
    }

    public final String getCustomTemplateId() {
        return "";
    }

    public final Bundle getExtras() {
        return this.mExtras;
    }

    public final String getHeadline() {
        return this.zzbhw;
    }

    public final List getImages() {
        return this.zzbhx;
    }

    public final String getMediationAdapterClassName() {
        return this.zzbii;
    }

    public final zzlo getVideoController() {
        return this.zzbif;
    }

    public final void performClick(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzakb.e("#001 Attempt to perform click before app native ad initialized.");
            } else {
                this.zzbij.performClick(bundle);
            }
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzakb.e("#002 Attempt to record impression before native ad initialized.");
                return false;
            }
            boolean recordImpression = this.zzbij.recordImpression(bundle);
            return recordImpression;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzakb.e("#003 Attempt to report touch event before native ad initialized.");
            } else {
                this.zzbij.reportTouchEvent(bundle);
            }
        }
    }

    public final void zzb(zzoz zzoz) {
        synchronized (this.mLock) {
            this.zzbij = zzoz;
        }
    }

    public final IObjectWrapper zzka() {
        return ObjectWrapper.wrap(this.zzbij);
    }

    public final String zzkb() {
        return "1";
    }

    public final zzoj zzkc() {
        return this.zzbie;
    }

    public final View zzkd() {
        return this.zzbig;
    }

    public final IObjectWrapper zzke() {
        return this.zzbih;
    }

    public final zzps zzkf() {
        return this.zzbie;
    }

    public final zzpw zzkg() {
        return this.zzbil;
    }
}
