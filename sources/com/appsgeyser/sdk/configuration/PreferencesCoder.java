package com.appsgeyser.sdk.configuration;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesCoder {
    private final SharedPreferences prefsSettings;

    public PreferencesCoder(Context context) {
        this.prefsSettings = context.getSharedPreferences("AppsgeyserPrefs", 0);
    }

    public String getPrefString(String str, String str2) {
        return this.prefsSettings.getString(str, str2);
    }

    public void savePrefString(String str, String str2) {
        SharedPreferences.Editor edit = this.prefsSettings.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public int getPrefInt(String str, int i) {
        return this.prefsSettings.getInt(str, i);
    }

    public void savePrefInt(String str, int i) {
        SharedPreferences.Editor edit = this.prefsSettings.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public long getPrefLong(String str, long j) {
        return this.prefsSettings.getLong(str, j);
    }

    public void savePrefLong(String str, long j) {
        SharedPreferences.Editor edit = this.prefsSettings.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public boolean getPrefBoolean(String str, boolean z) {
        return this.prefsSettings.getBoolean(str, z);
    }

    public void savePrefBoolean(String str, boolean z) {
        SharedPreferences.Editor edit = this.prefsSettings.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }
}
