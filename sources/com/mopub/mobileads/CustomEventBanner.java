package com.mopub.mobileads;

import android.content.Context;
import android.view.View;
import java.util.Map;

public abstract class CustomEventBanner {
    private boolean mAutomaticImpressionAndClickTracking = true;

    public interface CustomEventBannerListener {
        void onBannerClicked();

        void onBannerCollapsed();

        void onBannerExpanded();

        void onBannerFailed(MoPubErrorCode moPubErrorCode);

        void onBannerImpression();

        void onBannerLoaded(View view);

        void onLeaveApplication();
    }

    /* access modifiers changed from: protected */
    public abstract void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2);

    /* access modifiers changed from: protected */
    public abstract void onInvalidate();

    /* access modifiers changed from: protected */
    public void trackMpxAndThirdPartyImpressions() {
    }

    /* access modifiers changed from: protected */
    public void setAutomaticImpressionAndClickTracking(boolean z) {
        this.mAutomaticImpressionAndClickTracking = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isAutomaticImpressionAndClickTrackingEnabled() {
        return this.mAutomaticImpressionAndClickTracking;
    }
}
