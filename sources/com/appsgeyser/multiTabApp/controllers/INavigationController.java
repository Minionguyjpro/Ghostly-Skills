package com.appsgeyser.multiTabApp.controllers;

import android.net.Uri;

public interface INavigationController {

    public interface OnTabsControllerReady {
        void tabsControllerReady(boolean z);
    }

    String findTabIdToOpenFromDeepLink(Uri uri);

    void firstBannerWasShown();

    WebContentController getSelectedTab();

    int getSelectedTabId();

    void initWithTabs(WidgetsController widgetsController);

    void isTabsControllerReady(OnTabsControllerReady onTabsControllerReady);

    boolean onBackKeyDown();

    void onPause();

    void onResume();

    void setSwipeEnabled(boolean z);

    void swipeOnPage(int i, boolean z);

    void swipeOnPageByTabId(String str);
}
