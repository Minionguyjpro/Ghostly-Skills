package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzadh
public final class zzze extends zzyg {
    private final UnifiedNativeAdMapper zzbvh;

    public zzze(UnifiedNativeAdMapper unifiedNativeAdMapper) {
        this.zzbvh = unifiedNativeAdMapper;
    }

    public final String getAdvertiser() {
        return this.zzbvh.getAdvertiser();
    }

    public final String getBody() {
        return this.zzbvh.getBody();
    }

    public final String getCallToAction() {
        return this.zzbvh.getCallToAction();
    }

    public final Bundle getExtras() {
        return this.zzbvh.getExtras();
    }

    public final String getHeadline() {
        return this.zzbvh.getHeadline();
    }

    public final List getImages() {
        List<NativeAd.Image> images = this.zzbvh.getImages();
        ArrayList arrayList = new ArrayList();
        if (images != null) {
            for (NativeAd.Image next : images) {
                arrayList.add(new zzon(next.getDrawable(), next.getUri(), next.getScale()));
            }
        }
        return arrayList;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzbvh.getOverrideClickHandling();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzbvh.getOverrideImpressionRecording();
    }

    public final String getPrice() {
        return this.zzbvh.getPrice();
    }

    public final double getStarRating() {
        if (this.zzbvh.getStarRating() != null) {
            return this.zzbvh.getStarRating().doubleValue();
        }
        return -1.0d;
    }

    public final String getStore() {
        return this.zzbvh.getStore();
    }

    public final zzlo getVideoController() {
        if (this.zzbvh.getVideoController() != null) {
            return this.zzbvh.getVideoController().zzbc();
        }
        return null;
    }

    public final void recordImpression() {
        this.zzbvh.recordImpression();
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzbvh.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zzbvh.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final zzpw zzjz() {
        NativeAd.Image icon = this.zzbvh.getIcon();
        if (icon != null) {
            return new zzon(icon.getDrawable(), icon.getUri(), icon.getScale());
        }
        return null;
    }

    public final IObjectWrapper zzke() {
        Object zzbh = this.zzbvh.zzbh();
        if (zzbh == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzbh);
    }

    public final zzps zzkf() {
        return null;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzbvh.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzmv() {
        View adChoicesContent = this.zzbvh.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    public final IObjectWrapper zzmw() {
        View zzvy = this.zzbvh.zzvy();
        if (zzvy == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzvy);
    }
}
