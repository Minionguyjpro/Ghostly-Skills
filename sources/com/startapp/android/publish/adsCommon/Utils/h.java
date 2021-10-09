package com.startapp.android.publish.adsCommon.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Set;

/* compiled from: StartAppSDK */
public class h {
    public static int a(Context context, int i) {
        return Math.round(TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics()));
    }

    public static int b(Context context, int i) {
        return Math.round(((float) i) / context.getResources().getDisplayMetrics().density);
    }

    public static void a(TextView textView, Set<String> set) {
        if (set.contains("UNDERLINE")) {
            textView.setPaintFlags(textView.getPaintFlags() | 8);
        }
        int i = 0;
        if (set.contains("BOLD") && set.contains("ITALIC")) {
            i = 3;
        } else if (set.contains("BOLD")) {
            i = 1;
        } else if (set.contains("ITALIC")) {
            i = 2;
        }
        textView.setTypeface((Typeface) null, i);
    }

    public static TextView a(Context context, TextView textView, Typeface typeface, int i, float f, int i2, int i3) {
        TextView textView2 = new TextView(context);
        textView2.setTypeface(typeface, i);
        textView2.setTextSize(1, f);
        textView2.setSingleLine(true);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setTextColor(i2);
        textView2.setId(i3);
        return textView2;
    }

    public static RelativeLayout.LayoutParams a(Context context, int[] iArr, int[] iArr2) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        for (int addRule : iArr2) {
            layoutParams.addRule(addRule);
        }
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = iArr[i] == 0 ? 0 : a(context, iArr[i]);
        }
        layoutParams.setMargins(iArr[0], iArr[1], iArr[2], iArr[3]);
        return layoutParams;
    }

    public static RelativeLayout.LayoutParams a(Context context, int[] iArr, int[] iArr2, int i, int i2) {
        RelativeLayout.LayoutParams a2 = a(context, iArr, iArr2);
        a2.addRule(i, i2);
        return a2;
    }

    public static Bitmap a(Context context, String str) {
        return a.a(context, str);
    }

    public static ImageView a(Context context, ImageView imageView, Bitmap bitmap, int i) {
        ImageView imageView2 = new ImageView(context);
        imageView2.setImageBitmap(bitmap);
        imageView2.setId(i);
        return imageView2;
    }

    public static void a(Context context, WindowManager windowManager, Point point) {
        if (Build.VERSION.SDK_INT >= 13) {
            windowManager.getDefaultDisplay().getSize(point);
        } else {
            point.x = windowManager.getDefaultDisplay().getWidth();
            point.y = windowManager.getDefaultDisplay().getHeight();
        }
        point.x = b(context, point.x);
        point.y = b(context, point.y);
    }

    public static void a(WindowManager windowManager, Point point) {
        if (Build.VERSION.SDK_INT >= 13) {
            windowManager.getDefaultDisplay().getSize(point);
            return;
        }
        point.x = windowManager.getDefaultDisplay().getWidth();
        point.y = windowManager.getDefaultDisplay().getHeight();
    }
}
