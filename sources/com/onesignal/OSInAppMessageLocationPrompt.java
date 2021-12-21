package com.onesignal;

import com.onesignal.OneSignal;

class OSInAppMessageLocationPrompt extends OSInAppMessagePrompt {
    /* access modifiers changed from: package-private */
    public String getPromptKey() {
        return "location";
    }

    OSInAppMessageLocationPrompt() {
    }

    /* access modifiers changed from: package-private */
    public void handlePrompt(OneSignal.OSPromptActionCompletionCallback oSPromptActionCompletionCallback) {
        OneSignal.promptLocation(oSPromptActionCompletionCallback, true);
    }
}
