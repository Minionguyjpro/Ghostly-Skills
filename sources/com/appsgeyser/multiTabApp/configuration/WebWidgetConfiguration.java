package com.appsgeyser.multiTabApp.configuration;

import com.appsgeyser.multiTabApp.MainNavigationActivity;
import java.io.Serializable;
import java.util.ArrayList;

public class WebWidgetConfiguration implements Serializable {
    private boolean acceptCookie = true;
    private String addUsageUrl;
    private String affiliateGetString = "";
    private String appGuid = "";
    private MainNavigationActivity.ApplicationMode appMode = MainNavigationActivity.ApplicationMode.UNKNOWN;
    private ApplicationThemes appTheme = ApplicationThemes.SLIDER;
    private int applicationId;
    private DownloadActions downloadAction = DownloadActions.OPEN;
    private boolean fullScreenBannerEnabled = false;
    private String httpAccessLogin;
    private String httpAccessPassword;
    private boolean isAboutScreenEnabled = true;
    private RedirectionTypes isRedirectEnabled = RedirectionTypes.REDIRECT_ALL;
    private boolean isSplashScreenEnabled = false;
    private String locationUrl;
    private boolean onExitFullScreenBannerEnabled = false;
    private boolean preventFromSleep;
    private String publisherName = "";
    private String pushAccount = "";
    private boolean rateItemVisibility = false;
    private String registeredUrl;
    private String shareExtraLink = "";
    private boolean showAboutMenuItem;
    private boolean showDownloadList;
    private boolean showExitMenuItem;
    private boolean showInAppMenuItem;
    private boolean showRefreshMenuItem;
    private boolean showSearchNotice = false;
    private boolean showSettings;
    private boolean showShareMenuItem;
    private boolean showStartupConfirmationDialog = false;
    private String splashScreenImage = "";
    private TabsEnabledHide tabsEnabledHide = TabsEnabledHide.DISABLED;
    private TabsPositions tabsPosition = TabsPositions.TOP;
    private UrlBarHide urlBarHide = UrlBarHide.DISABLED;
    private ArrayList<UrlBarMenuButton> urlBarMenuButtons = new ArrayList<>();
    private UrlBarStyles urlBarStyle = UrlBarStyles.BOTTOM;
    private UrlBarStates urlOverlayEnabled = UrlBarStates.DISABLED;
    private String widgetName;

    public enum ApplicationThemes {
        ACTION_BAR,
        SLIDER,
        NO_MENU
    }

    public enum DownloadActions {
        OPEN,
        SAVE,
        DIALOG
    }

    public enum RedirectionTypes {
        REDIRECT_ALL,
        REDIRECT_EXTERNAL,
        NO_REDIRECT
    }

    public enum TabsEnabledHide {
        ENABLED,
        DISABLED
    }

    public enum TabsPositions {
        TOP,
        BOTTOM,
        BOTTOM_MENU,
        DRAWER
    }

    public enum UrlBarHide {
        ENABLED,
        DISABLED
    }

    public enum UrlBarStates {
        ENABLED,
        ENABLED_ON_EXTERNAL_URLS,
        DISABLED
    }

    public enum UrlBarStyles {
        TOP,
        BOTTOM
    }

    public void addUrlBarMenuButton(UrlBarMenuButton urlBarMenuButton) {
        this.urlBarMenuButtons.add(urlBarMenuButton);
    }

    public RedirectionTypes getIsRedirectEnabled() {
        return this.isRedirectEnabled;
    }

    public void setIsRedirectEnabled(RedirectionTypes redirectionTypes) {
        this.isRedirectEnabled = redirectionTypes;
    }

    public ArrayList<UrlBarMenuButton> getUrlBarMenuButtons() {
        return this.urlBarMenuButtons;
    }

    public UrlBarStyles getUrlBarStyle() {
        return this.urlBarStyle;
    }

    public void setUrlBarStyle(UrlBarStyles urlBarStyles) {
        this.urlBarStyle = urlBarStyles;
    }

    public void setUrlBarHide(UrlBarHide urlBarHide2) {
        this.urlBarHide = urlBarHide2;
    }

    public String getAffiliateString() {
        return this.affiliateGetString;
    }

    public void setAffiliateString(String str) {
        this.affiliateGetString = str;
    }

    public void setPublisherName(String str) {
        this.publisherName = str;
    }

    public String getWidgetName() {
        return this.widgetName;
    }

    public void setWidgetName(String str) {
        this.widgetName = str;
    }

    public void setRegisteredUrl(String str) {
        this.registeredUrl = str;
    }

    public void setLocationUrl(String str) {
        this.locationUrl = str;
    }

    public int getApplicationId() {
        return this.applicationId;
    }

    public boolean getPreventFromSleep() {
        return this.preventFromSleep;
    }

    public void setApplicationId(int i) {
        this.applicationId = i;
    }

    public boolean getShowRefreshMenuItem() {
        return this.showRefreshMenuItem;
    }

    public void setShowRefreshMenuItem(boolean z) {
        this.showRefreshMenuItem = z;
    }

    public void setIsAboutScreenEnabled(boolean z) {
        this.isAboutScreenEnabled = z;
    }

    public void setAddUsageUrl(String str) {
        this.addUsageUrl = str;
    }

    public String getHttpAccessLogin() {
        return this.httpAccessLogin;
    }

    public void setHttpAccessLogin(String str) {
        this.httpAccessLogin = str;
    }

    public String getHttpAccessPassword() {
        return this.httpAccessPassword;
    }

    public void setHttpAccessPassword(String str) {
        this.httpAccessPassword = str;
    }

    public void setPreventFromSleep(boolean z) {
        this.preventFromSleep = z;
    }

    public MainNavigationActivity.ApplicationMode getApplicationMode() {
        return this.appMode;
    }

    public void setApplicationMode(MainNavigationActivity.ApplicationMode applicationMode) {
        this.appMode = applicationMode;
    }

    public ApplicationThemes getApplicationTheme() {
        return this.appTheme;
    }

    public void setApplicationTheme(ApplicationThemes applicationThemes) {
        this.appTheme = applicationThemes;
    }

    public void setRateItemVisibility(boolean z) {
        this.rateItemVisibility = z;
    }

    public void setAcceptCookie(boolean z) {
        this.acceptCookie = z;
    }

    public void setFullscreenBannerEnabled(boolean z) {
        this.fullScreenBannerEnabled = z;
    }

    public void setOnExitFullscreenBannerEnabled(boolean z) {
        this.onExitFullScreenBannerEnabled = z;
    }

    public UrlBarStates getUrlOverlayState() {
        return this.urlOverlayEnabled;
    }

    public void setUrlOverlayState(UrlBarStates urlBarStates) {
        this.urlOverlayEnabled = urlBarStates;
    }

    public boolean getShowShareMenuItem() {
        return this.showShareMenuItem;
    }

    public void setShowShareMenuItem(boolean z) {
        this.showShareMenuItem = z;
    }

    public void setShowAboutMenuItem(boolean z) {
        this.showAboutMenuItem = z;
    }

    public void setShowInAppMenuItem(boolean z) {
        this.showInAppMenuItem = z;
    }

    public boolean getShowExitMenuItem() {
        return this.showExitMenuItem;
    }

    public void setShowExitMenuItem(boolean z) {
        this.showExitMenuItem = z;
    }

    public void setSplashScreen(String str) {
        if (str != null) {
            this.isSplashScreenEnabled = true;
            this.splashScreenImage = str;
        }
    }

    public boolean isSplashScreenEnabled() {
        return this.isSplashScreenEnabled;
    }

    public String getSplashScreenImage() {
        return this.splashScreenImage;
    }

    public DownloadActions getDownloadAction() {
        return this.downloadAction;
    }

    public void setDownloadAction(DownloadActions downloadActions) {
        this.downloadAction = downloadActions;
    }

    public void setShowStartupConfirmationDialog(boolean z) {
        this.showStartupConfirmationDialog = z;
    }

    public String getShareExtraLink() {
        return this.shareExtraLink;
    }

    public void setShareExtraLink(String str) {
        this.shareExtraLink = str;
    }

    public TabsPositions getTabsPosition() {
        return this.tabsPosition;
    }

    public void setTabsPosition(TabsPositions tabsPositions) {
        this.tabsPosition = tabsPositions;
    }

    public boolean getShowDownloadList() {
        return this.showDownloadList;
    }

    public void setShowDownloadList(boolean z) {
        this.showDownloadList = z;
    }

    public boolean getShowSettings() {
        return this.showSettings;
    }

    public void setShowSettings(boolean z) {
        this.showSettings = z;
    }

    public void setTabsEnabledHide(TabsEnabledHide tabsEnabledHide2) {
        this.tabsEnabledHide = tabsEnabledHide2;
    }

    public boolean getShowSearchNotice() {
        return this.showSearchNotice;
    }

    public void setShowSearchNotice(boolean z) {
        this.showSearchNotice = z;
    }
}
