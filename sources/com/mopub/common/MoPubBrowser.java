package com.mopub.common;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.mopub.common.util.Drawables;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.BaseWebView;
import com.mopub.mobileads.util.WebViews;

public class MoPubBrowser extends Activity {
    public static final String DESTINATION_URL_KEY = "URL";
    public static final String DSP_CREATIVE_ID = "mopub-dsp-creative-id";
    private static final int INNER_LAYOUT_ID = 1;
    public static final int MOPUB_BROWSER_REQUEST_CODE = 1;
    private ImageButton mBackButton;
    private ImageButton mCloseButton;
    private ImageButton mForwardButton;
    /* access modifiers changed from: private */
    public boolean mProgressBarAvailable;
    private ImageButton mRefreshButton;
    /* access modifiers changed from: private */
    public WebView mWebView;

    public ImageButton getBackButton() {
        return this.mBackButton;
    }

    public ImageButton getCloseButton() {
        return this.mCloseButton;
    }

    public ImageButton getForwardButton() {
        return this.mForwardButton;
    }

    public ImageButton getRefreshButton() {
        return this.mRefreshButton;
    }

    public WebView getWebView() {
        return this.mWebView;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setResult(-1);
        boolean requestFeature = getWindow().requestFeature(2);
        this.mProgressBarAvailable = requestFeature;
        if (requestFeature) {
            getWindow().setFeatureInt(2, -1);
        }
        setContentView(getMoPubBrowserView());
        initializeWebView();
        initializeButtons();
        enableCookies();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Utils.hideNavigationBar(this);
    }

    private void initializeWebView() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        this.mWebView.loadUrl(getIntent().getStringExtra(DESTINATION_URL_KEY));
        this.mWebView.setWebViewClient(new BrowserWebViewClient(this));
    }

    private void initializeButtons() {
        this.mBackButton.setBackgroundColor(0);
        this.mBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MoPubBrowser.this.mWebView.canGoBack()) {
                    MoPubBrowser.this.mWebView.goBack();
                }
            }
        });
        this.mForwardButton.setBackgroundColor(0);
        this.mForwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MoPubBrowser.this.mWebView.canGoForward()) {
                    MoPubBrowser.this.mWebView.goForward();
                }
            }
        });
        this.mRefreshButton.setBackgroundColor(0);
        this.mRefreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MoPubBrowser.this.mWebView.reload();
            }
        });
        this.mCloseButton.setBackgroundColor(0);
        this.mCloseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MoPubBrowser.this.finish();
            }
        });
    }

    private void enableCookies() {
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
        this.mWebView.setWebChromeClient((WebChromeClient) null);
        WebViews.onPause(this.mWebView, isFinishing());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        CookieSyncManager.getInstance().startSync();
        this.mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    MoPubBrowser.this.setTitle(webView.getUrl());
                } else {
                    MoPubBrowser.this.setTitle("Loading...");
                }
                if (MoPubBrowser.this.mProgressBarAvailable && Build.VERSION.SDK_INT < 24) {
                    MoPubBrowser.this.setProgress(i * 100);
                }
            }
        });
        this.mWebView.onResume();
    }

    public void finish() {
        ((ViewGroup) getWindow().getDecorView()).removeAllViews();
        super.finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mWebView.destroy();
        this.mWebView = null;
    }

    private View getMoPubBrowserView() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.addView(relativeLayout);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setId(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setBackgroundDrawable(Drawables.BACKGROUND.createDrawable(this));
        relativeLayout.addView(linearLayout2);
        this.mBackButton = getButton(Drawables.UNLEFT_ARROW.createDrawable(this));
        this.mForwardButton = getButton(Drawables.UNRIGHT_ARROW.createDrawable(this));
        this.mRefreshButton = getButton(Drawables.REFRESH.createDrawable(this));
        this.mCloseButton = getButton(Drawables.CLOSE.createDrawable(this));
        linearLayout2.addView(this.mBackButton);
        linearLayout2.addView(this.mForwardButton);
        linearLayout2.addView(this.mRefreshButton);
        linearLayout2.addView(this.mCloseButton);
        this.mWebView = new BaseWebView(this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(2, 1);
        this.mWebView.setLayoutParams(layoutParams2);
        relativeLayout.addView(this.mWebView);
        return linearLayout;
    }

    private ImageButton getButton(Drawable drawable) {
        ImageButton imageButton = new ImageButton(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        layoutParams.gravity = 16;
        imageButton.setLayoutParams(layoutParams);
        imageButton.setImageDrawable(drawable);
        return imageButton;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setWebView(WebView webView) {
        this.mWebView = webView;
    }
}
