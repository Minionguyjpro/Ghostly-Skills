package com.appsgeyser.multiTabApp.configuration;

import java.io.Serializable;

public class UrlBarMenuButton implements Serializable {
    private UrlBarMenuButtonTypes type;

    public enum UrlBarMenuButtonTypes {
        BACK,
        FORWARD,
        REFRESH,
        REQUEST_DESKTOP,
        ADD_TO_HOME,
        HOME,
        URL_BUTTON,
        LINK,
        ICON,
        DOWNLOADS_LIST
    }

    UrlBarMenuButton(UrlBarMenuButtonTypes urlBarMenuButtonTypes) {
        this.type = urlBarMenuButtonTypes;
    }

    public UrlBarMenuButtonTypes getType() {
        return this.type;
    }

    public void setType(UrlBarMenuButtonTypes urlBarMenuButtonTypes) {
        this.type = urlBarMenuButtonTypes;
    }
}
