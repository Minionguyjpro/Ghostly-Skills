package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzjk {
    private Bundle mExtras;
    private long zzaqm;
    private int zzaqn;
    private List<String> zzaqo;
    private boolean zzaqp;
    private int zzaqq;
    private String zzaqr;
    private zzmq zzaqs;
    private String zzaqt;
    private Bundle zzaqu;
    private Bundle zzaqv;
    private List<String> zzaqw;
    private String zzaqx;
    private String zzaqy;
    private boolean zzaqz;
    private Location zzhp;
    private boolean zzvm;

    public zzjk() {
        this.zzaqm = -1;
        this.mExtras = new Bundle();
        this.zzaqn = -1;
        this.zzaqo = new ArrayList();
        this.zzaqp = false;
        this.zzaqq = -1;
        this.zzvm = false;
        this.zzaqr = null;
        this.zzaqs = null;
        this.zzhp = null;
        this.zzaqt = null;
        this.zzaqu = new Bundle();
        this.zzaqv = new Bundle();
        this.zzaqw = new ArrayList();
        this.zzaqx = null;
        this.zzaqy = null;
        this.zzaqz = false;
    }

    public zzjk(zzjj zzjj) {
        this.zzaqm = zzjj.zzapw;
        this.mExtras = zzjj.extras;
        this.zzaqn = zzjj.zzapx;
        this.zzaqo = zzjj.zzapy;
        this.zzaqp = zzjj.zzapz;
        this.zzaqq = zzjj.zzaqa;
        this.zzvm = zzjj.zzaqb;
        this.zzaqr = zzjj.zzaqc;
        this.zzaqs = zzjj.zzaqd;
        this.zzhp = zzjj.zzaqe;
        this.zzaqt = zzjj.zzaqf;
        this.zzaqu = zzjj.zzaqg;
        this.zzaqv = zzjj.zzaqh;
        this.zzaqw = zzjj.zzaqi;
        this.zzaqx = zzjj.zzaqj;
        this.zzaqy = zzjj.zzaqk;
    }

    public final zzjk zza(Location location) {
        this.zzhp = null;
        return this;
    }

    public final zzjj zzhw() {
        return new zzjj(7, this.zzaqm, this.mExtras, this.zzaqn, this.zzaqo, this.zzaqp, this.zzaqq, this.zzvm, this.zzaqr, this.zzaqs, this.zzhp, this.zzaqt, this.zzaqu, this.zzaqv, this.zzaqw, this.zzaqx, this.zzaqy, false);
    }
}
