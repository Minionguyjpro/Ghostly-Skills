package com.appsgeyser.multiTabApp.model;

import android.net.Uri;
import com.appsgeyser.multiTabApp.configuration.IncludeScriptConfigEntity;
import java.io.Serializable;
import java.util.ArrayList;

public class WidgetEntity implements Serializable {
    private int _height = 0;
    private String _id;
    private String _injectJS;
    private ArrayList<IncludeScriptConfigEntity> _injectScripts;
    private String _link;
    private LoadingCurtainType _loadingCurtainType;
    private String _name;
    private boolean _showAsTab = true;
    private String _tabIcon;
    private String _tabId;
    private String _tabName;
    private String _tabType;
    private int _updateTime = 0;
    private String _userAgent;
    private int _width = 0;

    public enum LoadingCurtainType {
        NONE,
        DEFAULT,
        BANNER,
        CUSTOM
    }

    public boolean isShowAsTab() {
        return this._showAsTab;
    }

    public void setShowAsTab(boolean z) {
        this._showAsTab = z;
    }

    public void addScript(IncludeScriptConfigEntity includeScriptConfigEntity) {
        if (this._injectScripts == null) {
            this._injectScripts = new ArrayList<>();
        }
        this._injectScripts.add(includeScriptConfigEntity);
    }

    public String getId() {
        return this._id;
    }

    public void setId(String str) {
        this._id = str;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String str) {
        this._name = str;
    }

    public String getLink() {
        return this._link;
    }

    public void setLink(String str) {
        this._link = str;
    }

    public String getHostFromLink() {
        return Uri.parse(this._link).getHost();
    }

    public String getPathFromLink() {
        return Uri.parse(this._link).getPath();
    }

    public int getWidth() {
        return this._width;
    }

    public void setWidth(int i) {
        this._width = i;
    }

    public int getHeight() {
        return this._height;
    }

    public void setHeight(int i) {
        this._height = i;
    }

    public void setUpdateTime(int i) {
        this._updateTime = i;
    }

    public String getTabName() {
        return this._tabName;
    }

    public void setTabName(String str) {
        this._tabName = str;
    }

    public String getTabIcon() {
        return this._tabIcon;
    }

    public void setTabIcon(String str) {
        this._tabIcon = str;
    }

    public String getInjectJS() {
        return this._injectJS;
    }

    public ArrayList<IncludeScriptConfigEntity> getInjectScripts() {
        return this._injectScripts;
    }

    public void setInjectJS(String str) {
        this._injectJS = str;
    }

    public LoadingCurtainType getLoadingCurtainType() {
        return this._loadingCurtainType;
    }

    public void setLoadingCurtainType(LoadingCurtainType loadingCurtainType) {
        this._loadingCurtainType = loadingCurtainType;
    }

    public void setUserAgent(String str) {
        this._userAgent = str;
    }

    public void setTabId(String str) {
        this._tabId = str;
    }

    public String getTabId() {
        return this._tabId;
    }

    public String getTabType() {
        return this._tabType;
    }

    public void setTabType(String str) {
        this._tabType = str;
    }
}
