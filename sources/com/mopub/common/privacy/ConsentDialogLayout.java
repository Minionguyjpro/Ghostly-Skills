package com.mopub.common.privacy;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.mopub.common.CloseableLayout;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.mobileads.MoPubErrorCode;

class ConsentDialogLayout extends CloseableLayout {
    static int FINISHED_LOADING = 101;
    static final String URL_CLOSE = "mopub://close";
    static final String URL_CONSENT_NO = "mopub://consent?no";
    static final String URL_CONSENT_YES = "mopub://consent?yes";
    /* access modifiers changed from: private */
    public ConsentListener mConsentListener;
    /* access modifiers changed from: private */
    public PageLoadListener mLoadListener;
    private final WebView mWebView = initWebView();
    private final WebViewClient webViewClient = new WebViewClient() {
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (ConsentDialogLayout.this.mLoadListener != null) {
                ConsentDialogLayout.this.mLoadListener.onLoadProgress(0);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (ConsentDialogLayout.this.mLoadListener != null) {
                ConsentDialogLayout.this.mLoadListener.onLoadProgress(ConsentDialogLayout.FINISHED_LOADING);
            }
            super.onPageFinished(webView, str);
        }

        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            Object[] objArr = new Object[1];
            objArr[0] = (renderProcessGoneDetail == null || !renderProcessGoneDetail.didCrash()) ? MoPubErrorCode.RENDER_PROCESS_GONE_UNSPECIFIED : MoPubErrorCode.RENDER_PROCESS_GONE_WITH_CRASH;
            MoPubLog.log(sdkLogEvent, objArr);
            return true;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (ConsentDialogLayout.URL_CONSENT_YES.equals(str)) {
                if (ConsentDialogLayout.this.mConsentListener != null) {
                    ConsentDialogLayout.this.mConsentListener.onConsentClick(ConsentStatus.EXPLICIT_YES);
                }
                return true;
            } else if (ConsentDialogLayout.URL_CONSENT_NO.equals(str)) {
                if (ConsentDialogLayout.this.mConsentListener != null) {
                    ConsentDialogLayout.this.mConsentListener.onConsentClick(ConsentStatus.EXPLICIT_NO);
                }
                return true;
            } else if (ConsentDialogLayout.URL_CLOSE.equals(str)) {
                if (ConsentDialogLayout.this.mConsentListener != null) {
                    ConsentDialogLayout.this.mConsentListener.onCloseClick();
                }
                return true;
            } else {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        Context context = ConsentDialogLayout.this.getContext();
                        Uri parse = Uri.parse(str);
                        Intents.launchActionViewIntent(context, parse, "Cannot open native browser for " + str);
                        return true;
                    } catch (IntentNotResolvableException e) {
                        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, e.getMessage());
                    }
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        }
    };

    interface ConsentListener {
        void onCloseClick();

        void onConsentClick(ConsentStatus consentStatus);
    }

    interface PageLoadListener {
        void onLoadProgress(int i);
    }

    public ConsentDialogLayout(Context context) {
        super(context);
    }

    public ConsentDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ConsentDialogLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: package-private */
    public void startLoading(String str, PageLoadListener pageLoadListener) {
        Preconditions.checkNotNull(str);
        this.mLoadListener = pageLoadListener;
        setupEventsListeners(this.mWebView);
        WebView webView = this.mWebView;
        webView.loadDataWithBaseURL("https://" + Constants.HOST + "/", str, "text/html", "UTF-8", (String) null);
    }

    /* access modifiers changed from: package-private */
    public void setConsentClickListener(ConsentListener consentListener) {
        Preconditions.checkNotNull(consentListener);
        this.mConsentListener = consentListener;
    }

    private WebView initWebView() {
        WebView webView = new WebView(getContext());
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        webView.setId(View.generateViewId());
        setCloseVisible(false);
        addView(webView, new FrameLayout.LayoutParams(-1, -1));
        return webView;
    }

    private void setupEventsListeners(WebView webView) {
        webView.setWebViewClient(this.webViewClient);
        setOnCloseListener(new CloseableLayout.OnCloseListener() {
            public void onClose() {
                if (ConsentDialogLayout.this.mConsentListener != null) {
                    ConsentDialogLayout.this.mConsentListener.onCloseClick();
                }
            }
        });
    }
}
