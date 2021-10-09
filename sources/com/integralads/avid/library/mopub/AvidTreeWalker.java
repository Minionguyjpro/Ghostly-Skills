package com.integralads.avid.library.mopub;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.integralads.avid.library.mopub.processing.AvidProcessorFactory;
import com.integralads.avid.library.mopub.processing.IAvidNodeProcessor;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.utils.AvidTimestamp;
import com.integralads.avid.library.mopub.utils.AvidViewUtil;
import com.integralads.avid.library.mopub.walking.AvidAdViewCache;
import com.integralads.avid.library.mopub.walking.AvidStatePublisher;
import com.integralads.avid.library.mopub.walking.ViewType;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTaskQueue;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class AvidTreeWalker implements IAvidNodeProcessor.IAvidViewWalker {
    private static AvidTreeWalker avidTreeWalker = new AvidTreeWalker();
    /* access modifiers changed from: private */
    public static TreeWalkerHandler handler;
    /* access modifiers changed from: private */
    public static final Runnable viewTreeUpdaterRunnable = new Runnable() {
        public void run() {
            if (AvidTreeWalker.handler != null) {
                AvidTreeWalker.handler.sendEmptyMessage(0);
                AvidTreeWalker.handler.postDelayed(AvidTreeWalker.viewTreeUpdaterRunnable, 200);
            }
        }
    };
    private AvidAdViewCache adViewCache = new AvidAdViewCache(AvidAdSessionRegistry.getInstance());
    private double endTime;
    private int objectsCount;
    private AvidProcessorFactory processorFactory = new AvidProcessorFactory();
    private double startTime;
    private AvidStatePublisher statePublisher = new AvidStatePublisher(AvidAdSessionRegistry.getInstance(), new AvidAsyncTaskQueue());
    private List<AvidTreeWalkerTimeLogger> timeLoggers = new ArrayList();

    public interface AvidTreeWalkerTimeLogger {
        void onTreeProcessed(int i, long j);
    }

    public static AvidTreeWalker getInstance() {
        return avidTreeWalker;
    }

    public void addTimeLogger(AvidTreeWalkerTimeLogger avidTreeWalkerTimeLogger) {
        if (!this.timeLoggers.contains(avidTreeWalkerTimeLogger)) {
            this.timeLoggers.add(avidTreeWalkerTimeLogger);
        }
    }

    public void removeTimeLogger(AvidTreeWalkerTimeLogger avidTreeWalkerTimeLogger) {
        if (this.timeLoggers.contains(avidTreeWalkerTimeLogger)) {
            this.timeLoggers.remove(avidTreeWalkerTimeLogger);
        }
    }

    public void start() {
        startTreeWalkerUpdater();
        updateTreeState();
    }

    public void stop() {
        pause();
        this.timeLoggers.clear();
        this.statePublisher.cleanupCache();
    }

    public void pause() {
        stopTreeWalkerUpdater();
    }

    /* access modifiers changed from: private */
    public void updateTreeState() {
        beforeWalk();
        doWalk();
        afterWalk();
    }

    private void beforeWalk() {
        this.objectsCount = 0;
        this.startTime = AvidTimestamp.getCurrentTime();
    }

    private void afterWalk() {
        double currentTime = AvidTimestamp.getCurrentTime();
        this.endTime = currentTime;
        notifyTimeLogger((long) (currentTime - this.startTime));
    }

    /* access modifiers changed from: package-private */
    public void doWalk() {
        this.adViewCache.prepare();
        double currentTime = AvidTimestamp.getCurrentTime();
        IAvidNodeProcessor rootProcessor = this.processorFactory.getRootProcessor();
        if (this.adViewCache.getHiddenSessionIds().size() > 0) {
            this.statePublisher.publishEmptyState(rootProcessor.getState((View) null), this.adViewCache.getHiddenSessionIds(), currentTime);
        }
        if (this.adViewCache.getVisibleSessionIds().size() > 0) {
            JSONObject state = rootProcessor.getState((View) null);
            walkViewChildren((View) null, rootProcessor, state, ViewType.ROOT_VIEW);
            AvidJSONUtil.fixStateFrame(state);
            this.statePublisher.publishState(state, this.adViewCache.getVisibleSessionIds(), currentTime);
        } else {
            this.statePublisher.cleanupCache();
        }
        this.adViewCache.cleanup();
    }

    public void walkView(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject) {
        ViewType viewType;
        if (AvidViewUtil.isViewVisible(view) && (viewType = this.adViewCache.getViewType(view)) != ViewType.UNDERLYING_VIEW) {
            JSONObject state = iAvidNodeProcessor.getState(view);
            AvidJSONUtil.addChildState(jSONObject, state);
            if (!handleAdView(view, state)) {
                checkFriendlyObstruction(view, state);
                walkViewChildren(view, iAvidNodeProcessor, state, viewType);
            }
            this.objectsCount++;
        }
    }

    private void walkViewChildren(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject, ViewType viewType) {
        iAvidNodeProcessor.iterateChildren(view, jSONObject, this, viewType == ViewType.ROOT_VIEW);
    }

    private boolean handleAdView(View view, JSONObject jSONObject) {
        String sessionId = this.adViewCache.getSessionId(view);
        if (sessionId == null) {
            return false;
        }
        AvidJSONUtil.addAvidId(jSONObject, sessionId);
        this.adViewCache.onAdViewProcessed();
        return true;
    }

    private void checkFriendlyObstruction(View view, JSONObject jSONObject) {
        ArrayList<String> friendlySessionIds = this.adViewCache.getFriendlySessionIds(view);
        if (friendlySessionIds != null) {
            AvidJSONUtil.addFriendlyObstruction(jSONObject, friendlySessionIds);
        }
    }

    private void notifyTimeLogger(long j) {
        if (this.timeLoggers.size() > 0) {
            for (AvidTreeWalkerTimeLogger onTreeProcessed : this.timeLoggers) {
                onTreeProcessed.onTreeProcessed(this.objectsCount, j);
            }
        }
    }

    private void startTreeWalkerUpdater() {
        if (handler == null) {
            TreeWalkerHandler treeWalkerHandler = new TreeWalkerHandler();
            handler = treeWalkerHandler;
            treeWalkerHandler.postDelayed(viewTreeUpdaterRunnable, 200);
        }
    }

    private void stopTreeWalkerUpdater() {
        TreeWalkerHandler treeWalkerHandler = handler;
        if (treeWalkerHandler != null) {
            treeWalkerHandler.removeCallbacks(viewTreeUpdaterRunnable);
            handler = null;
        }
    }

    private static class TreeWalkerHandler extends Handler {
        private TreeWalkerHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            AvidTreeWalker.getInstance().updateTreeState();
        }
    }
}
