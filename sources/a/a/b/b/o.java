package a.a.b.b;

import a.a.d.b;
import a.a.d.d;
import a.a.d.f;

/* compiled from: StartAppSDK */
public class o {
    public d a(g gVar) {
        return gVar;
    }

    public f a(k kVar) {
        return kVar;
    }

    public b a(Class cls) {
        return new c(cls);
    }

    public String a(i iVar) {
        String obj = iVar.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("truenet.kotlin.jvm.functions.") ? obj.substring(29) : obj;
    }
}
