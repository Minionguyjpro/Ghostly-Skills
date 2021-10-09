package com.appsgeyser.multiTabApp.configuration;

import com.appnext.ads.fullscreen.RewardedVideo;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.configuration.UrlBarMenuButton;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.appsgeyser.multiTabApp.controllers.WidgetsController;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import com.appsgeyser.multiTabApp.ui.views.TabContent;
import com.google.android.gms.plus.PlusShare;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLConfigurationHandler extends DefaultHandler {
    private WidgetEntity _currentWidgetEntity = null;
    private StringBuilder builder;
    private String current2ndParentTag = "";
    private String currentParentTag = "";
    private Boolean inInjectJsTag = false;
    private Boolean scriptTagsFound = false;
    private WebWidgetConfiguration webWidgetConfiguration;
    private WidgetsController widgetsController;

    XMLConfigurationHandler(WebWidgetConfiguration webWidgetConfiguration2, WidgetsController widgetsController2) {
        this.webWidgetConfiguration = webWidgetConfiguration2;
        this.widgetsController = widgetsController2;
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        super.characters(cArr, i, i2);
        String trim = String.copyValueOf(cArr, i, i2).trim();
        if (trim.length() != 0) {
            this.builder.append(trim);
        }
    }

    public void startDocument() throws SAXException {
        super.startDocument();
        this.builder = new StringBuilder();
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        String value;
        super.startElement(str, str2, str3, attributes);
        if (str2.equalsIgnoreCase("fullScreenMode")) {
            this.current2ndParentTag = str2;
        } else if (str2.equalsIgnoreCase("registeredUrl")) {
            this.currentParentTag = str2;
        } else if (str2.equalsIgnoreCase("usage")) {
            this.currentParentTag = str2;
        } else if (str2.equalsIgnoreCase("locationUrl")) {
            this.currentParentTag = str2;
        } else if (str2.equalsIgnoreCase(Constants.VAST_TRACKER_CONTENT)) {
            this.currentParentTag = str2;
            this._currentWidgetEntity = new WidgetEntity();
        } else if (str2.equalsIgnoreCase("contentProtection")) {
            this.currentParentTag = str2;
        } else if (str2.equalsIgnoreCase("affiliate")) {
            this.currentParentTag = str2;
        } else if (str2.equalsIgnoreCase("injectJS")) {
            this.inInjectJsTag = true;
        } else if (str2.equalsIgnoreCase("script")) {
            String value2 = attributes.getValue("pattern");
            String value3 = attributes.getValue("regexp");
            String value4 = attributes.getValue("file");
            if (value4 != null && this._currentWidgetEntity != null && this.inInjectJsTag.booleanValue()) {
                this._currentWidgetEntity.addScript(new IncludeScriptConfigEntity(value2, value3, value4));
                this.scriptTagsFound = true;
            }
        } else if (str2.equalsIgnoreCase("urlBarMenuItems")) {
            this.currentParentTag = str2;
        } else if (this.currentParentTag.equalsIgnoreCase("urlBarMenuItems") && str2.equalsIgnoreCase("button")) {
            String value5 = attributes.getValue("name");
            if (value5.equalsIgnoreCase("back")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButton.UrlBarMenuButtonTypes.BACK));
            } else if (value5.equalsIgnoreCase("forward")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButton.UrlBarMenuButtonTypes.FORWARD));
            } else if (value5.equalsIgnoreCase("refresh")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButton.UrlBarMenuButtonTypes.REFRESH));
            } else if (value5.equalsIgnoreCase("request_desktop")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButton.UrlBarMenuButtonTypes.REQUEST_DESKTOP));
            } else if (value5.equalsIgnoreCase("add_to_home")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButton.UrlBarMenuButtonTypes.ADD_TO_HOME));
            } else if (value5.equalsIgnoreCase("home")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButton.UrlBarMenuButtonTypes.HOME));
            } else if (value5.equalsIgnoreCase("downloads_list")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButton.UrlBarMenuButtonTypes.DOWNLOADS_LIST));
            } else if (value5.equalsIgnoreCase("link")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuLinkButton(attributes.getValue(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE), attributes.getValue("url")));
            } else if (value5.equalsIgnoreCase("icon")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarIcon(attributes.getValue(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE), attributes.getValue("url"), attributes.getValue("icon")));
            }
        } else if (str2.equalsIgnoreCase("splashScreen") && (value = attributes.getValue("image")) != null) {
            this.webWidgetConfiguration.setSplashScreen(value);
        }
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        super.endElement(str, str2, str3);
        if (str2.equalsIgnoreCase("fullScreenMode")) {
            this.current2ndParentTag = "";
        } else if (str2.equalsIgnoreCase("registeredUrl") || str2.equalsIgnoreCase("usage") || str2.equalsIgnoreCase("locationUrl") || str2.equalsIgnoreCase(Constants.VAST_TRACKER_CONTENT) || str2.equalsIgnoreCase("contentProtection") || str2.equalsIgnoreCase("affiliate") || str2.equalsIgnoreCase("urlBarMenuItems")) {
            this.currentParentTag = "";
            if (str2.equalsIgnoreCase(Constants.VAST_TRACKER_CONTENT)) {
                this.widgetsController.addWidget(this._currentWidgetEntity);
                this._currentWidgetEntity = null;
            }
        } else if (this.current2ndParentTag.equalsIgnoreCase("fullScreenMode")) {
            if (this.currentParentTag.equalsIgnoreCase(Constants.VAST_TRACKER_CONTENT)) {
                if (str2.equalsIgnoreCase("id")) {
                    WidgetEntity widgetEntity = this._currentWidgetEntity;
                    if (widgetEntity != null) {
                        widgetEntity.setId(this.builder.toString());
                    }
                } else if (str2.equalsIgnoreCase("name")) {
                    WidgetEntity widgetEntity2 = this._currentWidgetEntity;
                    if (widgetEntity2 != null) {
                        widgetEntity2.setName(this.builder.toString());
                    }
                } else if (str2.equalsIgnoreCase("link")) {
                    WidgetEntity widgetEntity3 = this._currentWidgetEntity;
                    if (widgetEntity3 != null) {
                        widgetEntity3.setLink(this.builder.toString());
                    }
                } else if (str2.equalsIgnoreCase("update")) {
                    if (this._currentWidgetEntity != null) {
                        if (this.builder.toString().length() > 0) {
                            this._currentWidgetEntity.setUpdateTime(Integer.parseInt(this.builder.toString()));
                        } else {
                            this._currentWidgetEntity.setUpdateTime(0);
                        }
                    }
                } else if (str2.equalsIgnoreCase("width")) {
                    WidgetEntity widgetEntity4 = this._currentWidgetEntity;
                    if (widgetEntity4 != null) {
                        widgetEntity4.setWidth(Integer.parseInt(this.builder.toString()));
                    }
                } else if (str2.equalsIgnoreCase("height")) {
                    WidgetEntity widgetEntity5 = this._currentWidgetEntity;
                    if (widgetEntity5 != null) {
                        widgetEntity5.setHeight(Integer.parseInt(this.builder.toString()));
                    }
                } else if (str2.equalsIgnoreCase("tabName")) {
                    WidgetEntity widgetEntity6 = this._currentWidgetEntity;
                    if (widgetEntity6 != null) {
                        widgetEntity6.setTabName(this.builder.toString());
                    }
                } else if (str2.equalsIgnoreCase("tabIcon")) {
                    WidgetEntity widgetEntity7 = this._currentWidgetEntity;
                    if (widgetEntity7 != null) {
                        widgetEntity7.setTabIcon(this.builder.toString());
                    }
                } else if (str2.equalsIgnoreCase("injectJS")) {
                    if (this._currentWidgetEntity != null && !this.scriptTagsFound.booleanValue()) {
                        this._currentWidgetEntity.setInjectJS(this.builder.toString());
                    }
                    this.scriptTagsFound = false;
                    this.inInjectJsTag = false;
                } else if (str2.equalsIgnoreCase("loadingCurtain")) {
                    if (this._currentWidgetEntity != null) {
                        String sb = this.builder.toString();
                        WidgetEntity.LoadingCurtainType loadingCurtainType = WidgetEntity.LoadingCurtainType.NONE;
                        if (sb.compareToIgnoreCase("none") == 0) {
                            loadingCurtainType = WidgetEntity.LoadingCurtainType.NONE;
                        } else if (sb.compareToIgnoreCase(RewardedVideo.VIDEO_MODE_DEFAULT) == 0) {
                            loadingCurtainType = WidgetEntity.LoadingCurtainType.DEFAULT;
                        } else if (sb.compareToIgnoreCase("banner") == 0) {
                            loadingCurtainType = WidgetEntity.LoadingCurtainType.BANNER;
                        } else if (sb.compareToIgnoreCase(AdType.CUSTOM) == 0) {
                            loadingCurtainType = WidgetEntity.LoadingCurtainType.CUSTOM;
                        }
                        this._currentWidgetEntity.setLoadingCurtainType(loadingCurtainType);
                    }
                } else if (str2.equalsIgnoreCase("tabType")) {
                    if (this._currentWidgetEntity != null) {
                        String sb2 = this.builder.toString();
                        TabContent.TabType tabType = TabContent.TabType.WEB;
                        if (sb2.compareToIgnoreCase("web") == 0) {
                            tabType = TabContent.TabType.WEB;
                        } else if (sb2.compareToIgnoreCase("pdf") == 0) {
                            tabType = TabContent.TabType.PDF;
                        }
                        this._currentWidgetEntity.setTabType(tabType.toString());
                    }
                } else if (str2.equalsIgnoreCase("userAgent")) {
                    WidgetEntity widgetEntity8 = this._currentWidgetEntity;
                    if (widgetEntity8 != null) {
                        widgetEntity8.setUserAgent(this.builder.toString());
                    }
                } else if (str2.equalsIgnoreCase("tabId")) {
                    this._currentWidgetEntity.setTabId(this.builder.toString());
                } else if (str2.equalsIgnoreCase("showAsTab")) {
                    this._currentWidgetEntity.setShowAsTab(this.builder.toString().equalsIgnoreCase("true"));
                }
            }
        } else if (this.currentParentTag.equalsIgnoreCase("registeredUrl")) {
            if (str2.equalsIgnoreCase("link")) {
                this.webWidgetConfiguration.setRegisteredUrl(this.builder.toString());
            }
        } else if (this.currentParentTag.equalsIgnoreCase("usage")) {
            if (str2.equalsIgnoreCase("link")) {
                this.webWidgetConfiguration.setAddUsageUrl(this.builder.toString());
            }
        } else if (this.currentParentTag.equalsIgnoreCase("locationUrl")) {
            if (str2.equalsIgnoreCase("link")) {
                this.webWidgetConfiguration.setLocationUrl(this.builder.toString());
            }
        } else if (str2.equalsIgnoreCase("id")) {
            this.webWidgetConfiguration.setApplicationId(Integer.parseInt(this.builder.toString().replaceAll("\n", "").replaceAll("\t", "")));
        } else if (str2.equalsIgnoreCase("widgetName")) {
            this.webWidgetConfiguration.setWidgetName(this.builder.toString());
        } else if (this.currentParentTag.equalsIgnoreCase("contentProtection")) {
            if (str2.equalsIgnoreCase("login")) {
                this.webWidgetConfiguration.setHttpAccessLogin(this.builder.toString());
            } else if (str2.equalsIgnoreCase("password")) {
                this.webWidgetConfiguration.setHttpAccessPassword(this.builder.toString());
            }
        } else if (str2.equalsIgnoreCase("userInterface")) {
            String sb3 = this.builder.toString();
            if (sb3.equals("standard")) {
                this.webWidgetConfiguration.setApplicationMode(MainNavigationActivity.ApplicationMode.COMMON);
            } else if (sb3.equals(AdType.CUSTOM)) {
                this.webWidgetConfiguration.setApplicationMode(MainNavigationActivity.ApplicationMode.CUSTOM);
            }
        } else if (str2.equalsIgnoreCase("rateItemVisibility")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setRateItemVisibility(true);
            } else {
                this.webWidgetConfiguration.setRateItemVisibility(false);
            }
        } else if (str2.equalsIgnoreCase("acceptCookie")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setAcceptCookie(true);
            } else {
                this.webWidgetConfiguration.setAcceptCookie(false);
            }
        } else if (str2.equalsIgnoreCase("preventFromSleep")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setPreventFromSleep(true);
            } else {
                this.webWidgetConfiguration.setPreventFromSleep(false);
            }
        } else if (str2.equalsIgnoreCase("showRefreshMenuItem")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setShowRefreshMenuItem(true);
            } else {
                this.webWidgetConfiguration.setShowRefreshMenuItem(false);
            }
        } else if (str2.equalsIgnoreCase("showShareMenuItem")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setShowShareMenuItem(true);
            } else {
                this.webWidgetConfiguration.setShowShareMenuItem(false);
            }
        } else if (str2.equalsIgnoreCase("showDownloadList")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setShowDownloadList(true);
            } else {
                this.webWidgetConfiguration.setShowDownloadList(false);
            }
        } else if (str2.equalsIgnoreCase("showAboutMenuItem")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setShowAboutMenuItem(true);
            } else {
                this.webWidgetConfiguration.setShowAboutMenuItem(false);
            }
        } else if (str2.equalsIgnoreCase("showInAppsMenuItem")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setShowInAppMenuItem(true);
            } else {
                this.webWidgetConfiguration.setShowInAppMenuItem(false);
            }
        } else if (str2.equalsIgnoreCase("showExitMenuItem")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setShowExitMenuItem(true);
            } else {
                this.webWidgetConfiguration.setShowExitMenuItem(false);
            }
        } else if (str2.equalsIgnoreCase("showSettings")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setShowSettings(true);
            } else {
                this.webWidgetConfiguration.setShowSettings(false);
            }
        } else if (str2.equalsIgnoreCase("shareExtraLink")) {
            this.webWidgetConfiguration.setShareExtraLink(this.builder.toString());
        } else if (str2.equalsIgnoreCase("enableFullScreenBanner")) {
            this.webWidgetConfiguration.setFullscreenBannerEnabled(this.builder.toString().equalsIgnoreCase("true"));
        } else if (str2.equalsIgnoreCase("enableOnExitFullScreenBanner")) {
            this.webWidgetConfiguration.setOnExitFullscreenBannerEnabled(this.builder.toString().equalsIgnoreCase("true"));
        } else if (str2.equalsIgnoreCase("showStartupConfirmationDialog")) {
            this.webWidgetConfiguration.setShowStartupConfirmationDialog(this.builder.toString().equalsIgnoreCase("true"));
        } else if (str2.equalsIgnoreCase("enableAboutScreen")) {
            this.webWidgetConfiguration.setIsAboutScreenEnabled(this.builder.toString().equalsIgnoreCase("true"));
        } else if (str2.equalsIgnoreCase("theme")) {
            String sb4 = this.builder.toString();
            if (sb4.equalsIgnoreCase("ACTION_BAR")) {
                this.webWidgetConfiguration.setApplicationTheme(WebWidgetConfiguration.ApplicationThemes.ACTION_BAR);
            } else if (sb4.equalsIgnoreCase("NO_MENU")) {
                this.webWidgetConfiguration.setApplicationTheme(WebWidgetConfiguration.ApplicationThemes.NO_MENU);
            } else {
                this.webWidgetConfiguration.setApplicationTheme(WebWidgetConfiguration.ApplicationThemes.SLIDER);
            }
        } else if (str2.equalsIgnoreCase("enableUrlBar")) {
            WebWidgetConfiguration.UrlBarStates urlBarStates = WebWidgetConfiguration.UrlBarStates.DISABLED;
            if (this.builder.toString().equalsIgnoreCase("true")) {
                urlBarStates = WebWidgetConfiguration.UrlBarStates.ENABLED;
            } else if (this.builder.toString().equalsIgnoreCase("on_external_urls")) {
                urlBarStates = WebWidgetConfiguration.UrlBarStates.ENABLED_ON_EXTERNAL_URLS;
            }
            this.webWidgetConfiguration.setUrlOverlayState(urlBarStates);
        } else if (str2.equalsIgnoreCase("enableHideUrlBar")) {
            WebWidgetConfiguration.UrlBarHide urlBarHide = WebWidgetConfiguration.UrlBarHide.ENABLED;
            if (this.builder.toString().equalsIgnoreCase("false")) {
                urlBarHide = WebWidgetConfiguration.UrlBarHide.DISABLED;
            }
            this.webWidgetConfiguration.setUrlBarHide(urlBarHide);
        } else if (str2.equalsIgnoreCase("showSearchNotice")) {
            if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setShowSearchNotice(true);
            } else {
                this.webWidgetConfiguration.setShowSearchNotice(false);
            }
        } else if (str2.equalsIgnoreCase("urlBarStyle")) {
            WebWidgetConfiguration.UrlBarStyles urlBarStyles = WebWidgetConfiguration.UrlBarStyles.BOTTOM;
            if (this.builder.toString().equalsIgnoreCase("top")) {
                urlBarStyles = WebWidgetConfiguration.UrlBarStyles.TOP;
            }
            this.webWidgetConfiguration.setUrlBarStyle(urlBarStyles);
        } else if (str2.equalsIgnoreCase("tabsPosition")) {
            WebWidgetConfiguration.TabsPositions tabsPositions = WebWidgetConfiguration.TabsPositions.TOP;
            if (this.builder.toString().equalsIgnoreCase("bottom")) {
                tabsPositions = WebWidgetConfiguration.TabsPositions.BOTTOM;
            } else if (this.builder.toString().equalsIgnoreCase("drawer")) {
                tabsPositions = WebWidgetConfiguration.TabsPositions.DRAWER;
            } else if (this.builder.toString().equalsIgnoreCase("bottom_menu")) {
                tabsPositions = WebWidgetConfiguration.TabsPositions.BOTTOM_MENU;
            }
            this.webWidgetConfiguration.setTabsPosition(tabsPositions);
        } else if (str2.equalsIgnoreCase("enableHideTabBar")) {
            WebWidgetConfiguration.TabsEnabledHide tabsEnabledHide = WebWidgetConfiguration.TabsEnabledHide.ENABLED;
            if (this.builder.toString().equalsIgnoreCase("false")) {
                tabsEnabledHide = WebWidgetConfiguration.TabsEnabledHide.DISABLED;
            }
            this.webWidgetConfiguration.setTabsEnabledHide(tabsEnabledHide);
        } else if (str2.equalsIgnoreCase("publisher")) {
            this.webWidgetConfiguration.setPublisherName(this.builder.toString());
        } else if (this.currentParentTag.equalsIgnoreCase("affiliate")) {
            if (str2.equalsIgnoreCase("getString")) {
                this.webWidgetConfiguration.setAffiliateString(this.builder.toString());
            }
        } else if (str2.equalsIgnoreCase("enableRedirection")) {
            if (this.builder.toString().equalsIgnoreCase("false")) {
                this.webWidgetConfiguration.setIsRedirectEnabled(WebWidgetConfiguration.RedirectionTypes.NO_REDIRECT);
            } else if (this.builder.toString().equalsIgnoreCase("true")) {
                this.webWidgetConfiguration.setIsRedirectEnabled(WebWidgetConfiguration.RedirectionTypes.REDIRECT_ALL);
            } else {
                this.webWidgetConfiguration.setIsRedirectEnabled(WebWidgetConfiguration.RedirectionTypes.REDIRECT_EXTERNAL);
            }
        } else if (str2.equalsIgnoreCase("downloadAction")) {
            String lowerCase = this.builder.toString().toLowerCase();
            WebWidgetConfiguration.DownloadActions downloadActions = WebWidgetConfiguration.DownloadActions.DIALOG;
            if (lowerCase.equals("open")) {
                downloadActions = WebWidgetConfiguration.DownloadActions.OPEN;
            } else if (lowerCase.equals("save")) {
                downloadActions = WebWidgetConfiguration.DownloadActions.SAVE;
            } else if (lowerCase.equals("dialog")) {
                downloadActions = WebWidgetConfiguration.DownloadActions.DIALOG;
            }
            this.webWidgetConfiguration.setDownloadAction(downloadActions);
        }
        this.builder.setLength(0);
    }
}
