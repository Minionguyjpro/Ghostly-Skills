package com.mopub.mraid;

public enum MraidJavascriptCommand {
    CLOSE("close"),
    EXPAND("expand") {
        /* access modifiers changed from: package-private */
        public boolean requiresClick(PlacementType placementType) {
            return placementType == PlacementType.INLINE;
        }
    },
    USE_CUSTOM_CLOSE("usecustomclose"),
    OPEN("open") {
        /* access modifiers changed from: package-private */
        public boolean requiresClick(PlacementType placementType) {
            return true;
        }
    },
    RESIZE("resize") {
        /* access modifiers changed from: package-private */
        public boolean requiresClick(PlacementType placementType) {
            return true;
        }
    },
    SET_ORIENTATION_PROPERTIES("setOrientationProperties"),
    PLAY_VIDEO("playVideo") {
        /* access modifiers changed from: package-private */
        public boolean requiresClick(PlacementType placementType) {
            return placementType == PlacementType.INLINE;
        }
    },
    STORE_PICTURE("storePicture") {
        /* access modifiers changed from: package-private */
        public boolean requiresClick(PlacementType placementType) {
            return true;
        }
    },
    CREATE_CALENDAR_EVENT("createCalendarEvent") {
        /* access modifiers changed from: package-private */
        public boolean requiresClick(PlacementType placementType) {
            return true;
        }
    },
    UNSPECIFIED("");
    
    private final String mJavascriptString;

    /* access modifiers changed from: package-private */
    public boolean requiresClick(PlacementType placementType) {
        return false;
    }

    private MraidJavascriptCommand(String str) {
        this.mJavascriptString = str;
    }

    static MraidJavascriptCommand fromJavascriptString(String str) {
        for (MraidJavascriptCommand mraidJavascriptCommand : values()) {
            if (mraidJavascriptCommand.mJavascriptString.equals(str)) {
                return mraidJavascriptCommand;
            }
        }
        return UNSPECIFIED;
    }

    /* access modifiers changed from: package-private */
    public String toJavascriptString() {
        return this.mJavascriptString;
    }
}
