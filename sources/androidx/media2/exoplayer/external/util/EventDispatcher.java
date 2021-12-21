package androidx.media2.exoplayer.external.util;

import android.os.Handler;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class EventDispatcher<T> {
    private final CopyOnWriteArrayList<HandlerAndListener<T>> listeners = new CopyOnWriteArrayList<>();

    public interface Event<T> {
        void sendTo(T t);
    }

    public void addListener(Handler handler, T t) {
        Assertions.checkArgument((handler == null || t == null) ? false : true);
        removeListener(t);
        this.listeners.add(new HandlerAndListener(handler, t));
    }

    public void removeListener(T t) {
        Iterator<HandlerAndListener<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            HandlerAndListener next = it.next();
            if (next.listener == t) {
                next.release();
                this.listeners.remove(next);
            }
        }
    }

    public void dispatch(Event<T> event) {
        Iterator<HandlerAndListener<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().dispatch(event);
        }
    }

    private static final class HandlerAndListener<T> {
        private final Handler handler;
        /* access modifiers changed from: private */
        public final T listener;
        private boolean released;

        public HandlerAndListener(Handler handler2, T t) {
            this.handler = handler2;
            this.listener = t;
        }

        public void release() {
            this.released = true;
        }

        public void dispatch(Event<T> event) {
            this.handler.post(new EventDispatcher$HandlerAndListener$$Lambda$0(this, event));
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$dispatch$0$EventDispatcher$HandlerAndListener(Event event) {
            if (!this.released) {
                event.sendTo(this.listener);
            }
        }
    }
}
