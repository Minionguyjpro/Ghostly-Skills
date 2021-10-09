package androidx.media2.widget;

import android.net.Uri;
import com.mopub.common.Constants;

class UriUtil {
    static boolean isFromNetwork(Uri uri) {
        String scheme = uri.getScheme();
        if (scheme == null) {
            return false;
        }
        if (scheme.equals(Constants.HTTP) || scheme.equals(Constants.HTTPS) || scheme.equals("rtsp")) {
            return true;
        }
        return false;
    }
}
