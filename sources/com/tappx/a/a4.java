package com.tappx.a;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import java.io.File;

public class a4 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f370a;
    /* access modifiers changed from: private */
    public MediaScannerConnection b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public String d;
    private final MediaScannerConnection.MediaScannerConnectionClient e = new a();

    class a implements MediaScannerConnection.MediaScannerConnectionClient {
        a() {
        }

        public void onMediaScannerConnected() {
            if (a4.this.b != null) {
                a4.this.b.scanFile(a4.this.c, a4.this.d);
            }
        }

        public void onScanCompleted(String str, Uri uri) {
            if (a4.this.b != null) {
                a4.this.b.disconnect();
            }
        }
    }

    public a4(Context context) {
        this.f370a = context.getApplicationContext();
    }

    public void a(String str, String str2) {
        this.c = str;
        this.d = str2;
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(this.f370a, this.e);
        this.b = mediaScannerConnection;
        mediaScannerConnection.connect();
    }

    public void a(File file, String str) {
        a(file.getAbsolutePath(), str);
    }
}
