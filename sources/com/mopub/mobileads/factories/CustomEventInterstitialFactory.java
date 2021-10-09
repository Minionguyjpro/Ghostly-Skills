package com.mopub.mobileads.factories;

import com.mopub.mobileads.CustomEventInterstitial;
import java.lang.reflect.Constructor;

public class CustomEventInterstitialFactory {
    private static CustomEventInterstitialFactory instance = new CustomEventInterstitialFactory();

    public static CustomEventInterstitial create(String str) throws Exception {
        return instance.internalCreate(str);
    }

    @Deprecated
    public static void setInstance(CustomEventInterstitialFactory customEventInterstitialFactory) {
        instance = customEventInterstitialFactory;
    }

    /* access modifiers changed from: protected */
    public CustomEventInterstitial internalCreate(String str) throws Exception {
        Constructor<? extends U> declaredConstructor = Class.forName(str).asSubclass(CustomEventInterstitial.class).getDeclaredConstructor((Class[]) null);
        declaredConstructor.setAccessible(true);
        return (CustomEventInterstitial) declaredConstructor.newInstance(new Object[0]);
    }
}
