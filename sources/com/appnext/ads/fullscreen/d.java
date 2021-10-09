package com.appnext.ads.fullscreen;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appnext.R;
import com.appnext.ads.a;
import com.appnext.core.AppnextAd;
import com.appnext.core.a.b;
import com.appnext.core.f;
import com.appnext.core.k;
import java.util.ArrayList;

public final class d extends Fragment {
    /* access modifiers changed from: private */
    public ImageView aS;
    private TextView aT;
    /* access modifiers changed from: private */
    public h aU;
    /* access modifiers changed from: private */
    public ArrayList<AppnextAd> ads;
    /* access modifiers changed from: private */
    public boolean clicked = false;

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        this.aU = (h) activity;
    }

    public final void onAttach(Context context) {
        super.onAttach(context);
        this.aU = (h) context;
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(this.aU.getTemplate("S3"), viewGroup, false);
            this.ads = this.aU.getPostRollAds();
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.privacy);
            ImageView imageView2 = (ImageView) relativeLayout.findViewById(R.id.close);
            View findViewById = relativeLayout.findViewById(R.id.click);
            this.aS = (ImageView) relativeLayout.findViewById(R.id.media);
            this.aT = (TextView) relativeLayout.findViewById(R.id.install);
            imageView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    d.this.aU.privacyClicked();
                }
            });
            if (k.a(this.ads.get(0), this.aU.getConfigManager())) {
                k.a((Context) this.aU, imageView);
            }
            imageView2.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    d.this.aU.closeClicked();
                }
            });
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    d.this.aU.installClicked((AppnextAd) d.this.ads.get(0));
                }
            });
            String ctaText = this.aU.getCtaText();
            int parseInt = Integer.parseInt(b.bp().b(this.aU.getLanguage(), b.hW, "len"));
            if (!TextUtils.isEmpty(ctaText) && ctaText.length() > parseInt) {
                ctaText = ctaText.substring(0, parseInt);
            }
            this.aT.setText(ctaText);
            this.aT.setTextSize(2, (float) Integer.parseInt(b.bp().b(this.aU.getLanguage(), b.hW, "font_size_sp")));
            new Thread(new Runnable() {
                public final void run() {
                    final Bitmap Y = f.Y(((AppnextAd) d.this.ads.get(0)).getWideImageURL());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            d.this.aS.setImageBitmap(Y);
                        }
                    });
                }
            }).start();
            a(relativeLayout, this.ads.get(0), true);
            View findViewById2 = relativeLayout.findViewById(R.id.extra);
            if (findViewById2 != null) {
                if (this.ads.size() > 1) {
                    a((RelativeLayout) findViewById2.findViewById(R.id.item1), this.ads.get(1), false);
                } else {
                    findViewById2.findViewById(R.id.item1).setVisibility(4);
                }
                if (this.ads.size() > 2) {
                    a((RelativeLayout) findViewById2.findViewById(R.id.item2), this.ads.get(2), false);
                } else {
                    findViewById2.findViewById(R.id.item2).setVisibility(4);
                }
                if (findViewById2.findViewById(R.id.item3) != null) {
                    if (this.ads.size() > 3) {
                        a((RelativeLayout) findViewById2.findViewById(R.id.item3), this.ads.get(3), false);
                    } else {
                        findViewById2.findViewById(R.id.item3).setVisibility(4);
                    }
                }
            }
            report(a.R);
            return relativeLayout;
        } catch (Throwable unused) {
            this.aU.closeClicked();
            return null;
        }
    }

    private void a(final RelativeLayout relativeLayout, final AppnextAd appnextAd, final boolean z) {
        new Thread(new Runnable() {
            public final void run() {
                final Bitmap Y = f.Y(appnextAd.getImageURL());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        ((ImageView) relativeLayout.findViewById(R.id.icon)).setImageBitmap(Y);
                    }
                });
            }
        }).start();
        ((TextView) relativeLayout.findViewById(R.id.title)).setText(appnextAd.getAdTitle());
        ((RatingBar) relativeLayout.findViewById(R.id.rating)).setRating(Float.parseFloat(appnextAd.getStoreRating()));
        relativeLayout.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
                if (z) {
                    d.this.report(a.T);
                } else {
                    d.this.report(a.U);
                    StringBuilder sb = new StringBuilder();
                    sb.append(fullscreenAd.getAppURL());
                    sb.append("&tem_id=");
                    sb.append(d.this.aU.isRewarded() ? "8" : "7");
                    sb.append("05");
                    fullscreenAd.setAppURL(sb.toString());
                }
                d.this.aU.installClicked(fullscreenAd);
                boolean unused = d.this.clicked = true;
            }
        });
    }

    public final void onDestroyView() {
        report(a.S);
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        this.aU.report(str, "S3");
    }
}
