package com.truenet.android;

import a.a.b.b.h;
import com.appnext.base.b.d;
import com.google.android.gms.ads.AdRequest;

/* compiled from: StartAppSDK */
public final class DeviceInfo {
    private final String advertisingId;
    private final String cellId;
    private final String deviceManufacturer;
    private final String deviceModel;
    private final String deviceType;
    private final String deviceVersion;
    private final String isp;
    private final String ispName;
    private final String latitude;
    private final String locale;
    private final String locationAreaCode;
    private final String longitude;
    private final String networkOperName;
    private final String networkType;
    private final String osId;
    private final String osVer;
    private final String packageName;
    private String publisherId;
    private final String sdkVersion;
    private final String signalLevel;
    private final String userAgent;

    public static /* synthetic */ DeviceInfo copy$default(DeviceInfo deviceInfo, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, int i, Object obj) {
        DeviceInfo deviceInfo2 = deviceInfo;
        int i2 = i;
        return deviceInfo.copy((i2 & 1) != 0 ? deviceInfo2.latitude : str, (i2 & 2) != 0 ? deviceInfo2.longitude : str2, (i2 & 4) != 0 ? deviceInfo2.userAgent : str3, (i2 & 8) != 0 ? deviceInfo2.locale : str4, (i2 & 16) != 0 ? deviceInfo2.advertisingId : str5, (i2 & 32) != 0 ? deviceInfo2.osId : str6, (i2 & 64) != 0 ? deviceInfo2.osVer : str7, (i2 & 128) != 0 ? deviceInfo2.deviceModel : str8, (i2 & 256) != 0 ? deviceInfo2.deviceManufacturer : str9, (i2 & AdRequest.MAX_CONTENT_URL_LENGTH) != 0 ? deviceInfo2.deviceVersion : str10, (i2 & d.fb) != 0 ? deviceInfo2.packageName : str11, (i2 & 2048) != 0 ? deviceInfo2.networkOperName : str12, (i2 & 4096) != 0 ? deviceInfo2.isp : str13, (i2 & 8192) != 0 ? deviceInfo2.ispName : str14, (i2 & 16384) != 0 ? deviceInfo2.cellId : str15, (i2 & 32768) != 0 ? deviceInfo2.locationAreaCode : str16, (i2 & 65536) != 0 ? deviceInfo2.networkType : str17, (i2 & 131072) != 0 ? deviceInfo2.signalLevel : str18, (i2 & 262144) != 0 ? deviceInfo2.deviceType : str19, (i2 & 524288) != 0 ? deviceInfo2.sdkVersion : str20, (i2 & 1048576) != 0 ? deviceInfo2.publisherId : str21);
    }

    public final String component1() {
        return this.latitude;
    }

    public final String component10() {
        return this.deviceVersion;
    }

    public final String component11() {
        return this.packageName;
    }

    public final String component12() {
        return this.networkOperName;
    }

    public final String component13() {
        return this.isp;
    }

    public final String component14() {
        return this.ispName;
    }

    public final String component15() {
        return this.cellId;
    }

    public final String component16() {
        return this.locationAreaCode;
    }

    public final String component17() {
        return this.networkType;
    }

    public final String component18() {
        return this.signalLevel;
    }

    public final String component19() {
        return this.deviceType;
    }

    public final String component2() {
        return this.longitude;
    }

    public final String component20() {
        return this.sdkVersion;
    }

    public final String component21() {
        return this.publisherId;
    }

    public final String component3() {
        return this.userAgent;
    }

    public final String component4() {
        return this.locale;
    }

    public final String component5() {
        return this.advertisingId;
    }

    public final String component6() {
        return this.osId;
    }

    public final String component7() {
        return this.osVer;
    }

    public final String component8() {
        return this.deviceModel;
    }

    public final String component9() {
        return this.deviceManufacturer;
    }

    public final DeviceInfo copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21) {
        String str22 = str;
        h.b(str22, "latitude");
        h.b(str2, "longitude");
        h.b(str3, "userAgent");
        h.b(str4, "locale");
        h.b(str5, "advertisingId");
        h.b(str6, "osId");
        h.b(str7, "osVer");
        h.b(str8, "deviceModel");
        h.b(str9, "deviceManufacturer");
        h.b(str10, "deviceVersion");
        h.b(str11, "packageName");
        h.b(str12, "networkOperName");
        h.b(str13, "isp");
        h.b(str14, "ispName");
        h.b(str15, "cellId");
        h.b(str16, "locationAreaCode");
        h.b(str17, "networkType");
        h.b(str18, "signalLevel");
        h.b(str19, "deviceType");
        h.b(str20, "sdkVersion");
        h.b(str21, "publisherId");
        return new DeviceInfo(str22, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        return h.a((Object) this.latitude, (Object) deviceInfo.latitude) && h.a((Object) this.longitude, (Object) deviceInfo.longitude) && h.a((Object) this.userAgent, (Object) deviceInfo.userAgent) && h.a((Object) this.locale, (Object) deviceInfo.locale) && h.a((Object) this.advertisingId, (Object) deviceInfo.advertisingId) && h.a((Object) this.osId, (Object) deviceInfo.osId) && h.a((Object) this.osVer, (Object) deviceInfo.osVer) && h.a((Object) this.deviceModel, (Object) deviceInfo.deviceModel) && h.a((Object) this.deviceManufacturer, (Object) deviceInfo.deviceManufacturer) && h.a((Object) this.deviceVersion, (Object) deviceInfo.deviceVersion) && h.a((Object) this.packageName, (Object) deviceInfo.packageName) && h.a((Object) this.networkOperName, (Object) deviceInfo.networkOperName) && h.a((Object) this.isp, (Object) deviceInfo.isp) && h.a((Object) this.ispName, (Object) deviceInfo.ispName) && h.a((Object) this.cellId, (Object) deviceInfo.cellId) && h.a((Object) this.locationAreaCode, (Object) deviceInfo.locationAreaCode) && h.a((Object) this.networkType, (Object) deviceInfo.networkType) && h.a((Object) this.signalLevel, (Object) deviceInfo.signalLevel) && h.a((Object) this.deviceType, (Object) deviceInfo.deviceType) && h.a((Object) this.sdkVersion, (Object) deviceInfo.sdkVersion) && h.a((Object) this.publisherId, (Object) deviceInfo.publisherId);
    }

    public int hashCode() {
        String str = this.latitude;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.longitude;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.userAgent;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.locale;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.advertisingId;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.osId;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.osVer;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.deviceModel;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.deviceManufacturer;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.deviceVersion;
        int hashCode10 = (hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.packageName;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.networkOperName;
        int hashCode12 = (hashCode11 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.isp;
        int hashCode13 = (hashCode12 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.ispName;
        int hashCode14 = (hashCode13 + (str14 != null ? str14.hashCode() : 0)) * 31;
        String str15 = this.cellId;
        int hashCode15 = (hashCode14 + (str15 != null ? str15.hashCode() : 0)) * 31;
        String str16 = this.locationAreaCode;
        int hashCode16 = (hashCode15 + (str16 != null ? str16.hashCode() : 0)) * 31;
        String str17 = this.networkType;
        int hashCode17 = (hashCode16 + (str17 != null ? str17.hashCode() : 0)) * 31;
        String str18 = this.signalLevel;
        int hashCode18 = (hashCode17 + (str18 != null ? str18.hashCode() : 0)) * 31;
        String str19 = this.deviceType;
        int hashCode19 = (hashCode18 + (str19 != null ? str19.hashCode() : 0)) * 31;
        String str20 = this.sdkVersion;
        int hashCode20 = (hashCode19 + (str20 != null ? str20.hashCode() : 0)) * 31;
        String str21 = this.publisherId;
        if (str21 != null) {
            i = str21.hashCode();
        }
        return hashCode20 + i;
    }

    public String toString() {
        return "DeviceInfo(latitude=" + this.latitude + ", longitude=" + this.longitude + ", userAgent=" + this.userAgent + ", locale=" + this.locale + ", advertisingId=" + this.advertisingId + ", osId=" + this.osId + ", osVer=" + this.osVer + ", deviceModel=" + this.deviceModel + ", deviceManufacturer=" + this.deviceManufacturer + ", deviceVersion=" + this.deviceVersion + ", packageName=" + this.packageName + ", networkOperName=" + this.networkOperName + ", isp=" + this.isp + ", ispName=" + this.ispName + ", cellId=" + this.cellId + ", locationAreaCode=" + this.locationAreaCode + ", networkType=" + this.networkType + ", signalLevel=" + this.signalLevel + ", deviceType=" + this.deviceType + ", sdkVersion=" + this.sdkVersion + ", publisherId=" + this.publisherId + ")";
    }

    public DeviceInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21) {
        String str22 = str;
        String str23 = str2;
        String str24 = str3;
        String str25 = str4;
        String str26 = str5;
        String str27 = str6;
        String str28 = str7;
        String str29 = str8;
        String str30 = str9;
        String str31 = str10;
        String str32 = str11;
        String str33 = str12;
        String str34 = str13;
        String str35 = str14;
        String str36 = str16;
        h.b(str22, "latitude");
        h.b(str23, "longitude");
        h.b(str24, "userAgent");
        h.b(str25, "locale");
        h.b(str26, "advertisingId");
        h.b(str27, "osId");
        h.b(str28, "osVer");
        h.b(str29, "deviceModel");
        h.b(str30, "deviceManufacturer");
        h.b(str31, "deviceVersion");
        h.b(str32, "packageName");
        h.b(str33, "networkOperName");
        h.b(str34, "isp");
        h.b(str35, "ispName");
        h.b(str15, "cellId");
        h.b(str16, "locationAreaCode");
        h.b(str17, "networkType");
        h.b(str18, "signalLevel");
        h.b(str19, "deviceType");
        h.b(str20, "sdkVersion");
        h.b(str21, "publisherId");
        this.latitude = str22;
        this.longitude = str23;
        this.userAgent = str24;
        this.locale = str25;
        this.advertisingId = str26;
        this.osId = str27;
        this.osVer = str28;
        this.deviceModel = str29;
        this.deviceManufacturer = str30;
        this.deviceVersion = str31;
        this.packageName = str32;
        this.networkOperName = str33;
        this.isp = str34;
        this.ispName = str35;
        this.cellId = str15;
        this.locationAreaCode = str16;
        this.networkType = str17;
        this.signalLevel = str18;
        this.deviceType = str19;
        this.sdkVersion = str20;
        this.publisherId = str21;
    }

    public final String getLatitude() {
        return this.latitude;
    }

    public final String getLocale() {
        return this.locale;
    }

    public final String getLongitude() {
        return this.longitude;
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    public final String getAdvertisingId() {
        return this.advertisingId;
    }

    public final String getOsId() {
        return this.osId;
    }

    public final String getOsVer() {
        return this.osVer;
    }

    public final String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public final String getDeviceModel() {
        return this.deviceModel;
    }

    public final String getDeviceVersion() {
        return this.deviceVersion;
    }

    public final String getIsp() {
        return this.isp;
    }

    public final String getNetworkOperName() {
        return this.networkOperName;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getCellId() {
        return this.cellId;
    }

    public final String getIspName() {
        return this.ispName;
    }

    public final String getLocationAreaCode() {
        return this.locationAreaCode;
    }

    public final String getNetworkType() {
        return this.networkType;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final String getPublisherId() {
        return this.publisherId;
    }

    public final String getSdkVersion() {
        return this.sdkVersion;
    }

    public final String getSignalLevel() {
        return this.signalLevel;
    }

    public final void setPublisherId(String str) {
        h.b(str, "<set-?>");
        this.publisherId = str;
    }
}
