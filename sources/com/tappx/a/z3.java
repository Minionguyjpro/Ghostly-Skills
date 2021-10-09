package com.tappx.a;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.mopub.common.Constants;

public class z3 {

    private static final class b extends AsyncTask<String, Void, Uri> {

        /* renamed from: a  reason: collision with root package name */
        private final Context f638a;

        private String a(String str) {
            return str;
        }

        private boolean b(Uri uri) {
            return "about".equalsIgnoreCase(uri.getScheme());
        }

        private boolean c(Uri uri) {
            String scheme = uri.getScheme();
            return Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme);
        }

        private void d(Uri uri) {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (q3.a(this.f638a, intent)) {
                a(intent);
            }
        }

        private void e(Uri uri) {
            d(uri);
        }

        private void f(Uri uri) {
            if (!b(uri)) {
                if (c(uri)) {
                    d(uri);
                } else {
                    e(uri);
                }
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Uri doInBackground(String... strArr) {
            String a2 = a(strArr[0]);
            if (a2 == null) {
                return null;
            }
            return Uri.parse(a2);
        }

        private b(Context context) {
            this.f638a = context;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Uri uri) {
            if (uri != null) {
                f(uri);
            }
        }

        private void a(Intent intent) {
            if (!(this.f638a instanceof Activity)) {
                intent.addFlags(268435456);
            }
            try {
                this.f638a.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str) {
        u4.a(new b(context), (P[]) new String[]{str});
    }
}
