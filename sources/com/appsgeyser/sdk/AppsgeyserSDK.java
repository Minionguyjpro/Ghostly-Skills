package com.appsgeyser.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;

public final class AppsgeyserSDK {

    public interface OfferWallEnabledListener {
        void isOfferWallEnabled(boolean z);
    }

    public interface OnAboutDialogEnableListener {
        void onDialogEnableReceived(boolean z);
    }

    public interface OnRateMyAppEnableListener {
        void onRateMyAppEnableReceived(boolean z);
    }

    public static void takeOff(Activity activity, String str, String str2, String str3) {
        InternalEntryPoint.getInstance().takeOff(activity, str, str2, str3);
    }

    public static FastTrackAdsController getFastTrackAdsController() {
        return InternalEntryPoint.getInstance().getFastTrackAdsController();
    }

    public static void onPause(Context context) {
        InternalEntryPoint.getInstance().onPause(context);
    }

    public static void onResume(Context context) {
        InternalEntryPoint.getInstance().onResume(context);
    }

    public static void showAboutDialog(Activity activity) {
        InternalEntryPoint.getInstance().showAboutDialog(activity);
    }

    public static void isAboutDialogEnabled(Context context, OnAboutDialogEnableListener onAboutDialogEnableListener) {
        InternalEntryPoint.getInstance().getNewConfigPhp(context, onAboutDialogEnableListener);
    }

    public static void isOfferWallEnabled(Context context, OfferWallEnabledListener offerWallEnabledListener) {
        InternalEntryPoint.getInstance().checkIsOfferWallEnabled(context, offerWallEnabledListener);
    }

    public static void setApplicationInstance(Application application) {
        InternalEntryPoint.getInstance().setApplication(application);
    }

    public static String getAdditionalJsCode() {
        return InternalEntryPoint.getInstance().getAdditionalJsCode();
    }
}
