package com.yandex.metrica.impl.ob;

public class fr extends Exception {

    public enum a {
        DEFAULT,
        AUTH,
        NETWORK,
        NO_CONNECTION,
        PARSE,
        SERVER,
        TIMEOUT
    }

    public fr() {
    }

    public fr(byte b) {
    }

    public fr(Throwable th) {
        super(th);
    }
}
