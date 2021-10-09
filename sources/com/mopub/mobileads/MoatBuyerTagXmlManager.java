package com.mopub.mobileads;

import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.w3c.dom.Node;

class MoatBuyerTagXmlManager {
    private static final String ID = "id";
    private static final String VIEWABLE_IMPRESSION = "ViewableImpression";
    private final List<Node> mMoatVerificationNodes;

    MoatBuyerTagXmlManager(List<Node> list) {
        Preconditions.checkNotNull(list);
        this.mMoatVerificationNodes = list;
    }

    /* access modifiers changed from: package-private */
    public Set<String> getImpressionPixelsXml() {
        String viewableImpressionXml;
        HashSet hashSet = new HashSet();
        for (Node next : this.mMoatVerificationNodes) {
            if (!(next == null || (viewableImpressionXml = getViewableImpressionXml(XmlUtils.getFirstMatchingChildNode(next, VIEWABLE_IMPRESSION))) == null)) {
                hashSet.add(viewableImpressionXml);
            }
        }
        return hashSet;
    }

    private String getViewableImpressionXml(Node node) {
        if (node == null || !node.hasAttributes()) {
            return null;
        }
        String attributeValue = XmlUtils.getAttributeValue(node, "id");
        String nodeValue = XmlUtils.getNodeValue(node);
        return String.format(Locale.US, "<ViewableImpression id=\"%s\"><![CDATA[%s]]</ViewableImpression>", new Object[]{attributeValue, nodeValue});
    }
}
