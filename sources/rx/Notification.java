package rx;

public final class Notification<T> {
    private static final Notification<Void> ON_COMPLETED = new Notification<>(Kind.OnCompleted, (Object) null, (Throwable) null);
    private final Kind kind;
    private final Throwable throwable;
    private final T value;

    public enum Kind {
        OnNext,
        OnError,
        OnCompleted
    }

    private Notification(Kind kind2, T t, Throwable th) {
        this.value = t;
        this.throwable = th;
        this.kind = kind2;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public T getValue() {
        return this.value;
    }

    public boolean hasValue() {
        return isOnNext() && this.value != null;
    }

    public boolean hasThrowable() {
        return isOnError() && this.throwable != null;
    }

    public Kind getKind() {
        return this.kind;
    }

    public boolean isOnError() {
        return getKind() == Kind.OnError;
    }

    public boolean isOnNext() {
        return getKind() == Kind.OnNext;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append('[');
        sb.append(super.toString());
        sb.append(' ');
        sb.append(getKind());
        if (hasValue()) {
            sb.append(' ');
            sb.append(getValue());
        }
        if (hasThrowable()) {
            sb.append(' ');
            sb.append(getThrowable().getMessage());
        }
        sb.append(']');
        return sb.toString();
    }

    public int hashCode() {
        int hashCode = getKind().hashCode();
        if (hasValue()) {
            hashCode = (hashCode * 31) + getValue().hashCode();
        }
        return hasThrowable() ? (hashCode * 31) + getThrowable().hashCode() : hashCode;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Notification notification = (Notification) obj;
        if (notification.getKind() != getKind()) {
            return false;
        }
        T t = this.value;
        T t2 = notification.value;
        if (t != t2 && (t == null || !t.equals(t2))) {
            return false;
        }
        Throwable th = this.throwable;
        Throwable th2 = notification.throwable;
        if (th == th2 || (th != null && th.equals(th2))) {
            return true;
        }
        return false;
    }
}
