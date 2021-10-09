package com.appsgeyser.multiTabApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.appsgeyser.multiTabApp.controllers.INavigationController;
import com.appsgeyser.multiTabApp.controllers.ITabContentController;
import com.appsgeyser.multiTabApp.controllers.WebContentController;
import com.appsgeyser.multiTabApp.controllers.WidgetsController;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import com.appsgeyser.multiTabApp.storage.BookmarksManager;
import com.appsgeyser.multiTabApp.ui.navigationwidget.INavigationWidget;
import com.appsgeyser.multiTabApp.ui.views.TabContent;
import com.wGhostlySkills_14510784.R;
import java.util.HashMap;

public class Factory {
    private static Factory _instance;
    private MainNavigationActivity _activity;
    private BookmarksManager _homePageManager;
    private INavigationController _navigationController;
    private INavigationWidget _navigationWidget = null;
    private WidgetsController _widgetsController = new WidgetsController();
    private HashMap<String, BookmarksManager> bookmarkManagers = new HashMap<>();
    private ITabContentController tccRes = null;

    private Factory() {
    }

    public static Factory getInstance() {
        if (_instance == null) {
            _instance = new Factory();
        }
        return _instance;
    }

    public void Init(MainNavigationActivity mainNavigationActivity) {
        this._activity = mainNavigationActivity;
    }

    public void setNavigationWidget(INavigationWidget iNavigationWidget) {
        this._navigationWidget = iNavigationWidget;
    }

    public void setNavigationController(INavigationController iNavigationController) {
        this._navigationController = iNavigationController;
    }

    public INavigationWidget getNavigationWidget() {
        return this._navigationWidget;
    }

    public MainNavigationActivity getMainNavigationActivity() {
        return this._activity;
    }

    public WidgetsController getWidgetsController() {
        return this._widgetsController;
    }

    public void setWidgetsController(WidgetsController widgetsController) {
        this._widgetsController = widgetsController;
    }

    public INavigationController getTabsController() {
        return this._navigationController;
    }

    /* renamed from: com.appsgeyser.multiTabApp.Factory$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$appsgeyser$multiTabApp$ui$views$TabContent$TabType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.appsgeyser.multiTabApp.ui.views.TabContent$TabType[] r0 = com.appsgeyser.multiTabApp.ui.views.TabContent.TabType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$appsgeyser$multiTabApp$ui$views$TabContent$TabType = r0
                com.appsgeyser.multiTabApp.ui.views.TabContent$TabType r1 = com.appsgeyser.multiTabApp.ui.views.TabContent.TabType.WEB     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$appsgeyser$multiTabApp$ui$views$TabContent$TabType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appsgeyser.multiTabApp.ui.views.TabContent$TabType r1 = com.appsgeyser.multiTabApp.ui.views.TabContent.TabType.PDF     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.multiTabApp.Factory.AnonymousClass1.<clinit>():void");
        }
    }

    public View getTabContent(TabContent.TabType tabType, LayoutInflater layoutInflater, ViewGroup viewGroup) throws Exception {
        int i = AnonymousClass1.$SwitchMap$com$appsgeyser$multiTabApp$ui$views$TabContent$TabType[tabType.ordinal()];
        if (i == 1) {
            return layoutInflater.inflate(R.layout.web_content, viewGroup, false);
        }
        if (i == 2) {
            return layoutInflater.inflate(R.layout.pdf_content, viewGroup, false);
        }
        throw new Exception("Unknown Content Type");
    }

    public ITabContentController getTabContentController(WidgetEntity widgetEntity) {
        WebContentController webContentController = new WebContentController(widgetEntity);
        this.tccRes = webContentController;
        webContentController.setMainNavigationActivity(this._activity);
        return this.tccRes;
    }

    public ITabContentController getWebContentController() {
        return this.tccRes;
    }

    public BookmarksManager getHomePageManager() {
        if (this._homePageManager == null) {
            this._homePageManager = new BookmarksManager("Homepage", this._activity);
        }
        return this._homePageManager;
    }

    public BookmarksManager getBookmarkManager(String str) {
        if (!this.bookmarkManagers.containsKey(str)) {
            this.bookmarkManagers.put(str, new BookmarksManager(str, this._activity));
        }
        return this.bookmarkManagers.get(str);
    }
}
