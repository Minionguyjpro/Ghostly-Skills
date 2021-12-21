package com.mopub.mobileads;

import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;

abstract class VastBaseInLineWrapperXmlManager {
    private static final String COMPANION = "Companion";
    private static final String COMPANION_ADS = "CompanionAds";
    private static final String CREATIVE = "Creative";
    private static final String CREATIVES = "Creatives";
    private static final String ERROR = "Error";
    private static final String EXTENSIONS = "Extensions";
    private static final String IMPRESSION_TRACKER = "Impression";
    private static final String LINEAR = "Linear";
    protected final Node mNode;

    VastBaseInLineWrapperXmlManager(Node node) {
        Preconditions.checkNotNull(node);
        this.mNode = node;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getImpressionTrackers() {
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(this.mNode, IMPRESSION_TRACKER);
        ArrayList arrayList = new ArrayList();
        for (Node nodeValue : matchingChildNodes) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (!TextUtils.isEmpty(nodeValue2)) {
                arrayList.add(new VastTracker(nodeValue2));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getErrorTrackers() {
        ArrayList arrayList = new ArrayList();
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(this.mNode, ERROR);
        if (matchingChildNodes == null) {
            return arrayList;
        }
        for (Node nodeValue : matchingChildNodes) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (!TextUtils.isEmpty(nodeValue2)) {
                arrayList.add(new VastTracker(nodeValue2, true));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<VastLinearXmlManager> getLinearXmlManagers() {
        List<Node> matchingChildNodes;
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mNode, CREATIVES);
        if (firstMatchingChildNode == null || (matchingChildNodes = XmlUtils.getMatchingChildNodes(firstMatchingChildNode, CREATIVE)) == null) {
            return arrayList;
        }
        for (Node firstMatchingChildNode2 : matchingChildNodes) {
            Node firstMatchingChildNode3 = XmlUtils.getFirstMatchingChildNode(firstMatchingChildNode2, LINEAR);
            if (firstMatchingChildNode3 != null) {
                arrayList.add(new VastLinearXmlManager(firstMatchingChildNode3));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<VastCompanionAdXmlManager> getCompanionAdXmlManagers() {
        List<Node> matchingChildNodes;
        List<Node> matchingChildNodes2;
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mNode, CREATIVES);
        if (firstMatchingChildNode == null || (matchingChildNodes = XmlUtils.getMatchingChildNodes(firstMatchingChildNode, CREATIVE)) == null) {
            return arrayList;
        }
        for (Node firstMatchingChildNode2 : matchingChildNodes) {
            Node firstMatchingChildNode3 = XmlUtils.getFirstMatchingChildNode(firstMatchingChildNode2, COMPANION_ADS);
            if (!(firstMatchingChildNode3 == null || (matchingChildNodes2 = XmlUtils.getMatchingChildNodes(firstMatchingChildNode3, COMPANION)) == null)) {
                for (Node vastCompanionAdXmlManager : matchingChildNodes2) {
                    arrayList.add(new VastCompanionAdXmlManager(vastCompanionAdXmlManager));
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public VastExtensionParentXmlManager getVastExtensionParentXmlManager() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mNode, EXTENSIONS);
        if (firstMatchingChildNode == null) {
            return null;
        }
        return new VastExtensionParentXmlManager(firstMatchingChildNode);
    }
}
