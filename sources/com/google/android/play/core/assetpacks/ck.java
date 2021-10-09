package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.util.Base64;
import com.google.android.play.core.internal.bz;
import com.google.android.play.core.splitcompat.p;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public final class ck {

    /* renamed from: a  reason: collision with root package name */
    private static a f1071a;

    static String a(List<File> list) throws NoSuchAlgorithmException, IOException {
        int read;
        MessageDigest instance = MessageDigest.getInstance("SHA256");
        byte[] bArr = new byte[8192];
        for (File fileInputStream : list) {
            FileInputStream fileInputStream2 = new FileInputStream(fileInputStream);
            do {
                try {
                    read = fileInputStream2.read(bArr);
                    if (read > 0) {
                        instance.update(bArr, 0, read);
                    }
                } catch (Throwable th) {
                    bz.a(th, th);
                }
            } while (read != -1);
            fileInputStream2.close();
        }
        return Base64.encodeToString(instance.digest(), 11);
        throw th;
    }

    static long d(byte[] bArr, int i) {
        return ((long) ((e(bArr, i + 2) << 16) | e(bArr, i))) & 4294967295L;
    }

    static int e(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    public static boolean f(int i) {
        return i == 1 || i == 7 || i == 2 || i == 3;
    }

    public static boolean g(int i) {
        return i == 5 || i == 6 || i == 4;
    }

    public static boolean h(int i) {
        return i == 2 || i == 7 || i == 3;
    }

    static boolean i(int i, int i2) {
        if (i == 5) {
            if (i2 != 5) {
                return true;
            }
            i = 5;
        }
        if (i == 6 && i2 != 6 && i2 != 5) {
            return true;
        }
        if (i == 4 && i2 != 4) {
            return true;
        }
        if (i == 3 && (i2 == 2 || i2 == 7 || i2 == 1 || i2 == 8)) {
            return true;
        }
        if (i == 2) {
            return i2 == 1 || i2 == 8;
        }
        return false;
    }

    static synchronized a j(Context context) {
        a aVar;
        synchronized (ck.class) {
            if (f1071a == null) {
                bg bgVar = new bg((byte[]) null);
                bgVar.b(new l(p.c(context)));
                f1071a = bgVar.a();
            }
            aVar = f1071a;
        }
        return aVar;
    }
}
