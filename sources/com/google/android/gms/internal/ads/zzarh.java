package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.Predicate;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zzarh extends FrameLayout implements zzaqw {
    private final zzaqw zzdcy;
    private final zzapn zzdcz;

    public zzarh(zzaqw zzaqw) {
        super(zzaqw.getContext());
        this.zzdcy = zzaqw;
        this.zzdcz = new zzapn(zzaqw.zzua(), this, this);
        addView(this.zzdcy.getView());
    }

    public final void destroy() {
        this.zzdcy.destroy();
    }

    public final View.OnClickListener getOnClickListener() {
        return this.zzdcy.getOnClickListener();
    }

    public final int getRequestedOrientation() {
        return this.zzdcy.getRequestedOrientation();
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this.zzdcy.getWebView();
    }

    public final boolean isDestroyed() {
        return this.zzdcy.isDestroyed();
    }

    public final void loadData(String str, String str2, String str3) {
        this.zzdcy.loadData(str, str2, str3);
    }

    public final void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zzdcy.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public final void loadUrl(String str) {
        this.zzdcy.loadUrl(str);
    }

    public final void onPause() {
        this.zzdcz.onPause();
        this.zzdcy.onPause();
    }

    public final void onResume() {
        this.zzdcy.onResume();
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzdcy.setOnClickListener(onClickListener);
    }

    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.zzdcy.setOnTouchListener(onTouchListener);
    }

    public final void setRequestedOrientation(int i) {
        this.zzdcy.setRequestedOrientation(i);
    }

    public final void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zzdcy.setWebChromeClient(webChromeClient);
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        this.zzdcy.setWebViewClient(webViewClient);
    }

    public final void stopLoading() {
        this.zzdcy.stopLoading();
    }

    public final void zza(zzc zzc) {
        this.zzdcy.zza(zzc);
    }

    public final void zza(zzd zzd) {
        this.zzdcy.zza(zzd);
    }

    public final void zza(zzarl zzarl) {
        this.zzdcy.zza(zzarl);
    }

    public final void zza(zzasi zzasi) {
        this.zzdcy.zza(zzasi);
    }

    public final void zza(zzfs zzfs) {
        this.zzdcy.zza(zzfs);
    }

    public final void zza(String str, zzv<? super zzaqw> zzv) {
        this.zzdcy.zza(str, zzv);
    }

    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        this.zzdcy.zza(str, predicate);
    }

    public final void zza(String str, Map<String, ?> map) {
        this.zzdcy.zza(str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        this.zzdcy.zza(str, jSONObject);
    }

    public final void zza(boolean z, int i) {
        this.zzdcy.zza(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzdcy.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzdcy.zza(z, i, str, str2);
    }

    public final void zzah(boolean z) {
        this.zzdcy.zzah(z);
    }

    public final void zzai(int i) {
        this.zzdcy.zzai(i);
    }

    public final void zzai(boolean z) {
        this.zzdcy.zzai(z);
    }

    public final void zzaj(boolean z) {
        this.zzdcy.zzaj(z);
    }

    public final void zzak(boolean z) {
        this.zzdcy.zzak(z);
    }

    public final void zzb(zzd zzd) {
        this.zzdcy.zzb(zzd);
    }

    public final void zzb(zzox zzox) {
        this.zzdcy.zzb(zzox);
    }

    public final void zzb(String str, zzv<? super zzaqw> zzv) {
        this.zzdcy.zzb(str, zzv);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        this.zzdcy.zzb(str, jSONObject);
    }

    public final void zzbe(String str) {
        this.zzdcy.zzbe(str);
    }

    public final zzw zzbi() {
        return this.zzdcy.zzbi();
    }

    public final void zzbm(Context context) {
        this.zzdcy.zzbm(context);
    }

    public final void zzc(String str, String str2, String str3) {
        this.zzdcy.zzc(str, str2, str3);
    }

    public final void zzcl() {
        this.zzdcy.zzcl();
    }

    public final void zzcm() {
        this.zzdcy.zzcm();
    }

    public final void zzdr(String str) {
        this.zzdcy.zzdr(str);
    }

    public final void zzno() {
        this.zzdcy.zzno();
    }

    public final void zznp() {
        this.zzdcy.zznp();
    }

    public final String zzol() {
        return this.zzdcy.zzol();
    }

    public final zzapn zztl() {
        return this.zzdcz;
    }

    public final zzarl zztm() {
        return this.zzdcy.zztm();
    }

    public final zznv zztn() {
        return this.zzdcy.zztn();
    }

    public final Activity zzto() {
        return this.zzdcy.zzto();
    }

    public final zznw zztp() {
        return this.zzdcy.zztp();
    }

    public final zzang zztq() {
        return this.zzdcy.zztq();
    }

    public final int zztr() {
        return getMeasuredHeight();
    }

    public final int zzts() {
        return getMeasuredWidth();
    }

    public final void zzty() {
        this.zzdcy.zzty();
    }

    public final void zztz() {
        this.zzdcy.zztz();
    }

    public final void zzu(boolean z) {
        this.zzdcy.zzu(z);
    }

    public final Context zzua() {
        return this.zzdcy.zzua();
    }

    public final zzd zzub() {
        return this.zzdcy.zzub();
    }

    public final zzd zzuc() {
        return this.zzdcy.zzuc();
    }

    public final zzasi zzud() {
        return this.zzdcy.zzud();
    }

    public final String zzue() {
        return this.zzdcy.zzue();
    }

    public final zzasc zzuf() {
        return this.zzdcy.zzuf();
    }

    public final WebViewClient zzug() {
        return this.zzdcy.zzug();
    }

    public final boolean zzuh() {
        return this.zzdcy.zzuh();
    }

    public final zzci zzui() {
        return this.zzdcy.zzui();
    }

    public final boolean zzuj() {
        return this.zzdcy.zzuj();
    }

    public final void zzuk() {
        this.zzdcz.onDestroy();
        this.zzdcy.zzuk();
    }

    public final boolean zzul() {
        return this.zzdcy.zzul();
    }

    public final boolean zzum() {
        return this.zzdcy.zzum();
    }

    public final boolean zzun() {
        return this.zzdcy.zzun();
    }

    public final void zzuo() {
        this.zzdcy.zzuo();
    }

    public final void zzup() {
        this.zzdcy.zzup();
    }

    public final zzox zzuq() {
        return this.zzdcy.zzuq();
    }

    public final void zzur() {
        setBackgroundColor(0);
        this.zzdcy.setBackgroundColor(0);
    }

    public final void zzus() {
        TextView textView = new TextView(getContext());
        Resources resources = zzbv.zzeo().getResources();
        textView.setText(resources != null ? resources.getString(R.string.s7) : "Test Ad");
        textView.setTextSize(15.0f);
        textView.setTextColor(-1);
        textView.setPadding(5, 0, 5, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-12303292);
        gradientDrawable.setCornerRadius(8.0f);
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setBackground(gradientDrawable);
        } else {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        addView(textView, new FrameLayout.LayoutParams(-2, -2, 49));
        bringChildToFront(textView);
    }
}
