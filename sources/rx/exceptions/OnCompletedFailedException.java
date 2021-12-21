package rx.exceptions;

public final class OnCompletedFailedException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnCompletedFailedException(String str, Throwable th) {
        super(str, th == null ? new NullPointerException() : th);
    }
}
