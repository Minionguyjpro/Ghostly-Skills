package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzatc {
    private static final String[] zzdbo = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzdbp = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};

    zzatc() {
    }

    private static void zzd(String str, String str2, String str3) {
        String str4;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazy)).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString("err", str);
            bundle.putString("code", str2);
            if (!TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str3);
                if (parse.getHost() != null) {
                    str4 = parse.getHost();
                    bundle.putString("host", str4);
                }
            }
            str4 = "";
            bundle.putString("host", str4);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(@Nullable SslError sslError) {
        String str;
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            if (primaryError >= 0) {
                String[] strArr = zzdbp;
                if (primaryError < strArr.length) {
                    str = strArr[primaryError];
                    zzd("ssl_err", str, sslError.getUrl());
                }
            }
            str = String.valueOf(primaryError);
            zzd("ssl_err", str, sslError.getUrl());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(int i, String str) {
        String str2;
        if (i < 0) {
            int i2 = (-i) - 1;
            String[] strArr = zzdbo;
            if (i2 < strArr.length) {
                str2 = strArr[i2];
                zzd("http_err", str2, str);
            }
        }
        str2 = String.valueOf(i);
        zzd("http_err", str2, str);
    }
}
