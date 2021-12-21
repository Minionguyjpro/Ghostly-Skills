package com.mopub.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.MoPubIdentifier;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import java.util.Locale;

public class ClientMetadata {
    private static final String DEVICE_ORIENTATION_LANDSCAPE = "l";
    private static final String DEVICE_ORIENTATION_PORTRAIT = "p";
    private static final String DEVICE_ORIENTATION_SQUARE = "s";
    private static final String DEVICE_ORIENTATION_UNKNOWN = "u";
    private static volatile ClientMetadata sInstance;
    private String mAppName;
    private final String mAppPackageName;
    private final String mAppVersion = getAppVersionFromContext(this.mContext);
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private final String mDeviceManufacturer = Build.MANUFACTURER;
    private final String mDeviceModel = Build.MODEL;
    private final String mDeviceOsVersion = Build.VERSION.RELEASE;
    private final String mDeviceProduct = Build.PRODUCT;
    private String mIsoCountryCode;
    private String mNetworkOperator;
    private String mNetworkOperatorForUrl;
    private String mNetworkOperatorName;
    private final String mSdkVersion = "5.12.0";
    private String mSimIsoCountryCode;
    private String mSimOperator;
    private String mSimOperatorName;
    private final MoPubIdentifier moPubIdentifier;

    public enum MoPubNetworkType {
        UNKNOWN(0),
        ETHERNET(1),
        WIFI(2),
        MOBILE(3),
        GG(4),
        GGG(5),
        GGGG(6);
        
        private final int mId;

        private MoPubNetworkType(int i) {
            this.mId = i;
        }

        public String toString() {
            return Integer.toString(this.mId);
        }

        public int getId() {
            return this.mId;
        }
    }

    public static ClientMetadata getInstance(Context context) {
        ClientMetadata clientMetadata = sInstance;
        if (clientMetadata == null) {
            synchronized (ClientMetadata.class) {
                clientMetadata = sInstance;
                if (clientMetadata == null) {
                    clientMetadata = new ClientMetadata(context);
                    sInstance = clientMetadata;
                }
            }
        }
        return clientMetadata;
    }

    public static ClientMetadata getInstance() {
        ClientMetadata clientMetadata = sInstance;
        if (clientMetadata == null) {
            synchronized (ClientMetadata.class) {
                clientMetadata = sInstance;
            }
        }
        return clientMetadata;
    }

    private ClientMetadata(Context context) {
        ApplicationInfo applicationInfo;
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mConnectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        PackageManager packageManager = this.mContext.getPackageManager();
        String packageName = this.mContext.getPackageName();
        this.mAppPackageName = packageName;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            this.mAppName = (String) packageManager.getApplicationLabel(applicationInfo);
        }
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        if (telephonyManager != null) {
            this.mNetworkOperatorForUrl = telephonyManager.getNetworkOperator();
            this.mNetworkOperator = telephonyManager.getNetworkOperator();
            if (telephonyManager.getPhoneType() == 2 && telephonyManager.getSimState() == 5) {
                this.mNetworkOperatorForUrl = telephonyManager.getSimOperator();
                this.mSimOperator = telephonyManager.getSimOperator();
            }
            if (MoPub.canCollectPersonalInformation()) {
                this.mIsoCountryCode = telephonyManager.getNetworkCountryIso();
                this.mSimIsoCountryCode = telephonyManager.getSimCountryIso();
            } else {
                this.mIsoCountryCode = "";
                this.mSimIsoCountryCode = "";
            }
            try {
                this.mNetworkOperatorName = telephonyManager.getNetworkOperatorName();
                if (telephonyManager.getSimState() == 5) {
                    this.mSimOperatorName = telephonyManager.getSimOperatorName();
                }
            } catch (SecurityException unused2) {
                this.mNetworkOperatorName = null;
                this.mSimOperatorName = null;
            }
        }
        this.moPubIdentifier = new MoPubIdentifier(this.mContext);
    }

    public void repopulateCountryData() {
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        if (MoPub.canCollectPersonalInformation() && telephonyManager != null) {
            this.mIsoCountryCode = telephonyManager.getNetworkCountryIso();
            this.mSimIsoCountryCode = telephonyManager.getSimCountryIso();
        }
    }

    private static String getAppVersionFromContext(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Failed to retrieve PackageInfo#versionName.");
            return null;
        }
    }

    public String getOrientationString() {
        int i = this.mContext.getResources().getConfiguration().orientation;
        if (i == 1) {
            return DEVICE_ORIENTATION_PORTRAIT;
        }
        if (i == 2) {
            return DEVICE_ORIENTATION_LANDSCAPE;
        }
        return i == 3 ? DEVICE_ORIENTATION_SQUARE : DEVICE_ORIENTATION_UNKNOWN;
    }

    public MoPubNetworkType getActiveNetworkType() {
        if (!DeviceUtils.isPermissionGranted(this.mContext, "android.permission.ACCESS_NETWORK_STATE")) {
            return MoPubNetworkType.UNKNOWN;
        }
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return MoPubNetworkType.UNKNOWN;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            for (Network networkCapabilities : this.mConnectivityManager.getAllNetworks()) {
                NetworkCapabilities networkCapabilities2 = this.mConnectivityManager.getNetworkCapabilities(networkCapabilities);
                if (networkCapabilities2 != null && networkCapabilities2.hasTransport(3)) {
                    return MoPubNetworkType.ETHERNET;
                }
            }
        } else if (activeNetworkInfo.getType() == 9) {
            return MoPubNetworkType.ETHERNET;
        }
        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(1);
        if (networkInfo != null && networkInfo.isConnected()) {
            return MoPubNetworkType.WIFI;
        }
        NetworkInfo networkInfo2 = this.mConnectivityManager.getNetworkInfo(0);
        if (networkInfo2 == null || !networkInfo2.isConnected()) {
            return MoPubNetworkType.UNKNOWN;
        }
        switch (networkInfo2.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return MoPubNetworkType.GG;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
                return MoPubNetworkType.GGG;
            case 13:
            case 15:
                return MoPubNetworkType.GGGG;
            default:
                return MoPubNetworkType.MOBILE;
        }
    }

    public float getDensity() {
        return this.mContext.getResources().getDisplayMetrics().density;
    }

    public String getNetworkOperatorForUrl() {
        return this.mNetworkOperatorForUrl;
    }

    public String getNetworkOperator() {
        return this.mNetworkOperator;
    }

    public Locale getDeviceLocale() {
        return this.mContext.getResources().getConfiguration().locale;
    }

    public String getSimOperator() {
        return this.mSimOperator;
    }

    public String getIsoCountryCode() {
        return MoPub.canCollectPersonalInformation() ? this.mIsoCountryCode : "";
    }

    public String getSimIsoCountryCode() {
        return MoPub.canCollectPersonalInformation() ? this.mSimIsoCountryCode : "";
    }

    public String getNetworkOperatorName() {
        return this.mNetworkOperatorName;
    }

    public String getSimOperatorName() {
        return this.mSimOperatorName;
    }

    public MoPubIdentifier getMoPubIdentifier() {
        return this.moPubIdentifier;
    }

    public String getDeviceManufacturer() {
        return this.mDeviceManufacturer;
    }

    public String getDeviceModel() {
        return this.mDeviceModel;
    }

    public String getDeviceProduct() {
        return this.mDeviceProduct;
    }

    public String getDeviceOsVersion() {
        return this.mDeviceOsVersion;
    }

    public int getDeviceScreenWidthDip() {
        return Dips.screenWidthAsIntDips(this.mContext);
    }

    public int getDeviceScreenHeightDip() {
        return Dips.screenHeightAsIntDips(this.mContext);
    }

    public Point getDeviceDimensions() {
        if (Preconditions.NoThrow.checkNotNull(this.mContext)) {
            return DeviceUtils.getDeviceDimensions(this.mContext);
        }
        return new Point(0, 0);
    }

    public String getSdkVersion() {
        return this.mSdkVersion;
    }

    public String getAppVersion() {
        return this.mAppVersion;
    }

    public String getAppPackageName() {
        return this.mAppPackageName;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public static String getCurrentLanguage(Context context) {
        String trim = Locale.getDefault().getLanguage().trim();
        Locale locale = context.getResources().getConfiguration().locale;
        return (locale == null || locale.getLanguage().trim().isEmpty()) ? trim : locale.getLanguage().trim();
    }

    @Deprecated
    public static void setInstance(ClientMetadata clientMetadata) {
        synchronized (ClientMetadata.class) {
            sInstance = clientMetadata;
        }
    }

    @Deprecated
    public static void clearForTesting() {
        sInstance = null;
    }
}
