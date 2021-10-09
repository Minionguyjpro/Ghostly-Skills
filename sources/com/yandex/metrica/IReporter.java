package com.yandex.metrica;

import java.util.Map;

public interface IReporter {
    void reportError(String str, Throwable th);

    void reportEvent(String str);

    void reportEvent(String str, Map<String, Object> map);

    void reportUnhandledException(Throwable th);
}
