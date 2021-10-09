package com.google.firebase.events;

/* compiled from: com.google.firebase:firebase-components@@16.0.0 */
public interface Subscriber {
    <T> void subscribe(Class<T> cls, EventHandler<? super T> eventHandler);
}
