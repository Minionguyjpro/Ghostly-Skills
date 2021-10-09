package androidx.media2.common;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.versionedparcelable.CustomVersionedParcelable;
import androidx.versionedparcelable.ParcelImpl;
import androidx.versionedparcelable.VersionedParcelable;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.ArrayList;
import java.util.List;

public final class MediaMetadata extends CustomVersionedParcelable {
    static final ArrayMap<String, Integer> METADATA_KEYS_TYPE;
    private static final String[] PREFERRED_BITMAP_ORDER = {"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
    private static final String[] PREFERRED_DESCRIPTION_ORDER = {"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
    private static final String[] PREFERRED_URI_ORDER = {"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
    ParcelImplListSlice mBitmapListSlice;
    Bundle mBundle;

    static {
        ArrayMap<String, Integer> arrayMap = new ArrayMap<>();
        METADATA_KEYS_TYPE = arrayMap;
        arrayMap.put("android.media.metadata.TITLE", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.ARTIST", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.DURATION", 0);
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.AUTHOR", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.WRITER", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.COMPOSER", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.COMPILATION", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.DATE", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.YEAR", 0);
        METADATA_KEYS_TYPE.put("android.media.metadata.GENRE", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.TRACK_NUMBER", 0);
        METADATA_KEYS_TYPE.put("android.media.metadata.NUM_TRACKS", 0);
        METADATA_KEYS_TYPE.put("android.media.metadata.DISC_NUMBER", 0);
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ARTIST", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.ART", 2);
        METADATA_KEYS_TYPE.put("android.media.metadata.ART_URI", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART", 2);
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART_URI", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.USER_RATING", 3);
        METADATA_KEYS_TYPE.put("android.media.metadata.RATING", 3);
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_TITLE", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON", 2);
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_ID", 1);
        METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_URI", 1);
        METADATA_KEYS_TYPE.put("androidx.media2.metadata.RADIO_FREQUENCY", 4);
        METADATA_KEYS_TYPE.put("androidx.media2.metadata.RADIO_PROGRAM_NAME", 1);
        METADATA_KEYS_TYPE.put("androidx.media2.metadata.BROWSABLE", 0);
        METADATA_KEYS_TYPE.put("androidx.media2.metadata.PLAYABLE", 0);
        METADATA_KEYS_TYPE.put("androidx.media2.metadata.ADVERTISEMENT", 0);
        METADATA_KEYS_TYPE.put("androidx.media2.metadata.DOWNLOAD_STATUS", 0);
        METADATA_KEYS_TYPE.put("androidx.media2.metadata.EXTRAS", 5);
    }

    MediaMetadata() {
    }

    public boolean containsKey(String str) {
        if (str != null) {
            return this.mBundle.containsKey(str);
        }
        throw new NullPointerException("key shouldn't be null");
    }

    public CharSequence getText(String str) {
        if (str != null) {
            return this.mBundle.getCharSequence(str);
        }
        throw new NullPointerException("key shouldn't be null");
    }

    public String getString(String str) {
        if (str != null) {
            CharSequence charSequence = this.mBundle.getCharSequence(str);
            if (charSequence != null) {
                return charSequence.toString();
            }
            return null;
        }
        throw new NullPointerException("key shouldn't be null");
    }

    public long getLong(String str) {
        if (str != null) {
            return this.mBundle.getLong(str, 0);
        }
        throw new NullPointerException("key shouldn't be null");
    }

    public Bitmap getBitmap(String str) {
        if (str != null) {
            try {
                return (Bitmap) this.mBundle.getParcelable(str);
            } catch (Exception e) {
                Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", e);
                return null;
            }
        } else {
            throw new NullPointerException("key shouldn't be null");
        }
    }

    public String toString() {
        return this.mBundle.toString();
    }

    public void onPreParceling(boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (String str : this.mBundle.keySet()) {
            Object obj = this.mBundle.get(str);
            if (obj instanceof Bitmap) {
                arrayList.add(MediaParcelUtils.toParcelable(new BitmapEntry(str, (Bitmap) obj)));
                arrayList2.add(str);
            }
        }
        for (String remove : arrayList2) {
            this.mBundle.remove(remove);
        }
        this.mBitmapListSlice = new ParcelImplListSlice((List<ParcelImpl>) arrayList);
    }

    public void onPostParceling() {
        List<ParcelImpl> list = this.mBitmapListSlice.getList();
        for (ParcelImpl fromParcelable : list) {
            BitmapEntry bitmapEntry = (BitmapEntry) MediaParcelUtils.fromParcelable(fromParcelable);
            this.mBundle.putParcelable(bitmapEntry.getKey(), bitmapEntry.getBitmap());
        }
        list.clear();
        this.mBitmapListSlice = null;
    }

    static final class BitmapEntry implements VersionedParcelable {
        Bitmap mBitmap;
        String mKey;

        BitmapEntry() {
        }

        BitmapEntry(String str, Bitmap bitmap) {
            this.mKey = str;
            this.mBitmap = bitmap;
            int bitmapSizeInBytes = getBitmapSizeInBytes(bitmap);
            if (bitmapSizeInBytes > 262144) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                double d = (double) bitmapSizeInBytes;
                Double.isNaN(d);
                double sqrt = Math.sqrt(262144.0d / d);
                double d2 = (double) width;
                Double.isNaN(d2);
                int i = (int) (d2 * sqrt);
                double d3 = (double) height;
                Double.isNaN(d3);
                int i2 = (int) (d3 * sqrt);
                Log.i("MediaMetadata", "Scaling large bitmap of " + width + AvidJSONUtil.KEY_X + height + " into " + i + AvidJSONUtil.KEY_X + i2);
                this.mBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
            }
        }

        /* access modifiers changed from: package-private */
        public String getKey() {
            return this.mKey;
        }

        /* access modifiers changed from: package-private */
        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        private int getBitmapSizeInBytes(Bitmap bitmap) {
            if (Build.VERSION.SDK_INT >= 19) {
                return bitmap.getAllocationByteCount();
            }
            return bitmap.getByteCount();
        }
    }
}
