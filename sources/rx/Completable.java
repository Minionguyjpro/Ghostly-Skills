package rx;

import rx.functions.Action1;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;

public class Completable {
    static final Completable COMPLETE = new Completable(new OnSubscribe() {
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            completableSubscriber.onCompleted();
        }
    }, false);
    static final Completable NEVER = new Completable(new OnSubscribe() {
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
        }
    }, false);
    private final OnSubscribe onSubscribe;

    public interface OnSubscribe extends Action1<CompletableSubscriber> {
    }

    public interface Operator extends Func1<CompletableSubscriber, CompletableSubscriber> {
    }

    protected Completable(OnSubscribe onSubscribe2, boolean z) {
        this.onSubscribe = z ? RxJavaHooks.onCreate(onSubscribe2) : onSubscribe2;
    }
}
