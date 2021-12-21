package com.mopub.mobileads.factories;

import android.content.Context;
import com.mopub.mobileads.MoPubView;

public class MoPubViewFactory {
    protected static MoPubViewFactory instance = new MoPubViewFactory();

    @Deprecated
    public static void setInstance(MoPubViewFactory moPubViewFactory) {
        instance = moPubViewFactory;
    }

    public static MoPubView create(Context context) {
        return instance.internalCreate(context);
    }

    /* access modifiers changed from: protected */
    public MoPubView internalCreate(Context context) {
        return new MoPubView(context);
    }
}
