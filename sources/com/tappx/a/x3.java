package com.tappx.a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tappx.a.o3;

public class x3 extends l3 {
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public final o3 e;
    /* access modifiers changed from: private */
    public d f;
    private final o3.a g = new b();

    class a implements View.OnTouchListener {
        a() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            x3.this.e.a(motionEvent);
            int action = motionEvent.getAction();
            if ((action != 0 && action != 1) || view.hasFocus()) {
                return false;
            }
            view.requestFocus();
            return false;
        }
    }

    class b implements o3.a {
        b() {
        }

        public void a() {
            boolean unused = x3.this.d = true;
        }
    }

    public interface d {
        void a();

        void b();

        void c();
    }

    public x3(Context context, boolean z) {
        super(context);
        if (!z) {
            c();
        }
        getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 14) {
            a(true);
        }
        setBackgroundColor(0);
        o3 o3Var = new o3();
        this.e = o3Var;
        o3Var.a(this.g);
        setWebViewClient(new c(this, (a) null));
        setOnTouchListener(new a());
    }

    public void setListener(d dVar) {
        this.f = dVar;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
    }

    private final class c extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        private final t4 f624a;

        private c() {
            this.f624a = new t4();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (this.f624a.a(str, x3.this.f)) {
                return true;
            }
            if (x3.this.d) {
                boolean unused = x3.this.d = false;
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.addFlags(268435456);
                try {
                    x3.this.getContext().startActivity(intent);
                    if (x3.this.f != null) {
                        x3.this.f.b();
                    }
                    return true;
                } catch (ActivityNotFoundException unused2) {
                    j4.a("No activity found to handle this URL " + str);
                }
            }
            return false;
        }

        /* synthetic */ c(x3 x3Var, a aVar) {
            this();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        loadDataWithBaseURL((String) null, str, "text/html", "utf-8", (String) null);
    }
}
