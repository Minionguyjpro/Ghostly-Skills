package com.appsgeyser.multiTabApp.configuration;

import com.appsgeyser.multiTabApp.configuration.UrlBarMenuButton;
import java.io.Serializable;

public class UrlBarIcon extends UrlBarMenuLinkButton implements Serializable {
    private String icon;

    public UrlBarIcon(String str, String str2, String str3) {
        super(str, str2);
        setType(UrlBarMenuButton.UrlBarMenuButtonTypes.ICON);
        this.icon = str3;
    }
}
