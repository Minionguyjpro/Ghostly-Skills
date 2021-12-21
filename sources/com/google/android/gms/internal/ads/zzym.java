package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzadh
public final class zzym extends zzya {
    private final NativeAppInstallAdMapper zzbuy;

    public zzym(NativeAppInstallAdMapper nativeAppInstallAdMapper) {
        this.zzbuy = nativeAppInstallAdMapper;
    }

    public final String getBody() {
        return this.zzbuy.getBody();
    }

    public final String getCallToAction() {
        return this.zzbuy.getCallToAction();
    }

    public final Bundle getExtras() {
        return this.zzbuy.getExtras();
    }

    public final String getHeadline() {
        return this.zzbuy.getHeadline();
    }

    public final List getImages() {
        List<NativeAd.Image> images = this.zzbuy.getImages();
        if (images == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NativeAd.Image next : images) {
            arrayList.add(new zzon(next.getDrawable(), next.getUri(), next.getScale()));
        }
        return arrayList;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzbuy.getOverrideClickHandling();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzbuy.getOverrideImpressionRecording();
    }

    public final String getPrice() {
        return this.zzbuy.getPrice();
    }

    public final double getStarRating() {
        return this.zzbuy.getStarRating();
    }

    public final String getStore() {
        return this.zzbuy.getStore();
    }

    public final zzlo getVideoController() {
        if (this.zzbuy.getVideoController() != null) {
            return this.zzbuy.getVideoController().zzbc();
        }
        return null;
    }

    public final void recordImpression() {
        this.zzbuy.recordImpression();
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzbuy.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zzbuy.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final zzpw zzjz() {
        NativeAd.Image icon = this.zzbuy.getIcon();
        if (icon != null) {
            return new zzon(icon.getDrawable(), icon.getUri(), icon.getScale());
        }
        return null;
    }

    public final void zzk(IObjectWrapper iObjectWrapper) {
        this.zzbuy.trackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzke() {
        return null;
    }

    public final zzps zzkf() {
        return null;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzbuy.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzmv() {
        View adChoicesContent = this.zzbuy.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    public final IObjectWrapper zzmw() {
        View zzvy = this.zzbuy.zzvy();
        if (zzvy == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzvy);
    }
}
