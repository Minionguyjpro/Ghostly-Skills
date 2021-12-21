package com.mopub.mobileads;

import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Node;

class VastLinearXmlManager {
    private static final String CLICK_THROUGH = "ClickThrough";
    private static final String CLICK_TRACKER = "ClickTracking";
    private static final String CLOSE = "close";
    private static final String CLOSE_LINEAR = "closeLinear";
    private static final String COMPLETE = "complete";
    private static final String CREATIVE_VIEW = "creativeView";
    private static final int CREATIVE_VIEW_TRACKER_THRESHOLD = 0;
    private static final String EVENT = "event";
    private static final float FIRST_QUARTER_MARKER = 0.25f;
    private static final String FIRST_QUARTILE = "firstQuartile";
    public static final String ICON = "Icon";
    public static final String ICONS = "Icons";
    private static final String MEDIA_FILE = "MediaFile";
    private static final String MEDIA_FILES = "MediaFiles";
    private static final String MIDPOINT = "midpoint";
    private static final float MID_POINT_MARKER = 0.5f;
    private static final String OFFSET = "offset";
    private static final String PAUSE = "pause";
    private static final String PROGRESS = "progress";
    private static final String RESUME = "resume";
    private static final String SKIP = "skip";
    private static final String SKIP_OFFSET = "skipoffset";
    private static final String START = "start";
    private static final int START_TRACKER_THRESHOLD = 0;
    private static final float THIRD_QUARTER_MARKER = 0.75f;
    private static final String THIRD_QUARTILE = "thirdQuartile";
    private static final String TRACKING_EVENTS = "TrackingEvents";
    private static final String VIDEO_CLICKS = "VideoClicks";
    private static final String VIDEO_TRACKER = "Tracking";
    private final Node mLinearNode;

    VastLinearXmlManager(Node node) {
        Preconditions.checkNotNull(node);
        this.mLinearNode = node;
    }

    /* access modifiers changed from: package-private */
    public List<VastFractionalProgressTracker> getFractionalProgressTrackers() {
        ArrayList arrayList = new ArrayList();
        addQuartileTrackerWithFraction(arrayList, getVideoTrackersByAttribute(FIRST_QUARTILE), FIRST_QUARTER_MARKER);
        addQuartileTrackerWithFraction(arrayList, getVideoTrackersByAttribute(MIDPOINT), MID_POINT_MARKER);
        addQuartileTrackerWithFraction(arrayList, getVideoTrackersByAttribute(THIRD_QUARTILE), THIRD_QUARTER_MARKER);
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, TRACKING_EVENTS);
        if (firstMatchingChildNode != null) {
            for (Node next : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, VIDEO_TRACKER, EVENT, Collections.singletonList(PROGRESS))) {
                String attributeValue = XmlUtils.getAttributeValue(next, "offset");
                if (attributeValue != null) {
                    String trim = attributeValue.trim();
                    if (VastFractionalProgressTrackerTwo.Companion.isPercentageTracker(trim)) {
                        String nodeValue = XmlUtils.getNodeValue(next);
                        try {
                            float parseFloat = Float.parseFloat(trim.replace("%", "")) / 100.0f;
                            if (parseFloat >= 0.0f) {
                                arrayList.add(new VastFractionalProgressTracker(nodeValue, parseFloat));
                            }
                        } catch (NumberFormatException unused) {
                            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format("Failed to parse VAST progress tracker %s", new Object[]{trim}));
                        }
                    }
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<VastAbsoluteProgressTracker> getAbsoluteProgressTrackers() {
        ArrayList arrayList = new ArrayList();
        for (String vastAbsoluteProgressTracker : getVideoTrackersByAttribute(START)) {
            arrayList.add(new VastAbsoluteProgressTracker(vastAbsoluteProgressTracker, 0));
        }
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, TRACKING_EVENTS);
        if (firstMatchingChildNode != null) {
            for (Node next : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, VIDEO_TRACKER, EVENT, Collections.singletonList(PROGRESS))) {
                String attributeValue = XmlUtils.getAttributeValue(next, "offset");
                if (attributeValue != null) {
                    String trim = attributeValue.trim();
                    if (VastAbsoluteProgressTracker.isAbsoluteTracker(trim)) {
                        String nodeValue = XmlUtils.getNodeValue(next);
                        try {
                            Integer parseAbsoluteOffset = VastAbsoluteProgressTracker.parseAbsoluteOffset(trim);
                            if (parseAbsoluteOffset != null && parseAbsoluteOffset.intValue() >= 0) {
                                arrayList.add(new VastAbsoluteProgressTracker(nodeValue, parseAbsoluteOffset.intValue()));
                            }
                        } catch (NumberFormatException unused) {
                            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format("Failed to parse VAST progress tracker %s", new Object[]{trim}));
                        }
                    }
                }
            }
            for (Node nodeValue2 : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, VIDEO_TRACKER, EVENT, Collections.singletonList(CREATIVE_VIEW))) {
                String nodeValue3 = XmlUtils.getNodeValue(nodeValue2);
                if (nodeValue3 != null) {
                    arrayList.add(new VastAbsoluteProgressTracker(nodeValue3, 0));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getVideoCompleteTrackers() {
        return getVideoTrackersByAttributeAsVastTrackers(COMPLETE);
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getPauseTrackers() {
        List<String> videoTrackersByAttribute = getVideoTrackersByAttribute(PAUSE);
        ArrayList arrayList = new ArrayList();
        for (String vastTracker : videoTrackersByAttribute) {
            arrayList.add(new VastTracker(vastTracker, true));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getResumeTrackers() {
        List<String> videoTrackersByAttribute = getVideoTrackersByAttribute(RESUME);
        ArrayList arrayList = new ArrayList();
        for (String vastTracker : videoTrackersByAttribute) {
            arrayList.add(new VastTracker(vastTracker, true));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getVideoCloseTrackers() {
        List<VastTracker> videoTrackersByAttributeAsVastTrackers = getVideoTrackersByAttributeAsVastTrackers(CLOSE);
        videoTrackersByAttributeAsVastTrackers.addAll(getVideoTrackersByAttributeAsVastTrackers(CLOSE_LINEAR));
        return videoTrackersByAttributeAsVastTrackers;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getVideoSkipTrackers() {
        return getVideoTrackersByAttributeAsVastTrackers(SKIP);
    }

    /* access modifiers changed from: package-private */
    public String getClickThroughUrl() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, VIDEO_CLICKS);
        if (firstMatchingChildNode == null) {
            return null;
        }
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(firstMatchingChildNode, CLICK_THROUGH));
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getClickTrackers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, VIDEO_CLICKS);
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node nodeValue : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, CLICK_TRACKER)) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (nodeValue2 != null) {
                arrayList.add(new VastTracker(nodeValue2));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public String getSkipOffset() {
        String attributeValue = XmlUtils.getAttributeValue(this.mLinearNode, SKIP_OFFSET);
        if (attributeValue != null && !attributeValue.trim().isEmpty()) {
            return attributeValue.trim();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public List<VastMediaXmlManager> getMediaXmlManagers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, MEDIA_FILES);
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node vastMediaXmlManager : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, MEDIA_FILE)) {
            arrayList.add(new VastMediaXmlManager(vastMediaXmlManager));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<VastIconXmlManager> getIconXmlManagers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, ICONS);
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node vastIconXmlManager : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, ICON)) {
            arrayList.add(new VastIconXmlManager(vastIconXmlManager));
        }
        return arrayList;
    }

    private List<VastTracker> getVideoTrackersByAttributeAsVastTrackers(String str) {
        List<String> videoTrackersByAttribute = getVideoTrackersByAttribute(str);
        ArrayList arrayList = new ArrayList(videoTrackersByAttribute.size());
        for (String vastTracker : videoTrackersByAttribute) {
            arrayList.add(new VastTracker(vastTracker));
        }
        return arrayList;
    }

    private List<String> getVideoTrackersByAttribute(String str) {
        Preconditions.checkNotNull(str);
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, TRACKING_EVENTS);
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node nodeValue : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, VIDEO_TRACKER, EVENT, Collections.singletonList(str))) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (nodeValue2 != null) {
                arrayList.add(nodeValue2);
            }
        }
        return arrayList;
    }

    private void addQuartileTrackerWithFraction(List<VastFractionalProgressTracker> list, List<String> list2, float f) {
        Preconditions.checkNotNull(list, "trackers cannot be null");
        Preconditions.checkNotNull(list2, "urls cannot be null");
        for (String vastFractionalProgressTracker : list2) {
            list.add(new VastFractionalProgressTracker(vastFractionalProgressTracker, f));
        }
    }
}
