package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

public final class zzci {
    private static final String[] zzrc = {"/aclk", "/pcs/click"};
    private String zzqy = "googleads.g.doubleclick.net";
    private String zzqz = "/pagead/ads";
    private String zzra = "ad.doubleclick.net";
    private String[] zzrb = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private zzce zzrd;

    public zzci(zzce zzce) {
        this.zzrd = zzce;
    }

    private final Uri zza(Uri uri, Context context, String str, boolean z, View view, Activity activity) throws zzcj {
        try {
            boolean zza = zza(uri);
            if (zza) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzcj("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzcj("Query parameter already exists: ms");
            }
            String zza2 = z ? this.zzrd.zza(context, str, view, activity) : this.zzrd.zza(context);
            if (zza) {
                String uri2 = uri.toString();
                int indexOf = uri2.indexOf(";adurl");
                if (indexOf != -1) {
                    int i = indexOf + 1;
                    return Uri.parse(uri2.substring(0, i) + "dc_ms" + "=" + zza2 + ";" + uri2.substring(i));
                }
                String encodedPath = uri.getEncodedPath();
                int indexOf2 = uri2.indexOf(encodedPath);
                return Uri.parse(uri2.substring(0, encodedPath.length() + indexOf2) + ";" + "dc_ms" + "=" + zza2 + ";" + uri2.substring(indexOf2 + encodedPath.length()));
            }
            String uri3 = uri.toString();
            int indexOf3 = uri3.indexOf("&adurl");
            if (indexOf3 == -1) {
                indexOf3 = uri3.indexOf("?adurl");
            }
            if (indexOf3 == -1) {
                return uri.buildUpon().appendQueryParameter("ms", zza2).build();
            }
            int i2 = indexOf3 + 1;
            return Uri.parse(uri3.substring(0, i2) + "ms" + "=" + zza2 + "&" + uri3.substring(i2));
        } catch (UnsupportedOperationException unused) {
            throw new zzcj("Provided Uri is not in a valid state");
        }
    }

    private final boolean zza(Uri uri) {
        if (uri != null) {
            try {
                return uri.getHost().equals(this.zzra);
            } catch (NullPointerException unused) {
                return false;
            }
        } else {
            throw null;
        }
    }

    public final Uri zza(Uri uri, Context context) throws zzcj {
        return zza(uri, context, (String) null, false, (View) null, (Activity) null);
    }

    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzcj {
        try {
            return zza(uri, context, uri.getQueryParameter("ai"), true, view, activity);
        } catch (UnsupportedOperationException unused) {
            throw new zzcj("Provided Uri is not in a valid state");
        }
    }

    public final void zza(MotionEvent motionEvent) {
        this.zzrd.zza(motionEvent);
    }

    public final zzce zzaa() {
        return this.zzrd;
    }

    public final boolean zzb(Uri uri) {
        if (uri != null) {
            try {
                String host = uri.getHost();
                for (String endsWith : this.zzrb) {
                    if (host.endsWith(endsWith)) {
                        return true;
                    }
                }
            } catch (NullPointerException unused) {
            }
            return false;
        }
        throw null;
    }

    public final boolean zzc(Uri uri) {
        if (zzb(uri)) {
            for (String endsWith : zzrc) {
                if (uri.getPath().endsWith(endsWith)) {
                    return true;
                }
            }
        }
        return false;
    }
}
