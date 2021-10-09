package com.appnext.ads.fullscreen;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appnext.R;
import com.appnext.ads.a;
import com.appnext.base.b.d;
import com.appnext.core.AppnextAd;
import com.appnext.core.f;
import com.appnext.core.k;
import java.util.ArrayList;

public final class e extends Fragment {
    /* access modifiers changed from: private */
    public i bc;
    /* access modifiers changed from: private */
    public TextView bd;
    /* access modifiers changed from: private */
    public int be = 0;
    /* access modifiers changed from: private */
    public ArrayList<AppnextAd> bf;
    /* access modifiers changed from: private */
    public Handler handler;
    Runnable tick = new Runnable() {
        public final void run() {
            e.this.handler.removeCallbacks(this);
            if (e.c(e.this) == 0) {
                e.this.bc.videoSelected((AppnextAd) e.this.bf.get(0));
                e.this.report(a.E);
                return;
            }
            if (e.this.bd != null) {
                TextView e = e.this.bd;
                e.setText(e.this.be + " sec");
            }
            e.this.handler.postDelayed(e.this.tick, 1000);
        }
    };

    static /* synthetic */ int c(e eVar) {
        int i = eVar.be - 1;
        eVar.be = i;
        return i;
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null && getArguments().containsKey(d.fl)) {
            this.be = getArguments().getInt(d.fl);
        }
        if (bundle != null) {
            this.be = bundle.getInt(d.fl);
        }
    }

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        this.bc = (i) activity;
        this.handler = new Handler();
    }

    public final void onAttach(Context context) {
        super.onAttach(context);
        this.bc = (i) context;
        this.handler = new Handler();
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(this.bc.getTemplate("S1"), viewGroup, false);
            View findViewById = relativeLayout.findViewById(R.id.item1);
            View findViewById2 = relativeLayout.findViewById(R.id.item2);
            ((TextView) relativeLayout.findViewById(R.id.title)).setText(this.bc.getConfigManager().get("pre_title_string1"));
            ((TextView) relativeLayout.findViewById(R.id.secondTile)).setText(this.bc.getConfigManager().get("pre_title_string2"));
            this.bd = (TextView) relativeLayout.findViewById(R.id.timer);
            ArrayList<AppnextAd> preRollAds = this.bc.getPreRollAds();
            this.bf = preRollAds;
            if (preRollAds.size() < 2) {
                this.bc.videoSelected(this.bf.get(0));
                return null;
            }
            a((ViewGroup) findViewById, this.bf.get(0), 0);
            a((ViewGroup) findViewById2, this.bf.get(1), 1);
            relativeLayout.findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    e.this.bc.privacyClicked();
                }
            });
            if (k.a(this.bf.get(0), this.bc.getConfigManager())) {
                k.a((Context) this.bc, (ImageView) relativeLayout.findViewById(R.id.privacy));
            }
            TextView textView = this.bd;
            textView.setText(this.be + " sec");
            report(a.B);
            return relativeLayout;
        } catch (Throwable unused) {
            this.bc.closeClicked();
            return null;
        }
    }

    private void a(ViewGroup viewGroup, final AppnextAd appnextAd, final int i) {
        TextView textView = (TextView) viewGroup.findViewById(R.id.title);
        final ImageView imageView = (ImageView) viewGroup.findViewById(R.id.icon);
        final ImageView imageView2 = (ImageView) viewGroup.findViewById(R.id.background_image);
        RatingBar ratingBar = (RatingBar) viewGroup.findViewById(R.id.ratingBar);
        if (viewGroup.findViewById(R.id.playGameTextView) != null) {
            ((TextView) viewGroup.findViewById(R.id.playGameTextView)).setText(this.bc.getConfigManager().get("pre_cta_string"));
        }
        textView.setText(appnextAd.getAdTitle());
        ratingBar.setRating(Float.parseFloat(appnextAd.getStoreRating()));
        viewGroup.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                e.this.bc.videoSelected(appnextAd);
                int i = i;
                if (i == 0) {
                    e.this.report(a.C);
                } else if (i == 1) {
                    e.this.report(a.D);
                }
            }
        });
        if (imageView2 != null) {
            new Thread(new Runnable() {
                public final void run() {
                    final Bitmap Y = f.Y(appnextAd.getWideImageURL());
                    final Bitmap Y2 = f.Y(appnextAd.getImageURL());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            imageView2.setImageBitmap(Y);
                            imageView.setImageBitmap(Y2);
                        }
                    });
                }
            }).start();
        }
        new Thread(new Runnable() {
            public final void run() {
                final Bitmap Y = imageView2 != null ? f.Y(appnextAd.getWideImageURL()) : null;
                final Bitmap Y2 = f.Y(appnextAd.getImageURL());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        if (imageView2 != null) {
                            imageView2.setImageBitmap(Y);
                        }
                        imageView.setImageBitmap(Y2);
                    }
                });
            }
        }).start();
    }

    public final void onDestroyView() {
        super.onDestroyView();
        this.handler.removeCallbacksAndMessages((Object) null);
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(d.fl, this.be);
        super.onSaveInstanceState(bundle);
    }

    public final void onPause() {
        super.onPause();
        this.handler.removeCallbacks(this.tick);
    }

    public final void onResume() {
        super.onResume();
        this.handler.postDelayed(this.tick, 1000);
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        this.bc.report(str, "S1");
    }
}
