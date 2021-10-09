package kotlin.collections;

import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MutableCollectionsJVM.kt */
class CollectionsKt__MutableCollectionsJVMKt extends CollectionsKt__IteratorsKt {
    public static final <T extends Comparable<? super T>> void sort(List<T> list) {
        Intrinsics.checkParameterIsNotNull(list, "$this$sort");
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }
}
