package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.internal.i;

public abstract class AssetPackState {
    public static AssetPackState c(String str, int i, int i2, long j, long j2, double d, int i3, String str2) {
        return new az(str, i, i2, j, j2, (int) Math.rint(100.0d * d), i3, str2);
    }

    static AssetPackState d(Bundle bundle, String str, bo boVar, as asVar) {
        Bundle bundle2 = bundle;
        String str2 = str;
        int a2 = asVar.a(bundle2.getInt(i.e("status", str2)), str2);
        int i = bundle2.getInt(i.e("error_code", str2));
        long j = bundle2.getLong(i.e("bytes_downloaded", str2));
        long j2 = bundle2.getLong(i.e("total_bytes_to_download", str2));
        double b = boVar.b(str2);
        long j3 = bundle2.getLong(i.e("pack_version", str2));
        long j4 = bundle2.getLong(i.e("pack_base_version", str2));
        int i2 = 1;
        if (!(a2 != 4 || j4 == 0 || j4 == j3)) {
            i2 = 2;
        }
        return c(str, a2, i, j, j2, b, i2, bundle2.getString(i.e("pack_version_tag", str2), ""));
    }

    public abstract int a();

    public abstract String b();

    public abstract long bytesDownloaded();

    public abstract int errorCode();

    public abstract String name();

    public abstract int status();

    public abstract long totalBytesToDownload();

    public abstract int transferProgressPercentage();
}
