package com.startapp.android.publish.adsCommon.g.a;

import android.app.Activity;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.common.a.c;
import com.startapp.common.a.g;
import java.net.URLDecoder;
import java.util.Map;

/* compiled from: StartAppSDK */
public abstract class a implements b {
    private static final String TAG = "BaseMraidController";
    protected C0005a openListener;

    /* renamed from: com.startapp.android.publish.adsCommon.g.a.a$a  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public interface C0005a {
        boolean a(String str);
    }

    public abstract void close();

    public abstract boolean isFeatureSupported(String str);

    public abstract void setOrientationProperties(Map<String, String> map);

    public abstract void useCustomClose(String str);

    public a(C0005a aVar) {
        this.openListener = aVar;
    }

    public void resize() {
        g.a(TAG, 3, "resize");
    }

    public void expand(String str) {
        g.a(TAG, 3, "expand");
    }

    public boolean open(String str) {
        g.a(TAG, 3, "open " + str);
        try {
            String trim = URLDecoder.decode(str, "UTF-8").trim();
            if (trim.startsWith("sms")) {
                return openSMS(trim);
            }
            if (trim.startsWith("tel")) {
                return openTel(trim);
            }
            return this.openListener.a(trim);
        } catch (Exception e) {
            g.a(TAG, 6, e.getMessage());
            return this.openListener.a(str);
        }
    }

    /* access modifiers changed from: protected */
    public void applyOrientationProperties(Activity activity, com.startapp.android.publish.adsCommon.g.c.a aVar) {
        try {
            int i = 0;
            int i2 = activity.getResources().getConfiguration().orientation == 1 ? 1 : 0;
            if (aVar.b == 0) {
                i = 1;
            } else if (aVar.b != 1) {
                i = aVar.f251a ? -1 : i2;
            }
            c.a(activity, i);
        } catch (Exception e) {
            f.a(activity, d.EXCEPTION, "BaseMraidController.applyOrientationProperties", e.getMessage(), "");
        }
    }

    public void setResizeProperties(Map<String, String> map) {
        g.a(TAG, 3, "setResizeProperties " + map);
    }

    public void createCalendarEvent(String str) {
        g.a(TAG, 3, "createCalendarEvent " + str);
        isFeatureSupported("calendar");
    }

    public void playVideo(String str) {
        g.a(TAG, 3, "playVideo " + str);
        isFeatureSupported("inlineVideo");
    }

    public void storePicture(String str) {
        g.a(TAG, 3, "storePicture " + str);
        isFeatureSupported("storePicture");
    }

    public boolean openSMS(String str) {
        g.a(TAG, 3, "openSMS " + str);
        isFeatureSupported("sms");
        return true;
    }

    public boolean openTel(String str) {
        g.a(TAG, 3, "openTel " + str);
        isFeatureSupported("tel");
        return true;
    }
}
