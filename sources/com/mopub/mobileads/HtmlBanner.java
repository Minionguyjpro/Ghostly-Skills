package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.factories.HtmlBannerWebViewFactory;
import java.lang.ref.WeakReference;
import java.util.Map;

public class HtmlBanner extends CustomEventBanner {
    public static final String ADAPTER_NAME = HtmlBanner.class.getSimpleName();
    private ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    private HtmlBannerWebView mHtmlBannerWebView;
    private WeakReference<Activity> mWeakActivity;

    /* access modifiers changed from: protected */
    public void loadBanner(Context context, CustomEventBanner.CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        if (extrasAreValid(map2)) {
            String str = map2.get(DataKeys.HTML_RESPONSE_BODY_KEY);
            try {
                HtmlBannerWebView create = HtmlBannerWebViewFactory.create(context, (AdReport) map.get(DataKeys.AD_REPORT_KEY), customEventBannerListener, map2.get(DataKeys.CLICKTHROUGH_URL_KEY));
                this.mHtmlBannerWebView = create;
                AdViewController.setShouldHonorServerDimensions(create);
                MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, ADAPTER_NAME);
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    this.mWeakActivity = new WeakReference<>(activity);
                    ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(activity);
                    this.mExternalViewabilitySessionManager = externalViewabilitySessionManager;
                    externalViewabilitySessionManager.createDisplaySession(activity, this.mHtmlBannerWebView);
                } else {
                    MoPubLog.log(MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Unable to start viewability session for HTML banner: Context provided was not an Activity.");
                }
                this.mHtmlBannerWebView.loadHtmlResponse(str);
                MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_SUCCESS, ADAPTER_NAME);
            } catch (ClassCastException unused) {
                MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.INTERNAL_ERROR.getIntCode()), MoPubErrorCode.INTERNAL_ERROR);
                customEventBannerListener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        } else {
            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.NETWORK_INVALID_STATE.getIntCode()), MoPubErrorCode.NETWORK_INVALID_STATE);
            customEventBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        HtmlBannerWebView htmlBannerWebView = this.mHtmlBannerWebView;
        if (htmlBannerWebView != null) {
            htmlBannerWebView.destroy();
            this.mHtmlBannerWebView = null;
        }
    }

    /* access modifiers changed from: protected */
    public void trackMpxAndThirdPartyImpressions() {
        WeakReference<Activity> weakReference;
        HtmlBannerWebView htmlBannerWebView = this.mHtmlBannerWebView;
        if (htmlBannerWebView != null) {
            htmlBannerWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
            if (this.mExternalViewabilitySessionManager != null && (weakReference = this.mWeakActivity) != null) {
                Activity activity = (Activity) weakReference.get();
                if (activity != null) {
                    this.mExternalViewabilitySessionManager.startDeferredDisplaySession(activity);
                    return;
                }
                MoPubLog.log(MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Lost the activity for deferred Viewability tracking. Dropping session.");
            }
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        return map.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }
}
