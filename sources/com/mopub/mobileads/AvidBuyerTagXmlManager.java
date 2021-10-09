package com.mopub.mobileads;

import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.w3c.dom.Node;

class AvidBuyerTagXmlManager {
    private static final String AD_VERIFICATIONS = "AdVerifications";
    private static final String JAVA_SCRIPT_RESOURCE = "JavaScriptResource";
    private static final String VERIFICATION = "Verification";
    private final Node mAvidNode;

    AvidBuyerTagXmlManager(Node node) {
        Preconditions.checkNotNull(node);
        this.mAvidNode = node;
    }

    /* access modifiers changed from: package-private */
    public Set<String> getJavaScriptResources() {
        List<Node> matchingChildNodes;
        HashSet hashSet = new HashSet();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mAvidNode, "AdVerifications");
        if (firstMatchingChildNode == null || (matchingChildNodes = XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "Verification")) == null) {
            return hashSet;
        }
        for (Node firstMatchingChildNode2 : matchingChildNodes) {
            Node firstMatchingChildNode3 = XmlUtils.getFirstMatchingChildNode(firstMatchingChildNode2, JAVA_SCRIPT_RESOURCE);
            if (firstMatchingChildNode3 != null) {
                hashSet.add(XmlUtils.getNodeValue(firstMatchingChildNode3));
            }
        }
        return hashSet;
    }
}
