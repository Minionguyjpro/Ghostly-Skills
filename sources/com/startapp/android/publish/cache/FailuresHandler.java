package com.startapp.android.publish.cache;

import com.startapp.common.c.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: StartAppSDK */
public class FailuresHandler implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean infiniteLastRetry = true;
    @f(b = ArrayList.class, c = Integer.class)
    private List<Integer> intervals = Arrays.asList(new Integer[]{10, 30, 60, 300});

    public List<Integer> getIntervals() {
        return this.intervals;
    }

    public boolean isInfiniteLastRetry() {
        return this.infiniteLastRetry;
    }
}
