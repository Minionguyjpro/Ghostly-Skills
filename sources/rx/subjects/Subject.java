package rx.subjects;

import rx.Observable;
import rx.Observer;

public abstract class Subject<T, R> extends Observable<R> implements Observer<T> {
    protected Subject(Observable.OnSubscribe<R> onSubscribe) {
        super(onSubscribe);
    }
}
