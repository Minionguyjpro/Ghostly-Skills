package com.appnext.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

public final class k {
    public static void a(final Context context, final ImageView imageView) {
        new Thread(new Runnable() {
            public final void run() {
                try {
                    final Bitmap Y = f.Y("https://cdn.appnext.com/tools/sdk/adchoices/adchoices_big.png");
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            try {
                                if (Y != null) {
                                    imageView.setImageDrawable(new BitmapDrawable(context.getResources(), Y));
                                    return;
                                }
                                imageView.setImageResource(R.drawable.apnxt_adchoices);
                            } catch (Throwable unused) {
                            }
                        }
                    });
                } catch (Throwable unused) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            imageView.setImageResource(R.drawable.apnxt_adchoices);
                        }
                    });
                }
            }
        }).start();
    }

    public static boolean a(AppnextAd appnextAd, p pVar) {
        return appnextAd.isGdpr() && Boolean.parseBoolean(pVar.get("gdpr"));
    }
}
