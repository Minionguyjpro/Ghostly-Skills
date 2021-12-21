package com.mopub.mobileads;

import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;

public class VastIconXmlManager {
    public static final String DURATION = "duration";
    public static final String HEIGHT = "height";
    public static final String ICON_CLICKS = "IconClicks";
    public static final String ICON_CLICK_THROUGH = "IconClickThrough";
    public static final String ICON_CLICK_TRACKING = "IconClickTracking";
    public static final String ICON_VIEW_TRACKING = "IconViewTracking";
    public static final String OFFSET = "offset";
    public static final String WIDTH = "width";
    private final Node mIconNode;
    private final VastResourceXmlManager mResourceXmlManager;

    VastIconXmlManager(Node node) {
        Preconditions.checkNotNull(node);
        this.mIconNode = node;
        this.mResourceXmlManager = new VastResourceXmlManager(node);
    }

    /* access modifiers changed from: package-private */
    public Integer getWidth() {
        return XmlUtils.getAttributeValueAsInt(this.mIconNode, "width");
    }

    /* access modifiers changed from: package-private */
    public Integer getHeight() {
        return XmlUtils.getAttributeValueAsInt(this.mIconNode, "height");
    }

    /* access modifiers changed from: package-private */
    public Integer getOffsetMS() {
        String attributeValue = XmlUtils.getAttributeValue(this.mIconNode, OFFSET);
        try {
            return VastAbsoluteProgressTracker.parseAbsoluteOffset(attributeValue);
        } catch (NumberFormatException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format("Invalid VAST icon offset format: %s:", new Object[]{attributeValue}));
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Integer getDurationMS() {
        String attributeValue = XmlUtils.getAttributeValue(this.mIconNode, DURATION);
        try {
            return VastAbsoluteProgressTracker.parseAbsoluteOffset(attributeValue);
        } catch (NumberFormatException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format("Invalid VAST icon duration format: %s:", new Object[]{attributeValue}));
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public VastResourceXmlManager getResourceXmlManager() {
        return this.mResourceXmlManager;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getClickTrackingUris() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mIconNode, ICON_CLICKS);
        ArrayList arrayList = new ArrayList();
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node nodeValue : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, ICON_CLICK_TRACKING)) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (nodeValue2 != null) {
                arrayList.add(new VastTracker(nodeValue2));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public String getClickThroughUri() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mIconNode, ICON_CLICKS);
        if (firstMatchingChildNode == null) {
            return null;
        }
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(firstMatchingChildNode, ICON_CLICK_THROUGH));
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getViewTrackingUris() {
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(this.mIconNode, ICON_VIEW_TRACKING);
        ArrayList arrayList = new ArrayList();
        for (Node nodeValue : matchingChildNodes) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (nodeValue2 != null) {
                arrayList.add(new VastTracker(nodeValue2));
            }
        }
        return arrayList;
    }
}
