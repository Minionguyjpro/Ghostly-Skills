package org.altbeacon.beacon.service;

import android.os.Bundle;
import org.altbeacon.beacon.Region;

public class MonitoringData {
    private final boolean mInside;
    private final Region mRegion;

    public MonitoringData(boolean z, Region region) {
        this.mInside = z;
        this.mRegion = region;
    }

    public boolean isInside() {
        return this.mInside;
    }

    public Region getRegion() {
        return this.mRegion;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("region", this.mRegion);
        bundle.putBoolean("inside", this.mInside);
        return bundle;
    }

    public static MonitoringData fromBundle(Bundle bundle) {
        bundle.setClassLoader(Region.class.getClassLoader());
        return new MonitoringData(Boolean.valueOf(bundle.getBoolean("inside")).booleanValue(), bundle.get("region") != null ? (Region) bundle.getSerializable("region") : null);
    }
}
