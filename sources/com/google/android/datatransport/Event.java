package com.google.android.datatransport;

/* compiled from: com.google.android.datatransport:transport-api@@2.2.0 */
public abstract class Event<T> {
    public abstract Integer getCode();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public static <T> Event<T> ofTelemetry(T t) {
        return new AutoValue_Event((Integer) null, t, Priority.VERY_LOW);
    }
}
