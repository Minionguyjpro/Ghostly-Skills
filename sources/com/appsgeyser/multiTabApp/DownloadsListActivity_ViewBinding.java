package com.appsgeyser.multiTabApp;

import android.view.View;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wGhostlySkills_14510784.R;

public class DownloadsListActivity_ViewBinding implements Unbinder {
    private DownloadsListActivity target;

    public DownloadsListActivity_ViewBinding(DownloadsListActivity downloadsListActivity, View view) {
        this.target = downloadsListActivity;
        downloadsListActivity.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, R.id.downloads_toolbar, "field 'toolbar'", Toolbar.class);
    }
}
