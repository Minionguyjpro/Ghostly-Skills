package com.integralads.avid.library.mopub.walking;

import android.view.View;
import android.view.ViewParent;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.utils.AvidViewUtil;
import com.integralads.avid.library.mopub.weakreference.AvidView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class AvidAdViewCache {
    private final AvidAdSessionRegistry adSessionRegistry;
    private final HashMap<View, String> adViews = new HashMap<>();
    private final HashMap<View, ArrayList<String>> friendlyObstructions = new HashMap<>();
    private final HashSet<String> hiddenSessionIds = new HashSet<>();
    private boolean isAdViewProcessed;
    private final HashSet<View> rootViews = new HashSet<>();
    private final HashSet<String> visibleSessionIds = new HashSet<>();

    public AvidAdViewCache(AvidAdSessionRegistry avidAdSessionRegistry) {
        this.adSessionRegistry = avidAdSessionRegistry;
    }

    public HashSet<String> getVisibleSessionIds() {
        return this.visibleSessionIds;
    }

    public HashSet<String> getHiddenSessionIds() {
        return this.hiddenSessionIds;
    }

    public void prepare() {
        for (InternalAvidAdSession next : this.adSessionRegistry.getInternalAvidAdSessions()) {
            View view = next.getView();
            if (next.isActive() && view != null) {
                if (buildRootViews(view)) {
                    this.visibleSessionIds.add(next.getAvidAdSessionId());
                    this.adViews.put(view, next.getAvidAdSessionId());
                    prepareFriendlyObstructions(next);
                } else {
                    this.hiddenSessionIds.add(next.getAvidAdSessionId());
                }
            }
        }
    }

    private boolean buildRootViews(View view) {
        if (!view.hasWindowFocus()) {
            return false;
        }
        HashSet hashSet = new HashSet();
        while (view != null) {
            if (!AvidViewUtil.isViewVisible(view)) {
                return false;
            }
            hashSet.add(view);
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        this.rootViews.addAll(hashSet);
        return true;
    }

    private void prepareFriendlyObstructions(InternalAvidAdSession internalAvidAdSession) {
        Iterator<AvidView> it = internalAvidAdSession.getObstructionsWhiteList().getWhiteList().iterator();
        while (it.hasNext()) {
            AvidView next = it.next();
            if (!next.isEmpty()) {
                addFriendlyObstruction((View) next.get(), internalAvidAdSession);
            }
        }
    }

    private void addFriendlyObstruction(View view, InternalAvidAdSession internalAvidAdSession) {
        ArrayList arrayList = this.friendlyObstructions.get(view);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.friendlyObstructions.put(view, arrayList);
        }
        arrayList.add(internalAvidAdSession.getAvidAdSessionId());
    }

    public void cleanup() {
        this.adViews.clear();
        this.friendlyObstructions.clear();
        this.rootViews.clear();
        this.visibleSessionIds.clear();
        this.hiddenSessionIds.clear();
        this.isAdViewProcessed = false;
    }

    public void onAdViewProcessed() {
        this.isAdViewProcessed = true;
    }

    public String getSessionId(View view) {
        if (this.adViews.size() == 0) {
            return null;
        }
        String str = this.adViews.get(view);
        if (str != null) {
            this.adViews.remove(view);
        }
        return str;
    }

    public ArrayList<String> getFriendlySessionIds(View view) {
        if (this.friendlyObstructions.size() == 0) {
            return null;
        }
        ArrayList<String> arrayList = this.friendlyObstructions.get(view);
        if (arrayList != null) {
            this.friendlyObstructions.remove(view);
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public ViewType getViewType(View view) {
        if (this.rootViews.contains(view)) {
            return ViewType.ROOT_VIEW;
        }
        return this.isAdViewProcessed ? ViewType.OBSTRUCTION_VIEW : ViewType.UNDERLYING_VIEW;
    }
}
