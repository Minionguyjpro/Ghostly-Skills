package androidx.media2.exoplayer.external.offline;

import java.util.List;

public interface FilterableManifest<T> {
    T copy(List<StreamKey> list);
}
