package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zzadh
public final class zzanf implements zzamx {
    private final String zzcpq;

    public zzanf() {
        this((String) null);
    }

    public zzanf(String str) {
        this.zzcpq = str;
    }

    public final void zzcz(String str) {
        String sb;
        String message;
        StringBuilder sb2;
        HttpURLConnection httpURLConnection;
        try {
            String valueOf = String.valueOf(str);
            zzane.zzck(valueOf.length() != 0 ? "Pinging URL: ".concat(valueOf) : new String("Pinging URL: "));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            zzkb.zzif();
            zzamu.zza(true, httpURLConnection, this.zzcpq);
            zzamy zzamy = new zzamy();
            zzamy.zza(httpURLConnection, (byte[]) null);
            int responseCode = httpURLConnection.getResponseCode();
            zzamy.zza(httpURLConnection, responseCode);
            if (responseCode < 200 || responseCode >= 300) {
                StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 65);
                sb3.append("Received non-success response code ");
                sb3.append(responseCode);
                sb3.append(" from pinging URL: ");
                sb3.append(str);
                zzane.zzdk(sb3.toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            String message2 = e.getMessage();
            StringBuilder sb4 = new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(message2).length());
            sb4.append("Error while parsing ping URL: ");
            sb4.append(str);
            sb4.append(". ");
            sb4.append(message2);
            sb = sb4.toString();
            zzane.zzdk(sb);
        } catch (IOException e2) {
            message = e2.getMessage();
            sb2 = new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(message).length());
            sb2.append("Error while pinging URL: ");
            sb2.append(str);
            sb2.append(". ");
            sb2.append(message);
            sb = sb2.toString();
            zzane.zzdk(sb);
        } catch (RuntimeException e3) {
            message = e3.getMessage();
            sb2 = new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(message).length());
            sb2.append("Error while pinging URL: ");
            sb2.append(str);
            sb2.append(". ");
            sb2.append(message);
            sb = sb2.toString();
            zzane.zzdk(sb);
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
    }
}
