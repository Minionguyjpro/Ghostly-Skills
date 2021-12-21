package com.mopub.mobileads;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.mobileads.CustomEventBanner;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerListener;
import com.startapp.android.publish.ads.banner.bannerstandard.BannerStandard;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.Map;

public class StartAppCustomEventBanner extends CustomEventBanner {
    public static final String AD_HEIGHT_KEY = "adHeight";
    public static final String AD_WIDTH_KEY = "adWidth";
    public static final String BANNER_MODE_KEY = "bannerMode";

    /* access modifiers changed from: protected */
    public int getDefaultHeight() {
        return 50;
    }

    /* access modifiers changed from: protected */
    public int getDefaultWidth() {
        return 320;
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
    }

    /* access modifiers changed from: protected */
    public void loadBanner(Context context, final CustomEventBanner.CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        int i;
        int i2;
        StartAppCustomEventUtils.checkInit(context, map2);
        AdPreferences extractAdPrefs = StartAppCustomEventUtils.extractAdPrefs(context, map, map2);
        AnonymousClass1 r5 = new BannerListener() {
            public void onReceiveAd(View view) {
                customEventBannerListener.onBannerLoaded(view);
            }

            public void onFailedToReceiveAd(View view) {
                customEventBannerListener.onBannerFailed(MoPubErrorCode.UNSPECIFIED);
            }

            public void onClick(View view) {
                customEventBannerListener.onBannerClicked();
                customEventBannerListener.onLeaveApplication();
            }
        };
        if (extrasAreValid(map2)) {
            i = Integer.parseInt(map2.get("adHeight"));
            i2 = Integer.parseInt(map2.get("adWidth"));
        } else {
            i = getDefaultHeight();
            i2 = getDefaultWidth();
        }
        getBanner(map, map2, context, extractAdPrefs, r5).setLayoutParams(new ViewGroup.LayoutParams(Math.round(TypedValue.applyDimension(1, (float) i2, context.getResources().getDisplayMetrics())), Math.round(TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics()))));
    }

    private boolean extrasAreValid(Map<String, String> map) {
        if (map == null || !map.containsKey("adHeight") || !map.containsKey("adWidth")) {
            return false;
        }
        try {
            Integer.parseInt(map.get("adHeight"));
            Integer.parseInt(map.get("adWidth"));
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public View getBanner(Map<String, Object> map, Map<String, String> map2, Context context, AdPreferences adPreferences, BannerListener bannerListener) {
        if (!(map2 == null || map2.get(BANNER_MODE_KEY) == null)) {
            if (map2.get(BANNER_MODE_KEY).equalsIgnoreCase("BannerMode.STANDARD")) {
                return new BannerStandard(context, adPreferences, bannerListener);
            }
            if (map2.get(BANNER_MODE_KEY).equalsIgnoreCase("BannerMode.THREED")) {
                return StartAppCustomEventUtils.Banner3Dcreate(context, adPreferences, bannerListener);
            }
            if (map2.get(BANNER_MODE_KEY).equalsIgnoreCase("BannerMode.AUTO")) {
                return new Banner(context, adPreferences, bannerListener);
            }
        }
        if (map.get(StartAppExtras.STARTAPP_EXTRAS_KEY) != null) {
            StartAppBannerExtras startAppBannerExtras = (StartAppBannerExtras) map.get(StartAppExtras.STARTAPP_EXTRAS_KEY);
            if (startAppBannerExtras.getBannerMode() != null) {
                int i = AnonymousClass2.$SwitchMap$com$mopub$mobileads$StartAppBannerExtras$BannerMode[startAppBannerExtras.getBannerMode().ordinal()];
                if (i == 1) {
                    return new Banner(context, adPreferences, bannerListener);
                }
                if (i == 2) {
                    return new BannerStandard(context, adPreferences, bannerListener);
                }
                if (i == 3) {
                    return StartAppCustomEventUtils.Banner3Dcreate(context, adPreferences, bannerListener);
                }
            }
        }
        return new Banner(context, adPreferences, bannerListener);
    }

    /* renamed from: com.mopub.mobileads.StartAppCustomEventBanner$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$StartAppBannerExtras$BannerMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.mopub.mobileads.StartAppBannerExtras$BannerMode[] r0 = com.mopub.mobileads.StartAppBannerExtras.BannerMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$mobileads$StartAppBannerExtras$BannerMode = r0
                com.mopub.mobileads.StartAppBannerExtras$BannerMode r1 = com.mopub.mobileads.StartAppBannerExtras.BannerMode.AUTO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$mobileads$StartAppBannerExtras$BannerMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.mobileads.StartAppBannerExtras$BannerMode r1 = com.mopub.mobileads.StartAppBannerExtras.BannerMode.STANDARD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$mobileads$StartAppBannerExtras$BannerMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.mobileads.StartAppBannerExtras$BannerMode r1 = com.mopub.mobileads.StartAppBannerExtras.BannerMode.THREED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.StartAppCustomEventBanner.AnonymousClass2.<clinit>():void");
        }
    }
}
