package com.google.android.gms.internal.ads;

import java.util.List;

final class zzaca implements zzank<List<zzon>, zzoj> {
    private final /* synthetic */ String zzcan;
    private final /* synthetic */ Integer zzcao;
    private final /* synthetic */ Integer zzcap;
    private final /* synthetic */ int zzcaq;
    private final /* synthetic */ int zzcar;
    private final /* synthetic */ int zzcas;
    private final /* synthetic */ int zzcat;
    private final /* synthetic */ boolean zzcau;

    zzaca(zzabv zzabv, String str, Integer num, Integer num2, int i, int i2, int i3, int i4, boolean z) {
        this.zzcan = str;
        this.zzcao = num;
        this.zzcap = num2;
        this.zzcaq = i;
        this.zzcar = i2;
        this.zzcas = i3;
        this.zzcat = i4;
        this.zzcau = z;
    }

    public final /* synthetic */ Object apply(Object obj) {
        List list = (List) obj;
        Integer num = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        String str = this.zzcan;
        Integer num2 = this.zzcao;
        Integer num3 = this.zzcap;
        int i = this.zzcaq;
        if (i > 0) {
            num = Integer.valueOf(i);
        }
        return new zzoj(str, list, num2, num3, num, this.zzcar + this.zzcas, this.zzcat, this.zzcau);
    }
}
