package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zac {
    public final Uri zaa;

    public zac(Uri uri) {
        this.zaa = uri;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zaa);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zac)) {
            return false;
        }
        return Objects.equal(((zac) obj).zaa, this.zaa);
    }
}
