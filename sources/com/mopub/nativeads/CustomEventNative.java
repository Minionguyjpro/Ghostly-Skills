package com.mopub.nativeads;

import android.content.Context;
import java.util.Map;

public abstract class CustomEventNative {

    public interface CustomEventNativeListener {
        void onNativeAdFailed(NativeErrorCode nativeErrorCode);

        void onNativeAdLoaded(BaseNativeAd baseNativeAd);
    }

    /* access modifiers changed from: protected */
    public abstract void loadNativeAd(Context context, CustomEventNativeListener customEventNativeListener, Map<String, Object> map, Map<String, String> map2);

    /* access modifiers changed from: protected */
    public void onInvalidate() {
    }
}
