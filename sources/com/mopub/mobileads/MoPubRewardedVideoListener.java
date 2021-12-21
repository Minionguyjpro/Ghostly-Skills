package com.mopub.mobileads;

import com.mopub.common.MoPubReward;
import java.util.Set;

public interface MoPubRewardedVideoListener {
    void onRewardedVideoClicked(String str);

    void onRewardedVideoClosed(String str);

    void onRewardedVideoCompleted(Set<String> set, MoPubReward moPubReward);

    void onRewardedVideoLoadFailure(String str, MoPubErrorCode moPubErrorCode);

    void onRewardedVideoLoadSuccess(String str);

    void onRewardedVideoPlaybackError(String str, MoPubErrorCode moPubErrorCode);

    void onRewardedVideoStarted(String str);
}
