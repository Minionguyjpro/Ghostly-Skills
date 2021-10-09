package com.google.firebase.events;

/* compiled from: com.google.firebase:firebase-components@@16.0.0 */
public class Event<T> {
    private final T payload;
    private final Class<T> type;

    public Class<T> getType() {
        return this.type;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", new Object[]{this.type, this.payload});
    }
}
