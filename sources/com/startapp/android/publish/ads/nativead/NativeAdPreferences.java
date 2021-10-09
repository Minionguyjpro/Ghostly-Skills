package com.startapp.android.publish.ads.nativead;

import com.mopub.mobileads.resource.DrawableConstants;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class NativeAdPreferences extends AdPreferences {
    private static final int DEFAULT_ADS_NUMBER = 1;
    private static final boolean DEFAULT_AUTO_DOWNLOAD_BITMAP = false;
    private static final boolean DEFAULT_IS_CONTENT_AD = false;
    private static final boolean DEFAULT_USE_SIMPLE_TOKEN = true;
    private static String EXCEPTION_LOW_ADS_NUMBER = "Ads Number must be >= 1";
    private static final long serialVersionUID = 1;
    private int adsNumber = 1;
    private boolean autoBitmapDownload = false;
    private NativeAdBitmapSize bitmapSize;
    private boolean isContentAd = false;
    private int moreImage = -1;
    private int primaryImage = -1;
    private boolean useSimpleToken = true;

    /* compiled from: StartAppSDK */
    public enum NativeAdBitmapSize {
        SIZE72X72(72, 72),
        SIZE100X100(100, 100),
        SIZE150X150(DrawableConstants.CtaButton.WIDTH_DIPS, DrawableConstants.CtaButton.WIDTH_DIPS),
        SIZE340X340(340, 340);
        
        int height;
        int width;

        private NativeAdBitmapSize(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }
    }

    public int getAdsNumber() {
        return this.adsNumber;
    }

    public NativeAdPreferences setAdsNumber(int i) {
        if (i > 0) {
            this.adsNumber = i;
            return this;
        }
        throw new IllegalArgumentException(EXCEPTION_LOW_ADS_NUMBER);
    }

    public boolean isAutoBitmapDownload() {
        return this.autoBitmapDownload;
    }

    public NativeAdPreferences setAutoBitmapDownload(boolean z) {
        this.autoBitmapDownload = z;
        return this;
    }

    public NativeAdBitmapSize getImageSize() {
        return this.bitmapSize;
    }

    public NativeAdPreferences setImageSize(NativeAdBitmapSize nativeAdBitmapSize) {
        this.bitmapSize = nativeAdBitmapSize;
        return this;
    }

    public NativeAdPreferences setPrimaryImageSize(int i) {
        this.primaryImage = i;
        return this;
    }

    public int getPrimaryImageSize() {
        return this.primaryImage;
    }

    public NativeAdPreferences setSecondaryImageSize(int i) {
        this.moreImage = i;
        return this;
    }

    public int getSecondaryImageSize() {
        return this.moreImage;
    }

    public boolean isContentAd() {
        return this.isContentAd;
    }

    public NativeAdPreferences setContentAd(boolean z) {
        this.isContentAd = z;
        return this;
    }

    public boolean isSimpleToken() {
        return this.useSimpleToken;
    }

    public NativeAdPreferences useSimpleToken(boolean z) {
        this.useSimpleToken = z;
        return this;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n===== NativeAdConfig =====\n");
        stringBuffer.append("    adsNumber: [" + getAdsNumber() + "]\n");
        stringBuffer.append("    autoBitmapDownload: [" + isAutoBitmapDownload() + "]\n");
        stringBuffer.append("    useSimpleToken: [" + isSimpleToken() + "]\n");
        stringBuffer.append("===== End NativeAdConfig =====");
        return stringBuffer.toString();
    }
}
