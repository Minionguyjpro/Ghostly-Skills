package com.startapp.android.publish.adsCommon;

import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class AdsConstants {
    public static final int AD_INFORMATION_EXTENDED_ID = 1475346434;
    public static final int AD_INFORMATION_ID = 1475346433;
    public static final Boolean FORCE_NATIVE_VIDEO_PLAYER = false;
    public static final int LIST_3D_CLOSE_BUTTON_ID = 1475346435;
    public static final String OVERRIDE_HOST = null;
    public static final Boolean OVERRIDE_NETWORK = false;
    public static final int SPLASH_NATIVE_MAIN_LAYOUT_ID = 1475346437;
    public static final int STARTAPP_AD_MAIN_LAYOUT_ID = 1475346432;
    public static final Boolean VIDEO_DEBUG = false;

    /* renamed from: a  reason: collision with root package name */
    public static final String f166a = "get";
    public static final String b;
    public static final String c;
    public static final String d = "trackdownload";
    public static final String e;
    public static final String f = "https://imp.startappservice.com/tracking/adImpression";
    public static final Boolean g = false;
    public static final String h = i.b();
    public static final String i = i.c();
    public static final String j = i.d();
    public static final String[] k = {"back_", "back_dark", "browser_icon_dark", "forward_", "forward_dark", "x_dark"};
    public static final String[] l = {"empty_star", "filled_star", "half_star"};

    /* compiled from: StartAppSDK */
    public enum AdApiType {
        HTML,
        JSON
    }

    /* compiled from: StartAppSDK */
    public enum ServiceApiType {
        METADATA,
        DOWNLOAD
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(f166a);
        sb.append("ads");
        b = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(f166a);
        sb2.append("htmlad");
        c = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(f166a);
        sb3.append("adsmetadata");
        e = sb3.toString();
    }

    public static String a(ServiceApiType serviceApiType) {
        String str;
        String str2;
        String str3;
        int i2 = AnonymousClass1.f167a[serviceApiType.ordinal()];
        String str4 = null;
        if (i2 == 1) {
            str3 = e;
            str2 = MetaData.getInstance().getMetaDataHost();
        } else if (i2 != 2) {
            str = null;
            return str4 + str;
        } else {
            str3 = d;
            str2 = MetaData.getInstance().getAdPlatformHost();
        }
        String str5 = str3;
        str4 = str2;
        str = str5;
        return str4 + str;
    }

    /* renamed from: com.startapp.android.publish.adsCommon.AdsConstants$1  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f167a;
        static final /* synthetic */ int[] b;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        static {
            /*
                com.startapp.android.publish.adsCommon.AdsConstants$AdApiType[] r0 = com.startapp.android.publish.adsCommon.AdsConstants.AdApiType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r1 = 1
                com.startapp.android.publish.adsCommon.AdsConstants$AdApiType r2 = com.startapp.android.publish.adsCommon.AdsConstants.AdApiType.HTML     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.adsCommon.AdsConstants$AdApiType r3 = com.startapp.android.publish.adsCommon.AdsConstants.AdApiType.JSON     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.startapp.android.publish.adsCommon.AdsConstants$ServiceApiType[] r2 = com.startapp.android.publish.adsCommon.AdsConstants.ServiceApiType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                f167a = r2
                com.startapp.android.publish.adsCommon.AdsConstants$ServiceApiType r3 = com.startapp.android.publish.adsCommon.AdsConstants.ServiceApiType.METADATA     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = f167a     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.startapp.android.publish.adsCommon.AdsConstants$ServiceApiType r2 = com.startapp.android.publish.adsCommon.AdsConstants.ServiceApiType.DOWNLOAD     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.AdsConstants.AnonymousClass1.<clinit>():void");
        }
    }

    public static String a(AdApiType adApiType, AdPreferences.Placement placement) {
        String str;
        String str2;
        String str3;
        int i2 = AnonymousClass1.b[adApiType.ordinal()];
        String str4 = null;
        if (i2 == 1) {
            str3 = c;
            str2 = MetaData.getInstance().getAdPlatformHost(placement);
        } else if (i2 != 2) {
            str = null;
            return str4 + str;
        } else {
            str3 = b;
            str2 = MetaData.getInstance().getAdPlatformHost(placement);
        }
        String str5 = str3;
        str4 = str2;
        str = str5;
        return str4 + str;
    }

    public static Boolean a() {
        return VIDEO_DEBUG;
    }
}
