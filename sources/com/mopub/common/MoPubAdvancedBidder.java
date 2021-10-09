package com.mopub.common;

import android.content.Context;

public interface MoPubAdvancedBidder {
    String getCreativeNetworkName();

    String getToken(Context context);
}
