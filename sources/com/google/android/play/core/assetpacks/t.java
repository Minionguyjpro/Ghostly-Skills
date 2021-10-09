package com.google.android.play.core.assetpacks;

import android.os.ParcelFileDescriptor;
import com.google.android.play.core.tasks.Task;
import java.util.List;
import java.util.Map;

interface t {
    void b(List<String> list);

    Task<List<String>> c(Map<String, Long> map);

    void e(int i, String str, String str2, int i2);

    void f(int i, String str);

    void g(int i);

    Task<ParcelFileDescriptor> h(int i, String str, String str2, int i2);

    void j();
}
