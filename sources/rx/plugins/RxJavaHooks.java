package rx.plugins;

import java.io.PrintStream;
import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.SingleFromObservable;
import rx.internal.operators.SingleToObservable;

public final class RxJavaHooks {
    static volatile Func1<Completable.OnSubscribe, Completable.OnSubscribe> onCompletableCreate;
    static volatile Func1<Completable.Operator, Completable.Operator> onCompletableLift;
    static volatile Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> onCompletableStart;
    static volatile Func1<Throwable, Throwable> onCompletableSubscribeError;
    static volatile Action1<Throwable> onError;
    static volatile Func1<Observable.OnSubscribe, Observable.OnSubscribe> onObservableCreate;
    static volatile Func1<Observable.Operator, Observable.Operator> onObservableLift;
    static volatile Func1<Subscription, Subscription> onObservableReturn;
    static volatile Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> onObservableStart;
    static volatile Func1<Throwable, Throwable> onObservableSubscribeError;
    static volatile Func1<Action0, Action0> onScheduleAction;
    static volatile Func1<Single.OnSubscribe, Single.OnSubscribe> onSingleCreate;
    static volatile Func1<Observable.Operator, Observable.Operator> onSingleLift;
    static volatile Func1<Subscription, Subscription> onSingleReturn;
    static volatile Func2<Single, Single.OnSubscribe, Single.OnSubscribe> onSingleStart;
    static volatile Func1<Throwable, Throwable> onSingleSubscribeError;

    static {
        init();
    }

    static void init() {
        onError = new Action1<Throwable>() {
            public void call(Throwable th) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        };
        onObservableStart = new Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe>() {
            public Observable.OnSubscribe call(Observable observable, Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeStart(observable, onSubscribe);
            }
        };
        onObservableReturn = new Func1<Subscription, Subscription>() {
            public Subscription call(Subscription subscription) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeReturn(subscription);
            }
        };
        onSingleStart = new Func2<Single, Single.OnSubscribe, Single.OnSubscribe>() {
            public Single.OnSubscribe call(Single single, Single.OnSubscribe onSubscribe) {
                RxJavaSingleExecutionHook singleExecutionHook = RxJavaPlugins.getInstance().getSingleExecutionHook();
                if (singleExecutionHook == RxJavaSingleExecutionHookDefault.getInstance()) {
                    return onSubscribe;
                }
                return new SingleFromObservable(singleExecutionHook.onSubscribeStart(single, new SingleToObservable(onSubscribe)));
            }
        };
        onSingleReturn = new Func1<Subscription, Subscription>() {
            public Subscription call(Subscription subscription) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeReturn(subscription);
            }
        };
        onCompletableStart = new Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe>() {
            public Completable.OnSubscribe call(Completable completable, Completable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeStart(completable, onSubscribe);
            }
        };
        onScheduleAction = new Func1<Action0, Action0>() {
            public Action0 call(Action0 action0) {
                return RxJavaPlugins.getInstance().getSchedulersHook().onSchedule(action0);
            }
        };
        onObservableSubscribeError = new Func1<Throwable, Throwable>() {
            public Throwable call(Throwable th) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeError(th);
            }
        };
        onObservableLift = new Func1<Observable.Operator, Observable.Operator>() {
            public Observable.Operator call(Observable.Operator operator) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onLift(operator);
            }
        };
        onSingleSubscribeError = new Func1<Throwable, Throwable>() {
            public Throwable call(Throwable th) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeError(th);
            }
        };
        onSingleLift = new Func1<Observable.Operator, Observable.Operator>() {
            public Observable.Operator call(Observable.Operator operator) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onLift(operator);
            }
        };
        onCompletableSubscribeError = new Func1<Throwable, Throwable>() {
            public Throwable call(Throwable th) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeError(th);
            }
        };
        onCompletableLift = new Func1<Completable.Operator, Completable.Operator>() {
            public Completable.Operator call(Completable.Operator operator) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onLift(operator);
            }
        };
        initCreate();
    }

    static void initCreate() {
        onObservableCreate = new Func1<Observable.OnSubscribe, Observable.OnSubscribe>() {
            public Observable.OnSubscribe call(Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onCreate(onSubscribe);
            }
        };
        onSingleCreate = new Func1<Single.OnSubscribe, Single.OnSubscribe>() {
            public Single.OnSubscribe call(Single.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onCreate(onSubscribe);
            }
        };
        onCompletableCreate = new Func1<Completable.OnSubscribe, Completable.OnSubscribe>() {
            public Completable.OnSubscribe call(Completable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onCreate(onSubscribe);
            }
        };
    }

    public static void onError(Throwable th) {
        Action1<Throwable> action1 = onError;
        if (action1 != null) {
            try {
                action1.call(th);
                return;
            } catch (Throwable th2) {
                PrintStream printStream = System.err;
                printStream.println("The onError handler threw an Exception. It shouldn't. => " + th2.getMessage());
                th2.printStackTrace();
                signalUncaught(th2);
            }
        }
        signalUncaught(th);
    }

    static void signalUncaught(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static Completable.OnSubscribe onCreate(Completable.OnSubscribe onSubscribe) {
        Func1<Completable.OnSubscribe, Completable.OnSubscribe> func1 = onCompletableCreate;
        return func1 != null ? func1.call(onSubscribe) : onSubscribe;
    }

    public static <T> Observable.OnSubscribe<T> onObservableStart(Observable<T> observable, Observable.OnSubscribe<T> onSubscribe) {
        Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> func2 = onObservableStart;
        return func2 != null ? func2.call(observable, onSubscribe) : onSubscribe;
    }

    public static Subscription onObservableReturn(Subscription subscription) {
        Func1<Subscription, Subscription> func1 = onObservableReturn;
        return func1 != null ? func1.call(subscription) : subscription;
    }

    public static Throwable onObservableError(Throwable th) {
        Func1<Throwable, Throwable> func1 = onObservableSubscribeError;
        return func1 != null ? func1.call(th) : th;
    }

    public static <T, R> Observable.Operator<R, T> onSingleLift(Observable.Operator<R, T> operator) {
        Func1 func1 = onSingleLift;
        return func1 != null ? (Observable.Operator) func1.call(operator) : operator;
    }
}
