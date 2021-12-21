package com.mopub.mobileads.factories;

import com.mopub.common.AdReport;
import com.mopub.mobileads.CustomEventBannerAdapter;
import com.mopub.mobileads.MoPubView;
import java.util.Map;

public class CustomEventBannerAdapterFactory {
    protected static CustomEventBannerAdapterFactory instance = new CustomEventBannerAdapterFactory();

    @Deprecated
    public static void setInstance(CustomEventBannerAdapterFactory customEventBannerAdapterFactory) {
        instance = customEventBannerAdapterFactory;
    }

    public static CustomEventBannerAdapter create(MoPubView moPubView, String str, Map<String, String> map, long j, AdReport adReport) {
        return instance.internalCreate(moPubView, str, map, j, adReport);
    }

    /* access modifiers changed from: protected */
    public CustomEventBannerAdapter internalCreate(MoPubView moPubView, String str, Map<String, String> map, long j, AdReport adReport) {
        return new CustomEventBannerAdapter(moPubView, str, map, j, adReport);
    }
}
