package com.appsgeyser.multiTabApp.ui.navigationdrawerwidget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.drawerlayout.widget.DrawerLayout;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.controllers.INavigationController;
import com.appsgeyser.multiTabApp.controllers.WidgetsController;
import com.wGhostlySkills_14510784.R;
import java.util.ArrayList;

public class NavigationDrawerWidget {
    /* access modifiers changed from: private */
    public final MainNavigationActivity activity;
    /* access modifiers changed from: private */
    public final ListView drawerListView;
    private ArrayList<DrawerWidgetModel> drawerWidgetModels;
    private boolean hasOptions;
    private final boolean hasTabs;
    private final DrawerLayout navigationDrawer;
    private final NavigationDrawerWidgetSimpleListener navigationDrawerWidgetSimpleListener;
    /* access modifiers changed from: private */
    public final ImageView sliderHandle;
    private final Animation sliderHandleAnimation;
    private final Animation sliderHandleHideAnimation;
    /* access modifiers changed from: private */
    public final INavigationController tabsController = Factory.getInstance().getTabsController();
    private boolean userKnowsAboutDrawer;

    public NavigationDrawerWidget(MainNavigationActivity mainNavigationActivity, boolean z, boolean z2, boolean z3) {
        this.activity = mainNavigationActivity;
        this.navigationDrawer = (DrawerLayout) mainNavigationActivity.findViewById(R.id.drawer_layout);
        this.drawerListView = (ListView) mainNavigationActivity.findViewById(R.id.left_drawer_widget);
        this.sliderHandle = (ImageView) mainNavigationActivity.findViewById(R.id.slider_handle);
        Animation loadAnimation = AnimationUtils.loadAnimation(mainNavigationActivity, R.anim.slider_handle_animation);
        this.sliderHandleAnimation = loadAnimation;
        loadAnimation.setRepeatCount(-1);
        this.sliderHandleHideAnimation = AnimationUtils.loadAnimation(mainNavigationActivity, R.anim.slider_handle_hide_animation);
        if (z) {
            addTabsInListView();
        }
        this.hasTabs = z;
        if (z2) {
            boolean z4 = mainNavigationActivity.getPreferences(0).getBoolean("com.appsgeyser.multiTabApp.ui.navigationdrawerwidget.NavigationDrawerWidget.userKnowsAboutDrawer", false);
            this.userKnowsAboutDrawer = z4;
            if (!z4) {
                showSliderHandle();
            }
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    NavigationDrawerWidget.this.hideSliderHandle();
                }
            }, 20000);
        }
        if (z3) {
            initHeader();
            mainNavigationActivity.findViewById(R.id.main_toolbar).setVisibility(0);
            Toolbar toolbar = (Toolbar) mainNavigationActivity.findViewById(R.id.main_toolbar);
            toolbar.showOverflowMenu();
            mainNavigationActivity.setSupportActionBar(toolbar);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(mainNavigationActivity, this.navigationDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
            this.navigationDrawer.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
            this.drawerListView.setAdapter(new NavigationDrawerWidgetAdapter(mainNavigationActivity, this.drawerWidgetModels));
        }
        NavigationDrawerWidgetSimpleListener navigationDrawerWidgetSimpleListener2 = new NavigationDrawerWidgetSimpleListener(this);
        this.navigationDrawerWidgetSimpleListener = navigationDrawerWidgetSimpleListener2;
        this.navigationDrawer.addDrawerListener(navigationDrawerWidgetSimpleListener2);
        this.drawerListView.setOnItemClickListener(new NavigationDrawerWidgetClickListener(this.tabsController, this.navigationDrawer, this.drawerListView, mainNavigationActivity));
    }

    public void show() {
        this.drawerListView.setVisibility(0);
        this.sliderHandle.bringToFront();
        this.navigationDrawer.setDrawerLockMode(0);
    }

    public void open() {
        this.navigationDrawer.openDrawer(3);
    }

    public void setOptions(ArrayList<DrawerWidgetModel> arrayList) {
        if (!this.hasOptions) {
            initHeader();
            if (this.hasTabs) {
                DrawerWidgetModel drawerWidgetModel = new DrawerWidgetModel((Drawable) null, 0, (String) null);
                drawerWidgetModel.setDivider(true);
                this.drawerWidgetModels.add(drawerWidgetModel);
            } else {
                this.drawerWidgetModels = new ArrayList<>(arrayList.size());
            }
            this.drawerWidgetModels.addAll(arrayList);
            this.drawerListView.setAdapter(new NavigationDrawerWidgetAdapter(this.activity, this.drawerWidgetModels));
            if (this.hasTabs) {
                this.drawerListView.post(new Runnable() {
                    public void run() {
                        SelectItemController.selectItem(NavigationDrawerWidget.this.drawerListView, NavigationDrawerWidget.this.tabsController.getSelectedTabId() + 1, NavigationDrawerWidget.this.activity);
                    }
                });
            }
            this.hasOptions = true;
        }
    }

    public void hideMenuItem(long j) {
        NavigationDrawerWidgetAdapter navigationDrawerWidgetAdapter = (NavigationDrawerWidgetAdapter) ((HeaderViewListAdapter) this.drawerListView.getAdapter()).getWrappedAdapter();
        for (int i = 0; i < navigationDrawerWidgetAdapter.getCount(); i++) {
            DrawerWidgetModel item = navigationDrawerWidgetAdapter.getItem(i);
            if (item != null && ((long) item.getMenuId()) == j) {
                navigationDrawerWidgetAdapter.remove(item);
            }
        }
        navigationDrawerWidgetAdapter.notifyDataSetChanged();
        this.drawerListView.invalidate();
    }

    private void addTabsInListView() {
        WidgetsController widgetsController = Factory.getInstance().getWidgetsController();
        this.drawerWidgetModels = new ArrayList<>(widgetsController.tabsCount());
        int tabsCount = widgetsController.tabsCount();
        for (int i = 0; i < tabsCount; i++) {
            this.drawerWidgetModels.add(new DrawerWidgetModel((Drawable) null, 0, widgetsController.getTabByPosition(i).getName()));
        }
        this.drawerListView.post(new Runnable() {
            public void run() {
                SelectItemController.selectItem(NavigationDrawerWidget.this.drawerListView, NavigationDrawerWidget.this.tabsController.getSelectedTabId() + 1, NavigationDrawerWidget.this.activity);
            }
        });
    }

    private void setCircularIconHeader() {
        Resources resources = this.activity.getResources();
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, R.drawable.icon);
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(resources, decodeResource);
        create.setCornerRadius(((float) Math.max(decodeResource.getWidth(), decodeResource.getHeight())) / 0.2f);
        ((ImageView) this.activity.findViewById(R.id.navigation_drawer_header_imageView_icon)).setImageDrawable(create);
    }

    private void initHeader() {
        if (this.drawerListView.getHeaderViewsCount() == 0) {
            LinearLayout linearLayout = (LinearLayout) this.activity.getLayoutInflater().inflate(R.layout.navigation_drawer_header, this.drawerListView, false);
            ((TextView) linearLayout.findViewById(R.id.navigation_drawer_header_textView_appname)).setText(this.activity.getString(R.string.app_caption));
            this.drawerListView.addHeaderView(linearLayout, (Object) null, false);
            setCircularIconHeader();
        }
    }

    private void showSliderHandle() {
        this.sliderHandle.setVisibility(0);
        this.sliderHandle.bringToFront();
        this.sliderHandle.startAnimation(this.sliderHandleAnimation);
    }

    /* access modifiers changed from: package-private */
    public void hideSliderHandle() {
        if (!this.userKnowsAboutDrawer) {
            this.userKnowsAboutDrawer = true;
            this.activity.getPreferences(0).edit().putBoolean("com.appsgeyser.multiTabApp.ui.navigationdrawerwidget.NavigationDrawerWidget.userKnowsAboutDrawer", this.userKnowsAboutDrawer).apply();
        }
        this.sliderHandle.clearAnimation();
        this.sliderHandleHideAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                NavigationDrawerWidget.this.sliderHandle.setVisibility(8);
            }
        });
        this.sliderHandle.startAnimation(this.sliderHandleHideAnimation);
    }

    public void remove() {
        ListView listView = (ListView) this.activity.findViewById(R.id.left_drawer_widget);
        if (listView != null) {
            listView.setVisibility(8);
        }
        this.navigationDrawer.setDrawerLockMode(1);
        this.tabsController.setSwipeEnabled(true);
    }

    public void close() {
        ListView listView;
        DrawerLayout drawerLayout = this.navigationDrawer;
        if (drawerLayout != null && (listView = this.drawerListView) != null) {
            drawerLayout.closeDrawer((View) listView);
        }
    }

    public boolean isOpened() {
        return this.navigationDrawerWidgetSimpleListener.isOpened();
    }
}
