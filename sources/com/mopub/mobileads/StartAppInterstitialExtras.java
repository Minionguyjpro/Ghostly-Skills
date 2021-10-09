package com.mopub.mobileads;

import com.startapp.android.publish.adsCommon.StartAppAd;
import java.util.Map;

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
