package androidx.viewpager2.widget;

final class FakeDrag {
    private final ScrollEventAdapter mScrollEventAdapter;

    /* access modifiers changed from: package-private */
    public boolean isFakeDragging() {
        return this.mScrollEventAdapter.isFakeDragging();
    }
}
