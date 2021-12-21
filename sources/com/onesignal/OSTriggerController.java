package com.onesignal;

import com.onesignal.OSDynamicTriggerController;
import com.onesignal.OSTrigger;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

class OSTriggerController {
    OSDynamicTriggerController dynamicTriggerController;
    private final ConcurrentHashMap<String, Object> triggers = new ConcurrentHashMap<>();

    OSTriggerController(OSDynamicTriggerController.OSDynamicTriggerControllerObserver oSDynamicTriggerControllerObserver) {
        this.dynamicTriggerController = new OSDynamicTriggerController(oSDynamicTriggerControllerObserver);
    }

    /* access modifiers changed from: package-private */
    public boolean evaluateMessageTriggers(OSInAppMessage oSInAppMessage) {
        if (oSInAppMessage.triggers.size() == 0) {
            return true;
        }
        Iterator<ArrayList<OSTrigger>> it = oSInAppMessage.triggers.iterator();
        while (it.hasNext()) {
            if (evaluateAndTriggers(it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean evaluateAndTriggers(ArrayList<OSTrigger> arrayList) {
        Iterator<OSTrigger> it = arrayList.iterator();
        while (it.hasNext()) {
            if (!evaluateTrigger(it.next())) {
                return false;
            }
        }
        return true;
    }

    private boolean evaluateTrigger(OSTrigger oSTrigger) {
        if (oSTrigger.kind == OSTrigger.OSTriggerKind.UNKNOWN) {
            return false;
        }
        if (oSTrigger.kind != OSTrigger.OSTriggerKind.CUSTOM) {
            return this.dynamicTriggerController.dynamicTriggerShouldFire(oSTrigger);
        }
        OSTrigger.OSTriggerOperator oSTriggerOperator = oSTrigger.operatorType;
        Object obj = this.triggers.get(oSTrigger.property);
        if (obj == null) {
            if (oSTriggerOperator == OSTrigger.OSTriggerOperator.NOT_EXISTS) {
                return true;
            }
            if (oSTriggerOperator != OSTrigger.OSTriggerOperator.NOT_EQUAL_TO || oSTrigger.value == null) {
                return false;
            }
            return true;
        } else if (oSTriggerOperator == OSTrigger.OSTriggerOperator.EXISTS) {
            return true;
        } else {
            if (oSTriggerOperator == OSTrigger.OSTriggerOperator.NOT_EXISTS) {
                return false;
            }
            if (oSTriggerOperator == OSTrigger.OSTriggerOperator.CONTAINS) {
                if (!(obj instanceof Collection) || !((Collection) obj).contains(oSTrigger.value)) {
                    return false;
                }
                return true;
            } else if ((obj instanceof String) && (oSTrigger.value instanceof String) && triggerMatchesStringValue((String) oSTrigger.value, (String) obj, oSTriggerOperator)) {
                return true;
            } else {
                if ((!(oSTrigger.value instanceof Number) || !(obj instanceof Number) || !triggerMatchesNumericValue((Number) oSTrigger.value, (Number) obj, oSTriggerOperator)) && !triggerMatchesFlex(oSTrigger.value, obj, oSTriggerOperator)) {
                    return false;
                }
                return true;
            }
        }
    }

    /* renamed from: com.onesignal.OSTriggerController$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.onesignal.OSTrigger$OSTriggerOperator[] r0 = com.onesignal.OSTrigger.OSTriggerOperator.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator = r0
                com.onesignal.OSTrigger$OSTriggerOperator r1 = com.onesignal.OSTrigger.OSTriggerOperator.EQUAL_TO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x001d }
                com.onesignal.OSTrigger$OSTriggerOperator r1 = com.onesignal.OSTrigger.OSTriggerOperator.NOT_EQUAL_TO     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.onesignal.OSTrigger$OSTriggerOperator r1 = com.onesignal.OSTrigger.OSTriggerOperator.EXISTS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.onesignal.OSTrigger$OSTriggerOperator r1 = com.onesignal.OSTrigger.OSTriggerOperator.CONTAINS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x003e }
                com.onesignal.OSTrigger$OSTriggerOperator r1 = com.onesignal.OSTrigger.OSTriggerOperator.NOT_EXISTS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.onesignal.OSTrigger$OSTriggerOperator r1 = com.onesignal.OSTrigger.OSTriggerOperator.LESS_THAN     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.onesignal.OSTrigger$OSTriggerOperator r1 = com.onesignal.OSTrigger.OSTriggerOperator.GREATER_THAN     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.onesignal.OSTrigger$OSTriggerOperator r1 = com.onesignal.OSTrigger.OSTriggerOperator.LESS_THAN_OR_EQUAL_TO     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator     // Catch:{ NoSuchFieldError -> 0x006c }
                com.onesignal.OSTrigger$OSTriggerOperator r1 = com.onesignal.OSTrigger.OSTriggerOperator.GREATER_THAN_OR_EQUAL_TO     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSTriggerController.AnonymousClass1.<clinit>():void");
        }
    }

    private boolean triggerMatchesStringValue(String str, String str2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        int i = AnonymousClass1.$SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[oSTriggerOperator.ordinal()];
        if (i == 1) {
            return str.equals(str2);
        }
        if (i == 2) {
            return !str.equals(str2);
        }
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
        OneSignal.onesignalLog(log_level, "Attempted to use an invalid operator for a string trigger comparison: " + oSTriggerOperator.toString());
        return false;
    }

    private boolean triggerMatchesFlex(Object obj, Object obj2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        if (obj == null) {
            return false;
        }
        if (oSTriggerOperator.checksEquality()) {
            return triggerMatchesStringValue(obj.toString(), obj2.toString(), oSTriggerOperator);
        }
        if (!(obj2 instanceof String) || !(obj instanceof Number)) {
            return false;
        }
        return triggerMatchesNumericValueFlex((Number) obj, (String) obj2, oSTriggerOperator);
    }

    private boolean triggerMatchesNumericValueFlex(Number number, String str, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        try {
            return triggerMatchesNumericValue(Double.valueOf(number.doubleValue()), Double.valueOf(Double.parseDouble(str)), oSTriggerOperator);
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private boolean triggerMatchesNumericValue(Number number, Number number2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        double doubleValue = number.doubleValue();
        double doubleValue2 = number2.doubleValue();
        switch (AnonymousClass1.$SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[oSTriggerOperator.ordinal()]) {
            case 1:
                if (doubleValue2 == doubleValue) {
                    return true;
                }
                return false;
            case 2:
                if (doubleValue2 != doubleValue) {
                    return true;
                }
                return false;
            case 3:
            case 4:
            case 5:
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.onesignalLog(log_level, "Attempted to use an invalid operator with a numeric value: " + oSTriggerOperator.toString());
                return false;
            case 6:
                return doubleValue2 < doubleValue;
            case 7:
                return doubleValue2 > doubleValue;
            case 8:
                return doubleValue2 < doubleValue || doubleValue2 == doubleValue;
            case 9:
                return doubleValue2 > doubleValue || doubleValue2 == doubleValue;
            default:
                return false;
        }
    }
}
