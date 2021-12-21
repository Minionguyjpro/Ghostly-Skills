package com.mopub.mobileads;

import kotlin.text.StringsKt;

/* compiled from: VideoTrackingEvent.kt */
public enum VideoTrackingEvent {
    START("start"),
    FIRST_QUARTILE("firstQuartile"),
    MIDPOINT("midpoint"),
    THIRD_QUARTILE("thirdQuartile"),
    COMPLETE("complete"),
    COMPANION_AD_VIEW("companionAdView"),
    COMPANION_AD_CLICK("companionAdClick"),
    UNKNOWN("");
    
    public static final Companion Companion = null;
    private final String value;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] iArr = new int[VideoTrackingEvent.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[VideoTrackingEvent.FIRST_QUARTILE.ordinal()] = 1;
            $EnumSwitchMapping$0[VideoTrackingEvent.MIDPOINT.ordinal()] = 2;
            $EnumSwitchMapping$0[VideoTrackingEvent.THIRD_QUARTILE.ordinal()] = 3;
            $EnumSwitchMapping$0[VideoTrackingEvent.COMPLETE.ordinal()] = 4;
        }
    }

    private VideoTrackingEvent(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    /* compiled from: VideoTrackingEvent.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VideoTrackingEvent fromString(String str) {
            VideoTrackingEvent videoTrackingEvent;
            VideoTrackingEvent[] values = VideoTrackingEvent.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    videoTrackingEvent = null;
                    break;
                }
                videoTrackingEvent = values[i];
                if (StringsKt.equals(videoTrackingEvent.getValue(), str, true)) {
                    break;
                }
                i++;
            }
            return videoTrackingEvent != null ? videoTrackingEvent : VideoTrackingEvent.UNKNOWN;
        }
    }

    public final float toFloat() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 0.25f;
        }
        if (i == 2) {
            return 0.5f;
        }
        if (i != 3) {
            return i != 4 ? 0.0f : 1.0f;
        }
        return 0.75f;
    }
}
