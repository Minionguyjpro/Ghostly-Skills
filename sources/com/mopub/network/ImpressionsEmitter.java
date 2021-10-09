package com.mopub.network;

import com.mopub.common.Preconditions;
import java.util.HashSet;
import java.util.Set;

public class ImpressionsEmitter {
    private static final HashSet<ImpressionListener> mListeners = new HashSet<>();

    public static void addListener(ImpressionListener impressionListener) {
        Preconditions.checkNotNull(impressionListener);
        synchronized (ImpressionsEmitter.class) {
            mListeners.add(impressionListener);
        }
    }

    public static void removeListener(ImpressionListener impressionListener) {
        Preconditions.checkNotNull(impressionListener);
        synchronized (ImpressionsEmitter.class) {
            mListeners.remove(impressionListener);
        }
    }

    static void send(String str, ImpressionData impressionData) {
        Preconditions.checkNotNull(str);
        for (ImpressionListener onImpression : cloneListeners()) {
            onImpression.onImpression(str, impressionData);
        }
    }

    private static Set<ImpressionListener> cloneListeners() {
        HashSet hashSet;
        synchronized (ImpressionsEmitter.class) {
            hashSet = new HashSet(mListeners);
        }
        return hashSet;
    }

    static void clear() {
        synchronized (ImpressionsEmitter.class) {
            mListeners.clear();
        }
    }
}
