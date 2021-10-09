package com.google.android.gms.ads.internal.gmsg;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.internal.ads.zzaab;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajb;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarr;
import com.google.android.gms.internal.ads.zzars;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarz;
import com.google.android.gms.internal.ads.zzasb;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzcj;
import com.google.android.gms.internal.ads.zzjd;
import com.mopub.common.AdType;
import java.net.URISyntaxException;
import java.util.Map;

@zzadh
public final class zzad<T extends zzarr & zzars & zzarw & zzarz & zzasb> implements zzv<T> {
    private final Context mContext;
    private final zzjd zzapt;
    private final zzb zzbll;
    private final zzd zzblm;
    private final zzx zzbmw;
    private final zzaab zzbmx;
    private final zzci zzbna;
    private final zzt zzbnb;
    private final zzn zzbnc;
    private final zzaqw zzbnd = null;
    private final zzang zzzw;

    public zzad(Context context, zzang zzang, zzci zzci, zzt zzt, zzjd zzjd, zzb zzb, zzd zzd, zzn zzn, zzx zzx, zzaab zzaab) {
        this.mContext = context;
        this.zzzw = zzang;
        this.zzbna = zzci;
        this.zzbnb = zzt;
        this.zzapt = zzjd;
        this.zzbll = zzb;
        this.zzblm = zzd;
        this.zzbmw = zzx;
        this.zzbmx = zzaab;
        this.zzbnc = zzn;
    }

    static String zza(Context context, zzci zzci, String str, View view, Activity activity) {
        if (zzci == null) {
            return str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (zzci.zzc(parse)) {
                parse = zzci.zza(parse, context, view, activity);
            }
            return parse.toString();
        } catch (zzcj unused) {
            return str;
        } catch (Exception e) {
            zzbv.zzeo().zza(e, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            return str;
        }
    }

    private static boolean zzg(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int zzh(Map<String, String> map) {
        String str = map.get("o");
        if (str == null) {
            return -1;
        }
        if ("p".equalsIgnoreCase(str)) {
            return zzbv.zzem().zzrm();
        }
        if ("l".equalsIgnoreCase(str)) {
            return zzbv.zzem().zzrl();
        }
        if ("c".equalsIgnoreCase(str)) {
            return zzbv.zzem().zzrn();
        }
        return -1;
    }

    private final void zzl(boolean z) {
        zzaab zzaab = this.zzbmx;
        if (zzaab != null) {
            zzaab.zzm(z);
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzarr zzarr = (zzarr) obj;
        String zzb = zzajb.zzb((String) map.get("u"), zzarr.getContext());
        String str = (String) map.get("a");
        if (str == null) {
            zzakb.zzdk("Action missing from an open GMSG.");
            return;
        }
        zzx zzx = this.zzbmw;
        if (zzx != null && !zzx.zzcy()) {
            this.zzbmw.zzs(zzb);
        } else if ("expand".equalsIgnoreCase(str)) {
            if (((zzars) zzarr).zzuj()) {
                zzakb.zzdk("Cannot expand WebView that is already expanded.");
                return;
            }
            zzl(false);
            ((zzarw) zzarr).zza(zzg(map), zzh(map));
        } else if ("webapp".equalsIgnoreCase(str)) {
            zzl(false);
            zzarw zzarw = (zzarw) zzarr;
            boolean zzg = zzg(map);
            if (zzb != null) {
                zzarw.zza(zzg, zzh(map), zzb);
            } else {
                zzarw.zza(zzg, zzh(map), (String) map.get(AdType.HTML), (String) map.get("baseurl"));
            }
        } else if (!"app".equalsIgnoreCase(str) || !"true".equalsIgnoreCase((String) map.get("system_browser"))) {
            zzl(true);
            String str2 = (String) map.get("intent_url");
            Intent intent = null;
            if (!TextUtils.isEmpty(str2)) {
                try {
                    intent = Intent.parseUri(str2, 0);
                } catch (URISyntaxException e) {
                    String valueOf = String.valueOf(str2);
                    zzakb.zzb(valueOf.length() != 0 ? "Error parsing the url: ".concat(valueOf) : new String("Error parsing the url: "), e);
                }
            }
            if (!(intent == null || intent.getData() == null)) {
                Uri data = intent.getData();
                String uri = data.toString();
                if (!TextUtils.isEmpty(uri)) {
                    try {
                        uri = zza(zzarr.getContext(), ((zzarz) zzarr).zzui(), uri, ((zzasb) zzarr).getView(), zzarr.zzto());
                    } catch (Exception e2) {
                        zzakb.zzb("Error occurred while adding signals.", e2);
                        zzbv.zzeo().zza(e2, "OpenGmsgHandler.onGmsg");
                    }
                    try {
                        data = Uri.parse(uri);
                    } catch (Exception e3) {
                        String valueOf2 = String.valueOf(uri);
                        zzakb.zzb(valueOf2.length() != 0 ? "Error parsing the uri: ".concat(valueOf2) : new String("Error parsing the uri: "), e3);
                        zzbv.zzeo().zza(e3, "OpenGmsgHandler.onGmsg");
                    }
                }
                intent.setData(data);
            }
            if (intent != null) {
                ((zzarw) zzarr).zza(new zzc(intent));
                return;
            }
            if (!TextUtils.isEmpty(zzb)) {
                zzb = zza(zzarr.getContext(), ((zzarz) zzarr).zzui(), zzb, ((zzasb) zzarr).getView(), zzarr.zzto());
            }
            ((zzarw) zzarr).zza(new zzc((String) map.get("i"), zzb, (String) map.get("m"), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
        } else {
            zzl(true);
            zzarr.getContext();
            if (TextUtils.isEmpty(zzb)) {
                zzakb.zzdk("Destination url cannot be empty.");
                return;
            }
            try {
                ((zzarw) zzarr).zza(new zzc(new zzae(zzarr.getContext(), ((zzarz) zzarr).zzui(), ((zzasb) zzarr).getView()).zzi(map)));
            } catch (ActivityNotFoundException e4) {
                zzakb.zzdk(e4.getMessage());
            }
        }
    }
}
