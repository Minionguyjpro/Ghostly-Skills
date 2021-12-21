package com.appnext.ads.fullscreen;

import android.net.Uri;
import com.appnext.core.AppnextAd;
import com.appnext.core.p;

public interface j {
    void closeClicked();

    long closeDelay();

    int getCaptionTextTime();

    p getConfigManager();

    String getCtaText();

    String getLanguage();

    boolean getMute();

    AppnextAd getSelectedAd();

    Uri getSelectedVideoUri();

    int getTemplate(String str);

    void installClicked(AppnextAd appnextAd);

    boolean isInstalled();

    boolean isRewarded();

    void privacyClicked();

    void report(String str, String str2);

    boolean showClose();

    void videoEnded();

    void videoStarted();
}
