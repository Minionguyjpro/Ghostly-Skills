package com.startapp.common.c;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: StartAppSDK */
public @interface e {
    String a() default "";

    String b() default "";

    boolean c() default false;
}
