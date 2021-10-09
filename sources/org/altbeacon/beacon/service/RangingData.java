package org.altbeacon.beacon.service;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;

public class RangingData {
    private final Collection<Beacon> mBeacons;
    private final Region mRegion;

    public RangingData(Collection<Beacon> collection, Region region) {
        synchronized (collection) {
            this.mBeacons = collection;
        }
        this.mRegion = region;
    }

    public Collection<Beacon> getBeacons() {
        return this.mBeacons;
    }

    public Region getRegion() {
        return this.mRegion;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("region", this.mRegion);
        ArrayList arrayList = new ArrayList();
        for (Beacon add : this.mBeacons) {
            arrayList.add(add);
        }
        bundle.putSerializable("beacons", arrayList);
        return bundle;
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [java.io.Serializable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.altbeacon.beacon.service.RangingData fromBundle(android.os.Bundle r4) {
        /*
            java.lang.Class<org.altbeacon.beacon.Region> r0 = org.altbeacon.beacon.Region.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            r4.setClassLoader(r0)
            java.lang.String r0 = "beacons"
            java.lang.Object r1 = r4.get(r0)
            r2 = 0
            if (r1 == 0) goto L_0x0019
            java.io.Serializable r0 = r4.getSerializable(r0)
            java.util.Collection r0 = (java.util.Collection) r0
            goto L_0x001a
        L_0x0019:
            r0 = r2
        L_0x001a:
            java.lang.String r1 = "region"
            java.lang.Object r3 = r4.get(r1)
            if (r3 == 0) goto L_0x0029
            java.io.Serializable r4 = r4.getSerializable(r1)
            r2 = r4
            org.altbeacon.beacon.Region r2 = (org.altbeacon.beacon.Region) r2
        L_0x0029:
            org.altbeacon.beacon.service.RangingData r4 = new org.altbeacon.beacon.service.RangingData
            r4.<init>(r0, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.RangingData.fromBundle(android.os.Bundle):org.altbeacon.beacon.service.RangingData");
    }
}
