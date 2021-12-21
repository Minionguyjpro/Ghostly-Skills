package com.startapp.android.publish.ads.video.tracking;

import com.startapp.android.publish.ads.video.c.a.a.c;
import com.startapp.android.publish.ads.video.c.a.b;
import com.startapp.android.publish.ads.video.c.a.e;
import com.startapp.common.c.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* compiled from: StartAppSDK */
public class VideoTrackingDetails implements Serializable {
    private static final long serialVersionUID = 1;
    @f(b = AbsoluteTrackingLink.class)
    private AbsoluteTrackingLink[] absoluteTrackingUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] creativeViewUrls;
    @f(b = FractionTrackingLink.class)
    private FractionTrackingLink[] fractionTrackingUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] impressionUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] inlineErrorTrackingUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] soundMuteUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] soundUnmuteUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoClickTrackingUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoClosedUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoPausedUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoPostRollClosedUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoPostRollImpressionUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoResumedUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoRewardedUrls;
    @f(b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoSkippedUrls;

    public VideoTrackingDetails() {
    }

    public VideoTrackingDetails(e eVar) {
        if (eVar != null) {
            HashMap<b, List<c>> a2 = eVar.a();
            ArrayList arrayList = new ArrayList();
            addFractionUrls(a2.get(b.start), 0, arrayList);
            addFractionUrls(a2.get(b.firstQuartile), 25, arrayList);
            addFractionUrls(a2.get(b.midpoint), 50, arrayList);
            addFractionUrls(a2.get(b.thirdQuartile), 75, arrayList);
            addFractionUrls(a2.get(b.complete), 100, arrayList);
            this.fractionTrackingUrls = (FractionTrackingLink[]) arrayList.toArray(new FractionTrackingLink[arrayList.size()]);
            this.impressionUrls = urlToTrackingList(eVar.d());
            this.soundMuteUrls = trackingToTrackingList(a2.get(b.mute));
            this.soundUnmuteUrls = trackingToTrackingList(a2.get(b.unmute));
            this.videoPausedUrls = trackingToTrackingList(a2.get(b.pause));
            this.videoResumedUrls = trackingToTrackingList(a2.get(b.resume));
            this.videoSkippedUrls = trackingToTrackingList(a2.get(b.skip));
            this.videoPausedUrls = trackingToTrackingList(a2.get(b.pause));
            this.videoClosedUrls = trackingToTrackingList(a2.get(b.close));
            this.inlineErrorTrackingUrls = urlToTrackingList(eVar.e());
            this.videoClickTrackingUrls = urlToTrackingList(eVar.c().b());
            this.absoluteTrackingUrls = toAbsoluteTrackingList(a2.get(b.progress));
        }
    }

    public FractionTrackingLink[] getFractionTrackingUrls() {
        return this.fractionTrackingUrls;
    }

    public AbsoluteTrackingLink[] getAbsoluteTrackingUrls() {
        return this.absoluteTrackingUrls;
    }

    public ActionTrackingLink[] getImpressionUrls() {
        return this.impressionUrls;
    }

    public ActionTrackingLink[] getSoundUnmuteUrls() {
        return this.soundUnmuteUrls;
    }

    public ActionTrackingLink[] getCreativeViewUrls() {
        return this.creativeViewUrls;
    }

    public ActionTrackingLink[] getSoundMuteUrls() {
        return this.soundMuteUrls;
    }

    public ActionTrackingLink[] getVideoPausedUrls() {
        return this.videoPausedUrls;
    }

    public ActionTrackingLink[] getVideoResumedUrls() {
        return this.videoResumedUrls;
    }

    public ActionTrackingLink[] getVideoSkippedUrls() {
        return this.videoSkippedUrls;
    }

    public ActionTrackingLink[] getVideoClosedUrls() {
        return this.videoClosedUrls;
    }

    public ActionTrackingLink[] getVideoPostRollImpressionUrls() {
        return this.videoPostRollImpressionUrls;
    }

    public ActionTrackingLink[] getVideoPostRollClosedUrls() {
        return this.videoPostRollClosedUrls;
    }

    public ActionTrackingLink[] getVideoRewardedUrls() {
        return this.videoRewardedUrls;
    }

    public ActionTrackingLink[] getVideoClickTrackingUrls() {
        return this.videoClickTrackingUrls;
    }

    public ActionTrackingLink[] getInlineErrorTrackingUrls() {
        return this.inlineErrorTrackingUrls;
    }

    public String toString() {
        return "VideoTrackingDetails [fractionTrackingUrls=" + printTrackingLinks(this.fractionTrackingUrls) + ", absoluteTrackingUrls=" + printTrackingLinks(this.absoluteTrackingUrls) + ", impressionUrls=" + printTrackingLinks(this.impressionUrls) + ", creativeViewUrls=" + printTrackingLinks(this.creativeViewUrls) + ", soundMuteUrls=" + printTrackingLinks(this.soundMuteUrls) + ", soundUnmuteUrls=" + printTrackingLinks(this.soundUnmuteUrls) + ", videoPausedUrls=" + printTrackingLinks(this.videoPausedUrls) + ", videoResumedUrls=" + printTrackingLinks(this.videoResumedUrls) + ", videoSkippedUrls=" + printTrackingLinks(this.videoSkippedUrls) + ", videoClosedUrls=" + printTrackingLinks(this.videoClosedUrls) + ", videoPostRollImpressionUrls=" + printTrackingLinks(this.videoPostRollImpressionUrls) + ", videoPostRollClosedUrls=" + printTrackingLinks(this.videoPostRollClosedUrls) + ", videoRewardedUrls=" + printTrackingLinks(this.videoRewardedUrls) + ", videoClickTrackingUrls=" + printTrackingLinks(this.videoClickTrackingUrls) + ", inlineErrorTrackingUrls=" + printTrackingLinks(this.inlineErrorTrackingUrls) + "]";
    }

    private String printTrackingLinks(VideoTrackingLink[] videoTrackingLinkArr) {
        return videoTrackingLinkArr != null ? Arrays.toString(videoTrackingLinkArr) : "";
    }

    private static void addFractionUrls(List<c> list, int i, List<FractionTrackingLink> list2) {
        if (list != null) {
            for (c a2 : list) {
                FractionTrackingLink fractionTrackingLink = new FractionTrackingLink();
                fractionTrackingLink.setTrackingUrl(a2.a());
                fractionTrackingLink.setFraction(i);
                fractionTrackingLink.setAppendReplayParameter(true);
                fractionTrackingLink.setReplayParameter("");
                list2.add(fractionTrackingLink);
            }
        }
    }

    private static ActionTrackingLink[] trackingToTrackingList(List<c> list) {
        if (list == null) {
            return new ActionTrackingLink[0];
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (c a2 : list) {
            ActionTrackingLink actionTrackingLink = new ActionTrackingLink();
            actionTrackingLink.setTrackingUrl(a2.a());
            arrayList.add(actionTrackingLink);
        }
        return (ActionTrackingLink[]) arrayList.toArray(new ActionTrackingLink[arrayList.size()]);
    }

    private static ActionTrackingLink[] urlToTrackingList(List<String> list) {
        if (list == null) {
            return new ActionTrackingLink[0];
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String trackingUrl : list) {
            ActionTrackingLink actionTrackingLink = new ActionTrackingLink();
            actionTrackingLink.setTrackingUrl(trackingUrl);
            arrayList.add(actionTrackingLink);
        }
        return (ActionTrackingLink[]) arrayList.toArray(new ActionTrackingLink[arrayList.size()]);
    }

    private AbsoluteTrackingLink[] toAbsoluteTrackingList(List<c> list) {
        if (list == null) {
            return new AbsoluteTrackingLink[0];
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (c next : list) {
            AbsoluteTrackingLink absoluteTrackingLink = new AbsoluteTrackingLink();
            absoluteTrackingLink.setTrackingUrl(next.a());
            if (next.b() != -1) {
                absoluteTrackingLink.setVideoOffsetMillis(next.b());
            }
            arrayList.add(absoluteTrackingLink);
        }
        return (AbsoluteTrackingLink[]) arrayList.toArray(new AbsoluteTrackingLink[arrayList.size()]);
    }
}
