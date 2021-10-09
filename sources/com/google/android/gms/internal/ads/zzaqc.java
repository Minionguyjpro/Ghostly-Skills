package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.appnext.base.b.d;
import com.appnext.core.a.b;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zzaqc implements zzv<zzapw> {
    private boolean zzdau;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            zzkb.zzif();
            return zzamu.zza(context, Integer.parseInt(str2));
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(str2).length());
            sb.append("Could not parse ");
            sb.append(str);
            sb.append(" in a video GMSG: ");
            sb.append(str2);
            zzakb.zzdk(sb.toString());
            return i;
        }
    }

    private static void zza(zzapi zzapi, Map<String, String> map) {
        String str = map.get("minBufferMs");
        String str2 = map.get("maxBufferMs");
        String str3 = map.get("bufferForPlaybackMs");
        String str4 = map.get("bufferForPlaybackAfterRebufferMs");
        if (str != null) {
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                zzakb.zzdk(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", new Object[]{str, str2}));
                return;
            }
        }
        if (str2 != null) {
            Integer.parseInt(str2);
        }
        if (str3 != null) {
            Integer.parseInt(str3);
        }
        if (str4 != null) {
            Integer.parseInt(str4);
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        int i;
        int i2;
        zzapw zzapw = (zzapw) obj;
        String str = (String) map.get("action");
        if (str == null) {
            zzakb.zzdk("Action missing from video GMSG.");
            return;
        }
        if (zzakb.isLoggable(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String jSONObject2 = jSONObject.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(jSONObject2).length());
            sb.append("Video GMSG: ");
            sb.append(str);
            sb.append(" ");
            sb.append(jSONObject2);
            zzakb.zzck(sb.toString());
        }
        if ("background".equals(str)) {
            String str2 = (String) map.get("color");
            if (TextUtils.isEmpty(str2)) {
                zzakb.zzdk("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzapw.setBackgroundColor(Color.parseColor(str2));
            } catch (IllegalArgumentException unused) {
                zzakb.zzdk("Invalid color parameter in video GMSG.");
            }
        } else {
            if ("decoderProps".equals(str)) {
                String str3 = (String) map.get("mimeTypes");
                if (str3 == null) {
                    zzakb.zzdk("No MIME types specified for decoder properties inspection.");
                    zzapi.zza(zzapw, "missingMimeTypes");
                } else if (Build.VERSION.SDK_INT < 16) {
                    zzakb.zzdk("Video decoder properties available on API versions >= 16.");
                    zzapi.zza(zzapw, "deficientApiVersion");
                } else {
                    HashMap hashMap = new HashMap();
                    for (String str4 : str3.split(",")) {
                        hashMap.put(str4, zzams.zzdd(str4.trim()));
                    }
                    zzapi.zza(zzapw, (Map<String, List<Map<String, Object>>>) hashMap);
                }
            } else {
                zzapn zztl = zzapw.zztl();
                if (zztl == null) {
                    zzakb.zzdk("Could not get underlay container for a video GMSG.");
                    return;
                }
                boolean equals = b.hX.equals(str);
                boolean equals2 = "position".equals(str);
                if (equals || equals2) {
                    Context context = zzapw.getContext();
                    int zza = zza(context, map, AvidJSONUtil.KEY_X, 0);
                    int zza2 = zza(context, map, AvidJSONUtil.KEY_Y, 0);
                    int zza3 = zza(context, map, "w", -1);
                    int zza4 = zza(context, map, "h", -1);
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzbca)).booleanValue()) {
                        zza3 = Math.min(zza3, zzapw.zzts() - zza);
                        i = Math.min(zza4, zzapw.zztr() - zza2);
                    } else {
                        i = zza4;
                    }
                    try {
                        i2 = Integer.parseInt((String) map.get("player"));
                    } catch (NumberFormatException unused2) {
                        i2 = 0;
                    }
                    boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                    if (!equals || zztl.zzth() != null) {
                        zztl.zze(zza, zza2, zza3, i);
                        return;
                    }
                    zztl.zza(zza, zza2, zza3, i, i2, parseBoolean, new zzapv((String) map.get("flags")));
                    zzapi zzth = zztl.zzth();
                    if (zzth != null) {
                        zza(zzth, (Map<String, String>) map);
                        return;
                    }
                    return;
                }
                zzapi zzth2 = zztl.zzth();
                if (zzth2 == null) {
                    zzapi.zza(zzapw);
                } else if ("click".equals(str)) {
                    Context context2 = zzapw.getContext();
                    int zza5 = zza(context2, map, AvidJSONUtil.KEY_X, 0);
                    int zza6 = zza(context2, map, AvidJSONUtil.KEY_Y, 0);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) zza5, (float) zza6, 0);
                    zzth2.zzf(obtain);
                    obtain.recycle();
                } else if ("currentTime".equals(str)) {
                    String str5 = (String) map.get(d.fl);
                    if (str5 == null) {
                        zzakb.zzdk("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        zzth2.seekTo((int) (Float.parseFloat(str5) * 1000.0f));
                    } catch (NumberFormatException unused3) {
                        String valueOf = String.valueOf(str5);
                        zzakb.zzdk(valueOf.length() != 0 ? "Could not parse time parameter from currentTime video GMSG: ".concat(valueOf) : new String("Could not parse time parameter from currentTime video GMSG: "));
                    }
                } else if ("hide".equals(str)) {
                    zzth2.setVisibility(4);
                } else if ("load".equals(str)) {
                    zzth2.zzta();
                } else if ("loadControl".equals(str)) {
                    zza(zzth2, (Map<String, String>) map);
                } else if ("muted".equals(str)) {
                    if (Boolean.parseBoolean((String) map.get("muted"))) {
                        zzth2.zztb();
                    } else {
                        zzth2.zztc();
                    }
                } else if ("pause".equals(str)) {
                    zzth2.pause();
                } else if ("play".equals(str)) {
                    zzth2.play();
                } else if ("show".equals(str)) {
                    zzth2.setVisibility(0);
                } else if ("src".equals(str)) {
                    zzth2.zzdn((String) map.get("src"));
                } else if ("touchMove".equals(str)) {
                    Context context3 = zzapw.getContext();
                    zzth2.zza((float) zza(context3, map, "dx", 0), (float) zza(context3, map, "dy", 0));
                    if (!this.zzdau) {
                        zzapw.zznp();
                        this.zzdau = true;
                    }
                } else if (AvidVideoPlaybackListenerImpl.VOLUME.equals(str)) {
                    String str6 = (String) map.get(AvidVideoPlaybackListenerImpl.VOLUME);
                    if (str6 == null) {
                        zzakb.zzdk("Level parameter missing from volume video GMSG.");
                        return;
                    }
                    try {
                        zzth2.setVolume(Float.parseFloat(str6));
                    } catch (NumberFormatException unused4) {
                        String valueOf2 = String.valueOf(str6);
                        zzakb.zzdk(valueOf2.length() != 0 ? "Could not parse volume parameter from volume video GMSG: ".concat(valueOf2) : new String("Could not parse volume parameter from volume video GMSG: "));
                    }
                } else if ("watermark".equals(str)) {
                    zzth2.zztd();
                } else {
                    String valueOf3 = String.valueOf(str);
                    zzakb.zzdk(valueOf3.length() != 0 ? "Unknown video action: ".concat(valueOf3) : new String("Unknown video action: "));
                }
            }
        }
    }
}
