package com.startapp.android.mediation.admob;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.NativeAd;

public class StartAppNativeAdImage extends NativeAd.Image {
    private Bitmap bitmap;
    private Context context;
    private String uri;

    public double getScale() {
        return 1.0d;
    }

    public StartAppNativeAdImage(Context context2, Bitmap bitmap2, String str) {
        this.context = context2;
        this.bitmap = bitmap2;
        this.uri = str;
    }

    public Drawable getDrawable() {
        return new BitmapDrawable(this.context.getResources(), this.bitmap);
    }

    public Uri getUri() {
        return Uri.parse(this.uri);
    }
}
