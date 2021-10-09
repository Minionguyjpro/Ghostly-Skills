package androidx.media2.common;

public class CallbackMediaItem extends MediaItem {
    DataSourceCallback mDataSourceCallback;

    CallbackMediaItem() {
    }

    public DataSourceCallback getDataSourceCallback() {
        return this.mDataSourceCallback;
    }
}
