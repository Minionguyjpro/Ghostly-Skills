package androidx.media2.widget;

interface MediaTimeProvider {

    public interface OnMediaTimeListener {
    }

    void cancelNotifications(OnMediaTimeListener onMediaTimeListener);

    void scheduleUpdate(OnMediaTimeListener onMediaTimeListener);
}
