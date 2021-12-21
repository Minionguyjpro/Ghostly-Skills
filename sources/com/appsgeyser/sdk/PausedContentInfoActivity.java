package com.appsgeyser.sdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.server.network.NetworkManager;

public class PausedContentInfoActivity extends Activity {
    public static void startPausedContentInfoActivity(Context context, boolean z) {
        Intent intent = new Intent(context, PausedContentInfoActivity.class);
        intent.putExtra("CustomHtmlAboutKey", z);
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.appsgeysersdk_paused_content_activity);
        Log.d("PausedContentInfo", "created pausedActivity");
        WebView webView = (WebView) findViewById(R.id.webView);
        final boolean booleanExtra = getIntent().getBooleanExtra("CustomHtmlAboutKey", false);
        final String str2 = "http://www.appsgeyser.com/branding/" + Configuration.getInstance(this).getApplicationId();
        if (booleanExtra) {
            str = str2;
        } else {
            str = "https://www.appsgeyser.com/paused/" + Configuration.getInstance(this).getApplicationId();
        }
        if (booleanExtra) {
            ImageView imageView = (ImageView) findViewById(R.id.close_screen);
            imageView.setVisibility(0);
            imageView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    PausedContentInfoActivity.this.lambda$onCreate$0$PausedContentInfoActivity(view);
                }
            });
            imageView.bringToFront();
        }
        if (NetworkManager.isOnline(this)) {
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (Uri.parse(str).getScheme().equals("market")) {
                        try {
                            webView.stopLoading();
                            webView.goBack();
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setData(Uri.parse(str));
                            ((Activity) webView.getContext()).startActivity(intent);
                            return false;
                        } catch (ActivityNotFoundException unused) {
                            Uri parse = Uri.parse(str);
                            webView.loadUrl("http://play.google.com/store/apps/" + parse.getHost() + "?" + parse.getQuery());
                            return false;
                        }
                    } else if (!booleanExtra || str.contains("appsgeyser.com/branding/")) {
                        webView.loadUrl(str);
                        return false;
                    } else {
                        PausedContentInfoActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        return true;
                    }
                }

                public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                    if (!booleanExtra || webResourceRequest.getUrl().toString().equals(str2)) {
                        webView.loadUrl(webResourceRequest.getUrl().toString());
                    }
                    return super.shouldOverrideUrlLoading(webView, webResourceRequest);
                }
            });
            webView.loadUrl(str);
        } else if (!booleanExtra) {
            webView.setVisibility(8);
            ((FrameLayout) findViewById(R.id.ban_view)).setVisibility(0);
        }
    }

    public /* synthetic */ void lambda$onCreate$0$PausedContentInfoActivity(View view) {
        finish();
    }

    public void onBackPressed() {
        if (getIntent().getBooleanExtra("CustomHtmlAboutKey", false)) {
            super.onBackPressed();
        }
    }
}
