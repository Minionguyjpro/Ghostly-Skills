package androidx.media2.common;

import android.net.Uri;
import androidx.core.util.Preconditions;
import androidx.media2.common.MediaItem;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UriMediaItem extends MediaItem {
    Uri mUri;
    List<HttpCookie> mUriCookies;
    Map<String, String> mUriHeader;

    UriMediaItem() {
    }

    UriMediaItem(Builder builder) {
        super((MediaItem.Builder) builder);
        this.mUri = builder.mUri;
        this.mUriHeader = builder.mUriHeader;
        this.mUriCookies = builder.mUriCookies;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public static final class Builder extends MediaItem.Builder {
        Uri mUri;
        List<HttpCookie> mUriCookies;
        Map<String, String> mUriHeader;

        public Builder(Uri uri) {
            this(uri, (Map<String, String>) null, (List<HttpCookie>) null);
        }

        public Builder(Uri uri, Map<String, String> map, List<HttpCookie> list) {
            CookieHandler cookieHandler;
            Preconditions.checkNotNull(uri, "uri cannot be null");
            this.mUri = uri;
            if (list == null || (cookieHandler = CookieHandler.getDefault()) == null || (cookieHandler instanceof CookieManager)) {
                this.mUri = uri;
                if (map != null) {
                    this.mUriHeader = new HashMap(map);
                }
                if (list != null) {
                    this.mUriCookies = new ArrayList(list);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("The cookie handler has to be of CookieManager type when cookies are provided");
        }

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            return (Builder) super.setMetadata(mediaMetadata);
        }

        public Builder setStartPosition(long j) {
            return (Builder) super.setStartPosition(j);
        }

        public Builder setEndPosition(long j) {
            return (Builder) super.setEndPosition(j);
        }

        public UriMediaItem build() {
            return new UriMediaItem(this);
        }
    }
}
