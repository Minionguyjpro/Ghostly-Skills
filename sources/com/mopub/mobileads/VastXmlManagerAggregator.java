package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.mobileads.VastResource;
import com.mopub.network.Networking;
import com.mopub.network.TrackingRequest;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class VastXmlManagerAggregator extends AsyncTask<String, Void, VastVideoConfig> {
    public static final String ADS_BY_AD_SLOT_ID = "adsBy";
    private static final int BITRATE_THRESHOLD_HIGH = 1500;
    private static final int BITRATE_THRESHOLD_LOW = 700;
    static final int MAX_TIMES_TO_FOLLOW_VAST_REDIRECT = 10;
    private static final String MIME_TYPE_3GPP = "video/3gpp";
    private static final String MIME_TYPE_MP4 = "video/mp4";
    private static final int MINIMUM_COMPANION_AD_HEIGHT = 250;
    private static final int MINIMUM_COMPANION_AD_WIDTH = 300;
    private static final String MOPUB = "MoPub";
    private static final List<String> VIDEO_MIME_TYPES = Arrays.asList(new String[]{MIME_TYPE_MP4, MIME_TYPE_3GPP});
    private final Context mContext;
    private final double mScreenAspectRatio;
    private final int mScreenWidthDp;
    private int mTimesFollowedVastRedirect;
    private final WeakReference<VastXmlManagerAggregatorListener> mVastXmlManagerAggregatorListener;

    enum CompanionOrientation {
        LANDSCAPE,
        PORTRAIT
    }

    interface VastXmlManagerAggregatorListener {
        void onAggregationComplete(VastVideoConfig vastVideoConfig);
    }

    VastXmlManagerAggregator(VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener, double d, int i, Context context) {
        Preconditions.checkNotNull(vastXmlManagerAggregatorListener);
        Preconditions.checkNotNull(context);
        this.mVastXmlManagerAggregatorListener = new WeakReference<>(vastXmlManagerAggregatorListener);
        this.mScreenAspectRatio = d;
        this.mScreenWidthDp = i;
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        Networking.getUserAgent(this.mContext);
    }

    /* access modifiers changed from: protected */
    public VastVideoConfig doInBackground(String... strArr) {
        if (!(strArr == null || strArr.length == 0 || strArr[0] == null)) {
            try {
                return evaluateVastXmlManager(strArr[0], new ArrayList());
            } catch (Exception e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Unable to generate VastVideoConfig.", e);
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(VastVideoConfig vastVideoConfig) {
        VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener = (VastXmlManagerAggregatorListener) this.mVastXmlManagerAggregatorListener.get();
        if (vastXmlManagerAggregatorListener != null) {
            vastXmlManagerAggregatorListener.onAggregationComplete(vastVideoConfig);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener = (VastXmlManagerAggregatorListener) this.mVastXmlManagerAggregatorListener.get();
        if (vastXmlManagerAggregatorListener != null) {
            vastXmlManagerAggregatorListener.onAggregationComplete((VastVideoConfig) null);
        }
    }

    /* access modifiers changed from: package-private */
    public VastVideoConfig evaluateVastXmlManager(String str, List<VastTracker> list) {
        VastVideoConfig evaluateVastXmlManager;
        VastVideoConfig evaluateInLineXmlManager;
        Preconditions.checkNotNull(str, "vastXml cannot be null");
        Preconditions.checkNotNull(list, "errorTrackers cannot be null");
        VastXmlManager vastXmlManager = new VastXmlManager();
        try {
            vastXmlManager.parseVastXml(str);
            List<VastAdXmlManager> adXmlManagers = vastXmlManager.getAdXmlManagers();
            if (fireErrorTrackerIfNoAds(adXmlManagers, vastXmlManager, this.mContext)) {
                return null;
            }
            for (VastAdXmlManager next : adXmlManagers) {
                if (isValidSequenceNumber(next.getSequence())) {
                    VastInLineXmlManager inLineXmlManager = next.getInLineXmlManager();
                    if (inLineXmlManager == null || (evaluateInLineXmlManager = evaluateInLineXmlManager(inLineXmlManager, list)) == null) {
                        VastWrapperXmlManager wrapperXmlManager = next.getWrapperXmlManager();
                        if (wrapperXmlManager != null) {
                            ArrayList arrayList = new ArrayList(list);
                            arrayList.addAll(wrapperXmlManager.getErrorTrackers());
                            String evaluateWrapperRedirect = evaluateWrapperRedirect(wrapperXmlManager, arrayList);
                            if (!(evaluateWrapperRedirect == null || (evaluateVastXmlManager = evaluateVastXmlManager(evaluateWrapperRedirect, arrayList)) == null)) {
                                evaluateVastXmlManager.addImpressionTrackers(wrapperXmlManager.getImpressionTrackers());
                                for (VastLinearXmlManager populateLinearTrackersAndIcon : wrapperXmlManager.getLinearXmlManagers()) {
                                    populateLinearTrackersAndIcon(populateLinearTrackersAndIcon, evaluateVastXmlManager);
                                }
                                populateVideoViewabilityTracker(wrapperXmlManager, evaluateVastXmlManager);
                                populateViewabilityMetadata(wrapperXmlManager, evaluateVastXmlManager);
                                List<VastCompanionAdXmlManager> companionAdXmlManagers = wrapperXmlManager.getCompanionAdXmlManagers();
                                if (!evaluateVastXmlManager.hasCompanionAd()) {
                                    evaluateVastXmlManager.setVastCompanionAd(getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.LANDSCAPE), getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.PORTRAIT));
                                } else {
                                    VastCompanionAdConfig vastCompanionAd = evaluateVastXmlManager.getVastCompanionAd(2);
                                    VastCompanionAdConfig vastCompanionAd2 = evaluateVastXmlManager.getVastCompanionAd(1);
                                    if (!(vastCompanionAd == null || vastCompanionAd2 == null)) {
                                        for (VastCompanionAdXmlManager next2 : companionAdXmlManagers) {
                                            if (!next2.hasResources()) {
                                                vastCompanionAd.addClickTrackers(next2.getClickTrackers());
                                                vastCompanionAd.addCreativeViewTrackers(next2.getCompanionCreativeViewTrackers());
                                                vastCompanionAd2.addClickTrackers(next2.getClickTrackers());
                                                vastCompanionAd2.addCreativeViewTrackers(next2.getCompanionCreativeViewTrackers());
                                            }
                                        }
                                    }
                                }
                                populateMoPubCustomElements(vastXmlManager, evaluateVastXmlManager);
                                return evaluateVastXmlManager;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        populateMoPubCustomElements(vastXmlManager, evaluateInLineXmlManager);
                        return evaluateInLineXmlManager;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Failed to parse VAST XML", e);
            TrackingRequest.makeVastTrackingHttpRequest(list, VastErrorCode.XML_PARSING_ERROR, (Integer) null, (String) null, this.mContext);
            return null;
        }
    }

    private VastVideoConfig evaluateInLineXmlManager(VastInLineXmlManager vastInLineXmlManager, List<VastTracker> list) {
        Preconditions.checkNotNull(vastInLineXmlManager);
        Preconditions.checkNotNull(list);
        for (VastLinearXmlManager next : vastInLineXmlManager.getLinearXmlManagers()) {
            String bestMediaFileUrl = getBestMediaFileUrl(next.getMediaXmlManagers());
            if (bestMediaFileUrl != null) {
                VastVideoConfig vastVideoConfig = new VastVideoConfig();
                vastVideoConfig.addImpressionTrackers(vastInLineXmlManager.getImpressionTrackers());
                populateLinearTrackersAndIcon(next, vastVideoConfig);
                vastVideoConfig.setClickThroughUrl(next.getClickThroughUrl());
                vastVideoConfig.setNetworkMediaFileUrl(bestMediaFileUrl);
                List<VastCompanionAdXmlManager> companionAdXmlManagers = vastInLineXmlManager.getCompanionAdXmlManagers();
                vastVideoConfig.setVastCompanionAd(getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.LANDSCAPE), getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.PORTRAIT));
                list.addAll(vastInLineXmlManager.getErrorTrackers());
                vastVideoConfig.addErrorTrackers(list);
                populateVideoViewabilityTracker(vastInLineXmlManager, vastVideoConfig);
                populateViewabilityMetadata(vastInLineXmlManager, vastVideoConfig);
                return vastVideoConfig;
            }
        }
        return null;
    }

    private void populateVideoViewabilityTracker(VastBaseInLineWrapperXmlManager vastBaseInLineWrapperXmlManager, VastVideoConfig vastVideoConfig) {
        VastExtensionParentXmlManager vastExtensionParentXmlManager;
        Preconditions.checkNotNull(vastBaseInLineWrapperXmlManager);
        Preconditions.checkNotNull(vastVideoConfig);
        if (vastVideoConfig.getVideoViewabilityTracker() == null && (vastExtensionParentXmlManager = vastBaseInLineWrapperXmlManager.getVastExtensionParentXmlManager()) != null) {
            for (VastExtensionXmlManager next : vastExtensionParentXmlManager.getVastExtensionXmlManagers()) {
                if ("MoPub".equals(next.getType())) {
                    vastVideoConfig.setVideoViewabilityTracker(next.getVideoViewabilityTracker());
                    return;
                }
            }
        }
    }

    private void populateViewabilityMetadata(VastBaseInLineWrapperXmlManager vastBaseInLineWrapperXmlManager, VastVideoConfig vastVideoConfig) {
        VastExtensionParentXmlManager vastExtensionParentXmlManager = vastBaseInLineWrapperXmlManager.getVastExtensionParentXmlManager();
        if (vastExtensionParentXmlManager != null) {
            for (VastExtensionXmlManager next : vastExtensionParentXmlManager.getVastExtensionXmlManagers()) {
                if (next != null) {
                    vastVideoConfig.addAvidJavascriptResources(next.getAvidJavaScriptResources());
                    vastVideoConfig.addMoatImpressionPixels(next.getMoatImpressionPixels());
                }
            }
        }
    }

    private String evaluateWrapperRedirect(VastWrapperXmlManager vastWrapperXmlManager, List<VastTracker> list) {
        String vastAdTagURI = vastWrapperXmlManager.getVastAdTagURI();
        if (vastAdTagURI == null) {
            return null;
        }
        try {
            return followVastRedirect(vastAdTagURI);
        } catch (Exception e) {
            MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Failed to follow VAST redirect", e);
            if (list.isEmpty()) {
                return null;
            }
            TrackingRequest.makeVastTrackingHttpRequest(list, VastErrorCode.WRAPPER_TIMEOUT, (Integer) null, (String) null, this.mContext);
            return null;
        }
    }

    private void populateLinearTrackersAndIcon(VastLinearXmlManager vastLinearXmlManager, VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastLinearXmlManager, "linearXmlManager cannot be null");
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        vastVideoConfig.addAbsoluteTrackers(vastLinearXmlManager.getAbsoluteProgressTrackers());
        vastVideoConfig.addFractionalTrackers(vastLinearXmlManager.getFractionalProgressTrackers());
        vastVideoConfig.addPauseTrackers(vastLinearXmlManager.getPauseTrackers());
        vastVideoConfig.addResumeTrackers(vastLinearXmlManager.getResumeTrackers());
        vastVideoConfig.addCompleteTrackers(vastLinearXmlManager.getVideoCompleteTrackers());
        vastVideoConfig.addCloseTrackers(vastLinearXmlManager.getVideoCloseTrackers());
        vastVideoConfig.addSkipTrackers(vastLinearXmlManager.getVideoSkipTrackers());
        vastVideoConfig.addClickTrackers(vastLinearXmlManager.getClickTrackers());
        if (vastVideoConfig.getSkipOffsetString() == null) {
            vastVideoConfig.setSkipOffset(vastLinearXmlManager.getSkipOffset());
        }
        if (vastVideoConfig.getVastIconConfig() == null) {
            vastVideoConfig.setVastIconConfig(getBestIcon(vastLinearXmlManager.getIconXmlManagers()));
        }
    }

    private void populateMoPubCustomElements(VastXmlManager vastXmlManager, VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastXmlManager, "xmlManager cannot be null");
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        vastVideoConfig.addImpressionTrackers(vastXmlManager.getMoPubImpressionTrackers());
        if (vastVideoConfig.getCustomCtaText() == null) {
            vastVideoConfig.setCustomCtaText(vastXmlManager.getCustomCtaText());
        }
        if (vastVideoConfig.getCustomSkipText() == null) {
            vastVideoConfig.setCustomSkipText(vastXmlManager.getCustomSkipText());
        }
        if (vastVideoConfig.getCustomCloseIconUrl() == null) {
            vastVideoConfig.setCustomCloseIconUrl(vastXmlManager.getCustomCloseIconUrl());
        }
    }

    private boolean fireErrorTrackerIfNoAds(List<VastAdXmlManager> list, VastXmlManager vastXmlManager, Context context) {
        if (!list.isEmpty() || vastXmlManager.getErrorTracker() == null) {
            return false;
        }
        TrackingRequest.makeVastTrackingHttpRequest(Collections.singletonList(vastXmlManager.getErrorTracker()), this.mTimesFollowedVastRedirect > 0 ? VastErrorCode.NO_ADS_VAST_RESPONSE : VastErrorCode.UNDEFINED_ERROR, (Integer) null, (String) null, context);
        return true;
    }

    /* access modifiers changed from: package-private */
    public String getBestMediaFileUrl(List<VastMediaXmlManager> list) {
        Preconditions.checkNotNull(list, "managers cannot be null");
        Iterator it = new ArrayList(list).iterator();
        double d = Double.NEGATIVE_INFINITY;
        String str = null;
        while (it.hasNext()) {
            VastMediaXmlManager vastMediaXmlManager = (VastMediaXmlManager) it.next();
            String type = vastMediaXmlManager.getType();
            String mediaUrl = vastMediaXmlManager.getMediaUrl();
            if (!VIDEO_MIME_TYPES.contains(type) || mediaUrl == null) {
                it.remove();
            } else {
                Integer width = vastMediaXmlManager.getWidth();
                Integer height = vastMediaXmlManager.getHeight();
                Integer bitrate = vastMediaXmlManager.getBitrate();
                if (width != null && width.intValue() > 0 && height != null && height.intValue() > 0) {
                    double calculateFitness = calculateFitness(width.intValue(), height.intValue(), bitrate, type);
                    if (calculateFitness > d) {
                        d = calculateFitness;
                        str = mediaUrl;
                    }
                }
            }
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public VastCompanionAdConfig getBestCompanionAd(List<VastCompanionAdXmlManager> list, CompanionOrientation companionOrientation) {
        int i;
        ArrayList arrayList;
        VastResource.Type[] typeArr;
        double d;
        List<VastCompanionAdXmlManager> list2 = list;
        CompanionOrientation companionOrientation2 = companionOrientation;
        Preconditions.checkNotNull(list2, "managers cannot be null");
        Preconditions.checkNotNull(companionOrientation2, "orientation cannot be null");
        ArrayList<VastCompanionAdXmlManager> arrayList2 = new ArrayList<>(list2);
        VastResource.Type[] values = VastResource.Type.values();
        int length = values.length;
        double d2 = Double.NEGATIVE_INFINITY;
        int i2 = 0;
        VastCompanionAdXmlManager vastCompanionAdXmlManager = null;
        VastResource vastResource = null;
        Point point = null;
        while (i2 < length) {
            VastResource.Type type = values[i2];
            for (VastCompanionAdXmlManager vastCompanionAdXmlManager2 : arrayList2) {
                Integer width = vastCompanionAdXmlManager2.getWidth();
                Integer height = vastCompanionAdXmlManager2.getHeight();
                if (width != null) {
                    typeArr = values;
                    if (width.intValue() >= MINIMUM_COMPANION_AD_WIDTH && height != null && height.intValue() >= 250) {
                        Point scaledDimensions = getScaledDimensions(width.intValue(), height.intValue(), type, companionOrientation2);
                        arrayList = arrayList2;
                        i = length;
                        VastResource fromVastResourceXmlManager = VastResource.fromVastResourceXmlManager(vastCompanionAdXmlManager2.getResourceXmlManager(), type, scaledDimensions.x, scaledDimensions.y);
                        if (fromVastResourceXmlManager != null) {
                            if ((CompanionOrientation.LANDSCAPE != companionOrientation2 || this.mScreenAspectRatio >= 1.0d) && (CompanionOrientation.PORTRAIT != companionOrientation2 || this.mScreenAspectRatio <= 1.0d)) {
                                d = calculateFitness(width.intValue(), height.intValue(), (Integer) null, (String) null);
                            } else {
                                d = calculateFitness(height.intValue(), width.intValue(), (Integer) null, (String) null);
                            }
                            if (d > d2) {
                                point = scaledDimensions;
                                vastResource = fromVastResourceXmlManager;
                                d2 = d;
                                vastCompanionAdXmlManager = vastCompanionAdXmlManager2;
                            }
                        }
                        values = typeArr;
                        arrayList2 = arrayList;
                        length = i;
                    }
                } else {
                    typeArr = values;
                }
                arrayList = arrayList2;
                i = length;
                values = typeArr;
                arrayList2 = arrayList;
                length = i;
            }
            VastResource.Type[] typeArr2 = values;
            ArrayList arrayList3 = arrayList2;
            int i3 = length;
            if (vastCompanionAdXmlManager != null) {
                break;
            }
            i2++;
            values = typeArr2;
            arrayList2 = arrayList3;
            length = i3;
        }
        VastResource vastResource2 = vastResource;
        if (vastCompanionAdXmlManager != null) {
            return new VastCompanionAdConfig(point.x, point.y, vastResource2, vastCompanionAdXmlManager.getClickThroughUrl(), vastCompanionAdXmlManager.getClickTrackers(), vastCompanionAdXmlManager.getCompanionCreativeViewTrackers());
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Point getScaledDimensions(int i, int i2, VastResource.Type type, CompanionOrientation companionOrientation) {
        int i3;
        int i4;
        Point point = new Point(i, i2);
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        int dipsToIntPixels = Dips.dipsToIntPixels((float) i, this.mContext);
        int dipsToIntPixels2 = Dips.dipsToIntPixels((float) i2, this.mContext);
        if (CompanionOrientation.LANDSCAPE == companionOrientation) {
            i3 = Math.max(width, height);
            i4 = Math.min(width, height);
        } else {
            i3 = Math.min(width, height);
            i4 = Math.max(width, height);
        }
        if (dipsToIntPixels <= i3 - 16 && dipsToIntPixels2 <= i4 - 16) {
            return point;
        }
        Point point2 = new Point();
        if (VastResource.Type.HTML_RESOURCE == type) {
            point2.x = Math.min(i3, dipsToIntPixels);
            point2.y = Math.min(i4, dipsToIntPixels2);
        } else {
            float f = (float) dipsToIntPixels;
            float f2 = f / ((float) i3);
            float f3 = (float) dipsToIntPixels2;
            float f4 = f3 / ((float) i4);
            if (f2 >= f4) {
                point2.x = i3;
                point2.y = (int) (f3 / f2);
            } else {
                point2.x = (int) (f / f4);
                point2.y = i4;
            }
        }
        point2.x -= 16;
        point2.y -= 16;
        if (point2.x < 0 || point2.y < 0) {
            return point;
        }
        point2.x = Dips.pixelsToIntDips((float) point2.x, this.mContext);
        point2.y = Dips.pixelsToIntDips((float) point2.y, this.mContext);
        return point2;
    }

    /* access modifiers changed from: package-private */
    public VastIconConfig getBestIcon(List<VastIconXmlManager> list) {
        VastResource fromVastResourceXmlManager;
        List<VastIconXmlManager> list2 = list;
        Preconditions.checkNotNull(list2, "managers cannot be null");
        ArrayList<VastIconXmlManager> arrayList = new ArrayList<>(list2);
        for (VastResource.Type type : VastResource.Type.values()) {
            for (VastIconXmlManager vastIconXmlManager : arrayList) {
                Integer width = vastIconXmlManager.getWidth();
                Integer height = vastIconXmlManager.getHeight();
                if (width != null && width.intValue() > 0 && width.intValue() <= MINIMUM_COMPANION_AD_WIDTH && height != null && height.intValue() > 0 && height.intValue() <= MINIMUM_COMPANION_AD_WIDTH && (fromVastResourceXmlManager = VastResource.fromVastResourceXmlManager(vastIconXmlManager.getResourceXmlManager(), type, width.intValue(), height.intValue())) != null) {
                    return new VastIconConfig(vastIconXmlManager.getWidth().intValue(), vastIconXmlManager.getHeight().intValue(), vastIconXmlManager.getOffsetMS(), vastIconXmlManager.getDurationMS(), fromVastResourceXmlManager, vastIconXmlManager.getClickTrackingUris(), vastIconXmlManager.getClickThroughUri(), vastIconXmlManager.getViewTrackingUris());
                }
            }
        }
        return null;
    }

    private double calculateFitness(int i, int i2, Integer num, String str) {
        double calculateScreenFitnessFactor = calculateScreenFitnessFactor(i, i2);
        return calculateFormatFitnessFactor(str) * (1.0d / ((calculateScreenFitnessFactor + 1.0d) + calculateBitrateFitnessFactor(num)));
    }

    private double calculateBitrateFitnessFactor(Integer num) {
        int intValue = (num == null || num.intValue() < 0) ? 0 : num.intValue();
        if (BITRATE_THRESHOLD_LOW > intValue || intValue > 1500) {
            return Math.min((double) (((float) Math.abs(700 - intValue)) / 700.0f), (double) (((float) Math.abs(1500 - intValue)) / 1500.0f));
        }
        return 0.0d;
    }

    private double calculateScreenFitnessFactor(int i, int i2) {
        double d = (double) i;
        double d2 = (double) i2;
        Double.isNaN(d);
        Double.isNaN(d2);
        double abs = Math.abs(this.mScreenAspectRatio - (d / d2));
        int i3 = this.mScreenWidthDp;
        double abs2 = (double) Math.abs((i3 - i) / i3);
        Double.isNaN(abs2);
        return abs + abs2;
    }

    private double calculateFormatFitnessFactor(String str) {
        if (str == null) {
            str = "";
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1664118616) {
            if (hashCode == 1331848029 && str.equals(MIME_TYPE_MP4)) {
                c = 0;
            }
        } else if (str.equals(MIME_TYPE_3GPP)) {
            c = 1;
        }
        return c != 0 ? 1.0d : 1.5d;
    }

    static boolean isValidSequenceNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            if (Integer.parseInt(str) < 2) {
                return true;
            }
            return false;
        } catch (NumberFormatException unused) {
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String followVastRedirect(java.lang.String r5) throws java.io.IOException {
        /*
            r4 = this;
            com.mopub.common.Preconditions.checkNotNull(r5)
            int r0 = r4.mTimesFollowedVastRedirect
            r1 = 0
            r2 = 10
            if (r0 >= r2) goto L_0x003c
            int r0 = r0 + 1
            r4.mTimesFollowedVastRedirect = r0
            java.net.HttpURLConnection r5 = com.mopub.common.MoPubHttpUrlConnection.getHttpUrlConnection(r5)     // Catch:{ all -> 0x002f }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x002a }
            java.io.InputStream r2 = r5.getInputStream()     // Catch:{ all -> 0x002a }
            r0.<init>(r2)     // Catch:{ all -> 0x002a }
            java.lang.String r1 = com.mopub.common.util.Strings.fromStream(r0)     // Catch:{ all -> 0x0028 }
            com.mopub.common.util.Streams.closeStream(r0)
            if (r5 == 0) goto L_0x0027
            r5.disconnect()
        L_0x0027:
            return r1
        L_0x0028:
            r1 = move-exception
            goto L_0x0033
        L_0x002a:
            r0 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0033
        L_0x002f:
            r5 = move-exception
            r0 = r1
            r1 = r5
            r5 = r0
        L_0x0033:
            com.mopub.common.util.Streams.closeStream(r0)
            if (r5 == 0) goto L_0x003b
            r5.disconnect()
        L_0x003b:
            throw r1
        L_0x003c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VastXmlManagerAggregator.followVastRedirect(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setTimesFollowedVastRedirect(int i) {
        this.mTimesFollowedVastRedirect = i;
    }
}
