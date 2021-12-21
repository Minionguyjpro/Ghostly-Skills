package com.onesignal.influence.model;

public enum OSInfluenceChannel {
    IAM("iam"),
    NOTIFICATION("notification");
    
    private final String name;

    private OSInfluenceChannel(String str) {
        this.name = str;
    }

    public boolean equalsName(String str) {
        return this.name.equals(str);
    }

    public String toString() {
        return this.name;
    }

    public static OSInfluenceChannel fromString(String str) {
        if (str == null || str.isEmpty()) {
            return NOTIFICATION;
        }
        for (OSInfluenceChannel oSInfluenceChannel : values()) {
            if (oSInfluenceChannel.equalsName(str)) {
                return oSInfluenceChannel;
            }
        }
        return NOTIFICATION;
    }
}
