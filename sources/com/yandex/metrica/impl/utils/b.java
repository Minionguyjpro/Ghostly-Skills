package com.yandex.metrica.impl.utils;

import android.util.Base64;
import com.yandex.metrica.impl.bk;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private final String f952a;
    private final String b;

    public b() {
        this("AES/CBC/PKCS7Padding", "RSA/ECB/PKCS1Padding");
    }

    b(String str, String str2) {
        this.f952a = str;
        this.b = str2;
    }

    public byte[] a(byte[] bArr) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[16];
            secureRandom.nextBytes(bArr3);
            secureRandom.nextBytes(bArr2);
            return a(bArr, bArr3, bArr2, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDhmH/m2qrRjxDHP794CeaZpENQNYydf8pqyXJilo6XxK+n+pvo27VxWfB3Z1yHrtKow+eZXKLQzrQ8wZMfRgADrYCQJ20y2hGZEUCN1tGSM+xqVKMeCtVi3NvQa54Cx7mT5ECVsH5DKEs/aeScDHP56FzcgEbtOSwyRZ8dsEM0wwIDAQAB", 0))));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, PublicKey publicKey) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream(bArr2.length + bArr3.length);
            byteArrayOutputStream3.write(bArr2);
            byteArrayOutputStream3.write(bArr3);
            byte[] byteArray = byteArrayOutputStream3.toByteArray();
            byteArrayOutputStream3.close();
            Cipher instance = Cipher.getInstance(this.b);
            instance.init(1, publicKey);
            byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            try {
                byteArrayOutputStream.write(instance.doFinal(byteArray));
                byteArrayOutputStream.write(new a(this.f952a, bArr2, bArr3).a(bArr));
                byte[] byteArray2 = byteArrayOutputStream.toByteArray();
                bk.a((Closeable) byteArrayOutputStream);
                return byteArray2;
            } catch (Exception unused) {
                bk.a((Closeable) byteArrayOutputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream2 = byteArrayOutputStream;
                bk.a((Closeable) byteArrayOutputStream2);
                throw th;
            }
        } catch (Exception unused2) {
            byteArrayOutputStream = null;
            bk.a((Closeable) byteArrayOutputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            bk.a((Closeable) byteArrayOutputStream2);
            throw th;
        }
    }
}
