package com.google.android.youtube.player.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;

public final class z {

    /* renamed from: a  reason: collision with root package name */
    private static final Uri f1145a = Uri.parse("http://play.google.com/store/apps/details");
    private static final String[] b = {"com.google.android.youtube", "com.google.android.youtube.tv", "com.google.android.youtube.googletv", "com.google.android.gms", null};

    public static String a() {
        StringBuilder sb = new StringBuilder(35);
        sb.append(1);
        sb.append(".2.2");
        return sb.toString();
    }

    public static String d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Cannot retrieve calling Context's PackageInfo", e);
        }
    }
}
