package com.startapp.android.publish.cache;

import com.mopub.network.ImpressionData;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.common.model.AdPreferences;
import java.io.Serializable;
import java.util.Set;

/* compiled from: StartAppSDK */
public class c implements Serializable {
    private static final long serialVersionUID = 1;
    private String advertiserId = null;
    private Set<String> categories;
    private Set<String> categoriesExclude;
    private String country = null;
    private boolean forceFullpage = false;
    private boolean forceOfferWall2D = false;
    private boolean forceOfferWall3D = false;
    private boolean forceOverlay = false;
    private Double minCpm;
    private AdPreferences.Placement placement;
    private String template = null;
    private boolean testMode = false;
    private Ad.AdType type = null;
    private boolean videoMuted = false;

    public c(AdPreferences.Placement placement2, AdPreferences adPreferences) {
        this.placement = placement2;
        this.categories = adPreferences.getCategories();
        this.categoriesExclude = adPreferences.getCategoriesExclude();
        this.videoMuted = adPreferences.isVideoMuted();
        this.minCpm = adPreferences.getMinCpm();
        this.forceOfferWall3D = i.a(adPreferences, "forceOfferWall3D");
        this.forceOfferWall2D = i.a(adPreferences, "forceOfferWall2D");
        this.forceFullpage = i.a(adPreferences, "forceFullpage");
        this.forceOverlay = i.a(adPreferences, "forceOverlay");
        this.testMode = i.a(adPreferences, "testMode");
        this.country = i.b(adPreferences, ImpressionData.COUNTRY);
        this.advertiserId = i.b(adPreferences, "advertiserId");
        this.template = i.b(adPreferences, "template");
        this.type = com.startapp.android.publish.adsCommon.c.a(adPreferences, "type");
    }

    public AdPreferences.Placement a() {
        return this.placement;
    }

    public int hashCode() {
        String str = this.advertiserId;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        Set<String> set = this.categories;
        int hashCode2 = (hashCode + (set == null ? 0 : set.hashCode())) * 31;
        Set<String> set2 = this.categoriesExclude;
        int hashCode3 = (hashCode2 + (set2 == null ? 0 : set2.hashCode())) * 31;
        String str2 = this.country;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Double d = this.minCpm;
        int i2 = 1231;
        int hashCode5 = (((((((((hashCode4 + (d == null ? 0 : d.hashCode())) * 31) + (this.forceFullpage ? 1231 : 1237)) * 31) + (this.forceOfferWall2D ? 1231 : 1237)) * 31) + (this.forceOfferWall3D ? 1231 : 1237)) * 31) + (this.forceOverlay ? 1231 : 1237)) * 31;
        AdPreferences.Placement placement2 = this.placement;
        int hashCode6 = (hashCode5 + (placement2 == null ? 0 : placement2.hashCode())) * 31;
        String str3 = this.template;
        int hashCode7 = (hashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
        if (!this.testMode) {
            i2 = 1237;
        }
        int i3 = (hashCode7 + i2) * 31;
        Ad.AdType adType = this.type;
        if (adType != null) {
            i = adType.hashCode();
        }
        return i3 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        String str = this.advertiserId;
        if (str == null) {
            if (cVar.advertiserId != null) {
                return false;
            }
        } else if (!str.equals(cVar.advertiserId)) {
            return false;
        }
        Set<String> set = this.categories;
        if (set == null) {
            if (cVar.categories != null) {
                return false;
            }
        } else if (!set.equals(cVar.categories)) {
            return false;
        }
        Set<String> set2 = this.categoriesExclude;
        if (set2 == null) {
            if (cVar.categoriesExclude != null) {
                return false;
            }
        } else if (!set2.equals(cVar.categoriesExclude)) {
            return false;
        }
        String str2 = this.country;
        if (str2 == null) {
            if (cVar.country != null) {
                return false;
            }
        } else if (!str2.equals(cVar.country)) {
            return false;
        }
        if (this.forceFullpage != cVar.forceFullpage || this.forceOfferWall2D != cVar.forceOfferWall2D || this.forceOfferWall3D != cVar.forceOfferWall3D || this.forceOverlay != cVar.forceOverlay || this.placement != cVar.placement) {
            return false;
        }
        String str3 = this.template;
        if (str3 == null) {
            if (cVar.template != null) {
                return false;
            }
        } else if (!str3.equals(cVar.template)) {
            return false;
        }
        if (this.testMode != cVar.testMode || this.videoMuted != cVar.videoMuted) {
            return false;
        }
        Ad.AdType adType = this.type;
        if (adType == null) {
            if (cVar.type != null) {
                return false;
            }
        } else if (!adType.equals(cVar.type)) {
            return false;
        }
        Double d = this.minCpm;
        if (d == null) {
            if (cVar.minCpm != null) {
                return false;
            }
        } else if (d != cVar.minCpm) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "CacheKey [placement=" + this.placement + ", categories=" + this.categories + ", categoriesExclude=" + this.categoriesExclude + ", forceOfferWall3D=" + this.forceOfferWall3D + ", forceOfferWall2D=" + this.forceOfferWall2D + ", forceFullpage=" + this.forceFullpage + ", forceOverlay=" + this.forceOverlay + ", testMode=" + this.testMode + ", minCpm=" + this.minCpm + ", country=" + this.country + ", advertiserId=" + this.advertiserId + ", template=" + this.template + ", type=" + this.type + "]";
    }
}
