package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.AdReport;
import com.mopub.mobileads.CustomEventBanner;

public class HtmlBannerWebView extends BaseHtmlWebView {
    public static final String EXTRA_AD_CLICK_DATA = "com.mopub.intent.extra.AD_CLICK_DATA";

    public HtmlBannerWebView(Context context, AdReport adReport) {
        super(context, adReport);
    }

    public void init(CustomEventBanner.CustomEventBannerListener customEventBannerListener, String str, String str2) {
        super.init();
        setWebViewClient(new HtmlWebViewClient(new HtmlBannerWebViewListener(customEventBannerListener), this, str, str2));
    }

    static class HtmlBannerWebViewListener implements HtmlWebViewListener {
        private final CustomEventBanner.CustomEventBannerListener mCustomEventBannerListener;

        public HtmlBannerWebViewListener(CustomEventBanner.CustomEventBannerListener customEventBannerListener) {
            this.mCustomEventBannerListener = customEventBannerListener;
        }

        public void onLoaded(BaseHtmlWebView baseHtmlWebView) {
            this.mCustomEventBannerListener.onBannerLoaded(baseHtmlWebView);
        }

        public void onFailed(MoPubErrorCode moPubErrorCode) {
            this.mCustomEventBannerListener.onBannerFailed(moPubErrorCode);
        }

        public void onClicked() {
            this.mCustomEventBannerListener.onBannerClicked();
        }

        public void onCollapsed() {
            this.mCustomEventBannerListener.onBannerCollapsed();
        }
    }
}
