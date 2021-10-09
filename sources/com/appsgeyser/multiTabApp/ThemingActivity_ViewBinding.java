package com.appsgeyser.multiTabApp;

import android.content.res.Resources;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wGhostlySkills_14510784.R;

public class ThemingActivity_ViewBinding implements Unbinder {
    private ThemingActivity target;

    public ThemingActivity_ViewBinding(ThemingActivity themingActivity, View view) {
        this.target = themingActivity;
        themingActivity.presetsRecycler = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.theming_recycler, "field 'presetsRecycler'", RecyclerView.class);
        themingActivity.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, R.id.theming_toolbar, "field 'toolbar'", Toolbar.class);
        Resources resources = view.getContext().getResources();
        themingActivity.noAvailableVideoString = resources.getString(R.string.noAvailableVideo);
        themingActivity.noInternetConnectionString = resources.getString(R.string.noInternetConnectionMessage);
        themingActivity.rewardedVideoThemingString = resources.getString(R.string.rewardedVideoTheming);
        themingActivity.okString = resources.getString(17039370);
        themingActivity.cancelString = resources.getString(17039360);
    }
}
