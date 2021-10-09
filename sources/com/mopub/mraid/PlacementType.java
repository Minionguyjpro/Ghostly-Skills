package com.mopub.mraid;

import java.util.Locale;

public enum PlacementType {
    INLINE,
    INTERSTITIAL;

    /* access modifiers changed from: package-private */
    public String toJavascriptString() {
        return toString().toLowerCase(Locale.US);
    }
}
