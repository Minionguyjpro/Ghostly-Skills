package com.startapp.android.publish.ads.video.c.a;

import android.text.TextUtils;
import com.mopub.mobileads.VastExtensionXmlManager;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.mobileads.VastResourceXmlManager;
import com.startapp.android.publish.ads.video.c.a.a.a;
import com.startapp.android.publish.ads.video.c.a.a.b;
import com.startapp.android.publish.ads.video.c.a.a.c;
import com.startapp.android.publish.ads.video.c.a.a.d;
import com.startapp.android.publish.adsCommon.p;
import com.startapp.android.publish.omsdk.AdVerification;
import com.startapp.android.publish.omsdk.VerificationDetails;
import com.startapp.common.a.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* compiled from: StartAppSDK */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static String f129a = "VASTModel";
    private HashMap<b, List<c>> b;
    private List<b> c;
    private int d;
    private com.startapp.android.publish.ads.video.c.a.a.e e;
    private List<String> f;
    private List<String> g;
    private int h;
    private b i = null;
    private List<a> j;
    private AdVerification k;

    public e(Document document) {
        this.d = c(document);
        this.b = a(document);
        this.c = b(document);
        this.e = d(document);
        this.f = e(document);
        this.g = f(document);
        this.h = g(document);
        this.j = h(document);
        this.k = i(document);
    }

    public boolean a(c cVar) {
        b a2 = com.startapp.android.publish.ads.video.c.b.a.a(this, cVar);
        this.i = a2;
        return a2 != null;
    }

    public HashMap<b, List<c>> a() {
        return this.b;
    }

    public List<b> b() {
        return this.c;
    }

    public com.startapp.android.publish.ads.video.c.a.a.e c() {
        return this.e;
    }

    public List<String> d() {
        return this.f;
    }

    public List<String> e() {
        return this.g;
    }

    public int f() {
        return this.h;
    }

    public b g() {
        return this.i;
    }

    public AdVerification h() {
        return this.k;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:8|9|10|11|(3:15|16|(1:18)(2:19|20))|21|22|(1:24)(1:25)|26|36) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0072 */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0078 A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f A[Catch:{ Exception -> 0x00b1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<com.startapp.android.publish.ads.video.c.a.b, java.util.List<com.startapp.android.publish.ads.video.c.a.a.c>> a(org.w3c.dom.Document r9) {
        /*
            r8 = this;
            java.lang.String r0 = "%"
            java.lang.String r1 = f129a
            r2 = 3
            java.lang.String r3 = "getTrackingUrls"
            com.startapp.common.a.g.a((java.lang.String) r1, (int) r2, (java.lang.String) r3)
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            javax.xml.xpath.XPathFactory r2 = javax.xml.xpath.XPathFactory.newInstance()
            javax.xml.xpath.XPath r2 = r2.newXPath()
            java.lang.String r3 = "/VASTS/VAST/Ad/InLine/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/InLine/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking"
            javax.xml.namespace.QName r4 = javax.xml.xpath.XPathConstants.NODESET     // Catch:{ Exception -> 0x00b1 }
            java.lang.Object r9 = r2.evaluate(r3, r9, r4)     // Catch:{ Exception -> 0x00b1 }
            org.w3c.dom.NodeList r9 = (org.w3c.dom.NodeList) r9     // Catch:{ Exception -> 0x00b1 }
            if (r9 == 0) goto L_0x00b0
            r2 = 0
        L_0x0024:
            int r3 = r9.getLength()     // Catch:{ Exception -> 0x00b1 }
            if (r2 >= r3) goto L_0x00b0
            org.w3c.dom.Node r3 = r9.item(r2)     // Catch:{ Exception -> 0x00b1 }
            org.w3c.dom.NamedNodeMap r4 = r3.getAttributes()     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = "event"
            org.w3c.dom.Node r5 = r4.getNamedItem(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = r5.getNodeValue()     // Catch:{ Exception -> 0x00b1 }
            com.startapp.android.publish.ads.video.c.a.b r5 = com.startapp.android.publish.ads.video.c.a.b.valueOf(r5)     // Catch:{ IllegalArgumentException -> 0x0090 }
            java.lang.String r3 = com.startapp.android.publish.adsCommon.p.b(r3)     // Catch:{ Exception -> 0x00b1 }
            r6 = -1
            java.lang.String r7 = "offset"
            org.w3c.dom.Node r4 = r4.getNamedItem(r7)     // Catch:{ Exception -> 0x00b1 }
            if (r4 == 0) goto L_0x0072
            java.lang.String r4 = r4.getNodeValue()     // Catch:{ Exception -> 0x00b1 }
            if (r4 == 0) goto L_0x0072
            boolean r7 = r4.contains(r0)     // Catch:{ Exception -> 0x0072 }
            if (r7 == 0) goto L_0x006b
            java.lang.String r7 = ""
            java.lang.String r4 = r4.replace(r0, r7)     // Catch:{ Exception -> 0x0072 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x0072 }
            int r7 = r8.d     // Catch:{ Exception -> 0x0072 }
            int r7 = r7 / 100
            int r7 = r7 * r4
            r6 = r7
            goto L_0x0072
        L_0x006b:
            int r4 = a((java.lang.String) r4)     // Catch:{ Exception -> 0x0072 }
            int r4 = r4 * 1000
            r6 = r4
        L_0x0072:
            boolean r4 = r1.containsKey(r5)     // Catch:{ Exception -> 0x00b1 }
            if (r4 == 0) goto L_0x007f
            java.lang.Object r4 = r1.get(r5)     // Catch:{ Exception -> 0x00b1 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ Exception -> 0x00b1 }
            goto L_0x0087
        L_0x007f:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x00b1 }
            r4.<init>()     // Catch:{ Exception -> 0x00b1 }
            r1.put(r5, r4)     // Catch:{ Exception -> 0x00b1 }
        L_0x0087:
            com.startapp.android.publish.ads.video.c.a.a.c r5 = new com.startapp.android.publish.ads.video.c.a.a.c     // Catch:{ Exception -> 0x00b1 }
            r5.<init>(r3, r6)     // Catch:{ Exception -> 0x00b1 }
            r4.add(r5)     // Catch:{ Exception -> 0x00b1 }
            goto L_0x00ac
        L_0x0090:
            java.lang.String r3 = f129a     // Catch:{ Exception -> 0x00b1 }
            r4 = 5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b1 }
            r6.<init>()     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r7 = "Event:"
            r6.append(r7)     // Catch:{ Exception -> 0x00b1 }
            r6.append(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = " is not valid. Skipping it."
            r6.append(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x00b1 }
            com.startapp.common.a.g.a((java.lang.String) r3, (int) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00b1 }
        L_0x00ac:
            int r2 = r2 + 1
            goto L_0x0024
        L_0x00b0:
            return r1
        L_0x00b1:
            r9 = move-exception
            java.lang.String r0 = f129a
            r1 = 6
            java.lang.String r2 = r9.getMessage()
            com.startapp.common.a.g.a(r0, r1, r2, r9)
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.video.c.a.e.a(org.w3c.dom.Document):java.util.HashMap");
    }

    private List<b> b(Document document) {
        String str;
        Integer num;
        String str2;
        Integer num2;
        Integer num3;
        String str3;
        Boolean bool;
        Boolean bool2;
        String str4;
        g.a(f129a, 3, "getMediaFiles");
        ArrayList arrayList = new ArrayList();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//MediaFile", document, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                    b bVar = new b();
                    Node item = nodeList.item(i2);
                    NamedNodeMap attributes = item.getAttributes();
                    Node namedItem = attributes.getNamedItem("apiFramework");
                    if (namedItem == null) {
                        str = null;
                    } else {
                        str = namedItem.getNodeValue();
                    }
                    bVar.e(str);
                    Node namedItem2 = attributes.getNamedItem("bitrate");
                    if (namedItem2 == null) {
                        num = null;
                    } else {
                        num = Integer.valueOf(namedItem2.getNodeValue());
                    }
                    bVar.a(num);
                    Node namedItem3 = attributes.getNamedItem("delivery");
                    if (namedItem3 == null) {
                        str2 = null;
                    } else {
                        str2 = namedItem3.getNodeValue();
                    }
                    bVar.c(str2);
                    Node namedItem4 = attributes.getNamedItem("height");
                    if (namedItem4 == null) {
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(namedItem4.getNodeValue());
                    }
                    bVar.c(num2);
                    Node namedItem5 = attributes.getNamedItem("width");
                    if (namedItem5 == null) {
                        num3 = null;
                    } else {
                        num3 = Integer.valueOf(namedItem5.getNodeValue());
                    }
                    bVar.b(num3);
                    Node namedItem6 = attributes.getNamedItem("id");
                    if (namedItem6 == null) {
                        str3 = null;
                    } else {
                        str3 = namedItem6.getNodeValue();
                    }
                    bVar.b(str3);
                    Node namedItem7 = attributes.getNamedItem("maintainAspectRatio");
                    if (namedItem7 == null) {
                        bool = null;
                    } else {
                        bool = Boolean.valueOf(namedItem7.getNodeValue());
                    }
                    bVar.b(bool);
                    Node namedItem8 = attributes.getNamedItem("scalable");
                    if (namedItem8 == null) {
                        bool2 = null;
                    } else {
                        bool2 = Boolean.valueOf(namedItem8.getNodeValue());
                    }
                    bVar.a(bool2);
                    Node namedItem9 = attributes.getNamedItem("type");
                    if (namedItem9 == null) {
                        str4 = null;
                    } else {
                        str4 = namedItem9.getNodeValue();
                    }
                    bVar.d(str4);
                    bVar.a(p.b(item));
                    if (bVar.f()) {
                        arrayList.add(bVar);
                    }
                }
            }
            return arrayList;
        } catch (Exception e2) {
            g.a(f129a, 6, e2.getMessage(), e2);
            return null;
        }
    }

    private int c(Document document) {
        g.a(f129a, 3, "getDuration");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//Duration", document, XPathConstants.NODESET);
            if (nodeList == null || nodeList.getLength() <= 0) {
                return Integer.MAX_VALUE;
            }
            return a(p.b(nodeList.item(0)));
        } catch (Exception e2) {
            g.a(f129a, 6, e2.getMessage(), e2);
            return Integer.MAX_VALUE;
        }
    }

    private com.startapp.android.publish.ads.video.c.a.a.e d(Document document) {
        g.a(f129a, 3, "getVideoClicks");
        com.startapp.android.publish.ads.video.c.a.a.e eVar = new com.startapp.android.publish.ads.video.c.a.a.e();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//VideoClicks", document, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                    NodeList childNodes = nodeList.item(i2).getChildNodes();
                    for (int i3 = 0; i3 < childNodes.getLength(); i3++) {
                        Node item = childNodes.item(i3);
                        String nodeName = item.getNodeName();
                        String b2 = p.b(item);
                        if (nodeName.equalsIgnoreCase("ClickTracking")) {
                            eVar.b().add(b2);
                        } else if (nodeName.equalsIgnoreCase("ClickThrough")) {
                            eVar.a(b2);
                        } else if (nodeName.equalsIgnoreCase("CustomClick")) {
                            eVar.c().add(b2);
                        }
                    }
                }
            }
            return eVar;
        } catch (Exception e2) {
            g.a(f129a, 6, e2.getMessage(), e2);
            return null;
        }
    }

    private List<String> e(Document document) {
        g.a(f129a, 3, "getImpressions");
        return a(document, "//Impression");
    }

    private List<String> f(Document document) {
        g.a(f129a, 3, "getErrorUrl");
        return a(document, "//Error");
    }

    private int g(Document document) {
        g.a(f129a, 3, "getSkipOffset");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//Linear", document, XPathConstants.NODESET);
            if (nodeList == null) {
                return Integer.MAX_VALUE;
            }
            int i2 = 0;
            while (i2 < nodeList.getLength()) {
                try {
                    if (nodeList.item(i2).getAttributes().getNamedItem("skipoffset") != null) {
                        return a(nodeList.item(i2).getAttributes().getNamedItem("skipoffset").getNodeValue());
                    }
                    i2++;
                } catch (Exception e2) {
                    g.a(f129a, 6, e2.getMessage(), e2);
                }
            }
            return Integer.MAX_VALUE;
        } catch (Exception e3) {
            g.a(f129a, 6, e3.getMessage(), e3);
            return Integer.MAX_VALUE;
        }
    }

    private List<a> h(Document document) {
        String str;
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        Integer num5;
        Integer num6;
        String str2;
        Integer num7;
        String str3;
        g.a(f129a, 3, "getIcons");
        ArrayList arrayList = new ArrayList();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//Icon", document, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                    a aVar = new a();
                    Node item = nodeList.item(i2);
                    NamedNodeMap attributes = item.getAttributes();
                    Node namedItem = attributes.getNamedItem("program");
                    if (namedItem == null) {
                        str = null;
                    } else {
                        str = namedItem.getNodeValue();
                    }
                    aVar.a(str);
                    Node namedItem2 = attributes.getNamedItem("width");
                    if (namedItem2 == null) {
                        num = null;
                    } else {
                        num = Integer.valueOf(namedItem2.getNodeValue());
                    }
                    aVar.a(num);
                    Node namedItem3 = attributes.getNamedItem("height");
                    if (namedItem3 == null) {
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(namedItem3.getNodeValue());
                    }
                    aVar.b(num2);
                    Node namedItem4 = attributes.getNamedItem("xPosition");
                    if (namedItem4 == null) {
                        num3 = null;
                    } else {
                        num3 = Integer.valueOf(namedItem4.getNodeValue());
                    }
                    aVar.c(num3);
                    Node namedItem5 = attributes.getNamedItem("yPosition");
                    if (namedItem5 == null) {
                        num4 = null;
                    } else {
                        num4 = Integer.valueOf(namedItem5.getNodeValue());
                    }
                    aVar.d(num4);
                    Node namedItem6 = attributes.getNamedItem(VastIconXmlManager.DURATION);
                    if (namedItem6 == null) {
                        num5 = null;
                    } else {
                        num5 = Integer.valueOf(namedItem6.getNodeValue());
                    }
                    aVar.e(num5);
                    Node namedItem7 = attributes.getNamedItem(VastIconXmlManager.OFFSET);
                    if (namedItem7 == null) {
                        num6 = null;
                    } else {
                        num6 = Integer.valueOf(namedItem7.getNodeValue());
                    }
                    aVar.f(num6);
                    Node namedItem8 = attributes.getNamedItem("apiFramework");
                    if (namedItem8 == null) {
                        str2 = null;
                    } else {
                        str2 = namedItem8.getNodeValue();
                    }
                    aVar.b(str2);
                    Node namedItem9 = attributes.getNamedItem("pxratio");
                    if (namedItem9 == null) {
                        num7 = null;
                    } else {
                        num7 = Integer.valueOf(namedItem9.getNodeValue());
                    }
                    aVar.g(num7);
                    NodeList childNodes = item.getChildNodes();
                    for (int i3 = 0; i3 < childNodes.getLength(); i3++) {
                        Node item2 = childNodes.item(i3);
                        String nodeName = item2.getNodeName();
                        String b2 = p.b(item2);
                        if (nodeName.equalsIgnoreCase(VastIconXmlManager.ICON_CLICKS)) {
                            NodeList childNodes2 = item.getChildNodes();
                            for (int i4 = 0; i4 < childNodes2.getLength(); i4++) {
                                Node item3 = childNodes.item(i3);
                                String nodeName2 = item3.getNodeName();
                                String b3 = p.b(item3);
                                if (nodeName2.equalsIgnoreCase("ClickThrough")) {
                                    aVar.c(b3);
                                } else if (nodeName2.equalsIgnoreCase(VastIconXmlManager.ICON_VIEW_TRACKING)) {
                                    aVar.g().add(b3);
                                }
                            }
                        } else if (nodeName.equalsIgnoreCase("ClickTracking")) {
                            aVar.f().add(b2);
                        } else if (nodeName.equalsIgnoreCase(VastResourceXmlManager.STATIC_RESOURCE)) {
                            d dVar = new d();
                            dVar.b(b2);
                            Node namedItem10 = item.getAttributes().getNamedItem(VastResourceXmlManager.CREATIVE_TYPE);
                            if (namedItem10 == null) {
                                str3 = null;
                            } else {
                                str3 = namedItem10.getNodeValue();
                            }
                            dVar.a(str3);
                            if (dVar.a()) {
                                aVar.e().add(dVar);
                            }
                        }
                    }
                    if (aVar.h()) {
                        arrayList.add(aVar);
                    }
                }
            }
            return arrayList;
        } catch (Exception e2) {
            g.a(f129a, 6, e2.getMessage(), e2);
            return null;
        }
    }

    private List<String> a(Document document, String str) {
        g.a(f129a, 3, "getListFromXPath");
        ArrayList arrayList = new ArrayList();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(str, document, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                    arrayList.add(p.b(nodeList.item(i2)));
                }
            }
            return arrayList;
        } catch (Exception e2) {
            g.a(f129a, 6, e2.getMessage(), e2);
            return null;
        }
    }

    private AdVerification i(Document document) {
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//AdVerifications", document, XPathConstants.NODESET);
            if (nodeList == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                NodeList childNodes = nodeList.item(i2).getChildNodes();
                for (int i3 = 0; i3 < childNodes.getLength(); i3++) {
                    Node item = childNodes.item(i3);
                    if (item.getNodeName().equalsIgnoreCase(VastExtensionXmlManager.VERIFICATION)) {
                        String str = "";
                        NamedNodeMap attributes = item.getAttributes();
                        String nodeValue = (attributes == null || attributes.getNamedItem(VastExtensionXmlManager.VENDOR) == null) ? null : attributes.getNamedItem(VastExtensionXmlManager.VENDOR).getNodeValue();
                        NodeList childNodes2 = item.getChildNodes();
                        String str2 = null;
                        String str3 = null;
                        for (int i4 = 0; i4 < childNodes2.getLength(); i4++) {
                            Node item2 = childNodes2.item(i4);
                            if (item2.getNodeName().equalsIgnoreCase("JavaScriptResource")) {
                                Node namedItem = item2.getAttributes().getNamedItem("apiFramework");
                                if (namedItem != null) {
                                    str = namedItem.getNodeValue();
                                }
                                str2 = p.b(item2);
                            } else if (item2.getNodeName().equalsIgnoreCase("VerificationParameters")) {
                                str3 = p.b(item2);
                            }
                        }
                        if (!TextUtils.isEmpty(nodeValue) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && "omid".equalsIgnoreCase(str)) {
                            arrayList.add(new VerificationDetails(nodeValue, str2, str3));
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                return new AdVerification((VerificationDetails[]) arrayList.toArray(new VerificationDetails[arrayList.size()]));
            }
            return null;
        } catch (Exception e2) {
            g.a(f129a, 6, e2.getMessage(), e2);
            return null;
        }
    }

    private static int a(String str) {
        String[] split = str.split(":");
        return (Integer.parseInt(split[0]) * 3600) + (Integer.parseInt(split[1]) * 60) + Integer.parseInt(split[2]);
    }
}
