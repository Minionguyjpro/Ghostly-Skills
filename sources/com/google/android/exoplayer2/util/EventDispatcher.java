package com.google.android.exoplayer2.util;

import android.os.Handler;
import com.google.android.exoplayer2.util.EventDispatcher;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class EventDispatcher<T> {
    private final CopyOnWriteArrayList<HandlerAndListener<T>> listeners = new CopyOnWriteArrayList<>();

    public interface Event<T> {
        void sendTo(T t);
    }

    public void dispatch(Event<T> event) {
        Iterator<HandlerAndListener<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().dispatch(event);
        }
    }

    private static final class HandlerAndListener<T> {
        private final Handler handler;
        private final T listener;
        private boolean released;

        public void dispatch(Event<T> event) {
            this.handler.post(new Runnable(event) {
                public final /* synthetic */ EventDispatcher.Event f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    EventDispatcher.HandlerAndListener.this.lambda$dispatch$0$EventDispatcher$HandlerAndListener(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$dispatch$0$EventDispatcher$HandlerAndListener(Event event) {
            if (!this.released) {
                event.sendTo(this.listener);
            }
        }
    }
}
