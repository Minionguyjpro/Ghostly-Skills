package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.Map;

@zzadh
public final class zzaqd implements zzv<zzapw> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzapw zzapw = (zzapw) obj;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbae)).booleanValue()) {
            zzarl zztm = zzapw.zztm();
            if (zztm == null) {
                try {
                    zzarl zzarl = new zzarl(zzapw, Float.parseFloat((String) map.get(VastIconXmlManager.DURATION)), "1".equals(map.get("customControlsAllowed")), "1".equals(map.get("clickToExpandAllowed")));
                    zzapw.zza(zzarl);
                    zztm = zzarl;
                } catch (NullPointerException e) {
                    e = e;
                    zzakb.zzb("Unable to parse videoMeta message.", e);
                    zzbv.zzeo().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                } catch (NumberFormatException e2) {
                    e = e2;
                    zzakb.zzb("Unable to parse videoMeta message.", e);
                    zzbv.zzeo().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                }
            }
            boolean equals = "1".equals(map.get("muted"));
            float parseFloat = Float.parseFloat((String) map.get("currentTime"));
            int parseInt = Integer.parseInt((String) map.get("playbackState"));
            if (parseInt < 0 || 3 < parseInt) {
                parseInt = 0;
            }
            String str = (String) map.get("aspectRatio");
            float parseFloat2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (zzakb.isLoggable(3)) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 79);
                sb.append("Video Meta GMSG: isMuted : ");
                sb.append(equals);
                sb.append(" , playbackState : ");
                sb.append(parseInt);
                sb.append(" , aspectRatio : ");
                sb.append(str);
                zzakb.zzck(sb.toString());
            }
            zztm.zza(parseFloat, parseInt, equals, parseFloat2);
        }
    }
}
