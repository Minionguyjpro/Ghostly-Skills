package com.startapp.common.c;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: StartAppSDK */
public @interface f {
    boolean a() default false;

    Class b() default Object.class;

    Class c() default String.class;

    Class d() default String.class;

    Class e() default String.class;

    String f() default "";
}
