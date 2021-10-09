package com.yandex.metrica.impl.interact;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import com.yandex.metrica.a;
import com.yandex.metrica.impl.am;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DeviceInfo {

    /* renamed from: a  reason: collision with root package name */
    private static volatile DeviceInfo f770a;
    public final String appPlatform = "android";
    private final Context b;
    public final String deviceRootStatus;
    public final List<String> deviceRootStatusMarkers;
    public final String deviceType;
    public String locale;
    public final String manufacturer;
    public final String model;
    public final String osVersion;
    public final String platform = "android";
    public final String platformDeviceId;
    public final float scaleFactor;
    public final int screenDpi;
    public final int screenHeight;
    public final int screenWidth;

    public static DeviceInfo getInstance(Context context) {
        if (f770a == null) {
            synchronized (DeviceInfo.class) {
                if (f770a == null) {
                    f770a = new DeviceInfo(context.getApplicationContext());
                }
            }
        }
        return f770a;
    }

    private DeviceInfo(Context context) {
        a aVar;
        this.b = context;
        this.platformDeviceId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        this.manufacturer = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.osVersion = Build.VERSION.RELEASE;
        this.screenWidth = am.a(this.b).x;
        this.screenHeight = am.a(this.b).y;
        this.screenDpi = this.b.getResources().getDisplayMetrics().densityDpi;
        this.scaleFactor = this.b.getResources().getDisplayMetrics().density;
        this.locale = am.b(this.b);
        Context context2 = this.b;
        DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
        Point a2 = am.a(context2);
        int i = a2.x;
        int i2 = a2.y;
        float f = displayMetrics.density;
        float f2 = (float) i;
        float f3 = (float) i2;
        float min = Math.min(f2 / f, f3 / f);
        float f4 = f * 160.0f;
        float f5 = f2 / f4;
        float f6 = f3 / f4;
        double sqrt = Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
        if (sqrt >= 15.0d && !context2.getPackageManager().hasSystemFeature("android.hardware.touchscreen")) {
            aVar = a.TV;
        } else if (sqrt >= 7.0d || min >= 600.0f) {
            aVar = a.TABLET;
        } else {
            aVar = a.PHONE;
        }
        this.deviceType = aVar.name().toLowerCase(Locale.US);
        this.deviceRootStatus = String.valueOf(am.a.c());
        this.deviceRootStatusMarkers = Collections.unmodifiableList(new ArrayList<String>() {
            {
                if (am.a.a()) {
                    add("Superuser.apk");
                }
                if (am.a.b()) {
                    add("su.so");
                }
            }
        });
    }

    public String getLocale() {
        String b2 = am.b(this.b);
        this.locale = b2;
        return b2;
    }
}
