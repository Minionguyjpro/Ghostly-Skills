package com.startapp.android.publish.ads.video.c.b;

import android.content.Context;
import com.startapp.android.publish.ads.video.c.a.a;
import com.startapp.android.publish.ads.video.c.a.c;
import com.startapp.android.publish.ads.video.c.a.e;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.adsCommon.p;
import com.startapp.common.a.g;
import com.startapp.common.a.h;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/* compiled from: StartAppSDK */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final int f131a;
    private final int b;
    private e c;
    private StringBuilder d = new StringBuilder(500);
    private long e = -1;

    public b(int i, int i2) {
        this.f131a = i;
        this.b = i2;
    }

    public e a() {
        return this.c;
    }

    public a a(Context context, String str, c cVar) {
        this.c = null;
        this.e = System.currentTimeMillis();
        a a2 = a(context, str, 0);
        if (a2 == a.XMLParsingError) {
            g.a("VASTProcessor", 3, "processXml error " + a2);
            return a.XMLParsingError;
        }
        Document a3 = a(this.d.toString());
        if (a3 == null) {
            g.a("VASTProcessor", 3, "wrapMergedVastDocWithVasts error " + a2);
            return a.XMLParsingError;
        }
        e eVar = new e(a3);
        this.c = eVar;
        if (eVar.a(cVar)) {
            return a2;
        }
        g.a("VASTProcessor", 3, "validate error " + a2);
        return a2 == a.ErrorNone ? a.MediaNotSupported : a2;
    }

    public a a(Context context, String str, int i) {
        if (i >= this.f131a) {
            g.a("VASTProcessor", 6, "VAST wrapping exceeded max limit of " + this.f131a);
            return a.WrapperLimitReached;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.e;
        long j2 = currentTimeMillis - j;
        if (j2 <= ((long) this.b) || j <= 0) {
            Document b2 = b(str);
            if (b2 == null) {
                return a.XMLParsingError;
            }
            String a2 = a(b2);
            if (b2.getChildNodes().getLength() == 0 || b2.getChildNodes().item(0).getChildNodes().getLength() == 0 || a2 == null) {
                return a.WrapperNoReponse;
            }
            this.d.append(a2);
            NodeList elementsByTagName = b2.getElementsByTagName("VASTAdTagURI");
            if (elementsByTagName == null || elementsByTagName.getLength() == 0) {
                return a.ErrorNone;
            }
            String b3 = p.b(elementsByTagName.item(0));
            g.a("VASTProcessor", 3, "Wrapper URL: " + b3);
            try {
                h.a a3 = h.a(context, b3.replace(" ", "%20"), (Map<String, String>) null, k.a(context, "User-Agent", "-1"), false);
                if (a3 == null || a3.a() == null) {
                    return a.WrapperNoReponse;
                }
                return a(context, a3.a(), i + 1);
            } catch (Exception e2) {
                g.a("VASTProcessor", 6, "processXml network", e2);
                return a.GeneralWrapperError;
            }
        } else {
            g.a("VASTProcessor", 6, "VAST wrapping exceeded timeout " + j2);
            return a.WrapperTimeout;
        }
    }

    public static Document a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return p.a("<VASTS>" + str + "</VASTS>");
    }

    public static Document b(String str) {
        Document a2 = p.a(str);
        if (a2 != null) {
            a2.getDocumentElement().normalize();
        }
        return a2;
    }

    public static String a(Document document) {
        NodeList elementsByTagName;
        if (document == null || (elementsByTagName = document.getElementsByTagName("VAST")) == null || elementsByTagName.getLength() <= 0) {
            return null;
        }
        return p.a(elementsByTagName.item(0));
    }
}
