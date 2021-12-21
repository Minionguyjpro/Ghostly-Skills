package com.mopub.nativeads;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibilityTracker;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.util.List;
import java.util.WeakHashMap;

public class MoPubAdAdapter extends BaseAdapter {
    private MoPubNativeAdLoadedListener mAdLoadedListener;
    /* access modifiers changed from: private */
    public final Adapter mOriginalAdapter;
    /* access modifiers changed from: private */
    public final MoPubStreamAdPlacer mStreamAdPlacer;
    private final WeakHashMap<View, Integer> mViewPositionMap;
    private final VisibilityTracker mVisibilityTracker;

    public MoPubAdAdapter(Activity activity, Adapter adapter) {
        this(activity, adapter, MoPubNativeAdPositioning.serverPositioning());
    }

    public MoPubAdAdapter(Activity activity, Adapter adapter, MoPubNativeAdPositioning.MoPubServerPositioning moPubServerPositioning) {
        this(new MoPubStreamAdPlacer(activity, moPubServerPositioning), adapter, new VisibilityTracker(activity));
    }

    public MoPubAdAdapter(Activity activity, Adapter adapter, MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning) {
        this(new MoPubStreamAdPlacer(activity, moPubClientPositioning), adapter, new VisibilityTracker(activity));
    }

    MoPubAdAdapter(MoPubStreamAdPlacer moPubStreamAdPlacer, Adapter adapter, VisibilityTracker visibilityTracker) {
        this.mOriginalAdapter = adapter;
        this.mStreamAdPlacer = moPubStreamAdPlacer;
        this.mViewPositionMap = new WeakHashMap<>();
        this.mVisibilityTracker = visibilityTracker;
        visibilityTracker.setVisibilityTrackerListener(new VisibilityTracker.VisibilityTrackerListener() {
            public void onVisibilityChanged(List<View> list, List<View> list2) {
                MoPubAdAdapter.this.handleVisibilityChange(list);
            }
        });
        this.mOriginalAdapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                MoPubAdAdapter.this.mStreamAdPlacer.setItemCount(MoPubAdAdapter.this.mOriginalAdapter.getCount());
                MoPubAdAdapter.this.notifyDataSetChanged();
            }

            public void onInvalidated() {
                MoPubAdAdapter.this.notifyDataSetInvalidated();
            }
        });
        this.mStreamAdPlacer.setAdLoadedListener(new MoPubNativeAdLoadedListener() {
            public void onAdLoaded(int i) {
                MoPubAdAdapter.this.handleAdLoaded(i);
            }

            public void onAdRemoved(int i) {
                MoPubAdAdapter.this.handleAdRemoved(i);
            }
        });
        this.mStreamAdPlacer.setItemCount(this.mOriginalAdapter.getCount());
    }

    /* access modifiers changed from: package-private */
    public void handleAdLoaded(int i) {
        MoPubNativeAdLoadedListener moPubNativeAdLoadedListener = this.mAdLoadedListener;
        if (moPubNativeAdLoadedListener != null) {
            moPubNativeAdLoadedListener.onAdLoaded(i);
        }
        notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    public void handleAdRemoved(int i) {
        MoPubNativeAdLoadedListener moPubNativeAdLoadedListener = this.mAdLoadedListener;
        if (moPubNativeAdLoadedListener != null) {
            moPubNativeAdLoadedListener.onAdRemoved(i);
        }
        notifyDataSetChanged();
    }

    public final void registerAdRenderer(MoPubAdRenderer moPubAdRenderer) {
        if (Preconditions.NoThrow.checkNotNull(moPubAdRenderer, "Tried to set a null ad renderer on the placer.")) {
            this.mStreamAdPlacer.registerAdRenderer(moPubAdRenderer);
        }
    }

    public final void setAdLoadedListener(MoPubNativeAdLoadedListener moPubNativeAdLoadedListener) {
        this.mAdLoadedListener = moPubNativeAdLoadedListener;
    }

    public void loadAds(String str) {
        this.mStreamAdPlacer.loadAds(str);
    }

    public void loadAds(String str, RequestParameters requestParameters) {
        this.mStreamAdPlacer.loadAds(str, requestParameters);
    }

    public boolean isAd(int i) {
        return this.mStreamAdPlacer.isAd(i);
    }

    public void clearAds() {
        this.mStreamAdPlacer.clearAds();
    }

    public void destroy() {
        this.mStreamAdPlacer.destroy();
        this.mVisibilityTracker.destroy();
    }

    public boolean areAllItemsEnabled() {
        Adapter adapter = this.mOriginalAdapter;
        return (adapter instanceof ListAdapter) && ((ListAdapter) adapter).areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        if (!isAd(i)) {
            Adapter adapter = this.mOriginalAdapter;
            if (!(adapter instanceof ListAdapter) || !((ListAdapter) adapter).isEnabled(this.mStreamAdPlacer.getOriginalPosition(i))) {
                return false;
            }
        }
        return true;
    }

    public int getCount() {
        return this.mStreamAdPlacer.getAdjustedCount(this.mOriginalAdapter.getCount());
    }

    public Object getItem(int i) {
        Object adData = this.mStreamAdPlacer.getAdData(i);
        if (adData != null) {
            return adData;
        }
        return this.mOriginalAdapter.getItem(this.mStreamAdPlacer.getOriginalPosition(i));
    }

    public long getItemId(int i) {
        Object adData = this.mStreamAdPlacer.getAdData(i);
        if (adData != null) {
            return (long) (-System.identityHashCode(adData));
        }
        return this.mOriginalAdapter.getItemId(this.mStreamAdPlacer.getOriginalPosition(i));
    }

    public boolean hasStableIds() {
        return this.mOriginalAdapter.hasStableIds();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View adView = this.mStreamAdPlacer.getAdView(i, view, viewGroup);
        if (adView == null) {
            adView = this.mOriginalAdapter.getView(this.mStreamAdPlacer.getOriginalPosition(i), view, viewGroup);
        }
        this.mViewPositionMap.put(adView, Integer.valueOf(i));
        this.mVisibilityTracker.addView(adView, 0, (Integer) null);
        return adView;
    }

    public int getItemViewType(int i) {
        int adViewType = this.mStreamAdPlacer.getAdViewType(i);
        if (adViewType != 0) {
            return (adViewType + this.mOriginalAdapter.getViewTypeCount()) - 1;
        }
        return this.mOriginalAdapter.getItemViewType(this.mStreamAdPlacer.getOriginalPosition(i));
    }

    public int getViewTypeCount() {
        return this.mOriginalAdapter.getViewTypeCount() + this.mStreamAdPlacer.getAdViewTypeCount();
    }

    public boolean isEmpty() {
        return this.mOriginalAdapter.isEmpty() && this.mStreamAdPlacer.getAdjustedCount(0) == 0;
    }

    /* access modifiers changed from: private */
    public void handleVisibilityChange(List<View> list) {
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

    public int getOriginalPosition(int i) {
        return this.mStreamAdPlacer.getOriginalPosition(i);
    }

    public int getAdjustedPosition(int i) {
        return this.mStreamAdPlacer.getAdjustedPosition(i);
    }

    public void insertItem(int i) {
        this.mStreamAdPlacer.insertItem(i);
    }

    public void removeItem(int i) {
        this.mStreamAdPlacer.removeItem(i);
    }

    public void setOnClickListener(ListView listView, final AdapterView.OnItemClickListener onItemClickListener) {
        if (Preconditions.NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setOnClickListener with a null ListView")) {
            if (onItemClickListener == null) {
                listView.setOnItemClickListener((AdapterView.OnItemClickListener) null);
            } else {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (!MoPubAdAdapter.this.mStreamAdPlacer.isAd(i)) {
                            onItemClickListener.onItemClick(adapterView, view, MoPubAdAdapter.this.mStreamAdPlacer.getOriginalPosition(i), j);
                        }
                    }
                });
            }
        }
    }

    public void setOnItemLongClickListener(ListView listView, final AdapterView.OnItemLongClickListener onItemLongClickListener) {
        if (Preconditions.NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setOnItemLongClickListener with a null ListView")) {
            if (onItemLongClickListener == null) {
                listView.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) null);
            } else {
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (!MoPubAdAdapter.this.isAd(i)) {
                            return onItemLongClickListener.onItemLongClick(adapterView, view, MoPubAdAdapter.this.mStreamAdPlacer.getOriginalPosition(i), j);
                        }
                    }
                });
            }
        }
    }

    public void setOnItemSelectedListener(ListView listView, final AdapterView.OnItemSelectedListener onItemSelectedListener) {
        if (Preconditions.NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setOnItemSelectedListener with a null ListView")) {
            if (onItemSelectedListener == null) {
                listView.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
            } else {
                listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                        if (!MoPubAdAdapter.this.isAd(i)) {
                            onItemSelectedListener.onItemSelected(adapterView, view, MoPubAdAdapter.this.mStreamAdPlacer.getOriginalPosition(i), j);
                        }
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        onItemSelectedListener.onNothingSelected(adapterView);
                    }
                });
            }
        }
    }

    public void setSelection(ListView listView, int i) {
        if (Preconditions.NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setSelection with a null ListView")) {
            listView.setSelection(this.mStreamAdPlacer.getAdjustedPosition(i));
        }
    }

    public void smoothScrollToPosition(ListView listView, int i) {
        if (Preconditions.NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.smoothScrollToPosition with a null ListView")) {
            listView.smoothScrollToPosition(this.mStreamAdPlacer.getAdjustedPosition(i));
        }
    }

    public void refreshAds(ListView listView, String str) {
        refreshAds(listView, str, (RequestParameters) null);
    }

    public void refreshAds(ListView listView, String str, RequestParameters requestParameters) {
        int i;
        if (Preconditions.NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.refreshAds with a null ListView")) {
            View childAt = listView.getChildAt(0);
            if (childAt == null) {
                i = 0;
            } else {
                i = childAt.getTop();
            }
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            int max = Math.max(firstVisiblePosition - 1, 0);
            while (this.mStreamAdPlacer.isAd(max) && max > 0) {
                max--;
            }
            int lastVisiblePosition = listView.getLastVisiblePosition();
            while (this.mStreamAdPlacer.isAd(lastVisiblePosition) && lastVisiblePosition < getCount() - 1) {
                lastVisiblePosition++;
            }
            int originalPosition = this.mStreamAdPlacer.getOriginalPosition(max);
            this.mStreamAdPlacer.removeAdsInRange(this.mStreamAdPlacer.getOriginalCount(lastVisiblePosition + 1), this.mStreamAdPlacer.getOriginalCount(getCount()));
            int removeAdsInRange = this.mStreamAdPlacer.removeAdsInRange(0, originalPosition);
            if (removeAdsInRange > 0) {
                listView.setSelectionFromTop(firstVisiblePosition - removeAdsInRange, i);
            }
            loadAds(str, requestParameters);
        }
    }
}
