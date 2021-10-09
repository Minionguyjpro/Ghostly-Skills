package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.appnext.base.b.d;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Asserts;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzapi extends FrameLayout implements zzapf {
    private final zzapw zzcxm;
    private final FrameLayout zzcxn;
    private final zznx zzcxo;
    private final zzapy zzcxp;
    private final long zzcxq;
    private zzapg zzcxr;
    private boolean zzcxs;
    private boolean zzcxt;
    private boolean zzcxu;
    private boolean zzcxv;
    private long zzcxw;
    private long zzcxx;
    private String zzcxy;
    private Bitmap zzcxz;
    private ImageView zzcya;
    private boolean zzcyb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzapi(Context context, zzapw zzapw, int i, boolean z, zznx zznx, zzapv zzapv) {
        super(context);
        Context context2 = context;
        zzapw zzapw2 = zzapw;
        this.zzcxm = zzapw2;
        zznx zznx2 = zznx;
        this.zzcxo = zznx2;
        FrameLayout frameLayout = new FrameLayout(context);
        this.zzcxn = frameLayout;
        addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        Asserts.checkNotNull(zzapw.zzbi());
        zzapg zza = zzapw.zzbi().zzwz.zza(context, zzapw2, i, z, zznx2, zzapv);
        this.zzcxr = zza;
        if (zza != null) {
            this.zzcxn.addView(zza, new FrameLayout.LayoutParams(-1, -1, 17));
            if (((Boolean) zzkb.zzik().zzd(zznk.zzavg)).booleanValue()) {
                zztd();
            }
        }
        this.zzcya = new ImageView(context);
        this.zzcxq = ((Long) zzkb.zzik().zzd(zznk.zzavk)).longValue();
        boolean booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzavi)).booleanValue();
        this.zzcxv = booleanValue;
        zznx zznx3 = this.zzcxo;
        if (zznx3 != null) {
            zznx3.zze("spinner_used", booleanValue ? "1" : "0");
        }
        this.zzcxp = new zzapy(this);
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.zza(this);
        }
        if (this.zzcxr == null) {
            zzg("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    public static void zza(zzapw zzapw) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "no_video_view");
        zzapw.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    public static void zza(zzapw zzapw, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "decoderProps");
        hashMap.put("error", str);
        zzapw.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    public static void zza(zzapw zzapw, Map<String, List<Map<String, Object>>> map) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "decoderProps");
        hashMap.put("mimeTypes", map);
        zzapw.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    /* access modifiers changed from: private */
    public final void zza(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", str);
        String str2 = null;
        for (String str3 : strArr) {
            if (str2 == null) {
                str2 = str3;
            } else {
                hashMap.put(str2, str3);
                str2 = null;
            }
        }
        this.zzcxm.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    private final boolean zztf() {
        return this.zzcya.getParent() != null;
    }

    private final void zztg() {
        if (this.zzcxm.zzto() != null && this.zzcxt && !this.zzcxu) {
            this.zzcxm.zzto().getWindow().clearFlags(128);
            this.zzcxt = false;
        }
    }

    public final void destroy() {
        this.zzcxp.pause();
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.stop();
        }
        zztg();
    }

    public final void finalize() throws Throwable {
        try {
            this.zzcxp.pause();
            if (this.zzcxr != null) {
                zzapg zzapg = this.zzcxr;
                Executor executor = zzaoe.zzcvy;
                zzapg.getClass();
                executor.execute(zzapj.zza(zzapg));
            }
        } finally {
            super.finalize();
        }
    }

    public final void onPaused() {
        zza("pause", new String[0]);
        zztg();
        this.zzcxs = false;
    }

    public final void onWindowVisibilityChanged(int i) {
        boolean z;
        if (i == 0) {
            this.zzcxp.resume();
            z = true;
        } else {
            this.zzcxp.pause();
            this.zzcxx = this.zzcxw;
            z = false;
        }
        zzakk.zzcrm.post(new zzapm(this, z));
    }

    public final void pause() {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.pause();
        }
    }

    public final void play() {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.play();
        }
    }

    public final void seekTo(int i) {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.seekTo(i);
        }
    }

    public final void setVolume(float f) {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.zzcxl.setVolume(f);
            zzapg.zzst();
        }
    }

    public final void zza(float f, float f2) {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.zza(f, f2);
        }
    }

    public final void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzcxn.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public final void zzdn(String str) {
        this.zzcxy = str;
    }

    public final void zzf(int i, int i2) {
        if (this.zzcxv) {
            int max = Math.max(i / ((Integer) zzkb.zzik().zzd(zznk.zzavj)).intValue(), 1);
            int max2 = Math.max(i2 / ((Integer) zzkb.zzik().zzd(zznk.zzavj)).intValue(), 1);
            Bitmap bitmap = this.zzcxz;
            if (bitmap == null || bitmap.getWidth() != max || this.zzcxz.getHeight() != max2) {
                this.zzcxz = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
                this.zzcyb = false;
            }
        }
    }

    public final void zzf(MotionEvent motionEvent) {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.dispatchTouchEvent(motionEvent);
        }
    }

    public final void zzg(String str, String str2) {
        zza("error", "what", str, "extra", str2);
    }

    public final void zzsu() {
        this.zzcxp.resume();
        zzakk.zzcrm.post(new zzapk(this));
    }

    public final void zzsv() {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null && this.zzcxx == 0) {
            zza("canplaythrough", VastIconXmlManager.DURATION, String.valueOf(((float) zzapg.getDuration()) / 1000.0f), "videoWidth", String.valueOf(this.zzcxr.getVideoWidth()), "videoHeight", String.valueOf(this.zzcxr.getVideoHeight()));
        }
    }

    public final void zzsw() {
        if (this.zzcxm.zzto() != null && !this.zzcxt) {
            boolean z = (this.zzcxm.zzto().getWindow().getAttributes().flags & 128) != 0;
            this.zzcxu = z;
            if (!z) {
                this.zzcxm.zzto().getWindow().addFlags(128);
                this.zzcxt = true;
            }
        }
        this.zzcxs = true;
    }

    public final void zzsx() {
        zza("ended", new String[0]);
        zztg();
    }

    public final void zzsy() {
        if (this.zzcyb && this.zzcxz != null && !zztf()) {
            this.zzcya.setImageBitmap(this.zzcxz);
            this.zzcya.invalidate();
            this.zzcxn.addView(this.zzcya, new FrameLayout.LayoutParams(-1, -1));
            this.zzcxn.bringChildToFront(this.zzcya);
        }
        this.zzcxp.pause();
        this.zzcxx = this.zzcxw;
        zzakk.zzcrm.post(new zzapl(this));
    }

    public final void zzsz() {
        if (this.zzcxs && zztf()) {
            this.zzcxn.removeView(this.zzcya);
        }
        if (this.zzcxz != null) {
            long elapsedRealtime = zzbv.zzer().elapsedRealtime();
            if (this.zzcxr.getBitmap(this.zzcxz) != null) {
                this.zzcyb = true;
            }
            long elapsedRealtime2 = zzbv.zzer().elapsedRealtime() - elapsedRealtime;
            if (zzakb.zzqp()) {
                StringBuilder sb = new StringBuilder(46);
                sb.append("Spinner frame grab took ");
                sb.append(elapsedRealtime2);
                sb.append("ms");
                zzakb.v(sb.toString());
            }
            if (elapsedRealtime2 > this.zzcxq) {
                zzakb.zzdk("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzcxv = false;
                this.zzcxz = null;
                zznx zznx = this.zzcxo;
                if (zznx != null) {
                    zznx.zze("spinner_jank", Long.toString(elapsedRealtime2));
                }
            }
        }
    }

    public final void zzta() {
        if (this.zzcxr != null) {
            if (!TextUtils.isEmpty(this.zzcxy)) {
                this.zzcxr.setVideoPath(this.zzcxy);
            } else {
                zza("no_src", new String[0]);
            }
        }
    }

    public final void zztb() {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.zzcxl.setMuted(true);
            zzapg.zzst();
        }
    }

    public final void zztc() {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            zzapg.zzcxl.setMuted(false);
            zzapg.zzst();
        }
    }

    public final void zztd() {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            TextView textView = new TextView(zzapg.getContext());
            String valueOf = String.valueOf(this.zzcxr.zzsp());
            textView.setText(valueOf.length() != 0 ? "AdMob - ".concat(valueOf) : new String("AdMob - "));
            textView.setTextColor(-65536);
            textView.setBackgroundColor(-256);
            this.zzcxn.addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
            this.zzcxn.bringChildToFront(textView);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzte() {
        zzapg zzapg = this.zzcxr;
        if (zzapg != null) {
            long currentPosition = (long) zzapg.getCurrentPosition();
            if (this.zzcxw != currentPosition && currentPosition > 0) {
                zza("timeupdate", d.fl, String.valueOf(((float) currentPosition) / 1000.0f));
                this.zzcxw = currentPosition;
            }
        }
    }
}
