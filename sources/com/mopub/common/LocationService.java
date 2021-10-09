package com.mopub.common;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.SystemClock;
import com.mopub.common.MoPub;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import java.math.BigDecimal;

public class LocationService {
    private static final int DEFAULT_LOCATION_PRECISION = 6;
    private static final long DEFAULT_LOCATION_REFRESH_TIME_MILLIS = 600000;
    private static volatile LocationService sInstance;
    Location mLastKnownLocation;
    private volatile MoPub.LocationAwareness mLocationAwareness = MoPub.LocationAwareness.NORMAL;
    long mLocationLastUpdatedMillis;
    private volatile int mLocationPrecision = 6;
    private volatile long mMinimumLocationRefreshTimeMillis = DEFAULT_LOCATION_REFRESH_TIME_MILLIS;

    public enum LocationAwareness {
        NORMAL,
        TRUNCATED,
        DISABLED;

        @Deprecated
        public MoPub.LocationAwareness getNewLocationAwareness() {
            if (this == TRUNCATED) {
                return MoPub.LocationAwareness.TRUNCATED;
            }
            if (this == DISABLED) {
                return MoPub.LocationAwareness.DISABLED;
            }
            return MoPub.LocationAwareness.NORMAL;
        }

        @Deprecated
        public static LocationAwareness fromMoPubLocationAwareness(MoPub.LocationAwareness locationAwareness) {
            if (locationAwareness == MoPub.LocationAwareness.DISABLED) {
                return DISABLED;
            }
            if (locationAwareness == MoPub.LocationAwareness.TRUNCATED) {
                return TRUNCATED;
            }
            return NORMAL;
        }
    }

    private LocationService() {
    }

    static LocationService getInstance() {
        LocationService locationService = sInstance;
        if (locationService == null) {
            synchronized (LocationService.class) {
                locationService = sInstance;
                if (locationService == null) {
                    locationService = new LocationService();
                    sInstance = locationService;
                }
            }
        }
        return locationService;
    }

    public enum ValidLocationProvider {
        NETWORK("network"),
        GPS("gps");
        
        final String name;

        private ValidLocationProvider(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }

        /* access modifiers changed from: private */
        public boolean hasRequiredPermissions(Context context) {
            int i = AnonymousClass1.$SwitchMap$com$mopub$common$LocationService$ValidLocationProvider[ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return false;
                }
                return DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION");
            } else if (DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /* renamed from: com.mopub.common.LocationService$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$common$LocationService$ValidLocationProvider;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.mopub.common.LocationService$ValidLocationProvider[] r0 = com.mopub.common.LocationService.ValidLocationProvider.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$common$LocationService$ValidLocationProvider = r0
                com.mopub.common.LocationService$ValidLocationProvider r1 = com.mopub.common.LocationService.ValidLocationProvider.NETWORK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$common$LocationService$ValidLocationProvider     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.common.LocationService$ValidLocationProvider r1 = com.mopub.common.LocationService.ValidLocationProvider.GPS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.LocationService.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public MoPub.LocationAwareness getLocationAwareness() {
        return this.mLocationAwareness;
    }

    /* access modifiers changed from: package-private */
    public void setLocationAwareness(MoPub.LocationAwareness locationAwareness) {
        Preconditions.checkNotNull(locationAwareness);
        this.mLocationAwareness = locationAwareness;
    }

    /* access modifiers changed from: package-private */
    public int getLocationPrecision() {
        return this.mLocationPrecision;
    }

    /* access modifiers changed from: package-private */
    public void setLocationPrecision(int i) {
        this.mLocationPrecision = Math.min(Math.max(0, i), 6);
    }

    /* access modifiers changed from: package-private */
    public void setMinimumLocationRefreshTimeMillis(long j) {
        this.mMinimumLocationRefreshTimeMillis = j;
    }

    /* access modifiers changed from: package-private */
    public long getMinimumLocationRefreshTimeMillis() {
        return this.mMinimumLocationRefreshTimeMillis;
    }

    public static Location getLastKnownLocation(Context context) {
        if (!MoPub.canCollectPersonalInformation()) {
            return null;
        }
        Preconditions.checkNotNull(context);
        LocationService instance = getInstance();
        MoPub.LocationAwareness locationAwareness = instance.mLocationAwareness;
        int i = instance.mLocationPrecision;
        if (locationAwareness == MoPub.LocationAwareness.DISABLED) {
            return null;
        }
        if (isLocationFreshEnough()) {
            return instance.mLastKnownLocation;
        }
        Location locationFromProvider = getLocationFromProvider(context, ValidLocationProvider.GPS);
        if (locationFromProvider == null) {
            locationFromProvider = getLocationFromProvider(context, ValidLocationProvider.NETWORK);
        }
        if (locationAwareness == MoPub.LocationAwareness.TRUNCATED) {
            truncateLocationLatLon(locationFromProvider, i);
        }
        if (locationFromProvider != null) {
            instance.setLastLocation(locationFromProvider);
        }
        return instance.mLastKnownLocation;
    }

    static Location getLocationFromProvider(Context context, ValidLocationProvider validLocationProvider) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(validLocationProvider);
        if (!MoPub.canCollectPersonalInformation() || !validLocationProvider.hasRequiredPermissions(context)) {
            return null;
        }
        try {
            return ((LocationManager) context.getSystemService("location")).getLastKnownLocation(validLocationProvider.toString());
        } catch (SecurityException unused) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Failed to retrieve location from " + validLocationProvider.toString() + " provider: access appears to be disabled.");
            return null;
        } catch (IllegalArgumentException unused2) {
            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent2, "Failed to retrieve location: device has no " + validLocationProvider.toString() + " location provider.");
            return null;
        } catch (NullPointerException unused3) {
            MoPubLog.SdkLogEvent sdkLogEvent3 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent3, "Failed to retrieve location: device has no " + validLocationProvider.toString() + " location provider.");
            return null;
        }
    }

    static Location getMostRecentValidLocation(Location location, Location location2) {
        if (location == null) {
            return location2;
        }
        return (location2 != null && location.getTime() <= location2.getTime()) ? location2 : location;
    }

    static void truncateLocationLatLon(Location location, int i) {
        if (location != null && i >= 0) {
            location.setLatitude(BigDecimal.valueOf(location.getLatitude()).setScale(i, 5).doubleValue());
            location.setLongitude(BigDecimal.valueOf(location.getLongitude()).setScale(i, 5).doubleValue());
        }
    }

    private static boolean isLocationFreshEnough() {
        LocationService instance = getInstance();
        if (instance.mLastKnownLocation != null && SystemClock.elapsedRealtime() - instance.mLocationLastUpdatedMillis <= instance.getMinimumLocationRefreshTimeMillis()) {
            return true;
        }
        return false;
    }

    @Deprecated
    public static void clearLastKnownLocation() {
        getInstance().mLastKnownLocation = null;
    }

    /* access modifiers changed from: package-private */
    public void setLastLocation(Location location) {
        if (location != null) {
            LocationService instance = getInstance();
            instance.mLastKnownLocation = location;
            instance.mLocationLastUpdatedMillis = SystemClock.elapsedRealtime();
        }
    }
}
