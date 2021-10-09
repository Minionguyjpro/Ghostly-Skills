package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaae extends zzaal {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzbgp;

    public zzaae(zzaqw zzaqw, Map<String, String> map) {
        super(zzaqw, "storePicture");
        this.zzbgp = map;
        this.mContext = zzaqw.zzto();
    }

    public final void execute() {
        if (this.mContext == null) {
            zzbw("Activity context is not available");
            return;
        }
        zzbv.zzek();
        if (!zzakk.zzao(this.mContext).zziy()) {
            zzbw("Feature is not supported by the device.");
            return;
        }
        String str = this.zzbgp.get("iurl");
        if (TextUtils.isEmpty(str)) {
            zzbw("Image url cannot be empty.");
        } else if (!URLUtil.isValidUrl(str)) {
            String valueOf = String.valueOf(str);
            zzbw(valueOf.length() != 0 ? "Invalid image url: ".concat(valueOf) : new String("Invalid image url: "));
        } else {
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            zzbv.zzek();
            if (!zzakk.zzcw(lastPathSegment)) {
                String valueOf2 = String.valueOf(lastPathSegment);
                zzbw(valueOf2.length() != 0 ? "Image type not recognized: ".concat(valueOf2) : new String("Image type not recognized: "));
                return;
            }
            Resources resources = zzbv.zzeo().getResources();
            zzbv.zzek();
            AlertDialog.Builder zzan = zzakk.zzan(this.mContext);
            zzan.setTitle(resources != null ? resources.getString(R.string.s1) : "Save image");
            zzan.setMessage(resources != null ? resources.getString(R.string.s2) : "Allow Ad to store image in Picture gallery?");
            zzan.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzaaf(this, str, lastPathSegment));
            zzan.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzaag(this));
            zzan.create().show();
        }
    }
}
