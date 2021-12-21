package com.mopub.mobileads;

import android.app.Activity;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.SdkConfiguration;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import java.util.Set;

public class MoPubRewardedVideos {
    private static void initializeRewardedVideo(Activity activity, MediationSettings... mediationSettingsArr) {
        Preconditions.checkNotNull(activity);
        MoPubRewardedVideoManager.init(activity, mediationSettingsArr);
    }

    private static void initializeRewardedVideo(Activity activity, SdkConfiguration sdkConfiguration) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(sdkConfiguration);
        initializeRewardedVideo(activity, sdkConfiguration.getMediationSettings());
    }

    public static void setRewardedVideoListener(MoPubRewardedVideoListener moPubRewardedVideoListener) {
        MoPubRewardedVideoManager.setVideoListener(moPubRewardedVideoListener);
    }

    public static void loadRewardedVideo(String str, MediationSettings... mediationSettingsArr) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager.loadVideo(str, (MoPubRewardedVideoManager.RequestParameters) null, mediationSettingsArr);
    }

    public static void loadRewardedVideo(String str, MoPubRewardedVideoManager.RequestParameters requestParameters, MediationSettings... mediationSettingsArr) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager.loadVideo(str, requestParameters, mediationSettingsArr);
    }

    public static boolean hasRewardedVideo(String str) {
        Preconditions.checkNotNull(str);
        return MoPubRewardedVideoManager.hasVideo(str);
    }

    public static void showRewardedVideo(String str) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager.showVideo(str);
    }

    public static void showRewardedVideo(String str, String str2) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager.showVideo(str, str2);
    }

    public static Set<MoPubReward> getAvailableRewards(String str) {
        Preconditions.checkNotNull(str);
        return MoPubRewardedVideoManager.getAvailableRewards(str);
    }

    public static void selectReward(String str, MoPubReward moPubReward) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(moPubReward);
        MoPubRewardedVideoManager.selectReward(str, moPubReward);
    }
}
