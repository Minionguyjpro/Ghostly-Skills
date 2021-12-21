package rx.internal.util;

import rx.functions.Func1;

public final class UtilityFunctions {
    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        public Boolean call(Object obj) {
            return true;
        }
    }
}
