package com.mopub.mobileads;

import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

class VastMediaXmlManager {
    private static final String BITRATE = "bitrate";
    private static final String BITRATE_MAX = "maxBitrate";
    private static final String BITRATE_MIN = "minBitrate";
    private static final String DELIVERY = "delivery";
    private static final String HEIGHT = "height";
    private static final String VIDEO_TYPE = "type";
    private static final String WIDTH = "width";
    private final Node mMediaNode;

    VastMediaXmlManager(Node node) {
        Preconditions.checkNotNull(node, "mediaNode cannot be null");
        this.mMediaNode = node;
    }

    /* access modifiers changed from: package-private */
    public String getDelivery() {
        return XmlUtils.getAttributeValue(this.mMediaNode, DELIVERY);
    }

    /* access modifiers changed from: package-private */
    public Integer getWidth() {
        return XmlUtils.getAttributeValueAsInt(this.mMediaNode, "width");
    }

    /* access modifiers changed from: package-private */
    public Integer getHeight() {
        return XmlUtils.getAttributeValueAsInt(this.mMediaNode, "height");
    }

    /* access modifiers changed from: package-private */
    public String getType() {
        return XmlUtils.getAttributeValue(this.mMediaNode, "type");
    }

    /* access modifiers changed from: package-private */
    public String getMediaUrl() {
        return XmlUtils.getNodeValue(this.mMediaNode);
    }

    /* access modifiers changed from: package-private */
    public Integer getBitrate() {
        Integer attributeValueAsInt = XmlUtils.getAttributeValueAsInt(this.mMediaNode, BITRATE);
        if (attributeValueAsInt != null) {
            return attributeValueAsInt;
        }
        Integer attributeValueAsInt2 = XmlUtils.getAttributeValueAsInt(this.mMediaNode, BITRATE_MIN);
        Integer attributeValueAsInt3 = XmlUtils.getAttributeValueAsInt(this.mMediaNode, BITRATE_MAX);
        if (attributeValueAsInt2 == null || attributeValueAsInt3 == null) {
            return attributeValueAsInt2 != null ? attributeValueAsInt2 : attributeValueAsInt3;
        }
        return Integer.valueOf((attributeValueAsInt2.intValue() + attributeValueAsInt3.intValue()) / 2);
    }
}
