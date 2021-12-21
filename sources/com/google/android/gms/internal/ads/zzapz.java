package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;

@zzadh
public final class zzapz implements AudioManager.OnAudioFocusChangeListener {
    private final AudioManager mAudioManager;
    private boolean zzcxs;
    private final zzaqa zzdaq;
    private boolean zzdar;
    private boolean zzdas;
    private float zzdat = 1.0f;

    public zzapz(Context context, zzaqa zzaqa) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.zzdaq = zzaqa;
    }

    private final void zztw() {
        boolean z;
        boolean z2;
        boolean z3 = false;
        boolean z4 = this.zzcxs && !this.zzdas && this.zzdat > 0.0f;
        if (z4 && !(z2 = this.zzdar)) {
            AudioManager audioManager = this.mAudioManager;
            if (audioManager != null && !z2) {
                if (audioManager.requestAudioFocus(this, 3, 2) == 1) {
                    z3 = true;
                }
                this.zzdar = z3;
            }
            this.zzdaq.zzst();
        } else if (!z4 && (z = this.zzdar)) {
            AudioManager audioManager2 = this.mAudioManager;
            if (audioManager2 != null && z) {
                if (audioManager2.abandonAudioFocus(this) == 0) {
                    z3 = true;
                }
                this.zzdar = z3;
            }
            this.zzdaq.zzst();
        }
    }

    public final float getVolume() {
        float f = this.zzdas ? 0.0f : this.zzdat;
        if (this.zzdar) {
            return f;
        }
        return 0.0f;
    }

    public final void onAudioFocusChange(int i) {
        this.zzdar = i > 0;
        this.zzdaq.zzst();
    }

    public final void setMuted(boolean z) {
        this.zzdas = z;
        zztw();
    }

    public final void setVolume(float f) {
        this.zzdat = f;
        zztw();
    }

    public final void zztt() {
        this.zzcxs = true;
        zztw();
    }

    public final void zztu() {
        this.zzcxs = false;
        zztw();
    }
}
