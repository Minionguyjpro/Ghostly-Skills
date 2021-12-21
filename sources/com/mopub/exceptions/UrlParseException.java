package com.mopub.exceptions;

public class UrlParseException extends Exception {
    public UrlParseException(String str) {
        super(str);
    }

    public UrlParseException(Throwable th) {
        super(th);
    }
}
