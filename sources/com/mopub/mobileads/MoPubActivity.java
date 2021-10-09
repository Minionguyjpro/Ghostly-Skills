package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.common.AdReport;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.WebViewCacheService;
import com.mopub.mobileads.factories.HtmlInterstitialWebViewFactory;
import com.mopub.mraid.MraidController;
import java.io.Serializable;

public class MoPubActivity extends BaseInterstitialActivity {
    private ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    /* access modifiers changed from: private */
    public HtmlInterstitialWebView mHtmlInterstitialWebView;

    public static void start(Context context, AdReport adReport, String str, CreativeOrientation creativeOrientation, long j) {
        MoPubLog.log(MoPubLog.AdLogEvent.SHOW_ATTEMPTED, new Object[0]);
        try {
            context.startActivity(createIntent(context, adReport, str, creativeOrientation, j));
        } catch (ActivityNotFoundException unused) {
            Log.d("MoPubActivity", "MoPubActivity not found - did you declare it in AndroidManifest.xml?");
        }
    }

    static Intent createIntent(Context context, AdReport adReport, String str, CreativeOrientation creativeOrientation, long j) {
        Intent intent = new Intent(context, MoPubActivity.class);
        intent.putExtra(DataKeys.CLICKTHROUGH_URL_KEY, str);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.putExtra(DataKeys.CREATIVE_ORIENTATION_KEY, creativeOrientation);
        intent.addFlags(268435456);
        return intent;
    }

    static void preRenderHtml(Interstitial interstitial, Context context, AdReport adReport, final CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, String str, long j) {
        MoPubLog.log(MoPubLog.AdLogEvent.LOAD_ATTEMPTED, new Object[0]);
        HtmlInterstitialWebView create = HtmlInterstitialWebViewFactory.create(context.getApplicationContext(), adReport, customEventInterstitialListener, str);
        create.enableJavascriptCaching();
        create.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if ("mopub://finishLoad".equals(str)) {
                    customEventInterstitialListener.onInterstitialLoaded();
                    return true;
                } else if (!"mopub://failLoad".equals(str)) {
                    return true;
                } else {
                    customEventInterstitialListener.onInterstitialFailed((MoPubErrorCode) null);
                    return true;
                }
            }
        });
        ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(context);
        externalViewabilitySessionManager.createDisplaySession(context, create);
        create.loadHtmlResponse(getResponseString(adReport));
        WebViewCacheService.storeWebViewConfig(Long.valueOf(j), interstitial, create, externalViewabilitySessionManager, (MraidController) null);
    }

    public View getAdView() {
        WebViewCacheService.Config popWebViewConfig;
        String stringExtra = getIntent().getStringExtra(DataKeys.CLICKTHROUGH_URL_KEY);
        String responseString = getResponseString();
        Long broadcastIdentifier = getBroadcastIdentifier();
        if (broadcastIdentifier == null || (popWebViewConfig = WebViewCacheService.popWebViewConfig(broadcastIdentifier)) == null || !(popWebViewConfig.getWebView() instanceof HtmlInterstitialWebView)) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "WebView cache miss. Recreating the WebView.");
            this.mHtmlInterstitialWebView = HtmlInterstitialWebViewFactory.create(getApplicationContext(), this.mAdReport, new BroadcastingInterstitialListener(), stringExtra);
            ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(this);
            this.mExternalViewabilitySessionManager = externalViewabilitySessionManager;
            externalViewabilitySessionManager.createDisplaySession(this, this.mHtmlInterstitialWebView);
            this.mHtmlInterstitialWebView.loadHtmlResponse(responseString);
            return this.mHtmlInterstitialWebView;
        }
        HtmlInterstitialWebView htmlInterstitialWebView = (HtmlInterstitialWebView) popWebViewConfig.getWebView();
        this.mHtmlInterstitialWebView = htmlInterstitialWebView;
        htmlInterstitialWebView.init(new BroadcastingInterstitialListener(), stringExtra, this.mAdReport != null ? this.mAdReport.getDspCreativeId() : null);
        this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
        this.mExternalViewabilitySessionManager = popWebViewConfig.getViewabilityManager();
        return this.mHtmlInterstitialWebView;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        CreativeOrientation creativeOrientation;
        super.onCreate(bundle);
        Serializable serializableExtra = getIntent().getSerializableExtra(DataKeys.CREATIVE_ORIENTATION_KEY);
        if (serializableExtra == null || !(serializableExtra instanceof CreativeOrientation)) {
            creativeOrientation = CreativeOrientation.DEVICE;
        } else {
            creativeOrientation = (CreativeOrientation) serializableExtra;
        }
        DeviceUtils.lockOrientation(this, creativeOrientation);
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.startDeferredDisplaySession(this);
        }
        EventForwardingBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_SHOW);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        HtmlInterstitialWebView htmlInterstitialWebView = this.mHtmlInterstitialWebView;
        if (htmlInterstitialWebView != null) {
            htmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getUrl());
            this.mHtmlInterstitialWebView.destroy();
        }
        EventForwardingBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_DISMISS);
        super.onDestroy();
    }

    class BroadcastingInterstitialListener implements CustomEventInterstitial.CustomEventInterstitialListener {
        public void onInterstitialImpression() {
        }

        BroadcastingInterstitialListener() {
        }

        public void onInterstitialLoaded() {
            MoPubLog.log(MoPubLog.AdLogEvent.LOAD_SUCCESS, new Object[0]);
            if (MoPubActivity.this.mHtmlInterstitialWebView != null) {
                MoPubActivity.this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
            }
        }

        public void onInterstitialFailed(MoPubErrorCode moPubErrorCode) {
            MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, Integer.valueOf(MoPubErrorCode.VIDEO_CACHE_ERROR.getIntCode()), MoPubErrorCode.VIDEO_CACHE_ERROR);
            MoPubActivity moPubActivity = MoPubActivity.this;
            EventForwardingBroadcastReceiver.broadcastAction(moPubActivity, moPubActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_FAIL);
            MoPubActivity.this.finish();
        }

        public void onInterstitialShown() {
            MoPubLog.log(MoPubLog.AdLogEvent.SHOW_SUCCESS, new Object[0]);
        }

        public void onInterstitialClicked() {
            MoPubLog.log(MoPubLog.AdLogEvent.CLICKED, new Object[0]);
            MoPubActivity moPubActivity = MoPubActivity.this;
            EventForwardingBroadcastReceiver.broadcastAction(moPubActivity, moPubActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_CLICK);
        }

        public void onLeaveApplication() {
            MoPubLog.log(MoPubLog.AdLogEvent.WILL_LEAVE_APPLICATION, new Object[0]);
        }

        public void onInterstitialDismissed() {
            MoPubLog.log(MoPubLog.AdLogEvent.DID_DISAPPEAR, new Object[0]);
        }
    }
}
