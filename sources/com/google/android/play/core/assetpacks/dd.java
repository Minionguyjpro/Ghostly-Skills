package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.ag;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

final class dd {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f1090a = new ag("VerifySliceTaskHandler");
    private final au b;

    dd(au auVar) {
        this.b = auVar;
    }

    private final void b(dc dcVar, File file) {
        try {
            File o = this.b.o(dcVar.k, dcVar.f1089a, dcVar.b, dcVar.c);
            if (o.exists()) {
                try {
                    if (ck.a(db.a(file, o)).equals(dcVar.d)) {
                        f1090a.d("Verification of slice %s of pack %s successful.", dcVar.c, dcVar.k);
                        return;
                    }
                    throw new bk(String.format("Verification failed for slice %s.", new Object[]{dcVar.c}), dcVar.j);
                } catch (NoSuchAlgorithmException e) {
                    throw new bk("SHA256 algorithm not supported.", e, dcVar.j);
                } catch (IOException e2) {
                    throw new bk(String.format("Could not digest file during verification for slice %s.", new Object[]{dcVar.c}), e2, dcVar.j);
                }
            } else {
                throw new bk(String.format("Cannot find metadata files for slice %s.", new Object[]{dcVar.c}), dcVar.j);
            }
        } catch (IOException e3) {
            throw new bk(String.format("Could not reconstruct slice archive during verification for slice %s.", new Object[]{dcVar.c}), e3, dcVar.j);
        }
    }

    public final void a(dc dcVar) {
        File h = this.b.h(dcVar.k, dcVar.f1089a, dcVar.b, dcVar.c);
        if (h.exists()) {
            b(dcVar, h);
            File i = this.b.i(dcVar.k, dcVar.f1089a, dcVar.b, dcVar.c);
            if (!i.exists()) {
                i.mkdirs();
            }
            if (!h.renameTo(i)) {
                throw new bk(String.format("Failed to move slice %s after verification.", new Object[]{dcVar.c}), dcVar.j);
            }
            return;
        }
        throw new bk(String.format("Cannot find unverified files for slice %s.", new Object[]{dcVar.c}), dcVar.j);
    }
}
