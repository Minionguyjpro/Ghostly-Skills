package com.mopub.common;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.ExternalViewabilitySession;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.mobileads.VastVideoConfigTwo;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ExternalViewabilitySessionManager {
    private final Set<ExternalViewabilitySession> mViewabilitySessions;

    /* renamed from: com.mopub.common.ExternalViewabilitySessionManager$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$common$ExternalViewabilitySessionManager$ViewabilityVendor;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor[] r0 = com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$common$ExternalViewabilitySessionManager$ViewabilityVendor = r0
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor r1 = com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor.AVID     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySessionManager$ViewabilityVendor     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor r1 = com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor.MOAT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$common$ExternalViewabilitySessionManager$ViewabilityVendor     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor r1 = com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor.ALL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.ExternalViewabilitySessionManager.AnonymousClass1.<clinit>():void");
        }
    }

    public enum ViewabilityVendor {
        AVID,
        MOAT,
        ALL;

        public void disable() {
            int i = AnonymousClass1.$SwitchMap$com$mopub$common$ExternalViewabilitySessionManager$ViewabilityVendor[ordinal()];
            if (i == 1) {
                AvidViewabilitySession.disable();
            } else if (i == 2) {
                MoatViewabilitySession.disable();
            } else if (i != 3) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "Attempted to disable an invalid viewability vendor: " + this);
                return;
            } else {
                AvidViewabilitySession.disable();
                MoatViewabilitySession.disable();
            }
            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent2, "Disabled viewability for " + this);
        }

        public static String getEnabledVendorKey() {
            boolean isEnabled = AvidViewabilitySession.isEnabled();
            boolean isEnabled2 = MoatViewabilitySession.isEnabled();
            if (isEnabled && isEnabled2) {
                return "3";
            }
            if (isEnabled) {
                return "1";
            }
            return isEnabled2 ? InternalAvidAdSessionContext.AVID_API_LEVEL : "0";
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor fromKey(java.lang.String r3) {
            /*
                com.mopub.common.Preconditions.checkNotNull(r3)
                int r0 = r3.hashCode()
                r1 = 2
                r2 = 1
                switch(r0) {
                    case 49: goto L_0x0021;
                    case 50: goto L_0x0017;
                    case 51: goto L_0x000d;
                    default: goto L_0x000c;
                }
            L_0x000c:
                goto L_0x002b
            L_0x000d:
                java.lang.String r0 = "3"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x002b
                r3 = 2
                goto L_0x002c
            L_0x0017:
                java.lang.String r0 = "2"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x002b
                r3 = 1
                goto L_0x002c
            L_0x0021:
                java.lang.String r0 = "1"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x002b
                r3 = 0
                goto L_0x002c
            L_0x002b:
                r3 = -1
            L_0x002c:
                if (r3 == 0) goto L_0x003a
                if (r3 == r2) goto L_0x0037
                if (r3 == r1) goto L_0x0034
                r3 = 0
                return r3
            L_0x0034:
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor r3 = ALL
                return r3
            L_0x0037:
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor r3 = MOAT
                return r3
            L_0x003a:
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor r3 = AVID
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor.fromKey(java.lang.String):com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor");
        }
    }

    public ExternalViewabilitySessionManager(Context context) {
        Preconditions.checkNotNull(context);
        HashSet hashSet = new HashSet();
        this.mViewabilitySessions = hashSet;
        hashSet.add(new AvidViewabilitySession());
        this.mViewabilitySessions.add(new MoatViewabilitySession());
        initialize(context);
    }

    private void initialize(Context context) {
        Preconditions.checkNotNull(context);
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            logEvent(next, "initialize", next.initialize(context), false);
        }
    }

    public void invalidate() {
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            logEvent(next, "invalidate", next.invalidate(), false);
        }
    }

    public void createDisplaySession(Context context, WebView webView) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(webView);
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            logEvent(next, "start display session", next.createDisplaySession(context, webView, true), true);
        }
    }

    public void startDeferredDisplaySession(Activity activity) {
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            logEvent(next, "record deferred session", next.startDeferredDisplaySession(activity), true);
        }
    }

    public void endDisplaySession() {
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            logEvent(next, "end display session", next.endDisplaySession(), true);
        }
    }

    public void createVideoSession(Activity activity, View view, VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(vastVideoConfig);
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            HashSet hashSet = new HashSet();
            if (next instanceof AvidViewabilitySession) {
                hashSet.addAll(vastVideoConfig.getAvidJavascriptResources());
            } else if (next instanceof MoatViewabilitySession) {
                hashSet.addAll(vastVideoConfig.getMoatImpressionPixels());
            }
            logEvent(next, "start video session", next.createVideoSession(activity, view, hashSet, vastVideoConfig.getExternalViewabilityTrackers()), true);
        }
    }

    public void createVideoSession(Activity activity, View view, VastVideoConfigTwo vastVideoConfigTwo) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(vastVideoConfigTwo);
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            HashSet hashSet = new HashSet();
            if (next instanceof AvidViewabilitySession) {
                hashSet.addAll(vastVideoConfigTwo.getAvidJavascriptResources());
            } else if (next instanceof MoatViewabilitySession) {
                hashSet.addAll(vastVideoConfigTwo.getMoatImpressionPixels());
            }
            logEvent(next, "start video session", next.createVideoSession(activity, view, hashSet, vastVideoConfigTwo.getExternalViewabilityTrackers()), true);
        }
    }

    public void registerVideoObstruction(View view) {
        Preconditions.checkNotNull(view);
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            logEvent(next, "register friendly obstruction", next.registerVideoObstruction(view), true);
        }
    }

    public void onVideoPrepared(View view, int i) {
        Preconditions.checkNotNull(view);
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            logEvent(next, "on video prepared", next.onVideoPrepared(view, i), true);
        }
    }

    public void recordVideoEvent(ExternalViewabilitySession.VideoEvent videoEvent, int i) {
        Preconditions.checkNotNull(videoEvent);
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            Boolean recordVideoEvent = next.recordVideoEvent(videoEvent, i);
            logEvent(next, "record video event (" + videoEvent.name() + ")", recordVideoEvent, true);
        }
    }

    public void endVideoSession() {
        for (ExternalViewabilitySession next : this.mViewabilitySessions) {
            logEvent(next, "end video session", next.endVideoSession(), true);
        }
    }

    private void logEvent(ExternalViewabilitySession externalViewabilitySession, String str, Boolean bool, boolean z) {
        Preconditions.checkNotNull(externalViewabilitySession);
        Preconditions.checkNotNull(str);
        if (bool != null) {
            String format = String.format(Locale.US, "%s viewability event: %s%s.", new Object[]{externalViewabilitySession.getName(), bool.booleanValue() ? "" : "failed to ", str});
            if (z) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, format);
            }
        }
    }
}
