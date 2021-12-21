package com.startapp.common.c;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
public class c {
    public static String a(Field field) {
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        if (declaredAnnotations != null && declaredAnnotations.length > 0) {
            Annotation annotation = field.getDeclaredAnnotations()[0];
            if (annotation.annotationType().equals(f.class)) {
                f fVar = (f) annotation;
                if (!"".equals(fVar.f())) {
                    return fVar.f();
                }
            }
        }
        return field.getName();
    }

    public static boolean b(Field field) {
        return Map.class.isAssignableFrom(field.getType());
    }

    public static boolean c(Field field) {
        return List.class.isAssignableFrom(field.getType());
    }

    public static boolean d(Field field) {
        return Set.class.isAssignableFrom(field.getType());
    }

    public static boolean e(Field field) {
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        if (declaredAnnotations == null || declaredAnnotations.length == 0) {
            return false;
        }
        Annotation annotation = field.getDeclaredAnnotations()[0];
        if (!annotation.annotationType().equals(f.class)) {
            return false;
        }
        return ((f) annotation).a();
    }

    public static boolean a(Object obj) {
        Class<?> cls = obj.getClass();
        return cls.equals(Boolean.class) || cls.equals(Integer.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Short.class) || cls.equals(Double.class) || cls.equals(Long.class) || cls.equals(Float.class) || cls.equals(String.class);
    }
}
