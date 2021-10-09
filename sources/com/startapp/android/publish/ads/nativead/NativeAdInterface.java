package com.startapp.android.publish.ads.nativead;

import android.content.Context;
import android.graphics.Bitmap;
import com.startapp.android.publish.ads.nativead.StartAppNativeAd;

/* compiled from: StartAppSDK */
public interface NativeAdInterface {
    StartAppNativeAd.b getCampaignAction();

    String getCategory();

    String getDescription();

    Bitmap getImageBitmap();

    String getImageUrl();

    String getInstalls();

    String getPackacgeName();

    float getRating();

    Bitmap getSecondaryImageBitmap();

    String getSecondaryImageUrl();

    String getTitle();

    Boolean isApp();

    void sendClick(Context context);

    void sendImpression(Context context);
}
