package com.mopub.mobileads.factories;

import com.mopub.mobileads.CustomEventBanner;
import java.lang.reflect.Constructor;

public class CustomEventBannerFactory {
    private static CustomEventBannerFactory instance = new CustomEventBannerFactory();

    public static CustomEventBanner create(String str) throws Exception {
        return instance.internalCreate(str);
    }

    @Deprecated
    public static void setInstance(CustomEventBannerFactory customEventBannerFactory) {
        instance = customEventBannerFactory;
    }

    /* access modifiers changed from: protected */
    public CustomEventBanner internalCreate(String str) throws Exception {
        Constructor<? extends U> declaredConstructor = Class.forName(str).asSubclass(CustomEventBanner.class).getDeclaredConstructor((Class[]) null);
        declaredConstructor.setAccessible(true);
        return (CustomEventBanner) declaredConstructor.newInstance(new Object[0]);
    }
}
