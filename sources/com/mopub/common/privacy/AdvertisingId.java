package com.mopub.common.privacy;

import android.text.TextUtils;
import com.mopub.common.Preconditions;
import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class AdvertisingId implements Serializable {
    static final long ONE_DAY_MS = 86400000;
    private static final String PREFIX_IFA = "ifa:";
    private static final String PREFIX_MOPUB = "mopub:";
    final String mAdvertisingId;
    final boolean mDoNotTrack;
    final Calendar mLastRotation;
    final String mMopubId;

    AdvertisingId(String str, String str2, boolean z, long j) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.mAdvertisingId = str;
        this.mMopubId = str2;
        this.mDoNotTrack = z;
        Calendar instance = Calendar.getInstance();
        this.mLastRotation = instance;
        instance.setTimeInMillis(j);
    }

    public String getIdentifier(boolean z) {
        return (this.mDoNotTrack || !z) ? this.mMopubId : this.mAdvertisingId;
    }

    public String getIdWithPrefix(boolean z) {
        if (this.mDoNotTrack || !z || this.mAdvertisingId.isEmpty()) {
            return PREFIX_MOPUB + this.mMopubId;
        }
        return PREFIX_IFA + this.mAdvertisingId;
    }

    /* access modifiers changed from: package-private */
    public String getIfaWithPrefix() {
        if (TextUtils.isEmpty(this.mAdvertisingId)) {
            return "";
        }
        return PREFIX_IFA + this.mAdvertisingId;
    }

    public boolean isDoNotTrack() {
        return this.mDoNotTrack;
    }

    static AdvertisingId generateExpiredAdvertisingId() {
        return new AdvertisingId("", generateIdString(), false, (Calendar.getInstance().getTimeInMillis() - ONE_DAY_MS) - 1);
    }

    static AdvertisingId generateFreshAdvertisingId() {
        return new AdvertisingId("", generateIdString(), false, Calendar.getInstance().getTimeInMillis());
    }

    static String generateIdString() {
        return UUID.randomUUID().toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isRotationRequired() {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Calendar instance2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        instance2.setTimeInMillis(this.mLastRotation.getTimeInMillis());
        if (instance.get(6) == instance2.get(6) && instance.get(1) == instance2.get(1)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "AdvertisingId{mLastRotation=" + this.mLastRotation + ", mAdvertisingId='" + this.mAdvertisingId + '\'' + ", mMopubId='" + this.mMopubId + '\'' + ", mDoNotTrack=" + this.mDoNotTrack + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdvertisingId)) {
            return false;
        }
        AdvertisingId advertisingId = (AdvertisingId) obj;
        if (this.mDoNotTrack == advertisingId.mDoNotTrack && this.mAdvertisingId.equals(advertisingId.mAdvertisingId)) {
            return this.mMopubId.equals(advertisingId.mMopubId);
        }
        return false;
    }

    public int hashCode() {
        return (((this.mAdvertisingId.hashCode() * 31) + this.mMopubId.hashCode()) * 31) + (this.mDoNotTrack ? 1 : 0);
    }
}
