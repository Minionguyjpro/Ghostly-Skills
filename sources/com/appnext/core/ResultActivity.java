package com.appnext.core;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.google.android.gms.plus.PlusShare;
import com.mopub.common.Constants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ResultActivity extends Activity {
    private Intent hA;
    private WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Intent selector;
        requestWindowFeature(1);
        super.onCreate(bundle);
        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(1);
        WebView webView2 = new WebView(getApplicationContext());
        this.webView = webView2;
        webView2.getSettings().setTextZoom(100);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setAppCacheEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            this.webView.getSettings().setMixedContentMode(0);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.webView.setLayerType(2, (Paint) null);
        } else {
            this.webView.setLayerType(1, (Paint) null);
        }
        this.webView.setWebViewClient(new WebViewClient() {
            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                String str2;
                if (str == null) {
                    return false;
                }
                new StringBuilder("result page url ").append(str);
                if (str.startsWith(Constants.HTTP)) {
                    ResultActivity resultActivity = ResultActivity.this;
                    if (resultActivity.hasNewResolver(ResultActivity.U(str).setComponent((ComponentName) null))) {
                        ResultActivity.b(ResultActivity.this, str);
                    } else {
                        webView.loadUrl(str);
                    }
                    return true;
                } else if (str.startsWith("intent://")) {
                    try {
                        Intent parseUri = Intent.parseUri(str, 1);
                        if (ResultActivity.this.getPackageManager().resolveActivity(parseUri, 65536) != null) {
                            ResultActivity.b(ResultActivity.this, parseUri.getData().toString());
                            return true;
                        }
                        if (parseUri.getExtras() != null) {
                            if (parseUri.getExtras().containsKey("browser_fallback_url") && !parseUri.getExtras().getString("browser_fallback_url").equals("")) {
                                str2 = parseUri.getExtras().getString("browser_fallback_url");
                                ResultActivity.b(ResultActivity.this, str2);
                                return true;
                            }
                        }
                        if (parseUri.getExtras().containsKey("market_referrer") && !parseUri.getExtras().getString("market_referrer").equals("")) {
                            str2 = "market://details?id=" + parseUri.getPackage() + "&referrer=" + parseUri.getExtras().getString("market_referrer");
                            ResultActivity.b(ResultActivity.this, str2);
                        }
                        return true;
                    } catch (Throwable unused) {
                        return false;
                    }
                } else {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    try {
                        if (ResultActivity.this.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                            ResultActivity.b(ResultActivity.this, str);
                            return true;
                        }
                    } catch (Throwable unused2) {
                    }
                    return false;
                }
            }
        });
        linearLayout.addView(this.webView);
        this.webView.setLayoutParams(new LinearLayout.LayoutParams(-1, 0));
        ((LinearLayout.LayoutParams) this.webView.getLayoutParams()).weight = 1.0f;
        try {
            String string = getIntent().getExtras().getString("url");
            getIntent().getExtras().getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
            new StringBuilder("loading result page ").append(string);
            this.hA = new Intent(U(string)).setComponent((ComponentName) null);
            if (Build.VERSION.SDK_INT >= 15 && (selector = this.hA.getSelector()) != null) {
                selector.setComponent((ComponentName) null);
            }
            this.webView.loadUrl(string);
        } catch (Throwable unused) {
            finish();
        }
    }

    private static List a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : queryIntentActivities) {
            arrayList.add(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
        }
        return arrayList;
    }

    public final boolean hasNewResolver(Intent intent) {
        if (this.hA == null) {
            return intent != null;
        }
        if (intent == null) {
            return false;
        }
        List<ComponentName> a2 = a((Context) this, intent);
        HashSet hashSet = new HashSet();
        hashSet.addAll(a((Context) this, this.hA));
        for (ComponentName contains : a2) {
            if (!hashSet.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static Intent U(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        return intent;
    }

    private void bl() {
        onBackPressed();
    }

    private void openLink(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void onBackPressed() {
        try {
            if (this.webView == null || !this.webView.canGoBack()) {
                super.onBackPressed();
            } else {
                this.webView.goBack();
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    static /* synthetic */ void b(ResultActivity resultActivity, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        resultActivity.startActivity(intent);
    }
}
