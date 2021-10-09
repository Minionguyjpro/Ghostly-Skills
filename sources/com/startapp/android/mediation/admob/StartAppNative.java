package com.startapp.android.mediation.admob;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventNative;
import com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener;
import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import com.startapp.android.publish.ads.nativead.NativeAdPreferences;
import com.startapp.android.publish.ads.nativead.StartAppNativeAd;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.SDKAdPreferences;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import java.util.ArrayList;

public class StartAppNative implements CustomEventNative {
    public static final String EXTRAS_CATEGORY = "category";
    public static final String EXTRAS_INSTALLS = "installs";

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void requestNativeAd(final Context context, final CustomEventNativeListener customEventNativeListener, String str, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle) {
        if (nativeMediationAdRequest.isDesignedForFamilies()) {
            customEventNativeListener.onAdFailedToLoad(3);
            return;
        }
        final StartAppNativeAd startAppNativeAd = new StartAppNativeAd(context);
        startAppNativeAd.loadAd(createNativeAdPreferences(nativeMediationAdRequest), new AdEventListener() {
            public void onReceiveAd(Ad ad) {
                ArrayList<NativeAdDetails> nativeAds = startAppNativeAd.getNativeAds();
                if (nativeAds == null || nativeAds.size() <= 0) {
                    customEventNativeListener.onAdFailedToLoad(3);
                } else {
                    customEventNativeListener.onAdLoaded(StartAppNativeAdMapperBuilder.buildMapper(context, nativeAds.get(0)));
                }
            }

            public void onFailedToReceiveAd(Ad ad) {
                customEventNativeListener.onAdFailedToLoad(0);
            }
        });
    }

    private static NativeAdPreferences createNativeAdPreferences(NativeMediationAdRequest nativeMediationAdRequest) {
        NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        boolean z = nativeAdOptions != null && !nativeAdOptions.shouldReturnUrlsForImageAssets();
        NativeAdPreferences nativeAdPreferences = new NativeAdPreferences();
        nativeAdPreferences.setAutoBitmapDownload(z);
        if (nativeMediationAdRequest.getGender() == 1) {
            nativeAdPreferences.setGender(SDKAdPreferences.Gender.MALE);
        } else if (nativeMediationAdRequest.getGender() == 2) {
            nativeAdPreferences.setGender(SDKAdPreferences.Gender.FEMALE);
        }
        if (nativeMediationAdRequest.getBirthday() != null) {
            nativeAdPreferences.setAge(Integer.valueOf(Utils.getDiffInYears(System.currentTimeMillis(), nativeMediationAdRequest.getBirthday().getTime())));
        }
        if (nativeMediationAdRequest.getKeywords() != null) {
            StringBuilder sb = new StringBuilder();
            for (String append : nativeMediationAdRequest.getKeywords()) {
                sb.append(append);
                sb.append(',');
            }
            nativeAdPreferences.setKeywords(sb.substring(0, sb.length() - 1));
        }
        if (nativeMediationAdRequest.getLocation() != null) {
            nativeAdPreferences.setLongitude(nativeMediationAdRequest.getLocation().getLongitude());
            nativeAdPreferences.setLatitude(nativeMediationAdRequest.getLocation().getLatitude());
        }
        return nativeAdPreferences;
    }
}
