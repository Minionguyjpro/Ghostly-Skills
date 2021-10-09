package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzaah {
    private final boolean zzbwr;
    private final boolean zzbws;
    private final boolean zzbwt;
    private final boolean zzbwu;
    private final boolean zzbwv;

    private zzaah(zzaaj zzaaj) {
        this.zzbwr = zzaaj.zzbwr;
        this.zzbws = zzaaj.zzbws;
        this.zzbwt = zzaaj.zzbwt;
        this.zzbwu = zzaaj.zzbwu;
        this.zzbwv = zzaaj.zzbwv;
    }

    public final JSONObject zzng() {
        try {
            return new JSONObject().put("sms", this.zzbwr).put("tel", this.zzbws).put("calendar", this.zzbwt).put("storePicture", this.zzbwu).put("inlineVideo", this.zzbwv);
        } catch (JSONException e) {
            zzakb.zzb("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
