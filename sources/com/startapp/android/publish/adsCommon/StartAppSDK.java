package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import android.content.Context;
import com.startapp.android.publish.common.metaData.MetaDataRequest;

public class StartAppSDK {
    public static void init(Activity activity, String str) {
        init(activity, str, new SDKAdPreferences());
    }

    public static void init(Activity activity, String str, String str2) {
        init(activity, str, str2, new SDKAdPreferences());
    }

    public static void init(Activity activity, String str, SDKAdPreferences sDKAdPreferences) {
        m.a().a((Context) activity, (String) null, str, sDKAdPreferences, true);
    }

    public static void init(Activity activity, String str, String str2, SDKAdPreferences sDKAdPreferences) {
        m.a().a((Context) activity, str, str2, sDKAdPreferences, true);
    }

    public static void init(Activity activity, String str, boolean z) {
        init(activity, str, new SDKAdPreferences(), z);
    }

    public static void init(Activity activity, String str, String str2, boolean z) {
        init(activity, str, str2, new SDKAdPreferences(), z);
    }

    public static void init(Activity activity, String str, SDKAdPreferences sDKAdPreferences, boolean z) {
        m.a().a((Context) activity, (String) null, str, sDKAdPreferences, z);
    }

    public static void init(Activity activity, String str, String str2, SDKAdPreferences sDKAdPreferences, boolean z) {
        m.a().a((Context) activity, str, str2, sDKAdPreferences, z);
    }

    @Deprecated
    public static void init(Context context, String str, String str2) {
        init(context, str, str2, new SDKAdPreferences());
    }

    @Deprecated
    public static void init(Context context, String str, String str2, SDKAdPreferences sDKAdPreferences) {
        m.a().a(context, str, str2, sDKAdPreferences, true);
    }

    @Deprecated
    public static void init(Context context, String str, boolean z) {
        init(context, (String) null, str, z);
    }

    @Deprecated
    public static void init(Context context, String str, String str2, boolean z) {
        init(context, str, str2, new SDKAdPreferences(), z);
    }

    @Deprecated
    public static void init(Context context, String str, String str2, SDKAdPreferences sDKAdPreferences, boolean z) {
        m.a().a(context, str, str2, sDKAdPreferences, z);
    }

    public static void inAppPurchaseMade(Context context) {
        inAppPurchaseMade(context, 0.0d);
    }

    public static void inAppPurchaseMade(Context context, double d) {
        k.b(context, "payingUser", (Boolean) true);
        double floatValue = (double) k.a(context, "inAppPurchaseAmount", Float.valueOf(0.0f)).floatValue();
        Double.isNaN(floatValue);
        k.b(context, "inAppPurchaseAmount", Float.valueOf((float) (floatValue + d)));
        m.a().a(context, MetaDataRequest.a.IN_APP_PURCHASE);
    }

    public static void startNewSession(Context context) {
        m.a().a(context, MetaDataRequest.a.CUSTOM);
    }

    public static void addWrapper(Context context, String str, String str2) {
        m.a().a(context, str, str2);
    }

    public static void enableReturnAds(boolean z) {
        m.a().e(z);
    }

    private static void pauseServices(Context context) {
        m.a().a(context);
        m.a().b(context);
    }

    private static void resumeServices(Context context) {
        m.a().c(context);
        m.a().d(context);
    }

    public static void setUserConsent(Context context, String str, long j, boolean z) {
        m.a().a(context, str, j, z, true);
    }
}
