package com.mopub.exceptions;

public class IntentNotResolvableException extends Exception {
    public IntentNotResolvableException(Throwable th) {
        super(th);
    }

    public IntentNotResolvableException(String str) {
        super(str);
    }
}
