package com.b.a.a.a.h.a;

import android.os.AsyncTask;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONObject;

public abstract class b extends AsyncTask<Object, Void, String> {

    /* renamed from: a  reason: collision with root package name */
    private a f1009a;
    protected final C0042b d;

    public interface a {
        void a(b bVar);
    }

    /* renamed from: com.b.a.a.a.h.a.b$b  reason: collision with other inner class name */
    public interface C0042b {
        JSONObject a();

        void a(JSONObject jSONObject);
    }

    public b(C0042b bVar) {
        this.d = bVar;
    }

    public void a(a aVar) {
        this.f1009a = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        a aVar = this.f1009a;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    public void a(ThreadPoolExecutor threadPoolExecutor) {
        executeOnExecutor(threadPoolExecutor, new Object[0]);
    }
}
