package com.startapp.android.publish.adsCommon;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.startapp.android.publish.adsCommon.Utils.NameValueSerializer;
import com.startapp.android.publish.adsCommon.Utils.d;
import com.startapp.android.publish.adsCommon.Utils.e;
import com.startapp.android.publish.adsCommon.Utils.g;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a.b;
import com.startapp.common.a.c;
import com.startapp.common.a.f;
import com.startapp.common.a.h;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* compiled from: StartAppSDK */
public abstract class BaseRequest implements NameValueSerializer {
    private static final String OS = "android";
    private String androidId;
    private int appCode;
    private Boolean appOnForeground;
    private String appVersion;
    private String blat;
    private String blon;
    private String bssid;
    private String cellSignalLevel;
    private String cellTimingAdv;
    private String cid;
    private String clientSessionId;
    private float density;
    private String deviceIP;
    private String deviceVersion;
    private Map<String, String> frameworksMap = new TreeMap();
    private int height;
    private Set<String> inputLangs;
    private String installerPkg;
    private String isp;
    private String ispName;
    private String lac;
    private String locale;
    private List<Location> locations = null;
    private String manufacturer;
    private String model;
    private String netOper;
    private String networkOperName;
    private String networkType;
    private String os = OS;
    private String packageId;
    private Map<String, String> parameters = new HashMap();
    private String personalizedAdsServing;
    private String productId;
    private String publisherId;
    protected Integer retry;
    private Boolean roaming;
    private boolean root;
    private long sdkFlavor = new BigInteger(AdsConstants.i, 2).longValue();
    private int sdkId = 3;
    private String sdkVersion = AdsConstants.h;
    private String signalLevel;
    private boolean simulator;
    private String ssid;
    private String subProductId;
    private String subPublisherId;
    private Boolean unknownSourcesAllowed;
    private boolean usbDebug;
    private b.c userAdvertisingId;
    private String wfScanRes;
    private int width;
    private String wifiRSSILevel;
    private String wifiSignalLevel;

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, String> map) {
        this.parameters = map;
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    public void setPublisherId(String str) {
        this.publisherId = str;
    }

    public long getSdkFlavor() {
        return this.sdkFlavor;
    }

    public void setSdkFlavor(long j) {
        this.sdkFlavor = j;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public void setPackageId(String str) {
        this.packageId = str;
    }

    public String getAndroidId() {
        return this.androidId;
    }

    private void setAndroidId(Context context) {
        if (c.a(context, "com.google.android.gms", 0)) {
            try {
                if (Integer.parseInt(Integer.toString(context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode).substring(0, 1)) >= 4) {
                    this.androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
                }
            } catch (PackageManager.NameNotFoundException | Exception unused) {
            }
        }
    }

    public String getInstallerPkg() {
        return this.installerPkg;
    }

    public void setInstallerPkg(String str) {
        this.installerPkg = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public String getSignalLevel() {
        return this.signalLevel;
    }

    private void setNetworkType(Context context) {
        this.networkType = h.a(context);
    }

    private void setNetworkLevels(Context context) {
        try {
            com.startapp.common.c a2 = com.startapp.common.c.a();
            if (a2 != null) {
                this.cellSignalLevel = a2.b();
                h.b bVar = null;
                if (MetaData.getInstance().isWfScanEnabled()) {
                    bVar = h.a(context, this.networkType);
                }
                if (bVar == null) {
                    this.signalLevel = this.cellSignalLevel;
                } else if (bVar.a() == null) {
                    this.signalLevel = bVar.c();
                    this.wifiRSSILevel = bVar.b();
                    this.wifiSignalLevel = bVar.c();
                } else {
                    this.signalLevel = bVar.a();
                    this.wifiRSSILevel = bVar.a();
                    this.wifiSignalLevel = bVar.a();
                }
            } else {
                this.signalLevel = "e106";
                this.cellSignalLevel = "e106";
                this.wifiSignalLevel = "e106";
                this.wifiRSSILevel = "e106";
            }
        } catch (Exception unused) {
        }
    }

    public String getCellSignalLevel() {
        return this.cellSignalLevel;
    }

    public String getWifiSignalLevel() {
        return this.wifiSignalLevel;
    }

    public String getWifiRssiLevel() {
        return this.wifiRSSILevel;
    }

    public b.c getUserAdvertisingId() {
        return this.userAdvertisingId;
    }

    public void setUserAdvertisingId(b.c cVar) {
        this.userAdvertisingId = cVar;
    }

    public String getIsp() {
        return this.isp;
    }

    public void setIsp(String str) {
        this.isp = str;
    }

    public String getIspName() {
        return this.ispName;
    }

    public void setIspName(String str) {
        this.ispName = str;
    }

    public String getNetOper() {
        return this.netOper;
    }

    public void setNetOper(String str) {
        this.netOper = str;
    }

    public String getNetworkOperName() {
        return this.networkOperName;
    }

    public void setNetworkOperName(String str) {
        this.networkOperName = str;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getLac() {
        return this.lac;
    }

    public void setLac(String str) {
        this.lac = str;
    }

    public String getBlat() {
        return this.blat;
    }

    public void setBlat(String str) {
        this.blat = str;
    }

    public String getBlon() {
        return this.blon;
    }

    public void setBlon(String str) {
        this.blon = str;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public String getBssid() {
        return this.bssid;
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public String getWfScanRes() {
        return this.wfScanRes;
    }

    public void setWfScanRes(String str) {
        this.wfScanRes = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setRetry(int i) {
        this.retry = null;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public String getSubPublisherId() {
        return this.subPublisherId;
    }

    public void setSubPublisherId(String str) {
        this.subPublisherId = str;
    }

    public String getSubProductId() {
        return this.subProductId;
    }

    public void setSubProductId(String str) {
        this.subProductId = str;
    }

    public String getOs() {
        return this.os;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public String getSessionId() {
        String str = this.clientSessionId;
        return str == null ? "" : str;
    }

    public void setSessionId(String str) {
        this.clientSessionId = str;
    }

    public Boolean isRoaming() {
        return this.roaming;
    }

    public void setRoaming(Context context) {
        this.roaming = h.c(context);
    }

    public String getDeviceIP() {
        return this.deviceIP;
    }

    public void setDeviceIP(WifiInfo wifiInfo) {
        this.deviceIP = h.a(wifiInfo);
    }

    public Boolean isUnknownSourcesAllowed() {
        return this.unknownSourcesAllowed;
    }

    public void setUnknownSourcesAllowed(Boolean bool) {
        this.unknownSourcesAllowed = bool;
    }

    public float getDensity() {
        return this.density;
    }

    public void setDensity(float f) {
        this.density = f;
    }

    public Boolean isAppOnForeground() {
        return this.appOnForeground;
    }

    public void setAppOnForeground(Context context) {
        try {
            this.appOnForeground = Boolean.valueOf(i.e(context));
        } catch (Exception unused) {
            this.appOnForeground = null;
        }
    }

    public Set<String> getInputLangs() {
        return this.inputLangs;
    }

    public void setInputLangs(Set<String> set) {
        this.inputLangs = set;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public int getAppCode() {
        return this.appCode;
    }

    public void setAppCode(int i) {
        this.appCode = i;
    }

    public List<Location> getLocations() {
        return this.locations;
    }

    public void setLocations(List<Location> list) {
        this.locations = list;
    }

    public String toString() {
        return "BaseRequest [parameters=" + this.parameters + "]";
    }

    public void fillApplicationDetails(Context context, AdPreferences adPreferences) {
        fillApplicationDetails(context, adPreferences, true);
    }

    public void fillApplicationDetails(Context context, AdPreferences adPreferences, boolean z) {
        setPublisherId(adPreferences.getPublisherId());
        setProductId(adPreferences.getProductId());
        this.frameworksMap = k.b(context, "sharedPrefsWrappers", this.frameworksMap);
        if (!AdsConstants.g.booleanValue()) {
            setUserAdvertisingId(b.a().a(context));
            if (this.userAdvertisingId == null) {
                setAndroidId(context);
            }
        }
        setPackageId(context.getPackageName());
        setInstallerPkg(i.d(context));
        setManufacturer(Build.MANUFACTURER);
        setModel(Build.MODEL);
        setDeviceVersion(Integer.toString(Build.VERSION.SDK_INT));
        setLocale(context.getResources().getConfiguration().locale.toString());
        setInputLangs(c.b(context));
        setWidth(context.getResources().getDisplayMetrics().widthPixels);
        setHeight(context.getResources().getDisplayMetrics().heightPixels);
        setDensity(context.getResources().getDisplayMetrics().density);
        setAppOnForeground(context);
        setSessionId(g.d().a());
        setUnknownSourcesAllowed(Boolean.valueOf(c.a(context)));
        setRoaming(context);
        setNetworkType(context);
        setNetworkLevels(context);
        setAppVersion(c.e(context));
        setAppCode(c.d(context));
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                fillSimDetails(telephonyManager);
                fillNetworkOperatorDetails(context, telephonyManager);
                fillCellLocationDetails(context, telephonyManager);
                setCellTimingAdv(context, telephonyManager);
            }
        } catch (Exception unused) {
        }
        fillWifiDetails(context, z);
        this.usbDebug = c.g(context);
        this.root = c.h(context);
        this.simulator = c.i(context);
        this.personalizedAdsServing = k.a(context, "USER_CONSENT_PERSONALIZED_ADS_SERVING", (String) null);
    }

    public e getNameValueJson() {
        com.startapp.android.publish.adsCommon.Utils.c cVar = new com.startapp.android.publish.adsCommon.Utils.c();
        addParams(cVar);
        return cVar;
    }

    public e getNameValueMap() {
        d dVar = new d();
        addParams(dVar);
        return dVar;
    }

    private void addParams(e eVar) {
        String a2;
        eVar.a("publisherId", (Object) this.publisherId, false);
        eVar.a("productId", (Object) this.productId, true);
        eVar.a("os", (Object) this.os, true);
        eVar.a("sdkVersion", (Object) this.sdkVersion, false);
        eVar.a("flavor", (Object) Long.valueOf(this.sdkFlavor), false);
        Map<String, String> map = this.frameworksMap;
        if (map != null && !map.isEmpty()) {
            String str = "";
            for (String next : this.frameworksMap.keySet()) {
                str = str + next + ":" + this.frameworksMap.get(next) + ";";
            }
            eVar.a("frameworksData", (Object) str.substring(0, str.length() - 1), false, false);
        }
        eVar.a("packageId", (Object) this.packageId, false);
        eVar.a("installerPkg", (Object) this.installerPkg, false);
        b.c cVar = this.userAdvertisingId;
        if (cVar != null) {
            eVar.a("userAdvertisingId", (Object) cVar.a(), false);
            if (this.userAdvertisingId.b()) {
                eVar.a("limat", (Object) Boolean.valueOf(this.userAdvertisingId.b()), false);
            }
            eVar.a("advertisingIdSource", (Object) this.userAdvertisingId.c(), false);
        } else {
            String str2 = this.androidId;
            if (str2 != null) {
                eVar.a("userId", (Object) str2, false);
            }
        }
        eVar.a("model", (Object) this.model, false);
        eVar.a("manufacturer", (Object) this.manufacturer, false);
        eVar.a("deviceVersion", (Object) this.deviceVersion, false);
        eVar.a("locale", (Object) this.locale, false);
        eVar.a("inputLangs", this.inputLangs, false);
        eVar.a("isp", (Object) this.isp, false);
        eVar.a("ispName", (Object) this.ispName, false);
        eVar.a("netOper", (Object) getNetOper(), false);
        eVar.a("networkOperName", (Object) getNetworkOperName(), false);
        eVar.a("cid", (Object) getCid(), false);
        eVar.a("lac", (Object) getLac(), false);
        eVar.a("blat", (Object) getBlat(), false);
        eVar.a("blon", (Object) getBlon(), false);
        eVar.a("ssid", (Object) getSsid(), false);
        eVar.a("bssid", (Object) getBssid(), false);
        eVar.a("wfScanRes", (Object) getWfScanRes(), false);
        eVar.a("subPublisherId", (Object) this.subPublisherId, false);
        eVar.a("subProductId", (Object) this.subProductId, false);
        eVar.a("retryCount", (Object) this.retry, false);
        eVar.a("roaming", (Object) isRoaming(), false);
        eVar.a("deviceIP", (Object) getDeviceIP(), false);
        eVar.a("grid", (Object) getNetworkType(), false);
        eVar.a("silev", (Object) getSignalLevel(), false);
        eVar.a("cellSignalLevel", (Object) getCellSignalLevel(), false);
        if (getWifiSignalLevel() != null) {
            eVar.a("wifiSignalLevel", (Object) getWifiSignalLevel(), false);
        }
        if (getWifiRssiLevel() != null) {
            eVar.a("wifiRssiLevel", (Object) getWifiRssiLevel(), false);
        }
        if (getCellTimingAdv() != null) {
            eVar.a("cellTimingAdv", (Object) getCellTimingAdv(), false);
        }
        eVar.a("outsource", (Object) isUnknownSourcesAllowed(), false);
        eVar.a("width", (Object) String.valueOf(this.width), false);
        eVar.a("height", (Object) String.valueOf(this.height), false);
        eVar.a("density", (Object) String.valueOf(this.density), false);
        eVar.a("fgApp", (Object) isAppOnForeground(), false);
        eVar.a("sdkId", (Object) String.valueOf(this.sdkId), true);
        eVar.a("clientSessionId", (Object) this.clientSessionId, false);
        eVar.a("appVersion", (Object) this.appVersion, false);
        eVar.a("appCode", (Object) Integer.valueOf(this.appCode), false);
        eVar.a("timeSinceBoot", (Object) Long.valueOf(getTimeSinceBoot()), false);
        if (getLocations() != null && getLocations().size() > 0 && (a2 = f.a(getLocations())) != null && !a2.equals("")) {
            eVar.a("locations", (Object) com.startapp.common.a.a.c(a2), false);
        }
        eVar.a("udbg", (Object) Boolean.valueOf(this.usbDebug), false);
        eVar.a("root", (Object) Boolean.valueOf(this.root), false);
        eVar.a("smltr", (Object) Boolean.valueOf(this.simulator), false);
        eVar.a("pas", (Object) this.personalizedAdsServing, false);
    }

    public String getRequestString() {
        return getNameValueMap().toString();
    }

    private void fillSimDetails(TelephonyManager telephonyManager) {
        if (telephonyManager.getSimState() == 5) {
            setIsp(telephonyManager.getSimOperator());
            setIspName(telephonyManager.getSimOperatorName());
        }
    }

    private void fillNetworkOperatorDetails(Context context, TelephonyManager telephonyManager) {
        int phoneType = telephonyManager.getPhoneType();
        if (phoneType != 0 && phoneType != 2) {
            String networkOperator = telephonyManager.getNetworkOperator();
            if (networkOperator != null) {
                setNetOper(com.startapp.common.a.a.c(networkOperator));
            }
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null) {
                setNetworkOperName(com.startapp.common.a.a.c(networkOperatorName));
            }
        }
    }

    private void fillCellLocationDetails(Context context, TelephonyManager telephonyManager) {
        CellLocation cellLocation;
        if ((!c.a(context, "android.permission.ACCESS_FINE_LOCATION") && !c.a(context, "android.permission.ACCESS_COARSE_LOCATION")) || (cellLocation = telephonyManager.getCellLocation()) == null) {
            return;
        }
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            setCid(com.startapp.common.a.a.c(String.valueOf(gsmCellLocation.getCid())));
            setLac(com.startapp.common.a.a.c(String.valueOf(gsmCellLocation.getLac())));
        } else if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            setBlat(com.startapp.common.a.a.c(String.valueOf(cdmaCellLocation.getBaseStationLatitude())));
            setBlon(com.startapp.common.a.a.c(String.valueOf(cdmaCellLocation.getBaseStationLongitude())));
        }
    }

    private String getCellTimingAdv() {
        return this.cellTimingAdv;
    }

    private void setCellTimingAdv(Context context, TelephonyManager telephonyManager) {
        this.cellTimingAdv = c.b(context, telephonyManager);
    }

    private static long getTimeSinceBoot() {
        return SystemClock.elapsedRealtime();
    }

    public void fillLocationDetails(AdPreferences adPreferences, Context context) {
        boolean z;
        this.locations = new ArrayList();
        boolean z2 = true;
        if (adPreferences == null || adPreferences.getLatitude() == null || adPreferences.getLongitude() == null) {
            z = false;
        } else {
            Location location = new Location("loc");
            location.setLongitude(adPreferences.getLongitude().doubleValue());
            location.setLongitude(adPreferences.getLongitude().doubleValue());
            location.setProvider(MetaData.DEFAULT_LOCATION_SOURCE);
            this.locations.add(location);
            z = true;
        }
        m.a().h(context);
        List<Location> a2 = f.a(context, MetaData.getInstance().getLocationConfig().isFiEnabled(), MetaData.getInstance().getLocationConfig().isCoEnabled());
        if (a2 == null || a2.size() <= 0) {
            z2 = z;
        } else {
            this.locations.addAll(a2);
        }
        setUsingLocation(context, z2);
    }

    public static void setUsingLocation(Context context, boolean z) {
        k.b(context, "shared_prefs_using_location", Boolean.valueOf(z));
    }

    private void fillWifiDetails(Context context, boolean z) {
        WifiManager wifiManager;
        List<ScanResult> a2;
        WifiInfo connectionInfo;
        try {
            if (MetaData.getInstance().isWfScanEnabled() && (wifiManager = (WifiManager) context.getSystemService("wifi")) != null && c.a(context, "android.permission.ACCESS_WIFI_STATE")) {
                if (getNetworkType().equals("WIFI") && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                    setDeviceIP(connectionInfo);
                    String ssid2 = connectionInfo.getSSID();
                    String bssid2 = connectionInfo.getBSSID();
                    if (ssid2 != null) {
                        setSsid(com.startapp.common.a.a.c(ssid2));
                    }
                    if (bssid2 != null) {
                        setBssid(com.startapp.common.a.a.c(bssid2));
                    }
                }
                if (z && (a2 = c.a(context, wifiManager)) != null && !a2.equals(Collections.EMPTY_LIST)) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < Math.min(5, a2.size()); i++) {
                        arrayList.add(new a(a2.get(i)));
                    }
                    setWfScanRes(com.startapp.common.a.a.c(TextUtils.join(";", arrayList)));
                }
            }
        } catch (Exception unused) {
        }
    }

    /* compiled from: StartAppSDK */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        private ScanResult f168a;

        public a(ScanResult scanResult) {
            this.f168a = scanResult;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            ScanResult scanResult = this.f168a;
            if (scanResult != null) {
                sb.append(scanResult.SSID);
                sb.append(',');
                sb.append(this.f168a.BSSID);
                sb.append(',');
                sb.append(WifiManager.calculateSignalLevel(this.f168a.level, 5));
                sb.append(',');
                sb.append(this.f168a.level);
                sb.append(',');
                long a2 = c.a(this.f168a);
                if (a2 != 0) {
                    sb.append(a2);
                }
                sb.append(',');
                CharSequence b = c.b(this.f168a);
                if (b != null) {
                    sb.append(b);
                }
            }
            return sb.toString();
        }
    }
}
