package rx.plugins;

import rx.functions.Action0;

public class RxJavaSchedulersHook {
    private static final RxJavaSchedulersHook DEFAULT_INSTANCE = new RxJavaSchedulersHook();

    @Deprecated
    public Action0 onSchedule(Action0 action0) {
        return action0;
    }

    public static RxJavaSchedulersHook getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }
}
