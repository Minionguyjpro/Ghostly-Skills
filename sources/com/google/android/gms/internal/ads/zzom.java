package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzom extends RelativeLayout {
    private static final float[] zzbhs = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    private AnimationDrawable zzbht;

    public zzom(Context context, zzoj zzoj, RelativeLayout.LayoutParams layoutParams) {
        super(context);
        Preconditions.checkNotNull(zzoj);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zzbhs, (RectF) null, (float[]) null));
        shapeDrawable.getPaint().setColor(zzoj.getBackgroundColor());
        setLayoutParams(layoutParams);
        zzbv.zzem().setBackground(this, shapeDrawable);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(zzoj.getText())) {
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(zzoj.getText());
            textView.setTextColor(zzoj.getTextColor());
            textView.setTextSize((float) zzoj.getTextSize());
            zzkb.zzif();
            int zza = zzamu.zza(context, 4);
            zzkb.zzif();
            textView.setPadding(zza, 0, zzamu.zza(context, 4), 0);
            addView(textView);
            layoutParams2.addRule(1, textView.getId());
        }
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setId(1195835394);
        List<zzon> zzjs = zzoj.zzjs();
        if (zzjs != null && zzjs.size() > 1) {
            this.zzbht = new AnimationDrawable();
            for (zzon zzjy : zzjs) {
                try {
                    this.zzbht.addFrame((Drawable) ObjectWrapper.unwrap(zzjy.zzjy()), zzoj.zzjt());
                } catch (Exception e) {
                    zzakb.zzb("Error while getting drawable.", e);
                }
            }
            zzbv.zzem().setBackground(imageView, this.zzbht);
        } else if (zzjs.size() == 1) {
            try {
                imageView.setImageDrawable((Drawable) ObjectWrapper.unwrap(zzjs.get(0).zzjy()));
            } catch (Exception e2) {
                zzakb.zzb("Error while getting drawable.", e2);
            }
        }
        addView(imageView);
    }

    public final void onAttachedToWindow() {
        AnimationDrawable animationDrawable = this.zzbht;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        super.onAttachedToWindow();
    }
}
