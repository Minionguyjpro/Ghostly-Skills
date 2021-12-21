package com.google.android.gms.internal.ads;

final class zzbea {
    static String zzaq(zzbah zzbah) {
        String str;
        zzbeb zzbeb = new zzbeb(zzbah);
        StringBuilder sb = new StringBuilder(zzbeb.size());
        for (int i = 0; i < zzbeb.size(); i++) {
            int zzbn = zzbeb.zzbn(i);
            if (zzbn == 34) {
                str = "\\\"";
            } else if (zzbn == 39) {
                str = "\\'";
            } else if (zzbn != 92) {
                switch (zzbn) {
                    case 7:
                        str = "\\a";
                        break;
                    case 8:
                        str = "\\b";
                        break;
                    case 9:
                        str = "\\t";
                        break;
                    case 10:
                        str = "\\n";
                        break;
                    case 11:
                        str = "\\v";
                        break;
                    case 12:
                        str = "\\f";
                        break;
                    case 13:
                        str = "\\r";
                        break;
                    default:
                        if (zzbn < 32 || zzbn > 126) {
                            sb.append('\\');
                            sb.append((char) (((zzbn >>> 6) & 3) + 48));
                            sb.append((char) (((zzbn >>> 3) & 7) + 48));
                            zzbn = (zzbn & 7) + 48;
                        }
                        sb.append((char) zzbn);
                        continue;
                }
            } else {
                str = "\\\\";
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
