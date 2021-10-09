package com.mopub.nativeads;

import android.location.Location;
import android.text.TextUtils;
import com.google.android.gms.plus.PlusShare;
import com.mopub.common.MoPub;
import java.util.EnumSet;

public class RequestParameters {
    private final EnumSet<NativeAdAsset> mDesiredAssets;
    private final String mKeywords;
    private final Location mLocation;
    private final String mUserDataKeywords;

    public enum NativeAdAsset {
        TITLE(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE),
        TEXT("text"),
        ICON_IMAGE("iconimage"),
        MAIN_IMAGE("mainimage"),
        CALL_TO_ACTION_TEXT("ctatext"),
        STAR_RATING("starrating"),
        SPONSORED("sponsored");
        
        private final String mAssetName;

        private NativeAdAsset(String str) {
            this.mAssetName = str;
        }

        public String toString() {
            return this.mAssetName;
        }
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public EnumSet<NativeAdAsset> desiredAssets;
        /* access modifiers changed from: private */
        public String keywords;
        /* access modifiers changed from: private */
        public Location location;
        /* access modifiers changed from: private */
        public String userDatakeywords;

        public final Builder keywords(String str) {
            this.keywords = str;
            return this;
        }

        public final Builder userDataKeywords(String str) {
            if (!MoPub.canCollectPersonalInformation()) {
                str = null;
            }
            this.userDatakeywords = str;
            return this;
        }

        public final Builder location(Location location2) {
            if (!MoPub.canCollectPersonalInformation()) {
                location2 = null;
            }
            this.location = location2;
            return this;
        }

        public final Builder desiredAssets(EnumSet<NativeAdAsset> enumSet) {
            this.desiredAssets = EnumSet.copyOf(enumSet);
            return this;
        }

        public final RequestParameters build() {
            return new RequestParameters(this);
        }
    }

    private RequestParameters(Builder builder) {
        this.mKeywords = builder.keywords;
        this.mDesiredAssets = builder.desiredAssets;
        boolean canCollectPersonalInformation = MoPub.canCollectPersonalInformation();
        Location location = null;
        this.mUserDataKeywords = canCollectPersonalInformation ? builder.userDatakeywords : null;
        this.mLocation = canCollectPersonalInformation ? builder.location : location;
    }

    public final String getKeywords() {
        return this.mKeywords;
    }

    public final String getUserDataKeywords() {
        if (!MoPub.canCollectPersonalInformation()) {
            return null;
        }
        return this.mUserDataKeywords;
    }

    public final Location getLocation() {
        return this.mLocation;
    }

    public final String getDesiredAssets() {
        EnumSet<NativeAdAsset> enumSet = this.mDesiredAssets;
        return enumSet != null ? TextUtils.join(",", enumSet.toArray()) : "";
    }
}
