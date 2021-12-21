package com.startapp.android.mediation.admob;

import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.startapp.android.publish.adsCommon.StartAppAd;

public class StartAppInterstitialExtras extends StartAppExtras {
    private StartAppAd.AdMode adMode;

    private StartAppInterstitialExtras() {
    }

    public StartAppAd.AdMode getAdMode() {
        return this.adMode;
    }

    public StartAppExtras setAdMode(StartAppAd.AdMode adMode2) {
        this.adMode = adMode2;
        return this;
    }

    public static class Builder {
        private StartAppInterstitialExtras extras = new StartAppInterstitialExtras();

        public Builder setAdMode(StartAppAd.AdMode adMode) {
            this.extras.setAdMode(adMode);
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
