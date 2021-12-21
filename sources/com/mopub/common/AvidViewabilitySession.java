package com.mopub.common;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.mopub.common.ExternalViewabilitySession;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection;
import com.mopub.mobileads.VastExtensionXmlManager;
import java.util.Map;
import java.util.Set;

class AvidViewabilitySession implements ExternalViewabilitySession {
    private static final String AVID_AD_SESSION_MANAGER_PATH = "com.integralads.avid.library.mopub.session.AvidAdSessionManager";
    private static final String AVID_KEY = "avid";
    private static final String AVID_MANAGER_PATH = "com.integralads.avid.library.mopub.AvidManager";
    private static final String EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH = "com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext";
    private static Object sAvidAdSessionContextDeferred;
    private static Object sAvidAdSessionContextNonDeferred;
    private static boolean sIsVendorDisabled;
    private static Boolean sIsViewabilityEnabledViaReflection;
    private Object mAvidDisplayAdSession;
    private Object mAvidVideoAdSession;

    public String getName() {
        return VastExtensionXmlManager.AVID;
    }

    AvidViewabilitySession() {
    }

    static boolean isEnabled() {
        return !sIsVendorDisabled && isViewabilityEnabledViaReflection();
    }

    static void disable() {
        sIsVendorDisabled = true;
    }

    private static boolean isViewabilityEnabledViaReflection() {
        if (sIsViewabilityEnabledViaReflection == null) {
            sIsViewabilityEnabledViaReflection = Boolean.valueOf(Reflection.classFound(AVID_AD_SESSION_MANAGER_PATH));
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("Avid is ");
            sb.append(sIsViewabilityEnabledViaReflection.booleanValue() ? "" : "un");
            sb.append("available via reflection.");
            objArr[0] = sb.toString();
            MoPubLog.log(sdkLogEvent, objArr);
        }
        return sIsViewabilityEnabledViaReflection.booleanValue();
    }

    private static Object getAvidAdSessionContextDeferred() {
        if (sAvidAdSessionContextDeferred == null) {
            Class<Object> cls = Object.class;
            try {
                sAvidAdSessionContextDeferred = Reflection.instantiateClassWithConstructor(EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH, cls, new Class[]{String.class, Boolean.TYPE}, new Object[]{"5.12.0", true});
            } catch (Exception e) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "Unable to generate Avid deferred ad session context: " + e.getMessage());
            }
        }
        return sAvidAdSessionContextDeferred;
    }

    private static Object getAvidAdSessionContextNonDeferred() {
        if (sAvidAdSessionContextNonDeferred == null) {
            try {
                sAvidAdSessionContextNonDeferred = Reflection.instantiateClassWithConstructor(EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH, Object.class, new Class[]{String.class}, new Object[]{"5.12.0"});
            } catch (Exception e) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "Unable to generate Avid ad session context: " + e.getMessage());
            }
        }
        return sAvidAdSessionContextNonDeferred;
    }

    public Boolean initialize(Context context) {
        Preconditions.checkNotNull(context);
        if (!isEnabled()) {
            return null;
        }
        return true;
    }

    public Boolean invalidate() {
        if (!isEnabled()) {
            return null;
        }
        this.mAvidDisplayAdSession = null;
        this.mAvidVideoAdSession = null;
        return true;
    }

    public Boolean createDisplaySession(Context context, WebView webView, boolean z) {
        Object obj;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(webView);
        if (!isEnabled()) {
            return null;
        }
        if (z) {
            obj = getAvidAdSessionContextDeferred();
        } else {
            obj = getAvidAdSessionContextNonDeferred();
        }
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            Object execute = new Reflection.MethodBuilder((Object) null, "startAvidDisplayAdSession").setStatic(AVID_AD_SESSION_MANAGER_PATH).addParam(Context.class, context).addParam(EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH, obj).execute();
            this.mAvidDisplayAdSession = execute;
            new Reflection.MethodBuilder(execute, "registerAdView").addParam(View.class, webView).addParam(Activity.class, activity).execute();
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to execute Avid start display session: " + e.getMessage());
            return false;
        }
    }

    public Boolean startDeferredDisplaySession(Activity activity) {
        if (!isEnabled()) {
            return null;
        }
        if (this.mAvidDisplayAdSession == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Avid DisplayAdSession unexpectedly null.");
            return false;
        }
        try {
            new Reflection.MethodBuilder(new Reflection.MethodBuilder((Object) null, "getInstance").setStatic(AVID_MANAGER_PATH).execute(), "registerActivity").addParam(Activity.class, activity).execute();
            Object execute = new Reflection.MethodBuilder(this.mAvidDisplayAdSession, "getAvidDeferredAdSessionListener").execute();
            if (execute == null) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Avid AdSessionListener unexpectedly null.");
                return false;
            }
            new Reflection.MethodBuilder(execute, "recordReadyEvent").execute();
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to execute Avid record deferred session: " + e.getMessage());
            return false;
        }
    }

    public Boolean endDisplaySession() {
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mAvidDisplayAdSession;
        if (obj == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Avid DisplayAdSession unexpectedly null.");
            return false;
        }
        try {
            new Reflection.MethodBuilder(obj, "endSession").execute();
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to execute Avid end session: " + e.getMessage());
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
        try {
            Object execute = new Reflection.MethodBuilder((Object) null, "startAvidManagedVideoAdSession").setStatic(AVID_AD_SESSION_MANAGER_PATH).addParam(Context.class, activity).addParam(EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH, getAvidAdSessionContextNonDeferred()).execute();
            this.mAvidVideoAdSession = execute;
            new Reflection.MethodBuilder(execute, "registerAdView").addParam(View.class, view).addParam(Activity.class, activity).execute();
            if (!TextUtils.isEmpty(map.get("avid"))) {
                new Reflection.MethodBuilder(this.mAvidVideoAdSession, "injectJavaScriptResource").addParam(String.class, map.get("avid")).execute();
            }
            for (String next : set) {
                if (!TextUtils.isEmpty(next)) {
                    new Reflection.MethodBuilder(this.mAvidVideoAdSession, "injectJavaScriptResource").addParam(String.class, next).execute();
                }
            }
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to execute Avid start video session: " + e.getMessage());
            return false;
        }
    }

    public Boolean registerVideoObstruction(View view) {
        Preconditions.checkNotNull(view);
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mAvidVideoAdSession;
        if (obj == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Avid VideoAdSession unexpectedly null.");
            return false;
        }
        try {
            new Reflection.MethodBuilder(obj, "registerFriendlyObstruction").addParam(View.class, view).execute();
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to register Avid video obstructions: " + e.getMessage());
            return false;
        }
    }

    public Boolean onVideoPrepared(View view, int i) {
        Preconditions.checkNotNull(view);
        if (!isEnabled()) {
            return null;
        }
        return true;
    }

    public Boolean recordVideoEvent(ExternalViewabilitySession.VideoEvent videoEvent, int i) {
        Preconditions.checkNotNull(videoEvent);
        if (!isEnabled()) {
            return null;
        }
        if (this.mAvidVideoAdSession == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Avid VideoAdSession unexpectedly null.");
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
                case 10:
                case 11:
                case 12:
                    handleVideoEventReflection(videoEvent);
                    return true;
                case 13:
                    handleVideoEventReflection(videoEvent, "error");
                    return true;
                default:
                    MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                    MoPubLog.log(sdkLogEvent, "Unexpected video event type: " + videoEvent);
                    return false;
            }
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent2, "Unable to execute Avid video event for " + videoEvent.getAvidMethodName() + ": " + e.getMessage());
            return false;
        }
    }

    /* renamed from: com.mopub.common.AvidViewabilitySession$1  reason: invalid class name */
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
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_LOADED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_STARTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_STOPPED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_PAUSED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x003e }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_PLAYING     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_SKIPPED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_IMPRESSED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_CLICK_THRU     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x006c }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_VIDEO_FIRST_QUARTILE     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_VIDEO_MIDPOINT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_VIDEO_THIRD_QUARTILE     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySession$VideoEvent     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.mopub.common.ExternalViewabilitySession$VideoEvent r1 = com.mopub.common.ExternalViewabilitySession.VideoEvent.AD_COMPLETE     // Catch:{ NoSuchFieldError -> 0x0090 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.AvidViewabilitySession.AnonymousClass1.<clinit>():void");
        }
    }

    public Boolean endVideoSession() {
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mAvidVideoAdSession;
        if (obj == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Avid VideoAdSession unexpectedly null.");
            return false;
        }
        try {
            new Reflection.MethodBuilder(obj, "endSession").execute();
            return true;
        } catch (Exception e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Unable to execute Avid end video session: " + e.getMessage());
            return false;
        }
    }

    private void handleVideoEventReflection(ExternalViewabilitySession.VideoEvent videoEvent) throws Exception {
        handleVideoEventReflection(videoEvent, (String) null);
    }

    private void handleVideoEventReflection(ExternalViewabilitySession.VideoEvent videoEvent, String str) throws Exception {
        Reflection.MethodBuilder methodBuilder = new Reflection.MethodBuilder(new Reflection.MethodBuilder(this.mAvidVideoAdSession, "getAvidVideoPlaybackListener").execute(), videoEvent.getAvidMethodName());
        if (!TextUtils.isEmpty(str)) {
            methodBuilder.addParam(String.class, str);
        }
        methodBuilder.execute();
    }

    static void resetStaticState() {
        sIsVendorDisabled = false;
        sIsViewabilityEnabledViaReflection = null;
        sAvidAdSessionContextDeferred = null;
        sAvidAdSessionContextNonDeferred = null;
    }
}
