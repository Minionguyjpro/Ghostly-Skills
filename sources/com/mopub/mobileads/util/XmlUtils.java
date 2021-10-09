package com.mopub.mobileads.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtils {

    public interface NodeProcessor<T> {
        T process(Node node);
    }

    private XmlUtils() {
    }

    public static Node getFirstMatchingChildNode(Node node, String str) {
        return getFirstMatchingChildNode(node, str, (String) null, (List<String>) null);
    }

    public static Node getFirstMatchingChildNode(Node node, String str, String str2, List<String> list) {
        List<Node> matchingChildNodes;
        if (node == null || str == null || (matchingChildNodes = getMatchingChildNodes(node, str, str2, list)) == null || matchingChildNodes.isEmpty()) {
            return null;
        }
        return matchingChildNodes.get(0);
    }

    public static List<Node> getMatchingChildNodes(Node node, String str) {
        return getMatchingChildNodes(node, str, (String) null, (List<String>) null);
    }

    public static List<Node> getMatchingChildNodes(Node node, String str, String str2, List<String> list) {
        if (node == null || str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeName().equals(str) && nodeMatchesAttributeFilter(item, str2, list)) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    public static boolean nodeMatchesAttributeFilter(Node node, String str, List<String> list) {
        Node namedItem;
        if (str == null || list == null) {
            return true;
        }
        NamedNodeMap attributes = node.getAttributes();
        if (attributes == null || (namedItem = attributes.getNamedItem(str)) == null || !list.contains(namedItem.getNodeValue())) {
            return false;
        }
        return true;
    }

    public static String getNodeValue(Node node) {
        if (node == null || node.getFirstChild() == null || node.getFirstChild().getNodeValue() == null) {
            return null;
        }
        return node.getFirstChild().getNodeValue().trim();
    }

    public static Integer getAttributeValueAsInt(Node node, String str) {
        if (!(node == null || str == null)) {
            try {
                return Integer.valueOf(Integer.parseInt(getAttributeValue(node, str)));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public static String getAttributeValue(Node node, String str) {
        Node namedItem;
        if (node == null || str == null || (namedItem = node.getAttributes().getNamedItem(str)) == null) {
            return null;
        }
        return namedItem.getNodeValue();
    }

    public static <T> List<T> getListFromDocument(Document document, String str, String str2, String str3, NodeProcessor<T> nodeProcessor) {
        NodeList elementsByTagName;
        List list;
        T process;
        ArrayList arrayList = new ArrayList();
        if (document == null || (elementsByTagName = document.getElementsByTagName(str)) == null) {
            return arrayList;
        }
        if (str3 == null) {
            list = null;
        } else {
            list = Arrays.asList(new String[]{str3});
        }
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            Node item = elementsByTagName.item(i);
            if (!(item == null || !nodeMatchesAttributeFilter(item, str2, list) || (process = nodeProcessor.process(item)) == null)) {
                arrayList.add(process);
            }
        }
        return arrayList;
    }

    public static <T> T getFirstMatchFromDocument(Document document, String str, String str2, String str3, NodeProcessor<T> nodeProcessor) {
        NodeList elementsByTagName;
        List list;
        T process;
        if (document == null || (elementsByTagName = document.getElementsByTagName(str)) == null) {
            return null;
        }
        if (str3 == null) {
            list = null;
        } else {
            list = Arrays.asList(new String[]{str3});
        }
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            Node item = elementsByTagName.item(i);
            if (item != null && nodeMatchesAttributeFilter(item, str2, list) && (process = nodeProcessor.process(item)) != null) {
                return process;
            }
        }
        return null;
    }

    public static String getFirstMatchingStringData(Document document, String str) {
        return getFirstMatchingStringData(document, str, (String) null, (String) null);
    }

    public static String getFirstMatchingStringData(Document document, String str, String str2, String str3) {
        return (String) getFirstMatchFromDocument(document, str, str2, str3, new NodeProcessor<String>() {
            public String process(Node node) {
                return XmlUtils.getNodeValue(node);
            }
        });
    }

    public static List<String> getStringDataAsList(Document document, String str) {
        return getStringDataAsList(document, str, (String) null, (String) null);
    }

    public static List<String> getStringDataAsList(Document document, String str, String str2, String str3) {
        return getListFromDocument(document, str, str2, str3, new NodeProcessor<String>() {
            public String process(Node node) {
                return XmlUtils.getNodeValue(node);
            }
        });
    }

    public static List<Node> getNodesWithElementAndAttribute(Document document, String str, String str2, String str3) {
        return getListFromDocument(document, str, str2, str3, new NodeProcessor<Node>() {
            public Node process(Node node) {
                return node;
            }
        });
    }
}
