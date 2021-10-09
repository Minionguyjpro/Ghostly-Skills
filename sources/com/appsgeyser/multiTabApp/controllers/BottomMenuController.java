package com.appsgeyser.multiTabApp.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.controllers.INavigationController;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import com.appsgeyser.multiTabApp.ui.adapters.BottomSheetMenuAdapter;
import com.appsgeyser.multiTabApp.ui.views.TabContent;
import com.appsgeyser.multiTabApp.ui.views.TabFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wGhostlySkills_14510784.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class BottomMenuController implements INavigationController {
    private Activity activity;
    private BottomNavigationView.OnNavigationItemSelectedListener bottomMenuListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            int unused = BottomMenuController.this.selectedMenuItem = menuItem.getItemId();
            if (menuItem.getItemId() != 1) {
                BottomMenuController.this.bottomSheet.setVisibility(8);
                BottomMenuController bottomMenuController = BottomMenuController.this;
                bottomMenuController.swipeOnPageByTabId(String.valueOf(bottomMenuController.selectedMenuItem));
            } else if (BottomMenuController.this.bottomSheet.getVisibility() == 0) {
                BottomMenuController.this.bottomSheet.setVisibility(8);
            } else {
                BottomMenuController.this.bottomSheet.setVisibility(0);
            }
            BottomMenuController.this.bottomNavigationView.getMenu().findItem(BottomMenuController.this.selectedMenuItem).setChecked(true);
            return false;
        }
    };
    /* access modifiers changed from: private */
    public BottomNavigationView bottomNavigationView;
    /* access modifiers changed from: private */
    public LinearLayout bottomSheet;
    private final BottomSheetMenuAdapter.OnItemClickListener bottomSheetClickListener = new BottomSheetMenuAdapter.OnItemClickListener() {
        public void onItemClick(View view, int i, String str) {
            BottomMenuController.this.swipeOnPageByTabId(str);
        }
    };
    /* access modifiers changed from: private */
    public RecyclerView bottomSheetMenuList;
    private boolean firstBannerWasShown;
    private final ViewPager.SimpleOnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        public void onPageSelected(int i) {
            int unused = BottomMenuController.this.selectedPosition = i;
            WidgetEntity tabByPosition = Factory.getInstance().getWidgetsController().getTabByPosition(BottomMenuController.this.selectedPosition);
            BottomSheetMenuAdapter bottomSheetMenuAdapter = (BottomSheetMenuAdapter) BottomMenuController.this.bottomSheetMenuList.getAdapter();
            MenuItem findItem = BottomMenuController.this.bottomNavigationView.getMenu().findItem(Integer.valueOf(tabByPosition.getTabId()).intValue());
            if (findItem != null) {
                findItem.setChecked(true);
                if (BottomMenuController.this.bottomSheet.getVisibility() == 0) {
                    BottomMenuController.this.bottomSheet.setVisibility(8);
                }
                if (bottomSheetMenuAdapter != null) {
                    bottomSheetMenuAdapter.uncheckLastView();
                }
            } else if (bottomSheetMenuAdapter != null) {
                bottomSheetMenuAdapter.setItemChecked(tabByPosition);
                BottomMenuController.this.bottomNavigationView.getMenu().findItem(1).setChecked(true);
            }
            WebContentController webContentController = (WebContentController) Factory.getInstance().getWebContentController();
            if (webContentController != null) {
                webContentController.showBanner(MainNavigationActivity.UserEvent.TAB_CHANGED, true);
            }
        }
    };
    private INavigationController.OnTabsControllerReady onTabsControllerReadyListener;
    /* access modifiers changed from: private */
    public int selectedMenuItem;
    /* access modifiers changed from: private */
    public int selectedPosition;
    private TabsAdapter tabsAdapter;
    private ContentSwipeAwareViewPager viewPager;

    public void initWithTabs(WidgetsController widgetsController) {
        MainNavigationActivity mainNavigationActivity = Factory.getInstance().getMainNavigationActivity();
        this.activity = mainNavigationActivity;
        ContentSwipeAwareViewPager contentSwipeAwareViewPager = (ContentSwipeAwareViewPager) mainNavigationActivity.findViewById(R.id.tabcontents_panel);
        this.viewPager = contentSwipeAwareViewPager;
        contentSwipeAwareViewPager.setOffscreenPageLimit(10);
        BottomNavigationView bottomNavigationView2 = (BottomNavigationView) this.activity.findViewById(R.id.bottom_navigation);
        this.bottomNavigationView = bottomNavigationView2;
        bottomNavigationView2.setItemIconTintList((ColorStateList) null);
        LinearLayout linearLayout = (LinearLayout) this.activity.findViewById(R.id.bottom_sheet);
        this.bottomSheet = linearLayout;
        linearLayout.setVisibility(8);
        RecyclerView recyclerView = (RecyclerView) this.activity.findViewById(R.id.bottom_sheet_menu_list);
        this.bottomSheetMenuList = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.activity));
        widgetsController.sortCollectionByShowingTabs();
        inflateMenu(widgetsController.getWidgetCollection());
        this.bottomNavigationView.setOnNavigationItemSelectedListener(this.bottomMenuListener);
        this.viewPager.addOnPageChangeListener(this.onPageChangeListener);
        TabsAdapter tabsAdapter2 = new TabsAdapter(false);
        this.tabsAdapter = tabsAdapter2;
        this.viewPager.setAdapter(tabsAdapter2);
        this.selectedPosition = this.viewPager.getCurrentItem();
        INavigationController.OnTabsControllerReady onTabsControllerReady = this.onTabsControllerReadyListener;
        if (onTabsControllerReady != null) {
            onTabsControllerReady.tabsControllerReady(true);
        }
    }

    private void inflateMenu(Vector<WidgetEntity> vector) {
        Menu menu = this.bottomNavigationView.getMenu();
        ArrayList arrayList = new ArrayList();
        ArrayList<WidgetEntity> arrayList2 = new ArrayList<>();
        Iterator<WidgetEntity> it = vector.iterator();
        while (it.hasNext()) {
            WidgetEntity next = it.next();
            if (next.isShowAsTab()) {
                arrayList2.add(next);
            } else {
                arrayList.add(next);
            }
        }
        if (vector.size() == 5 && arrayList2.size() == 5) {
            for (WidgetEntity widgetEntity : arrayList2) {
                String tabIcon = widgetEntity.getTabIcon();
                MenuItem add = menu.add(0, Integer.valueOf(widgetEntity.getTabId()).intValue(), 0, widgetEntity.getTabName());
                if (tabIcon != null) {
                    Activity activity2 = this.activity;
                    Drawable drawableIconFromAssets = getDrawableIconFromAssets(activity2, "tabIcons/" + tabIcon);
                    if (drawableIconFromAssets != null) {
                        add.setIcon(drawableIconFromAssets);
                    }
                }
            }
        } else {
            int i = 0;
            while (true) {
                if (i > arrayList2.size()) {
                    break;
                } else if (i != arrayList2.size()) {
                    WidgetEntity widgetEntity2 = (WidgetEntity) arrayList2.get(i);
                    String tabIcon2 = widgetEntity2.getTabIcon();
                    MenuItem add2 = menu.add(0, Integer.valueOf(widgetEntity2.getTabId()).intValue(), 0, widgetEntity2.getTabName());
                    if (!tabIcon2.equals("")) {
                        Activity activity3 = this.activity;
                        Drawable drawableIconFromAssets2 = getDrawableIconFromAssets(activity3, "tabIcons/" + tabIcon2);
                        if (drawableIconFromAssets2 != null) {
                            add2.setIcon(drawableIconFromAssets2);
                        }
                    }
                    i++;
                } else if (arrayList.size() != 0) {
                    menu.add(0, 1, 0, R.string.bottomMenuItemMore).setIcon(this.activity.getResources().getDrawable(R.drawable.baseline_more_horiz_white_24));
                }
            }
        }
        if (arrayList.size() != 0) {
            if (vector.size() > 5 && arrayList2.size() >= 5) {
                arrayList.add(0, arrayList2.get(arrayList2.size() - 1));
            }
            this.bottomSheetMenuList.setAdapter(new BottomSheetMenuAdapter(arrayList, this.bottomSheetClickListener));
            if (arrayList.size() == 1) {
                this.bottomSheet.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            }
        }
    }

    public boolean onBackKeyDown() {
        WebContentController selectedTab = getSelectedTab();
        if (selectedTab == null || selectedTab.getWidgetInfo().getTabType().equals(TabContent.TabType.PDF.toString()) || !selectedTab.onBackKeyDown()) {
            return false;
        }
        return true;
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

    public void swipeOnPage(int i, boolean z) {
        this.viewPager.setCurrentItem(i, z);
        this.bottomNavigationView.getMenu().findItem(this.selectedMenuItem).setChecked(true);
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

    public void isTabsControllerReady(INavigationController.OnTabsControllerReady onTabsControllerReady) {
        if (this.bottomNavigationView != null) {
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

    public int getSelectedTabId() {
        return this.selectedPosition;
    }

    public void setSwipeEnabled(boolean z) {
        this.viewPager.setPagingEnabled(z);
    }

    public static Drawable getDrawableIconFromAssets(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = calculateInSampleSize(options, 50, 50);
            options.inJustDecodeBounds = false;
            Bitmap decodeStream = BitmapFactory.decodeStream(open, (Rect) null, options);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (decodeStream != null) {
                decodeStream.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
            }
            return new BitmapDrawable(context.getResources(), decodeStream);
        } catch (IOException e) {
            Log.e("Error getting icon ", str);
            e.printStackTrace();
            return null;
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }
}
