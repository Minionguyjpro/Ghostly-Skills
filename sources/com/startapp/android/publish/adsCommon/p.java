package com.startapp.android.publish.adsCommon;

import com.startapp.common.a.g;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/* compiled from: StartAppSDK */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private static String f281a = "XmlTools";

    public static String a(Node node) {
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
            newTransformer.setOutputProperty("method", "xml");
            newTransformer.setOutputProperty("indent", "no");
            newTransformer.setOutputProperty("encoding", "UTF-8");
            StringWriter stringWriter = new StringWriter();
            newTransformer.transform(new DOMSource(node), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            g.a(f281a, 6, "xmlDocumentToString", e);
            return null;
        }
    }

    public static Document a(String str) {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            newInstance.setIgnoringElementContentWhitespace(true);
            newInstance.setIgnoringComments(true);
            DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(str));
            return newDocumentBuilder.parse(inputSource);
        } catch (Exception e) {
            g.a(f281a, 6, "stringToDocument", e);
            return null;
        }
    }

    public static String b(Node node) {
        NodeList childNodes = node.getChildNodes();
        String str = null;
        for (int i = 0; i < childNodes.getLength(); i++) {
            str = ((CharacterData) childNodes.item(i)).getData().trim();
            if (str.length() != 0) {
                break;
            }
        }
        return str;
    }
}
