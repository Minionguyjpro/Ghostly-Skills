package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzadh
public final class zzyn extends zzyd {
    private final NativeContentAdMapper zzbuz;

    public zzyn(NativeContentAdMapper nativeContentAdMapper) {
        this.zzbuz = nativeContentAdMapper;
    }

    public final String getAdvertiser() {
        return this.zzbuz.getAdvertiser();
    }

    public final String getBody() {
        return this.zzbuz.getBody();
    }

    public final String getCallToAction() {
        return this.zzbuz.getCallToAction();
    }

    public final Bundle getExtras() {
        return this.zzbuz.getExtras();
    }

    public final String getHeadline() {
        return this.zzbuz.getHeadline();
    }

    public final List getImages() {
        List<NativeAd.Image> images = this.zzbuz.getImages();
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
        return this.zzbuz.getOverrideClickHandling();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzbuz.getOverrideImpressionRecording();
    }

    public final zzlo getVideoController() {
        if (this.zzbuz.getVideoController() != null) {
            return this.zzbuz.getVideoController().zzbc();
        }
        return null;
    }

    public final void recordImpression() {
        this.zzbuz.recordImpression();
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzbuz.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zzbuz.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzk(IObjectWrapper iObjectWrapper) {
        this.zzbuz.trackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzke() {
        return null;
    }

    public final zzps zzkf() {
        return null;
    }

    public final zzpw zzkg() {
        NativeAd.Image logo = this.zzbuz.getLogo();
        if (logo != null) {
            return new zzon(logo.getDrawable(), logo.getUri(), logo.getScale());
        }
        return null;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzbuz.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzmv() {
        View adChoicesContent = this.zzbuz.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    public final IObjectWrapper zzmw() {
        View zzvy = this.zzbuz.zzvy();
        if (zzvy == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzvy);
    }
}
