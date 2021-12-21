package com.startapp.android.publish.common.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class AdDetails implements Parcelable, Serializable {
    public static final Parcelable.Creator<AdDetails> CREATOR = new Parcelable.Creator<AdDetails>() {
        public AdDetails createFromParcel(Parcel parcel) {
            return new AdDetails(parcel);
        }

        public AdDetails[] newArray(int i) {
            return new AdDetails[i];
        }
    };
    private static final long serialVersionUID = 1;
    private String adId;
    private boolean app;
    private String appPresencePackage;
    private boolean belowMinCPM;
    private String category;
    private String clickUrl;
    private String closeUrl;
    private Long delayImpressionInSeconds;
    private String description;
    private String imageUrl;
    private String installs;
    private String intentDetails;
    private String intentPackageName;
    private int minAppVersion;
    private String packageName;
    private float rating;
    private String secondaryImageUrl;
    private Boolean sendRedirectHops;
    private boolean smartRedirect;
    private boolean startappBrowserEnabled;
    private String template;
    private String title;
    private String trackingClickUrl;
    private String trackingUrl;
    private Long ttl;

    public int describeContents() {
        return 0;
    }

    public AdDetails() {
        this.rating = 5.0f;
        this.belowMinCPM = false;
    }

    public String getAdId() {
        return this.adId;
    }

    public String getTrackingCloseUrl() {
        return this.closeUrl;
    }

    public String getClickUrl() {
        return this.clickUrl;
    }

    public String getTrackingUrl() {
        return this.trackingUrl;
    }

    public String getTrackingClickUrl() {
        return this.trackingClickUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public boolean getIsBelowMinCPM() {
        return this.belowMinCPM;
    }

    public String getSecondaryImageUrl() {
        return this.secondaryImageUrl;
    }

    public float getRating() {
        return this.rating;
    }

    public boolean isSmartRedirect() {
        return this.smartRedirect;
    }

    public String getTemplate() {
        return this.template;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getAppPresencePackage() {
        return this.appPresencePackage;
    }

    public String getIntentDetails() {
        return this.intentDetails;
    }

    public String getIntentPackageName() {
        return this.intentPackageName;
    }

    public boolean isCPE() {
        return this.intentPackageName != null;
    }

    public String getInstalls() {
        return this.installs;
    }

    public String getCategory() {
        return this.category;
    }

    public boolean isApp() {
        return this.app;
    }

    public int getMinAppVersion() {
        return this.minAppVersion;
    }

    public void setMinAppVersion(int i) {
        this.minAppVersion = i;
    }

    public boolean isStartappBrowserEnabled() {
        return this.startappBrowserEnabled;
    }

    public void setStartappBrowserEnabled(boolean z) {
        this.startappBrowserEnabled = z;
    }

    public Long getTtl() {
        return this.ttl;
    }

    public Long getDelayImpressionInSeconds() {
        return this.delayImpressionInSeconds;
    }

    public Boolean shouldSendRedirectHops() {
        return this.sendRedirectHops;
    }

    public String toString() {
        return "AdDetails [adId=" + this.adId + ", clickUrl=" + this.clickUrl + ", trackingUrl=" + this.trackingUrl + ", trackingClickUrl=" + this.trackingClickUrl + ", closeUrl=" + this.closeUrl + ", title=" + this.title + ", description=" + this.description + ", imageUrl=" + this.imageUrl + ", secondaryImageUrl=" + this.secondaryImageUrl + ", rating=" + this.rating + ", smartRedirect=" + this.smartRedirect + ", template=" + this.template + ", packageName=" + this.packageName + ", appPresencePackage=" + this.appPresencePackage + ", intentDetails=" + this.intentDetails + ", intentPackageName=" + this.intentPackageName + ", minAppVersion=" + this.minAppVersion + ", startappBrowserEnabled=" + this.startappBrowserEnabled + ", ttl=" + this.ttl + ", app=" + this.app + ", belowMinCPM=" + this.belowMinCPM + ", installs=" + this.installs + ", category=" + this.category + ", delayImpressionInSeconds=" + this.delayImpressionInSeconds + ", sendRedirectHops=" + this.sendRedirectHops + "]";
    }

    public AdDetails(Parcel parcel) {
        this.rating = 5.0f;
        boolean z = false;
        this.belowMinCPM = false;
        this.adId = parcel.readString();
        this.clickUrl = parcel.readString();
        this.trackingUrl = parcel.readString();
        this.trackingClickUrl = parcel.readString();
        this.closeUrl = parcel.readString();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.imageUrl = parcel.readString();
        this.secondaryImageUrl = parcel.readString();
        this.rating = parcel.readFloat();
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt();
        this.smartRedirect = false;
        if (readInt == 1) {
            this.smartRedirect = true;
        }
        this.startappBrowserEnabled = true;
        if (readInt2 == 0) {
            this.startappBrowserEnabled = false;
        }
        this.template = parcel.readString();
        this.packageName = parcel.readString();
        this.appPresencePackage = parcel.readString();
        this.intentPackageName = parcel.readString();
        this.intentDetails = parcel.readString();
        this.minAppVersion = parcel.readInt();
        this.installs = parcel.readString();
        this.category = parcel.readString();
        int readInt3 = parcel.readInt();
        this.app = false;
        if (readInt3 == 1) {
            this.app = true;
        }
        int readInt4 = parcel.readInt();
        this.belowMinCPM = false;
        if (readInt4 == 1) {
            this.belowMinCPM = true;
        }
        Long valueOf = Long.valueOf(parcel.readLong());
        this.ttl = valueOf;
        if (valueOf.longValue() == -1) {
            this.ttl = null;
        }
        Long valueOf2 = Long.valueOf(parcel.readLong());
        this.delayImpressionInSeconds = valueOf2;
        if (valueOf2.longValue() == -1) {
            this.delayImpressionInSeconds = null;
        }
        int readInt5 = parcel.readInt();
        if (readInt5 == 0) {
            this.sendRedirectHops = null;
        } else {
            this.sendRedirectHops = Boolean.valueOf(readInt5 == 1 ? true : z);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.adId);
        parcel.writeString(this.clickUrl);
        parcel.writeString(this.trackingUrl);
        parcel.writeString(this.trackingClickUrl);
        parcel.writeString(this.closeUrl);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.imageUrl);
        parcel.writeString(this.secondaryImageUrl);
        parcel.writeFloat(this.rating);
        boolean z = this.smartRedirect;
        boolean z2 = this.startappBrowserEnabled;
        parcel.writeInt(z ? 1 : 0);
        parcel.writeInt(z2 ? 1 : 0);
        parcel.writeString(this.template);
        parcel.writeString(this.packageName);
        parcel.writeString(this.appPresencePackage);
        parcel.writeString(this.intentPackageName);
        parcel.writeString(this.intentDetails);
        parcel.writeInt(this.minAppVersion);
        parcel.writeString(this.installs);
        parcel.writeString(this.category);
        parcel.writeInt(this.app ? 1 : 0);
        parcel.writeInt(this.belowMinCPM ? 1 : 0);
        Long l = this.ttl;
        if (l != null) {
            parcel.writeLong(l.longValue());
        } else {
            parcel.writeLong(-1);
        }
        Long l2 = this.delayImpressionInSeconds;
        if (l2 != null) {
            parcel.writeLong(l2.longValue());
        } else {
            parcel.writeLong(-1);
        }
        Boolean bool = this.sendRedirectHops;
        if (bool == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(bool.booleanValue() ? 1 : -1);
        }
    }
}
