package rx.subjects;

import java.util.ArrayList;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;
import rx.subjects.SubjectSubscriptionManager;

public final class BehaviorSubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private final SubjectSubscriptionManager<T> state;

    public static <T> BehaviorSubject<T> create() {
        return create((Object) null, false);
    }

    private static <T> BehaviorSubject<T> create(T t, boolean z) {
        final SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        if (z) {
            subjectSubscriptionManager.setLatest(NotificationLite.next(t));
        }
        subjectSubscriptionManager.onAdded = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() {
            public void call(SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
                subjectObserver.emitFirst(subjectSubscriptionManager.getLatest());
            }
        };
        subjectSubscriptionManager.onTerminated = subjectSubscriptionManager.onAdded;
        return new BehaviorSubject<>(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    protected BehaviorSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(onSubscribe);
        this.state = subjectSubscriptionManager;
    }

    public void onCompleted() {
        if (this.state.getLatest() == null || this.state.active) {
            Object completed = NotificationLite.completed();
            for (SubjectSubscriptionManager.SubjectObserver emitNext : this.state.terminate(completed)) {
                emitNext.emitNext(completed);
            }
        }
    }

    public void onError(Throwable th) {
        if (this.state.getLatest() == null || this.state.active) {
            Object error = NotificationLite.error(th);
            ArrayList arrayList = null;
            for (SubjectSubscriptionManager.SubjectObserver emitNext : this.state.terminate(error)) {
                try {
                    emitNext.emitNext(error);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }
    }

    public void onNext(T t) {
        if (this.state.getLatest() == null || this.state.active) {
            Object next = NotificationLite.next(t);
            for (SubjectSubscriptionManager.SubjectObserver emitNext : this.state.next(next)) {
                emitNext.emitNext(next);
            }
        }
    }
}
