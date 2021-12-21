package com.mopub.common.factories;

import com.mopub.common.util.Reflection;

public class MethodBuilderFactory {
    protected static MethodBuilderFactory instance = new MethodBuilderFactory();

    @Deprecated
    public static void setInstance(MethodBuilderFactory methodBuilderFactory) {
        instance = methodBuilderFactory;
    }

    public static Reflection.MethodBuilder create(Object obj, String str) {
        return instance.internalCreate(obj, str);
    }

    /* access modifiers changed from: protected */
    public Reflection.MethodBuilder internalCreate(Object obj, String str) {
        return new Reflection.MethodBuilder(obj, str);
    }
}
