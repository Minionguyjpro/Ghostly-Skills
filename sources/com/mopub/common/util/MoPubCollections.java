package com.mopub.common.util;

import com.mopub.common.Preconditions;
import java.util.Collection;
import java.util.Collections;

public class MoPubCollections {
    public static <T> void addAllNonNull(Collection<? super T> collection, T... tArr) {
        Collections.addAll(collection, tArr);
        collection.removeAll(Collections.singleton((Object) null));
    }

    public static <T> void addAllNonNull(Collection<? super T> collection, Collection<T> collection2) {
        Preconditions.checkNotNull(collection);
        Preconditions.checkNotNull(collection2);
        collection.addAll(collection2);
        collection.removeAll(Collections.singleton((Object) null));
    }
}
