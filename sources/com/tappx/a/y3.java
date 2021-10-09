package com.tappx.a;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import androidx.recyclerview.widget.RecyclerView;

public class y3 {

    /* renamed from: a  reason: collision with root package name */
    private static SparseArray<String> f629a = new SparseArray<>();
    private static int b = RecyclerView.UNDEFINED_DURATION;

    public static void a(Intent intent, String str) {
        if (str.length() > 262144) {
            intent.putExtra("aavc_5orHkZouKDEIkayJNWLC", a(str));
        } else {
            intent.putExtra("ipc_aavc_bkN4RpcYmIsYuf4eZQOt", str);
        }
    }

    public static String a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras.containsKey("ipc_aavc_bkN4RpcYmIsYuf4eZQOt")) {
            return extras.getString("ipc_aavc_bkN4RpcYmIsYuf4eZQOt");
        }
        return f629a.get(extras.getInt("aavc_5orHkZouKDEIkayJNWLC"));
    }

    private static int a(String str) {
        int a2 = a();
        f629a.put(a2, str);
        return a2;
    }

    public static synchronized int a() {
        int i;
        synchronized (y3.class) {
            do {
                i = b + 1;
                b = i;
            } while (i == 0);
        }
        return i;
    }
}
