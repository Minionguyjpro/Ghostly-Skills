package rx;

import rx.functions.Action1;

public class Single<T> {

    public interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>> {
    }
}
