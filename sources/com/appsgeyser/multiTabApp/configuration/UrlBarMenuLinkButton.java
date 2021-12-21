package com.appsgeyser.multiTabApp.configuration;

import com.appsgeyser.multiTabApp.configuration.UrlBarMenuButton;
import java.io.Serializable;

public class UrlBarMenuLinkButton extends UrlBarMenuButton implements Serializable {
    private String title;
    private String url;

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    UrlBarMenuLinkButton(String str, String str2) {
        super(UrlBarMenuButton.UrlBarMenuButtonTypes.LINK);
        this.title = str;
        this.url = str2;
    }
}
