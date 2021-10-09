package com.tappx.a;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class l3 extends WebView {
    private static boolean c = false;

    /* renamed from: a  reason: collision with root package name */
    private a f509a;
    private boolean b;

    public interface a {
        void b(boolean z);
    }

    public l3(Context context) {
        super(context.getApplicationContext());
        boolean z = false;
        a(false);
        c();
        w4.a((WebView) this);
        if (!c) {
            a(getContext());
            c = true;
        }
        this.b = getVisibility() == 0 ? true : z;
    }

    private void c() {
        getSettings().setAllowFileAccess(false);
        getSettings().setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            getSettings().setAllowFileAccessFromFileURLs(false);
            getSettings().setAllowUniversalAccessFromFileURLs(false);
        }
    }

    public void a() {
        if (Build.VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
    }

    public boolean b() {
        return this.b;
    }

    public void destroy() {
        w4.b(this);
        removeAllViews();
        super.destroy();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        boolean z = i == 0;
        if (z != this.b) {
            this.b = z;
            a aVar = this.f509a;
            if (aVar != null) {
                aVar.b(z);
            }
        }
    }

    public void setVisibilityChangedListener(a aVar) {
        this.f509a = aVar;
    }

    public void a(boolean z) {
        if (Build.VERSION.SDK_INT < 18) {
            if (z) {
                getSettings().setPluginState(WebSettings.PluginState.ON);
            } else {
                getSettings().setPluginState(WebSettings.PluginState.OFF);
            }
        }
    }

    private void a(Context context) {
        if (Build.VERSION.SDK_INT == 19) {
            WebView webView = new WebView(context.getApplicationContext());
            webView.setBackgroundColor(0);
            webView.loadDataWithBaseURL((String) null, "", "text/html", "UTF-8", (String) null);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = 1;
            layoutParams.height = 1;
            layoutParams.type = 2005;
            layoutParams.flags = 16777240;
            layoutParams.format = -2;
            layoutParams.gravity = 8388659;
            try {
                ((WindowManager) context.getSystemService("window")).addView(webView, layoutParams);
            } catch (Exception unused) {
            }
        }
    }
}
