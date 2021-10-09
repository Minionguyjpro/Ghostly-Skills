package com.yandex.metrica.impl;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcel;
import com.yandex.metrica.impl.d;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cc;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public final class y implements d {

    /* renamed from: a  reason: collision with root package name */
    static final long f962a = TimeUnit.SECONDS.toMillis(300);
    static final long b = TimeUnit.SECONDS.toMillis(120);
    static final Set<String> c = new HashSet(Arrays.asList(new String[]{"gps"}));
    private static volatile y i;
    private static final Object j = new Object();
    private final Context d;
    private final HandlerThread e;
    private final LocationManager f;
    private final WeakHashMap<Object, Object> g;
    private boolean h;
    private d.a<Location> k = new d.a<>();
    private boolean l = false;
    private cc m;
    private LocationListener n = new LocationListener() {
        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onLocationChanged(Location location) {
            y.this.a(location);
        }
    };

    private y(Context context) {
        this.d = context;
        this.g = new WeakHashMap<>();
        this.h = false;
        HandlerThread handlerThread = new HandlerThread("LHandlerThread");
        this.e = handlerThread;
        handlerThread.start();
        this.f = (LocationManager) context.getSystemService("location");
        cc ccVar = new cc(bp.a(this.d).b());
        this.m = ccVar;
        this.l = ccVar.c();
    }

    public static y a(Context context) {
        if (i == null) {
            synchronized (j) {
                if (i == null) {
                    i = new y(context.getApplicationContext());
                }
            }
        }
        return i;
    }

    public synchronized void a(Object obj) {
        if (this.l && al.a(this.d, "android.permission.ACCESS_COARSE_LOCATION")) {
            this.g.put(obj, (Object) null);
            if (!this.h) {
                this.h = true;
                a("network", 0.0f, f962a, this.n, this.e.getLooper());
                if (al.a(this.d, "android.permission.ACCESS_FINE_LOCATION")) {
                    a("passive", 0.0f, f962a, this.n, this.e.getLooper());
                }
            }
        }
    }

    private void a(String str, float f2, long j2, LocationListener locationListener, Looper looper) {
        try {
            if (this.f != null) {
                this.f.requestLocationUpdates(str, j2, f2, locationListener, looper);
            }
        } catch (Exception unused) {
        }
    }

    public synchronized void b(Object obj) {
        this.g.remove(obj);
        b();
    }

    /* access modifiers changed from: package-private */
    public synchronized void a() {
        this.g.clear();
        b();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (this.h && this.g.isEmpty()) {
            this.h = false;
            try {
                if (this.f != null) {
                    this.f.removeUpdates(this.n);
                }
            } catch (Exception unused) {
            }
        }
    }

    public synchronized void a(Location location) {
        Location location2;
        if (this.k.c() || a(location, this.k.a())) {
            if (location == null) {
                location2 = null;
            } else {
                location2 = new Location(location);
            }
            this.k.a(location2);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized Location c() {
        return this.k.d();
    }

    public Location d() {
        List<String> allProviders;
        Location location;
        LocationManager locationManager = this.f;
        if (locationManager == null || (allProviders = locationManager.getAllProviders()) == null) {
            return null;
        }
        boolean a2 = al.a(this.d, "android.permission.ACCESS_COARSE_LOCATION");
        boolean a3 = al.a(this.d, "android.permission.ACCESS_FINE_LOCATION");
        Location location2 = null;
        for (String next : allProviders) {
            if (!c.contains(next)) {
                if (a2) {
                    try {
                        if (!"passive".equals(next) || a3) {
                            location = this.f.getLastKnownLocation(next);
                            if (location != null && a(location, location2)) {
                                location2 = location;
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
                location = null;
                location2 = location;
            }
        }
        return location2;
    }

    static boolean a(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        if (location == null) {
            return false;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > b;
        boolean z2 = time < (-b);
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z4 = accuracy > 0;
        boolean z5 = accuracy < 0;
        boolean z6 = ((long) accuracy) > 200;
        String provider = location.getProvider();
        String provider2 = location2.getProvider();
        boolean equals = provider == null ? provider2 == null : provider.equals(provider2);
        if (z5) {
            return true;
        }
        if (!z3 || z4) {
            return z3 && !z6 && equals;
        }
        return true;
    }

    public static byte[] b(Location location) {
        Parcel obtain = Parcel.obtain();
        byte[] bArr = new byte[0];
        try {
            obtain.writeValue(location);
            bArr = obtain.marshall();
        } catch (Exception unused) {
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
        obtain.recycle();
        return bArr;
    }

    /* JADX INFO: finally extract failed */
    public static Location a(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            Location location = (Location) obtain.readValue(Location.class.getClassLoader());
            obtain.recycle();
            return location;
        } catch (Exception unused) {
            obtain.recycle();
            return null;
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    public void a(Object obj, boolean z, boolean z2) {
        if (this.l == z2) {
            return;
        }
        if (z) {
            this.l = z2;
            this.m.a(z2);
            if (this.l) {
                a(obj);
            } else {
                a();
            }
        } else if (!z2) {
            b(obj);
        }
    }
}
