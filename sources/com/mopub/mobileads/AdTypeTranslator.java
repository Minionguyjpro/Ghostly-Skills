package com.mopub.mobileads;

import com.mopub.common.AdType;

public class AdTypeTranslator {
    public static final String BANNER_SUFFIX = "_banner";
    public static final String INTERSTITIAL_SUFFIX = "_interstitial";
    private static final int NEW_VAST_PLAYER_FLAG = 2;
    private static final String NEW_VAST_PLAYER_SUFFIX = "_two";

    public enum CustomEventType {
        GOOGLE_PLAY_SERVICES_BANNER("admob_native_banner", "com.mopub.mobileads.GooglePlayServicesBanner", false),
        GOOGLE_PLAY_SERVICES_INTERSTITIAL("admob_full_interstitial", "com.mopub.mobileads.GooglePlayServicesInterstitial", false),
        MRAID_BANNER("mraid_banner", "com.mopub.mraid.MraidBanner", true),
        MRAID_INTERSTITIAL("mraid_interstitial", "com.mopub.mraid.MraidInterstitial", true),
        HTML_BANNER("html_banner", "com.mopub.mobileads.HtmlBanner", true),
        HTML_INTERSTITIAL("html_interstitial", "com.mopub.mobileads.HtmlInterstitial", true),
        VAST_VIDEO_INTERSTITIAL("vast_interstitial", "com.mopub.mobileads.VastVideoInterstitial", true),
        VAST_VIDEO_INTERSTITIAL_TWO("vast_interstitial_two", "com.mopub.mobileads.VastVideoInterstitialTwo", true),
        MOPUB_NATIVE("mopub_native", "com.mopub.nativeads.MoPubCustomEventNative", true),
        MOPUB_VIDEO_NATIVE("mopub_video_native", "com.mopub.nativeads.MoPubCustomEventVideoNative", true),
        MOPUB_REWARDED_VIDEO(AdType.REWARDED_VIDEO, "com.mopub.mobileads.MoPubRewardedVideo", true),
        MOPUB_REWARDED_VIDEO_TWO("rewarded_video_two", "com.mopub.mobileads.MoPubRewardedVideoTwo", true),
        MOPUB_REWARDED_PLAYABLE(AdType.REWARDED_PLAYABLE, "com.mopub.mobileads.MoPubRewardedPlayable", true),
        UNSPECIFIED("", (int) null, false);
        
        private final String mClassName;
        private final boolean mIsMoPubSpecific;
        private final String mKey;

        private CustomEventType(String str, String str2, boolean z) {
            this.mKey = str;
            this.mClassName = str2;
            this.mIsMoPubSpecific = z;
        }

        /* access modifiers changed from: private */
        public static CustomEventType fromString(String str) {
            for (CustomEventType customEventType : values()) {
                if (customEventType.mKey.equals(str)) {
                    return customEventType;
                }
            }
            return UNSPECIFIED;
        }

        private static CustomEventType fromClassName(String str) {
            for (CustomEventType customEventType : values()) {
                String str2 = customEventType.mClassName;
                if (str2 != null && str2.equals(str)) {
                    return customEventType;
                }
            }
            return UNSPECIFIED;
        }

        public String toString() {
            return this.mClassName;
        }

        public static boolean isMoPubSpecific(String str) {
            return fromClassName(str).mIsMoPubSpecific;
        }
    }

    static String getAdNetworkType(String str, String str2) {
        if (AdType.INTERSTITIAL.equals(str)) {
            str = str2;
        }
        return str != null ? str : "unknown";
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        if (r3.equals(com.mopub.common.AdType.STATIC_NATIVE) != false) goto L_0x0076;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getCustomEventName(com.mopub.common.AdFormat r6, java.lang.String r7, java.lang.String r8, org.json.JSONObject r9) {
        /*
            com.mopub.common.Preconditions.checkNotNull(r6)
            com.mopub.common.Preconditions.checkNotNull(r7)
            com.mopub.common.util.ResponseHeader r0 = com.mopub.common.util.ResponseHeader.VAST_VIDEO_PLAYER_VERSION
            r1 = 1
            java.lang.Integer r0 = com.mopub.network.HeaderUtils.extractIntegerHeader(r9, r0, r1)
            int r0 = r0.intValue()
            r2 = 2
            if (r2 != r0) goto L_0x0017
            java.lang.String r0 = "_two"
            goto L_0x0019
        L_0x0017:
            java.lang.String r0 = ""
        L_0x0019:
            java.lang.String r3 = r7.toLowerCase()
            r4 = -1
            int r5 = r3.hashCode()
            switch(r5) {
                case -1364000502: goto L_0x006b;
                case -1349088399: goto L_0x0061;
                case 3213227: goto L_0x0057;
                case 3271912: goto L_0x004e;
                case 104156535: goto L_0x0044;
                case 474479519: goto L_0x003a;
                case 604727084: goto L_0x0030;
                case 797120100: goto L_0x0026;
                default: goto L_0x0025;
            }
        L_0x0025:
            goto L_0x0075
        L_0x0026:
            java.lang.String r1 = "json_video"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0075
            r1 = 2
            goto L_0x0076
        L_0x0030:
            java.lang.String r1 = "interstitial"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0075
            r1 = 7
            goto L_0x0076
        L_0x003a:
            java.lang.String r1 = "rewarded_playable"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0075
            r1 = 4
            goto L_0x0076
        L_0x0044:
            java.lang.String r1 = "mraid"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0075
            r1 = 6
            goto L_0x0076
        L_0x004e:
            java.lang.String r2 = "json"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0075
            goto L_0x0076
        L_0x0057:
            java.lang.String r1 = "html"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0075
            r1 = 5
            goto L_0x0076
        L_0x0061:
            java.lang.String r1 = "custom"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0075
            r1 = 0
            goto L_0x0076
        L_0x006b:
            java.lang.String r1 = "rewarded_video"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0075
            r1 = 3
            goto L_0x0076
        L_0x0075:
            r1 = -1
        L_0x0076:
            java.lang.String r2 = "_banner"
            java.lang.String r3 = "_interstitial"
            switch(r1) {
                case 0: goto L_0x0111;
                case 1: goto L_0x010a;
                case 2: goto L_0x0103;
                case 3: goto L_0x00eb;
                case 4: goto L_0x00e4;
                case 5: goto L_0x00b0;
                case 6: goto L_0x00b0;
                case 7: goto L_0x0095;
                default: goto L_0x007d;
            }
        L_0x007d:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r7)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            com.mopub.mobileads.AdTypeTranslator$CustomEventType r6 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.fromString(r6)
            java.lang.String r6 = r6.toString()
            return r6
        L_0x0095:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r8)
            r6.append(r3)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            com.mopub.mobileads.AdTypeTranslator$CustomEventType r6 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.fromString(r6)
            java.lang.String r6 = r6.toString()
            return r6
        L_0x00b0:
            com.mopub.common.AdFormat r8 = com.mopub.common.AdFormat.INTERSTITIAL
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x00cc
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r7)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            com.mopub.mobileads.AdTypeTranslator$CustomEventType r6 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.fromString(r6)
            goto L_0x00df
        L_0x00cc:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r7)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            com.mopub.mobileads.AdTypeTranslator$CustomEventType r6 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.fromString(r6)
        L_0x00df:
            java.lang.String r6 = r6.toString()
            return r6
        L_0x00e4:
            com.mopub.mobileads.AdTypeTranslator$CustomEventType r6 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.MOPUB_REWARDED_PLAYABLE
            java.lang.String r6 = r6.toString()
            return r6
        L_0x00eb:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r7)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            com.mopub.mobileads.AdTypeTranslator$CustomEventType r6 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.fromString(r6)
            java.lang.String r6 = r6.toString()
            return r6
        L_0x0103:
            com.mopub.mobileads.AdTypeTranslator$CustomEventType r6 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.MOPUB_VIDEO_NATIVE
            java.lang.String r6 = r6.toString()
            return r6
        L_0x010a:
            com.mopub.mobileads.AdTypeTranslator$CustomEventType r6 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.MOPUB_NATIVE
            java.lang.String r6 = r6.toString()
            return r6
        L_0x0111:
            com.mopub.common.util.ResponseHeader r6 = com.mopub.common.util.ResponseHeader.CUSTOM_EVENT_NAME
            java.lang.String r6 = com.mopub.network.HeaderUtils.extractHeader(r9, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.AdTypeTranslator.getCustomEventName(com.mopub.common.AdFormat, java.lang.String, java.lang.String, org.json.JSONObject):java.lang.String");
    }
}
