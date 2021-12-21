package com.tappx.a;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.mopub.mraid.MraidNativeCommandHandler;
import com.tappx.a.r3;
import com.tappx.sdk.android.VideoAdActivity;
import java.net.URI;
import java.util.Map;

public class k4 {

    /* renamed from: a  reason: collision with root package name */
    private final z3 f495a;

    class a implements DialogInterface.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Context f496a;
        final /* synthetic */ String b;
        final /* synthetic */ c c;

        a(Context context, String str, c cVar) {
            this.f496a = context;
            this.b = str;
            this.c = cVar;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            k4.this.a(this.f496a, this.b, this.c);
        }
    }

    class b implements r3.b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Context f497a;
        final /* synthetic */ c b;

        b(k4 k4Var, Context context, c cVar) {
            this.f497a = context;
            this.b = cVar;
        }

        public void a() {
            Toast.makeText(this.f497a, "Image downloaded", 0).show();
        }

        public void b() {
            Toast.makeText(this.f497a, "Image download failed", 0).show();
            this.b.a(new f4("Download error"));
        }
    }

    interface c {
        void a(f4 f4Var);
    }

    public k4(z3 z3Var) {
        this.f495a = z3Var;
    }

    public void a(Context context, String str) {
        VideoAdActivity.startMraid(context, str);
    }

    /* access modifiers changed from: package-private */
    public boolean b(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return q3.a(context, intent);
    }

    /* access modifiers changed from: package-private */
    public boolean c(Context context) {
        return "mounted".equals(Environment.getExternalStorageState()) && d3.a(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* access modifiers changed from: package-private */
    public boolean d(Context context) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return q3.a(context, intent);
    }

    /* access modifiers changed from: package-private */
    public boolean a(Context context) {
        Intent type = new Intent("android.intent.action.INSERT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE);
        if (!(Build.VERSION.SDK_INT >= 14) || !q3.a(context, type)) {
            return false;
        }
        return true;
    }

    public k4() {
        this(new z3());
    }

    /* access modifiers changed from: package-private */
    public boolean a(Activity activity, View view) {
        if (Build.VERSION.SDK_INT < 12) {
            return false;
        }
        while (view.isHardwareAccelerated() && !u4.a(view.getLayerType(), 1)) {
            if (!(view.getParent() instanceof View)) {
                Window window = activity.getWindow();
                if (window == null || !u4.a(window.getAttributes().flags, 16777216)) {
                    return false;
                }
                return true;
            }
            view = (View) view.getParent();
        }
        return false;
    }

    public void b(Context context, String str, c cVar) {
        if (!c(context)) {
            throw new f4("Unsupported action");
        } else if (context instanceof Activity) {
            new AlertDialog.Builder(context).setTitle("Save Image").setMessage("Download image to Gallery?").setNegativeButton("Cancel", (DialogInterface.OnClickListener) null).setPositiveButton("Ok", new a(context, str, cVar)).setCancelable(true).show();
        } else {
            Toast.makeText(context, "Downloading image ...", 0).show();
            a(context, str, cVar);
        }
    }

    public void a(Context context, Map<String, String> map) {
        if (a(context)) {
            try {
                Map<String, Object> a2 = m4.a(map);
                Intent type = new Intent("android.intent.action.INSERT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE);
                for (String next : a2.keySet()) {
                    Object obj = a2.get(next);
                    if (obj instanceof Long) {
                        type.putExtra(next, ((Long) obj).longValue());
                    } else if (obj instanceof Integer) {
                        type.putExtra(next, ((Integer) obj).intValue());
                    } else {
                        type.putExtra(next, (String) obj);
                    }
                }
                type.setFlags(268435456);
                context.startActivity(type);
            } catch (ActivityNotFoundException unused) {
                throw new f4("App not found");
            } catch (IllegalArgumentException e) {
                j4.a("Invalid params " + e.getMessage());
                throw new f4((Throwable) e);
            } catch (Exception e2) {
                throw new f4((Throwable) e2);
            }
        } else {
            throw new f4("Unsupported action");
        }
    }

    public void a(Context context, URI uri) {
        this.f495a.a(context, uri.toString());
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str, c cVar) {
        u4.a(new r3(context, new b(this, context, cVar)), (P[]) new String[]{str});
    }
}
