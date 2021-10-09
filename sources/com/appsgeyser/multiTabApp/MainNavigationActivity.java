package com.appsgeyser.multiTabApp;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.drawerlayout.widget.DrawerLayout;
import com.appsgeyser.multiTabApp.browser.DownloadsItem;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfigurationManager;
import com.appsgeyser.multiTabApp.controllers.BottomMenuController;
import com.appsgeyser.multiTabApp.controllers.FirstLaunchController;
import com.appsgeyser.multiTabApp.controllers.INavigationController;
import com.appsgeyser.multiTabApp.controllers.SplashScreenController;
import com.appsgeyser.multiTabApp.controllers.TabsController;
import com.appsgeyser.multiTabApp.controllers.WebContentController;
import com.appsgeyser.multiTabApp.media.camera.AlbumStorageController;
import com.appsgeyser.multiTabApp.storage.BrowsingHistoryItem;
import com.appsgeyser.multiTabApp.storage.DatabaseOpenHelper;
import com.appsgeyser.multiTabApp.ui.dialog.SimpleDialogs;
import com.appsgeyser.multiTabApp.ui.menu.MenuItemsHolder;
import com.appsgeyser.multiTabApp.ui.navigationdrawerwidget.NavigationDrawerWidget;
import com.appsgeyser.multiTabApp.ui.navigationwidget.INavigationWidget;
import com.appsgeyser.multiTabApp.ui.navigationwidget.NavigationWidget;
import com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget;
import com.appsgeyser.multiTabApp.ui.views.TabContent;
import com.appsgeyser.multiTabApp.utils.ThemeUtils;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.google.android.gms.plus.PlusShare;
import com.google.android.material.snackbar.Snackbar;
import com.wGhostlySkills_14510784.R;
import java.io.File;
import java.util.ArrayList;

public class MainNavigationActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    static final FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(-1, -1, 17);
    private static boolean _active = false;
    /* access modifiers changed from: private */
    public static volatile ApplicationState applicationState;
    private WebWidgetConfiguration _config;
    /* access modifiers changed from: private */
    public MenuItemsHolder _menuItemsHolder;
    /* access modifiers changed from: private */
    public INavigationController _tabsController;
    BroadcastReceiver activityReceiver = new BroadcastReceiver() {
        public void onReceive(final Context context, Intent intent) {
            try {
                if (intent.getIntExtra("type", 0) != 100) {
                    return;
                }
                if (MainNavigationActivity.this.isOptionDownloadsList) {
                    Snackbar.make(MainNavigationActivity.this.findViewById(R.id.frame_layout), (int) R.string.snackbar_downloads, 0).setAction((int) R.string.view, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(View view) {
                            MainNavigationActivity.this.startActivity(new Intent(context, DownloadsListActivity.class));
                        }
                    }).show();
                } else {
                    Toast.makeText(context, R.string.snackbar_downloads, 0).show();
                }
            } catch (Exception e) {
                Log.e("activityReceiver", "" + e);
            }
        }
    };
    private boolean adsKeyboardShow;
    private AlbumStorageController albumStorageController;
    private boolean bannerviewPlacementBlocked;
    private ApplicationMode currentMode = ApplicationMode.COMMON;
    private boolean isNotificationDialogShow = false;
    /* access modifiers changed from: private */
    public boolean isOptionDownloadsList = false;
    public boolean isThemeChangingInProgress = false;
    /* access modifiers changed from: private */
    public final Handler loadUrlFromIntentHandler = new Handler();
    private final Runnable loadUrlFromIntentRunnable = new Runnable() {
        public void run() {
            if (MainNavigationActivity.this.urlFromIntentToLoad != null && MainNavigationActivity.this.urlFromIntentToLoad.length() > 0) {
                WebContentController selectedTab = MainNavigationActivity.this._tabsController.getSelectedTab();
                if (selectedTab != null) {
                    WebView webView = selectedTab.getWebView();
                    webView.stopLoading();
                    webView.loadUrl(MainNavigationActivity.this.urlFromIntentToLoad);
                    return;
                }
                MainNavigationActivity.this.loadUrlFromIntentHandler.postDelayed(this, 500);
            }
        }
    };
    private LinearLayout mContentView;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private FrameLayout mCustomViewContainer;
    private FrameLayout mFullScreenBannerView;
    private ViewGroup mSplashScreenView;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessages;
    private VideoView mVideo;
    private View mVideoProgressView;
    /* access modifiers changed from: private */
    public NavigationDrawerWidget navigationDrawerWidget;
    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            long longExtra = intent.getLongExtra("extra_download_id", -1);
            SQLiteDatabase writableDatabase = new DatabaseOpenHelper(MainNavigationActivity.this).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(new long[]{longExtra});
            Cursor query2 = ((DownloadManager) MainNavigationActivity.this.getSystemService("download")).query(query);
            if (query2.moveToFirst()) {
                int i = query2.getInt(query2.getColumnIndex("status"));
                String string = query2.getString(query2.getColumnIndex("local_uri"));
                String absolutePath = string != null ? new File(Uri.parse(string).getPath()).getAbsolutePath() : null;
                int i2 = query2.getInt(query2.getColumnIndex("reason"));
                if (i == 8) {
                    contentValues.put("status", DownloadsItem.Status.Ok.toString());
                    contentValues.put("file_path", absolutePath);
                    contentValues.put("date", Long.valueOf(System.currentTimeMillis()));
                    writableDatabase.update("downloadsList", contentValues, "id_d = " + longExtra, (String[]) null);
                } else if (i == 16) {
                    contentValues.put("status", DownloadsItem.Status.Fail.toString());
                    contentValues.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, Integer.valueOf(i2));
                    contentValues.put("date", Long.valueOf(System.currentTimeMillis()));
                    writableDatabase.update("downloadsList", contentValues, "id_d = " + longExtra, (String[]) null);
                }
            }
            query2.close();
            Intent intent2 = new Intent();
            intent2.putExtra("type", 101);
            intent2.setAction(DownloadsListActivity.DOWNLOAD_LIST_MSG);
            MainNavigationActivity.this.sendBroadcast(intent2);
            writableDatabase.close();
        }
    };
    BroadcastReceiver onNotificationClick = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            MainNavigationActivity.this.startActivity(new Intent(context, DownloadsListActivity.class));
        }
    };
    private ValueCallback<Integer> permissionCallback;
    public boolean startCuebiqSdkPermission = false;
    public boolean startOneAudienceSdkPermission = false;
    public Long timeStart = null;
    /* access modifiers changed from: private */
    public String urlFromIntentToLoad;

    public enum ApplicationMode {
        UNKNOWN,
        COMMON,
        CUSTOM
    }

    public enum ApplicationState {
        STARTED,
        EXITING
    }

    private boolean _isMenuItemId(int i) {
        return i == R.id.webapp_exit || i == R.id.webapp_refresh || i == R.id.webapp_about || i == R.id.webapp_share || i == R.id.webapp_back || i == R.id.webapp_forward || i == R.id.webapp_request_desktop || i == R.id.webapp_add_to_home || i == R.id.webapp_home || i == R.id.webapp_downloads_list || i == R.id.webapp_settings || i == R.id.webapp_theming;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }

    public static ApplicationState getApplicationState() {
        return applicationState;
    }

    public void setPermissionCallback(ValueCallback<Integer> valueCallback) {
        this.permissionCallback = valueCallback;
    }

    public void onCreate(Bundle bundle) {
        int i;
        super.onCreate((Bundle) null);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ThemeUtils.setCurrentThemeWithActionBar(this);
        Factory.getInstance().Init(this);
        applicationState = ApplicationState.STARTED;
        try {
            this._config = WebWidgetConfigurationManager.getInstance(this).loadConfiguration(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadPreviousApplicationMode();
        setContentView((int) R.layout.main);
        initAppsgeyserSDK();
        ThemeUtils.initializeAppTheme(this, this._config);
        initToolBar();
        this.mContentView = (LinearLayout) findViewById(R.id.contentFrame);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.adView);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mContentView.getLayoutParams();
        Factory.getInstance().setNavigationController(new TabsController());
        if (this._config.getTabsPosition() == WebWidgetConfiguration.TabsPositions.BOTTOM) {
            i = R.layout.tabs_panel_bottom;
            layoutParams2.addRule(3, R.id.adView);
            this.mContentView.setLayoutParams(layoutParams2);
        } else if (this._config.getTabsPosition() == WebWidgetConfiguration.TabsPositions.DRAWER) {
            layoutParams.addRule(12);
            layoutParams.addRule(15);
            layoutParams.addRule(14);
            relativeLayout.setLayoutParams(layoutParams);
            layoutParams2.addRule(2, R.id.adView);
            this.mContentView.setLayoutParams(layoutParams2);
            i = R.layout.tabs_panel_drawer;
        } else if (this._config.getTabsPosition() == WebWidgetConfiguration.TabsPositions.BOTTOM_MENU) {
            i = R.layout.bottom_menu;
            layoutParams2.addRule(3, R.id.adView);
            this.mContentView.setLayoutParams(layoutParams2);
            Factory.getInstance().setNavigationController(new BottomMenuController());
        } else {
            layoutParams.addRule(12);
            layoutParams.addRule(15);
            layoutParams.addRule(14);
            relativeLayout.setLayoutParams(layoutParams);
            layoutParams2.addRule(2, R.id.adView);
            this.mContentView.setLayoutParams(layoutParams2);
            i = R.layout.tabs_panel;
        }
        getLayoutInflater().inflate(i, this.mContentView, true);
        _postApplyAppTheme(this._config);
        initDrawer();
        this.mContentView.setKeepScreenOn(this._config.getPreventFromSleep());
        this.mCustomViewContainer = (FrameLayout) findViewById(R.id.customFrame);
        this.mFullScreenBannerView = (FrameLayout) findViewById(R.id.fullScreenBannerContainer);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.splashScreenView);
        this.mSplashScreenView = viewGroup;
        SplashScreenController splashScreenController = new SplashScreenController(viewGroup, this);
        if (this._config.isSplashScreenEnabled()) {
            splashScreenController.showSplashScreen(this._config.getSplashScreenImage());
        } else {
            showContentView();
        }
        this.albumStorageController = new AlbumStorageController(this._config.getWidgetName());
        boolean z = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("show_quick_access_bar", true);
        if (z && getConfig().getShowSearchNotice() && getConfig().getUrlOverlayState() == WebWidgetConfiguration.UrlBarStates.ENABLED) {
            createNotice();
        }
        if (z && getIntent().getBooleanExtra("focus", false)) {
            this.adsKeyboardShow = false;
        }
        _initAppContent();
        if (this.timeStart == null) {
            this.timeStart = Long.valueOf(System.currentTimeMillis());
        }
        registerReceiver(this.onComplete, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        registerReceiver(this.activityReceiver, new IntentFilter(DownloadsListActivity.DOWNLOAD_LIST_MSG));
        registerReceiver(this.onNotificationClick, new IntentFilter("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED"));
    }

    private void initAppsgeyserSDK() {
        AppsgeyserSDK.takeOff(this, getResources().getString(R.string.wid), getString(R.string.app_metrica_on_start_event), getString(R.string.template_version));
        AppsgeyserSDK.getFastTrackAdsController().setFullscreenListener(new FastTrackBaseAdapter.FullscreenListener() {
            public void onRequest() {
            }

            public void onShow() {
                MainNavigationActivity.this._tabsController.isTabsControllerReady(new INavigationController.OnTabsControllerReady() {
                    public void tabsControllerReady(boolean z) {
                        MainNavigationActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                MainNavigationActivity.this._tabsController.onPause();
                            }
                        });
                    }
                });
                MainNavigationActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        MainNavigationActivity.this._tabsController.firstBannerWasShown();
                    }
                });
            }

            public void onClose() {
                MainNavigationActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        MainNavigationActivity.this._tabsController.onResume();
                    }
                });
            }

            public void onFailedToShow() {
                MainNavigationActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        MainNavigationActivity.this._tabsController.onResume();
                    }
                });
            }
        });
    }

    private void initDrawer() {
        if (this._config.getTabsPosition() == WebWidgetConfiguration.TabsPositions.DRAWER && this._config.getApplicationTheme() == WebWidgetConfiguration.ApplicationThemes.ACTION_BAR) {
            this.navigationDrawerWidget = new NavigationDrawerWidget(this, true, false, true);
            showNavigationDrawerWidget();
        } else if (this._config.getTabsPosition() == WebWidgetConfiguration.TabsPositions.DRAWER && this._config.getApplicationTheme() == WebWidgetConfiguration.ApplicationThemes.SLIDER) {
            this.navigationDrawerWidget = new NavigationDrawerWidget(this, true, false, false);
            showNavigationDrawerWidget();
        } else if (this._config.getApplicationTheme() == WebWidgetConfiguration.ApplicationThemes.SLIDER) {
            this.navigationDrawerWidget = new NavigationDrawerWidget(this, false, true, false);
            showNavigationDrawerWidget();
        }
    }

    private void initToolBar() {
        if (this._config.getTabsPosition() != WebWidgetConfiguration.TabsPositions.DRAWER && this._config.getApplicationTheme() == WebWidgetConfiguration.ApplicationThemes.ACTION_BAR) {
            findViewById(R.id.main_toolbar).setVisibility(0);
            setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));
        }
    }

    public void createNotice() {
        Class<MainNavigationActivity> cls = MainNavigationActivity.class;
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_search_white_24dp).setContent(new RemoteViews(getPackageName(), R.layout.search_notice)).setOngoing(true);
        Intent intent = new Intent(this, cls);
        intent.putExtra("focus", true);
        TaskStackBuilder create = TaskStackBuilder.create(this);
        create.addParentStack((Class<?>) cls);
        create.addNextIntent(intent);
        ongoing.setContentIntent(PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent, 0));
        ((NotificationManager) getSystemService("notification")).notify(120778953, ongoing.build());
    }

    public boolean getAdsKeyboardShow() {
        return this.adsKeyboardShow;
    }

    public void deleteNotice() {
        ((NotificationManager) getSystemService("notification")).cancel(120778953);
    }

    private void _postApplyAppTheme(WebWidgetConfiguration webWidgetConfiguration) {
        if (webWidgetConfiguration.getApplicationTheme() != WebWidgetConfiguration.ApplicationThemes.SLIDER && this._config.getTabsPosition() != WebWidgetConfiguration.TabsPositions.DRAWER) {
            removeNavigationDrawerWidget();
            freezeAllDrawers();
        }
    }

    private void _initAppContent() {
        INavigationController tabsController = Factory.getInstance().getTabsController();
        this._tabsController = tabsController;
        tabsController.initWithTabs(Factory.getInstance().getWidgetsController());
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
    }

    public void showCloseAppDialog() {
        SimpleDialogs.createConfirmDialog((String) null, getResources().getString(R.string.appExitCaption), this, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MainNavigationActivity.this.closeNavigationDrawerWidget();
                ApplicationState unused = MainNavigationActivity.applicationState = ApplicationState.EXITING;
                MainNavigationActivity.this.finish();
            }
        }, (DialogInterface.OnClickListener) null).show();
    }

    public void loadPreviousApplicationMode() {
        int i = getSharedPreferences("AppsgeyserPrefs", 0).getInt("applicationMode", ApplicationMode.COMMON.ordinal());
        if (ApplicationMode.COMMON.ordinal() == i) {
            this.currentMode = ApplicationMode.COMMON;
        } else if (ApplicationMode.CUSTOM.ordinal() == i) {
            this.currentMode = ApplicationMode.CUSTOM;
        }
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        this.mUploadMessage = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        if (str.length() == 0) {
            str = "*/*";
        }
        intent.setType(str);
        startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1);
    }

    public boolean openFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        if (Build.VERSION.SDK_INT >= 21) {
            ValueCallback<Uri[]> valueCallback2 = this.mUploadMessages;
            if (valueCallback2 != null) {
                valueCallback2.onReceiveValue((Object) null);
                this.mUploadMessages = null;
            }
            this.mUploadMessages = valueCallback;
            Intent createIntent = fileChooserParams.createIntent();
            createIntent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            createIntent.addCategory("android.intent.category.OPENABLE");
            createIntent.setType("*/*");
            try {
                startActivityForResult(createIntent, 100);
                return true;
            } catch (ActivityNotFoundException unused) {
                this.mUploadMessages = null;
                Toast.makeText(getApplicationContext(), "Cannot Open File Chooser", 1).show();
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri[] uriArr;
        Uri[] uriArr2;
        if (Build.VERSION.SDK_INT < 21 || i != 100) {
            if (i != 1) {
                Toast.makeText(getApplicationContext(), R.string.failed_upload_file, 1).show();
            } else if (this.mUploadMessage != null) {
                this.mUploadMessage.onReceiveValue((intent == null || i2 != -1) ? null : intent.getData());
                this.mUploadMessage = null;
            } else if (this.mUploadMessages != null && intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    uriArr = new Uri[clipData.getItemCount()];
                    for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
                        uriArr[i3] = clipData.getItemAt(i3).getUri();
                    }
                } else {
                    uriArr = null;
                }
                if (dataString != null) {
                    uriArr = new Uri[]{Uri.parse(dataString)};
                }
                this.mUploadMessages.onReceiveValue(uriArr);
                this.mUploadMessages = null;
            }
        } else if (this.mUploadMessages != null && intent != null) {
            String dataString2 = intent.getDataString();
            ClipData clipData2 = intent.getClipData();
            if (clipData2 != null) {
                uriArr2 = new Uri[clipData2.getItemCount()];
                for (int i4 = 0; i4 < clipData2.getItemCount(); i4++) {
                    uriArr2[i4] = clipData2.getItemAt(i4).getUri();
                }
            } else {
                uriArr2 = null;
            }
            if (dataString2 != null) {
                uriArr2 = new Uri[]{Uri.parse(dataString2)};
            }
            this.mUploadMessages.onReceiveValue(uriArr2);
            this.mUploadMessages = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        AppsgeyserSDK.onPause(this);
        this._tabsController.isTabsControllerReady(new INavigationController.OnTabsControllerReady() {
            public void tabsControllerReady(boolean z) {
                if (z) {
                    MainNavigationActivity.this._tabsController.onPause();
                }
            }
        });
        _active = false;
        boolean inKeyguardRestrictedInputMode = ((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        if (((TelephonyManager) getSystemService("phone")).getCallState() == 1) {
            pauseBrowser();
        }
        if (!inKeyguardRestrictedInputMode) {
            pauseBrowser();
        }
    }

    public void pauseBrowser() {
        CookieSyncManager.getInstance().stopSync();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
        toggleBannerviewAdsPlacement(true);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this._tabsController.isTabsControllerReady(new INavigationController.OnTabsControllerReady() {
            public void tabsControllerReady(boolean z) {
                if (z) {
                    MainNavigationActivity.this._tabsController.onResume();
                }
            }
        });
        _active = true;
        if (getIntent() != null) {
            checkReceivedIntentForDeepLinkAndHandleIt(getIntent());
            if (!this._config.getPreventFromSleep()) {
                CookieSyncManager.getInstance().startSync();
            }
            if (defaultSharedPreferences.getBoolean("show_quick_access_bar", true) && Factory.getInstance().getNavigationWidget() != null && getIntent().getBooleanExtra("focus", false)) {
                Factory.getInstance().getTabsController().getSelectedTab().getNavigationWidget().getNawigationWidgetView().findViewById(R.id.urlTextbox).requestFocus();
                getIntent().removeExtra("focus");
            }
        }
        if (!ThemeUtils.getActivityThemeName(this).equals(defaultSharedPreferences.getString("AppThemeName", "AppThemeDefault"))) {
            finish();
            startActivity(getIntent());
            this.isThemeChangingInProgress = true;
        }
    }

    public void blockBannerviewAdsPlacement() {
        this.bannerviewPlacementBlocked = true;
        toggleBannerviewAdsPlacement(false);
    }

    public void toggleBannerviewAdsPlacement(boolean z) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.adView);
        if (!z) {
            AppsgeyserSDK.getFastTrackAdsController().onPause();
        } else if (!this.bannerviewPlacementBlocked) {
            AppsgeyserSDK.getFastTrackAdsController().setBannerViewContainer(viewGroup, "SM_main");
        }
    }

    private void checkReceivedIntentForDeepLinkAndHandleIt(Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            String findTabIdToOpenFromDeepLink = Factory.getInstance().getTabsController().findTabIdToOpenFromDeepLink(data);
            if (!TextUtils.isEmpty(findTabIdToOpenFromDeepLink)) {
                Factory.getInstance().getTabsController().swipeOnPageByTabId(findTabIdToOpenFromDeepLink);
            }
            this.urlFromIntentToLoad = data.toString();
            this.loadUrlFromIntentHandler.post(this.loadUrlFromIntentRunnable);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        if (intent != null) {
            setIntent(intent);
            checkReceivedIntentForDeepLinkAndHandleIt(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.onComplete);
        unregisterReceiver(this.activityReceiver);
        unregisterReceiver(this.onNotificationClick);
        if (!this.isThemeChangingInProgress) {
            System.exit(0);
        }
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        NavigationDrawerWidget navigationDrawerWidget2 = this.navigationDrawerWidget;
        if (navigationDrawerWidget2 != null && navigationDrawerWidget2.isOpened()) {
            closeNavigationDrawerWidget();
            return true;
        } else if (this.mCustomView != null) {
            onHideCustomView();
            return true;
        } else {
            INavigationWidget navigationWidget = Factory.getInstance().getNavigationWidget();
            if (navigationWidget != null && (navigationWidget instanceof TopNavigationWidget)) {
                TopNavigationWidget topNavigationWidget = (TopNavigationWidget) navigationWidget;
                if (topNavigationWidget.isSuggestionsVisible()) {
                    topNavigationWidget.hideSuggestionsView();
                    return true;
                }
            }
            if (!this._tabsController.onBackKeyDown() && !getApplicationState().equals(ApplicationState.EXITING)) {
                showCloseAppDialog();
            }
            return true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this._menuItemsHolder == null) {
            this._menuItemsHolder = new MenuItemsHolder(this._config, this.currentMode, this, menu);
        }
        if (!(this.navigationDrawerWidget == null || this._config.getApplicationTheme() == WebWidgetConfiguration.ApplicationThemes.ACTION_BAR)) {
            this.navigationDrawerWidget.setOptions(this._menuItemsHolder.getAllItems());
        }
        try {
            this.isOptionDownloadsList = this._config.getShowDownloadList();
        } catch (Exception e) {
            Log.e("isOptionDownloadsList", "" + e);
        }
        return super.onCreateOptionsMenu(this._menuItemsHolder.getMenu());
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (_isMenuItemId(menuItem.getItemId())) {
            return onOptionsItemSelected(menuItem.getItemId(), menuItem);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onOptionsItemSelected(int i, MenuItem menuItem) {
        String str;
        String str2;
        WebContentController webContentController;
        NavigationWidget navigationWidget = (NavigationWidget) Factory.getInstance().getNavigationWidget();
        if (!(i == R.id.webapp_exit || (webContentController = (WebContentController) Factory.getInstance().getWebContentController()) == null)) {
            webContentController.showBanner(UserEvent.MENU_ITEM_CLICK, true);
        }
        switch (i) {
            case R.id.webapp_about:
                AppsgeyserSDK.showAboutDialog(this);
                return true;
            case R.id.webapp_add_to_home:
                if (navigationWidget != null) {
                    navigationWidget.onAddToStartPageClick();
                }
                return true;
            case R.id.webapp_back:
                if (navigationWidget != null) {
                    navigationWidget.onClickBackButton();
                }
                return true;
            case R.id.webapp_downloads_list:
                startActivity(new Intent(this, DownloadsListActivity.class));
                return true;
            case R.id.webapp_exit:
                showCloseAppDialog();
                return true;
            case R.id.webapp_forward:
                if (navigationWidget != null) {
                    navigationWidget.onClickForwardButton();
                }
                return true;
            case R.id.webapp_home:
                if (navigationWidget != null) {
                    navigationWidget.onHomeButtonClick();
                }
                return true;
            case R.id.webapp_refresh:
                if (this._tabsController.getSelectedTab().getWidgetInfo().getTabType().equals(TabContent.TabType.WEB.toString())) {
                    this._tabsController.getSelectedTab().getWebView().reload();
                }
                return true;
            case R.id.webapp_request_desktop:
                if (!(navigationWidget == null || menuItem == null)) {
                    navigationWidget.reloadWithChangedUserAgent(!menuItem.isChecked());
                    menuItem.setChecked(!menuItem.isChecked());
                }
                return true;
            case R.id.webapp_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.webapp_share:
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                if (this._config.getShareExtraLink() != null && !this._config.getShareExtraLink().equals("")) {
                    str = this._config.getShareExtraLink();
                    str2 = getResources().getString(R.string.shareContentSubject);
                } else if (this._config.getUrlOverlayState() == WebWidgetConfiguration.UrlBarStates.ENABLED) {
                    str = Factory.getInstance().getTabsController().getSelectedTab().getWebView().getUrl();
                    str2 = getResources().getString(R.string.shareSiteSubject);
                } else {
                    str = getResources().getString(R.string.getWidgetUrl) + this._config.getApplicationId() + "?" + this._config.getAffiliateString();
                    str2 = getResources().getString(R.string.shareContentSubject);
                }
                intent.putExtra("android.intent.extra.TEXT", str);
                intent.putExtra("android.intent.extra.SUBJECT", str2);
                startActivity(Intent.createChooser(intent, "Share using"));
                return true;
            case R.id.webapp_theming:
                startActivity(ThemingActivity.newThemingIntent(this, this._config, (Parcelable) null));
                return true;
            default:
                return false;
        }
    }

    public View getVideoLoadingProgressView() {
        if (this.mVideoProgressView == null) {
            this.mVideoProgressView = LayoutInflater.from(this).inflate(R.layout.video_loading_progress, (ViewGroup) null);
        }
        return this.mVideoProgressView;
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.mCustomView != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            if (frameLayout.getFocusedChild() instanceof VideoView) {
                VideoView videoView = (VideoView) frameLayout.getFocusedChild();
                this.mVideo = videoView;
                videoView.setOnCompletionListener(this);
                this.mVideo.setOnErrorListener(this);
            }
        }
        this.mCustomViewContainer.addView(view, COVER_SCREEN_GRAVITY_CENTER);
        this.mCustomView = view;
        this.mCustomViewCallback = customViewCallback;
        this.mContentView.setVisibility(8);
        this.mCustomViewContainer.setVisibility(0);
        this.mCustomViewContainer.bringToFront();
    }

    public void onHideCustomView() {
        if (this.mCustomView != null) {
            VideoView videoView = this.mVideo;
            if (videoView != null) {
                videoView.stopPlayback();
            }
            this.mCustomView.setVisibility(8);
            this.mCustomViewContainer.removeView(this.mCustomView);
            this.mCustomView = null;
            this.mCustomViewContainer.setVisibility(8);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mContentView.setVisibility(0);
        }
    }

    public void showContentView() {
        this.mFullScreenBannerView.setVisibility(8);
        this.mContentView.setVisibility(0);
        this.mSplashScreenView.setVisibility(8);
        this.mContentView.bringToFront();
    }

    public void showSplashScreen() {
        this.mContentView.setVisibility(8);
        this.mFullScreenBannerView.setVisibility(8);
        this.mSplashScreenView.setVisibility(0);
        this.mSplashScreenView.bringToFront();
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
        onHideCustomView();
    }

    public WebWidgetConfiguration getConfig() {
        return this._config;
    }

    public void showHttpAuthentication(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2, String str3, String str4, String str5, int i) {
        String str6;
        final HttpAuthHandler httpAuthHandler2 = httpAuthHandler;
        String str7 = str4;
        String str8 = str5;
        int i2 = i;
        if (getResources().getBoolean(R.bool.autoHttpAuthorization)) {
            String httpAccessLogin = this._config.getHttpAccessLogin();
            String httpAccessPassword = this._config.getHttpAccessPassword();
            setHttpAuthUsernamePassword(webView, str, str2, httpAccessLogin, httpAccessPassword);
            httpAuthHandler2.proceed(httpAccessLogin, httpAccessPassword);
            return;
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.http_authentication, (ViewGroup) null);
        if (str7 != null) {
            ((EditText) inflate.findViewById(R.id.username_edit)).setText(str7);
        }
        if (str8 != null) {
            ((EditText) inflate.findViewById(R.id.password_edit)).setText(str8);
        }
        if (str3 == null) {
            str6 = getText(R.string.sign_in_to).toString().replace("%s", str).replace("%s2", str2);
        } else {
            String str9 = str;
            String str10 = str2;
            str6 = str3;
        }
        final View view = inflate;
        final WebView webView2 = webView;
        final String str11 = str;
        final String str12 = str2;
        final HttpAuthHandler httpAuthHandler3 = httpAuthHandler;
        AlertDialog create = new AlertDialog.Builder(this).setTitle(str6).setView(inflate).setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = ((EditText) view.findViewById(R.id.username_edit)).getText().toString();
                String obj2 = ((EditText) view.findViewById(R.id.password_edit)).getText().toString();
                MainNavigationActivity.this.setHttpAuthUsernamePassword(webView2, str11, str12, obj, obj2);
                httpAuthHandler3.proceed(obj, obj2);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                httpAuthHandler2.cancel();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                httpAuthHandler2.cancel();
            }
        }).create();
        create.getWindow().setSoftInputMode(4);
        create.show();
        if (i2 != 0) {
            create.findViewById(i2).requestFocus();
        } else {
            inflate.findViewById(R.id.username_edit).requestFocus();
        }
    }

    public void setHttpAuthUsernamePassword(WebView webView, String str, String str2, String str3, String str4) {
        if (webView != null) {
            webView.setHttpAuthUsernamePassword(str, str2, str3, str4);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (this._config.getApplicationTheme() == WebWidgetConfiguration.ApplicationThemes.SLIDER) {
            if (this._menuItemsHolder == null) {
                this._menuItemsHolder = new MenuItemsHolder(this._config, this);
            }
            if (this.navigationDrawerWidget != null) {
                this.navigationDrawerWidget.setOptions(this._menuItemsHolder.getAllItems());
                AppsgeyserSDK.isAboutDialogEnabled(this, new AppsgeyserSDK.OnAboutDialogEnableListener() {
                    public void onDialogEnableReceived(boolean z) {
                        if (!z) {
                            MainNavigationActivity.this.navigationDrawerWidget.hideMenuItem(2131362366);
                        }
                    }
                });
            }
        }
    }

    public void setMenuItemVisible(final int i, final boolean z) {
        if (this._menuItemsHolder != null) {
            runOnUiThread(new Runnable() {
                public void run() {
                    MainNavigationActivity.this._menuItemsHolder.setItemVisible(i, z);
                    if (MainNavigationActivity.this.navigationDrawerWidget != null) {
                        MainNavigationActivity.this.navigationDrawerWidget.setOptions(MainNavigationActivity.this._menuItemsHolder.getAllItems());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void closeNavigationDrawerWidget() {
        NavigationDrawerWidget navigationDrawerWidget2 = this.navigationDrawerWidget;
        if (navigationDrawerWidget2 != null) {
            navigationDrawerWidget2.close();
        }
    }

    private void freezeAllDrawers() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(1);
        drawerLayout.setDrawerLockMode(1, 8388611);
        drawerLayout.setDrawerLockMode(1, 8388613);
    }

    private void showNavigationDrawerWidget() {
        this.navigationDrawerWidget.show();
        FirstLaunchController firstLaunchController = new FirstLaunchController(this);
        if (firstLaunchController.isFirstLaunch() && this._config.getTabsPosition() == WebWidgetConfiguration.TabsPositions.DRAWER && this._config.getApplicationTheme() != WebWidgetConfiguration.ApplicationThemes.ACTION_BAR) {
            this.navigationDrawerWidget.open();
            firstLaunchController.wasTheFirstLaunch();
        }
    }

    private void removeNavigationDrawerWidget() {
        NavigationDrawerWidget navigationDrawerWidget2 = this.navigationDrawerWidget;
        if (navigationDrawerWidget2 != null) {
            navigationDrawerWidget2.remove();
        }
    }

    public void setUrlBarVisibility(final int i) {
        runOnUiThread(new Runnable() {
            public void run() {
                INavigationWidget navigationWidget = Factory.getInstance().getNavigationWidget();
                if (navigationWidget == null) {
                    return;
                }
                if (i == 0) {
                    ((NavigationWidget) navigationWidget).showAnimated();
                } else {
                    ((NavigationWidget) navigationWidget).hideAnimated();
                }
            }
        });
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        ValueCallback<Integer> valueCallback;
        if (i != 4) {
            if (i == 35 && (valueCallback = this.permissionCallback) != null) {
                valueCallback.onReceiveValue(Integer.valueOf(iArr[0]));
                this.permissionCallback = null;
            }
        } else if (iArr.length > 0 && iArr[0] != 0) {
            Toast.makeText(this, getString(R.string.something_went_wrong), 0).show();
        }
    }

    public ArrayList<BrowsingHistoryItem> getWeeklyHistory() {
        ArrayList<BrowsingHistoryItem> arrayList = new ArrayList<>();
        INavigationWidget navigationWidget = Factory.getInstance().getNavigationWidget();
        return (navigationWidget == null || !(navigationWidget instanceof NavigationWidget)) ? arrayList : ((NavigationWidget) navigationWidget).getWeeklyHistory();
    }

    public int removeHistoryItem(long j) {
        INavigationWidget navigationWidget = Factory.getInstance().getNavigationWidget();
        if (navigationWidget == null || !(navigationWidget instanceof NavigationWidget)) {
            return -1;
        }
        return ((NavigationWidget) navigationWidget).removeHistoryItem(j);
    }

    public int removeHistoryAllItem() {
        INavigationWidget navigationWidget = Factory.getInstance().getNavigationWidget();
        if (navigationWidget == null || !(navigationWidget instanceof NavigationWidget)) {
            return -1;
        }
        return ((NavigationWidget) navigationWidget).removeHistoryAllItem();
    }

    public enum UserEvent {
        TOUCH("onTouch"),
        MENU_ITEM_CLICK("onMenuItemClick"),
        TAB_CHANGED("onTabChanged"),
        WEB_PAGES_CHANGED("onWebPageChanged"),
        PDF_EVENT("onPdfEvent");
        
        private String placementTag;

        private UserEvent(String str) {
            this.placementTag = str;
        }

        public String getPlacementTag() {
            return this.placementTag;
        }
    }
}
