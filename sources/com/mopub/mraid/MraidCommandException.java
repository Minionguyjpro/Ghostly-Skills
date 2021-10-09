package com.mopub.mraid;

class MraidCommandException extends Exception {
    MraidCommandException() {
    }

    MraidCommandException(String str) {
        super(str);
    }

    MraidCommandException(String str, Throwable th) {
        super(str, th);
    }

    MraidCommandException(Throwable th) {
        super(th);
    }
}
