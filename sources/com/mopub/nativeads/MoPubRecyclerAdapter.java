package com.mopub.nativeads;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibilityTracker;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.util.List;
import java.util.WeakHashMap;

public final class MoPubRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static final int NATIVE_AD_VIEW_TYPE_BASE = -56;
    private MoPubNativeAdLoadedListener mAdLoadedListener;
    private final RecyclerView.AdapterDataObserver mAdapterDataObserver;
    /* access modifiers changed from: private */
    public final RecyclerView.Adapter mOriginalAdapter;
    private RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public ContentChangeStrategy mStrategy;
    /* access modifiers changed from: private */
    public final MoPubStreamAdPlacer mStreamAdPlacer;
    private final WeakHashMap<View, Integer> mViewPositionMap;
    private final VisibilityTracker mVisibilityTracker;

    public enum ContentChangeStrategy {
        INSERT_AT_END,
        MOVE_ALL_ADS_WITH_CONTENT,
        KEEP_ADS_FIXED
    }

    public MoPubRecyclerAdapter(Activity activity, RecyclerView.Adapter adapter) {
        this(activity, adapter, MoPubNativeAdPositioning.serverPositioning());
    }

    public MoPubRecyclerAdapter(Activity activity, RecyclerView.Adapter adapter, MoPubNativeAdPositioning.MoPubServerPositioning moPubServerPositioning) {
        this(new MoPubStreamAdPlacer(activity, moPubServerPositioning), adapter, new VisibilityTracker(activity));
    }

    public MoPubRecyclerAdapter(Activity activity, RecyclerView.Adapter adapter, MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning) {
        this(new MoPubStreamAdPlacer(activity, moPubClientPositioning), adapter, new VisibilityTracker(activity));
    }

    MoPubRecyclerAdapter(MoPubStreamAdPlacer moPubStreamAdPlacer, RecyclerView.Adapter adapter, VisibilityTracker visibilityTracker) {
        this.mStrategy = ContentChangeStrategy.INSERT_AT_END;
        this.mViewPositionMap = new WeakHashMap<>();
        this.mOriginalAdapter = adapter;
        this.mVisibilityTracker = visibilityTracker;
        visibilityTracker.setVisibilityTrackerListener(new VisibilityTracker.VisibilityTrackerListener() {
            public void onVisibilityChanged(List<View> list, List<View> list2) {
                MoPubRecyclerAdapter.this.handleVisibilityChanged(list, list2);
            }
        });
        setHasStableIdsInternal(this.mOriginalAdapter.hasStableIds());
        this.mStreamAdPlacer = moPubStreamAdPlacer;
        moPubStreamAdPlacer.setAdLoadedListener(new MoPubNativeAdLoadedListener() {
            public void onAdLoaded(int i) {
                MoPubRecyclerAdapter.this.handleAdLoaded(i);
            }

            public void onAdRemoved(int i) {
                MoPubRecyclerAdapter.this.handleAdRemoved(i);
            }
        });
        this.mStreamAdPlacer.setItemCount(this.mOriginalAdapter.getItemCount());
        AnonymousClass3 r2 = new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                MoPubRecyclerAdapter.this.mStreamAdPlacer.setItemCount(MoPubRecyclerAdapter.this.mOriginalAdapter.getItemCount());
                MoPubRecyclerAdapter.this.notifyDataSetChanged();
            }

            public void onItemRangeChanged(int i, int i2) {
                int adjustedPosition = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedPosition((i2 + i) - 1);
                int adjustedPosition2 = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedPosition(i);
                MoPubRecyclerAdapter.this.notifyItemRangeChanged(adjustedPosition2, (adjustedPosition - adjustedPosition2) + 1);
            }

            public void onItemRangeInserted(int i, int i2) {
                int adjustedPosition = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedPosition(i);
                int itemCount = MoPubRecyclerAdapter.this.mOriginalAdapter.getItemCount();
                MoPubRecyclerAdapter.this.mStreamAdPlacer.setItemCount(itemCount);
                boolean z = i + i2 >= itemCount;
                if (ContentChangeStrategy.KEEP_ADS_FIXED == MoPubRecyclerAdapter.this.mStrategy || (ContentChangeStrategy.INSERT_AT_END == MoPubRecyclerAdapter.this.mStrategy && z)) {
                    MoPubRecyclerAdapter.this.notifyDataSetChanged();
                    return;
                }
                for (int i3 = 0; i3 < i2; i3++) {
                    MoPubRecyclerAdapter.this.mStreamAdPlacer.insertItem(i);
                }
                MoPubRecyclerAdapter.this.notifyItemRangeInserted(adjustedPosition, i2);
            }

            public void onItemRangeRemoved(int i, int i2) {
                int adjustedPosition = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedPosition(i);
                int itemCount = MoPubRecyclerAdapter.this.mOriginalAdapter.getItemCount();
                MoPubRecyclerAdapter.this.mStreamAdPlacer.setItemCount(itemCount);
                boolean z = i + i2 >= itemCount;
                if (ContentChangeStrategy.KEEP_ADS_FIXED == MoPubRecyclerAdapter.this.mStrategy || (ContentChangeStrategy.INSERT_AT_END == MoPubRecyclerAdapter.this.mStrategy && z)) {
                    MoPubRecyclerAdapter.this.notifyDataSetChanged();
                    return;
                }
                int adjustedCount = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedCount(itemCount + i2);
                for (int i3 = 0; i3 < i2; i3++) {
                    MoPubRecyclerAdapter.this.mStreamAdPlacer.removeItem(i);
                }
                int adjustedCount2 = adjustedCount - MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedCount(itemCount);
                MoPubRecyclerAdapter.this.notifyItemRangeRemoved(adjustedPosition - (adjustedCount2 - i2), adjustedCount2);
            }

            public void onItemRangeMoved(int i, int i2, int i3) {
                MoPubRecyclerAdapter.this.notifyDataSetChanged();
            }
        };
        this.mAdapterDataObserver = r2;
        this.mOriginalAdapter.registerAdapterDataObserver(r2);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
    }

    public void setAdLoadedListener(MoPubNativeAdLoadedListener moPubNativeAdLoadedListener) {
        this.mAdLoadedListener = moPubNativeAdLoadedListener;
    }

    public void registerAdRenderer(MoPubAdRenderer moPubAdRenderer) {
        if (Preconditions.NoThrow.checkNotNull(moPubAdRenderer, "Cannot register a null adRenderer")) {
            this.mStreamAdPlacer.registerAdRenderer(moPubAdRenderer);
        }
    }

    public void loadAds(String str) {
        this.mStreamAdPlacer.loadAds(str);
    }

    public void loadAds(String str, RequestParameters requestParameters) {
        this.mStreamAdPlacer.loadAds(str, requestParameters);
    }

    public static int computeScrollOffset(LinearLayoutManager linearLayoutManager, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            return 0;
        }
        View view = viewHolder.itemView;
        if (linearLayoutManager.canScrollVertically()) {
            if (linearLayoutManager.getStackFromEnd()) {
                return view.getBottom();
            }
            return view.getTop();
        } else if (!linearLayoutManager.canScrollHorizontally()) {
            return 0;
        } else {
            if (linearLayoutManager.getStackFromEnd()) {
                return view.getRight();
            }
            return view.getLeft();
        }
    }

    public void refreshAds(String str) {
        refreshAds(str, (RequestParameters) null);
    }

    public void refreshAds(String str, RequestParameters requestParameters) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "This adapter is not attached to a RecyclerView and cannot be refreshed.");
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Can't refresh ads when there is no layout manager on a RecyclerView.");
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            int computeScrollOffset = computeScrollOffset(linearLayoutManager, this.mRecyclerView.findViewHolderForLayoutPosition(findFirstVisibleItemPosition));
            int max = Math.max(0, findFirstVisibleItemPosition - 1);
            while (this.mStreamAdPlacer.isAd(max) && max > 0) {
                max--;
            }
            int itemCount = getItemCount();
            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            while (this.mStreamAdPlacer.isAd(findLastVisibleItemPosition) && findLastVisibleItemPosition < itemCount - 1) {
                findLastVisibleItemPosition++;
            }
            int originalPosition = this.mStreamAdPlacer.getOriginalPosition(max);
            this.mStreamAdPlacer.removeAdsInRange(this.mStreamAdPlacer.getOriginalPosition(findLastVisibleItemPosition), this.mOriginalAdapter.getItemCount());
            int removeAdsInRange = this.mStreamAdPlacer.removeAdsInRange(0, originalPosition);
            if (removeAdsInRange > 0) {
                linearLayoutManager.scrollToPositionWithOffset(findFirstVisibleItemPosition - removeAdsInRange, computeScrollOffset);
            }
            loadAds(str, requestParameters);
        } else {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "This LayoutManager can't be refreshed.");
        }
    }

    public void clearAds() {
        this.mStreamAdPlacer.clearAds();
    }

    public boolean isAd(int i) {
        return this.mStreamAdPlacer.isAd(i);
    }

    public int getAdjustedPosition(int i) {
        return this.mStreamAdPlacer.getAdjustedPosition(i);
    }

    public int getOriginalPosition(int i) {
        return this.mStreamAdPlacer.getOriginalPosition(i);
    }

    public void setContentChangeStrategy(ContentChangeStrategy contentChangeStrategy) {
        if (Preconditions.NoThrow.checkNotNull(contentChangeStrategy)) {
            this.mStrategy = contentChangeStrategy;
        }
    }

    public int getItemCount() {
        return this.mStreamAdPlacer.getAdjustedCount(this.mOriginalAdapter.getItemCount());
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i < NATIVE_AD_VIEW_TYPE_BASE || i > this.mStreamAdPlacer.getAdViewTypeCount() + NATIVE_AD_VIEW_TYPE_BASE) {
            return this.mOriginalAdapter.onCreateViewHolder(viewGroup, i);
        }
        MoPubAdRenderer adRendererForViewType = this.mStreamAdPlacer.getAdRendererForViewType(i - NATIVE_AD_VIEW_TYPE_BASE);
        if (adRendererForViewType != null) {
            return new MoPubRecyclerViewHolder(adRendererForViewType.createAdView((Activity) viewGroup.getContext(), viewGroup));
        }
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "No view binder was registered for ads in MoPubRecyclerAdapter.");
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Object adData = this.mStreamAdPlacer.getAdData(i);
        if (adData != null) {
            this.mStreamAdPlacer.bindAdView((NativeAd) adData, viewHolder.itemView);
            return;
        }
        this.mViewPositionMap.put(viewHolder.itemView, Integer.valueOf(i));
        this.mVisibilityTracker.addView(viewHolder.itemView, 0, (Integer) null);
        this.mOriginalAdapter.onBindViewHolder(viewHolder, this.mStreamAdPlacer.getOriginalPosition(i));
    }

    public int getItemViewType(int i) {
        int adViewType = this.mStreamAdPlacer.getAdViewType(i);
        if (adViewType != 0) {
            return adViewType + NATIVE_AD_VIEW_TYPE_BASE;
        }
        return this.mOriginalAdapter.getItemViewType(this.mStreamAdPlacer.getOriginalPosition(i));
    }

    public void setHasStableIds(boolean z) {
        setHasStableIdsInternal(z);
        this.mOriginalAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
        this.mOriginalAdapter.setHasStableIds(z);
        this.mOriginalAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
    }

    public void destroy() {
        this.mOriginalAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
        this.mStreamAdPlacer.destroy();
        this.mVisibilityTracker.destroy();
    }

    public long getItemId(int i) {
        if (!this.mOriginalAdapter.hasStableIds()) {
            return -1;
        }
        Object adData = this.mStreamAdPlacer.getAdData(i);
        if (adData != null) {
            return (long) (-System.identityHashCode(adData));
        }
        return this.mOriginalAdapter.getItemId(this.mStreamAdPlacer.getOriginalPosition(i));
    }

    public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof MoPubRecyclerViewHolder) {
            return super.onFailedToRecycleView(viewHolder);
        }
        return this.mOriginalAdapter.onFailedToRecycleView(viewHolder);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof MoPubRecyclerViewHolder) {
            super.onViewAttachedToWindow(viewHolder);
        } else {
            this.mOriginalAdapter.onViewAttachedToWindow(viewHolder);
        }
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof MoPubRecyclerViewHolder) {
            super.onViewDetachedFromWindow(viewHolder);
        } else {
            this.mOriginalAdapter.onViewDetachedFromWindow(viewHolder);
        }
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof MoPubRecyclerViewHolder) {
            super.onViewRecycled(viewHolder);
        } else {
            this.mOriginalAdapter.onViewRecycled(viewHolder);
        }
    }

    /* access modifiers changed from: package-private */
    public void handleAdLoaded(int i) {
        MoPubNativeAdLoadedListener moPubNativeAdLoadedListener = this.mAdLoadedListener;
        if (moPubNativeAdLoadedListener != null) {
            moPubNativeAdLoadedListener.onAdLoaded(i);
        }
        notifyItemInserted(i);
    }

    /* access modifiers changed from: package-private */
    public void handleAdRemoved(int i) {
        MoPubNativeAdLoadedListener moPubNativeAdLoadedListener = this.mAdLoadedListener;
        if (moPubNativeAdLoadedListener != null) {
            moPubNativeAdLoadedListener.onAdRemoved(i);
        }
        notifyItemRemoved(i);
    }

    /* access modifiers changed from: private */
    public void handleVisibilityChanged(List<View> list, List<View> list2) {
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        for (View view : list) {
            Integer num = this.mViewPositionMap.get(view);
            if (num != null) {
                i = Math.min(num.intValue(), i);
                i2 = Math.max(num.intValue(), i2);
            }
        }
        this.mStreamAdPlacer.placeAdsInRange(i, i2 + 1);
    }

    private void setHasStableIdsInternal(boolean z) {
        super.setHasStableIds(z);
    }
}
