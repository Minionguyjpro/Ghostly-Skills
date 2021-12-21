package com.onesignal;

import java.util.List;

public class OSNotification {
    public int androidNotificationId;
    public DisplayType displayType;
    public List<OSNotificationPayload> groupedNotifications;
    public boolean isAppInFocus;
    public OSNotificationPayload payload;
    public boolean shown;

    public enum DisplayType {
        Notification,
        InAppAlert,
        None
    }
}
