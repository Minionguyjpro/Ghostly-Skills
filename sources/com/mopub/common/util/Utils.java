package com.mopub.common.util;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import com.mopub.common.Preconditions;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {
    private static final AtomicLong sNextGeneratedId = new AtomicLong(1);

    public static boolean bitMaskContainsFlag(int i, int i2) {
        return (i & i2) != 0;
    }

    public static String sha1(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bytes = str.getBytes("UTF-8");
            instance.update(bytes, 0, bytes.length);
            byte[] digest = instance.digest();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X", new Object[]{Byte.valueOf(digest[i])}));
            }
            return sb.toString().toLowerCase(Locale.US);
        } catch (Exception unused) {
            return "";
        }
    }

    public static long generateUniqueId() {
        long j;
        long j2;
        do {
            j = sNextGeneratedId.get();
            j2 = 1;
            long j3 = j + 1;
            if (j3 <= 9223372036854775806L) {
                j2 = j3;
            }
        } while (!sNextGeneratedId.compareAndSet(j, j2));
        return j;
    }

    public static void hideNavigationBar(Activity activity) {
        Preconditions.checkNotNull(activity);
        Window window = activity.getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            hideNavigation(decorView);
            decorView.setOnSystemUiVisibilityChangeListener(createHideNavigationListener(decorView));
        }
    }

    static View.OnSystemUiVisibilityChangeListener createHideNavigationListener(final View view) {
        Preconditions.checkNotNull(view);
        return new View.OnSystemUiVisibilityChangeListener() {
            public void onSystemUiVisibilityChange(int i) {
                if ((i & 2) == 0) {
                    Utils.hideNavigation(view);
                }
            }
        };
    }

    static void hideNavigation(View view) {
        Preconditions.checkNotNull(view);
        view.setSystemUiVisibility(4870);
    }
}
