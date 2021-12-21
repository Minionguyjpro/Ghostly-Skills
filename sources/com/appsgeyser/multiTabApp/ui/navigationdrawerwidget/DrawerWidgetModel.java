package com.appsgeyser.multiTabApp.ui.navigationdrawerwidget;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;

public class DrawerWidgetModel {
    private String description;
    private final Drawable imageDrawable;
    private boolean isDivider;
    /* access modifiers changed from: private */
    public final int menuId;

    public DrawerWidgetModel(Drawable drawable, int i, String str) {
        this.imageDrawable = drawable;
        this.menuId = i;
        this.description = str;
    }

    /* access modifiers changed from: package-private */
    public Drawable getImageDrawable() {
        return this.imageDrawable;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDivider() {
        return this.isDivider;
    }

    public void setDivider(boolean z) {
        this.isDivider = z;
    }

    /* access modifiers changed from: package-private */
    public int getMenuId() {
        return this.menuId;
    }

    public void select() {
        final MainNavigationActivity mainNavigationActivity = Factory.getInstance().getMainNavigationActivity();
        if (mainNavigationActivity != null) {
            mainNavigationActivity.runOnUiThread(new Runnable() {
                public void run() {
                    mainNavigationActivity.onOptionsItemSelected(DrawerWidgetModel.this.menuId, (MenuItem) null);
                }
            });
        }
    }
}
