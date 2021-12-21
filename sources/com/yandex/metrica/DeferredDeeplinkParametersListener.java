package com.yandex.metrica;

import java.util.Map;

public interface DeferredDeeplinkParametersListener {
    void onError(Error error, String str);

    void onParametersLoaded(Map<String, String> map);

    public enum Error {
        NOT_A_FIRST_LAUNCH("Deferred deeplink parameters can be requested during first launch only."),
        PARSE_ERROR("Google Play referrer did not contain valid deferred deeplink parameters.");
        
        private final String mDescription;

        private Error(String str) {
            this.mDescription = str;
        }
    }
}
