package com.appsgeyser.multiTabApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.appsgeyser.multiTabApp.ui.adapters.ThemingAdapter;
import com.appsgeyser.multiTabApp.utils.ThemePreset;
import com.appsgeyser.multiTabApp.utils.ThemeUtils;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.wGhostlySkills_14510784.R;

public class ThemingActivity extends AppCompatActivity {
    @BindString
    String cancelString;
    @BindString
    String noAvailableVideoString;
    @BindString
    String noInternetConnectionString;
    @BindString
    String okString;
    @BindView
    RecyclerView presetsRecycler;
    @BindString
    String rewardedVideoThemingString;
    private ThemingAdapter themingAdapter;
    private LinearLayoutManager themingLinearLayoutManager;
    @BindView
    Toolbar toolbar;

    public static Intent newThemingIntent(Context context, WebWidgetConfiguration webWidgetConfiguration, Parcelable parcelable) {
        Intent intent = new Intent(context, ThemingActivity.class);
        intent.putExtra("configuration", webWidgetConfiguration);
        intent.putExtra("layout_manager_state", parcelable);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ThemeUtils.setCurrentThemeWithNoActionBar(this);
        setContentView((int) R.layout.theming);
        ButterKnife.bind(this);
        this.toolbar.setTitle((int) R.string.theming);
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setElevation(0.0f);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.themingLinearLayoutManager = linearLayoutManager;
        this.presetsRecycler.setLayoutManager(linearLayoutManager);
        ThemingAdapter themingAdapter2 = new ThemingAdapter(ThemePreset.values(), this, new ThemingAdapter.ViewHolder.ViewHolderRadioButtonClicks() {
            public void onRadioButtonClicked(int i) {
                if (!ThemePreset.values()[i].equals(ThemePreset.findByNoActionBarName(ThemeUtils.getActivityThemeName(ThemingActivity.this)))) {
                    ThemingActivity.this.changeThemeInPrefsByAdapterPosition(i, true);
                }
            }
        });
        this.themingAdapter = themingAdapter2;
        this.presetsRecycler.setAdapter(themingAdapter2);
        Parcelable parcelableExtra = getIntent().getParcelableExtra("layout_manager_state");
        if (parcelableExtra != null) {
            this.themingLinearLayoutManager.onRestoreInstanceState(parcelableExtra);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
        String string = PreferenceManager.getDefaultSharedPreferences(this).getString("AppThemeName", "AppThemeDefaultNoActionBar");
        if (!string.contains("NoActionBar")) {
            string = string + "NoActionBar";
        }
        if (!ThemeUtils.getActivityThemeName(this).equals(string)) {
            Intent newThemingIntent = newThemingIntent(this, (WebWidgetConfiguration) getIntent().getSerializableExtra("configuration"), this.themingLinearLayoutManager.onSaveInstanceState());
            finish();
            startActivity(newThemingIntent);
        }
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

    /* access modifiers changed from: private */
    public void changeThemeInPrefsByAdapterPosition(int i, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        if (((WebWidgetConfiguration) getIntent().getSerializableExtra("configuration")).getApplicationTheme() == WebWidgetConfiguration.ApplicationThemes.ACTION_BAR) {
            edit.putString("AppThemeName", ThemePreset.values()[i].themeNoActionBarName);
        } else {
            edit.putString("AppThemeName", ThemePreset.values()[i].themeName);
        }
        edit.apply();
        if (z) {
            recreate();
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        recreate();
    }
}
