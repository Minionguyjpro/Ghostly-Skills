package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzajb {
    public static Uri zzb(Uri uri, Context context) {
        if (!zzbv.zzfh().zzx(context) || !TextUtils.isEmpty(uri.getQueryParameter("fbs_aeid"))) {
            return uri;
        }
        String zzab = zzbv.zzfh().zzab(context);
        Uri zzb = zzb(uri.toString(), "fbs_aeid", zzab);
        zzbv.zzfh().zze(context, zzab);
        return zzb;
    }

    private static Uri zzb(String str, String str2, String str3) {
        int indexOf = str.indexOf("&adurl");
        if (indexOf == -1) {
            indexOf = str.indexOf("?adurl");
        }
        if (indexOf == -1) {
            return Uri.parse(str).buildUpon().appendQueryParameter(str2, str3).build();
        }
        int i = indexOf + 1;
        return Uri.parse(str.substring(0, i) + str2 + "=" + str3 + "&" + str.substring(i));
    }

    public static String zzb(String str, Context context) {
        String zzab;
        if (!zzbv.zzfh().zzs(context) || TextUtils.isEmpty(str) || (zzab = zzbv.zzfh().zzab(context)) == null) {
            return str;
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxr)).booleanValue()) {
            String str2 = (String) zzkb.zzik().zzd(zznk.zzaxs);
            if (!str.contains(str2)) {
                return str;
            }
            if (zzbv.zzek().zzcx(str)) {
                zzbv.zzfh().zze(context, zzab);
                return str.replace(str2, zzab);
            } else if (!zzbv.zzek().zzcy(str)) {
                return str;
            } else {
                zzbv.zzfh().zzf(context, zzab);
                return str.replace(str2, zzab);
            }
        } else if (str.contains("fbs_aeid")) {
            return str;
        } else {
            if (zzbv.zzek().zzcx(str)) {
                zzbv.zzfh().zze(context, zzab);
                return zzb(str, "fbs_aeid", zzab).toString();
            } else if (!zzbv.zzek().zzcy(str)) {
                return str;
            } else {
                zzbv.zzfh().zzf(context, zzab);
                return zzb(str, "fbs_aeid", zzab).toString();
            }
        }
    }

    public static String zzc(String str, Context context) {
        String zzab;
        if (!zzbv.zzfh().zzs(context) || TextUtils.isEmpty(str) || (zzab = zzbv.zzfh().zzab(context)) == null || !zzbv.zzek().zzcy(str)) {
            return str;
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzaxr)).booleanValue()) {
            return !str.contains("fbs_aeid") ? zzb(str, "fbs_aeid", zzab).toString() : str;
        }
        String str2 = (String) zzkb.zzik().zzd(zznk.zzaxs);
        return str.contains(str2) ? str.replace(str2, zzab) : str;
    }
}
