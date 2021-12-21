package com.appsgeyser.multiTabApp.controllers;

import android.content.Context;
import android.content.SharedPreferences;

public class FirstLaunchController {
    private final SharedPreferences preferences;

    public FirstLaunchController(Context context) {
        this.preferences = context.getSharedPreferences("com.appsgeyser.multiTabApp.controllers.FirstLaunchController", 0);
    }

    public boolean isFirstLaunch() {
        return this.preferences.getBoolean("com.appsgeyser.multiTabApp.controllers.FirstLaunchController.isFirstLaunch", true);
    }

    public void wasTheFirstLaunch() {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putBoolean("com.appsgeyser.multiTabApp.controllers.FirstLaunchController.isFirstLaunch", false);
        edit.apply();
    }
}
