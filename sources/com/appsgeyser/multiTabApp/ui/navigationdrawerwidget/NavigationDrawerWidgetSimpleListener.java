package com.appsgeyser.multiTabApp.ui.navigationdrawerwidget;

import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;

class NavigationDrawerWidgetSimpleListener extends DrawerLayout.SimpleDrawerListener {
    private boolean isOpened;
    private final NavigationDrawerWidget navigationDrawerWidget;

    NavigationDrawerWidgetSimpleListener(NavigationDrawerWidget navigationDrawerWidget2) {
        this.navigationDrawerWidget = navigationDrawerWidget2;
    }

    public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        this.isOpened = false;
    }

    public void onDrawerStateChanged(int i) {
        super.onDrawerStateChanged(i);
    }

    public void onDrawerOpened(View view) {
        super.onDrawerOpened(view);
        this.isOpened = true;
        this.navigationDrawerWidget.hideSliderHandle();
    }

    /* access modifiers changed from: package-private */
    public boolean isOpened() {
        return this.isOpened;
    }
}
