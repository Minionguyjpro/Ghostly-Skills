package rx.exceptions;

public final class UnsubscribeFailedException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnsubscribeFailedException(String str, Throwable th) {
        super(str, th == null ? new NullPointerException() : th);
    }
}
