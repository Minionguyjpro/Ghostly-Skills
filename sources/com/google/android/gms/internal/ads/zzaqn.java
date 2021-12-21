package com.google.android.gms.internal.ads;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzadh
public final class zzaqn extends zzaqh {
    private static final Set<String> zzdbg = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzdbh = new DecimalFormat("#,###");
    private File zzdbi;
    private boolean zzdbj;

    public zzaqn(zzapw zzapw) {
        super(zzapw);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzakb.zzdk("Context.getCacheDir() returned null");
            return;
        }
        File file = new File(cacheDir, "admobVideoStreams");
        this.zzdbi = file;
        if (!file.isDirectory() && !this.zzdbi.mkdirs()) {
            String valueOf = String.valueOf(this.zzdbi.getAbsolutePath());
            zzakb.zzdk(valueOf.length() != 0 ? "Could not create preload cache directory at ".concat(valueOf) : new String("Could not create preload cache directory at "));
            this.zzdbi = null;
        } else if (!this.zzdbi.setReadable(true, false) || !this.zzdbi.setExecutable(true, false)) {
            String valueOf2 = String.valueOf(this.zzdbi.getAbsolutePath());
            zzakb.zzdk(valueOf2.length() != 0 ? "Could not set cache file permissions at ".concat(valueOf2) : new String("Could not set cache file permissions at "));
            this.zzdbi = null;
        }
    }

    private final File zzc(File file) {
        return new File(this.zzdbi, String.valueOf(file.getName()).concat(".done"));
    }

    public final void abort() {
        this.zzdbj = true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v12, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v13, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v14, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v15, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v16, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v17, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v18, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v19, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v20, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v21, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v25, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v26, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v28, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v29, resolved type: java.lang.String} */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01ef, code lost:
        if ((r5 instanceof java.net.HttpURLConnection) == false) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        r1 = r5.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01fa, code lost:
        if (r1 < 400) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01fc, code lost:
        r15 = "badUrl";
        r2 = java.lang.String.valueOf(java.lang.Integer.toString(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x020c, code lost:
        if (r2.length() == 0) goto L_0x0214;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x020e, code lost:
        r2 = "HTTP request failed. Code: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0214, code lost:
        r2 = new java.lang.String("HTTP request failed. Code: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r31).length() + 32);
        r4.append("HTTP status code ");
        r4.append(r1);
        r4.append(" at ");
        r4.append(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0241, code lost:
        throw new java.io.IOException(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0242, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:?, code lost:
        r7 = r5.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x024a, code lost:
        if (r7 >= 0) goto L_0x0275;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:?, code lost:
        r1 = java.lang.String.valueOf(r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0256, code lost:
        if (r1.length() == 0) goto L_0x025d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0258, code lost:
        r0 = "Stream cache aborted, missing content-length header at ".concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x025d, code lost:
        r0 = new java.lang.String("Stream cache aborted, missing content-length header at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0263, code lost:
        com.google.android.gms.internal.ads.zzakb.zzdk(r0);
        zza(r9, r12.getAbsolutePath(), "contentLengthMissing", (java.lang.String) null);
        zzdbg.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0274, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
        r1 = zzdbh.format((long) r7);
        r3 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzauy)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x028c, code lost:
        if (r7 <= r3) goto L_0x02e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:?, code lost:
        r2 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 33) + java.lang.String.valueOf(r31).length());
        r2.append("Content length ");
        r2.append(r1);
        r2.append(" exceeds limit at ");
        r2.append(r9);
        com.google.android.gms.internal.ads.zzakb.zzdk(r2.toString());
        r1 = java.lang.String.valueOf(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02c7, code lost:
        if (r1.length() == 0) goto L_0x02ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02c9, code lost:
        r0 = "File too big for full file cache. Size: ".concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02ce, code lost:
        r0 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02d4, code lost:
        zza(r9, r12.getAbsolutePath(), "sizeExceeded", r0);
        zzdbg.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02e2, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:?, code lost:
        r4 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 20) + java.lang.String.valueOf(r31).length());
        r4.append("Caching ");
        r4.append(r1);
        r4.append(" bytes from ");
        r4.append(r9);
        com.google.android.gms.internal.ads.zzakb.zzck(r4.toString());
        r5 = java.nio.channels.Channels.newChannel(r5.getInputStream());
        r4 = new java.io.FileOutputStream(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:?, code lost:
        r2 = r4.getChannel();
        r1 = java.nio.ByteBuffer.allocate(1048576);
        r16 = com.google.android.gms.ads.internal.zzbv.zzer();
        r17 = r16.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x033c, code lost:
        r10 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:?, code lost:
        r6 = new com.google.android.gms.internal.ads.zzamj(((java.lang.Long) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzavb)).longValue());
        r13 = ((java.lang.Long) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzava)).longValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0356, code lost:
        r20 = r5.read(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x035a, code lost:
        if (r20 < 0) goto L_0x0472;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x035c, code lost:
        r11 = r11 + r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x035e, code lost:
        if (r11 <= r3) goto L_0x0393;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0360, code lost:
        r15 = "sizeExceeded";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:?, code lost:
        r1 = java.lang.String.valueOf(java.lang.Integer.toString(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0370, code lost:
        if (r1.length() == 0) goto L_0x0378;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0372, code lost:
        r1 = "File too big for full file cache. Size: ".concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0378, code lost:
        r1 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0384, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0385, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0388, code lost:
        r2 = r1;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x038b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x038e, code lost:
        r1 = r10;
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0390, code lost:
        r10 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:?, code lost:
        r1.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x039a, code lost:
        if (r2.write(r1) > 0) goto L_0x0396;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x039c, code lost:
        r1.clear();
        r24 = ((r16.currentTimeMillis() - r17) > (1000 * r13) ? 1 : ((r16.currentTimeMillis() - r17) == (1000 * r13) ? 0 : -1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03ab, code lost:
        if (r24 > 0) goto L_0x0426;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03ad, code lost:
        r20 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03b1, code lost:
        if (r8.zzdbj != false) goto L_0x0416;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03b7, code lost:
        if (r6.tryAcquire() == false) goto L_0x03f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03b9, code lost:
        r21 = r12.getAbsolutePath();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03bf, code lost:
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03c1, code lost:
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x03c3, code lost:
        r24 = r10;
        r10 = com.google.android.gms.internal.ads.zzamu.zzsy;
        r1 = r1;
        r25 = r2;
        r26 = r3;
        r27 = r4;
        r4 = r21;
        r21 = r5;
        r19 = r6;
        r29 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x03e1, code lost:
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:?, code lost:
        r1 = new com.google.android.gms.internal.ads.zzaqi(r30, r31, r4, r11, r7, false);
        r10.post(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x03e8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x03ea, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03eb, code lost:
        r27 = r4;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x03f0, code lost:
        r25 = r2;
        r26 = r3;
        r27 = r4;
        r21 = r5;
        r19 = r6;
        r29 = r7;
        r24 = r10;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0402, code lost:
        r6 = r19;
        r1 = r20;
        r5 = r21;
        r15 = r22;
        r10 = r24;
        r2 = r25;
        r3 = r26;
        r4 = r27;
        r7 = r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0416, code lost:
        r27 = r4;
        r24 = r10;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0425, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0426, code lost:
        r27 = r4;
        r24 = r10;
        r22 = r15;
        r15 = "downloadTimeout";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:?, code lost:
        r0 = java.lang.Long.toString(r13);
        r2 = new java.lang.StringBuilder(java.lang.String.valueOf(r0).length() + 29);
        r2.append("Timeout exceeded. Limit: ");
        r2.append(r0);
        r2.append(" sec");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0459, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x045a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x045d, code lost:
        r2 = r2.toString();
        r1 = r24;
        r10 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0464, code lost:
        r0 = e;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0468, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x046b, code lost:
        r27 = r4;
        r22 = r15;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0472, code lost:
        r27 = r4;
        r24 = r10;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:?, code lost:
        r27.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0481, code lost:
        if (com.google.android.gms.internal.ads.zzakb.isLoggable(3) == false) goto L_0x04c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0483, code lost:
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:?, code lost:
        r1 = zzdbh.format((long) r11);
        r3 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 22) + java.lang.String.valueOf(r31).length());
        r3.append("Preloaded ");
        r3.append(r1);
        r3.append(" bytes from ");
        r3.append(r9);
        com.google.android.gms.internal.ads.zzakb.zzck(r3.toString());
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x04ba, code lost:
        r0 = e;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x04bc, code lost:
        r0 = e;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:?, code lost:
        r12.setReadable(true, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x04cb, code lost:
        if (r0.isFile() == false) goto L_0x04d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x04cd, code lost:
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:?, code lost:
        r0.setLastModified(java.lang.System.currentTimeMillis());
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x04d5, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:?, code lost:
        r0.createNewFile();
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:?, code lost:
        zza(r9, r12.getAbsolutePath(), r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x04e1, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
        zzdbg.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x04e6, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x04e8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x04ec, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x04ef, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x04f1, code lost:
        r15 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x04f4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x04f7, code lost:
        r27 = r4;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x04fb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x04fe, code lost:
        r27 = r4;
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0501, code lost:
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0507, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x0511, code lost:
        throw new java.io.IOException("Invalid protocol.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0512, code lost:
        r1 = r14;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x051c, code lost:
        throw new java.io.IOException("Too many redirects (20)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x051d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0520, code lost:
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0523, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x0526, code lost:
        r1 = r14;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0529, code lost:
        r2 = null;
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x052f, code lost:
        com.google.android.gms.ads.internal.zzbv.zzeo().zza(r0, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0541, code lost:
        r3 = new java.lang.StringBuilder(java.lang.String.valueOf(r31).length() + 26);
        r3.append("Preload aborted for URL \"");
        r3.append(r9);
        r3.append("\"");
        com.google.android.gms.internal.ads.zzakb.zzdj(r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0565, code lost:
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r31).length() + 25);
        r4.append("Preload failed for URL \"");
        r4.append(r9);
        r4.append("\"");
        com.google.android.gms.internal.ads.zzakb.zzc(r4.toString(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x05a4, code lost:
        r0 = "Could not delete partial cache file at ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x05a9, code lost:
        r0 = new java.lang.String("Could not delete partial cache file at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x0402, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012f, code lost:
        r15 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        com.google.android.gms.ads.internal.zzbv.zzew();
        r1 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzavc)).intValue();
        r2 = new java.net.URL(r9);
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014a, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014d, code lost:
        if (r3 > 20) goto L_0x0512;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x014f, code lost:
        r5 = r2.openConnection();
        r5.setConnectTimeout(r1);
        r5.setReadTimeout(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x015b, code lost:
        if ((r5 instanceof java.net.HttpURLConnection) == false) goto L_0x0507;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x015d, code lost:
        r5 = (java.net.HttpURLConnection) r5;
        r6 = new com.google.android.gms.internal.ads.zzamy();
        r6.zza(r5, (byte[]) null);
        r5.setInstanceFollowRedirects(false);
        r7 = r5.getResponseCode();
        r6.zza(r5, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0174, code lost:
        if ((r7 / 100) != 3) goto L_0x01ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r4 = r5.getHeaderField("Location");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x017c, code lost:
        if (r4 == null) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x017e, code lost:
        r6 = new java.net.URL(r2, r4);
        r2 = r6.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0187, code lost:
        if (r2 == null) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x018f, code lost:
        if (r2.equals(com.mopub.common.Constants.HTTP) != false) goto L_0x01b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0197, code lost:
        if (r2.equals(com.mopub.common.Constants.HTTPS) != false) goto L_0x01b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0199, code lost:
        r2 = java.lang.String.valueOf(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01a5, code lost:
        if (r2.length() == 0) goto L_0x01ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01a7, code lost:
        r1 = "Unsupported scheme: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01ac, code lost:
        r1 = new java.lang.String("Unsupported scheme: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01b5, code lost:
        throw new java.io.IOException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01b6, code lost:
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01c0, code lost:
        if (r4.length() == 0) goto L_0x01c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01c2, code lost:
        r2 = "Redirecting to ".concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01c7, code lost:
        r2 = new java.lang.String("Redirecting to ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01cd, code lost:
        com.google.android.gms.internal.ads.zzakb.zzck(r2);
        r5.disconnect();
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01dd, code lost:
        throw new java.io.IOException("Protocol is null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01e5, code lost:
        throw new java.io.IOException("Missing Location header in redirect");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01e6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01e9, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01ea, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:216:0x04d8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x04ba A[ExcHandler: RuntimeException (e java.lang.RuntimeException), PHI: r22 r24 r27 
      PHI: (r22v8 java.lang.String) = (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v16 java.lang.String), (r22v16 java.lang.String) binds: [B:214:0x04d5, B:215:?, B:212:0x04cd, B:213:?, B:202:0x0483, B:203:?, B:172:0x03c1, B:175:0x03e1] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r24v4 java.lang.String) = (r24v12 java.lang.String), (r24v13 java.lang.String), (r24v15 java.lang.String), (r24v17 java.lang.String), (r24v19 java.lang.String), (r24v21 java.lang.String), (r24v26 java.lang.String), (r24v29 java.lang.String) binds: [B:214:0x04d5, B:215:?, B:212:0x04cd, B:213:?, B:202:0x0483, B:203:?, B:172:0x03c1, B:175:0x03e1] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r27v10 java.io.FileOutputStream) = (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v15 java.io.FileOutputStream), (r27v18 java.io.FileOutputStream) binds: [B:214:0x04d5, B:215:?, B:212:0x04cd, B:213:?, B:202:0x0483, B:203:?, B:172:0x03c1, B:175:0x03e1] A[DONT_GENERATE, DONT_INLINE], Splitter:B:175:0x03e1] */
    /* JADX WARNING: Removed duplicated region for block: B:245:0x052f  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0541  */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x0565  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x05a4  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x05a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzdp(java.lang.String r31) {
        /*
            r30 = this;
            r8 = r30
            r9 = r31
            java.io.File r0 = r8.zzdbi
            r10 = 0
            r11 = 0
            if (r0 != 0) goto L_0x0010
            java.lang.String r0 = "noCacheDir"
        L_0x000c:
            r8.zza(r9, r10, r0, r10)
            return r11
        L_0x0010:
            java.io.File r0 = r8.zzdbi
            if (r0 != 0) goto L_0x0016
            r3 = 0
            goto L_0x0032
        L_0x0016:
            java.io.File[] r0 = r0.listFiles()
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x001d:
            if (r2 >= r1) goto L_0x0032
            r4 = r0[r2]
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = ".done"
            boolean r4 = r4.endsWith(r5)
            if (r4 != 0) goto L_0x002f
            int r3 = r3 + 1
        L_0x002f:
            int r2 = r2 + 1
            goto L_0x001d
        L_0x0032:
            com.google.android.gms.internal.ads.zzna<java.lang.Integer> r0 = com.google.android.gms.internal.ads.zznk.zzaux
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            if (r3 <= r0) goto L_0x0093
            java.io.File r0 = r8.zzdbi
            if (r0 != 0) goto L_0x004a
        L_0x0048:
            r0 = 0
            goto L_0x0088
        L_0x004a:
            r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.io.File[] r0 = r0.listFiles()
            int r3 = r0.length
            r5 = r10
            r4 = 0
        L_0x0056:
            if (r4 >= r3) goto L_0x0073
            r6 = r0[r4]
            java.lang.String r7 = r6.getName()
            java.lang.String r12 = ".done"
            boolean r7 = r7.endsWith(r12)
            if (r7 != 0) goto L_0x0070
            long r12 = r6.lastModified()
            int r7 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r7 >= 0) goto L_0x0070
            r5 = r6
            r1 = r12
        L_0x0070:
            int r4 = r4 + 1
            goto L_0x0056
        L_0x0073:
            if (r5 == 0) goto L_0x0048
            boolean r0 = r5.delete()
            java.io.File r1 = r8.zzc(r5)
            boolean r2 = r1.isFile()
            if (r2 == 0) goto L_0x0088
            boolean r1 = r1.delete()
            r0 = r0 & r1
        L_0x0088:
            if (r0 != 0) goto L_0x0010
            java.lang.String r0 = "Unable to expire stream cache"
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)
            java.lang.String r0 = "expireFailed"
            goto L_0x000c
        L_0x0093:
            com.google.android.gms.internal.ads.zzkb.zzif()
            java.lang.String r0 = com.google.android.gms.internal.ads.zzamu.zzde(r31)
            java.io.File r12 = new java.io.File
            java.io.File r1 = r8.zzdbi
            r12.<init>(r1, r0)
            java.io.File r0 = r8.zzc(r12)
            boolean r1 = r12.isFile()
            r13 = 1
            if (r1 == 0) goto L_0x00d9
            boolean r1 = r0.isFile()
            if (r1 == 0) goto L_0x00d9
            long r0 = r12.length()
            int r1 = (int) r0
            java.lang.String r0 = "Stream cache hit at "
            java.lang.String r2 = java.lang.String.valueOf(r31)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x00c8
            java.lang.String r0 = r0.concat(r2)
            goto L_0x00ce
        L_0x00c8:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r0)
            r0 = r2
        L_0x00ce:
            com.google.android.gms.internal.ads.zzakb.zzck(r0)
            java.lang.String r0 = r12.getAbsolutePath()
            r8.zza((java.lang.String) r9, (java.lang.String) r0, (int) r1)
            return r13
        L_0x00d9:
            java.io.File r1 = r8.zzdbi
            java.lang.String r1 = r1.getAbsolutePath()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = java.lang.String.valueOf(r31)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x00f3
            java.lang.String r1 = r1.concat(r2)
            r14 = r1
            goto L_0x00f9
        L_0x00f3:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            r14 = r2
        L_0x00f9:
            java.util.Set<java.lang.String> r1 = zzdbg
            monitor-enter(r1)
            java.util.Set<java.lang.String> r2 = zzdbg     // Catch:{ all -> 0x05c0 }
            boolean r2 = r2.contains(r14)     // Catch:{ all -> 0x05c0 }
            if (r2 == 0) goto L_0x0129
            java.lang.String r0 = "Stream cache already in progress at "
            java.lang.String r2 = java.lang.String.valueOf(r31)     // Catch:{ all -> 0x05c0 }
            int r3 = r2.length()     // Catch:{ all -> 0x05c0 }
            if (r3 == 0) goto L_0x0115
            java.lang.String r0 = r0.concat(r2)     // Catch:{ all -> 0x05c0 }
            goto L_0x011b
        L_0x0115:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x05c0 }
            r2.<init>(r0)     // Catch:{ all -> 0x05c0 }
            r0 = r2
        L_0x011b:
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)     // Catch:{ all -> 0x05c0 }
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ all -> 0x05c0 }
            java.lang.String r2 = "inProgress"
            r8.zza(r9, r0, r2, r10)     // Catch:{ all -> 0x05c0 }
            monitor-exit(r1)     // Catch:{ all -> 0x05c0 }
            return r11
        L_0x0129:
            java.util.Set<java.lang.String> r2 = zzdbg     // Catch:{ all -> 0x05c0 }
            r2.add(r14)     // Catch:{ all -> 0x05c0 }
            monitor-exit(r1)     // Catch:{ all -> 0x05c0 }
            java.lang.String r15 = "error"
            com.google.android.gms.ads.internal.zzbv.zzew()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            com.google.android.gms.internal.ads.zzna<java.lang.Integer> r1 = com.google.android.gms.internal.ads.zznk.zzavc     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            int r1 = r1.intValue()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r2.<init>(r9)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r3 = 0
        L_0x014a:
            int r3 = r3 + r13
            r4 = 20
            if (r3 > r4) goto L_0x0512
            java.net.URLConnection r5 = r2.openConnection()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r5.setConnectTimeout(r1)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r5.setReadTimeout(r1)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            boolean r6 = r5 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            if (r6 == 0) goto L_0x0507
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            com.google.android.gms.internal.ads.zzamy r6 = new com.google.android.gms.internal.ads.zzamy     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r6.<init>()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r6.zza((java.net.HttpURLConnection) r5, (byte[]) r10)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r5.setInstanceFollowRedirects(r11)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            int r7 = r5.getResponseCode()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r6.zza((java.net.HttpURLConnection) r5, (int) r7)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            int r7 = r7 / 100
            r6 = 3
            if (r7 != r6) goto L_0x01ed
            java.lang.String r4 = "Location"
            java.lang.String r4 = r5.getHeaderField(r4)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            if (r4 == 0) goto L_0x01de
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r6.<init>(r2, r4)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r2 = r6.getProtocol()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            if (r2 == 0) goto L_0x01d6
            java.lang.String r7 = "http"
            boolean r7 = r2.equals(r7)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            if (r7 != 0) goto L_0x01b6
            java.lang.String r7 = "https"
            boolean r7 = r2.equals(r7)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            if (r7 != 0) goto L_0x01b6
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r1 = "Unsupported scheme: "
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r3 = r2.length()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            if (r3 == 0) goto L_0x01ac
            java.lang.String r1 = r1.concat(r2)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            goto L_0x01b2
        L_0x01ac:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r1 = r2
        L_0x01b2:
            r0.<init>(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            throw r0     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
        L_0x01b6:
            java.lang.String r2 = "Redirecting to "
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r7 = r4.length()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            if (r7 == 0) goto L_0x01c7
            java.lang.String r2 = r2.concat(r4)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            goto L_0x01cd
        L_0x01c7:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r2 = r4
        L_0x01cd:
            com.google.android.gms.internal.ads.zzakb.zzck(r2)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r5.disconnect()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r2 = r6
            goto L_0x014a
        L_0x01d6:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r1 = "Protocol is null"
            r0.<init>(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            throw r0     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
        L_0x01de:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r1 = "Missing Location header in redirect"
            r0.<init>(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            throw r0     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
        L_0x01e6:
            r0 = move-exception
            goto L_0x01e9
        L_0x01e8:
            r0 = move-exception
        L_0x01e9:
            r2 = r10
        L_0x01ea:
            r1 = r14
            goto L_0x052b
        L_0x01ed:
            boolean r1 = r5 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            if (r1 == 0) goto L_0x0246
            r1 = r5
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r1 = r1.getResponseCode()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r2 = 400(0x190, float:5.6E-43)
            if (r1 < r2) goto L_0x0246
            java.lang.String r15 = "badUrl"
            java.lang.String r0 = "HTTP request failed. Code: "
            java.lang.String r2 = java.lang.Integer.toString(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r3 = r2.length()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            if (r3 == 0) goto L_0x0214
            java.lang.String r0 = r0.concat(r2)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r2 = r0
            goto L_0x0219
        L_0x0214:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
        L_0x0219:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            java.lang.String r3 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            int r3 = r3 + 32
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            java.lang.String r3 = "HTTP status code "
            r4.append(r3)     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            r4.append(r1)     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            java.lang.String r1 = " at "
            r4.append(r1)     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            r4.append(r9)     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
            throw r0     // Catch:{ IOException -> 0x0244, RuntimeException -> 0x0242 }
        L_0x0242:
            r0 = move-exception
            goto L_0x01ea
        L_0x0244:
            r0 = move-exception
            goto L_0x01ea
        L_0x0246:
            int r7 = r5.getContentLength()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            if (r7 >= 0) goto L_0x0275
            java.lang.String r0 = "Stream cache aborted, missing content-length header at "
            java.lang.String r1 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r2 = r1.length()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            if (r2 == 0) goto L_0x025d
            java.lang.String r0 = r0.concat(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            goto L_0x0263
        L_0x025d:
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r0 = r1
        L_0x0263:
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r1 = "contentLengthMissing"
            r8.zza(r9, r0, r1, r10)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.util.Set<java.lang.String> r0 = zzdbg     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r0.remove(r14)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            return r11
        L_0x0275:
            java.text.DecimalFormat r1 = zzdbh     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            long r2 = (long) r7     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.lang.String r1 = r1.format(r2)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            com.google.android.gms.internal.ads.zzna<java.lang.Integer> r2 = com.google.android.gms.internal.ads.zznk.zzauy     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            com.google.android.gms.internal.ads.zzni r3 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.lang.Object r2 = r3.zzd(r2)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            int r3 = r2.intValue()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            if (r7 <= r3) goto L_0x02e3
            java.lang.String r0 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r0 = r0.length()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r0 = r0 + 33
            java.lang.String r2 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r2 = r2.length()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r0 = r0 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r0 = "Content length "
            r2.append(r0)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r2.append(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r0 = " exceeds limit at "
            r2.append(r0)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r2.append(r9)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r0 = r2.toString()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r0 = "File too big for full file cache. Size: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            int r2 = r1.length()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            if (r2 == 0) goto L_0x02ce
            java.lang.String r0 = r0.concat(r1)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            goto L_0x02d4
        L_0x02ce:
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r0 = r1
        L_0x02d4:
            java.lang.String r1 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.lang.String r2 = "sizeExceeded"
            r8.zza(r9, r1, r2, r0)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            java.util.Set<java.lang.String> r0 = zzdbg     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            r0.remove(r14)     // Catch:{ IOException -> 0x01e8, RuntimeException -> 0x01e6 }
            return r11
        L_0x02e3:
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            int r2 = r2.length()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            int r2 = r2 + r4
            java.lang.String r4 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            int r4 = r4.length()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            int r2 = r2 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.lang.String r2 = "Caching "
            r4.append(r2)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r4.append(r1)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.lang.String r1 = " bytes from "
            r4.append(r1)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r4.append(r9)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            com.google.android.gms.internal.ads.zzakb.zzck(r1)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.io.InputStream r1 = r5.getInputStream()     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.nio.channels.ReadableByteChannel r5 = java.nio.channels.Channels.newChannel(r1)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            r4.<init>(r12)     // Catch:{ IOException -> 0x0525, RuntimeException -> 0x0523 }
            java.nio.channels.FileChannel r2 = r4.getChannel()     // Catch:{ IOException -> 0x04fd, RuntimeException -> 0x04fb }
            r1 = 1048576(0x100000, float:1.469368E-39)
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r1)     // Catch:{ IOException -> 0x04fd, RuntimeException -> 0x04fb }
            com.google.android.gms.common.util.Clock r16 = com.google.android.gms.ads.internal.zzbv.zzer()     // Catch:{ IOException -> 0x04fd, RuntimeException -> 0x04fb }
            long r17 = r16.currentTimeMillis()     // Catch:{ IOException -> 0x04fd, RuntimeException -> 0x04fb }
            com.google.android.gms.internal.ads.zzna<java.lang.Long> r6 = com.google.android.gms.internal.ads.zznk.zzavb     // Catch:{ IOException -> 0x04fd, RuntimeException -> 0x04fb }
            com.google.android.gms.internal.ads.zzni r10 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ IOException -> 0x04fd, RuntimeException -> 0x04fb }
            java.lang.Object r6 = r10.zzd(r6)     // Catch:{ IOException -> 0x04fd, RuntimeException -> 0x04fb }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ IOException -> 0x04fd, RuntimeException -> 0x04fb }
            r10 = r14
            long r13 = r6.longValue()     // Catch:{ IOException -> 0x04f6, RuntimeException -> 0x04f4 }
            com.google.android.gms.internal.ads.zzamj r6 = new com.google.android.gms.internal.ads.zzamj     // Catch:{ IOException -> 0x04f6, RuntimeException -> 0x04f4 }
            r6.<init>(r13)     // Catch:{ IOException -> 0x04f6, RuntimeException -> 0x04f4 }
            com.google.android.gms.internal.ads.zzna<java.lang.Long> r13 = com.google.android.gms.internal.ads.zznk.zzava     // Catch:{ IOException -> 0x04f6, RuntimeException -> 0x04f4 }
            com.google.android.gms.internal.ads.zzni r14 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ IOException -> 0x04f6, RuntimeException -> 0x04f4 }
            java.lang.Object r13 = r14.zzd(r13)     // Catch:{ IOException -> 0x04f6, RuntimeException -> 0x04f4 }
            java.lang.Long r13 = (java.lang.Long) r13     // Catch:{ IOException -> 0x04f6, RuntimeException -> 0x04f4 }
            long r13 = r13.longValue()     // Catch:{ IOException -> 0x04f6, RuntimeException -> 0x04f4 }
        L_0x0356:
            int r20 = r5.read(r1)     // Catch:{ IOException -> 0x04f6, RuntimeException -> 0x04f4 }
            if (r20 < 0) goto L_0x0472
            int r11 = r11 + r20
            if (r11 <= r3) goto L_0x0393
            java.lang.String r15 = "sizeExceeded"
            java.lang.String r0 = "File too big for full file cache. Size: "
            java.lang.String r1 = java.lang.Integer.toString(r11)     // Catch:{ IOException -> 0x038d, RuntimeException -> 0x038b }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x038d, RuntimeException -> 0x038b }
            int r2 = r1.length()     // Catch:{ IOException -> 0x038d, RuntimeException -> 0x038b }
            if (r2 == 0) goto L_0x0378
            java.lang.String r0 = r0.concat(r1)     // Catch:{ IOException -> 0x038d, RuntimeException -> 0x038b }
            r1 = r0
            goto L_0x037d
        L_0x0378:
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException -> 0x038d, RuntimeException -> 0x038b }
            r1.<init>(r0)     // Catch:{ IOException -> 0x038d, RuntimeException -> 0x038b }
        L_0x037d:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0387, RuntimeException -> 0x0385 }
            java.lang.String r2 = "stream cache file size limit exceeded"
            r0.<init>(r2)     // Catch:{ IOException -> 0x0387, RuntimeException -> 0x0385 }
            throw r0     // Catch:{ IOException -> 0x0387, RuntimeException -> 0x0385 }
        L_0x0385:
            r0 = move-exception
            goto L_0x0388
        L_0x0387:
            r0 = move-exception
        L_0x0388:
            r2 = r1
            r1 = r10
            goto L_0x0390
        L_0x038b:
            r0 = move-exception
            goto L_0x038e
        L_0x038d:
            r0 = move-exception
        L_0x038e:
            r1 = r10
            r2 = 0
        L_0x0390:
            r10 = r4
            goto L_0x052b
        L_0x0393:
            r1.flip()     // Catch:{ IOException -> 0x046a, RuntimeException -> 0x0468 }
        L_0x0396:
            int r20 = r2.write(r1)     // Catch:{ IOException -> 0x046a, RuntimeException -> 0x0468 }
            if (r20 > 0) goto L_0x0396
            r1.clear()     // Catch:{ IOException -> 0x046a, RuntimeException -> 0x0468 }
            long r20 = r16.currentTimeMillis()     // Catch:{ IOException -> 0x046a, RuntimeException -> 0x0468 }
            long r20 = r20 - r17
            r22 = 1000(0x3e8, double:4.94E-321)
            long r22 = r22 * r13
            int r24 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
            if (r24 > 0) goto L_0x0426
            r20 = r1
            boolean r1 = r8.zzdbj     // Catch:{ IOException -> 0x046a, RuntimeException -> 0x0468 }
            if (r1 != 0) goto L_0x0416
            boolean r1 = r6.tryAcquire()     // Catch:{ IOException -> 0x046a, RuntimeException -> 0x0468 }
            if (r1 == 0) goto L_0x03f0
            java.lang.String r21 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x046a, RuntimeException -> 0x0468 }
            android.os.Handler r1 = com.google.android.gms.internal.ads.zzamu.zzsy     // Catch:{ IOException -> 0x046a, RuntimeException -> 0x0468 }
            r22 = r15
            com.google.android.gms.internal.ads.zzaqi r15 = new com.google.android.gms.internal.ads.zzaqi     // Catch:{ IOException -> 0x03ea, RuntimeException -> 0x03e8 }
            r23 = 0
            r24 = r10
            r10 = r1
            r1 = r15
            r25 = r2
            r2 = r30
            r26 = r3
            r3 = r31
            r27 = r4
            r4 = r21
            r21 = r5
            r5 = r11
            r19 = r6
            r28 = 3
            r6 = r7
            r29 = r7
            r7 = r23
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            r10.post(r15)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            goto L_0x0402
        L_0x03e8:
            r0 = move-exception
            goto L_0x03eb
        L_0x03ea:
            r0 = move-exception
        L_0x03eb:
            r27 = r4
            r1 = r10
            goto L_0x04f1
        L_0x03f0:
            r25 = r2
            r26 = r3
            r27 = r4
            r21 = r5
            r19 = r6
            r29 = r7
            r24 = r10
            r22 = r15
            r28 = 3
        L_0x0402:
            r6 = r19
            r1 = r20
            r5 = r21
            r15 = r22
            r10 = r24
            r2 = r25
            r3 = r26
            r4 = r27
            r7 = r29
            goto L_0x0356
        L_0x0416:
            r27 = r4
            r24 = r10
            r22 = r15
            java.lang.String r15 = "externalAbort"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            java.lang.String r1 = "abort requested"
            r0.<init>(r1)     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            throw r0     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
        L_0x0426:
            r27 = r4
            r24 = r10
            r22 = r15
            java.lang.String r15 = "downloadTimeout"
            java.lang.String r0 = java.lang.Long.toString(r13)     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            int r1 = r1.length()     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            int r1 = r1 + 29
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            java.lang.String r1 = "Timeout exceeded. Limit: "
            r2.append(r1)     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            r2.append(r0)     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            java.lang.String r0 = " sec"
            r2.append(r0)     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            java.lang.String r10 = r2.toString()     // Catch:{ IOException -> 0x0466, RuntimeException -> 0x0464 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x045c, RuntimeException -> 0x045a }
            java.lang.String r1 = "stream cache time limit exceeded"
            r0.<init>(r1)     // Catch:{ IOException -> 0x045c, RuntimeException -> 0x045a }
            throw r0     // Catch:{ IOException -> 0x045c, RuntimeException -> 0x045a }
        L_0x045a:
            r0 = move-exception
            goto L_0x045d
        L_0x045c:
            r0 = move-exception
        L_0x045d:
            r2 = r10
            r1 = r24
            r10 = r27
            goto L_0x052b
        L_0x0464:
            r0 = move-exception
            goto L_0x04bf
        L_0x0466:
            r0 = move-exception
            goto L_0x04bf
        L_0x0468:
            r0 = move-exception
            goto L_0x046b
        L_0x046a:
            r0 = move-exception
        L_0x046b:
            r27 = r4
            r22 = r15
            r1 = r10
            goto L_0x0503
        L_0x0472:
            r27 = r4
            r24 = r10
            r22 = r15
            r28 = 3
            r27.close()     // Catch:{ IOException -> 0x04ee, RuntimeException -> 0x04ec }
            boolean r1 = com.google.android.gms.internal.ads.zzakb.isLoggable(r28)     // Catch:{ IOException -> 0x04ee, RuntimeException -> 0x04ec }
            if (r1 == 0) goto L_0x04c2
            java.text.DecimalFormat r1 = zzdbh     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            long r2 = (long) r11     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            java.lang.String r1 = r1.format(r2)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            int r2 = r2.length()     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            int r2 = r2 + 22
            java.lang.String r3 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            int r3 = r3.length()     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            r3.<init>(r2)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            java.lang.String r2 = "Preloaded "
            r3.append(r2)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            r3.append(r1)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            java.lang.String r1 = " bytes from "
            r3.append(r1)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            r3.append(r9)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            com.google.android.gms.internal.ads.zzakb.zzck(r1)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            goto L_0x04c2
        L_0x04ba:
            r0 = move-exception
            goto L_0x04bd
        L_0x04bc:
            r0 = move-exception
        L_0x04bd:
            r15 = r22
        L_0x04bf:
            r1 = r24
            goto L_0x0503
        L_0x04c2:
            r1 = 1
            r2 = 0
            r12.setReadable(r1, r2)     // Catch:{ IOException -> 0x04ee, RuntimeException -> 0x04ec }
            boolean r1 = r0.isFile()     // Catch:{ IOException -> 0x04ee, RuntimeException -> 0x04ec }
            if (r1 == 0) goto L_0x04d5
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            r0.setLastModified(r1)     // Catch:{ IOException -> 0x04bc, RuntimeException -> 0x04ba }
            goto L_0x04d8
        L_0x04d5:
            r0.createNewFile()     // Catch:{ IOException -> 0x04d8, RuntimeException -> 0x04ba }
        L_0x04d8:
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x04ee, RuntimeException -> 0x04ec }
            r8.zza((java.lang.String) r9, (java.lang.String) r0, (int) r11)     // Catch:{ IOException -> 0x04ee, RuntimeException -> 0x04ec }
            java.util.Set<java.lang.String> r0 = zzdbg     // Catch:{ IOException -> 0x04ee, RuntimeException -> 0x04ec }
            r1 = r24
            r0.remove(r1)     // Catch:{ IOException -> 0x04ea, RuntimeException -> 0x04e8 }
            r0 = 1
            return r0
        L_0x04e8:
            r0 = move-exception
            goto L_0x04f1
        L_0x04ea:
            r0 = move-exception
            goto L_0x04f1
        L_0x04ec:
            r0 = move-exception
            goto L_0x04ef
        L_0x04ee:
            r0 = move-exception
        L_0x04ef:
            r1 = r24
        L_0x04f1:
            r15 = r22
            goto L_0x0503
        L_0x04f4:
            r0 = move-exception
            goto L_0x04f7
        L_0x04f6:
            r0 = move-exception
        L_0x04f7:
            r27 = r4
            r1 = r10
            goto L_0x0501
        L_0x04fb:
            r0 = move-exception
            goto L_0x04fe
        L_0x04fd:
            r0 = move-exception
        L_0x04fe:
            r27 = r4
            r1 = r14
        L_0x0501:
            r22 = r15
        L_0x0503:
            r10 = r27
            r2 = 0
            goto L_0x052b
        L_0x0507:
            r1 = r14
            r22 = r15
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x051f, RuntimeException -> 0x051d }
            java.lang.String r2 = "Invalid protocol."
            r0.<init>(r2)     // Catch:{ IOException -> 0x051f, RuntimeException -> 0x051d }
            throw r0     // Catch:{ IOException -> 0x051f, RuntimeException -> 0x051d }
        L_0x0512:
            r1 = r14
            r22 = r15
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x051f, RuntimeException -> 0x051d }
            java.lang.String r2 = "Too many redirects (20)"
            r0.<init>(r2)     // Catch:{ IOException -> 0x051f, RuntimeException -> 0x051d }
            throw r0     // Catch:{ IOException -> 0x051f, RuntimeException -> 0x051d }
        L_0x051d:
            r0 = move-exception
            goto L_0x0520
        L_0x051f:
            r0 = move-exception
        L_0x0520:
            r15 = r22
            goto L_0x0529
        L_0x0523:
            r0 = move-exception
            goto L_0x0526
        L_0x0525:
            r0 = move-exception
        L_0x0526:
            r1 = r14
            r22 = r15
        L_0x0529:
            r2 = 0
            r10 = 0
        L_0x052b:
            boolean r3 = r0 instanceof java.lang.RuntimeException
            if (r3 == 0) goto L_0x0538
            com.google.android.gms.internal.ads.zzajm r3 = com.google.android.gms.ads.internal.zzbv.zzeo()
            java.lang.String r4 = "VideoStreamFullFileCache.preload"
            r3.zza(r0, r4)
        L_0x0538:
            r10.close()     // Catch:{ IOException | NullPointerException -> 0x053c }
            goto L_0x053d
        L_0x053c:
        L_0x053d:
            boolean r3 = r8.zzdbj
            if (r3 == 0) goto L_0x0565
            java.lang.String r0 = java.lang.String.valueOf(r31)
            int r0 = r0.length()
            int r0 = r0 + 26
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.String r0 = "Preload aborted for URL \""
            r3.append(r0)
            r3.append(r9)
            java.lang.String r0 = "\""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.google.android.gms.internal.ads.zzakb.zzdj(r0)
            goto L_0x0588
        L_0x0565:
            java.lang.String r3 = java.lang.String.valueOf(r31)
            int r3 = r3.length()
            int r3 = r3 + 25
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Preload failed for URL \""
            r4.append(r3)
            r4.append(r9)
            java.lang.String r3 = "\""
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.google.android.gms.internal.ads.zzakb.zzc(r3, r0)
        L_0x0588:
            boolean r0 = r12.exists()
            if (r0 == 0) goto L_0x05b2
            boolean r0 = r12.delete()
            if (r0 != 0) goto L_0x05b2
            java.lang.String r0 = "Could not delete partial cache file at "
            java.lang.String r3 = r12.getAbsolutePath()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x05a9
            java.lang.String r0 = r0.concat(r3)
            goto L_0x05af
        L_0x05a9:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r0)
            r0 = r3
        L_0x05af:
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)
        L_0x05b2:
            java.lang.String r0 = r12.getAbsolutePath()
            r8.zza(r9, r0, r15, r2)
            java.util.Set<java.lang.String> r0 = zzdbg
            r0.remove(r1)
            r1 = 0
            return r1
        L_0x05c0:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x05c0 }
            goto L_0x05c4
        L_0x05c3:
            throw r0
        L_0x05c4:
            goto L_0x05c3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaqn.zzdp(java.lang.String):boolean");
    }
}
