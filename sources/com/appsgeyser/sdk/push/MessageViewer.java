package com.appsgeyser.sdk.push;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appsgeyser.sdk.R;

public class MessageViewer extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ActionBar actionBar;
        super.onCreate(bundle);
        setContentView(R.layout.appsgeysersdk_message_viewer);
        Intent intent = getIntent();
        if (intent != null) {
            if (Build.VERSION.SDK_INT >= 11 && (actionBar = getActionBar()) != null) {
                actionBar.hide();
            }
            TextView textView = (TextView) findViewById(R.id.message_viewer_app_name_text_view);
            PackageManager packageManager = getPackageManager();
            CharSequence applicationLabel = packageManager.getApplicationLabel(getApplicationInfo());
            if (applicationLabel != null) {
                textView.setText(applicationLabel);
            } else {
                textView.setVisibility(8);
            }
            ImageView imageView = (ImageView) findViewById(R.id.message_viewer_imageView_icon);
            Drawable applicationIcon = packageManager.getApplicationIcon(getApplicationInfo());
            if (applicationIcon != null) {
                Resources resources = getResources();
                int i = (int) ((resources.getDisplayMetrics().density * 36.0f) + 0.5f);
                imageView.setImageDrawable(new BitmapDrawable(resources, Bitmap.createScaledBitmap(((BitmapDrawable) applicationIcon).getBitmap(), i, i, true)));
            } else {
                imageView.setVisibility(8);
            }
            if (imageView.getVisibility() == 8 && textView.getVisibility() == 8) {
                ((RelativeLayout) findViewById(R.id.message_viewer_app_bar_fake)).setVisibility(8);
            }
            WebView webView = (WebView) findViewById(R.id.message_viewer_web_view);
            String stringExtra = intent.getStringExtra("com.appsgeyser.sdk.push.MessageViewer.title");
            String stringExtra2 = intent.getStringExtra("com.appsgeyser.sdk.push.MessageViewer.message");
            if (!(stringExtra == null || stringExtra2 == null)) {
                webView.loadDataWithBaseURL((String) null, createHtml(stringExtra, stringExtra2), "text/html", "utf-8", (String) null);
                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setAllowFileAccess(true);
                settings.setGeolocationEnabled(true);
                settings.setCacheMode(-1);
                webView.setVerticalScrollBarEnabled(false);
                webView.setHorizontalScrollBarEnabled(false);
                settings.setLoadWithOverviewMode(true);
                settings.setUseWideViewPort(true);
                webView.setInitialScale(0);
            }
            findViewById(R.id.message_viewer_button).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent launchIntentForPackage = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName());
                    launchIntentForPackage.addFlags(67108864);
                    launchIntentForPackage.addFlags(268435456);
                    launchIntentForPackage.addFlags(2097152);
                    this.startActivity(launchIntentForPackage);
                }
            });
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        finish();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (isFinishing()) {
            finish();
        }
    }

    private String createHtml(String str, String str2) {
        return "<!DOCTYPE html><html><head><title></title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><style>body {background-color:#ffffff;background-repeat:no-repeat;background-position:top left;background-attachment:fixed;}h1{font-family:Arial, sans-serif;font-size:16px;color:#000000;background-color:#ffffff;}p {font-family:Georgia, serif;font-size:14px;font-style:normal;font-weight:normal;color:#000000;background-color:#ffffff;}</style></head><body><h1>" + str + "</h1>" + str2 + "</body></html>";
    }
}
