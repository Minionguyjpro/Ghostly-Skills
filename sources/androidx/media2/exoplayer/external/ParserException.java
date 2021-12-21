package androidx.media2.exoplayer.external;

import java.io.IOException;

public class ParserException extends IOException {
    public ParserException() {
    }

    public ParserException(String str) {
        super(str);
    }

    public ParserException(String str, Throwable th) {
        super(str, th);
    }
}
