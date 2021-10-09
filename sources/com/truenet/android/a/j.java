package com.truenet.android.a;

import a.a.b.b.h;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.DisplayMetrics;
import android.webkit.WebView;

/* compiled from: StartAppSDK */
public final class j {
    public static final Bitmap a(WebView webView) {
        h.b(webView, "$receiver");
        if (Build.VERSION.SDK_INT >= 21) {
            WebView.enableSlowWholeDocumentDraw();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = webView.getContext();
        h.a((Object) context, "context");
        c.b(context).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        webView.measure(i, i2);
        webView.layout(0, 0, i, i2);
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache(true);
        Thread.sleep(500);
        Bitmap createBitmap = Bitmap.createBitmap(webView.getDrawingCache());
        webView.setDrawingCacheEnabled(false);
        h.a((Object) createBitmap, "bitmap");
        return createBitmap;
    }
}
