package com.yandex.metrica;

import java.util.Map;

public interface IIdentifierCallback {

    public enum Reason {
        UNKNOWN,
        NETWORK,
        INVALID_RESPONSE
    }

    void onReceive(Map<String, String> map);

    void onRequestError(Reason reason);
}
