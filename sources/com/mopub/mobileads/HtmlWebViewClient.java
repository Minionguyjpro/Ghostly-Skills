package com.mopub.mobileads;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import java.util.EnumSet;

class HtmlWebViewClient extends WebViewClient {
    static final String MOPUB_FAIL_LOAD = "mopub://failLoad";
    static final String MOPUB_FINISH_LOAD = "mopub://finishLoad";
    private final EnumSet<UrlAction> SUPPORTED_URL_ACTIONS = EnumSet.of(UrlAction.HANDLE_MOPUB_SCHEME, new UrlAction[]{UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.HANDLE_PHONE_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK});
    private final String mClickthroughUrl;
    private final Context mContext;
    private final String mDspCreativeId;
    /* access modifiers changed from: private */
    public final BaseHtmlWebView mHtmlWebView;
    /* access modifiers changed from: private */
    public final HtmlWebViewListener mHtmlWebViewListener;

    HtmlWebViewClient(HtmlWebViewListener htmlWebViewListener, BaseHtmlWebView baseHtmlWebView, String str, String str2) {
        this.mHtmlWebViewListener = htmlWebViewListener;
        this.mHtmlWebView = baseHtmlWebView;
        this.mClickthroughUrl = str;
        this.mDspCreativeId = str2;
        this.mContext = baseHtmlWebView.getContext();
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        new UrlHandler.Builder().withDspCreativeId(this.mDspCreativeId).withSupportedUrlActions(this.SUPPORTED_URL_ACTIONS).withResultActions(new UrlHandler.ResultActions() {
            public void urlHandlingFailed(String str, UrlAction urlAction) {
            }

            public void urlHandlingSucceeded(String str, UrlAction urlAction) {
                if (HtmlWebViewClient.this.mHtmlWebView.wasClicked()) {
                    HtmlWebViewClient.this.mHtmlWebViewListener.onClicked();
                    HtmlWebViewClient.this.mHtmlWebView.onResetUserClick();
                }
            }
        }).withMoPubSchemeListener(new UrlHandler.MoPubSchemeListener() {
            public void onCrash() {
            }

            public void onFinishLoad() {
                HtmlWebViewClient.this.mHtmlWebViewListener.onLoaded(HtmlWebViewClient.this.mHtmlWebView);
            }

            public void onClose() {
                HtmlWebViewClient.this.mHtmlWebViewListener.onCollapsed();
            }

            public void onFailLoad() {
                HtmlWebViewClient.this.mHtmlWebView.stopLoading();
                HtmlWebViewClient.this.mHtmlWebViewListener.onFailed(MoPubErrorCode.UNSPECIFIED);
            }
        }).build().handleUrl(this.mContext, str, this.mHtmlWebView.wasClicked());
        return true;
    }
}
