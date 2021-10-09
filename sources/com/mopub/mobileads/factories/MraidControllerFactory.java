package com.mopub.mobileads.factories;

import android.content.Context;
import com.mopub.common.AdReport;
import com.mopub.mraid.MraidController;
import com.mopub.mraid.PlacementType;

public class MraidControllerFactory {
    protected static MraidControllerFactory instance = new MraidControllerFactory();

    public static void setInstance(MraidControllerFactory mraidControllerFactory) {
        instance = mraidControllerFactory;
    }

    public static MraidController create(Context context, AdReport adReport, PlacementType placementType) {
        return instance.internalCreate(context, adReport, placementType);
    }

    /* access modifiers changed from: protected */
    public MraidController internalCreate(Context context, AdReport adReport, PlacementType placementType) {
        return new MraidController(context, adReport, placementType);
    }
}
