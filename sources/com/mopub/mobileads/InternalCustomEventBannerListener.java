package com.mopub.mobileads;

import com.mopub.mobileads.CustomEventBanner;

public interface InternalCustomEventBannerListener extends CustomEventBanner.CustomEventBannerListener {
    void onPauseAutoRefresh();

    void onResumeAutoRefresh();
}
