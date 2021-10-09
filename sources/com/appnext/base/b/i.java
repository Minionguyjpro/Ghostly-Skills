package com.appnext.base.b;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;

public final class i {
    private static final String TAG = "LibrarySettings";
    public static final String fA = "_cycles";
    public static final String fB = "google_ads_id";
    public static final String fC = "lat";
    public static final String fD = "lib_shared_preferences";
    private static final i fE = new i();
    public static final String fy = "_lastupdate";
    public static final String fz = "_lastcollectedtime";
    private SharedPreferences fx;
    private Context mContext;

    private i() {
        Context context = e.getContext();
        this.mContext = context;
        if (context != null) {
            this.fx = context.getSharedPreferences(TAG, 0);
        }
    }

    public static i aR() {
        return fE;
    }

    public final void clear() {
        this.fx.edit().clear().apply();
    }

    public final void init(Context context) {
        if (context != null) {
            this.mContext = context;
            this.fx = context.getSharedPreferences(fD, 0);
        }
    }

    public final String getString(String str, String str2) {
        SharedPreferences sharedPreferences = this.fx;
        return sharedPreferences != null ? sharedPreferences.getString(str, str2) : str2;
    }

    public final long getLong(String str, long j) {
        SharedPreferences sharedPreferences = this.fx;
        return sharedPreferences != null ? sharedPreferences.getLong(str, j) : j;
    }

    public final int getInt(String str, int i) {
        SharedPreferences sharedPreferences = this.fx;
        if (sharedPreferences != null) {
            return sharedPreferences.getInt(str, 0);
        }
        return 0;
    }

    public final boolean getBoolean(String str, boolean z) {
        SharedPreferences sharedPreferences = this.fx;
        return sharedPreferences != null ? sharedPreferences.getBoolean(str, z) : z;
    }

    public final void putLong(String str, long j) {
        SharedPreferences sharedPreferences = this.fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(str, j).apply();
        }
    }

    public final void putInt(String str, int i) {
        SharedPreferences sharedPreferences = this.fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(str, i).apply();
        }
    }

    public final void putBoolean(String str, boolean z) {
        SharedPreferences sharedPreferences = this.fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(str, true).apply();
        }
    }

    public final void putString(String str, String str2) {
        SharedPreferences sharedPreferences = this.fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(str, str2).apply();
        }
    }

    public final Set<String> getStringSet(String str, Set<String> set) {
        SharedPreferences sharedPreferences = this.fx;
        return sharedPreferences != null ? sharedPreferences.getStringSet(str, set) : set;
    }

    public final void putStringSet(String str, Set<String> set) {
        SharedPreferences sharedPreferences = this.fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(str);
            this.fx.edit().putStringSet(str, set).apply();
        }
    }

    private SharedPreferences aS() {
        return this.mContext.getSharedPreferences(TAG, 0);
    }
}
