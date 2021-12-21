package androidx.media2.exoplayer.external.upstream;

import android.content.Context;
import androidx.media2.exoplayer.external.upstream.DataSource;

public final class DefaultDataSourceFactory implements DataSource.Factory {
    private final DataSource.Factory baseDataSourceFactory;
    private final Context context;
    private final TransferListener listener;

    public DefaultDataSourceFactory(Context context2, String str) {
        this(context2, str, (TransferListener) null);
    }

    public DefaultDataSourceFactory(Context context2, String str, TransferListener transferListener) {
        this(context2, transferListener, (DataSource.Factory) new DefaultHttpDataSourceFactory(str, transferListener));
    }

    public DefaultDataSourceFactory(Context context2, TransferListener transferListener, DataSource.Factory factory) {
        this.context = context2.getApplicationContext();
        this.listener = transferListener;
        this.baseDataSourceFactory = factory;
    }

    public DefaultDataSource createDataSource() {
        DefaultDataSource defaultDataSource = new DefaultDataSource(this.context, this.baseDataSourceFactory.createDataSource());
        TransferListener transferListener = this.listener;
        if (transferListener != null) {
            defaultDataSource.addTransferListener(transferListener);
        }
        return defaultDataSource;
    }
}
