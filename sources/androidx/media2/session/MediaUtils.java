package androidx.media2.session;

import android.os.Bundle;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaParcelUtils;
import androidx.media2.common.ParcelImplListSlice;
import androidx.versionedparcelable.ParcelImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class MediaUtils {
    public static final Executor DIRECT_EXECUTOR = new Executor() {
        public void execute(Runnable runnable) {
            runnable.run();
        }
    };
    private static final Map<String, String> METADATA_COMPAT_KEY_TO_METADATA_KEY = new HashMap();
    private static final Map<String, String> METADATA_KEY_TO_METADATA_COMPAT_KEY = new HashMap();
    public static final MediaBrowserServiceCompat.BrowserRoot sDefaultBrowserRoot = new MediaBrowserServiceCompat.BrowserRoot("androidx.media2.session.MediaLibraryService", (Bundle) null);

    static {
        METADATA_COMPAT_KEY_TO_METADATA_KEY.put("android.media.metadata.ADVERTISEMENT", "androidx.media2.metadata.ADVERTISEMENT");
        METADATA_COMPAT_KEY_TO_METADATA_KEY.put("android.media.metadata.BT_FOLDER_TYPE", "androidx.media2.metadata.BROWSABLE");
        METADATA_COMPAT_KEY_TO_METADATA_KEY.put("android.media.metadata.DOWNLOAD_STATUS", "androidx.media2.metadata.DOWNLOAD_STATUS");
        for (Map.Entry next : METADATA_COMPAT_KEY_TO_METADATA_KEY.entrySet()) {
            if (!METADATA_KEY_TO_METADATA_COMPAT_KEY.containsKey(next.getValue())) {
                METADATA_KEY_TO_METADATA_COMPAT_KEY.put(next.getValue(), next.getKey());
            } else {
                throw new RuntimeException("Shouldn't map to the same value");
            }
        }
    }

    public static MediaItem upcastForPreparceling(MediaItem mediaItem) {
        return (mediaItem == null || mediaItem.getClass() == MediaItem.class) ? mediaItem : new MediaItem.Builder().setStartPosition(mediaItem.getStartPosition()).setEndPosition(mediaItem.getEndPosition()).setMetadata(mediaItem.getMetadata()).build();
    }

    public static List<MediaItem> convertParcelImplListSliceToMediaItemList(ParcelImplListSlice parcelImplListSlice) {
        if (parcelImplListSlice == null) {
            return null;
        }
        List<ParcelImpl> list = parcelImplListSlice.getList();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ParcelImpl parcelImpl = list.get(i);
            if (parcelImpl != null) {
                arrayList.add((MediaItem) MediaParcelUtils.fromParcelable(parcelImpl));
            }
        }
        return arrayList;
    }

    public static ParcelImplListSlice convertMediaItemListToParcelImplListSlice(List<MediaItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            MediaItem mediaItem = list.get(i);
            if (mediaItem != null) {
                arrayList.add(MediaParcelUtils.toParcelable(mediaItem));
            }
        }
        return new ParcelImplListSlice((List<ParcelImpl>) arrayList);
    }
}
