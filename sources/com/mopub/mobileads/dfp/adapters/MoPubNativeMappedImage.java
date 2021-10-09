package com.mopub.mobileads.dfp.adapters;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.NativeAd;

public class MoPubNativeMappedImage extends NativeAd.Image {
    private Drawable mDrawable;
    private Uri mImageUri;
    private double mScale;

    public MoPubNativeMappedImage(Drawable drawable, String str, double d) {
        this.mDrawable = drawable;
        this.mImageUri = Uri.parse(str);
        this.mScale = d;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public Uri getUri() {
        return this.mImageUri;
    }

    public double getScale() {
        return this.mScale;
    }
}
