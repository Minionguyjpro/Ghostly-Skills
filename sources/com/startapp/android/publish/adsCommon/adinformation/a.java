package com.startapp.android.publish.adsCommon.adinformation;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.common.a.e;
import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class a implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private static a f203a = new a();
    private static Object b = new Object();
    private static final long serialVersionUID = 1;
    @f(a = true)
    private AdInformationConfig AdInformation = AdInformationConfig.a();
    private String adInformationMetadataUpdateVersion = AdsConstants.h;

    public a() {
        a().g();
    }

    public AdInformationConfig a() {
        return this.AdInformation;
    }

    public static void a(Context context) {
        a aVar = (a) e.a(context, "StartappAdInfoMetadata", a.class);
        a aVar2 = new a();
        if (aVar != null) {
            boolean a2 = i.a(aVar, aVar2);
            if (!aVar.f() && a2) {
                com.startapp.android.publish.adsCommon.f.f.a(context, d.METADATA_NULL, "AdInformationMetaData", "", "");
            }
            aVar.e();
            f203a = aVar;
        } else {
            f203a = aVar2;
        }
        b().a().g();
    }

    private void e() {
        this.AdInformation.k();
    }

    public static a b() {
        return f203a;
    }

    public static void a(Context context, a aVar) {
        synchronized (b) {
            aVar.adInformationMetadataUpdateVersion = AdsConstants.h;
            f203a = aVar;
            AdInformationConfig.a(b().AdInformation);
            b().a().g();
            e.a(context, "StartappAdInfoMetadata", (Serializable) aVar);
        }
    }

    public String c() {
        return this.AdInformation.b();
    }

    public String d() {
        return this.AdInformation.c();
    }

    private boolean f() {
        return !AdsConstants.h.equals(this.adInformationMetadataUpdateVersion);
    }
}
