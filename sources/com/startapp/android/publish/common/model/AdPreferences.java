package com.startapp.android.publish.common.model;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.SDKAdPreferences;
import com.startapp.android.publish.adsCommon.m;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/* compiled from: StartAppSDK */
public class AdPreferences implements Serializable {
    public static final String TYPE_APP_WALL = "APP_WALL";
    public static final String TYPE_BANNER = "BANNER";
    public static final String TYPE_INAPP_EXIT = "INAPP_EXIT";
    public static final String TYPE_SCRINGO_TOOLBAR = "SCRINGO_TOOLBAR";
    public static final String TYPE_TEXT = "TEXT";
    private static final long serialVersionUID = 1;
    protected String advertiserId = null;
    private String age = null;
    private Boolean ai = null;
    private Boolean as = null;
    private Set<String> categories = null;
    private Set<String> categoriesExclude = null;
    protected String country = null;
    protected boolean forceFullpage = false;
    protected boolean forceOfferWall2D = false;
    protected boolean forceOfferWall3D = false;
    protected boolean forceOverlay = false;
    private SDKAdPreferences.Gender gender = null;
    private boolean hardwareAccelerated = m.a().d();
    private String keywords = null;
    private Double latitude = null;
    private Double longitude = null;
    protected Double minCpm = null;
    protected Set<String> packageInclude = null;
    private String productId = null;
    private String publisherId = null;
    protected String template = null;
    private boolean testMode = false;
    protected Ad.AdType type = null;
    private boolean videoMuted = false;

    public boolean isSimpleToken() {
        return true;
    }

    /* compiled from: StartAppSDK */
    public enum Placement {
        INAPP_FULL_SCREEN(1),
        INAPP_BANNER(2),
        INAPP_OFFER_WALL(3),
        INAPP_SPLASH(4),
        INAPP_OVERLAY(5),
        INAPP_NATIVE(6),
        DEVICE_SIDEBAR(7),
        INAPP_RETURN(8),
        INAPP_BROWSER(9);
        
        private int index;

        private Placement(int i) {
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        public boolean isInterstitial() {
            return this == INAPP_FULL_SCREEN || this == INAPP_OFFER_WALL || this == INAPP_SPLASH || this == INAPP_OVERLAY;
        }

        public static Placement getByIndex(int i) {
            Placement placement = INAPP_FULL_SCREEN;
            Placement[] values = values();
            for (int i2 = 0; i2 < values.length; i2++) {
                if (values[i2].getIndex() == i) {
                    placement = values[i2];
                }
            }
            return placement;
        }
    }

    public AdPreferences() {
    }

    public AdPreferences(AdPreferences adPreferences) {
        this.country = adPreferences.country;
        this.advertiserId = adPreferences.advertiserId;
        this.template = adPreferences.template;
        this.type = adPreferences.type;
        if (adPreferences.packageInclude != null) {
            this.packageInclude = new HashSet(adPreferences.packageInclude);
        }
        this.minCpm = adPreferences.minCpm;
        this.forceOfferWall3D = adPreferences.forceOfferWall3D;
        this.forceOfferWall2D = adPreferences.forceOfferWall2D;
        this.forceFullpage = adPreferences.forceFullpage;
        this.forceOverlay = adPreferences.forceOverlay;
        this.publisherId = adPreferences.publisherId;
        this.productId = adPreferences.productId;
        this.testMode = adPreferences.testMode;
        this.longitude = adPreferences.longitude;
        this.latitude = adPreferences.latitude;
        this.keywords = adPreferences.keywords;
        this.gender = adPreferences.gender;
        this.age = adPreferences.age;
        this.ai = adPreferences.ai;
        this.as = adPreferences.as;
        this.videoMuted = adPreferences.videoMuted;
        this.hardwareAccelerated = adPreferences.hardwareAccelerated;
        if (adPreferences.categories != null) {
            this.categories = new HashSet(adPreferences.categories);
        }
        if (adPreferences.categoriesExclude != null) {
            this.categoriesExclude = new HashSet(adPreferences.categoriesExclude);
        }
    }

    @Deprecated
    public AdPreferences(String str, String str2) {
        this.publisherId = str;
        this.productId = str2;
    }

    @Deprecated
    public AdPreferences(String str, String str2, String str3) {
        this.publisherId = str;
        this.productId = str2;
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    @Deprecated
    public AdPreferences setPublisherId(String str) {
        this.publisherId = str;
        return this;
    }

    public String getProductId() {
        return this.productId;
    }

    @Deprecated
    public AdPreferences setProductId(String str) {
        this.productId = str;
        return this;
    }

    public boolean isTestMode() {
        return this.testMode;
    }

    public AdPreferences setTestMode(boolean z) {
        this.testMode = z;
        return this;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public AdPreferences setLongitude(double d) {
        this.longitude = Double.valueOf(d);
        return this;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public AdPreferences setLatitude(double d) {
        this.latitude = Double.valueOf(d);
        return this;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public AdPreferences setKeywords(String str) {
        this.keywords = str;
        return this;
    }

    public SDKAdPreferences.Gender getGender(Context context) {
        SDKAdPreferences.Gender gender2 = this.gender;
        return gender2 == null ? m.a().g(context).getGender() : gender2;
    }

    public AdPreferences setGender(SDKAdPreferences.Gender gender2) {
        this.gender = gender2;
        return this;
    }

    public String getAge(Context context) {
        String str = this.age;
        return str == null ? m.a().g(context).getAge() : str;
    }

    public AdPreferences setAge(Integer num) {
        this.age = Integer.toString(num.intValue());
        return this;
    }

    public AdPreferences setAge(String str) {
        this.age = str;
        return this;
    }

    public Boolean getAi() {
        return this.ai;
    }

    public AdPreferences setAi(Boolean bool) {
        this.ai = bool;
        return this;
    }

    public Boolean getAs() {
        return this.as;
    }

    public AdPreferences setAs(Boolean bool) {
        this.as = bool;
        return this;
    }

    public AdPreferences muteVideo() {
        this.videoMuted = true;
        return this;
    }

    public boolean isVideoMuted() {
        return this.videoMuted;
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public AdPreferences addCategory(String str) {
        if (this.categories == null) {
            this.categories = new HashSet();
        }
        this.categories.add(str);
        return this;
    }

    public Set<String> getCategoriesExclude() {
        return this.categoriesExclude;
    }

    public AdPreferences addCategoryExclude(String str) {
        if (this.categoriesExclude == null) {
            this.categoriesExclude = new HashSet();
        }
        this.categoriesExclude.add(str);
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean isHardwareAccelerated() {
        return this.hardwareAccelerated;
    }

    public Ad.AdType getType() {
        return this.type;
    }

    public void setType(Ad.AdType adType) {
        this.type = adType;
    }

    public Double getMinCpm() {
        return this.minCpm;
    }

    public void setMinCpm(Double d) {
        this.minCpm = d;
    }

    public String toString() {
        return "AdPreferences [publisherId=" + this.publisherId + ", productId=" + this.productId + ", testMode=" + this.testMode + ", longitude=" + this.longitude + ", latitude=" + this.latitude + ", keywords=" + this.keywords + ", minCPM=" + this.minCpm + ", categories=" + this.categories + ", categoriesExclude=" + this.categoriesExclude + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdPreferences adPreferences = (AdPreferences) obj;
        if (this.forceOfferWall3D != adPreferences.forceOfferWall3D || this.forceOfferWall2D != adPreferences.forceOfferWall2D || this.forceFullpage != adPreferences.forceFullpage || this.forceOverlay != adPreferences.forceOverlay || this.testMode != adPreferences.testMode || this.videoMuted != adPreferences.videoMuted || this.hardwareAccelerated != adPreferences.hardwareAccelerated) {
            return false;
        }
        String str = this.country;
        if (str == null ? adPreferences.country != null : !str.equals(adPreferences.country)) {
            return false;
        }
        String str2 = this.advertiserId;
        if (str2 == null ? adPreferences.advertiserId != null : !str2.equals(adPreferences.advertiserId)) {
            return false;
        }
        String str3 = this.template;
        if (str3 == null ? adPreferences.template != null : !str3.equals(adPreferences.template)) {
            return false;
        }
        if (this.type != adPreferences.type) {
            return false;
        }
        Set<String> set = this.packageInclude;
        if (set == null ? adPreferences.packageInclude != null : !set.equals(adPreferences.packageInclude)) {
            return false;
        }
        Double d = this.minCpm;
        if (d == null ? adPreferences.minCpm != null : !d.equals(adPreferences.minCpm)) {
            return false;
        }
        String str4 = this.publisherId;
        if (str4 == null ? adPreferences.publisherId != null : !str4.equals(adPreferences.publisherId)) {
            return false;
        }
        String str5 = this.productId;
        if (str5 == null ? adPreferences.productId != null : !str5.equals(adPreferences.productId)) {
            return false;
        }
        Double d2 = this.longitude;
        if (d2 == null ? adPreferences.longitude != null : !d2.equals(adPreferences.longitude)) {
            return false;
        }
        Double d3 = this.latitude;
        if (d3 == null ? adPreferences.latitude != null : !d3.equals(adPreferences.latitude)) {
            return false;
        }
        String str6 = this.keywords;
        if (str6 == null ? adPreferences.keywords != null : !str6.equals(adPreferences.keywords)) {
            return false;
        }
        if (this.gender != adPreferences.gender) {
            return false;
        }
        String str7 = this.age;
        if (str7 == null ? adPreferences.age != null : !str7.equals(adPreferences.age)) {
            return false;
        }
        Boolean bool = this.ai;
        if (bool == null ? adPreferences.ai != null : !bool.equals(adPreferences.ai)) {
            return false;
        }
        Boolean bool2 = this.as;
        if (bool2 == null ? adPreferences.as != null : !bool2.equals(adPreferences.as)) {
            return false;
        }
        Set<String> set2 = this.categories;
        if (set2 == null ? adPreferences.categories != null : !set2.equals(adPreferences.categories)) {
            return false;
        }
        Set<String> set3 = this.categoriesExclude;
        Set<String> set4 = adPreferences.categoriesExclude;
        if (set3 != null) {
            return set3.equals(set4);
        }
        if (set4 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.country;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.advertiserId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.template;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Ad.AdType adType = this.type;
        int hashCode4 = (hashCode3 + (adType != null ? adType.hashCode() : 0)) * 31;
        Set<String> set = this.packageInclude;
        int hashCode5 = (((((((((hashCode4 + (set != null ? set.hashCode() : 0)) * 31) + (this.forceOfferWall3D ? 1 : 0)) * 31) + (this.forceOfferWall2D ? 1 : 0)) * 31) + (this.forceFullpage ? 1 : 0)) * 31) + (this.forceOverlay ? 1 : 0)) * 31;
        Double d = this.minCpm;
        int hashCode6 = (hashCode5 + (d != null ? d.hashCode() : 0)) * 31;
        String str4 = this.publisherId;
        int hashCode7 = (hashCode6 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.productId;
        int hashCode8 = (((hashCode7 + (str5 != null ? str5.hashCode() : 0)) * 31) + (this.testMode ? 1 : 0)) * 31;
        Double d2 = this.longitude;
        int hashCode9 = (hashCode8 + (d2 != null ? d2.hashCode() : 0)) * 31;
        Double d3 = this.latitude;
        int hashCode10 = (hashCode9 + (d3 != null ? d3.hashCode() : 0)) * 31;
        String str6 = this.keywords;
        int hashCode11 = (hashCode10 + (str6 != null ? str6.hashCode() : 0)) * 31;
        SDKAdPreferences.Gender gender2 = this.gender;
        int hashCode12 = (hashCode11 + (gender2 != null ? gender2.hashCode() : 0)) * 31;
        String str7 = this.age;
        int hashCode13 = (hashCode12 + (str7 != null ? str7.hashCode() : 0)) * 31;
        Boolean bool = this.ai;
        int hashCode14 = (hashCode13 + (bool != null ? bool.hashCode() : 0)) * 31;
        Boolean bool2 = this.as;
        int hashCode15 = (((((hashCode14 + (bool2 != null ? bool2.hashCode() : 0)) * 31) + (this.videoMuted ? 1 : 0)) * 31) + (this.hardwareAccelerated ? 1 : 0)) * 31;
        Set<String> set2 = this.categories;
        int hashCode16 = (hashCode15 + (set2 != null ? set2.hashCode() : 0)) * 31;
        Set<String> set3 = this.categoriesExclude;
        if (set3 != null) {
            i = set3.hashCode();
        }
        return hashCode16 + i;
    }
}
