package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.fr;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class fo extends fp<JSONObject> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public fo(int i, String str, JSONObject jSONObject) {
        super(i, str, jSONObject == null ? null : jSONObject.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public JSONObject b(ft ftVar) throws fr {
        try {
            return new JSONObject(new String(ftVar.f915a, fq.a(ftVar.b, "utf-8")));
        } catch (UnsupportedEncodingException e) {
            fr.a aVar = fr.a.PARSE;
            throw new fr((Throwable) e);
        } catch (JSONException e2) {
            fr.a aVar2 = fr.a.PARSE;
            throw new fr((Throwable) e2);
        }
    }
}
