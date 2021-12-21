package com.appnext.ads.fullscreen;

import com.appnext.core.AppnextAd;
import com.appnext.core.p;
import java.util.ArrayList;

public interface h {
    void closeClicked();

    p getConfigManager();

    String getCtaText();

    String getLanguage();

    ArrayList<AppnextAd> getPostRollAds();

    int getTemplate(String str);

    void installClicked(AppnextAd appnextAd);

    boolean isRewarded();

    void privacyClicked();

    void report(String str, String str2);
}
