package com.startapp.android.publish.ads.a;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mopub.common.AdType;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.g.a.a;
import com.startapp.android.publish.adsCommon.g.a.c;
import com.startapp.android.publish.adsCommon.g.a.e;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.a.g;
import java.util.Map;

/* compiled from: StartAppSDK */
public class d extends c {
    /* access modifiers changed from: private */
    public com.startapp.android.publish.adsCommon.g.a.d i = com.startapp.android.publish.adsCommon.g.a.d.LOADING;
    private DisplayMetrics j;
    /* access modifiers changed from: private */
    public b k;
    /* access modifiers changed from: private */
    public com.startapp.android.publish.adsCommon.g.b.b l;
    /* access modifiers changed from: private */
    public com.startapp.android.publish.adsCommon.g.c.a m;
    private ImageButton n;
    /* access modifiers changed from: private */
    public boolean o = false;
    /* access modifiers changed from: private */
    public boolean p = false;

    /* access modifiers changed from: protected */
    public boolean b(String str) {
        return false;
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        if (this.j == null) {
            this.j = new DisplayMetrics();
        }
        if (this.l == null) {
            this.l = new com.startapp.android.publish.adsCommon.g.b.b(b());
        }
        if (this.m == null) {
            this.m = new com.startapp.android.publish.adsCommon.g.c.a();
        }
        if (this.k == null) {
            this.k = new b(new a.C0005a() {
                public boolean a(String str) {
                    return d.this.a(str, true);
                }
            });
        }
    }

    public void u() {
        super.u();
        this.p = true;
        if (this.i == com.startapp.android.publish.adsCommon.g.a.d.DEFAULT) {
            this.k.fireViewableChangeEvent();
        }
    }

    public void a(Configuration configuration) {
        G();
    }

    public void s() {
        this.p = false;
        if (this.i == com.startapp.android.publish.adsCommon.g.a.d.DEFAULT) {
            this.k.fireViewableChangeEvent();
        }
        super.s();
    }

    /* access modifiers changed from: protected */
    public void x() {
        this.d.setWebViewClient(new a(this.k));
        this.d.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                try {
                    if (consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.ERROR) {
                        g.a("MraidMode", 6, "WebChromeClient console error: " + consoleMessage.message());
                        if (consoleMessage.message().contains(AdType.MRAID)) {
                            f.a(d.this.b(), com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "MraidMode.ConsoleError", consoleMessage.message(), "");
                        }
                    } else {
                        g.a("MraidMode", 3, "WebChromeClient console log: " + consoleMessage.message());
                    }
                } catch (Exception e) {
                    g.a("MraidMode", 6, "WebChromeClient onConsoleMessage Exception: " + e.getMessage());
                }
                return super.onConsoleMessage(consoleMessage);
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, boolean z) {
        g.a("MraidMode", 3, "adClicked with url: " + str);
        com.startapp.android.publish.adsCommon.g.a.d dVar = com.startapp.android.publish.adsCommon.g.a.d.HIDDEN;
        this.i = dVar;
        c.a(dVar, this.d);
        try {
            return super.a(str, z);
        } catch (Exception e) {
            Activity b2 = b();
            com.startapp.android.publish.adsCommon.f.d dVar2 = com.startapp.android.publish.adsCommon.f.d.EXCEPTION;
            f.a(b2, dVar2, "MraidMode.adClicked", "url = [" + str + "], " + e.getMessage(), "");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void G() {
        try {
            b().getWindowManager().getDefaultDisplay().getMetrics(this.j);
            c.a(b(), this.j.widthPixels, this.j.heightPixels, this.d);
            c.b(b(), this.j.widthPixels, this.j.heightPixels, this.d);
            c.a(b(), 0, 0, this.j.widthPixels, this.j.heightPixels, this.d);
            c.b(b(), 0, 0, this.j.widthPixels, this.j.heightPixels, this.d);
        } catch (Exception e) {
            f.a(b(), com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "MraidMode.updateDisplayMetrics", e.getMessage(), "");
        }
    }

    /* access modifiers changed from: private */
    public void H() {
        try {
            ImageButton imageButton = new ImageButton(b());
            this.n = imageButton;
            imageButton.setBackgroundColor(0);
            this.n.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    d.this.k.close();
                }
            });
            if (!this.o) {
                I();
            }
            int a2 = h.a((Context) b(), 50);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a2);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            this.f.addView(this.n, layoutParams);
        } catch (Exception e) {
            f.a(b(), com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "MraidMode.addCloseRegion", e.getMessage(), "");
        }
    }

    /* compiled from: StartAppSDK */
    private class b extends com.startapp.android.publish.adsCommon.g.a.a {
        public b(a.C0005a aVar) {
            super(aVar);
        }

        public void close() {
            g.a("MraidMode", 3, "close");
            com.startapp.android.publish.adsCommon.g.a.d unused = d.this.i = com.startapp.android.publish.adsCommon.g.a.d.HIDDEN;
            c.a(d.this.i, d.this.d);
            d.this.g.run();
        }

        public void useCustomClose(String str) {
            g.a("MraidMode", 3, "useCustomClose: " + str);
            boolean parseBoolean = Boolean.parseBoolean(str);
            if (d.this.o != parseBoolean) {
                boolean unused = d.this.o = parseBoolean;
                if (parseBoolean) {
                    d.this.J();
                } else {
                    d.this.I();
                }
            }
        }

        public void setOrientationProperties(Map<String, String> map) {
            g.a("MraidMode", 3, "setOrientationProperties: " + map);
            boolean parseBoolean = Boolean.parseBoolean(map.get("allowOrientationChange"));
            String str = map.get("forceOrientation");
            if (d.this.m.f251a != parseBoolean || d.this.m.b != com.startapp.android.publish.adsCommon.g.c.a.a(str)) {
                d.this.m.f251a = parseBoolean;
                d.this.m.b = com.startapp.android.publish.adsCommon.g.c.a.a(str);
                applyOrientationProperties(d.this.b(), d.this.m);
            }
        }

        public boolean isFeatureSupported(String str) {
            return d.this.l.a(str);
        }

        public void fireViewableChangeEvent() {
            c.a(d.this.d, d.this.p);
        }
    }

    /* access modifiers changed from: private */
    public void I() {
        try {
            if (this.n != null) {
                this.n.setImageDrawable(com.startapp.common.a.d.a(b().getResources(), "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA39pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDozODRkZTAxYi00OWRkLWM4NDYtYThkNC0wZWRiMDMwYTZlODAiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QkE0Q0U2MUY2QzA0MTFFNUE3MkJGQjQ1MTkzOEYxQUUiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QkE0Q0U2MUU2QzA0MTFFNUE3MkJGQjQ1MTkzOEYxQUUiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjlkZjAyMGU0LTNlYmUtZTY0ZC04YjRiLWM5ZWY4MTU4ZjFhYyIgc3RSZWY6ZG9jdW1lbnRJRD0iYWRvYmU6ZG9jaWQ6cGhvdG9zaG9wOmU1MzEzNDdlLTZjMDEtMTFlNS1hZGZlLThmMTBjZWYxMGRiZSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PngNsEEAAANeSURBVHjatFfNS1tBEH+pUZOQ0B4i3sTSxHMRFNQoFBEP7dHgvyDiKWgguQra9F+oxqNiwOTQ+oFI1ZM3jSf1YK5FL41ooaKZzu+x+4gv2bx9Rgd+JNn5zO7s7IzH0CQiCvLHZ8YnxkfGe8ZbwS4zSowTxi/GT4/Hc2u8BLHjCOM745b06VboRJpx7GN8ZfyDxUqlQgcHB5RMJmloaIg6Ozupra3NBL5jDTzIQFYQdDOw5db5B8YxLDw+PtLKygr19PQQWDqIRqOUzWZNXUHH2rvBgr2M39C6uLig/v5+bcd2QLdUKskgYLNX57yvIL2zs0OhUOjZziU6Ojro8PBQBnGl3Alm+BknkMI54mybdS4BW3t7ezKIInzVCwDJYm4Zon4p5xLYzfPzcxlEpl7S3SNpmjlznZwQiXn/5CjEnTUzt5GBsbExamlpUfLBg0wjG8vLy3IXlqTzEAoH7m4kElEqTk1Nmfd7bW2tbhBYAw8ykFXZgQ9RJ1CsQghgEr/29/eVStPT09XFhdbX18nr9Vr81tZWyuVyFh+yMzMzSnvwJWjyDS+MYic2NzeV17O7u9vg2m79jsfjBv9bg7PbxOrqqjExMWHxIdvV1aW0V+VrFDtwhFCGh4cbnl0mk6kp+BsbGybsBNlGtkZGRqToEQK4xjfUc6csXlhYcHyFFhcXHe3Al6BrQz427e3tWldpfn5e6Rw83cIkHyvXAUAZb4SdsKZbPe0BaB+Bz+cjTiDlDmxtbZkybo9AKwn9fj9tb2875gBkINvIFnzJJMQ1PMV9GBgYUF6bQCBgFAoFY3x8/Ml6KpUy0un0kzXIQBY6KqrydapViPL5fM0/Rfcj+fhuJw5CqxBpleJYLEY3NzeW8dnZ2RoZrEmCLHQcSvGdWYrFe7CEFTwUqqjR85XLZUokEkoZ8CADWe3HqKoTcnyOdW5KI5m+vj56eHiQz3G0bkNyeXn5ag3J2dmZ/PffVC1Z8bVast3d3eqWLKDVlAaDwaadh8Nhvaa0XluOHg7n9lzn0MWRarfltp0oysEErRqGDTeDCbK9ajApuh7TxGiWERlrjWZzc3M0ODhYM5phDTzbaHb/rNHMFkhUNK13LobTv6K2RJ3se1yO519s4/k7wf5jG89/6I7n/wUYAGo3YtcprD4sAAAAAElFTkSuQmCC"));
                this.n.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        } catch (Exception e) {
            f.a(b(), com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "MraidMode.showDefaultCloseButton", e.getMessage(), "");
        }
    }

    /* access modifiers changed from: private */
    public void J() {
        try {
            if (this.n != null) {
                this.n.setImageResource(17170445);
            }
        } catch (Exception e) {
            f.a(b(), com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "MraidMode.removeDefaultCloseButton", e.getMessage(), "");
        }
    }

    /* compiled from: StartAppSDK */
    private class a extends e {
        public a(com.startapp.android.publish.adsCommon.g.a.b bVar) {
            super(bVar);
        }

        public void onPageFinished(WebView webView, String str) {
            View a2;
            super.onPageFinished(webView, str);
            if (d.this.i == com.startapp.android.publish.adsCommon.g.a.d.LOADING) {
                c.a(AdType.INTERSTITIAL, webView);
                com.startapp.android.publish.adsCommon.g.b.a.a(d.this.b(), webView, d.this.l);
                d.this.G();
                d.this.H();
                com.startapp.android.publish.adsCommon.g.a.d unused = d.this.i = com.startapp.android.publish.adsCommon.g.a.d.DEFAULT;
                c.a(d.this.i, webView);
                c.a(webView);
                if (d.this.p) {
                    d.this.k.fireViewableChangeEvent();
                }
                if (MetaData.getInstance().isOmsdkEnabled()) {
                    d.this.e = null;
                    if (d.this.e != null) {
                        if (!(d.this.f28a == null || (a2 = d.this.f28a.a()) == null)) {
                            d.this.e.b(a2);
                        }
                        d.this.e.a(webView);
                        d.this.e.a();
                        com.b.a.a.a.b.a.a(d.this.e).a();
                    }
                }
            }
        }
    }
}
