package com.mopub.mobileads;

import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Node;

class VastCompanionAdXmlManager {
    private static final String AD_SLOT_ID = "adSlotID";
    private static final String COMPANION_CLICK_THROUGH = "CompanionClickThrough";
    private static final String COMPANION_CLICK_TRACKING = "CompanionClickTracking";
    private static final String CREATIVE_VIEW = "creativeView";
    private static final String EVENT = "event";
    private static final String HEIGHT = "height";
    private static final String TRACKING_EVENTS = "TrackingEvents";
    private static final String VIDEO_TRACKER = "Tracking";
    private static final String WIDTH = "width";
    private final Node mCompanionNode;
    private final VastResourceXmlManager mResourceXmlManager;

    VastCompanionAdXmlManager(Node node) {
        Preconditions.checkNotNull(node, "companionNode cannot be null");
        this.mCompanionNode = node;
        this.mResourceXmlManager = new VastResourceXmlManager(node);
    }

    /* access modifiers changed from: package-private */
    public Integer getWidth() {
        return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "width");
    }

    /* access modifiers changed from: package-private */
    public Integer getHeight() {
        return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "height");
    }

    /* access modifiers changed from: package-private */
    public String getAdSlotId() {
        return XmlUtils.getAttributeValue(this.mCompanionNode, AD_SLOT_ID);
    }

    /* access modifiers changed from: package-private */
    public VastResourceXmlManager getResourceXmlManager() {
        return this.mResourceXmlManager;
    }

    /* access modifiers changed from: package-private */
    public String getClickThroughUrl() {
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, COMPANION_CLICK_THROUGH));
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getClickTrackers() {
        ArrayList arrayList = new ArrayList();
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(this.mCompanionNode, COMPANION_CLICK_TRACKING);
        if (matchingChildNodes == null) {
            return arrayList;
        }
        for (Node nodeValue : matchingChildNodes) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (!TextUtils.isEmpty(nodeValue2)) {
                arrayList.add(new VastTracker(nodeValue2));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getCompanionCreativeViewTrackers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, TRACKING_EVENTS);
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node nodeValue : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, VIDEO_TRACKER, EVENT, Collections.singletonList(CREATIVE_VIEW))) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (nodeValue2 != null) {
                arrayList.add(new VastTracker(nodeValue2));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public boolean hasResources() {
        return !TextUtils.isEmpty(this.mResourceXmlManager.getStaticResource()) || !TextUtils.isEmpty(this.mResourceXmlManager.getHTMLResource()) || !TextUtils.isEmpty(this.mResourceXmlManager.getIFrameResource());
    }
}
