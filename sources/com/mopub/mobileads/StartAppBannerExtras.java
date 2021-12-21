package com.mopub.mobileads;

import java.util.Map;

public class StartAppBannerExtras extends StartAppExtras {
    private BannerMode bannerMode;

    public enum BannerMode {
        AUTO,
        STANDARD,
        THREED
    }

    private StartAppBannerExtras() {
    }

    public BannerMode getBannerMode() {
        return this.bannerMode;
    }

    public StartAppExtras setBannerMode(BannerMode bannerMode2) {
        this.bannerMode = bannerMode2;
        return this;
    }

    public static class Builder {
        private StartAppBannerExtras extras = new StartAppBannerExtras();

        public Builder setBannerMode(BannerMode bannerMode) {
            this.extras.setBannerMode(bannerMode);
            return this;
        }

        public Builder setAdTag(String str) {
            this.extras.setAdTag(str);
            return this;
        }

        public Builder setKeywords(String str) {
            this.extras.setKeywords(str);
            return this;
        }

        public Builder setAge(Integer num) {
            this.extras.setAge(num);
            return this;
        }

        public Map<String, Object> build(Map<String, Object> map) {
            map.put(StartAppExtras.STARTAPP_EXTRAS_KEY, this.extras);
            return map;
        }
    }
}
