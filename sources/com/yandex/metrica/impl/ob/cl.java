package com.yandex.metrica.impl.ob;

import android.content.pm.FeatureInfo;

public abstract class cl {
    /* access modifiers changed from: protected */
    public abstract cm a(FeatureInfo featureInfo);

    public cm b(FeatureInfo featureInfo) {
        if (featureInfo.name != null) {
            return a(featureInfo);
        }
        if (featureInfo.reqGlEsVersion == 0) {
            return a(featureInfo);
        }
        return new cm("openGlFeature", featureInfo.reqGlEsVersion, c(featureInfo));
    }

    /* access modifiers changed from: package-private */
    public boolean c(FeatureInfo featureInfo) {
        return (featureInfo.flags & 1) != 0;
    }

    public static class a extends cl {
        public cm a(FeatureInfo featureInfo) {
            return new cm(featureInfo.name, featureInfo.version, c(featureInfo));
        }
    }

    public static class b extends cl {
        public cm a(FeatureInfo featureInfo) {
            return new cm(featureInfo.name, c(featureInfo));
        }
    }
}
