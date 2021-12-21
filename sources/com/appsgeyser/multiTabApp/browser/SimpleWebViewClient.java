package com.appsgeyser.multiTabApp.browser;

import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.ui.dialog.SslErrorDialog;
import java.net.URISyntaxException;
import java.util.Map;

public class SimpleWebViewClient extends WebViewClient {
    protected Context _context;

    public SimpleWebViewClient(Context context) {
        this._context = context;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        try {
            if (str.contains("youtube.com/")) {
                Factory.getInstance().getMainNavigationActivity().blockBannerviewAdsPlacement();
            }
            if (MailTo.isMailTo(str)) {
                _handleMailTo(str);
                return true;
            } else if (str.startsWith("tel:")) {
                this._context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(str)));
                return true;
            } else {
                if (!str.startsWith("market:")) {
                    if (!str.startsWith("geo:")) {
                        if (str.startsWith("smsto:")) {
                            _handleSmsTo(str);
                            return true;
                        } else if (str.startsWith("intent://")) {
                            _handleIntent(str, webView);
                            return true;
                        } else if (str.contains("/store/apps/details?id=")) {
                            _handlePlayStoreLink(str);
                            return true;
                        } else {
                            if (!str.startsWith("http:") && !str.startsWith("https:") && !str.startsWith("file:") && !str.equals("about:blank")) {
                                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                                if (this._context.getPackageManager().resolveActivity(intent, 0) != null) {
                                    this._context.startActivity(intent);
                                    return true;
                                }
                            }
                            return false;
                        }
                    }
                }
                this._context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void _handleSmsTo(String str) {
        String[] split = str.split(":");
        String str2 = split[1];
        String str3 = split.length > 1 ? split[2] : "";
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str2));
        intent.putExtra("address", str2);
        intent.putExtra("sms_body", str3);
        this._context.startActivity(intent);
    }

    private void _handleMailTo(String str) {
        MailTo parse = MailTo.parse(str);
        if (parse.getTo().length() > 0) {
            Map<String, String> headers = parse.getHeaders();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.EMAIL", new String[]{parse.getTo()});
            intent.putExtra("android.intent.extra.SUBJECT", parse.getSubject());
            intent.putExtra("android.intent.extra.CC", parse.getCc());
            if (headers.containsKey("bcc")) {
                intent.putExtra("android.intent.extra.BCC", headers.get("bcc"));
            }
            intent.putExtra("android.intent.extra.TEXT", parse.getBody());
            this._context.startActivity(intent);
        }
    }

    private void _handleIntent(String str, WebView webView) {
        try {
            Intent parseUri = Intent.parseUri(str, 1);
            if (parseUri != null) {
                webView.stopLoading();
                if (this._context.getPackageManager().resolveActivity(parseUri, 65536) != null) {
                    this._context.startActivity(parseUri);
                    return;
                }
                String stringExtra = parseUri.getStringExtra("browser_fallback_url");
                if (!stringExtra.startsWith("market:")) {
                    if (!str.startsWith("geo:")) {
                        if (stringExtra.contains("/store/apps/details?id=")) {
                            _handlePlayStoreLink(stringExtra);
                            return;
                        } else {
                            webView.loadUrl(stringExtra);
                            return;
                        }
                    }
                }
                this._context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringExtra)));
            }
        } catch (URISyntaxException e) {
            Log.e("SimpleWebView", "Can't resolve intent://", e);
        }
    }

    private void _handlePlayStoreLink(String str) {
        String substring = str.substring(str.indexOf("/store/apps/details?id=") + 23);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + substring));
        this._context.startActivity(intent);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        new SslErrorDialog(this._context).execute(webView, sslErrorHandler, sslError);
    }
}
