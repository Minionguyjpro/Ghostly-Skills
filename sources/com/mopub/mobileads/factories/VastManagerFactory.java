package com.mopub.mobileads.factories;

import android.content.Context;
import com.mopub.mobileads.VastManager;

public class VastManagerFactory {
    protected static VastManagerFactory instance = new VastManagerFactory();

    public static VastManager create(Context context) {
        return instance.internalCreate(context, true);
    }

    public static VastManager create(Context context, boolean z) {
        return instance.internalCreate(context, z);
    }

    public VastManager internalCreate(Context context, boolean z) {
        return new VastManager(context, z);
    }

    @Deprecated
    public static void setInstance(VastManagerFactory vastManagerFactory) {
        instance = vastManagerFactory;
    }
}
