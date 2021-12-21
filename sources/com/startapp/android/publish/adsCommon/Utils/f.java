package com.startapp.android.publish.adsCommon.Utils;

import android.content.Context;
import android.os.Environment;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.common.a.g;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* compiled from: StartAppSDK */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f178a = true;

    public static void a(Context context, boolean z) {
        boolean z2 = true;
        if (z) {
            f178a = true;
            k.b(context, "copyDrawables", (Boolean) true);
        }
        if (f178a) {
            boolean booleanValue = k.a(context, "copyDrawables", (Boolean) true).booleanValue();
            f178a = booleanValue;
            if (booleanValue) {
                try {
                    String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
                    String a2 = a(context);
                    if (!a(context, str, "", "drawable-hdpi.zip") && !a(context, str, "assets/", "drawable-hdpi.zip") && !a(context, a2, "", "drawable-hdpi.zip")) {
                        if (!a(context, a2, "assets/", "drawable-hdpi.zip")) {
                            z2 = false;
                        }
                    }
                    if (!z2) {
                        g.a("ResourceHandler", 5, "Error initializing resources");
                    }
                } catch (Exception e) {
                    g.a("ResourceHandler", 6, "Error initializing resources", e);
                }
            }
        }
    }

    private static String a(Context context) {
        String str = Environment.getExternalStorageDirectory() + "/Android/obb/" + context.getPackageName() + "/";
        String str2 = "main.1." + context.getPackageName() + ".obb";
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            final Pattern compile = Pattern.compile("main[.][1-9][0-9]*[.]" + context.getPackageName() + "[.]obb");
            File[] listFiles = file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return compile.matcher(file.getName()).matches();
                }
            });
            if (listFiles.length > 0) {
                int i = 0;
                int i2 = 0;
                for (int i3 = 0; i3 < listFiles.length; i3++) {
                    try {
                        int parseInt = Integer.parseInt(listFiles[i3].getName().split("[.]")[1]);
                        if (parseInt > i2) {
                            i = i3;
                            i2 = parseInt;
                        }
                    } catch (Exception unused) {
                    }
                }
                str2 = listFiles[i].getName();
            }
        }
        return str + str2;
    }

    private static boolean a(Context context, String str, String str2, String str3) {
        g.a("ResourceHandler", 3, "Trying to copy resources from " + str + " in /" + str2);
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str3);
        String sb2 = sb.toString();
        if (!a(str, sb2, context.getFilesDir().getPath() + "/" + str3)) {
            g.a("ResourceHandler", 3, "Failed copying resources from " + str + " in /" + str2);
            return false;
        }
        a(context, context.getFilesDir().getPath() + "/" + str3);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str2);
        sb3.append("drawable.zip");
        String sb4 = sb3.toString();
        a(str, sb4, context.getFilesDir().getPath() + "/" + "drawable.zip");
        a(context, context.getFilesDir().getPath() + "/" + "drawable.zip");
        k.b(context, "copyDrawables", (Boolean) false);
        g.a("ResourceHandler", 3, "Copy from " + str + " in /" + str2 + " succeeded");
        return true;
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.util.zip.ZipFile] */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0051, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0052, code lost:
        r1 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0058, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0059, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x006c */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0058 A[ExcHandler: all (th java.lang.Throwable), PHI: r2 
      PHI: (r2v2 java.util.zip.ZipFile) = (r2v0 java.util.zip.ZipFile), (r2v0 java.util.zip.ZipFile), (r2v3 java.util.zip.ZipFile), (r2v3 java.util.zip.ZipFile), (r2v3 java.util.zip.ZipFile), (r2v3 java.util.zip.ZipFile), (r2v3 java.util.zip.ZipFile), (r2v3 java.util.zip.ZipFile) binds: [B:48:0x0061, B:49:?, B:3:0x0007, B:16:0x0030, B:17:?, B:19:0x0037, B:25:0x0046, B:26:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:3:0x0007] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static boolean a(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            r0 = 0
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x005f, all -> 0x005d }
            r2.<init>(r5)     // Catch:{ IOException -> 0x005f, all -> 0x005d }
            java.util.Enumeration r5 = r2.entries()     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
        L_0x000b:
            boolean r3 = r5.hasMoreElements()     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            if (r3 == 0) goto L_0x0029
            java.lang.Object r3 = r5.nextElement()     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            java.util.zip.ZipEntry r3 = (java.util.zip.ZipEntry) r3     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            boolean r4 = r3.isDirectory()     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            if (r4 == 0) goto L_0x001e
            goto L_0x000b
        L_0x001e:
            java.lang.String r4 = r3.getName()     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            boolean r4 = r4.equals(r6)     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            if (r4 == 0) goto L_0x000b
            goto L_0x002a
        L_0x0029:
            r3 = r1
        L_0x002a:
            if (r3 == 0) goto L_0x0054
            java.io.InputStream r5 = r2.getInputStream(r3)     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0051, all -> 0x0058 }
            r6.<init>(r7)     // Catch:{ IOException -> 0x0051, all -> 0x0058 }
            r7 = 256(0x100, float:3.59E-43)
            byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x0052, all -> 0x0058 }
        L_0x0039:
            int r1 = r5.read(r7)     // Catch:{ IOException -> 0x0052, all -> 0x0058 }
            if (r1 <= 0) goto L_0x0043
            r6.write(r7, r0, r1)     // Catch:{ IOException -> 0x0052, all -> 0x0058 }
            goto L_0x0039
        L_0x0043:
            r6.flush()     // Catch:{ IOException -> 0x0052, all -> 0x0058 }
            r5.close()     // Catch:{ IOException -> 0x004c, all -> 0x0058 }
            r6.close()     // Catch:{ IOException -> 0x004c, all -> 0x0058 }
        L_0x004c:
            r5 = 1
            r2.close()     // Catch:{ Exception -> 0x0050 }
        L_0x0050:
            return r5
        L_0x0051:
            r6 = r1
        L_0x0052:
            r1 = r5
            goto L_0x0061
        L_0x0054:
            r2.close()     // Catch:{ Exception -> 0x0057 }
        L_0x0057:
            return r0
        L_0x0058:
            r5 = move-exception
            r1 = r2
            goto L_0x0068
        L_0x005b:
            r6 = r1
            goto L_0x0061
        L_0x005d:
            r5 = move-exception
            goto L_0x0068
        L_0x005f:
            r6 = r1
            r2 = r6
        L_0x0061:
            r1.close()     // Catch:{ Exception -> 0x006c, all -> 0x0058 }
            r6.close()     // Catch:{ Exception -> 0x006c, all -> 0x0058 }
            goto L_0x006c
        L_0x0068:
            r1.close()     // Catch:{ Exception -> 0x006b }
        L_0x006b:
            throw r5
        L_0x006c:
            r2.close()     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.Utils.f.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:14|15|27|28|29|30|31) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0058 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x005b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0066 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void a(android.content.Context r7, java.lang.String r8) {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r0]
            r2 = 0
            java.util.zip.ZipInputStream r3 = new java.util.zip.ZipInputStream     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x006a, all -> 0x0061 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x006a, all -> 0x0061 }
            r4.<init>(r8)     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x006a, all -> 0x0061 }
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x006a, all -> 0x0061 }
            java.util.zip.ZipEntry r8 = r3.getNextEntry()     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
        L_0x0013:
            if (r8 == 0) goto L_0x0058
            java.lang.String r8 = r8.getName()     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            r5.<init>()     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            java.io.File r6 = r7.getFilesDir()     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            java.lang.String r6 = r6.getPath()     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            r5.append(r6)     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            java.lang.String r6 = "/"
            r5.append(r6)     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            r5.append(r8)     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            java.lang.String r8 = r5.toString()     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
            r4.<init>(r8)     // Catch:{ FileNotFoundException | IOException -> 0x0058, all -> 0x005f }
        L_0x003a:
            r8 = 0
            int r2 = r3.read(r1, r8, r0)     // Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0055, all -> 0x0052 }
            r5 = -1
            if (r2 <= r5) goto L_0x0046
            r4.write(r1, r8, r2)     // Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0055, all -> 0x0052 }
            goto L_0x003a
        L_0x0046:
            r4.close()     // Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0055, all -> 0x0052 }
            r3.closeEntry()     // Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0055, all -> 0x0052 }
            java.util.zip.ZipEntry r8 = r3.getNextEntry()     // Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0055, all -> 0x0052 }
            r2 = r4
            goto L_0x0013
        L_0x0052:
            r7 = move-exception
            r2 = r4
            goto L_0x0063
        L_0x0055:
            r2 = r4
            goto L_0x0058
        L_0x0057:
            r2 = r4
        L_0x0058:
            r2.close()     // Catch:{ IOException -> 0x005b }
        L_0x005b:
            r3.close()     // Catch:{ IOException -> 0x006e }
            goto L_0x006e
        L_0x005f:
            r7 = move-exception
            goto L_0x0063
        L_0x0061:
            r7 = move-exception
            r3 = r2
        L_0x0063:
            r2.close()     // Catch:{ IOException -> 0x0066 }
        L_0x0066:
            r3.close()     // Catch:{ IOException -> 0x0069 }
        L_0x0069:
            throw r7
        L_0x006a:
            r3 = r2
            goto L_0x0058
        L_0x006c:
            r3 = r2
            goto L_0x0058
        L_0x006e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.Utils.f.a(android.content.Context, java.lang.String):void");
    }
}
