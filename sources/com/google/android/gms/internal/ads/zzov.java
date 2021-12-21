package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzov extends zzrs implements zzpc {
    private Bundle mExtras;
    private Object mLock = new Object();
    private String zzbhw;
    private List<zzon> zzbhx;
    private String zzbhy;
    private zzpw zzbhz;
    private String zzbia;
    private double zzbib;
    private String zzbic;
    private String zzbid;
    private zzoj zzbie;
    private zzlo zzbif;
    private View zzbig;
    private IObjectWrapper zzbih;
    private String zzbii;
    /* access modifiers changed from: private */
    public zzoz zzbij;
    private String zzbim;

    public zzov(String str, List<zzon> list, String str2, zzpw zzpw, String str3, String str4, double d, String str5, String str6, zzoj zzoj, zzlo zzlo, View view, IObjectWrapper iObjectWrapper, String str7, Bundle bundle) {
        this.zzbhw = str;
        this.zzbhx = list;
        this.zzbhy = str2;
        this.zzbhz = zzpw;
        this.zzbia = str3;
        this.zzbim = str4;
        this.zzbib = d;
        this.zzbic = str5;
        this.zzbid = str6;
        this.zzbie = zzoj;
        this.zzbif = zzlo;
        this.zzbig = view;
        this.zzbih = iObjectWrapper;
        this.zzbii = str7;
        this.mExtras = bundle;
    }

    public final void cancelUnconfirmedClick() {
        this.zzbij.cancelUnconfirmedClick();
    }

    public final void destroy() {
        zzakk.zzcrm.post(new zzow(this));
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

    public final String getPrice() {
        return this.zzbid;
    }

    public final double getStarRating() {
        return this.zzbib;
    }

    public final String getStore() {
        return this.zzbic;
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

    public final void zza(zzro zzro) {
        this.zzbij.zza(zzro);
    }

    public final void zzb(zzoz zzoz) {
        synchronized (this.mLock) {
            this.zzbij = zzoz;
        }
    }

    public final zzpw zzjz() {
        return this.zzbhz;
    }

    public final IObjectWrapper zzka() {
        return ObjectWrapper.wrap(this.zzbij);
    }

    public final String zzkb() {
        return "6";
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
}
