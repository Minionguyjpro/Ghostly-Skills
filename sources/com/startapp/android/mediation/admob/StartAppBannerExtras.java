package com.startapp.android.mediation.admob;

import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;

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

        public NetworkExtras build(String str) {
            CustomEventExtras customEventExtras = new CustomEventExtras();
            customEventExtras.setExtra(str, this.extras);
            return customEventExtras;
        }
    }
}
