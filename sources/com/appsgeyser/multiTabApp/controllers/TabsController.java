package com.appsgeyser.multiTabApp.controllers;

import android.net.Uri;
import android.util.Log;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.controllers.INavigationController;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import com.appsgeyser.multiTabApp.ui.views.TabContent;
import com.appsgeyser.multiTabApp.ui.views.TabFragment;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.google.android.material.tabs.TabLayout;
import com.wGhostlySkills_14510784.R;
import java.io.PrintStream;
import java.util.Enumeration;

public class TabsController implements INavigationController {
    private boolean firstBannerWasShown = false;
    private final ViewPager.SimpleOnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        public void onPageSelected(int i) {
            int unused = TabsController.this.selectedPosition = i;
            WebContentController webContentController = (WebContentController) Factory.getInstance().getWebContentController();
            if (webContentController != null) {
                webContentController.showBanner(MainNavigationActivity.UserEvent.TAB_CHANGED, true);
            }
        }
    };
    private INavigationController.OnTabsControllerReady onTabsControllerReadyListener;
    /* access modifiers changed from: private */
    public int selectedPosition = 0;
    private TabsAdapter tabsAdapter;
    private ContentSwipeAwareViewPager viewPager;

    public void initWithTabs(WidgetsController widgetsController) {
        try {
            MainNavigationActivity mainNavigationActivity = Factory.getInstance().getMainNavigationActivity();
            ContentSwipeAwareViewPager contentSwipeAwareViewPager = (ContentSwipeAwareViewPager) mainNavigationActivity.findViewById(R.id.tabcontents_panel);
            this.viewPager = contentSwipeAwareViewPager;
            contentSwipeAwareViewPager.setOffscreenPageLimit(10);
            final TabLayout tabLayout = (TabLayout) mainNavigationActivity.findViewById(R.id.tabtags_panel);
            this.viewPager.addOnPageChangeListener(this.onPageChangeListener);
            if (widgetsController.tabsCount() < 2) {
                tabLayout.setVisibility(8);
                createTabsAdapterAndSetupTagsPanel(false, tabLayout);
                return;
            }
            AppsgeyserSDK.isOfferWallEnabled(this.viewPager.getContext(), new AppsgeyserSDK.OfferWallEnabledListener() {
                public void isOfferWallEnabled(boolean z) {
                    TabsController.this.createTabsAdapterAndSetupTagsPanel(z, tabLayout);
                }
            });
        } catch (Exception e) {
            Log.e("initWithTabs Error", e.getMessage());
            PrintStream printStream = System.out;
            printStream.println("initWithTabs Error" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void createTabsAdapterAndSetupTagsPanel(boolean z, TabLayout tabLayout) {
        TabsAdapter tabsAdapter2 = new TabsAdapter(z);
        this.tabsAdapter = tabsAdapter2;
        this.viewPager.setAdapter(tabsAdapter2);
        this.selectedPosition = this.viewPager.getCurrentItem();
        INavigationController.OnTabsControllerReady onTabsControllerReady = this.onTabsControllerReadyListener;
        if (onTabsControllerReady != null) {
            onTabsControllerReady.tabsControllerReady(true);
        }
        tabLayout.setupWithViewPager(this.viewPager);
        ViewCompat.setElevation(tabLayout, 16.0f);
    }

    public WebContentController getSelectedTab() {
        try {
            Fragment fragmentByPosition = this.tabsAdapter.getFragmentByPosition(this.selectedPosition);
            if (fragmentByPosition == null || !(fragmentByPosition instanceof TabFragment)) {
                return null;
            }
            return (WebContentController) ((TabFragment) fragmentByPosition).getContentController();
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public void isTabsControllerReady(INavigationController.OnTabsControllerReady onTabsControllerReady) {
        if (this.viewPager.getAdapter() != null) {
            onTabsControllerReady.tabsControllerReady(true);
        } else {
            this.onTabsControllerReadyListener = onTabsControllerReady;
        }
    }

    public void onPause() {
        WebContentController selectedTab = getSelectedTab();
        if (selectedTab != null && selectedTab.getWebView() != null) {
            selectedTab.getWebView().onPause();
        }
    }

    public void onResume() {
        WebContentController selectedTab = getSelectedTab();
        if (selectedTab != null && selectedTab.getWebView() != null) {
            selectedTab.getWebView().onResume();
        }
    }

    public void firstBannerWasShown() {
        this.firstBannerWasShown = true;
    }

    public boolean onBackKeyDown() {
        WebContentController selectedTab = getSelectedTab();
        if (selectedTab == null || selectedTab.getWidgetInfo().getTabType().equals(TabContent.TabType.PDF.toString()) || !selectedTab.onBackKeyDown()) {
            return false;
        }
        return true;
    }

    public void setSwipeEnabled(boolean z) {
        this.viewPager.setPagingEnabled(z);
    }

    public void swipeOnPage(int i, boolean z) {
        this.viewPager.setCurrentItem(i, z);
    }

    public void swipeOnPageByTabId(String str) {
        swipeOnPage(Factory.getInstance().getWidgetsController().getWidgetPositionByTabId(str), true);
    }

    public String findTabIdToOpenFromDeepLink(Uri uri) {
        Enumeration<WidgetEntity> enumeration = Factory.getInstance().getWidgetsController().getEnumeration();
        String str = "";
        while (enumeration.hasMoreElements()) {
            WidgetEntity nextElement = enumeration.nextElement();
            if (nextElement != null) {
                String hostFromLink = nextElement.getHostFromLink();
                String pathFromLink = nextElement.getPathFromLink();
                if (hostFromLink != null && hostFromLink.equals(uri.getHost())) {
                    if (pathFromLink != null && pathFromLink.equals(uri.getPath())) {
                        return nextElement.getTabId();
                    }
                    str = nextElement.getTabId();
                }
            }
        }
        return str;
    }

    public int getSelectedTabId() {
        return this.selectedPosition;
    }
}
