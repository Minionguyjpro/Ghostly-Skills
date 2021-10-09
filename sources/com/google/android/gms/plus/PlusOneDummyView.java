package com.google.android.gms.plus;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;

@Deprecated
public class PlusOneDummyView extends FrameLayout {
    @Deprecated
    public static final String TAG = "PlusOneDummyView";

    private static class zza implements zzd {
        private Context mContext;

        private zza(Context context) {
            this.mContext = context;
        }

        public final Drawable getDrawable(int i) {
            return this.mContext.getResources().getDrawable(17301508);
        }

        public final boolean isValid() {
            return true;
        }
    }

    private static class zzb implements zzd {
        private Context mContext;

        private zzb(Context context) {
            this.mContext = context;
        }

        public final Drawable getDrawable(int i) {
            try {
                Resources resources = this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
                return resources.getDrawable(resources.getIdentifier(i != 0 ? i != 1 ? i != 2 ? "ic_plusone_standard" : "ic_plusone_tall" : "ic_plusone_medium" : "ic_plusone_small", "drawable", "com.google.android.gms"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        public final boolean isValid() {
            try {
                this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            }
        }
    }

    private static class zzc implements zzd {
        private Context mContext;

        private zzc(Context context) {
            this.mContext = context;
        }

        public final Drawable getDrawable(int i) {
            return this.mContext.getResources().getDrawable(this.mContext.getResources().getIdentifier(i != 0 ? i != 1 ? i != 2 ? "ic_plusone_standard_off_client" : "ic_plusone_tall_off_client" : "ic_plusone_medium_off_client" : "ic_plusone_small_off_client", "drawable", this.mContext.getPackageName()));
        }

        public final boolean isValid() {
            return (this.mContext.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", this.mContext.getPackageName()) == 0) ? false : true;
        }
    }

    private interface zzd {
        Drawable getDrawable(int i);

        boolean isValid();
    }

    @Deprecated
    public PlusOneDummyView(Context context, int i) {
        super(context);
        Button button = new Button(context);
        button.setEnabled(false);
        zzd zzb2 = new zzb(getContext());
        zzb2 = !zzb2.isValid() ? new zzc(getContext()) : zzb2;
        button.setBackgroundDrawable((!zzb2.isValid() ? new zza(getContext()) : zzb2).getDrawable(i));
        Point point = new Point();
        int i2 = 20;
        int i3 = 24;
        if (i == 0) {
            i2 = 14;
        } else if (i == 1) {
            i3 = 32;
        } else if (i != 2) {
            i2 = 24;
            i3 = 38;
        } else {
            i3 = 50;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float applyDimension = TypedValue.applyDimension(1, (float) i3, displayMetrics);
        float applyDimension2 = TypedValue.applyDimension(1, (float) i2, displayMetrics);
        double d = (double) applyDimension;
        Double.isNaN(d);
        point.x = (int) (d + 0.5d);
        double d2 = (double) applyDimension2;
        Double.isNaN(d2);
        point.y = (int) (d2 + 0.5d);
        addView(button, new FrameLayout.LayoutParams(point.x, point.y, 17));
    }
}
