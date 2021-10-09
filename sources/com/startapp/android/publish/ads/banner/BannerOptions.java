package com.startapp.android.publish.ads.banner;

import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class BannerOptions implements Serializable {
    private static final long serialVersionUID = 1;
    private int adsNumber = 4;
    private int delayFaceTime = 5000;
    @f(b = Effect.class)
    private Effect effect = Effect.ROTATE_3D;
    private int height = 50;
    private float heightRatio = 1.0f;
    private int htmlAdsNumber = 10;
    private float minScale = 0.88f;
    private int minViewabilityPercentage = 50;
    private int refreshRate = 60000;
    private int refreshRate3D = 60000;
    private boolean rotateThroughOnStart = true;
    private int rotateThroughSpeedMult = 2;
    private int scalePower = 4;
    private int stepSize = 5;
    private int timeBetweenFrames = 25;
    private int width = 300;
    private float widthRatio = 1.0f;

    /* compiled from: StartAppSDK */
    public enum Effect {
        ROTATE_3D(1),
        EXCHANGE(2),
        FLY_IN(3);
        
        private int index;

        private Effect(int i) {
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        public int getRotationMultiplier() {
            return (int) Math.pow(2.0d, (double) (this.index - 1));
        }

        public static Effect getByIndex(int i) {
            Effect effect = ROTATE_3D;
            Effect[] values = values();
            for (int i2 = 0; i2 < values.length; i2++) {
                if (values[i2].getIndex() == i) {
                    effect = values[i2];
                }
            }
            return effect;
        }

        public static Effect getByName(String str) {
            Effect effect = ROTATE_3D;
            Effect[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    effect = values[i];
                }
            }
            return effect;
        }
    }

    public BannerOptions() {
    }

    public BannerOptions(BannerOptions bannerOptions) {
        this.width = bannerOptions.width;
        this.height = bannerOptions.height;
        this.timeBetweenFrames = bannerOptions.timeBetweenFrames;
        this.stepSize = bannerOptions.stepSize;
        this.delayFaceTime = bannerOptions.delayFaceTime;
        this.adsNumber = bannerOptions.adsNumber;
        this.htmlAdsNumber = bannerOptions.htmlAdsNumber;
        this.refreshRate3D = bannerOptions.refreshRate3D;
        this.widthRatio = bannerOptions.widthRatio;
        this.heightRatio = bannerOptions.heightRatio;
        this.minScale = bannerOptions.minScale;
        this.scalePower = bannerOptions.scalePower;
        this.effect = bannerOptions.effect;
        this.rotateThroughOnStart = bannerOptions.rotateThroughOnStart;
        this.rotateThroughSpeedMult = bannerOptions.rotateThroughSpeedMult;
        this.refreshRate = bannerOptions.refreshRate;
    }

    public void a(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public int a() {
        return this.timeBetweenFrames;
    }

    public int b() {
        return this.stepSize;
    }

    public int c() {
        return this.delayFaceTime;
    }

    public int d() {
        return this.width;
    }

    public int e() {
        return this.height;
    }

    public int f() {
        return this.adsNumber;
    }

    public int g() {
        return this.htmlAdsNumber;
    }

    public int h() {
        return this.refreshRate3D;
    }

    public int i() {
        return this.refreshRate;
    }

    public float j() {
        return this.widthRatio;
    }

    public float k() {
        return this.heightRatio;
    }

    public float l() {
        return this.minScale;
    }

    public int m() {
        return this.scalePower;
    }

    public Effect n() {
        return this.effect;
    }

    public boolean o() {
        return this.rotateThroughOnStart;
    }

    public int p() {
        return this.rotateThroughSpeedMult;
    }

    public int q() {
        return this.minViewabilityPercentage;
    }

    public boolean equals(Object obj) {
        BannerOptions bannerOptions = (BannerOptions) obj;
        return bannerOptions.f() == f() && bannerOptions.g() == g() && bannerOptions.h() == h() && bannerOptions.c() == c() && bannerOptions.e() == e() && bannerOptions.b() == b() && bannerOptions.a() == a() && bannerOptions.d() == d() && bannerOptions.i() == i();
    }
}
