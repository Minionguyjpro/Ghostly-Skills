package com.truenet.android.a;

import a.a.b.b.h;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.concurrent.SynchronousQueue;

/* compiled from: StartAppSDK */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public static final a f668a;
    /* access modifiers changed from: private */
    public static final String b;
    /* access modifiers changed from: private */
    public static String c;

    /* compiled from: StartAppSDK */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(e eVar) {
            this();
        }

        public final String a(Context context) {
            h.b(context, "context");
            if (i.c != null) {
                String a2 = i.c;
                if (a2 == null) {
                    h.a();
                }
                return a2;
            }
            SynchronousQueue synchronousQueue = new SynchronousQueue();
            new Handler(Looper.getMainLooper()).post(new C0020a(context, synchronousQueue));
            i.c = (String) synchronousQueue.take();
            String a3 = i.c;
            if (a3 == null) {
                h.a();
            }
            return a3;
        }

        /* renamed from: com.truenet.android.a.i$a$a  reason: collision with other inner class name */
        /* compiled from: StartAppSDK */
        static final class C0020a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Context f669a;
            final /* synthetic */ SynchronousQueue b;

            C0020a(Context context, SynchronousQueue synchronousQueue) {
                this.f669a = context;
                this.b = synchronousQueue;
            }

            public final void run() {
                try {
                    WebSettings settings = new WebView(this.f669a).getSettings();
                    h.a((Object) settings, "WebView(context).settings");
                    this.b.offer(settings.getUserAgentString());
                } catch (Exception e) {
                    Log.e(i.b, e.getMessage());
                    this.b.offer("undefined");
                }
            }
        }
    }

    static {
        a aVar = new a((e) null);
        f668a = aVar;
        b = aVar.getClass().getSimpleName();
    }
}
