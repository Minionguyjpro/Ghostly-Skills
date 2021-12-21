package com.appsgeyser.multiTabApp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.wGhostlySkills_14510784.R;

public class ThemeUtils {
    public static String getActivityThemeName(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.themeName, typedValue, true);
        return typedValue.string.toString();
    }

    public static void setCurrentThemeWithActionBar(Activity activity) {
        String string = PreferenceManager.getDefaultSharedPreferences(activity).getString("AppThemeName", "AppThemeDefault");
        if (string.contains("NoActionBar")) {
            string = string.replace("NoActionBar", "");
        }
        activity.setTheme(ThemePreset.findByName(string).themeId);
    }

    public static void setCurrentThemeWithNoActionBar(Activity activity) {
        String string = PreferenceManager.getDefaultSharedPreferences(activity).getString("AppThemeName", "AppThemeDefaultNoActionBar");
        if (!string.contains("NoActionBar")) {
            string = string + "NoActionBar";
        }
        activity.setTheme(ThemePreset.findByNoActionBarName(string).themeNoActionBarId);
    }

    public static void initializeAppTheme(Activity activity, WebWidgetConfiguration webWidgetConfiguration) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        if (webWidgetConfiguration.getApplicationTheme() == WebWidgetConfiguration.ApplicationThemes.ACTION_BAR) {
            String string = defaultSharedPreferences.getString("AppThemeName", "AppThemeDefaultNoActionBar");
            if (string.equals("AppThemeDefaultNoActionBar")) {
                SharedPreferences.Editor edit = defaultSharedPreferences.edit();
                edit.putString("AppThemeName", "AppThemeDefaultNoActionBar");
                edit.apply();
            }
            if (!getActivityThemeName(activity).equals(string)) {
                activity.setTheme(ThemePreset.findByNoActionBarName(string).themeNoActionBarId);
            }
        }
    }
}
