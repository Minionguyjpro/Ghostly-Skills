package com.startapp.common.a;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* compiled from: StartAppSDK */
public class f {
    public static List<Location> a(Context context, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        for (String lastKnownLocation : b(context, z, z2)) {
            try {
                LocationManager locationManager = (LocationManager) context.getSystemService("location");
                if (locationManager == null) {
                    return null;
                }
                Location lastKnownLocation2 = locationManager.getLastKnownLocation(lastKnownLocation);
                if (lastKnownLocation2 != null) {
                    arrayList.add(lastKnownLocation2);
                }
            } catch (Exception | IllegalArgumentException | SecurityException unused) {
            }
        }
        return arrayList;
    }

    public static String a(List<Location> list) {
        StringBuilder sb = new StringBuilder();
        if (list == null || list.size() <= 0) {
            return sb.toString();
        }
        for (Location next : list) {
            sb.append(next.getLongitude() + ",");
            sb.append(next.getLatitude() + ",");
            sb.append(next.getAccuracy() + ",");
            sb.append(next.getProvider() + ",");
            sb.append(next.getTime() + ";");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    private static Queue<String> b(Context context, boolean z, boolean z2) {
        LinkedList linkedList = new LinkedList();
        if (z && c.a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            linkedList.add("gps");
            linkedList.add("passive");
            linkedList.add("network");
        } else if (z2 && c.a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            linkedList.add("network");
        }
        return linkedList;
    }
}
