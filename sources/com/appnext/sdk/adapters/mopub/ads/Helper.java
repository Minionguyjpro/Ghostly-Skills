package com.appnext.sdk.adapters.mopub.ads;

import com.appnext.ads.fullscreen.FullscreenConfig;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.VideoConfig;
import com.appnext.banners.BannerSize;
import com.appnext.core.Configuration;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.Map;

public class Helper {
    protected static String getAppnextPlacementIdExtraKey(Map<String, String> map) {
        if (map != null) {
            return map.get("AppnextPlacementId");
        }
        return null;
    }

    protected static BannerSize getBannerSize(Map<String, String> map) {
        if (map == null) {
            return BannerSize.BANNER;
        }
        String str = map.get("AppnextBannerSize");
        if (str.equals("LARGE_BANNER")) {
            return BannerSize.LARGE_BANNER;
        }
        if (str.equals("MEDIUM_RECTANGLE")) {
            return BannerSize.MEDIUM_RECTANGLE;
        }
        if (str.equals(AdPreferences.TYPE_BANNER)) {
            return BannerSize.BANNER;
        }
        throw new IllegalArgumentException("Wrong size");
    }

    protected static boolean hasAdConfigServerExtras(Map<String, String> map) {
        return map != null && (map.containsKey("AppnextButtonColor") || map.containsKey("AppnextCategories") || map.containsKey("AppnextPostback") || map.containsKey("AppnextOrientation") || map.containsKey("AppnextMinVideoLen") || map.containsKey("AppnextMaxVideoLen") || map.containsKey("AppnextBackButtonCanClose") || map.containsKey("AppnextMute"));
    }

    protected static boolean hasVideoConfigServerExtras(Map<String, String> map) {
        return map != null && (map.containsKey("AppnextProgressType") || map.containsKey("AppnextProgressColor") || map.containsKey("AppnextVideoLength") || map.containsKey("AppnextShowClose") || map.containsKey("AppnextCloseDelay"));
    }

    protected static void setConfigFromExtras(Configuration configuration, Map<String, String> map) {
        if (configuration != null && map != null) {
            if (map.containsKey("AppnextCategories")) {
                try {
                    configuration.setCategories(map.get("AppnextCategories"));
                } catch (Throwable th) {
                    new StringBuilder("setCategories: ").append(th.getMessage());
                }
            }
            if (map.containsKey("AppnextPostback")) {
                try {
                    configuration.setPostback(map.get("AppnextPostback"));
                } catch (Throwable th2) {
                    new StringBuilder("setPostback: ").append(th2.getMessage());
                }
            }
            if (map.containsKey("AppnextOrientation")) {
                try {
                    configuration.setOrientation(map.get("AppnextOrientation"));
                } catch (Throwable th3) {
                    new StringBuilder("setOrientation: ").append(th3.getMessage());
                }
            }
            if (map.containsKey("AppnextMinVideoLen")) {
                try {
                    configuration.setMinVideoLength(Integer.parseInt(map.get("AppnextMinVideoLen")));
                } catch (Throwable th4) {
                    new StringBuilder("setMinVideoLength: ").append(th4.getMessage());
                }
            }
            if (map.containsKey("AppnextMaxVideoLen")) {
                try {
                    configuration.setMaxVideoLength(Integer.parseInt(map.get("AppnextMaxVideoLen")));
                } catch (Throwable th5) {
                    new StringBuilder("setMaxVideoLength: ").append(th5.getMessage());
                }
            }
            if (map.containsKey("AppnextMute")) {
                try {
                    configuration.setMute(Boolean.parseBoolean(map.get("AppnextMute")));
                } catch (Throwable th6) {
                    new StringBuilder("setMute: ").append(th6.getMessage());
                }
            }
            if (map.containsKey("AppnextLanguage")) {
                try {
                    configuration.setLanguage(map.get("AppnextLanguage"));
                } catch (Throwable th7) {
                    new StringBuilder("setLanguage: ").append(th7.getMessage());
                }
            }
        }
    }

    protected static void setVideoConfigFromExtras(VideoConfig videoConfig, Map<String, String> map) {
        if (videoConfig != null && map != null && map.containsKey("AppnextVideoLength")) {
            try {
                videoConfig.setVideoLength(map.get("AppnextVideoLength"));
            } catch (Throwable th) {
                new StringBuilder("setVideoLength: ").append(th.getMessage());
            }
        }
    }

    protected static void setRewardedVideoConfigFromExtras(RewardedConfig rewardedConfig, Map<String, String> map) {
        if (rewardedConfig != null && map != null) {
            if (map.containsKey("AppnextVideoMode")) {
                try {
                    rewardedConfig.setMode(map.get("AppnextVideoMode"));
                    if (rewardedConfig.getMode().equals("multi") && map.containsKey("AppnextMultiTimerLength")) {
                        rewardedConfig.setMultiTimerLength(Integer.parseInt(map.get("AppnextMultiTimerLength")));
                    }
                } catch (Throwable th) {
                    new StringBuilder("setVideoMode: ").append(th.getMessage());
                }
            }
            if (map.containsKey("AppnextRollCapTime")) {
                try {
                    rewardedConfig.setRollCaptionTime(Integer.parseInt(map.get("AppnextRollCapTime")));
                } catch (Throwable th2) {
                    new StringBuilder("setRollCaptionTime: ").append(th2.getMessage());
                }
            }
            if (map.containsKey("AppnextShowCta")) {
                try {
                    rewardedConfig.setShowCta(Boolean.parseBoolean(map.get("AppnextShowCta")));
                } catch (Throwable th3) {
                    new StringBuilder("setShowCta: ").append(th3.getMessage());
                }
            }
        }
    }

    protected static void setFullscreenConfigFromExtras(FullscreenConfig fullscreenConfig, Map<String, String> map) {
        if (fullscreenConfig != null && map != null && map.containsKey("AppnextBackButtonCanClose")) {
            try {
                fullscreenConfig.setBackButtonCanClose(Boolean.parseBoolean(map.get("AppnextBackButtonCanClose")));
            } catch (Throwable th) {
                new StringBuilder("setBackButtonCanClose: ").append(th.getMessage());
            }
        }
    }
}
