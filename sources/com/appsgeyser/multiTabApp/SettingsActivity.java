package com.appsgeyser.multiTabApp;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.appsgeyser.multiTabApp.controllers.ITabContentController;
import com.appsgeyser.multiTabApp.utils.AppCompatPreferenceActivity;
import com.appsgeyser.multiTabApp.utils.ThemeUtils;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.wGhostlySkills_14510784.R;

public class SettingsActivity extends AppCompatPreferenceActivity {
    private SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
    /* access modifiers changed from: private */
    public SharedPreferences settings;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ThemeUtils.setCurrentThemeWithNoActionBar(this);
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preferences);
        this.settings = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Factory.getInstance().getMainNavigationActivity().getConfig();
        final Preference findPreference = findPreference("time_from");
        final Preference findPreference2 = findPreference("time_to");
        Preference findPreference3 = findPreference("notifications_category");
        final PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("about_category");
        Preference findPreference4 = findPreference("show_quick_access_bar");
        PreferenceCategory preferenceCategory2 = (PreferenceCategory) findPreference("general_category");
        preferenceScreen.removePreference(findPreference3);
        getLayoutInflater().inflate(R.layout.settings_toolbar, (ViewGroup) findViewById(16908290));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_preference));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setElevation(0.0f);
        }
        int applyDimension = (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, (float) (((int) getResources().getDimension(R.dimen.activity_vertical_margin)) + 30), getResources().getDisplayMetrics());
        getListView().setPadding(applyDimension, applyDimension2, applyDimension, (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        AnonymousClass1 r7 = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                ITabContentController webContentController;
                if ((str.equals("hide_url_bar") || str.equals("hide_tab_bar")) && (webContentController = Factory.getInstance().getWebContentController()) != null) {
                    webContentController.showNavigationBars();
                }
                if (!str.equals("show_quick_access_bar")) {
                    return;
                }
                if (SettingsActivity.this.settings.getBoolean(str, true)) {
                    Factory.getInstance().getMainNavigationActivity().createNotice();
                } else {
                    Factory.getInstance().getMainNavigationActivity().deleteNotice();
                }
            }
        };
        this.onSharedPreferenceChangeListener = r7;
        this.settings.registerOnSharedPreferenceChangeListener(r7);
        findPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object obj) {
                findPreference.setSummary(obj.toString());
                return false;
            }
        });
        findPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object obj) {
                findPreference2.setSummary(obj.toString());
                return false;
            }
        });
        final Preference findPreference5 = findPreference("about_preference");
        findPreference5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                AppsgeyserSDK.showAboutDialog(SettingsActivity.this);
                return false;
            }
        });
        AppsgeyserSDK.isAboutDialogEnabled(this, new AppsgeyserSDK.OnAboutDialogEnableListener() {
            public void onDialogEnableReceived(boolean z) {
                if (z) {
                    preferenceCategory.addPreference(findPreference5);
                } else {
                    preferenceCategory.removePreference(findPreference5);
                }
            }
        });
        String str = null;
        try {
            str = getString(R.string.app_version, new Object[]{getPackageManager().getPackageInfo(getPackageName(), 0).versionName, "2.23.s"});
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        findPreference("build_version_preference").setSummary(str);
        preferenceScreen.removePreference(findPreference3);
        if (Factory.getInstance().getNavigationWidget() != null) {
            boolean z = this.settings.getBoolean("show_quick_access_bar", true);
            if (!findPreference4.isEnabled() && z) {
                preferenceCategory2.addPreference(findPreference4);
            }
            if (!Factory.getInstance().getMainNavigationActivity().getConfig().getShowSearchNotice()) {
                findPreference4.setDefaultValue(false);
            }
        } else if (!Factory.getInstance().getMainNavigationActivity().getConfig().getShowSearchNotice() || Factory.getInstance().getMainNavigationActivity().getConfig().getUrlOverlayState() != WebWidgetConfiguration.UrlBarStates.ENABLED) {
            preferenceCategory2.removePreference(findPreference4);
            if (preferenceCategory2.getPreferenceCount() == 0) {
                preferenceScreen.removePreference(preferenceCategory2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        AppsgeyserSDK.onPause(this);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        finish();
        return true;
    }
}
