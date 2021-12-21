package com.mopub.common;

import com.mopub.mobileads.MoPubErrorCode;

public interface OnNetworkInitializationFinishedListener {
    void onNetworkInitializationFinished(Class<? extends AdapterConfiguration> cls, MoPubErrorCode moPubErrorCode);
}
