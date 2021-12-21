package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.bh;
import com.google.android.play.core.internal.bz;
import com.google.android.play.core.internal.ca;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

final class cx {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f1084a = new ag("PatchSliceTaskHandler");
    private final au b;
    private final ca<t> c;

    cx(au auVar, ca<t> caVar) {
        this.b = auVar;
        this.c = caVar;
    }

    public final void a(cw cwVar) {
        InputStream inputStream;
        File f = this.b.f(cwVar.k, cwVar.f1083a, cwVar.b);
        au auVar = this.b;
        String str = cwVar.k;
        int i = cwVar.f1083a;
        long j = cwVar.b;
        File file = new File(auVar.g(str, i, j), cwVar.f);
        try {
            inputStream = cwVar.h;
            if (cwVar.e == 2) {
                inputStream = new GZIPInputStream(inputStream, 8192);
            }
            aw awVar = new aw(f, file);
            File h = this.b.h(cwVar.k, cwVar.c, cwVar.d, cwVar.f);
            if (!h.exists()) {
                h.mkdirs();
            }
            cz czVar = new cz(this.b, cwVar.k, cwVar.c, cwVar.d, cwVar.f);
            bh.l(awVar, inputStream, new bn(h, czVar), cwVar.g);
            czVar.d(0);
            inputStream.close();
            f1084a.d("Patching and extraction finished for slice %s of pack %s.", cwVar.f, cwVar.k);
            this.c.a().e(cwVar.j, cwVar.k, cwVar.f, 0);
            try {
                cwVar.h.close();
                return;
            } catch (IOException unused) {
                f1084a.e("Could not close file for slice %s of pack %s.", cwVar.f, cwVar.k);
                return;
            }
        } catch (IOException e) {
            f1084a.b("IOException during patching %s.", e.getMessage());
            throw new bk(String.format("Error patching slice %s of pack %s.", new Object[]{cwVar.f, cwVar.k}), e, cwVar.j);
        } catch (Throwable th) {
            bz.a(th, th);
        }
        throw th;
    }
}
