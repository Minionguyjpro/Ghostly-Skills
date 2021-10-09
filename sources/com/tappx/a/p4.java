package com.tappx.a;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.tappx.a.n3;

public class p4 extends n3 {
    private final VideoView e;
    /* access modifiers changed from: private */
    public View f;

    class a implements MediaPlayer.OnCompletionListener {
        a() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            if (p4.this.f != null) {
                p4.this.f.setVisibility(0);
            }
            p4.this.a(true);
        }
    }

    class b implements MediaPlayer.OnErrorListener {
        b() {
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            if (p4.this.f != null) {
                p4.this.f.setVisibility(0);
            }
            p4.this.b(false);
            return false;
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        public void onClick(View view) {
            p4.this.b().onFinish();
        }
    }

    public p4(Context context, Bundle bundle, Bundle bundle2, n3.a aVar) {
        super(context, aVar);
        VideoView videoView = new VideoView(context);
        this.e = videoView;
        videoView.setOnCompletionListener(new a());
        this.e.setOnErrorListener(new b());
        this.e.setVideoPath(bundle.getString("video_url"));
    }

    private void k() {
        this.f = new View(c());
        int b2 = q3.b(30.0f, c());
        int b3 = q3.b(10.0f, c());
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842919}, e4.INTERSTITIAL_CLOSE_BUTTON_NORMAL.a(c()));
        stateListDrawable.addState(new int[]{16842919}, e4.INTERSTITIAL_CLOSE_BUTTON_PRESSED.a(c()));
        this.f.setBackgroundDrawable(stateListDrawable);
        this.f.setOnClickListener(new c());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(b2, b2);
        layoutParams.addRule(11);
        layoutParams.setMargins(b3, b3, b3, b3);
        d().addView(this.f, layoutParams);
    }

    public void a(Configuration configuration) {
    }

    public void a(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public VideoView e() {
        return this.e;
    }

    public void f() {
    }

    public void g() {
        super.g();
        k();
        this.f.setVisibility(8);
        this.e.start();
    }

    public void h() {
    }

    public void i() {
    }

    public void j() {
    }
}
