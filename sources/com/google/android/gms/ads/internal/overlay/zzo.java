package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzkb;
import javax.annotation.Nullable;

@zzadh
public final class zzo extends FrameLayout implements View.OnClickListener {
    private final ImageButton zzbyy;
    private final zzw zzbyz;

    public zzo(Context context, zzp zzp, @Nullable zzw zzw) {
        super(context);
        this.zzbyz = zzw;
        setOnClickListener(this);
        ImageButton imageButton = new ImageButton(context);
        this.zzbyy = imageButton;
        imageButton.setImageResource(17301527);
        this.zzbyy.setBackgroundColor(0);
        this.zzbyy.setOnClickListener(this);
        ImageButton imageButton2 = this.zzbyy;
        zzkb.zzif();
        int zza = zzamu.zza(context, zzp.paddingLeft);
        zzkb.zzif();
        int zza2 = zzamu.zza(context, 0);
        zzkb.zzif();
        int zza3 = zzamu.zza(context, zzp.paddingRight);
        zzkb.zzif();
        imageButton2.setPadding(zza, zza2, zza3, zzamu.zza(context, zzp.paddingBottom));
        this.zzbyy.setContentDescription("Interstitial close button");
        zzkb.zzif();
        zzamu.zza(context, zzp.size);
        ImageButton imageButton3 = this.zzbyy;
        zzkb.zzif();
        int zza4 = zzamu.zza(context, zzp.size + zzp.paddingLeft + zzp.paddingRight);
        zzkb.zzif();
        addView(imageButton3, new FrameLayout.LayoutParams(zza4, zzamu.zza(context, zzp.size + zzp.paddingBottom), 17));
    }

    public final void onClick(View view) {
        zzw zzw = this.zzbyz;
        if (zzw != null) {
            zzw.zzni();
        }
    }

    public final void zzu(boolean z) {
        ImageButton imageButton;
        int i;
        if (z) {
            imageButton = this.zzbyy;
            i = 8;
        } else {
            imageButton = this.zzbyy;
            i = 0;
        }
        imageButton.setVisibility(i);
    }
}
