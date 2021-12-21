package com.mopub.mobileads;

import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;

public class VastExtensionParentXmlManager {
    private static final String EXTENSION = "Extension";
    private final Node mVastExtensionParentNode;

    VastExtensionParentXmlManager(Node node) {
        Preconditions.checkNotNull(node);
        this.mVastExtensionParentNode = node;
    }

    /* access modifiers changed from: package-private */
    public List<VastExtensionXmlManager> getVastExtensionXmlManagers() {
        ArrayList arrayList = new ArrayList();
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(this.mVastExtensionParentNode, EXTENSION);
        if (matchingChildNodes == null) {
            return arrayList;
        }
        for (Node vastExtensionXmlManager : matchingChildNodes) {
            arrayList.add(new VastExtensionXmlManager(vastExtensionXmlManager));
        }
        return arrayList;
    }
}
