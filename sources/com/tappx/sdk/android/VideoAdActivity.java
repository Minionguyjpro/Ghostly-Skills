package com.tappx.sdk.android;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import com.appnext.base.b.d;
import com.mopub.common.AdType;
import com.mopub.mobileads.resource.DrawableConstants;
import com.tappx.a.n3;
import com.tappx.a.p4;

public class VideoAdActivity extends Activity implements n3.a {
    public static final String VIDEO_CLASS_EXTRAS_KEY = "video_view_class_name";
    public static final String VIDEO_URL = "video_url";

    /* renamed from: a  reason: collision with root package name */
    private n3 f656a;

    static Intent a(Context context, String str) {
        Intent intent = new Intent(context, VideoAdActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("video_view_class_name", AdType.MRAID);
        intent.putExtra("video_url", str);
        return intent;
    }

    public static void startMraid(Context context, String str) {
        try {
            context.startActivity(a(context, str));
        } catch (ActivityNotFoundException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        n3 n3Var = this.f656a;
        if (n3Var != null) {
            n3Var.a(i, i2, intent);
        }
    }

    public void onBackPressed() {
        n3 n3Var = this.f656a;
        if (n3Var != null && n3Var.a()) {
            super.onBackPressed();
            this.f656a.f();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        n3 n3Var = this.f656a;
        if (n3Var != null) {
            n3Var.a(configuration);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(new ColorDrawable(DrawableConstants.CtaButton.BACKGROUND_COLOR));
        requestWindowFeature(1);
        getWindow().addFlags(d.fb);
        try {
            n3 a2 = a(bundle);
            this.f656a = a2;
            a2.g();
        } catch (Exception unused) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        n3 n3Var = this.f656a;
        if (n3Var != null) {
            n3Var.h();
        }
        super.onDestroy();
        a();
    }

    public void onFinish() {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        n3 n3Var = this.f656a;
        if (n3Var != null) {
            n3Var.i();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        n3 n3Var = this.f656a;
        if (n3Var != null) {
            n3Var.j();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        n3 n3Var = this.f656a;
        if (n3Var != null) {
            n3Var.a(bundle);
        }
    }

    public void onSetContentView(View view) {
        setContentView(view);
    }

    private n3 a(Bundle bundle) {
        return new p4(this, getIntent().getExtras(), bundle, this);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        if (audioManager != null) {
            audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
        }
    }
}
