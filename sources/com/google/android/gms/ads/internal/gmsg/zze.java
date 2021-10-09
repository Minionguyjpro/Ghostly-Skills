package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.plus.PlusShare;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.Map;

@zzadh
public final class zze implements zzv<zzaqw> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw zzaqw = (zzaqw) obj;
        String str = (String) map.get("action");
        if ("tick".equals(str)) {
            String str2 = (String) map.get(PlusShare.KEY_CALL_TO_ACTION_LABEL);
            String str3 = (String) map.get("start_label");
            String str4 = (String) map.get(AvidJSONUtil.KEY_TIMESTAMP);
            if (TextUtils.isEmpty(str2)) {
                zzakb.zzdk("No label given for CSI tick.");
            } else if (TextUtils.isEmpty(str4)) {
                zzakb.zzdk("No timestamp given for CSI tick.");
            } else {
                try {
                    long elapsedRealtime = zzbv.zzer().elapsedRealtime() + (Long.parseLong(str4) - zzbv.zzer().currentTimeMillis());
                    if (TextUtils.isEmpty(str3)) {
                        str3 = "native:view_load";
                    }
                    zzaqw.zztp().zza(str2, str3, elapsedRealtime);
                } catch (NumberFormatException e) {
                    zzakb.zzc("Malformed timestamp for CSI tick.", e);
                }
            }
        } else if ("experiment".equals(str)) {
            String str5 = (String) map.get("value");
            if (TextUtils.isEmpty(str5)) {
                zzakb.zzdk("No value given for CSI experiment.");
                return;
            }
            zznx zzji = zzaqw.zztp().zzji();
            if (zzji == null) {
                zzakb.zzdk("No ticker for WebView, dropping experiment ID.");
            } else {
                zzji.zze("e", str5);
            }
        } else if ("extra".equals(str)) {
            String str6 = (String) map.get("name");
            String str7 = (String) map.get("value");
            if (TextUtils.isEmpty(str7)) {
                zzakb.zzdk("No value given for CSI extra.");
            } else if (TextUtils.isEmpty(str6)) {
                zzakb.zzdk("No name given for CSI extra.");
            } else {
                zznx zzji2 = zzaqw.zztp().zzji();
                if (zzji2 == null) {
                    zzakb.zzdk("No ticker for WebView, dropping extra parameter.");
                } else {
                    zzji2.zze(str6, str7);
                }
            }
        }
    }
}
