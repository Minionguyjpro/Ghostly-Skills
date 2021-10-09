package com.onesignal;

import java.util.List;
import org.json.JSONObject;

public class OSNotificationPayload {
    public List<ActionButton> actionButtons;
    public JSONObject additionalData;
    public BackgroundImageLayout backgroundImageLayout;
    public String bigPicture;
    public String body;
    public String collapseId;
    public String fromProjectNumber;
    public String groupKey;
    public String groupMessage;
    public String largeIcon;
    public String launchURL;
    public String ledColor;
    public int lockScreenVisibility = 1;
    public String notificationID;
    public int priority;
    public String rawPayload;
    public String smallIcon;
    public String smallIconAccentColor;
    public String sound;
    public String templateId;
    public String templateName;
    public String title;

    public static class ActionButton {
        public String icon;
        public String id;
        public String text;
    }

    public static class BackgroundImageLayout {
        public String bodyTextColor;
        public String image;
        public String titleTextColor;
    }
}
