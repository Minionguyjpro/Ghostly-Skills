package com.appsgeyser.multiTabApp.ui.navigationdrawerwidget;

import android.widget.AdapterView;
import android.widget.ListView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.controllers.INavigationController;

class NavigationDrawerWidgetClickListener implements AdapterView.OnItemClickListener {
    private final MainNavigationActivity activity;
    private final ListView drawerListView;
    private final DrawerLayout navigationDrawer;
    private final INavigationController tabsController;

    NavigationDrawerWidgetClickListener(INavigationController iNavigationController, DrawerLayout drawerLayout, ListView listView, MainNavigationActivity mainNavigationActivity) {
        this.tabsController = iNavigationController;
        this.navigationDrawer = drawerLayout;
        this.drawerListView = listView;
        this.activity = mainNavigationActivity;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
        /*
            r0 = this;
            android.widget.Adapter r1 = r1.getAdapter()
            java.lang.Object r1 = r1.getItem(r3)
            com.appsgeyser.multiTabApp.ui.navigationdrawerwidget.DrawerWidgetModel r1 = (com.appsgeyser.multiTabApp.ui.navigationdrawerwidget.DrawerWidgetModel) r1
            int r2 = r1.getMenuId()
            if (r2 == 0) goto L_0x0014
            r1.select()
            goto L_0x0022
        L_0x0014:
            android.widget.ListView r1 = r0.drawerListView
            com.appsgeyser.multiTabApp.MainNavigationActivity r2 = r0.activity
            com.appsgeyser.multiTabApp.ui.navigationdrawerwidget.SelectItemController.selectItem(r1, r3, r2)
            com.appsgeyser.multiTabApp.controllers.INavigationController r1 = r0.tabsController
            int r2 = (int) r4
            r3 = 0
            r1.swipeOnPage(r2, r3)
        L_0x0022:
            androidx.drawerlayout.widget.DrawerLayout r1 = r0.navigationDrawer
            r1.closeDrawers()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.multiTabApp.ui.navigationdrawerwidget.NavigationDrawerWidgetClickListener.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
    }
}
