package com.appsgeyser.multiTabApp.ui.menu;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.content.ContextCompat;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.configuration.UrlBarMenuButton;
import com.appsgeyser.multiTabApp.configuration.UrlBarMenuLinkButton;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.appsgeyser.multiTabApp.ui.navigationdrawerwidget.DrawerWidgetModel;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.wGhostlySkills_14510784.R;
import java.util.ArrayList;
import java.util.Iterator;

public class MenuItemsHolder {
    private Menu _menu;
    private MainNavigationActivity activity;
    private int itemOrderId = 50;
    public boolean trigger = true;

    public MenuItemsHolder(WebWidgetConfiguration webWidgetConfiguration, MainNavigationActivity mainNavigationActivity) {
        MainNavigationActivity.ApplicationMode applicationMode = webWidgetConfiguration.getApplicationMode();
        if (mainNavigationActivity != null) {
            this._menu = MenuGenerator.newEmptyMenuInstance(mainNavigationActivity);
            _init(webWidgetConfiguration, applicationMode, mainNavigationActivity);
            this.activity = mainNavigationActivity;
        }
    }

    public MenuItemsHolder(WebWidgetConfiguration webWidgetConfiguration, MainNavigationActivity.ApplicationMode applicationMode, MainNavigationActivity mainNavigationActivity, Menu menu) {
        if (mainNavigationActivity != null) {
            this._menu = menu;
            _init(webWidgetConfiguration, applicationMode, mainNavigationActivity);
            this.activity = mainNavigationActivity;
        }
    }

    private void _init(WebWidgetConfiguration webWidgetConfiguration, MainNavigationActivity.ApplicationMode applicationMode, Activity activity2) {
        activity2.getMenuInflater().inflate(R.menu.webapp_menu, this._menu);
        _applyItemsVisibility(activity2, webWidgetConfiguration, applicationMode);
    }

    private void _applyItemsVisibility(Activity activity2, WebWidgetConfiguration webWidgetConfiguration, MainNavigationActivity.ApplicationMode applicationMode) {
        if (!webWidgetConfiguration.getUrlOverlayState().equals(WebWidgetConfiguration.UrlBarStates.DISABLED)) {
            Iterator<UrlBarMenuButton> it = webWidgetConfiguration.getUrlBarMenuButtons().iterator();
            while (it.hasNext()) {
                UrlBarMenuButton next = it.next();
                if (next.getType().equals(UrlBarMenuButton.UrlBarMenuButtonTypes.BACK)) {
                    this._menu.findItem(R.id.webapp_back).setVisible(true);
                }
                if (next.getType().equals(UrlBarMenuButton.UrlBarMenuButtonTypes.FORWARD)) {
                    this._menu.findItem(R.id.webapp_forward).setVisible(true);
                }
                if (next.getType().equals(UrlBarMenuButton.UrlBarMenuButtonTypes.REQUEST_DESKTOP)) {
                    this._menu.findItem(R.id.webapp_request_desktop).setVisible(true);
                }
                if (next.getType().equals(UrlBarMenuButton.UrlBarMenuButtonTypes.ADD_TO_HOME)) {
                    this._menu.findItem(R.id.webapp_add_to_home).setVisible(true);
                }
                if (next.getType().equals(UrlBarMenuButton.UrlBarMenuButtonTypes.HOME)) {
                    this._menu.findItem(R.id.webapp_home).setVisible(true);
                }
                if (next.getType().equals(UrlBarMenuButton.UrlBarMenuButtonTypes.DOWNLOADS_LIST)) {
                    this._menu.findItem(R.id.webapp_downloads_list).setVisible(true);
                }
                if (next.getType().equals(UrlBarMenuButton.UrlBarMenuButtonTypes.LINK) && (next instanceof UrlBarMenuLinkButton)) {
                    final UrlBarMenuLinkButton urlBarMenuLinkButton = (UrlBarMenuLinkButton) next;
                    this._menu.add(0, urlBarMenuLinkButton.getTitle().hashCode(), this.itemOrderId, urlBarMenuLinkButton.getTitle()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            Factory.getInstance().getTabsController().getSelectedTab().getWebView().loadUrl(urlBarMenuLinkButton.getUrl());
                            return false;
                        }
                    });
                }
            }
        }
        AppsgeyserSDK.isAboutDialogEnabled(activity2, new AppsgeyserSDK.OnAboutDialogEnableListener() {
            public final void onDialogEnableReceived(boolean z) {
                MenuItemsHolder.this.lambda$_applyItemsVisibility$0$MenuItemsHolder(z);
            }
        });
        if (applicationMode == MainNavigationActivity.ApplicationMode.CUSTOM) {
            this._menu.findItem(R.id.webapp_share).setVisible(false);
        } else {
            this._menu.findItem(R.id.webapp_share).setVisible(true);
        }
        this._menu.findItem(R.id.webapp_refresh).setVisible(webWidgetConfiguration.getShowRefreshMenuItem());
        this._menu.findItem(R.id.webapp_exit).setVisible(webWidgetConfiguration.getShowExitMenuItem());
        this._menu.findItem(R.id.webapp_share).setVisible(webWidgetConfiguration.getShowShareMenuItem());
        this._menu.findItem(R.id.webapp_downloads_list).setVisible(webWidgetConfiguration.getShowDownloadList());
        this._menu.findItem(R.id.webapp_settings).setVisible(webWidgetConfiguration.getShowSettings());
        this._menu.findItem(R.id.webapp_theming).setVisible(true);
    }

    public /* synthetic */ void lambda$_applyItemsVisibility$0$MenuItemsHolder(boolean z) {
        if (!z) {
            this._menu.findItem(R.id.webapp_about).setVisible(false);
        }
    }

    public void setItemVisible(int i, boolean z) {
        MenuItem findItem = this._menu.findItem(i);
        if (findItem != null) {
            findItem.setVisible(z);
        }
    }

    public ArrayList<DrawerWidgetModel> getAllItems() {
        ArrayList<DrawerWidgetModel> arrayList = new ArrayList<>();
        for (int i = 0; i < this._menu.size(); i++) {
            MenuItem item = this._menu.getItem(i);
            if (item.getItemId() != R.id.webapp_request_desktop && item.isVisible()) {
                int itemId = item.getItemId();
                arrayList.add(new DrawerWidgetModel(ContextCompat.getDrawable(this.activity, MenuStructure.getIconResourceIdByItemId(itemId)), itemId, item.getTitle().toString()));
            }
        }
        return arrayList;
    }

    public Menu getMenu() {
        return this._menu;
    }
}
