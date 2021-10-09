package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.view.View;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerListener;
import com.startapp.android.publish.ads.nativead.NativeAdPreferences;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.Map;

public class StartAppCustomEventUtils {
    public static final boolean DEFAULT_CONTENT_AD = false;
    public static final int DEFAULT_PRIMARY_IMAGE_SIZE = 4;
    public static final int DEFAULT_SECONDARY_IMAGE_SIZE = 2;
    public static final String LOCATION_KEY = "location";
    public static final String SERVER_EXTRA_ACCOUNT_ID = "accountID";
    public static final String SERVER_EXTRA_APP_ID = "appID";
    public static final String SERVER_EXTRA_CONTENT_AD = "contentAd";
    public static final String SERVER_EXTRA_NATIVE_PRIMARY_IMAGE = "primaryImageSize";
    public static final String SERVER_EXTRA_NATIVE_SECONDARY_IMAGE = "secondaryImageSize";

    public static NativeAdPreferences extractNativeAdPrefs(Context context, Map<String, Object> map, Map<String, String> map2) {
        boolean z;
        NativeAdPreferences nativeAdPreferences = new NativeAdPreferences();
        extractAdPrefs(map, nativeAdPreferences);
        nativeAdPreferences.setAdsNumber(1);
        int i = 4;
        if (validateExtras(map2, SERVER_EXTRA_NATIVE_PRIMARY_IMAGE)) {
            try {
                i = Integer.parseInt(map2.get(SERVER_EXTRA_NATIVE_PRIMARY_IMAGE));
            } catch (NumberFormatException unused) {
            }
        }
        int i2 = 2;
        if (validateExtras(map2, SERVER_EXTRA_NATIVE_SECONDARY_IMAGE)) {
            try {
                i2 = Integer.parseInt(map2.get(SERVER_EXTRA_NATIVE_SECONDARY_IMAGE));
            } catch (NumberFormatException unused2) {
            }
        }
        if (validateExtras(map2, SERVER_EXTRA_CONTENT_AD)) {
            try {
                z = Boolean.parseBoolean(map2.get(SERVER_EXTRA_CONTENT_AD));
            } catch (NumberFormatException unused3) {
            }
            nativeAdPreferences.setContentAd(z);
            nativeAdPreferences.setPrimaryImageSize(i);
            nativeAdPreferences.setSecondaryImageSize(i2);
            nativeAdPreferences.setAutoBitmapDownload(false);
            return nativeAdPreferences;
        }
        z = false;
        nativeAdPreferences.setContentAd(z);
        nativeAdPreferences.setPrimaryImageSize(i);
        nativeAdPreferences.setSecondaryImageSize(i2);
        nativeAdPreferences.setAutoBitmapDownload(false);
        return nativeAdPreferences;
    }

    private static boolean validateExtras(Map<String, String> map, String str) {
        return (map == null || !map.containsKey(str) || map.get(str) == null) ? false : true;
    }

    public static AdPreferences extractAdPrefs(Context context, Map<String, Object> map, Map<String, String> map2) {
        AdPreferences adPreferences = new AdPreferences();
        extractAdPrefs(map, adPreferences);
        return adPreferences;
    }

    private static void extractAdPrefs(Map<String, Object> map, AdPreferences adPreferences) {
        if (map != null && map.get(StartAppExtras.STARTAPP_EXTRAS_KEY) != null) {
            StartAppExtras startAppExtras = (StartAppExtras) map.get(StartAppExtras.STARTAPP_EXTRAS_KEY);
            setAge(adPreferences, startAppExtras);
            setKeywords(adPreferences, startAppExtras);
            setLocation(adPreferences, (Location) map.get("location"));
        }
    }

    public static View Banner3Dcreate(Context context, AdPreferences adPreferences, BannerListener bannerListener) {
        try {
            Class.forName("com.startapp.android.publish.banner.banner3d");
            return (View) Class.forName("com.startapp.android.publish.banner.banner3d.Banner3D").getConstructor(new Class[]{Context.class, AdPreferences.class, BannerListener.class}).newInstance(new Object[]{context, adPreferences, bannerListener});
        } catch (Exception unused) {
            return new Banner(context, adPreferences, bannerListener);
        }
    }

    private static void setKeywords(AdPreferences adPreferences, StartAppExtras startAppExtras) {
        if (startAppExtras.getKeywords() != null) {
            adPreferences.setKeywords(startAppExtras.getKeywords());
        }
    }

    private static void setLocation(AdPreferences adPreferences, Location location) {
        if (location != null) {
            adPreferences.setLongitude(location.getLongitude());
            adPreferences.setLatitude(location.getLatitude());
        }
    }

    private static void setAge(AdPreferences adPreferences, StartAppExtras startAppExtras) {
        if (startAppExtras.getAge() != null) {
            adPreferences.setAge(startAppExtras.getAge());
        }
    }

    public static String getStringFromExtras(String str, Map<String, String> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        return null;
    }

    public static void checkInit(Context context, Map<String, String> map) {
        if (map.get(SERVER_EXTRA_APP_ID) != null && map.get(SERVER_EXTRA_ACCOUNT_ID) != null) {
            StartAppSDK.init((Activity) context, getStringFromExtras(SERVER_EXTRA_APP_ID, map));
        }
    }
}
