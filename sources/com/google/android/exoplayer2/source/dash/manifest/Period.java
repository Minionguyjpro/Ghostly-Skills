package com.google.android.exoplayer2.source.dash.manifest;

import java.util.List;

public class Period {
    public final List<AdaptationSet> adaptationSets;
    public final List<EventStream> eventStreams;
    public final String id;
    public final long startMs;

    public int getAdaptationSetIndex(int i) {
        int size = this.adaptationSets.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.adaptationSets.get(i2).type == i) {
                return i2;
            }
        }
        return -1;
    }
}
