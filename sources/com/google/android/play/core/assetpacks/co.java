package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.ag;
import java.io.File;
import java.io.IOException;

final class co {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f1075a = new ag("MergeSliceTaskHandler");
    private final au b;

    co(au auVar) {
        this.b = auVar;
    }

    private static void b(File file, File file2) {
        if (file.isDirectory()) {
            file2.mkdirs();
            for (File file3 : file.listFiles()) {
                b(file3, new File(file2, file3.getName()));
            }
            if (!file.delete()) {
                String valueOf = String.valueOf(file);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 28);
                sb.append("Unable to delete directory: ");
                sb.append(valueOf);
                throw new bk(sb.toString());
            }
        } else if (file2.exists()) {
            String valueOf2 = String.valueOf(file2);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 51);
            sb2.append("File clashing with existing file from other slice: ");
            sb2.append(valueOf2);
            throw new bk(sb2.toString());
        } else if (!file.renameTo(file2)) {
            String valueOf3 = String.valueOf(file);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 21);
            sb3.append("Unable to move file: ");
            sb3.append(valueOf3);
            throw new bk(sb3.toString());
        }
    }

    public final void a(cn cnVar) {
        File i = this.b.i(cnVar.k, cnVar.f1074a, cnVar.b, cnVar.c);
        if (i.exists()) {
            File j = this.b.j(cnVar.k, cnVar.f1074a, cnVar.b);
            if (!j.exists()) {
                j.mkdirs();
            }
            b(i, j);
            try {
                this.b.l(cnVar.k, cnVar.f1074a, cnVar.b, this.b.k(cnVar.k, cnVar.f1074a, cnVar.b) + 1);
            } catch (IOException e) {
                f1075a.b("Writing merge checkpoint failed with %s.", e.getMessage());
                throw new bk("Writing merge checkpoint failed.", e, cnVar.j);
            }
        } else {
            throw new bk(String.format("Cannot find verified files for slice %s.", new Object[]{cnVar.c}), cnVar.j);
        }
    }
}
