package com.google.gson.stream;

import java.io.IOException;

public final class MalformedJsonException extends IOException {
    public MalformedJsonException(String str) {
        super(str);
    }
}
