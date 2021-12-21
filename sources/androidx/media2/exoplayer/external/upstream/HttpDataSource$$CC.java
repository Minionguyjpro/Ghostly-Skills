package androidx.media2.exoplayer.external.upstream;

import android.text.TextUtils;
import androidx.media2.exoplayer.external.util.Util;
import com.mopub.common.AdType;

public abstract /* synthetic */ class HttpDataSource$$CC {
    static /* synthetic */ boolean lambda$static$0$HttpDataSource$$CC(String str) {
        String lowerInvariant = Util.toLowerInvariant(str);
        return !TextUtils.isEmpty(lowerInvariant) && (!lowerInvariant.contains("text") || lowerInvariant.contains("text/vtt")) && !lowerInvariant.contains(AdType.HTML) && !lowerInvariant.contains("xml");
    }
}
