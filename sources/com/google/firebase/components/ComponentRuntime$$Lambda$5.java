package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collections;

/* compiled from: com.google.firebase:firebase-components@@16.0.0 */
final /* synthetic */ class ComponentRuntime$$Lambda$5 implements Provider {
    private static final ComponentRuntime$$Lambda$5 instance = new ComponentRuntime$$Lambda$5();

    private ComponentRuntime$$Lambda$5() {
    }

    public static Provider lambdaFactory$() {
        return instance;
    }

    public Object get() {
        return Collections.emptySet();
    }
}
