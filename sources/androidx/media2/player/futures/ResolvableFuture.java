package androidx.media2.player.futures;

public final class ResolvableFuture<V> extends AbstractResolvableFuture<V> {
    public static <V> ResolvableFuture<V> create() {
        return new ResolvableFuture<>();
    }

    public boolean set(V v) {
        return super.set(v);
    }

    public boolean setException(Throwable th) {
        return super.setException(th);
    }

    private ResolvableFuture() {
    }
}
