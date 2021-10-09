package com.onesignal;

import com.onesignal.OSTrigger;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.Date;

class OSDynamicTriggerController {
    static Date sessionLaunchTime = new Date();
    /* access modifiers changed from: private */
    public final OSDynamicTriggerControllerObserver observer;
    /* access modifiers changed from: private */
    public final ArrayList<String> scheduledMessages = new ArrayList<>();

    interface OSDynamicTriggerControllerObserver {
        void messageTriggerConditionChanged();
    }

    OSDynamicTriggerController(OSDynamicTriggerControllerObserver oSDynamicTriggerControllerObserver) {
        this.observer = oSDynamicTriggerControllerObserver;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0075 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dynamicTriggerShouldFire(final com.onesignal.OSTrigger r15) {
        /*
            r14 = this;
            java.lang.Object r0 = r15.value
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.util.ArrayList<java.lang.String> r0 = r14.scheduledMessages
            monitor-enter(r0)
            java.lang.Object r2 = r15.value     // Catch:{ all -> 0x009d }
            boolean r2 = r2 instanceof java.lang.Number     // Catch:{ all -> 0x009d }
            if (r2 != 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            return r1
        L_0x0011:
            int[] r2 = com.onesignal.OSDynamicTriggerController.AnonymousClass2.$SwitchMap$com$onesignal$OSTrigger$OSTriggerKind     // Catch:{ all -> 0x009d }
            com.onesignal.OSTrigger$OSTriggerKind r3 = r15.kind     // Catch:{ all -> 0x009d }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x009d }
            r2 = r2[r3]     // Catch:{ all -> 0x009d }
            r3 = 1
            r4 = 0
            if (r2 == r3) goto L_0x004b
            r6 = 2
            if (r2 == r6) goto L_0x0025
            r6 = r4
            goto L_0x005b
        L_0x0025:
            com.onesignal.OSInAppMessageController r2 = com.onesignal.OSInAppMessageController.getController()     // Catch:{ all -> 0x009d }
            boolean r2 = r2.isInAppMessageShowing()     // Catch:{ all -> 0x009d }
            if (r2 == 0) goto L_0x0031
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            return r1
        L_0x0031:
            com.onesignal.OSInAppMessageController r2 = com.onesignal.OSInAppMessageController.getController()     // Catch:{ all -> 0x009d }
            java.util.Date r2 = r2.lastTimeInAppDismissed     // Catch:{ all -> 0x009d }
            if (r2 != 0) goto L_0x003d
            r6 = 999999(0xf423f, double:4.94065E-318)
            goto L_0x005b
        L_0x003d:
            java.util.Date r6 = new java.util.Date     // Catch:{ all -> 0x009d }
            r6.<init>()     // Catch:{ all -> 0x009d }
            long r6 = r6.getTime()     // Catch:{ all -> 0x009d }
            long r8 = r2.getTime()     // Catch:{ all -> 0x009d }
            goto L_0x005a
        L_0x004b:
            java.util.Date r2 = new java.util.Date     // Catch:{ all -> 0x009d }
            r2.<init>()     // Catch:{ all -> 0x009d }
            long r6 = r2.getTime()     // Catch:{ all -> 0x009d }
            java.util.Date r2 = sessionLaunchTime     // Catch:{ all -> 0x009d }
            long r8 = r2.getTime()     // Catch:{ all -> 0x009d }
        L_0x005a:
            long r6 = r6 - r8
        L_0x005b:
            java.lang.Object r2 = r15.value     // Catch:{ all -> 0x009d }
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ all -> 0x009d }
            double r8 = r2.doubleValue()     // Catch:{ all -> 0x009d }
            r10 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r8 = r8 * r10
            long r8 = (long) r8     // Catch:{ all -> 0x009d }
            double r10 = (double) r8     // Catch:{ all -> 0x009d }
            double r12 = (double) r6     // Catch:{ all -> 0x009d }
            com.onesignal.OSTrigger$OSTriggerOperator r2 = r15.operatorType     // Catch:{ all -> 0x009d }
            boolean r2 = evaluateTimeIntervalWithOperator(r10, r12, r2)     // Catch:{ all -> 0x009d }
            if (r2 == 0) goto L_0x0077
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            return r3
        L_0x0077:
            long r8 = r8 - r6
            int r2 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r2 > 0) goto L_0x007e
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            return r1
        L_0x007e:
            java.util.ArrayList<java.lang.String> r2 = r14.scheduledMessages     // Catch:{ all -> 0x009d }
            java.lang.String r3 = r15.triggerId     // Catch:{ all -> 0x009d }
            boolean r2 = r2.contains(r3)     // Catch:{ all -> 0x009d }
            if (r2 == 0) goto L_0x008a
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            return r1
        L_0x008a:
            com.onesignal.OSDynamicTriggerController$1 r2 = new com.onesignal.OSDynamicTriggerController$1     // Catch:{ all -> 0x009d }
            r2.<init>(r15)     // Catch:{ all -> 0x009d }
            java.lang.String r3 = r15.triggerId     // Catch:{ all -> 0x009d }
            com.onesignal.OSDynamicTriggerTimer.scheduleTrigger(r2, r3, r8)     // Catch:{ all -> 0x009d }
            java.util.ArrayList<java.lang.String> r2 = r14.scheduledMessages     // Catch:{ all -> 0x009d }
            java.lang.String r15 = r15.triggerId     // Catch:{ all -> 0x009d }
            r2.add(r15)     // Catch:{ all -> 0x009d }
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            return r1
        L_0x009d:
            r15 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSDynamicTriggerController.dynamicTriggerShouldFire(com.onesignal.OSTrigger):boolean");
    }

    /* renamed from: com.onesignal.OSDynamicTriggerController$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$OSTrigger$OSTriggerKind;
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|14|15|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0028 */
        static {
            /*
                com.onesignal.OSTrigger$OSTriggerOperator[] r0 = com.onesignal.OSTrigger.OSTriggerOperator.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator = r0
                r1 = 1
                com.onesignal.OSTrigger$OSTriggerOperator r2 = com.onesignal.OSTrigger.OSTriggerOperator.LESS_THAN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x001d }
                com.onesignal.OSTrigger$OSTriggerOperator r3 = com.onesignal.OSTrigger.OSTriggerOperator.LESS_THAN_OR_EQUAL_TO     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r2 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.onesignal.OSTrigger$OSTriggerOperator r3 = com.onesignal.OSTrigger.OSTriggerOperator.GREATER_THAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r2 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.onesignal.OSTrigger$OSTriggerOperator r3 = com.onesignal.OSTrigger.OSTriggerOperator.GREATER_THAN_OR_EQUAL_TO     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4 = 4
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r2 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x003e }
                com.onesignal.OSTrigger$OSTriggerOperator r3 = com.onesignal.OSTrigger.OSTriggerOperator.EQUAL_TO     // Catch:{ NoSuchFieldError -> 0x003e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r4 = 5
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r2 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.onesignal.OSTrigger$OSTriggerOperator r3 = com.onesignal.OSTrigger.OSTriggerOperator.NOT_EQUAL_TO     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r4 = 6
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.onesignal.OSTrigger$OSTriggerKind[] r2 = com.onesignal.OSTrigger.OSTriggerKind.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerKind = r2
                com.onesignal.OSTrigger$OSTriggerKind r3 = com.onesignal.OSTrigger.OSTriggerKind.SESSION_TIME     // Catch:{ NoSuchFieldError -> 0x005a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerKind     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.onesignal.OSTrigger$OSTriggerKind r2 = com.onesignal.OSTrigger.OSTriggerKind.TIME_SINCE_LAST_IN_APP     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSDynamicTriggerController.AnonymousClass2.<clinit>():void");
        }
    }

    private static boolean evaluateTimeIntervalWithOperator(double d, double d2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        switch (AnonymousClass2.$SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[oSTriggerOperator.ordinal()]) {
            case 1:
                if (d2 < d) {
                    return true;
                }
                return false;
            case 2:
                if (d2 <= d || roughlyEqual(d, d2)) {
                    return true;
                }
                return false;
            case 3:
                if (d2 > d) {
                    return true;
                }
                return false;
            case 4:
                if (d2 >= d || roughlyEqual(d, d2)) {
                    return true;
                }
                return false;
            case 5:
                return roughlyEqual(d, d2);
            case 6:
                return !roughlyEqual(d, d2);
            default:
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.onesignalLog(log_level, "Attempted to apply an invalid operator on a time-based in-app-message trigger: " + oSTriggerOperator.toString());
                return false;
        }
    }

    private static boolean roughlyEqual(double d, double d2) {
        return Math.abs(d - d2) < 0.3d;
    }
}
