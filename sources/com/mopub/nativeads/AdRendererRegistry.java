package com.mopub.nativeads;

import com.mopub.common.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;

public class AdRendererRegistry {
    private final ArrayList<MoPubAdRenderer> mMoPubAdRenderers = new ArrayList<>();

    public void registerAdRenderer(MoPubAdRenderer moPubAdRenderer) {
        this.mMoPubAdRenderers.add(moPubAdRenderer);
    }

    public int getAdRendererCount() {
        return this.mMoPubAdRenderers.size();
    }

    public Iterable<MoPubAdRenderer> getRendererIterable() {
        return this.mMoPubAdRenderers;
    }

    public int getViewTypeForAd(NativeAd nativeAd) {
        Preconditions.checkNotNull(nativeAd);
        for (int i = 0; i < this.mMoPubAdRenderers.size(); i++) {
            if (nativeAd.getMoPubAdRenderer() == this.mMoPubAdRenderers.get(i)) {
                return i + 1;
            }
        }
        return 0;
    }

    public MoPubAdRenderer getRendererForAd(BaseNativeAd baseNativeAd) {
        Preconditions.checkNotNull(baseNativeAd);
        Iterator<MoPubAdRenderer> it = this.mMoPubAdRenderers.iterator();
        while (it.hasNext()) {
            MoPubAdRenderer next = it.next();
            if (next.supports(baseNativeAd)) {
                return next;
            }
        }
        return null;
    }

    public MoPubAdRenderer getRendererForViewType(int i) {
        try {
            return this.mMoPubAdRenderers.get(i - 1);
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }
}
