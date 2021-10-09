package com.mopub.mobileads;

import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

public class VideoViewabilityTrackerXmlManager {
    public static final String PERCENT_VIEWABLE = "percentViewable";
    public static final String VIEWABLE_PLAYTIME = "viewablePlaytime";
    private final Node mVideoViewabilityNode;

    VideoViewabilityTrackerXmlManager(Node node) {
        Preconditions.checkNotNull(node);
        this.mVideoViewabilityNode = node;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0058 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Integer getViewablePlaytimeMS() {
        /*
            r6 = this;
            org.w3c.dom.Node r0 = r6.mVideoViewabilityNode
            java.lang.String r1 = "viewablePlaytime"
            java.lang.String r0 = com.mopub.mobileads.util.XmlUtils.getAttributeValue(r0, r1)
            r1 = 0
            if (r0 != 0) goto L_0x000c
            return r1
        L_0x000c:
            boolean r2 = com.mopub.mobileads.VastAbsoluteProgressTracker.isAbsoluteTracker(r0)
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x002d
            java.lang.Integer r0 = com.mopub.mobileads.VastAbsoluteProgressTracker.parseAbsoluteOffset(r0)     // Catch:{ NumberFormatException -> 0x0019 }
            goto L_0x004f
        L_0x0019:
            com.mopub.common.logging.MoPubLog$SdkLogEvent r2 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r3] = r0
            java.lang.String r0 = "Invalid VAST viewablePlaytime format for \"HH:MM:SS[.mmm]\": %s:"
            java.lang.String r0 = java.lang.String.format(r0, r4)
            r5[r3] = r0
            com.mopub.common.logging.MoPubLog.log(r2, r5)
            goto L_0x004e
        L_0x002d:
            float r2 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x003b }
            r5 = 1148846080(0x447a0000, float:1000.0)
            float r2 = r2 * r5
            int r2 = (int) r2     // Catch:{ NumberFormatException -> 0x003b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)     // Catch:{ NumberFormatException -> 0x003b }
            goto L_0x004f
        L_0x003b:
            com.mopub.common.logging.MoPubLog$SdkLogEvent r2 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r3] = r0
            java.lang.String r0 = "Invalid VAST viewablePlaytime format for \"SS[.mmm]\": %s:"
            java.lang.String r0 = java.lang.String.format(r0, r4)
            r5[r3] = r0
            com.mopub.common.logging.MoPubLog.log(r2, r5)
        L_0x004e:
            r0 = r1
        L_0x004f:
            if (r0 == 0) goto L_0x0059
            int r2 = r0.intValue()
            if (r2 >= 0) goto L_0x0058
            goto L_0x0059
        L_0x0058:
            return r0
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VideoViewabilityTrackerXmlManager.getViewablePlaytimeMS():java.lang.Integer");
    }

    /* access modifiers changed from: package-private */
    public Integer getPercentViewable() {
        Integer num;
        String attributeValue = XmlUtils.getAttributeValue(this.mVideoViewabilityNode, PERCENT_VIEWABLE);
        if (attributeValue == null) {
            return null;
        }
        try {
            num = Integer.valueOf((int) Float.parseFloat(attributeValue.replace("%", "")));
        } catch (NumberFormatException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format("Invalid VAST percentViewable format for \"d{1,3}%%\": %s:", new Object[]{attributeValue}));
            num = null;
        }
        if (num == null || num.intValue() < 0 || num.intValue() > 100) {
            return null;
        }
        return num;
    }

    /* access modifiers changed from: package-private */
    public String getVideoViewabilityTrackerUrl() {
        return XmlUtils.getNodeValue(this.mVideoViewabilityNode);
    }
}
