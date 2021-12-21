package androidx.media2.exoplayer.external.source.hls;

import androidx.media2.exoplayer.external.upstream.DataSource;

public final class DefaultHlsDataSourceFactory implements HlsDataSourceFactory {
    private final DataSource.Factory dataSourceFactory;

    public DefaultHlsDataSourceFactory(DataSource.Factory factory) {
        this.dataSourceFactory = factory;
    }

    public DataSource createDataSource(int i) {
        return this.dataSourceFactory.createDataSource();
    }
}
