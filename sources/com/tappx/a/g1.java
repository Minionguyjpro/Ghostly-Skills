package com.tappx.a;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public final class g1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f445a;
    public final a b;

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final double f446a;
        public final double b;
        public final long c;
        public final long d;

        public a(double d2, double d3, long j, long j2) {
            this.f446a = d2;
            this.b = d3;
            this.c = j;
            this.d = j2;
        }
    }

    public g1(String str, a aVar) {
        this.f445a = str;
        this.b = aVar;
    }

    public static class b {
        private static volatile b b;

        /* renamed from: a  reason: collision with root package name */
        private final Context f447a;

        public b(Context context) {
            this.f447a = context;
        }

        public static final b a(Context context) {
            if (b == null) {
                synchronized (b.class) {
                    if (b == null) {
                        b = new b(context);
                    }
                }
            }
            return b;
        }

        private a b() {
            Location d = d();
            if (d == null) {
                return null;
            }
            return new a(d.getLatitude(), d.getLongitude(), (long) d.getAccuracy(), System.currentTimeMillis() - d.getTime());
        }

        private Location c() {
            if (!d3.a(this.f447a, "android.permission.ACCESS_FINE_LOCATION")) {
                return null;
            }
            return a("gps");
        }

        private Location d() {
            return a(e(), c());
        }

        private Location e() {
            if (!(d3.a(this.f447a, "android.permission.ACCESS_FINE_LOCATION") || d3.a(this.f447a, "android.permission.ACCESS_COARSE_LOCATION"))) {
                return null;
            }
            return a("network");
        }

        private String f() {
            return new SimpleDateFormat("Z", Locale.US).format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime());
        }

        public g1 a() {
            return new g1(f(), b());
        }

        private Location a(String str) {
            try {
                return ((LocationManager) this.f447a.getSystemService("location")).getLastKnownLocation(str);
            } catch (IllegalArgumentException | SecurityException e) {
                e.printStackTrace();
                return null;
            }
        }

        private Location a(Location location, Location location2) {
            if (location == null) {
                return location2;
            }
            return (location2 != null && location.getTime() <= location2.getTime()) ? location2 : location;
        }
    }
}
