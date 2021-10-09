package com.mopub.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.mopub.common.ExternalViewabilitySession;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection;
import com.mopub.mobileads.VastExtensionXmlManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MoatViewabilitySession implements ExternalViewabilitySession {
    private static final String DEFAULT_PARTNER_CODE = "mopubinapphtmvideo468906546585";
    private static final String MOAT_AD_EVENT_PATH = "com.moat.analytics.mobile.mpub.MoatAdEvent";
    private static final String MOAT_AD_EVENT_TYPE_PATH = "com.moat.analytics.mobile.mpub.MoatAdEventType";
    private static final String MOAT_ANALYTICS_PATH = "com.moat.analytics.mobile.mpub.MoatAnalytics";
    private static final String MOAT_FACTORY_PATH = "com.moat.analytics.mobile.mpub.MoatFactory";
    private static final String MOAT_KEY = "moat";
    private static final String MOAT_OPTIONS_PATH = "com.moat.analytics.mobile.mpub.MoatOptions";
    private static final String MOAT_PLUGIN_PATH = "com.moat.analytics.mobile.mpub.MoatPlugin";
    private static final String MOAT_REACTIVE_VIDEO_TRACKER_PLUGIN_PATH = "com.moat.analytics.mobile.mpub.ReactiveVideoTrackerPlugin";
    private static final String MOAT_VAST_IDS_KEY = "zMoatVASTIDs";
    private static final String PARTNER_CODE_KEY = "partnerCode";
    private static final Map<String, String> QUERY_PARAM_MAPPING;
    private static boolean sIsVendorDisabled = false;
    private static Boolean sIsViewabilityEnabledViaReflection = null;
    private static boolean sWasInitialized = false;
    private Map<String, String> mAdIds = new HashMap();
    private Object mMoatVideoTracker;
    private Object mMoatWebAdTracker;
    private boolean mWasVideoPrepared;

    public String getName() {
        return VastExtensionXmlManager.MOAT;
    }

    MoatViewabilitySession() {
    }

    static {
        HashMap hashMap = new HashMap();
        QUERY_PARAM_MAPPING = hashMap;
        hashMap.put("moatClientLevel1", "level1");
        QUERY_PARAM_MAPPING.put("moatClientLevel2", "level2");
        QUERY_PARAM_MAPPING.put("moatClientLevel3", "level3");
        QUERY_PARAM_MAPPING.put("moatClientLevel4", "level4");
        QUERY_PARAM_MAPPING.put("moatClientSlicer1", "slicer1");
        QUERY_PARAM_MAPPING.put("moatClientSlicer2", "slicer2");
    }

    static boolean isEnabled() {
        return !sIsVendorDisabled && isViewabilityEnabledViaReflection();
    }

    static void disable() {
        sIsVendorDisabled = true;
    }

    private static boolean isViewabilityEnabledViaReflection() {
        if (sIsViewabilityEnabledViaReflection == null) {
            sIsViewabilityEnabledViaReflection = Boolean.valueOf(Reflection.classFound(MOAT_FACTORY_PATH));
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("Moat is ");
            sb.append(sIsViewabilityEnabledViaReflection.booleanValue() ? "" : "un");
            sb.append("available via reflection.");
            objArr[0] = sb.toString();
            MoPubLog.log(sdkLogEvent, objArr);
        }
        return sIsViewabilityEnabledViaReflection.booleanValue();
    }

    public Boolean initialize(Context context) {
        Application application;
        Preconditions.checkNotNull(context);
        if (!isEnabled()) {
            return null;
        }
        if (sWasInitialized) {
            return true;
        }
        if (context instanceof Activity) {
            application = ((Activity) context).getApplication();
        } else {
            try {
                application = (Application) context.getApplicationContext();
            } catch (ClassCastException unused) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unable to initialize Moat, error obtaining application context.");
                return false;
            }
        }
        try {
            Object instantiateClassWithEmptyConstructor = Reflection.instantiateClassWithEmptyConstructor(MOAT_OPTIONS_PATH, Object.class);
            instantiateClassWithEmptyConstructor.getClass().getField("disableAdIdCollection").setBoolean(instantiateClassWithEmptyConstructor, true);
            instantiateClassWithEmptyConstructor.getClass().getField("disableLocationServices").setBoolean(instantiateClassWithEmptyConstructor, true);
            new Reflection.MethodBuilder(new Reflection.MethodBuilder((Object) null, "getInstance").setStatic(MOAT_ANALYTICS_PATH).execute(), "start").addParam(MOAT_OPTIONS_PATH, instantiateClassWithEmptyConstructor).addParam(Application.class, application).execute();
            sWasInitialized = true;
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to initialize Moat: " + e.getMessage());
            return false;
        }
    }

    public Boolean invalidate() {
        if (!isEnabled()) {
            return null;
        }
        this.mMoatWebAdTracker = null;
        this.mMoatVideoTracker = null;
        this.mAdIds.clear();
        return true;
    }

    public Boolean createDisplaySession(Context context, WebView webView, boolean z) {
        Preconditions.checkNotNull(context);
        if (!isEnabled()) {
            return null;
        }
        try {
            Object execute = new Reflection.MethodBuilder(new Reflection.MethodBuilder((Object) null, "create").setStatic(MOAT_FACTORY_PATH).execute(), "createWebAdTracker").addParam(WebView.class, webView).execute();
            this.mMoatWebAdTracker = execute;
            if (!z) {
                new Reflection.MethodBuilder(execute, "startTracking").execute();
            }
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to execute Moat start display session: " + e.getMessage());
            return false;
        }
    }

    public Boolean startDeferredDisplaySession(Activity activity) {
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mMoatWebAdTracker;
        if (obj == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "MoatWebAdTracker unexpectedly null.");
            return false;
        }
        try {
            new Reflection.MethodBuilder(obj, "startTracking").execute();
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to record deferred display session for Moat: " + e.getMessage());
            return false;
        }
    }

    public Boolean endDisplaySession() {
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mMoatWebAdTracker;
        if (obj == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Moat WebAdTracker unexpectedly null.");
            return false;
        }
        try {
            new Reflection.MethodBuilder(obj, "stopTracking").execute();
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to execute Moat end session: " + e.getMessage());
            return false;
        }
    }

    public Boolean createVideoSession(Activity activity, View view, Set<String> set, Map<String, String> map) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(set);
        Preconditions.checkNotNull(map);
        if (!isEnabled()) {
            return null;
        }
        updateAdIdsFromUrlStringAndBuyerResources(map.get(MOAT_KEY), set);
        String str = this.mAdIds.get(PARTNER_CODE_KEY);
        if (TextUtils.isEmpty(str)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "partnerCode was empty when starting Moat video session");
            return false;
        }
        try {
            this.mMoatVideoTracker = new Reflection.MethodBuilder(new Reflection.MethodBuilder((Object) null, "create").setStatic(MOAT_FACTORY_PATH).execute(), "createCustomTracker").addParam(MOAT_PLUGIN_PATH, Reflection.instantiateClassWithConstructor(MOAT_REACTIVE_VIDEO_TRACKER_PLUGIN_PATH, Object.class, new Class[]{String.class}, new Object[]{str})).execute();
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to execute Moat start video session: " + e.getMessage());
            return false;
        }
    }

    public Boolean registerVideoObstruction(View view) {
        Preconditions.checkNotNull(view);
        if (!isEnabled()) {
            return null;
        }
        return true;
    }

    public Boolean onVideoPrepared(View view, int i) {
        Preconditions.checkNotNull(view);
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mMoatVideoTracker;
        if (obj == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Moat VideoAdTracker unexpectedly null.");
            return false;
        } else if (this.mWasVideoPrepared) {
            return false;
        } else {
            try {
                new Reflection.MethodBuilder(obj, "trackVideoAd").addParam(Map.class, this.mAdIds).addParam(Integer.class, Integer.valueOf(i)).addParam(View.class, view).execute();
                this.mWasVideoPrepared = true;
                return true;
            } catch (Exception e) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "Unable to execute Moat onVideoPrepared: " + e.getMessage());
                return false;
            }
        }
    }

    public Boolean recordVideoEvent(ExternalViewabilitySession.VideoEvent videoEvent, int i) {
        Preconditions.checkNotNull(videoEvent);
        if (!isEnabled()) {
            return null;
        }
        if (this.mMoatVideoTracker == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Moat VideoAdTracker unexpectedly null.");
            return false;
        }
        try {
            switch (AnonymousClass1.$SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent[videoEvent.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    handleVideoEventReflection(videoEvent, i);
                    return true;
                case 10:
                case 11:
                case 12:
                case 13:
                    return null;
                default:
                    MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                    MoPubLog.log(sdkLogEvent, "Unexpected video event: " + videoEvent.getMoatEnumName());
                    return false;
            }
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent2, "Video event " + videoEvent.getMoatEnumName() + " failed. " + e.getMessage());
            return false;
        }
    }

    /* renamed from: com.mopub.common.MoatViewabilitySession$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(3:25|26|28)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.mopub.common.ExternalViewabilitySession$VideoEvent[] r0 = com.mopub.common.ExternalViewabilitySession.VideoEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent = r0
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_STARTED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_STOPPED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_PAUSED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_PLAYING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x003e }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_SKIPPED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_VIDEO_FIRST_QUARTILE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_VIDEO_MIDPOINT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_VIDEO_THIRD_QUARTILE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x006c }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_COMPLETE     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_LOADED     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_IMPRESSED     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_CLICK_THRU     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x009c }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.RECORD_AD_ERROR     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.MoatViewabilitySession.AnonymousClass1.<clinit>():void");
        }
    }

    public Boolean endVideoSession() {
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mMoatVideoTracker;
        if (obj == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Moat VideoAdTracker unexpectedly null.");
            return false;
        }
        try {
            new Reflection.MethodBuilder(obj, "stopTracking").execute();
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to execute Moat end video session: " + e.getMessage());
            return false;
        }
    }

    private void updateAdIdsFromUrlStringAndBuyerResources(String str, Set<String> set) {
        this.mAdIds.clear();
        this.mAdIds.put(PARTNER_CODE_KEY, DEFAULT_PARTNER_CODE);
        this.mAdIds.put(MOAT_VAST_IDS_KEY, TextUtils.join(";", set));
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            List<String> pathSegments = parse.getPathSegments();
            if (pathSegments.size() > 0 && !TextUtils.isEmpty(pathSegments.get(0))) {
                this.mAdIds.put(PARTNER_CODE_KEY, pathSegments.get(0));
            }
            String fragment = parse.getFragment();
            if (!TextUtils.isEmpty(fragment)) {
                for (String split : fragment.split("&")) {
                    String[] split2 = split.split("=");
                    if (split2.length >= 2) {
                        String str2 = split2[0];
                        String str3 = split2[1];
                        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && QUERY_PARAM_MAPPING.containsKey(str2)) {
                            this.mAdIds.put(QUERY_PARAM_MAPPING.get(str2), str3);
                        }
                    }
                }
            }
        }
    }

    private boolean handleVideoEventReflection(ExternalViewabilitySession.VideoEvent videoEvent, int i) throws Exception {
        if (videoEvent.getMoatEnumName() == null) {
            return false;
        }
        Class<?> cls = Class.forName(MOAT_AD_EVENT_TYPE_PATH);
        new Reflection.MethodBuilder(this.mMoatVideoTracker, "dispatchEvent").addParam(MOAT_AD_EVENT_PATH, Reflection.instantiateClassWithConstructor(MOAT_AD_EVENT_PATH, Object.class, new Class[]{cls, Integer.class}, new Object[]{Enum.valueOf(cls.asSubclass(Enum.class), videoEvent.getMoatEnumName()), Integer.valueOf(i)})).execute();
        return true;
    }
}
